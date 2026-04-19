package com.eyouth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the Search functionality.
 */
public class SearchPage extends BasePage {

    // Search input field (various possible selectors)
    @FindBy(css = "input[type='search'], input[placeholder*='ابحث'], input[name*='search'], input[class*='search']")
    private WebElement searchInput;

    // Search submit button
    @FindBy(css = "button[type='submit'], button[aria-label*='بحث'], [class*='search-btn'], [class*='searchButton']")
    private WebElement searchButton;

    // Results container
    @FindBy(css = "[class*='search-result'], [class*='results'], .course-card, [class*='courseCard']")
    private WebElement resultsContainer;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Types a keyword into the search box and submits.
     */
    public SearchPage searchFor(String keyword) {
        wait.waitForVisibility(searchInput);
        searchInput.clear();
        searchInput.sendKeys(keyword);
        // Try button click first; fallback to Enter key
        try {
            wait.waitForClickability(searchButton).click();
        } catch (Exception e) {
            searchInput.sendKeys(Keys.ENTER);
        }
        return this;
    }

    /**
     * Returns the current page title — used for assertion in TC01.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Returns true when results section is present.
     */
    public boolean areResultsDisplayed() {
        return isDisplayed(By.cssSelector(
                "[class*='search-result'], [class*='results'], .course-card"));
    }
}
