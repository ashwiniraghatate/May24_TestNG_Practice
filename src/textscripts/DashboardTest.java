package textscripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.ControlActions;
import pages.DashboardPage;
import pages.DashboardPage.CardOptions;
import pages.LoginPage;

public class DashboardTest {

	WebDriver driver;

	@BeforeClass
	public void launchBrowser() {
		ControlActions.start("https://staging.dev.theeliteqa.com");
	}
	@Test
	public void validateDashboardUI() {
		System.out.println("STEP 1- Login with Valid credentials");
		LoginPage loginPage = new LoginPage();
		loginPage.login("ashwinimay24@gmail.com", "May@123");
		
		System.out.println("VERFIY - Invites Used, Total Assessments, Total Appeared, Total Qualified is visible");	
		DashboardPage dashboardPage = new DashboardPage();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(dashboardPage.isCardElementDisplayedUsingText(CardOptions.INVITESUSED), "Invites Used tab is not displayed");
		softAssert.assertTrue(dashboardPage.isCardElementDisplayedUsingText(CardOptions.TOTALASSESSMENT), "Total Assessments tab is not displayed");
		softAssert.assertTrue(dashboardPage.isCardElementDisplayedUsingText(CardOptions.TOTALAPPEARED), "Total Appeared tab is not displayed");
		softAssert.assertTrue(dashboardPage.isCardElementDisplayedUsingText(CardOptions.TOTALQUALIFIED), "Total Qualified tab is not displayed");
		
		softAssert.assertAll();
		
		System.out.println("VERIFY - Assessments, Library, Candidates, Interviews tabs are visible.");
		softAssert.assertTrue(dashboardPage.isMenuDisplayed("Dashboard"), "Dashboard tab is not displayed");
		softAssert.assertTrue(dashboardPage.isMenuDisplayed("Assessments"), "Assessments tab is not displayed");
		softAssert.assertTrue(dashboardPage.isMenuDisplayed("Library"), "Library tab is not displayed");
		softAssert.assertTrue(dashboardPage.isMenuDisplayed("Candidates"), "Candidates tab is not displayed");
		softAssert.assertTrue(dashboardPage.isMenuDisplayed("Interviews"), "Interviews tab is not displayed");
		
		softAssert.assertAll();
		
		System.out.println("VERIFY - Create Assessments and Create Question button is clickable.");
		softAssert.assertTrue(dashboardPage.isCreateAssementBtnEnabled(), "Create Assessment btn is not clickable");
		softAssert.assertTrue(dashboardPage.isCreateQuestionBtnEnabled(), "Create question btn is not clickable");
	}
	
	@AfterClass
	public void closeBrowser() {
		ControlActions.quitBrowser();
	}
}
