package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class OrderPage {
    private final WebDriver driver;
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Ввод имени в Поле ввода имени
    public OrderPage inputFirstName(String firstName) {
        driver.findElement(By.xpath("//input[@placeholder= '* Имя']")).sendKeys(firstName);
        return this;
    }

    // Ввод фамилии в Поле ввода фамилии
    public OrderPage inputSecondName(String secondName) {
        driver.findElement(By.xpath("//input[@placeholder= '* Фамилия']")).sendKeys(secondName);
        return this;
    }

    // Ввод адреса в Поле ввода адреса
    public OrderPage inputAdress(String adress) {
        driver.findElement(By.xpath("//input[@placeholder= '* Адрес: куда привезти заказ']")).sendKeys(adress);
        return this;
    }

    // Выбор метро - клик по полю
    public void clickMetro() {
        driver.findElement(By.className("select-search")).click();
    }

    // Ввод названия станции и Клик по 1 выпавшей станции
    public void choseMetroStation(String metroStation) {
        driver.findElement(By.className("select-search__input")).sendKeys(metroStation);
        driver.findElement(By.className("select-search__select")).click();
    }

    // Ввод телефона в Поле ввода номера телефона
    public OrderPage inputPhoneNumber(String phoneNumber) {
        driver.findElement(By.xpath("//input[@placeholder= '* Телефон: на него позвонит курьер']")).sendKeys(phoneNumber);
        return this;
    }

    // Нажать кнопку "Далее"
    public void clickNext() {
        driver.findElement(By.xpath("//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']")).click();
    }

    // Перешли на следующий экран "Про аренду"
    // Кликнуть в поле выбора даты и поставить нужную и нажать Enter
    public OrderPage inputDate(String date) {
        driver.findElement(By.xpath("//input[@placeholder= '* Когда привезти самокат']")).sendKeys(date);
        driver.findElement(By.xpath("//input[@placeholder= '* Когда привезти самокат']")).sendKeys(Keys.ENTER);
        return this;
    }

    // Клик по выбору срока аренды и Выбор срока аренды в выпадающем меню - первый из списка
    public void clickTimeRent() {
        driver.findElement(By.className("Dropdown-placeholder")).click();
        driver.findElement(By.className("Dropdown-option")).click();
    }

    // Выбор цвета самоката (первый чек-бокс)
    public void clickColorSamokat(String samokatColor) {
        driver.findElement(By.id(samokatColor)).click();
    }

    // Ввод комментария в Поле "Комментарий для курьера"
    public OrderPage inputСommentForCourier(String commentForCourier) {
        driver.findElement(By.xpath("//input[@placeholder= 'Комментарий для курьера']")).sendKeys(commentForCourier);
        return this;
    }
    // Нажать кнопку Заказать
    public void clickOrder() {
        driver.findElement(By.xpath("//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']")).click();
    }
    //Проверка, что окно подтверждения заказа появилось
        public void chekOrderVerificationWindow() {
            System.out.println(driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText());
            assert driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText().contains("Хотите оформить заказ?");
        }
    // Клик на кнопку "Да"
    public void clickYesOrder() {
        driver.findElement(By.xpath("//button[text()='Да']")).click();
    }

    // Получаем текст в окне подтверждения заказа
    public String getFinalOrderMessage(){
        return driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText();
    }

}
