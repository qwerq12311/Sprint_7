import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrderAcceptanceEndpoint {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String ENDPOINT_ORDER_ACCEPT = "/api/v1/orders/accept/:id";

    // Метод для принятия заказа курьером
    public static void acceptOrder(String orderId, String courierId) {
        RestAssured.baseURI = BASE_URL;
        // Заменяем плейсхолдер ":id" на номер заказа (orderId)
        String endpoint = ENDPOINT_ORDER_ACCEPT.replace(":id", orderId);
        // Выполняем PUT-запрос с параметром "courierId"
        Response response = RestAssured.given()
                .queryParam("courierId", courierId)
                .put(endpoint);
        response.then().statusCode(200); // Проверяем, что статус код - 200 (OK)
    }
}
