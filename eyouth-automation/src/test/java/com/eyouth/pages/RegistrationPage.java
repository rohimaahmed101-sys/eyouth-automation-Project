package com.eyouth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the Registration / Signup page (/signup).
 */
public class RegistrationPage extends BasePage {

    // Username field
    @FindBy(css = "input[name='username'], input[placeholder*='اسم المستخدم'], input[id*='username']")
    private WebElement usernameField;

    // Email field
    @FindBy(css = "input[type='email'], input[name='email'], input[placeholder*='البريد']")
    private WebElement emailField;

    // Password field
    @FindBy(css = "input[type='password'][name*='pass']:not([name*='confirm']), input[id*='password']:not([id*='confirm']), input[placeholder*='كلمة المرور']:not([placeholder*='تأكيد'])")
    private WebElement passwordField;

    // Confirm password field
    @FindBy(css = "input[name*='confirm'], input[id*='confirm'], input[placeholder*='تأكيد']")
    private WebElement confirmPasswordField;

    // Submit / "انشاء" button
    @FindBy(css = "button[type='submit'], input[type='submit'], button[class*='register'], button[class*='create']")
    private WebElement createButton;

    // Username validation error
    @FindBy(xpath = "//*[contains(text(),'اسم المستخدم مطلوب') or contains(text(),'مطلوب') and contains(@class,'error')]")
    private WebElement usernameErrorMessage;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    /** Waits until the signup page is loaded (URL check). */
    public boolean isOnSignupPage() {
        return wait.waitForUrlContains("/signup");
    }

    /** Enters email into the registration form. */
    public RegistrationPage enterEmail(String email) {
        type(emailField, email);
        return this;
    }

    /** Enters password. */
    public RegistrationPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    /** Enters confirm password. */
    public RegistrationPage enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
        return this;
    }

    /** Leaves username empty (default — just don't fill it). */
    public RegistrationPage leaveUsernameEmpty() {
        // Intentionally leave blank
        return this;
    }

    /** Clicks the "انشاء" / submit button. */
    public RegistrationPage clickCreate() {
        click(createButton);
        return this;
    }

    /**
     * Returns true if the username validation error is displayed.
     * Checks multiple possible patterns for the error message.
     */
    public boolean isUsernameErrorDisplayed() {
        // Try the most specific XPath first
        boolean found = isDisplayed(By.xpath(
                "//*[contains(text(),'اسم المستخدم مطلوب') " +
                "or contains(text(),'مطلوب') " +
                "or contains(@class,'error') and ancestor::*[contains(@class,'username')]]"));
        if (!found) {
            // Fallback: any visible element with "مطلوب" near a username field
            found = isDisplayed(By.xpath(
                    "//*[contains(@class,'invalid-feedback') or contains(@class,'error-message') or contains(@class,'field-error')]" +
                    "[contains(text(),'مطلوب')]"));
        }
        return found;
    }

    public String getUsernameErrorText() {
        try {
            return getText(By.xpath(
                    "//*[contains(text(),'اسم المستخدم مطلوب') or (contains(text(),'مطلوب') and ancestor-or-self::*[contains(@class,'username')])]"));
        } catch (Exception e) {
            return "";
        }
    }
}
