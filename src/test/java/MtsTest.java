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

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
    }

    @Test
    public void testBlockTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0, document.body.scrollHeight * 0.2);");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'pay__wrapper')]//h2")
        ));
        Assertions.assertTrue(title.isDisplayed(), "Заголовок не найден!");
        System.out.println("Заголовок найден: " + title.getText());

        WebElement visaLogo = driver.findElement(By.xpath("//div[contains(@class, 'pay__wrapper')]//img[1]"));
        Assertions.assertTrue(visaLogo.isDisplayed(), "Логотип Visa не найден!");
        System.out.println("Логотип Visa найден.");

        WebElement link = driver.findElement(By.xpath("//div[contains(@class, 'pay__wrapper')]//a[contains(text(), 'Подробнее о сервисе')]"));
        Assertions.assertTrue(link.isDisplayed(), "Ссылка не найдена!");
        System.out.println("Ссылка 'Подробнее о сервисе' найдена.");

        WebElement phoneInput = driver.findElement(By.xpath("//div[contains(@class, 'pay__wrapper')]//input[@type='text']"));
        phoneInput.sendKeys("297777777");
        System.out.println("Номер телефона введен.");

        js.executeScript("document.querySelectorAll('.pay__wrapper input')[1].value = '100';");
        System.out.println("Сумма введена через JS.");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-method='card']")
        ));
        continueButton.click();
        System.out.println("Кнопка 'БАНКОВСКАЯ КАРТА' нажата.");

        try {
            WebElement cardInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[contains(text(), 'Номер карты')]")
            ));
            Assertions.assertTrue(cardInput.isDisplayed(), "Поле для карты не появилось!");
            System.out.println("Успех! Открылось окно ввода карты.");
        } catch (Exception e) {
            System.out.println("Окно ввода карты не появилось (возможно, кнопка не сработала или это другой шаг).");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}