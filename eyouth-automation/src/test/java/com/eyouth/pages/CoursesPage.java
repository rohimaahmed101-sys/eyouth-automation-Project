package com.eyouth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for the All Courses listing page.
 */
public class CoursesPage extends BasePage {

    // All course card elements
    @FindBy(css = ".course-card, [class*='courseCard'], [class*='course-item'], article.course")
    private List<WebElement> courseCards;

    // First course card (used for simple navigation)
    @FindBy(css = ".course-card:first-child, [class*='courseCard']:first-child")
    private WebElement firstCourseCard;

    // Course card: image
    @FindBy(css = ".course-card img, [class*='courseCard'] img, [class*='course-image']")
    private WebElement courseImage;

    // Course card: title
    @FindBy(css = ".course-card h2, .course-card h3, [class*='courseCard'] [class*='title'], [class*='course-title']")
    private WebElement courseTitle;

    // Course card: instructor name
    @FindBy(css = "[class*='instructor'], [class*='teacher'], .course-card [class*='author']")
    private WebElement instructorName;

    // Course card: subscribe / enroll button
    @FindBy(css = "[class*='subscribe'], [class*='enroll'], button[class*='course']")
    private WebElement subscribeButton;

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Waits for course cards to appear and returns the count.
     */
    public int getCourseCount() {
        wait.waitForPresenceOfAll(By.cssSelector(
                ".course-card, [class*='courseCard'], [class*='course-item']"));
        return courseCards.size();
    }

    /**
     * Clicks the first available course card and returns CourseDetailsPage.
     */
    public CourseDetailsPage clickFirstCourse() {
        wait.waitForPresenceOfAll(By.cssSelector(
                ".course-card, [class*='courseCard'], [class*='course-item']"));
        WebElement card = courseCards.get(0);
        scrollToElement(card);
        click(card);
        return new CourseDetailsPage(driver);
    }

    /**
     * Clicks course at a specific index (0-based).
     */
    public CourseDetailsPage clickCourseAtIndex(int index) {
        wait.waitForPresenceOfAll(By.cssSelector(
                ".course-card, [class*='courseCard'], [class*='course-item']"));
        WebElement card = courseCards.get(index);
        scrollToElement(card);
        click(card);
        return new CourseDetailsPage(driver);
    }

    // ── TC11: UI assertions for a single course card ──────────────────────────

    public boolean isCourseImageDisplayed() {
        try {
            return wait.waitForVisibility(
                    By.cssSelector(".course-card img, [class*='courseCard'] img")).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public boolean isCourseTitleDisplayed() {
        try {
            return wait.waitForVisibility(
                    By.cssSelector(".course-card h2, .course-card h3, [class*='course-title'], [class*='courseTitle']"))
                    .isDisplayed();
        } catch (Exception e) { return false; }
    }

    public boolean isInstructorNameDisplayed() {
        try {
            return wait.waitForVisibility(
                    By.cssSelector("[class*='instructor'], [class*='teacher'], [class*='author']"))
                    .isDisplayed();
        } catch (Exception e) { return false; }
    }

    public boolean isSubscribeButtonDisplayed() {
        try {
            return wait.waitForVisibility(
                    By.cssSelector("[class*='subscribe'], [class*='enroll'], [class*='register'], button[class*='course']"))
                    .isDisplayed();
        } catch (Exception e) { return false; }
    }
}
