package web;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserManager {

    public void switchBrowserSetting() {
        String browserParam = System.getProperty("browser", "chrome").toLowerCase();

        switch (browserParam) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "driver/chrome/chromedriver_138");

                Configuration.browser = "chrome";
                break;

            case "yandex":
                System.setProperty("webdriver.chrome.driver", "driver/yandex/chromedriver_136");

                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

                Configuration.browser = "chrome";
                Configuration.browserCapabilities = options;
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserParam);
        }
    }
}