# Stellar Burgers — UI Tests

UI automated tests for the **Stellar Burgers** training service
(`https://stellarburgers.nomoreparties.site`), built with the **Page Object**
pattern on top of **Selenide**. They cover the main user scenarios:
registration, login through several entry points, navigation within the
constructor, personal account actions, and scrolling through ingredient
sections.

Test users are created via the API (REST Assured) to avoid duplicating
registration in the UI and to speed up data setup.

## Tech stack

- Java 11
- JUnit 4
- Selenide 6 (UI interaction)
- REST Assured (test data setup via API)
- Allure (reporting)
- JavaFaker (test data generation)
- Maven

## Test coverage

| Test class | Scenarios |
|---|---|
| `RegistrationTests` | redirect to the login page after registration; error when the password is shorter than 6 characters |
| `LoginTests` | login via the "Log in to account" button, via "Personal Account", from the registration form, and from the password-recovery form |
| `PersonalAccountTest` | access to the profile page after login |
| `ConstructorTests` | navigation to the constructor via the "Constructor" button and via the Stellar Burgers logo |
| `ScrollableListTests` | scrolling to the "Buns", "Sauces", and "Fillings" sections |
| `ExitPersonalAccountTest` | logging out of the personal account and returning to the main page |

Total: 13 tests.

## Architecture

```
src/test/java/
├── modal/         # Page Object: page elements and actions
│   ├── ConstructorElements.java
│   ├── HeaderElements.java
│   ├── LoginPageElements.java
│   ├── RegistrationPageElements.java
│   ├── PersonalAccountElements.java
│   └── RestorePasswordElements.java
├── tests/         # test classes
├── apiactions/    # data setup via API (User, UserApiActions)
├── utils/assertions/  # custom UI assertions
└── web/           # browser configuration and base URL
```

## Browser setup ⚠️

The tests run in Chrome by default and also support Yandex Browser.
Before running, configure the driver for your machine in
`web/BrowserManager.java`:

- the `chromedriver` path must match your installed Chrome version;
- for Yandex Browser, set the path to the browser binary.

> The chromedriver version must match the installed browser version.

Browser selection is controlled via a system property:

```bash
# Chrome (default)
mvn clean test

# Yandex Browser
mvn clean test -Dbrowser=yandex
```

## Allure report

```bash
mvn allure:serve
```

Or a static report:

```bash
mvn allure:report
# report: target/site/allure-maven-plugin/index.html
```

## Requirements

- JDK 11+
- Maven 3.6+
- Installed Chrome and/or Yandex Browser + a compatible chromedriver
- [Allure CLI](https://docs.qameta.io/allure/) to view the report
