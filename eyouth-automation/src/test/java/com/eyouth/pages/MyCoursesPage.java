package com.eyouth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for the "My Courses" / Dashboard cart page.
 * Used in TC07 (end-to-end) to assert a course was added.
 */
public class MyCoursesPage extends BasePage {

    // Enrolled course cards inside "my courses" / cart area
    @FindBy(css = "[class*='my-course'], [class*='myCourse'], [class*='enrolled'], [class*='cart-item']")
    private List<WebElement> enrolledCourses;

    // Navigation link to "دوراتي" / "My Courses" 
    @FindBy(xpath = "//a[contains(text(),'دوراتي') or contains(text(),'my courses') or contains(@href,'/my-courses') or contains(@href,'/profile')]")
    private WebElement myCoursesNavLink;

    public MyCoursesPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToMyCourses() {
        wait.waitForClickability(myCoursesNavLink).click();
    }

    /**
     * Returns true if the enrolled courses list is non-empty.
     */
    public boolean hasEnrolledCourse() {
        try {
            wait.waitForPresenceOfAll(By.cssSelector(
                    "[class*='my-course'], [class*='myCourse'], [class*='enrolled'], [class*='cart-item']"));
            return !enrolledCourses.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if any enrolled course card contains the given course title.
     */
    public boolean containsCourseWithTitle(String partialTitle) {
        try {
            List<WebElement> cards = wait.waitForPresenceOfAll(By.cssSelector(
                    "[class*='my-course'], [class*='myCourse'], [class*='enrolled'], [class*='cart-item']"));
            return cards.stream()
                    .anyMatch(el -> el.getText().contains(partialTitle));
        } catch (Exception e) {
            return false;
        }
    }
}
