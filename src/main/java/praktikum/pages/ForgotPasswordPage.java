package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class ForgotPasswordPage {
    private final WebDriver driver;

    private static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.education-services.ru/forgot-password";

    private final By loginLinkOnForgotPasswordPage = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы восстановления пароля по URL")
    public void open() {
        driver.get(FORGOT_PASSWORD_PAGE_URL);
    }

    @Step("Клик по ссылке 'Войти' на странице восстановления пароля")
    public void clickloginLinkOnForgotPasswordPage() {
        driver.findElement(loginLinkOnForgotPasswordPage).click();
    }
}