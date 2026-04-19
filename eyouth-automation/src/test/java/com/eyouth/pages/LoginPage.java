package com.eyouth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the Login page.
 */
public class LoginPage extends BasePage {

    // Username / email input
    @FindBy(css = "input[name='username'], input[type='email'], input[name='email'], input[placeholder*='البريد'], input[placeholder*='اسم']")
    private WebElement usernameField;

    // Password input
    @FindBy(css = "input[type='password']")
    private WebElement passwordField;

    // Login / submit button
    @FindBy(css = "button[type='submit'], input[type='submit'], button[class*='login'], button[class*='signin']")
    private WebElement loginButton;

    // Error message container for invalid credentials
    @FindBy(css = "[class*='alert'], [class*='error-msg'], [class*='login-error'], .alert-danger")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnLoginPage() {
        return wait.waitForUrlContains("/login") || wait.waitForUrlContains("/signin");
    }

    public LoginPage enterUsername(String username) {
        wait.waitForVisibility(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        wait.waitForVisibility(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickLogin() {
        wait.waitForClickability(loginButton).click();
        return this;
    }

    /** Clears both fields and clicks login (TC06). */
    public LoginPage submitEmpty() {
        try { usernameField.clear(); } catch (Exception ignored) {}
        try { passwordField.clear(); } catch (Exception ignored) {}
        click(loginButton);
        return this;
    }

    /**
     * TC05: Returns true if an error message is shown after invalid login.
     */
    public boolean isErrorMessageDisplayed() {
        return isDisplayed(By.cssSelector(
                "[class*='alert'], [class*='error'], .alert-danger, [class*='login-error']"));
    }

    /**
     * TC06: Returns true if field-level validation messages appear.
     */
    public boolean areValidationMessagesDisplayed() {
        return isDisplayed(By.xpath(
                "//*[contains(@class,'invalid-feedback') or contains(@class,'field-error') " +
                "or contains(@class,'error-message')][contains(text(),'مطلوب')]"));
    }

    public String getErrorText() {
        try {
            return getText(By.cssSelector("[class*='alert'], [class*='error'], .alert-danger"));
        } catch (Exception e) {
            return "";
        }
    }
}
