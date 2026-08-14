package praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.settings.BaseTest;
import praktikum.pages.MainPage;
import static org.junit.Assert.assertTrue;

public class ConstructorSwitchTest extends BaseTest {

    private MainPage mainPage;

    @Before
    public void setUpConstructorSwitchTest() {
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.getHeader().clickConstructor();  // Страховочный клик, можно убрать
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void switchToBunsTest() {

        // Кликаем по разделу "Соусы" (чтобы сделать булки неактивными)
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        assertTrue("Раздел 'Булки' должен активироваться",
                mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void switchToSaucesTest() {

        mainPage.clickSaucesTab();

        assertTrue("Раздел 'Соусы' должен активироваться",
                mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void switchToFillingsTest() {

        mainPage.clickFillingsTab();

        assertTrue("Раздел 'Начинки' должен активироваться",
                mainPage.isFillingsTabActive());
    }
}