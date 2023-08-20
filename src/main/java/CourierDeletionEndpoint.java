import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierDeletionEndpoint {

    private static final String ENDPOINT_COURIER_DELETE = "/api/v1/courier/:id";

    public static void deleteCourier(String courierId) {
        RestAssured.baseURI = TestConfig.BASE_URL;
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(ENDPOINT_COURIER_DELETE, courierId)
                .then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }
}