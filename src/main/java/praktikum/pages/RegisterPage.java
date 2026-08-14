package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class RegisterPage {
    private final WebDriver driver;

    private static final String REGISTER_PAGE_URL = "https://stellarburgers.education-services.ru/register";

    private final By nameInput = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registerButtonOnRegisterPage = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLinkOnRegisterPage = By.xpath(".//a[text()='Войти']");  // ← возвращаем!
    private final By errorPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы регистрации")
    public void open() {
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Заполнение поля Имя: {name}")
    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Заполнение поля Email: {email}")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Заполнение поля Пароль: {password}")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButtonOnRegisterPage).click();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLinkOnRegisterPage() {
        driver.findElement(loginLinkOnRegisterPage).click();
    }

    @Step("Регистрация пользователя: {name} / {email} / {password}")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка отображения ошибки 'Некорректный пароль'")
    public boolean isPasswordErrorVisible() {
        return driver.findElement(errorPasswordMessage).isDisplayed();
    }
}