import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierLoginEndpoint {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String ENDPOINT_COURIER_LOGIN = "/api/v1/courier/login";

    public static Response loginCourier(String courierCredentialsJson) {
        RestAssured.baseURI = BASE_URL;
        return given()
                .contentType(ContentType.JSON)
                .body(courierCredentialsJson)
                .when()
                .post(ENDPOINT_COURIER_LOGIN);


    }
}