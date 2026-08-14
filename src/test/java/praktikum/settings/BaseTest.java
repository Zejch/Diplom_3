package praktikum.settings;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.clients.UserClient;
import praktikum.api.dto.UserRequest;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected Faker faker;
    protected UserClient userClient;
    protected UserRequest testUser;
    protected String accessToken;

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @BeforeClass
    public static void setUpBaseClass() {
        RestAssured.baseURI = BASE_URL;
        // логирование для отладки и Allure-отчета
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }

    @Before
    @DisplayName("Настройка тестового окружения")
    public void setUpBase() {
        // Переключение браузеров осуществляется командой -Dbrowser=yandex в настройках IDE
        // Либо: mvn test -Dbrowser=yandex / mvn test -Dbrowser=chrome
        String browser = System.getProperty("browser", "chrome");

        if ("yandex".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver.exe");
        } else {
            WebDriverManager.chromedriver().setup();
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage"); // после отладки можно добавить --headless

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Увеличенное неявное ожидание

        faker = new Faker();
        userClient = new UserClient();
        testUser = new UserRequest(
                faker.internet().emailAddress(),
                faker.internet().password(6, 10),
                faker.name().username()
        );
    }

    @After
    @DisplayName("Очистка данных и закрытие браузера")
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}