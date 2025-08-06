package modal;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RestorePasswordElements {

    private final SelenideElement loginButton = $(byText("Войти"));

    @Step("Click on \"Войти\" button")
    public void clickLoginButton() {
        loginButton.click();
    }

}