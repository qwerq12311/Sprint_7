import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestGetOrders {

    @Test
    @Description("Тест проверки получения списка заказов")
    public void testGetOrders() {
        allureStep("Шаг 1: Получение списка заказов");

        HttpClient httpClient = mock(HttpClient.class);
        when(httpClient.getOrders(Mockito.anyInt())).thenReturn(getOrdersData());

        int courierId = generateRandomCourierId();
        String responseBody = httpClient.getOrders(courierId);
        Gson gson = new Gson();
        List<OrderDetails> ordersList = gson.fromJson(responseBody, new TypeToken<List<OrderDetails>>(){}.getType());

        assertFalse(ordersList.isEmpty());
    }

    private int generateRandomCourierId() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public String getOrdersData() {
        TestDataOrder testDataOrder = new TestDataOrder();
        return testDataOrder.getOrdersData();
    }

    @Step("тест-тест-тест")
    private void allureStep(String stepName) {
    }

    interface HttpClient {
        String getOrders(int courierId);
    }
}
