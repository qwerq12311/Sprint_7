import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderCreationEndpoint {

    public static final String ENDPOINT_ORDER_CREATION = "/api/v1/orders";
    public static final String ENDPOINT_ORDER_CANCEL = "/api/v1/orders/cancel";

    static {
        RestAssured.baseURI = TestConfig.BASE_URL;
        RestAssured.requestSpecification = given().contentType(ContentType.JSON);
    }

    public static Response createOrder(OrderDetails orderDetails) {
        return given()
                .body(orderDetails)
                .when()
                .post(ENDPOINT_ORDER_CREATION)
                .then()
                .statusCode(201)
                .body("track", notNullValue())
                .extract()
                .response();
    }

    public static Response cancelOrder(String track) {
        return given()
                .pathParam("track", track)
                .when()
                .put(ENDPOINT_ORDER_CANCEL + "?track={track}");
    }

    public static OrderDetails createOrderRequest() {
        TestDataOrder testDataOrder = new TestDataOrder();
        OrderDetails orderDetails = testDataOrder.createOrderDetails();
        orderDetails.setFirstName("Ibragim");
        orderDetails.setLastName("Kandibober");
        orderDetails.setAddress("123 Afghanistan");
        orderDetails.setMetroStation("Nearest Station");
        orderDetails.setPhone("1111-22");
        orderDetails.setRentTime(4);
        orderDetails.setDeliveryDate("2023-07-30T21:00:00.000Z");
        orderDetails.setComment("FASTEEEEER");
        orderDetails.setColor(Arrays.asList("BLACK", "GREY"));
        return orderDetails;
    }
}
