package tests;

import apiactions.User;
import apiactions.UserApiActions;
import web.BrowserManager;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import modal.PersonalAccountElements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modal.ConstructorElements;
import modal.HeaderElements;
import modal.LoginPageElements;
import utils.assertions.UIAssertions;
import web.TestConfigurations;

import static com.codeborne.selenide.Selenide.*;

public class PersonalAccountTest {

    User user;
    HeaderElements headerElements = new HeaderElements();
    LoginPageElements loginPageElements = new LoginPageElements();
    PersonalAccountElements personalAccountElements = new PersonalAccountElements();
    ConstructorElements constructorElements = new ConstructorElements();
    UIAssertions uiAssertions = new UIAssertions();
    UserApiActions userApiActions = new UserApiActions();
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
    @DisplayName("User can access Profile page after successful login")
    public void userCanAccessProfilePageAfterLogin() {

        constructorElements.clickLoginButton();
        loginPageElements.completeLogin(randomEmail, randomPassword);
        headerElements.clickPersonalAccount();

        uiAssertions.assertUrl(TestConfigurations.BASE_URL + "/account/profile");
        uiAssertions.checkIfVisible("Profile Header", personalAccountElements.getProfileHeader());
    }

    @After
    public void tearDown() {
        closeWebDriver();

        user = new User(randomEmail, randomPassword);
        String token = userApiActions.extractToken(user);
        userApiActions.deleteUser(token);
    }
}