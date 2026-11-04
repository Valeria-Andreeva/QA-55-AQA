package Homework5;

public class Main {

    public static void main(String[] args) {
        String[][] validArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] invalidDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "пятнадцать", "16"}
        };

        String[][] invalidSizeArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        System.out.println("--- Тест 1: Правильный массив ---");
        try {
            System.out.println("Сумма: " + calculateSum(validArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n--- Тест 2: Ошибка в данных ---");
        try {
            System.out.println("Сумма: " + calculateSum(invalidDataArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n--- Тест 3: Неправильный размер ---");
        try {
            System.out.println("Сумма: " + calculateSum(invalidSizeArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n--- Тест 4: ArrayIndexOutOfBoundsException ---");
        try {
            int[] arr = new int[2];
            arr[5] = 10;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймали исключение: " + e);
        }
    }

    public static int calculateSum(String[][] arr) throws MyArraySizeException, MyArrayDataException {

        if (arr.length != 4 || arr[0].length != 4) {
            throw new MyArraySizeException("Неверный размер массива! Должен быть 4x4.");
        }

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]: " + arr[i][j]);
                }
            }
        }
        return sum;
    }
}