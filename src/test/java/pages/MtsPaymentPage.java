package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MtsPaymentPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы страницы MTS

    private final By blockTitle =
            By.xpath("//div[contains(@class,'pay__wrapper')]//h2");

    private final By visaLogo =
            By.xpath("//div[contains(@class,'pay__wrapper')]//img[1]");

    private final By detailsLink =
            By.xpath("//div[contains(@class,'pay__wrapper')]//a[contains(.,'Подробнее о сервисе')]");

    private final By selectHeader =
            By.cssSelector(".select__header");

    private final By inputFields =
            By.cssSelector(".pay__wrapper input");

    // Локатор iframe bePaid

    private final By paymentFrame =
            By.cssSelector("iframe[src*='checkout.bepaid.by']");


    public MtsPaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }


    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }


    // Открытие страницы

    public void openPage() {

        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();

        WebElement header = wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectHeader)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                header
        );

        pause(2);
    }


    // Проверка заголовка

    public boolean isBlockTitleDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(blockTitle)
        ).isDisplayed();
    }


    // Проверка логотипа Visa

    public boolean isVisaLogoDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(visaLogo)
        ).isDisplayed();
    }


    // Проверка ссылки

    public boolean isDetailsLinkDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(detailsLink)
        ).isDisplayed();
    }


    // Получение выбранного типа платежа

    public String getSelectedPaymentType() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectHeader)
        ).getText().trim();
    }


    // Выбор типа платежа

    public void selectPaymentType(String typeName) {

        WebElement header = wait.until(
                ExpectedConditions.elementToBeClickable(selectHeader)
        );

        header.click();

        By optionLocator = By.xpath(
                "//*[contains(@class,'select') and " +
                        "(self::li or self::div or self::span or self::button)]" +
                        "[normalize-space(.)='" + typeName + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(optionLocator)
        ).click();

        wait.until(driver ->
                getSelectedPaymentType().contains(typeName)
        );

        pause(2);

        System.out.println("Выбран вариант: " + typeName);
    }


    // Получение надписей в незаполненных полях

    public List<String> getEmptyFieldsPlaceholders() {

        List<String> placeholders = new ArrayList<>();

        for (WebElement input : driver.findElements(inputFields)) {

            if (input.isDisplayed()) {

                String value = input.getAttribute("value");

                if (value == null || value.trim().isEmpty()) {

                    String placeholder =
                            input.getAttribute("placeholder");

                    if (placeholder != null &&
                            !placeholder.trim().isEmpty()) {

                        placeholders.add(placeholder.trim());
                    }
                }
            }
        }

        return placeholders;
    }


    // Ввод телефона

    public void enterPhone(String phone) {

        By phoneInput = By.cssSelector(
                ".pay__wrapper input[type='text']"
        );

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(phoneInput)
        );

        input.clear();
        input.sendKeys(phone);

        System.out.println("Номер телефона введен: " + phone);
    }


    // Ввод суммы

    public void enterAmount(String amount) {

        By amountInput = By.cssSelector(
                ".pay__wrapper input[type='text']"
        );

        wait.until(driver ->
                driver.findElements(amountInput).size() > 1
        );

        List<WebElement> inputs =
                driver.findElements(amountInput);

        WebElement input = inputs.get(1);

        wait.until(
                ExpectedConditions.elementToBeClickable(input)
        );

        input.clear();
        input.sendKeys(amount);

        System.out.println("Сумма введена: " + amount);
    }


    // Нажатие кнопки банковской карты

    public void clickBankCardButton() {

        By button = By.xpath(
                "//button[@data-method='card']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(button)
        ).click();

        System.out.println(
                "Нажата кнопка оплаты банковской картой"
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(paymentFrame)
        );
    }


    // Вывод информации об iframe

    public void printFrames() {

        driver.switchTo().defaultContent();

        List<WebElement> frames =
                driver.findElements(By.tagName("iframe"));

        System.out.println(
                "Количество iframe: " + frames.size()
        );

        for (int i = 0; i < frames.size(); i++) {

            WebElement frame = frames.get(i);

            System.out.println("FRAME № " + i);

            System.out.println(
                    "ID: " + frame.getAttribute("id")
            );

            System.out.println(
                    "TITLE: " + frame.getAttribute("title")
            );

            System.out.println(
                    "NAME: " + frame.getAttribute("name")
            );

            System.out.println(
                    "SRC: " + frame.getAttribute("src")
            );
        }
    }


    // Переключение в iframe

    private void switchToPaymentFrame() {

        driver.switchTo().defaultContent();

        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                        paymentFrame
                )
        );

        System.out.println(
                "Переключились в iframe bePaid"
        );
    }


    // Диагностика содержимого iframe

    public void printPaymentFrameContent() {

        switchToPaymentFrame();

        try {

            WebElement body = driver.findElement(
                    By.tagName("body")
            );

            System.out.println(
                    "\n===== СОДЕРЖИМОЕ BEPAID ====="
            );

            System.out.println(body.getText());

            System.out.println(
                    "=============================\n"
            );

        } finally {

            driver.switchTo().defaultContent();
        }
    }


    // Получение суммы оплаты

    public String getPaymentAmount() {

        switchToPaymentFrame();

        try {

            By amount = By.xpath(
                    "//*[contains(text(),'BYN') and normalize-space()]"
            );

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(amount)
            );

            String result = element.getText().trim();

            System.out.println(
                    "Сумма из платёжной формы: " + result
            );

            return result;

        } finally {

            driver.switchTo().defaultContent();
        }
    }


    // Получение информации об оплате

    public String getPaymentInformation() {

        switchToPaymentFrame();

        try {

            By information = By.xpath(
                    "//*[contains(.,'Оплата:') and contains(.,'Номер:')]"
            );

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            information
                    )
            );

            return element.getText().trim();

        } finally {

            driver.switchTo().defaultContent();
        }
    }


    // Проверка полей банковской карты

    public List<String> getCardPlaceholders() {

        switchToPaymentFrame();

        try {

            List<String> result = new ArrayList<>();

            By cardInputs = By.cssSelector(
                    "input[formcontrolname='creditCard'], " +
                            "input[formcontrolname='expirationDate'], " +
                            "input[formcontrolname='cvc'], " +
                            "input[formcontrolname='holder']"
            );

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            cardInputs
                    )
            );

            List<WebElement> inputs =
                    driver.findElements(cardInputs);

            for (WebElement input : inputs) {

                if (!input.isDisplayed()) {
                    continue;
                }

                String name =
                        input.getAttribute("formcontrolname");

                if ("creditCard".equals(name)) {
                    result.add("Номер карты");

                } else if ("expirationDate".equals(name)) {
                    result.add("Срок действия");

                } else if ("cvc".equals(name)) {
                    result.add("CVC");

                } else if ("holder".equals(name)) {
                    result.add("Имя и фамилия на карте");
                }
            }

            return result;

        } finally {

            driver.switchTo().defaultContent();
        }
    }


    // Проверка логотипов платёжных систем

    public List<String> getPaymentLogos() {

        switchToPaymentFrame();

        try {

            List<String> logos = new ArrayList<>();

            By images = By.xpath("//img");

            List<WebElement> elements =
                    driver.findElements(images);

            for (WebElement image : elements) {

                if (!image.isDisplayed()) {
                    continue;
                }

                String description =
                        image.getAttribute("alt");

                String source =
                        image.getAttribute("src");

                String info = (
                        (description == null ? "" : description) +
                                " " +
                                (source == null ? "" : source)
                ).toLowerCase();

                if (info.contains("visa") ||
                        info.contains("mastercard") ||
                        info.contains("belkart")) {

                    logos.add(info);
                }
            }

            return logos;

        } finally {

            driver.switchTo().defaultContent();
        }
    }

}