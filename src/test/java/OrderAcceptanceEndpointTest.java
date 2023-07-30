/*import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderAcceptanceEndpointTest {

    @Test
    public void testOrderAcceptanceWithLoginResponse() {
        // Создаем нового курьера
        String courierJson = "{\n" +
                "  \"firstName\": \"Courier\",\n" +
                "  \"lastName\": \"Test\",\n" +
                "  \"login\": \"test\",\n" +
                "  \"password\": \"test123\"\n" +
                "}";


        // Авторизуемся и получаем курьер ID
        int authorizedCourierId = authenticateAndGetCourierId("test", "test123");


        // Создаем новый заказ
        OrderDetails orderDetails = OrderCreationEndpoint.createOrderRequest();
        Response orderCreationResponse = OrderCreationEndpoint.createOrder(orderDetails);

        // Проверяем, что создание заказа было успешным
        String orderId = orderCreationResponse.then().extract().path("track");
        assertNotNull(orderId, "Ошибка при создании заказа");

        // Принимаем заказ курьером
        OrderAcceptanceEndpoint.acceptOrder(orderId, String.valueOf(authorizedCourierId));
    }

    private int createCourierAndGetCourierId(String courierJson) {
        Response courierCreationResponse = CourierCreationEndpoint.createCourier(courierJson);
        return courierCreationResponse.then().extract().path("id");
    }

    private int authenticateAndGetCourierId(String login, String password) {
        String authenticationJson = "{\n" +
                "  \"login\": \"" + login + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";
        Response authenticationResponse = CourierLoginEndpoint.loginCourier(authenticationJson);
        return authenticationResponse.then().extract().path("id");
    }
}
*/