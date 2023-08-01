import io.qameta.allure.restassured.AllureRestAssured;
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

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderCreationEndpointTest {

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
        RestAssured.baseURI = TestConfig.BASE_URL;
        // Подключение плагина allure-rest-assured
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void testCreateOrder() {
        Response response = given()
                .contentType("application/json")
                .body(orderDetails)
                .when()
                .post(OrderCreationEndpoint.ENDPOINT_ORDER_CREATION)
                .then()
                .statusCode(201)
                .body("track", notNullValue())
                .extract().response();

        // Получение значения track из ответа и вывод сообщения
        Integer track = response.path("track");
        createdOrderTracks.add(track);

        System.out.println("Заказ с номером отслеживания : " + track + " успешно создан");
    }

    @AfterClass
    public static void cleanup() {
        for (Integer track : createdOrderTracks) {
            OrderCreationEndpoint.cancelOrder(String.valueOf(track));
            System.out.println("Заказ с номером отслеживания : " + track + " успешно удален");
        }
    }
}
