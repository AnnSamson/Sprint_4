package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    public enum PagePart {
        TOP,
        BOTTOM
    }

    private final WebDriver driver;
    // кнопка принять куки
    private final String locatorButtonAcceptCookies = ".//button[@class='App_CookieButton__3cvqF']";
    // Верхняя кнопка заказать, xPath локатор
    private final String locatorButtonOrderUP = ".//button[@class='Button_Button__ra12g']";
    //Нижняя кнопка заказать, xPath локатор
    private final String locatorButtonOrderDown = ".//div[@class='Home_FinishButton__1_cWm']/button";

    public MainPage(WebDriver driver) {this.driver = driver;}

    // Открыть страницу с сайтом
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        clickAcceptCookies();
    }

    // Кликнуть по вопросу в "Вопросы о важном"
    public void clickAccordeonQuestion(String question) {
//        WebElement element = driver.findElement(By.id(locatorAccordeonHeading));
        WebElement element = driver.findElement(By.xpath(String.format("//div[text()='%s']", question)));
        // Скролл до элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Явное ожидание видимости элемента
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//div[text()='%s']", question))));
        // Кликаем по строке
        element.click();
    }

    // Получить ответ под вопросом "Вопросы о важном"
    public String getAccordeonAnswer(String question) {
        WebElement element = driver.findElement(By.xpath(String.format("//div[text()='%s']/../../div[@class='accordion__panel']", question)));
        return element.getText();
    }

    // Клик по кнопке "да все привыкли" принять куки
    public void clickAcceptCookies() {
        try {
            WebElement element = driver.findElement(By.xpath(locatorButtonAcceptCookies));
            element.click();
        } catch (NoSuchElementException e) {
            // Нет такой кнопки
        }
    }

    // Клик по кнопке "Заказать"
    public void clickOrder(PagePart whereClick) {

        if (whereClick == PagePart.TOP) {
            driver.findElement(By.xpath(locatorButtonOrderUP)).click();
        } else if (whereClick == PagePart.BOTTOM) {
            // Скролл до элемента
            WebElement element = driver.findElement(By.xpath(locatorButtonOrderDown));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            driver.findElement(By.xpath(locatorButtonOrderDown)).click();
        }
    }
}

