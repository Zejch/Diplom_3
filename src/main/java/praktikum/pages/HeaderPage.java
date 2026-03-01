package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class HeaderPage {
    private final WebDriver driver;

    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Конструктор' в шапке")
    public void clickConstructor() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по кнопке 'Личный кабинет' в шапке")
    public void clickPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }
}