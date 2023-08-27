import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;

import static org.junit.Assert.assertEquals;


public class CheckQuestionMainPage {

    private WebDriver driver;
    final String[][] accordionHeading = {
        { "accordion__heading-0", "Сколько это стоит? И как оплатить?"},
        { "accordion__heading-1", "Хочу сразу несколько самокатов! Так можно?"},
        { "accordion__heading-2", "Как рассчитывается время аренды?"},
        { "accordion__heading-3", "Можно ли заказать самокат прямо на сегодня?"},
        { "accordion__heading-4", "Можно ли продлить заказ или вернуть самокат раньше?"},
        { "accordion__heading-5", "Вы привозите зарядку вместе с самокатом?"},
        { "accordion__heading-6", "Можно ли отменить заказ?"},
        { "accordion__heading-7", "Я жизу за МКАДом, привезёте?"},
    };
    final String[][] accordionPanel = {
        {"accordion__panel-0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
        {"accordion__panel-1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
        {"accordion__panel-2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
        {"accordion__panel-3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
        {"accordion__panel-4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
        {"accordion__panel-5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
        {"accordion__panel-6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
        {"accordion__panel-7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
    };

    @Before
    public void startUp() {
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
    }

    @Test
    public void checkTestOnAccordeonHeadingAndAccordeonPanel() {

        // Создали объект главной страницы
        MainPage mainPage = new MainPage(driver);

        // переход на страницу тестового приложения
        mainPage.openMainPage();



        // проходимся по каждому вопросу в списке
        for (int i = 0; i < accordionHeading.length; i++) {
            // Нажимаем на строку в разделе "Вопросы о важном"
            mainPage.clickAccordeonHeading(accordionHeading[i][0]);
            // Проверка текста вопроса в "Вопросы о важном"
            assertEquals(accordionHeading[i][1], mainPage.getTextAccordeonHeading(accordionHeading[i][0]));
            // Проверка текста ответа в "Вопросы о важном"
            assertEquals(accordionPanel[i][1], mainPage.getTextAccordeonPanel(accordionPanel[i][0]));
        }
    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

}
