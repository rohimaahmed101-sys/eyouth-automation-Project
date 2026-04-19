package com.eyouth.tests;

import com.eyouth.config.ConfigReader;
import com.eyouth.pages.HomePage;
import com.eyouth.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC05 – Login with invalid credentials.
 * TC06 – Login with empty fields.
 */
@Epic("EYouth Automation")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(description = "TC05: Login with invalid credentials – assert error message")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User attempts login with wrong username and password")
    @Description("Enter an invalid username and password, click Login, " +
                 "and assert an error message is displayed.")
    public void tc05_loginWithInvalidCredentials() {

        Allure.step("Step 1 – Navigate to login page", () -> {
            HomePage homePage = new HomePage(driver);
            homePage.openMenu();
            homePage.clickLogin();
        });

        LoginPage loginPage = new LoginPage(driver);

        Allure.step("Step 2 – Enter invalid username and password", () -> {
            loginPage.enterUsername(ConfigReader.getInvalidUsername())
                     .enterPassword(ConfigReader.getInvalidPassword());
        });

        Allure.step("Step 3 – Click Login", () -> {
            loginPage.clickLogin();
        });

        Allure.step("Step 4 – Assert error message is displayed", () -> {
            Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "An error message should appear when logging in with invalid credentials. " +
                "Error text: " + loginPage.getErrorText()
            );
        });
    }

    @Test(description = "TC06: Login with empty fields – assert validation messages")
    @Severity(SeverityLevel.NORMAL)
    @Story("User submits login form with both fields empty")
    @Description("Leave username and password empty, click Login, " +
                 "and assert that required field validation messages appear.")
    public void tc06_loginWithEmptyFields() {

        Allure.step("Step 1 – Navigate to login page", () -> {
            HomePage homePage = new HomePage(driver);
            homePage.openMenu();
            homePage.clickLogin();
        });

        LoginPage loginPage = new LoginPage(driver);

        Allure.step("Step 2 – Submit without entering any credentials", () -> {
            loginPage.submitEmpty();
        });

        Allure.step("Step 3 – Assert validation messages are shown for required fields", () -> {
            boolean errorShown = loginPage.isErrorMessageDisplayed()
                              || loginPage.areValidationMessagesDisplayed();
            Assert.assertTrue(
                errorShown,
                "Validation error messages should appear when submitting empty login form"
            );
        });
    }
}
