package com.eyouth.tests;

import com.eyouth.config.ConfigReader;
import com.eyouth.pages.HomePage;
import com.eyouth.pages.SearchPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC01 – Search with a valid keyword.
 */
@Epic("EYouth Automation")
@Feature("Search")
public class SearchTest extends BaseTest {

    @Test(description = "TC01: Search with valid Arabic keyword and verify page title")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User searches for a course using a valid keyword")
    @Description("Enter 'كيف تنضم إلى البنك' in the search field, click Search, " +
                 "and assert the page title contains the keyword.")
    public void tc01_searchWithValidKeyword() {

        String keyword = ConfigReader.getSearchKeyword(); // "كيف تنضم إلى البنك"

        Allure.step("Step 1 – Open home page and locate search", () -> {
            HomePage homePage = new HomePage(driver);
            homePage.openMenu();
        });

        Allure.step("Step 2 – Navigate to search page", () -> {
            // Navigate directly to search URL for reliability
            driver.get(ConfigReader.getBaseUrl() + "/search");
        });

        Allure.step("Step 3 – Enter keyword and submit search", () -> {
            SearchPage searchPage = new SearchPage(driver);
            searchPage.searchFor(keyword);
        });

        Allure.step("Step 4 – Assert page title contains keyword", () -> {
            String title = driver.getTitle();
            Assert.assertTrue(
                title.contains("البنك") || title.contains("كيف تنضم"),
                "Page title should contain the searched keyword. Actual title: " + title
            );
        });
    }
}
