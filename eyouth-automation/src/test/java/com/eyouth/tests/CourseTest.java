package com.eyouth.tests;

import com.eyouth.config.ConfigReader;
import com.eyouth.pages.CoursesPage;
import com.eyouth.pages.CourseDetailsPage;
import com.eyouth.pages.HomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC02 – Open course details.
 */
@Epic("EYouth Automation")
@Feature("Courses")
public class CourseTest extends BaseTest {

    @Test(description = "TC02: Navigate to all courses, open a course, assert details page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User opens a course detail page from the courses listing")
    @Description("From header/menu click 'جميع الدورات', click first course card, " +
                 "assert details page is open and 'حول الدورة التدريبية' section is displayed.")
    public void tc02_openCourseDetails() {

        Allure.step("Step 1 – Open home page", () -> {
            new HomePage(driver).openMenu();
        });

        CoursesPage coursesPage = new CoursesPage(driver);
        Allure.step("Step 2 – Click 'جميع الدورات'", () -> {
            HomePage homePage = new HomePage(driver);
            homePage.clickAllCourses();
        });

        Allure.step("Step 3 – Wait for course cards and click first course", () -> {
            int count = coursesPage.getCourseCount();
            Assert.assertTrue(count > 0, "At least one course card should be present");
        });

        CourseDetailsPage detailsPage = coursesPage.clickFirstCourse();

        Allure.step("Step 4 – Assert course details page is open", () -> {
            Assert.assertTrue(
                detailsPage.isCourseDetailsTitleDisplayed(),
                "Course details page title/heading should be displayed"
            );
        });

        Allure.step("Step 5 – Assert 'حول الدورة التدريبية' section is displayed", () -> {
            Assert.assertTrue(
                detailsPage.isAboutCourseSectionDisplayed(),
                "'حول الدورة التدريبية' section should be visible on the course details page"
            );
        });
    }
}
