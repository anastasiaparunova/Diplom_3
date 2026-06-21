package modal;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPageElements {

    private final SelenideElement personName = $x("//label[text()='Имя']/following-sibling::input");

    private final SelenideElement personEmail = $x("//label[text()='Email']/following-sibling::input");

    private final SelenideElement personPassword = $("input[name='Пароль']");

    private final SelenideElement registerButton = $(byText("Зарегистрироваться"));

    private final SelenideElement loginButton = $(byText("Войти"));


    @Step("Populate \"Имя\" field")
    public void inputName(String name) {
        personName.setValue(name);
    }

    @Step("Populate \"Email\" field")
    public void inputEmail(String email) {
        personEmail.setValue(email);
    }

    @Step("Populate \"Пароль\" field")
    public void inputPassword(String password) {
        personPassword.setValue(password);
    }

    @Step("Click on \"Зарегистрироваться\" button")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Click on \"Войти\" button")
    public void clickLoginButton() {
        loginButton.click();
    }


    @Step("Complete registration")
    public void completeRegistration(String name, String email, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        clickRegisterButton();
    }
}

