package modal;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPageElements {

    private final SelenideElement personEmail = $("input[name='name']");

    private final SelenideElement personPassword = $("input[name='Пароль']");

    private final SelenideElement loginButton = $(byText("Войти"));

    private final SelenideElement registrationButtonLoginPage = $(byLinkText("Зарегистрироваться"));

    private final SelenideElement restorePasswordButton = $(byLinkText("Восстановить пароль"));


    @Step("Populate \"Email\" field")
    public void inputEmail(String email) {
        personEmail.setValue(email);
    }

    @Step("Populate \"Пароль\" field")
    public void inputPassword(String password) {
        personPassword.setValue(password);
    }

    @Step("Click on \"Войти\" button")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Click on \"Зарегистрироваться\" button")
    public void clickRegisterButton() {
        registrationButtonLoginPage.click();
    }

    @Step("Click on \"Восстановить пароль\" button")
    public void clickRestorePasswordButton() {
        restorePasswordButton.click();
    }


    @Step("Complete login")
    public void completeLogin(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        clickLoginButton();
    }

}
