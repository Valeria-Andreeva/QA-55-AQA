import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MtsTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() throws InterruptedException {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();

        // Принятие Cookie
        try {
            WebElement acceptCookies = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(., 'Принять')]")
                    )
            );

            acceptCookies.click();
            System.out.println("Cookie приняты.");

        } catch (Exception e) {
            System.out.println("Окно cookie не появилось или уже закрыто.");
        }

        Thread.sleep(2000);
    }

    @Test
    public void testBlockTitle() throws InterruptedException {

        // 1. Проверка заголовка блока пополнения
        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'pay__wrapper')]//h2")
                )
        );

        Assertions.assertTrue(title.isDisplayed());

        System.out.println("Заголовок найден: " + title.getText());

        // 2. Проверка логотипа Visa
        WebElement visaLogo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'pay__wrapper')]//img[1]")
                )
        );

        Assertions.assertTrue(visaLogo.isDisplayed());

        System.out.println("Логотип Visa найден.");

        // 3. Проверка ссылки «Подробнее о сервисе»
        WebElement serviceLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'pay__wrapper')]//a[contains(., 'Подробнее о сервисе')]")
                )
        );

        Assertions.assertTrue(serviceLink.isDisplayed());

        System.out.println("Ссылка 'Подробнее о сервисе' найдена.");

        // 4. Ввод номера телефона
        WebElement phoneInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class, 'pay__wrapper')]//input[@type='text']")
                )
        );

        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        System.out.println("Номер телефона введен.");

        // 5. Ввод суммы
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "let input = document.querySelectorAll('.pay__wrapper input')[1];" +
                        "input.value = '100';" +
                        "input.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "input.dispatchEvent(new Event('change', { bubbles: true }));"
        );

        String amount = (String) js.executeScript(
                "return document.querySelectorAll('.pay__wrapper input')[1].value;"
        );

        Assertions.assertEquals("100", amount);

        System.out.println("Сумма введена: " + amount);

        Thread.sleep(2000);

        // 6. Нажатие кнопки «БАНКОВСКАЯ КАРТА»
        WebElement cardButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@data-method='card']")
                )
        );

        cardButton.click();

        System.out.println("Кнопка 'БАНКОВСКАЯ КАРТА' нажата.");

        // Ожидаем открытия платёжной панели
        By paymentFrameLocator = By.cssSelector("iframe.payment-widget-iframe");

        WebElement paymentFrame = wait.until(
                ExpectedConditions.visibilityOfElementLocated(paymentFrameLocator)
        );

        Assertions.assertTrue(paymentFrame.isDisplayed());

        System.out.println("Платёжная панель открылась.");

        // Пауза, чтобы увидеть открытую платёжную панель
        Thread.sleep(5000);

        // 7. Переход внутрь iframe
        driver.switchTo().frame(paymentFrame);

        // Поиск кнопки закрытия
        WebElement closeButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".header__close-button")
                )
        );

        closeButton.click();

        System.out.println("Кнопка закрытия платёжной панели нажата.");

        // Возврат на основную страницу
        driver.switchTo().defaultContent();

        // Ожидание закрытия платёжной панели
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        paymentFrameLocator
                )
        );

        System.out.println("Платёжная панель закрыта.");

        // Пауза, чтобы увидеть страницу после закрытия панели
        Thread.sleep(3000);

        // 8. Нажатие кнопки «КРОК»
        WebElement krokButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//img[@alt='КРОК']/..")
                )
        );

        krokButton.click();

        System.out.println("Кнопка 'КРОК' нажата.");

        // Пауза, чтобы увидеть открывшийся экран после нажатия «КРОК»
        Thread.sleep(7000);
    }

    @AfterEach
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}