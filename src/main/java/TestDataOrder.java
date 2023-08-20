import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

public class TestDataOrder {

    public String getOrdersData() {
        List<String> color1 = Arrays.asList("BLACK", "GREY");
        List<String> color2 = Arrays.asList("RED", "WHITE");

        OrderDetails order1 = new OrderDetails("Иван", "Петров", "ул. Центральная, д. 123",
                "2", "555-123-456", 2, "2023-07-30T12:00:00.000Z",
                "Заказ №1", color1);
        OrderDetails order2 = new OrderDetails("Анна", "Сидорова", "ул. Парковая, д. 456",
                "4", "555-987-654", 1, "2023-07-30T15:00:00.000Z",
                "Заказ №2", color2);

        List<OrderDetails> orderDetailsList = Arrays.asList(order1, order2);

        return new Gson().toJson(orderDetailsList);
    }

    public OrderDetails createOrderDetails() {
        List<String> color1 = Arrays.asList("BLACK", "GREY");

        return new OrderDetails("Иван", "Петров", "ул. Центральная, д. 123",
                "2", "555-123-456", 2, "2023-07-30T12:00:00.000Z",
                "Заказ №1", color1);
    }
}
