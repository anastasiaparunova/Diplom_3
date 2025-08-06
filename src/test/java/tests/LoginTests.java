package tests;

import apiactions.User;
import apiactions.UserApiActions;
import web.BrowserManager;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modal.*;
import utils.assertions.UIAssertions;
import web.TestConfigurations;

import static com.codeborne.selenide.Selenide.*;

public class LoginTests {

    UserApiActions userApiActions = new UserApiActions();
    HeaderElements headerElements = new HeaderElements();
    LoginPageElements loginPageElements = new LoginPageElements();
    UIAssertions uiAssertions = new UIAssertions();
    RegistrationPageElements registrationPageElements = new RegistrationPageElements();
    RestorePasswordElements restorePasswordElements = new RestorePasswordElements();
    ConstructorElements constructorElements = new ConstructorElements();
    User user;
    String randomEmail;
    String randomPassword;
    String randomName;

    @Before
    public void setUp() {
        new BrowserManager().switchBrowserSetting();
        open(TestConfigurations.BASE_URL);
        RestAssured.baseURI = TestConfigurations.BASE_URL;

        Faker faker = new Faker();

        randomEmail = faker.internet().emailAddress();
        randomPassword = faker.internet().password();
        randomName = faker.name().name();

        user = new User(randomEmail, randomPassword, randomName);
        userApiActions.createUser(user);
    }

    @Test
    @DisplayName("User logs in successfully via \"Войти в аккаунт\" button on the main page")
    public void shouldLoginViaMainPage() {

        constructorElements.clickLoginButton();
        loginPageElements.completeLogin(randomEmail, randomPassword);

        uiAssertions.checkIfVisible("\"Соберите бургер\" header", constructorElements.getCreateBurgerHeader());
    }


    @Test
    @DisplayName("User logs in successfully via Personal Account button and redirected to constructor")
    public void shouldLoginViaPersonalAccount() {

        headerElements.clickPersonalAccount();
        loginPageElements.completeLogin(randomEmail, randomPassword);

        uiAssertions.checkIfVisible("\"Соберите бургер\" header", constructorElements.getCreateBurgerHeader());
    }

    @Test
    @DisplayName("User logs in successfully via Registration Form and redirected to constructor")
    public void shouldLoginViaRegistrationForm() {

        headerElements.clickPersonalAccount();
        loginPageElements.clickRegisterButton();
        registrationPageElements.clickLoginButton();

        loginPageElements.completeLogin(randomEmail, randomPassword);

        uiAssertions.checkIfVisible("\"Соберите бургер\" header", constructorElements.getCreateBurgerHeader());
    }

    @Test
    @DisplayName("User logs in successfully via Restore Password Form and redirected to constructor")
    public void shouldLoginViaRestorePasswordForm() {

        headerElements.clickPersonalAccount();
        loginPageElements.clickRestorePasswordButton();
        restorePasswordElements.clickLoginButton();

        loginPageElements.completeLogin(randomEmail, randomPassword);

        uiAssertions.checkIfVisible("\"Соберите бургер\" header", constructorElements.getCreateBurgerHeader());
    }

    @After
    public void tearDown() {
        closeWebDriver();

        user = new User(randomEmail, randomPassword);
        String token = userApiActions.extractToken(user);
        userApiActions.deleteUser(token);
    }
}

