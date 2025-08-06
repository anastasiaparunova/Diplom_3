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
import web.TestConfigurations;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class ExitPersonalAccountTest {

    UserApiActions userApiActions = new UserApiActions();
    HeaderElements headerElements = new HeaderElements();
    LoginPageElements loginPageElements = new LoginPageElements();
    PersonalAccountElements personalAccountElements = new PersonalAccountElements();
    User user;
    String randomEmail;
    String randomPassword;
    String randomName;

    @Before
    public void setUp() {
        new BrowserManager().switchBrowserSetting();


        RestAssured.baseURI = TestConfigurations.BASE_URL;

        Faker faker = new Faker();

        randomEmail = faker.internet().emailAddress();
        randomPassword = faker.internet().password();
        randomName = faker.name().name();

        user = new User(randomEmail, randomPassword, randomName);
        userApiActions.createUser(user);

        open(TestConfigurations.BASE_URL);


        headerElements.clickPersonalAccount();
        loginPageElements.completeLogin(randomEmail, randomPassword);
    }


    @Test
    @DisplayName("Exiting from Personal Account leads to constructor main page")
    public void shouldExitPersonalAccount() {

        headerElements.clickPersonalAccount();
        personalAccountElements.clickExitButton();

        webdriver().shouldHave(url(TestConfigurations.BASE_URL + "/login"));

    }

    @After
    public void tearDown() {
        closeWebDriver();

        String token = userApiActions.extractToken(user);
        userApiActions.deleteUser(token);
    }
}
