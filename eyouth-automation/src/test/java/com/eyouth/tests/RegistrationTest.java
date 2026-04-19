package com.eyouth.tests;

import com.eyouth.pages.HomePage;
import com.eyouth.pages.RegistrationPage;
import com.eyouth.config.ConfigReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC03 – Open registration page.
 * TC04 – Register with empty username field.
 */
@Epic("EYouth Automation")
@Feature("Registration")
public class RegistrationTest extends BaseTest {

    @Test(description = "TC03: Click 'انضم لنا' and assert URL contains /signup")
    @Severity(SeverityLevel.NORMAL)
    @Story("User navigates to the registration page")
    @Description("Click the 'انضم لنا' button and assert the URL contains /signup.")
    public void tc03_openRegistrationPage() {

        Allure.step("Step 1 – Open home page and click 'انضم لنا'", () -> {
            HomePage homePage = new HomePage(driver);
            homePage.openMenu();
            homePage.clickJoinUs();
        });

        Allure.step("Step 2 – Assert URL contains /signup", () -> {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(
                currentUrl.contains("/signup") || currentUrl.contains("/register"),
                "URL should contain /signup. Actual: " + currentUrl
            );
        });
    }

    @Test(description = "TC04: Register with empty username – assert validation error")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User submits registration form without a username")
    @Description("Fill all fields except username, click 'انشاء', " +
                 "assert 'اسم المستخدم مطلوب' validation error appears.")
    public void tc04_registerWithEmptyUsername() {

        Allure.step("Step 1 – Navigate to signup page", () -> {
            driver.get(ConfigReader.getBaseUrl().replace("/ar", "") + "/ar/signup");
        });

        RegistrationPage regPage = new RegistrationPage(driver);

        Allure.step("Step 2 – Fill email, password, confirm password (leave username empty)", () -> {
            regPage.enterEmail(ConfigReader.getRegEmail())
                   .enterPassword(ConfigReader.getRegPassword())
                   .enterConfirmPassword(ConfigReader.getRegConfirmPassword())
                   .leaveUsernameEmpty();
        });

        Allure.step("Step 3 – Click 'انشاء' (Create/Submit)", () -> {
            regPage.clickCreate();
        });

        Allure.step("Step 4 – Assert username validation error is displayed", () -> {
            Assert.assertTrue(
                regPage.isUsernameErrorDisplayed(),
                "Validation error 'اسم المستخدم مطلوب' should be visible when username is empty"
            );
        });
    }
}
