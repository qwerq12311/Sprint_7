public class TestDataOrder {

    public static String getOrdersData() {
        return "{ \"orders\": [ \n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"courierId\": null,\n" +
                "      \"firstName\": \"Иван\",\n" +
                "      \"lastName\": \"Петров\",\n" +
                "      \"address\": \"ул. Центральная, д. 123\",\n" +
                "      \"metroStation\": \"2\",\n" +
                "      \"phone\": \"555-123-456\",\n" +
                "      \"rentTime\": 2,\n" +
                "      \"deliveryDate\": \"2023-07-30T12:00:00.000Z\",\n" +
                "      \"track\": 12345,\n" +
                "      \"color\": [\"BLACK\", \"GREY\"],\n" +
                "      \"comment\": \"Заказ №1\",\n" +
                "      \"createdAt\": \"2023-07-30T10:00:00.000Z\",\n" +
                "      \"updatedAt\": \"2023-07-30T10:30:00.000Z\",\n" +
                "      \"status\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"courierId\": null,\n" +
                "      \"firstName\": \"Анна\",\n" +
                "      \"lastName\": \"Сидорова\",\n" +
                "      \"address\": \"ул. Парковая, д. 456\",\n" +
                "      \"metroStation\": \"4\",\n" +
                "      \"phone\": \"555-987-654\",\n" +
                "      \"rentTime\": 1,\n" +
                "      \"deliveryDate\": \"2023-07-30T15:00:00.000Z\",\n" +
                "      \"track\": 98765,\n" +
                "      \"color\": [\"RED\", \"WHITE\"],\n" +
                "      \"comment\": \"Заказ №2\",\n" +
                "      \"createdAt\": \"2023-07-30T11:00:00.000Z\",\n" +
                "      \"updatedAt\": \"2023-07-30T11:30:00.000Z\",\n" +
                "      \"status\": 1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"pageInfo\": {\n" +
                "    \"page\": 0,\n" +
                "    \"total\": 2,\n" +
                "    \"limit\": 2\n" +
                "  },\n" +
                "  \"availableStations\": [\n" +
                "    {\n" +
                "      \"name\": \"Черкизовская\",\n" +
                "      \"number\": \"2\",\n" +
                "      \"color\": \"#D92B2C\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Преображенская площадь\",\n" +
                "      \"number\": \"3\",\n" +
                "      \"color\": \"#D92B2C\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Сокольники\",\n" +
                "      \"number\": \"4\",\n" +
                "      \"color\": \"#D92B2C\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}
