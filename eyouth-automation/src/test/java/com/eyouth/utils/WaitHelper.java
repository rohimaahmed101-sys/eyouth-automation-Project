package com.eyouth.utils;

import com.eyouth.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Centralised explicit-wait helpers.
 * Thread.sleep() is NEVER used here.
 */
public class WaitHelper {

    private final WebDriverWait wait;
    private final WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    /** Wait until element is visible and return it. */
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /** Wait until element is clickable and return it. */
    public WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /** Wait until URL contains the given fragment. */
    public boolean waitForUrlContains(String fragment) {
        return wait.until(ExpectedConditions.urlContains(fragment));
    }

    /** Wait until page title contains text. */
    public boolean waitForTitleContains(String text) {
        return wait.until(ExpectedConditions.titleContains(text));
    }

    /** Wait for a list of elements to be visible. */
    public List<WebElement> waitForVisibilityOfAll(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /** Wait until at least one element is present. */
    public List<WebElement> waitForPresenceOfAll(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /** Wait for element to be present in DOM (not necessarily visible). */
    public WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /** Scroll element into view via JS then wait for clickability. */
    public WebElement scrollAndWait(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        return waitForClickability(element);
    }

    /** Wait for number of browser tabs/windows to be count. */
    public boolean waitForNumberOfWindows(int count) {
        return wait.until(ExpectedConditions.numberOfWindowsToBe(count));
    }
}
