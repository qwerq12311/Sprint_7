import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CourierOrdersCountEndpoint {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String ENDPOINT_COURIER_ORDERS_COUNT = "/api/v1/courier/:id/ordersCount";

    public static Response getOrdersCountForCourier(int courierId) {
        RestAssured.baseURI = BASE_URL;
        String endpoint = ENDPOINT_COURIER_ORDERS_COUNT.replace(":id", String.valueOf(courierId));

        return RestAssured.get(endpoint);
    }
}
