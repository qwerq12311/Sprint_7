import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
@Epic("Тестирование API")
@Feature("Создание заказа. Затем заказы отменяем")
public class OrderCreationEndpointTest {

    // Отслеживание номеров (track) созданных заказов
    private static List<Integer> createdOrderTracks = new ArrayList<>();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // Обязательные поля заполнены, color не указан
                {new OrderDetails("Ibragim", "Kandibober", "123 Afganistan", "Nearest Station", "1111-22", 4,
                        "2023-07-30T21:00:00.000Z", "FASTEEEEER", null)},

                // Обязательные поля заполнены, указан только один цвет BLACK
                {new OrderDetails("Ibragim", "Kandibober", "123 Afganistan", "Nearest Station", "1111-22", 4,
                        "2023-07-30T21:00:00.000Z", "FASTEEEEER", Arrays.asList("BLACK"))},

                // Обязательные поля заполнены, указан только один цвет GREY
                {new OrderDetails("Ibragim", "Kandibober", "123 Afganistan", "Nearest Station", "1111-22", 4,
                        "2023-07-30T21:00:00.000Z", "FASTEEEEER", Arrays.asList("GREY"))},

                // Обязательные поля заполнены, указаны оба цвета: BLACK и GREY
                {new OrderDetails("Ibragim", "Kandibober", "123 Afganistan", "Nearest Station", "1111-22", 4,
                        "2023-07-30T21:00:00.000Z", "FASTEEEEER", Arrays.asList("BLACK", "GREY"))}
        });
    }

    @Parameterized.Parameter
    public OrderDetails orderDetails;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = OrderCreationEndpoint.BASE_URL;
    }

    @Test
    @Description("Тест на создание заказа")
    public void testCreateOrder() {
        // Шаг 1: Подготовка данных для создания заказа
        assertNotNull(orderDetails.getFirstName());
        assertNotNull(orderDetails.getLastName());
        assertNotNull(orderDetails.getAddress());
        assertNotNull(orderDetails.getMetroStation());
        assertNotNull(orderDetails.getPhone());
        assertTrue(orderDetails.getRentTime() > 0);
        assertNotNull(orderDetails.getDeliveryDate());

        // Шаг 2: Отправка запроса на создание заказа
        Response response = OrderCreationEndpoint.createOrder(orderDetails);
        response.then().statusCode(201);

        // Шаг 3: Проверка, что в ответе есть номер отслеживания (track)
        Integer track = response.jsonPath().getInt("track");
        assertNotNull(track);

        // Добавляем номер отслеживания в список созданных заказов
        createdOrderTracks.add(track);

        // Шаг 4: Вывод информации о созданном заказе
        System.out.println("Заказ создан. Номер отслеживания (track): " + track);

        // Allure: добавление шага в отчет
        Allure.step("Заказ с номером отслеживания (track): " + track + " успешно создан");
    }

    @AfterClass
    public static void cleanup() {
        for (Integer track : createdOrderTracks) {
            Response response = OrderCreationEndpoint.cancelOrder(track.toString());
            if (response != null && response.getStatusCode() == 200) {
                System.out.println("Заказ с номером отслеживания (track): " + track + " успешно отменен");
                // Allure: добавление шага в отчет
                Allure.step("Заказ с номером отслеживания (track): " + track + " успешно отменен");
            } else {
                System.out.println("Не удалось отменить заказ с номером отслеживания (track): " + track);
                // Allure: добавление шага в отчет
                Allure.step("Не удалось отменить заказ с номером отслеживания (track): " + track);
            }
        }
    }
}