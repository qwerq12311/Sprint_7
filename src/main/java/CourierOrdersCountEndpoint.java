import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CourierOrdersCountEndpoint {


    private static final String ENDPOINT_COURIER_ORDERS_COUNT = "/api/v1/courier/:id/ordersCount";

    public static Response getOrdersCountForCourier(int courierId) {

        String endpoint = String.format(ENDPOINT_COURIER_ORDERS_COUNT, courierId);
        RestAssured.baseURI = TestConfig.BASE_URL;

        return RestAssured.get(endpoint);
    }
}
