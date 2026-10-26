package Homework3;

public class Product {
    private String name;
    private String productionDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isBooked;

    public Product(String name, String productionDate, String manufacturer, String countryOfOrigin, double price, boolean isBooked) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isBooked = isBooked;
    }

    public void printInfo() {
        System.out.println("Товар: " + name +
                ", Дата: " + productionDate +
                ", Производитель: " + manufacturer +
                ", Страна: " + countryOfOrigin +
                ", Цена: " + price +
                ", Забронирован: " + (isBooked ? "Да" : "Нет"));
    }
}

