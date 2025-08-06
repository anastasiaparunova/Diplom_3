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

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class ConstructorTests {

    HeaderElements headerElements = new HeaderElements();
    UIAssertions uiAssertions = new UIAssertions();
    ConstructorElements constructorElements = new ConstructorElements();
    UserApiActions userApiActions = new UserApiActions();
    LoginPageElements loginPageElements = new LoginPageElements();
    String randomEmail;
    String randomPassword;
    String randomName;
    User user;


    @Before
    public void setUp() {
        new BrowserManager().switchBrowserSetting();

        open(TestConfigurations.BASE_URL + "/login");

        RestAssured.baseURI = TestConfigurations.BASE_URL;

        Faker faker = new Faker();
        randomEmail = faker.internet().emailAddress();
        randomPassword = faker.internet().password();
        randomName = faker.name().name();

        user = new User(randomEmail, randomPassword, randomName);
        userApiActions.createUser(user);
        loginPageElements.completeLogin(randomEmail, randomPassword);

        headerElements.clickPersonalAccount();
    }

    @Test
    @DisplayName("Clicking on \"Конструктор\" button from Personal Account page leads to Constructor")
    public void buttonShouldRedirectToConstructor() {

        headerElements.clickConstructorButton();

        uiAssertions.checkIfVisible("\"Соберите бургер\" header", constructorElements.getCreateBurgerHeader());
    }


    @Test
    @DisplayName("Clicking on Stellar Burgers logo button from Personal Account page leads to Constructor")
    public void logoShouldRedirectToConstructor() {

        headerElements.clickLogoButton();

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