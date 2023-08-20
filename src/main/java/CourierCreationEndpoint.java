import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierCreationEndpoint {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String ENDPOINT_COURIER_CREATE = "/api/v1/courier";

    public static Response createCourier(String courierJson) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .contentType(ContentType.JSON)
                .body(courierJson)
                .when()
                .post(ENDPOINT_COURIER_CREATE);
    }
}