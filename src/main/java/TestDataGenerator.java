import com.github.javafaker.Faker;

public class TestDataGenerator {

    public static final Faker faker = new Faker();

    public static String generateCourierJson(String login, String password, String firstName) {
        return String.format("{ \"firstName\": \"%s\", \"login\": \"%s\", \"password\": \"%s\" }",
                firstName, login, password);
    }

    public static String generateLoginCredentialsJson(String login, String password) {
        return String.format("{ \"login\": \"%s\", \"password\": \"%s\" }", login, password);
    }

    public static String generateRandomPassword() {
        return faker.internet().password();
    }

        // Метод для генерации тестовых данных с пустыми полями для авторизации курьера
        public static String generateEmptyCredentialsJson () {
            return generateLoginCredentialsJson("", "");
        }
    }

