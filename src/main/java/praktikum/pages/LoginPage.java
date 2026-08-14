package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By loginButtonOnLoginPage = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание загрузки страницы логина")
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("login"));
    }

    @Step("Заполнение поля Email: {email}")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Заполнение поля Пароль: {password}")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButtonOnLoginPage() {
        driver.findElement(loginButtonOnLoginPage).click();
    }

    @Step("Выполнение входа с email: {email}, пароль: {password}")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButtonOnLoginPage();
    }
}