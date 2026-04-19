package com.eyouth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for an individual Course Details page.
 */
public class CourseDetailsPage extends BasePage {

    // "حول الدورة التدريبية" section heading
    @FindBy(xpath = "//h2[contains(text(),'حول الدورة التدريبية')] | //h3[contains(text(),'حول الدورة التدريبية')] | //*[contains(@class,'about') and contains(text(),'حول')]")
    private WebElement aboutCourseSection;

    // Course title on details page
    @FindBy(css = "h1, .course-detail-title, [class*='courseTitle']")
    private WebElement courseDetailTitle;

    // Subscribe / Enroll button on details page
    @FindBy(css = "[class*='subscribe'], [class*='enroll'], button[class*='register'], [class*='addToCart']")
    private WebElement subscribeButton;

    public CourseDetailsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Returns true when the "حول الدورة التدريبية" section is visible.
     */
    public boolean isAboutCourseSectionDisplayed() {
        return isDisplayed(By.xpath(
                "//*[contains(text(),'حول الدورة التدريبية')]"));
    }

    /**
     * Returns true once the course details page has loaded (title present).
     */
    public boolean isCourseDetailsTitleDisplayed() {
        return isDisplayed(By.cssSelector("h1, .course-detail-title, [class*='courseTitle']"));
    }

    /**
     * Clicks the subscribe/enroll button. Used in TC07.
     */
    public void clickSubscribe() {
        wait.waitForClickability(subscribeButton);
        scrollToElement(subscribeButton);
        jsClick(subscribeButton);
    }

    public boolean isSubscribeButtonDisplayed() {
        return isDisplayed(By.cssSelector(
                "[class*='subscribe'], [class*='enroll'], [class*='register']"));
    }
}
