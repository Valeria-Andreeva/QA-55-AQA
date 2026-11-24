import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MtsPaymentPage;

import java.util.List;

public class MtsPageObjectTest {

    private WebDriver driver;
    private MtsPaymentPage paymentPage;

    @BeforeEach
    public void setUp() {

        driver = new ChromeDriver();

        paymentPage = new MtsPaymentPage(driver);

        paymentPage.openPage();
    }

    // Проверка надписей в полях
    private void checkPlaceholders(String paymentType) {

        System.out.println("\n================================");
        System.out.println("Проверяем вариант: " + paymentType);
        System.out.println("================================");

        List<String> placeholders =
                paymentPage.getEmptyFieldsPlaceholders();

        Assertions.assertFalse(
                placeholders.isEmpty(),
                "Не найдены надписи в незаполненных полях: " + paymentType
        );

        for (String placeholder : placeholders) {

            System.out.println("Надпись в поле: " + placeholder);

            Assertions.assertFalse(
                    placeholder.trim().isEmpty(),
                    "Обнаружено пустое название поля"
            );
        }

        System.out.println(
                "Проверка варианта " + paymentType + " пройдена!"
        );
    }

    // Первое задание
    public void testFirstTask() {

        System.out.println("\n########################################");
        System.out.println("НАЧАЛО ПЕРВОГО ЗАДАНИЯ");
        System.out.println("########################################");

        Assertions.assertTrue(
                paymentPage.isBlockTitleDisplayed(),
                "Заголовок не найден"
        );

        Assertions.assertTrue(
                paymentPage.isVisaLogoDisplayed(),
                "Логотип Visa не найден"
        );

        Assertions.assertTrue(
                paymentPage.isDetailsLinkDisplayed(),
                "Ссылка Подробнее о сервисе не найдена"
        );

        Assertions.assertTrue(
                paymentPage.getSelectedPaymentType().contains("Услуги связи"),
                "Не выбран вариант Услуги связи"
        );

        checkPlaceholders("Услуги связи");

        paymentPage.selectPaymentType("Домашний интернет");
        checkPlaceholders("Домашний интернет");

        paymentPage.selectPaymentType("Рассрочка");
        checkPlaceholders("Рассрочка");

        paymentPage.selectPaymentType("Задолженность");
        checkPlaceholders("Задолженность");

        System.out.println("\nПЕРВОЕ ЗАДАНИЕ ВЫПОЛНЕНО!");
    }

    // Второе задание
    public void testSecondTask() {

        System.out.println("\n########################################");
        System.out.println("НАЧАЛО ВТОРОГО ЗАДАНИЯ");
        System.out.println("########################################");

        // Выбираем услуги связи
        paymentPage.selectPaymentType("Услуги связи");

        // Вводим тестовый номер
        paymentPage.enterPhone("297777777");

        // Вводим сумму
        paymentPage.enterAmount("100");

        // Открываем платёжную форму
        paymentPage.clickBankCardButton();

        // Проверяем iframe
        paymentPage.printFrames();

        // Выводим содержимое платёжной формы
        paymentPage.printPaymentFrameContent();

        // Проверяем сумму
        String amount = paymentPage.getPaymentAmount();

        System.out.println("Отображаемая сумма: " + amount);

        Assertions.assertTrue(
                amount.contains("100.00") && amount.contains("BYN"),
                "Некорректная сумма оплаты: " + amount
        );

        // Проверяем информацию об оплате
        String information = paymentPage.getPaymentInformation();

        System.out.println("Информация об оплате: " + information);

        Assertions.assertTrue(
                information.contains("Услуги связи"),
                "Неверный вариант оплаты"
        );

        Assertions.assertTrue(
                information.contains("375297777777"),
                "Неверный номер телефона"
        );

        // Проверяем поля банковской карты
        List<String> cardFields = paymentPage.getCardPlaceholders();

        System.out.println("\nПоля банковской карты:");

        for (String field : cardFields) {
            System.out.println(field);
        }

        Assertions.assertTrue(
                cardFields.contains("Номер карты"),
                "Не найдено поле Номер карты"
        );

        Assertions.assertTrue(
                cardFields.contains("Срок действия"),
                "Не найдено поле Срок действия"
        );

        Assertions.assertTrue(
                cardFields.contains("CVC"),
                "Не найдено поле CVC"
        );

        Assertions.assertTrue(
                cardFields.contains("Имя и фамилия на карте"),
                "Не найдено поле Имя и фамилия на карте"
        );

        // Проверяем логотипы платёжных систем
        List<String> logos = paymentPage.getPaymentLogos();

        System.out.println("\nНайденные платёжные логотипы:");

        for (String logo : logos) {
            System.out.println(logo);
        }

        Assertions.assertFalse(
                logos.isEmpty(),
                "Не найдены логотипы платёжных систем"
        );

        // Закрываем платёжную панель
        paymentPage.closePaymentFrame();

        // Нажимаем кнопку КРОК
        paymentPage.clickKrokButton();

        System.out.println("\nВТОРОЕ ЗАДАНИЕ ВЫПОЛНЕНО!");
    }

    // Запуск обоих заданий
    @Test
    public void testAllTasks() {

        testFirstTask();

        testSecondTask();
    }

    // Закрытие браузера
    @AfterEach
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}