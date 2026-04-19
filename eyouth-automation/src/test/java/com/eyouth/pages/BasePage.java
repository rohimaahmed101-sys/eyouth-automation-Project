package com.eyouth.pages;

import com.eyouth.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Base Page providing shared driver, waits, and JS helpers.
 */
public class BasePage {

    protected WebDriver driver;
    protected WaitHelper wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }

    protected void click(By locator) {
        wait.waitForClickability(locator).click();
    }

    protected void click(WebElement element) {
        wait.waitForClickability(element).click();
    }

    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    protected void type(By locator, String text) {
        WebElement el = wait.waitForVisibility(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected void type(WebElement element, String text) {
        wait.waitForVisibility(element).clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return wait.waitForVisibility(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return wait.waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void scrollToBottom() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
