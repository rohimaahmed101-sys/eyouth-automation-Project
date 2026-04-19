package com.eyouth.tests;

import com.eyouth.config.ConfigReader;
import com.eyouth.utils.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base class for all test classes.
 * Handles driver init, teardown, and Allure screenshot attachment on failure.
 */
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.getBaseUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot();
        }
        DriverManager.quitDriver();
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    private byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
