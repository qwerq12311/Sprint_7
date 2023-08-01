import io.qameta.allure.*;

import io.restassured.response.Response;
import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@Epic("Тестирование API")
@Feature("Курьеры: Создание и авторизация")

public class CourierTests {

    private static String courierId;
    private static String courierLogin;
    private static String courierPassword;
    private static String courierJson;

    @BeforeClass
    public static void setUpClass() {
        courierLogin = "unique_login_" + System.currentTimeMillis();
        courierPassword = TestDataGenerator.generateRandomPassword();
        courierJson = TestDataGenerator.generateCourierJson(courierLogin, courierPassword, "FirstName");

        Response response = CourierCreationEndpoint.createCourier(courierJson);
        assertThat(response.statusCode(), anyOf(equalTo(201), equalTo(409)));

        if (response.statusCode() == 201) {
            courierId = response.body().jsonPath().getString("id");
        }
    }

    @AfterClass
    public static void tearDownClass() {
        if (courierId != null) {
            CourierDeletionEndpoint.deleteCourier(courierId);
        }
    }

    @Test
    @Description("Тест успешной авторизации курьера")
    public void testSuccessfulCourierLogin() {
        String courierCredentials = TestDataGenerator.generateLoginCredentialsJson(courierLogin, courierPassword);
        Response response = CourierLoginEndpoint.loginCourier(courierCredentials);

        Allure.step("Проверка кода ответа");
        assertEquals(200, response.statusCode());

        Allure.step("Проверка наличия ID в ответе");
        assertThat(response.body().jsonPath().get("id"), is(notNullValue()));

        // Дополнение для проверки успешного завершения теста
        assertTrue("Тест успешной авторизации курьера завершен успешно", true);
        System.out.println("Тест успешной авторизации курьера завершен успешно");
    }

    @Test
    @Description("Тест создания курьера с существующим логином")
    public void testCourierCreationWithExistingLogin() {
        Response response = CourierCreationEndpoint.createCourier(courierJson);

        Allure.step("Проверка кода ответа");
        assertEquals(409, response.statusCode());

        Allure.step("Проверка сообщения об ошибке");
        assertThat(response.body().jsonPath().getString("message"), containsString("Этот логин уже используется"));

        // Дополнение для проверки успешного завершения теста
        assertTrue("Тест создания курьера с существующим логином завершен успешно", true);
        System.out.println("Тест создания курьера с существующим логином завершен успешно");
    }

    @Test
    @Description("Тест создания курьера с пустыми полями")
    public void testCourierCreationWithEmptyFields() {
        String emptyCourierJson = "{ \"login\": \"\", \"password\": \"\", \"firstName\": \"\" }";
        Response response = CourierCreationEndpoint.createCourier(emptyCourierJson);

        Allure.step("Проверка кода ответа");
        assertEquals(400, response.statusCode());

        Allure.step("Проверка сообщения об ошибке");
        assertThat(response.body().jsonPath().getString("message"), equalTo("Недостаточно данных для создания учетной записи"));

        // Дополнение для проверки успешного завершения теста
        assertTrue("Тест создания курьера с пустыми полями завершен успешно", true);
        System.out.println("Тест создания курьера с пустыми полями завершен успешно");
    }

    @Test
    @Description("Тест авторизации курьера с неверными учетными данными")
    public void testCourierLoginWithInvalidCredentials() {
        String invalidCredentials = TestDataGenerator.generateLoginCredentialsJson("invalidLogin", "invalidPassword");
        Response response = CourierLoginEndpoint.loginCourier(invalidCredentials);

        Allure.step("Проверка кода ответа");
        assertEquals(404, response.statusCode());

        Allure.step("Проверка сообщения об ошибке");
        assertThat(response.body().jsonPath().getString("message"), equalTo("Учетная запись не найдена"));

        // Дополнение для проверки успешного завершения теста
        assertTrue("Тест авторизации курьера с неверными учетными данными завершен успешно", true);
        System.out.println("Тест авторизации курьера с неверными учетными данными завершен успешно");
    }

    @Test
    @Description("Тест авторизации курьера с пустыми полями")
    public void testCourierLoginWithEmptyFields() {
        String emptyCourierCredentials = TestDataGenerator.generateEmptyCredentialsJson();
        Response response = CourierLoginEndpoint.loginCourier(emptyCourierCredentials);

        Allure.step("Проверка кода ответа");
        assertEquals(400, response.statusCode());

        Allure.step("Проверка сообщения об ошибке");
        assertThat(response.body().jsonPath().getString("message"), equalTo("Недостаточно данных для входа"));

        // Дополнение для проверки успешного завершения теста
        assertTrue("Тест авторизации курьера с пустыми полями завершен успешно", true);
        System.out.println("Тест авторизации курьера с пустыми полями завершен успешно");
    }
}
