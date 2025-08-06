package modal;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class ConstructorElements {


    private final SelenideElement loginButton = $(byText("Войти в аккаунт"));

    private final SelenideElement bunsButton = $x("//span[text()='Булки']/parent::div");

    private final SelenideElement saucesButton = $x("//span[text()='Соусы']/parent::div");

    private final SelenideElement fillingsButton = $x("//span[text()='Начинки']/parent::div");

    private final SelenideElement activeTab = $("div.tab_tab__1SPyG.tab_tab_type_current__2BEPc span");

    private final SelenideElement constructorCreateBurgerHeader = $(byText("Соберите бургер"));


    @Step("Click on \"Войти в аккаунт\" button")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Get \"Соберите бургер\" header")
    public SelenideElement getCreateBurgerHeader() {
        return constructorCreateBurgerHeader;
    }

    @Step("Click on \"Булки\" button")
    public void clickBunsButton() {
        bunsButton.click();
    }

    @Step("Click on \"Соусы\" button")
    public void clickSaucesButton() {
        saucesButton.click();
    }

    @Step("Click on \"Начинки\" button")
    public void clickFillingsButton() {
        fillingsButton.click();
    }

    @Step("Get active tab")
    public SelenideElement getActiveTab() {
        return activeTab;
    }

    @Step("Get ingredient by name: {name}")
    public SelenideElement ingredientByName(String name) {
        return $x(".//img[@alt=\"" + name + "\"]");
    }

}
