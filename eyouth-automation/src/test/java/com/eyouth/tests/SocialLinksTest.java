package com.eyouth.tests;

import com.eyouth.pages.HomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * TC08 – Verify Facebook link.
 * TC09 – Verify LinkedIn link.
 * TC10 – Verify YouTube link.
 */
@Epic("EYouth Automation")
@Feature("Social Media Links")
public class SocialLinksTest extends BaseTest {

    @Test(description = "TC08: Click Facebook icon in footer – assert URL contains facebook.com")
    @Severity(SeverityLevel.MINOR)
    @Story("User clicks the Facebook icon in the footer")
    @Description("Scroll to footer, click the Facebook icon, and assert the URL contains facebook.com.")
    public void tc08_verifyFacebookLink() {

        HomePage homePage = new HomePage(driver);

        Allure.step("Step 1 – Scroll to footer", () -> {
            homePage.scrollToBottom();
        });

        Allure.step("Step 2 – Assert Facebook link is present in footer", () -> {
            Assert.assertTrue(
                homePage.isFacebookLinkPresent(),
                "Facebook icon/link should be present in the footer"
            );
        });

        Allure.step("Step 3 – Click Facebook icon", () -> {
            homePage.clickFacebookLink();
        });

        Allure.step("Step 4 – Assert new tab or current URL contains facebook.com", () -> {
            // Switch to new tab if opened
            if (driver.getWindowHandles().size() > 1) {
                wait.waitForNumberOfWindows(2);
                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));
            }
            String url = driver.getCurrentUrl();
            Assert.assertTrue(
                url.contains("facebook.com"),
                "URL should contain 'facebook.com'. Actual: " + url
            );
        });
    }

    @Test(description = "TC09: Click LinkedIn icon in footer – assert URL contains linkedin.com")
    @Severity(SeverityLevel.MINOR)
    @Story("User clicks the LinkedIn icon in the footer")
    @Description("Navigate to footer, click the LinkedIn icon, and assert the URL contains linkedin.com.")
    public void tc09_verifyLinkedInLink() {

        HomePage homePage = new HomePage(driver);

        Allure.step("Step 1 – Scroll to footer", () -> {
            homePage.scrollToBottom();
        });

        Allure.step("Step 2 – Assert LinkedIn link is present in footer", () -> {
            Assert.assertTrue(
                homePage.isLinkedInLinkPresent(),
                "LinkedIn icon/link should be present in the footer"
            );
        });

        Allure.step("Step 3 – Click LinkedIn icon", () -> {
            homePage.clickLinkedInLink();
        });

        Allure.step("Step 4 – Assert new tab or current URL contains linkedin.com", () -> {
            if (driver.getWindowHandles().size() > 1) {
                wait.waitForNumberOfWindows(2);
                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));
            }
            String url = driver.getCurrentUrl();
            Assert.assertTrue(
                url.contains("linkedin.com"),
                "URL should contain 'linkedin.com'. Actual: " + url
            );
        });
    }

    @Test(description = "TC10: Click YouTube icon in footer – assert new tab URL contains youtube.com")
    @Severity(SeverityLevel.MINOR)
    @Story("User clicks the YouTube icon in the footer")
    @Description("Navigate to footer, click the YouTube icon, switch to new tab, " +
                 "assert URL contains youtube.com.")
    public void tc10_verifyYouTubeLink() {

        HomePage homePage = new HomePage(driver);

        Allure.step("Step 1 – Scroll to footer", () -> {
            homePage.scrollToBottom();
        });

        Allure.step("Step 2 – Assert YouTube link is present in footer", () -> {
            Assert.assertTrue(
                homePage.isYouTubeLinkPresent(),
                "YouTube icon/link should be present in the footer"
            );
        });

        Allure.step("Step 3 – Click YouTube icon", () -> {
            homePage.clickYouTubeLink();
        });

        Allure.step("Step 4 – Switch to new tab and assert URL contains youtube.com", () -> {
            if (driver.getWindowHandles().size() > 1) {
                wait.waitForNumberOfWindows(2);
                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));
            }
            String url = driver.getCurrentUrl();
            Assert.assertTrue(
                url.contains("youtube.com"),
                "URL should contain 'youtube.com'. Actual: " + url
            );
        });
    }

    // ── Helper: reuse WaitHelper via driver ───────────────────────────────────
    private com.eyouth.utils.WaitHelper wait;

    @Override
    public void setUp() {
        super.setUp();
        wait = new com.eyouth.utils.WaitHelper(driver);
    }
}
