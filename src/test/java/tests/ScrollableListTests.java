package tests;

import web.BrowserManager;
import io.qameta.allure.junit4.DisplayName;
import modal.ConstructorElements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.assertions.UIAssertions;
import web.TestConfigurations;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class ScrollableListTests {

    ConstructorElements constructorElements = new ConstructorElements();
    UIAssertions UIAssertions = new UIAssertions();


    @Before
    public void setUp() {
        new BrowserManager().switchBrowserSetting();
        open(TestConfigurations.BASE_URL);
    }

    @Test
    @DisplayName("Clicking on \"Соусы\" button scrolls to sauces ingredients")
    public void shouldScrollToSauces() {

        constructorElements.clickSaucesButton();

        constructorElements.getActiveTab().shouldHave(text("Соусы"));
        UIAssertions.checkIfInViewport(constructorElements.ingredientByName("Соус Spicy-X"));
    }


    @Test
    @DisplayName("Clicking on \"Булки\" button scrolls to buns ingredients")
    public void shouldScrollToBuns() {

        constructorElements.clickFillingsButton();
        constructorElements.clickBunsButton();

        constructorElements.getActiveTab().shouldHave(text("Булки"));
        UIAssertions.checkIfInViewport(constructorElements.ingredientByName("Флюоресцентная булка R2-D3"));
    }

    @Test
    @DisplayName("Clicking on \"Начинки\" button scrolls to fillings ingredients")
    public void shouldScrollToFillings() {

        constructorElements.clickFillingsButton();

        constructorElements.getActiveTab().shouldHave(text("Начинки"));
        constructorElements.ingredientByName("Мясо бессмертных моллюсков Protostomia").shouldBe(visible);
        UIAssertions.checkIfInViewport(constructorElements.ingredientByName("Мясо бессмертных моллюсков Protostomia"));
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}
