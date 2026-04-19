package com.eyouth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the EYouth Home Page (https://eyouthlearning.com/ar).
 *
 * Locator strategy notes:
 *  - The site is an Arabic SPA (React/Vue).
 *  - Locators use aria-label, data-* attributes, link text, and CSS selectors
 *    for robustness. If the site updates its DOM, adjust the By values here.
 */
public class HomePage extends BasePage {

    // ── Header / Nav ──────────────────────────────────────────────────────────

    // Hamburger / side-menu toggle
    @FindBy(css = "button[aria-label='toggle menu'], .menu-toggle, .hamburger-btn, [class*='menuButton'], [class*='navToggle']")
    private WebElement menuToggleBtn;

    // Search icon in header
    @FindBy(css = "a[href*='search'], button[aria-label*='بحث'], [class*='search-icon'], .search-btn")
    private WebElement searchIcon;

    // "انضم لنا" (Join Us / Register) link
    @FindBy(xpath = "//a[contains(text(),'انضم لنا') or contains(text(),'انضم') or contains(@href,'/signup')]")
    private WebElement joinUsLink;

    // "تسجيل الدخول" (Login) link
    @FindBy(xpath = "//a[contains(text(),'تسجيل الدخول') or contains(@href,'/login') or contains(@href,'/signin')]")
    private WebElement loginLink;

    // "جميع الدورات" (All Courses) link
    @FindBy(xpath = "//a[contains(text(),'جميع الدورات') or contains(@href,'/courses') or contains(@href,'/ar/courses')]")
    private WebElement allCoursesLink;

    // ── Footer social icons ───────────────────────────────────────────────────

    @FindBy(xpath = "//footer//a[contains(@href,'facebook.com')]")
    private WebElement facebookLink;

    @FindBy(xpath = "//footer//a[contains(@href,'linkedin.com')]")
    private WebElement linkedInLink;

    @FindBy(xpath = "//footer//a[contains(@href,'youtube.com')]")
    private WebElement youtubeLink;

    // ── Constructor ───────────────────────────────────────────────────────────

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ── Actions ───────────────────────────────────────────────────────────────

    /** Opens the side/header menu if it exists as a toggle. */
    public void openMenu() {
        try {
            wait.waitForClickability(menuToggleBtn).click();
        } catch (Exception e) {
            // Menu may already be open on desktop; ignore.
        }
    }

    /** Navigates to the Search page by clicking the search icon. */
    public SearchPage clickSearchIcon() {
        wait.waitForClickability(searchIcon).click();
        return new SearchPage(driver);
    }

    /** Navigates directly to search URL (fallback if icon not visible). */
    public SearchPage navigateToSearch() {
        driver.get(driver.getCurrentUrl().replace("/ar", "") + "/ar/search");
        return new SearchPage(driver);
    }

    /** Clicks "جميع الدورات" and returns CoursesPage. */
    public CoursesPage clickAllCourses() {
        wait.waitForClickability(allCoursesLink).click();
        return new CoursesPage(driver);
    }

    /** Clicks "انضم لنا" and returns RegistrationPage. */
    public RegistrationPage clickJoinUs() {
        wait.waitForClickability(joinUsLink).click();
        return new RegistrationPage(driver);
    }

    /** Clicks "تسجيل الدخول" and returns LoginPage. */
    public LoginPage clickLogin() {
        wait.waitForClickability(loginLink).click();
        return new LoginPage(driver);
    }

    /** Scrolls to footer and clicks Facebook link. */
    public void clickFacebookLink() {
        scrollToBottom();
        wait.waitForClickability(facebookLink);
        scrollToElement(facebookLink);
        jsClick(facebookLink);
    }

    /** Scrolls to footer and clicks LinkedIn link. */
    public void clickLinkedInLink() {
        scrollToBottom();
        wait.waitForClickability(linkedInLink);
        scrollToElement(linkedInLink);
        jsClick(linkedInLink);
    }

    /** Scrolls to footer and clicks YouTube link. */
    public void clickYouTubeLink() {
        scrollToBottom();
        wait.waitForClickability(youtubeLink);
        scrollToElement(youtubeLink);
        jsClick(youtubeLink);
    }

    public boolean isFacebookLinkPresent() {
        scrollToBottom();
        return isDisplayed(By.xpath("//footer//a[contains(@href,'facebook.com')]"));
    }

    public boolean isLinkedInLinkPresent() {
        scrollToBottom();
        return isDisplayed(By.xpath("//footer//a[contains(@href,'linkedin.com')]"));
    }

    public boolean isYouTubeLinkPresent() {
        scrollToBottom();
        return isDisplayed(By.xpath("//footer//a[contains(@href,'youtube.com')]"));
    }
}
