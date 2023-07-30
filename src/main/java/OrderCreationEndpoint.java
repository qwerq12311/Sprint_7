import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OrderCreationEndpoint {

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    public static final String ENDPOINT_ORDER_CREATION = "/api/v1/orders";

    public static final String ENDPOINT_ORDER_CANCEL = "/api/v1/orders/cancel";

    public static Response createOrder(OrderDetails orderDetails) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .contentType(ContentType.JSON)
                .body(orderDetails)
                .when()
                .post(ENDPOINT_ORDER_CREATION)
                .then()
                .statusCode(201) // Verify status code is 201 (Created)
                .body("track", notNullValue()) // Verify the "track" field is not null
                .extract()
                .response();
    }

    public static Response cancelOrder(String track) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .contentType(ContentType.JSON)
                .pathParam("track", track) // Pass the "track" parameter in the URL
                .when()
                .put(ENDPOINT_ORDER_CANCEL + "?track={track}");
    }

    public static OrderDetails createOrderRequest() {
        // Create and return an OrderDetails object with sample parameterized data
        OrderDetails orderDetails = new OrderDetails();
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
