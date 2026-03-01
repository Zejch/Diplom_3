package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final HeaderPage headerPage;

    private static final String MAIN_PAGE_URL = "https://stellarburgers.education-services.ru/";

    // Локаторы для тестов
    private final By loginButtonOnMainPage = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By placeOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    // Локаторы для разделов конструктора
    private final By bunsTab = By.xpath(".//span[text()='Булки']/..");
    private final By saucesTab = By.xpath(".//span[text()='Соусы']/..");
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']/..");
    private final By activeTab = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.headerPage = new HeaderPage(driver);
    }

    @Step("Открытие главной страницы")
    public void open() {
        driver.get(MAIN_PAGE_URL);
    }

    public HeaderPage getHeader() {
        return headerPage;
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButtonOnMainPage() {
        driver.findElement(loginButtonOnMainPage).click();
    }

    // Является проверкой того, что пользователь залогинился
    @Step("Проверка отображения кнопки 'Оформить заказ' (=залогинено)")
    public boolean isPlaceOrderButtonVisible() {
        return driver.findElement(placeOrderButton).isDisplayed();
    }

    @Step("Клик по разделу 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
        waitForTabChange();
    }

    @Step("Клик по разделу 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
        waitForTabChange();
    }

    @Step("Клик по разделу 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
        waitForTabChange();
    }

    @Step("Проверка, что активен раздел 'Булки'")
    public boolean isBunsTabActive() {
        String classAttribute = driver.findElement(bunsTab).getAttribute("class");
        return classAttribute != null && classAttribute.contains("tab_tab_type_current");
    }

    @Step("Проверка, что активен раздел 'Соусы'")
    public boolean isSaucesTabActive() {
        String classAttribute = driver.findElement(saucesTab).getAttribute("class");
        return classAttribute != null && classAttribute.contains("tab_tab_type_current");
    }

    @Step("Проверка, что активен раздел 'Начинки'")
    public boolean isFillingsTabActive() {
        String classAttribute = driver.findElement(fillingsTab).getAttribute("class");
        return classAttribute != null && classAttribute.contains("tab_tab_type_current");
    }

    private void waitForTabChange() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(activeTab));
    }
}