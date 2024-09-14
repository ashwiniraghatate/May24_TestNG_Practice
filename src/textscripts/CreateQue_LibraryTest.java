package textscripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.DashboardPage;
import pages.LibraryPage;
import pages.LoginPage;
import pages.QuestionPage;
import pages.QuestionPage.QuestionType;

public class CreateQue_LibraryTest {

	@BeforeClass
	public void launchBrowser() {
		ControlActions.start("https://staging.dev.theeliteqa.com");
	}
	
	@Test
	public void verifyCreateMcqQuestion() throws InterruptedException {
		System.out.println("STEP 1- Login with Valid credentials.");
		LoginPage loginPage = new LoginPage();
		loginPage.login("ashwinimay24@gmail.com", "May@123");

		System.out.println("STEP - Click on Library Menu");
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateTo("Library");
		
		System.out.println("STEP 3 - Click on Create Question button.");
		LibraryPage libraryPage = new LibraryPage();
		QuestionPage questionPage = libraryPage.clickOnCreateQuestionBtn();
		questionPage.chooseQuestionType(QuestionType.MCQ);
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void closeBrowser() {
		ControlActions.quitBrowser();
	}
}
