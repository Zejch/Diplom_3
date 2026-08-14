package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.settings.BaseTest;
import praktikum.pages.*;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;

    @Before
    public void setUpLoginTest() {
        // Создаем пользователя через API для всех тестов входа
        accessToken = userClient.createUser(testUser);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    public void loginViaMainPageButtonTest() {
        mainPage.open();
        mainPage.clickLoginButtonOnMainPage();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Кнопка 'Оформить заказ' должна появиться после входа через кнопку с главной страницы",
                mainPage.isPlaceOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет' в шапке")
    public void loginViaPersonalAccountButtonTest() {
        mainPage.open();
        mainPage.getHeader().clickPersonalAccount();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Кнопка 'Оформить заказ' должна появиться после входа через кнопку Личный кабинет",
                mainPage.isPlaceOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginViaRegisterFormTest() {
        registerPage.open();
        registerPage.clickLoginLinkOnRegisterPage();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Кнопка 'Оформить заказ' должна появиться после входа через форму регистрации",
                mainPage.isPlaceOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginViaForgotPasswordFormTest() {
        forgotPasswordPage.open();
        forgotPasswordPage.clickloginLinkOnForgotPasswordPage();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Кнопка 'Оформить заказ' должна появиться после входа через форму восстановления пароля",
                mainPage.isPlaceOrderButtonVisible());
    }
}