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
import modal.RegistrationPageElements;
import utils.assertions.UIAssertions;
import web.TestConfigurations;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationTests {

    User user;
    UIAssertions uiAssertions = new UIAssertions();
    RegistrationPageElements registrationPageElements = new RegistrationPageElements();
    UserApiActions userApiActions = new UserApiActions();
    String randomEmail;
    String randomName;
    Faker faker;

    @Before
    public void setUp() {
        new BrowserManager().switchBrowserSetting();
        open(TestConfigurations.BASE_URL + "/register");
        RestAssured.baseURI = TestConfigurations.BASE_URL;

        faker = new Faker();
        randomEmail = faker.internet().emailAddress();
        randomName = faker.name().name();

    }

    @Test
    @DisplayName("User should be redirected to login page after Registration process was completed")
    public void registeredUserRedirectedToLogin() {

        String validPassword = faker.internet().password(6, 12);

        registrationPageElements.completeRegistration(randomName, randomEmail, validPassword);

        uiAssertions.assertUrl(TestConfigurations.BASE_URL + "/login");

        user = new User(randomEmail, validPassword);
    }

    @Test
    @DisplayName("Should throw error if the password is less than 6 symbols")
    public void shouldFailRegistrationWithShortPassword() {

        String shortPassword = faker.internet().password(3, 5);

        registrationPageElements.completeRegistration(randomName, randomEmail, shortPassword);

        uiAssertions.checkIfVisible("Incorrect Password", $(byText("Некорректный пароль")));

        user = new User(randomEmail, shortPassword);
    }

    @After
    public void tearDown() {
        closeWebDriver();

        if (userApiActions.isUserCreated(user)) {
            String token = userApiActions.extractToken(user);
            userApiActions.deleteUser(token);
        }
    }
}
