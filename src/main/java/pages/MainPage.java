package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

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

    // Кликнуть по строке в "Вопросы о важном"
    public void clickAccordeonHeading(String locatorAccordeonHeading) {
        WebElement element = driver.findElement(By.id(locatorAccordeonHeading));
        // Скролл до элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Явное ожидание видимости элемента
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id(locatorAccordeonHeading)));
        // Кликаем по строке
        element.click();
    }

    // Получить текст из строки "Вопросы о важном"
    public String getTextAccordeonHeading(String locatorAccordeonHeading) {
        WebElement element = driver.findElement(By.id(String.valueOf(locatorAccordeonHeading)));
        return element.getText();
    }

    // Получить текст из выпавшей панели (после клика по строке в "Вопросы о важном)
    public String getTextAccordeonPanel (String locatorAccordeonPanel) {
        // Явное ожидание видимости элемента
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorAccordeonPanel)));
        return driver.findElement(By.id(locatorAccordeonPanel)).getText();
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
    public void clickOrder(String whereClick) {

        if (whereClick.equals("up")) {
            driver.findElement(By.xpath(locatorButtonOrderUP)).click();
        } else if (whereClick.equals("down")) {
            // Скролл до элемента
            WebElement element = driver.findElement(By.xpath(locatorButtonOrderDown));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            driver.findElement(By.xpath(locatorButtonOrderDown)).click();
        }
    }
}
