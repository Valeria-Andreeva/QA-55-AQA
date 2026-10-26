package Homework3;

public class Main {
    public static void main(String[] args) {
        // --- Задание 2: Массив из 5 товаров ---
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 15", "15.09.2024", "Apple", "USA", 6999, false);
        productsArray[2] = new Product("Xiaomi 14", "01.01.2025", "Xiaomi", "China", 3999, true);
        productsArray[3] = new Product("Google Pixel 8", "10.10.2024", "Google", "USA", 4999, false);
        productsArray[4] = new Product("Huawei P60", "05.05.2024", "Huawei", "China", 4500, true);

        System.out.println("--- Список товаров ---");
        for (Product p : productsArray) {
            p.printInfo();
        }

        // --- Задание 3: Парк с аттракционами ---
        System.out.println("\n--- Парк аттракционов ---");
        Park park = new Park();

        Park.Attraction ferrisWheel = park.new Attraction("Колесо обозрения", "10:00 - 22:00", 500);
        Park.Attraction rollerCoaster = park.new Attraction("Американские горки", "12:00 - 20:00", 800);

        ferrisWheel.printInfo();
        rollerCoaster.printInfo();
    }
}

