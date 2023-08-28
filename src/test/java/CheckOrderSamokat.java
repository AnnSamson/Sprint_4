import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.MainPage.PagePart;
import pages.OrderPage;


//Параметризированный тест
@RunWith(Parameterized.class)
public class CheckOrderSamokat {

    private WebDriver driver;

    @Before
    public void startUp() {
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
    }

    private final PagePart whereOrder;
    private final String firstName;
    private final String secondName;
    private final String adress;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String samokatColor;
    private final String commentForCourier;


    public CheckOrderSamokat(PagePart whereOrder, String firstName, String secondName, String adress, String metroStation, String phoneNumber, String date, String samokatColor, String commentForCourier) {
        this.whereOrder = whereOrder;
        this.firstName = firstName;
        this.secondName = secondName;
        this.adress = adress;

        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.samokatColor = samokatColor;
        this.commentForCourier = commentForCourier;
    }


    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {PagePart.TOP, "Иван", "Иванов", "ул. Пушкина", "Черкизовская", "+71111111111", "27.08.2023", "black", ""},
                {PagePart.BOTTOM, "Мария", "Иванова", "ул. Твердая", "Лубянка", "88888888888", "30.08.2023", "grey", "Перед выездом позвонить мне!"},
        };
    }

    @Test
    public void positiveCheckOrderSamokat() {
        // Создали объект главной страницы
        MainPage mainPage = new MainPage(driver);
        // переход на страницу тестового приложения
        mainPage.openMainPage();
        // Клик по кнопке "Заказать"
        mainPage.clickOrder(whereOrder);
        // Создали объект страницы "Заказать"
        OrderPage orderPage = new OrderPage(driver);
        // Ввод имени в Поле ввода имени
        orderPage.inputFirstName(firstName);
        // Ввод фамилии в Поле ввода фамилии
        orderPage.inputSecondName(secondName);
        // Ввод адреса в Поле ввода адреса
        orderPage.inputAdress(adress);
        // Выбор метро - клик по полю
        orderPage.clickMetro();
        // Ввод названия станции и Клик по 1 выпавшей станции
        orderPage.choseMetroStation(metroStation);
        // Ввод телефона в Поле ввода номера телефона
        orderPage.inputPhoneNumber(phoneNumber);
        // Нажать кнопку "Далее"
        orderPage.clickNext();
        // Кликнуть в поле выбора даты и поставить нужную и нажать Enter
        orderPage.inputDate(date);
        // Клик по выбору срока аренды и Выбор срока аренды в выпадающем меню - первый из списка
        orderPage.clickTimeRent();
        // Выбор цвета самоката (первый чек-бокс)
        orderPage.clickColorSamokat(samokatColor);
        // Ввод комментария в Поле "Комментарий для курьера"
        orderPage.inputСommentForCourier(commentForCourier);
        // Нажать кнопку Заказать
        orderPage.clickOrder();
        //Проверка, что окно подтверждения заказа появилось
        orderPage.chekOrderVerificationWindow();
        // Клик на кнопку "Да"
        orderPage.clickYesOrder();
        //Прверка текста в финальном окне подтверждения заказа
        assert orderPage.getFinalOrderMessage().contains("Заказ оформлен");
   }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

}
