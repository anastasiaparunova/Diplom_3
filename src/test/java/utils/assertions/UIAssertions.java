package utils.assertions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;

import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

public class UIAssertions {

    @Step("Check if an element is visible")
    public void checkIfVisible(String elementName, SelenideElement selenideElement) {
        selenideElement.shouldBe(Condition.visible);
    }


    @Step("Check if an element is in viewport")
    public void checkIfInViewport(SelenideElement selenideElement) {
        boolean isInViewport = Wait().until(driver -> {
            Rectangle rect = selenideElement.getWrappedElement().getRect();
            Dimension windowSize = getWebDriver().manage().window().getSize();

            boolean inViewport =
                    rect.getX() >= 0 &&
                            rect.getY() >= 0 &&
                            rect.getX() + rect.getWidth() <= windowSize.getWidth() &&
                            rect.getY() + rect.getHeight() <= windowSize.getHeight();

            return inViewport;
        });

        assertTrue("Element is not in viewport", isInViewport);
    }

    @Step("Verify that current URL is: {expectedUrl}")
    public void assertUrl(String expectedUrl) {
        webdriver().shouldHave(url(expectedUrl));
    }
}

