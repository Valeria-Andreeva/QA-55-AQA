package Homework6;

import java.util.List;

public class MainPhone {
    public static void main(String[] args) {

        PhoneDirectory directory = new PhoneDirectory();

        directory.add("Иванов", "+7-900-111-11-11");
        directory.add("Петров", "+7-900-222-22-22");
        directory.add("Иванов", "+7-900-333-33-33"); // Второй номер для Иванова
        directory.add("Сидоров", "+7-900-444-44-44");
        directory.add("Петров", "+7-900-555-55-55"); // Второй номер для Петрова

        System.out.println("\n--- Поиск номера ---");

        List<String> ivanovPhones = directory.get("Иванов");
        System.out.println("Телефоны Иванова: " + ivanovPhones);

        List<String> petrovPhones = directory.get("Петров");
        System.out.println("Телефоны Петрова: " + petrovPhones);

        List<String> smirnovPhones = directory.get("Смирнов");
        if (smirnovPhones.isEmpty()) {
            System.out.println("Смирнов не найден в справочнике.");
        }
    }
}