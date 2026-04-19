package com.eyouth.tests;

import com.eyouth.pages.CoursesPage;
import com.eyouth.pages.HomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * TC11 – Verify course card UI elements.
 */
@Epic("EYouth Automation")
@Feature("Course Card UI")
public class CourseCardUITest extends BaseTest {

    @Test(description = "TC11: Assert course card contains image, title, instructor, subscribe button")
    @Severity(SeverityLevel.NORMAL)
    @Story("User views a course card on the Courses page")
    @Description("Open the Courses page and verify that at least one course card contains: " +
                 "a course image, course title, instructor name, and a subscribe button.")
    public void tc11_verifyCourseCardUIElements() {

        Allure.step("Step 1 – Navigate to All Courses page", () -> {
            HomePage homePage = new HomePage(driver);
            homePage.openMenu();
            homePage.clickAllCourses();
        });

        CoursesPage coursesPage = new CoursesPage(driver);

        Allure.step("Step 2 – Wait for course cards to load", () -> {
            int count = coursesPage.getCourseCount();
            Assert.assertTrue(count > 0,
                "At least one course card should be present on the Courses page");
        });

        // Use SoftAssert to report all failures, not just the first
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Step 3 – Assert course image is displayed", () -> {
            softAssert.assertTrue(
                coursesPage.isCourseImageDisplayed(),
                "Course card should display a course image"
            );
        });

        Allure.step("Step 4 – Assert course title is displayed", () -> {
            softAssert.assertTrue(
                coursesPage.isCourseTitleDisplayed(),
                "Course card should display a course title"
            );
        });

        Allure.step("Step 5 – Assert instructor name is displayed", () -> {
            softAssert.assertTrue(
                coursesPage.isInstructorNameDisplayed(),
                "Course card should display the instructor's name"
            );
        });

        Allure.step("Step 6 – Assert subscribe button is displayed", () -> {
            softAssert.assertTrue(
                coursesPage.isSubscribeButtonDisplayed(),
                "Course card should display a subscribe/enroll button"
            );
        });

        softAssert.assertAll();
    }
}
