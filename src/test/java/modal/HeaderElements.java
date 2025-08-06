package modal;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;

public class HeaderElements {

    private final SelenideElement personalAccountButton = $(byLinkText("Личный Кабинет"));

    private final SelenideElement constructorButton = $(byLinkText("Конструктор"));

    private final SelenideElement logoButton = $(".AppHeader_header__logo__2D0X2");



    @Step("Click on \"Личный кабинет\" button")
    public void clickPersonalAccount() {
        personalAccountButton.click();
    }

    @Step("Click on \"Конструктор\" button")
    public void clickConstructorButton() {
        constructorButton.click();
    }

    @Step("Click on \"Stellar Burgers\" logo")
    public void clickLogoButton() {
        logoButton.click();
    }

}