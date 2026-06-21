package modal;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PersonalAccountElements {

    private final SelenideElement profileHeader = $(byText("Профиль"));

    private final SelenideElement exitButton = $(byText("Выход"));

    @Step("Click on \"Выход\" button")
    public void clickExitButton() {
        exitButton.click();
    }

    @Step("Get \"Профиль\" header")
    public SelenideElement getProfileHeader() {
        return profileHeader;
    }
    }
