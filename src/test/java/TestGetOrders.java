import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TestGetOrders {

    // Определяем класс для представления данных о заказе
    static class Order {
        // Поля заказа (измените типы данных, если необходимо)
        private int id;
        private Integer courierId;
        private String firstName;
        private String lastName;
        private String address;
        private String metroStation;
        private String phone;
        private int rentTime;
        private String deliveryDate;
        private int track;
        private List<String> color;
        private String comment;
        private String createdAt;
        private String updatedAt;
        private int status;
    }

    // Определяем класс для представления ответа от сервера
    public static class OrderResponse {
        // Список заказов
        private List<Order> orders;

        // Геттер для получения списка заказов
        public List<Order> getOrders() {
            return orders;
        }
    }

    // Метод для получения тестовых данных в формате JSON
    public static String getOrdersData() {
        // Используем класс TestDataOrder для получения тестовых данных
        return TestDataOrder.getOrdersData();
    }

    // Метод для генерации случайного идентификатора курьера
    private int generateRandomCourierId() {
        Random random = new Random();
        return random.nextInt(100) + 1; // Генерируем значение от 1 до 100
    }

    @Test
    @Description("Тест проверки получения списка заказов")
    public void testGetOrders() {
        // Отмечаем тестовый метод как шаг (step) для Allure отчета
        allureStep("Шаг 1: Получение списка заказов");

        // Получаем тестовые данные в формате JSON
        String responseBody = getOrdersData();

        // Создаем заглушку для интерфейса HttpClient
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        // Устанавливаем поведение заглушки для метода getOrders
        when(httpClient.getOrders(Mockito.anyInt())).thenReturn(responseBody);

        // Генерируем случайный идентификатор курьера
        int courierId = generateRandomCourierId();
        // Создаем объект OrderResponse с использованием заглушки для HttpClient
        Gson gson = new Gson();
        OrderResponse orderResponse = gson.fromJson(responseBody, OrderResponse.class);

        // Вызываем метод getOrders для получения списка заказов
        List<Order> actualOrders = orderResponse.getOrders();

        // Проверяем, что список заказов не пустой
        assertFalse(actualOrders.isEmpty());
    }

    // Аннотация @Step для обозначения шага в Allure отчете
    @Step("Щаш")
    private void allureStep(String stepName) {
    }

    // Определяем интерфейс HttpClient, который будет использоваться для получения данных о заказах
    interface HttpClient {
        String getOrders(int courierId);
    }
}
