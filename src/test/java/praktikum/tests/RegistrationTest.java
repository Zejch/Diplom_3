package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.pages.MainPage;
import praktikum.settings.BaseTest;
import praktikum.pages.RegisterPage;
import praktikum.pages.LoginPage;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private MainPage mainPage;

    private static final String INVALID_PASSWORD = "12345";  // Хардкод можно заменить faker-ом

    @Before
    public void setUpRegistrationTest() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя и вход")
    public void successfulRegistrationTest() {

        // Регистрация
        registerPage.open();
        registerPage.register(
                testUser.getName(),
                testUser.getEmail(),
                testUser.getPassword()
        );

        // Ждём перехода на страницу логина и логинимся
        loginPage.waitForPageLoad();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Кнопка 'Оформить заказ' должна появиться после логина",
                mainPage.isPlaceOrderButtonVisible());

        // Сохраняем токен для удаления отдельно, т.к. эти тесты не затрагивают наши автоматические API-шаги
        accessToken = userClient.loginUser(testUser);
    }

    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем (меньше 6 символов)")
    public void registrationWithInvalidPasswordFailureTest() {

        registerPage.open();
        registerPage.register(
                testUser.getName(),
                testUser.getEmail(),
                INVALID_PASSWORD
        );

        assertTrue("Должна появиться ошибка о некорректном пароле",
                registerPage.isPasswordErrorVisible());
    }
}