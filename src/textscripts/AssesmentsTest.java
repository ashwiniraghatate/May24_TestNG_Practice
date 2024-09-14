package textscripts;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.AssessmentsPage;
import pages.AssessmentsPage.AssessmentStatus;
import pages.DashboardPage;
import pages.LoginPage;

public class AssesmentsTest {

	@BeforeClass
	public void launchBrowser() {
		ControlActions.start("https://staging.dev.theeliteqa.com");
	}

	@Test
	public void verifyAssesmenetCount() throws InterruptedException {
		System.out.println("STEP 1- Login with Valid credentials.");
		LoginPage loginPage = new LoginPage();
		loginPage.login("ashwinimay24@gmail.com", "May@123");

		System.out.println("STEP 2 - Go to Assessments Tab");
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.navigateTo("Assessments");
		
		AssessmentsPage assessmentPage = new AssessmentsPage();
//		assessmentPage.waitForPageLoad();
		Thread.sleep(3000);	//want to add wait till any element is displayed	
		
		System.out.println("STEP 3 - Count total number of assessments from Published, Completed and Draft");
		System.out.println("3.1 - Count published");
		int publishedCount = assessmentPage.getCountBasedOnAssessmentStatus(AssessmentStatus.PUBLISHED);
		System.out.println(publishedCount);
		
		System.out.println("3.2 - Completed count");
		int completedCount = assessmentPage.getCountBasedOnAssessmentStatus(AssessmentStatus.COMPLETED);
		System.out.println(completedCount);
		
		System.out.println("3.3 - Draft Count");
		int draftCount = assessmentPage.getCountBasedOnAssessmentStatus(AssessmentStatus.DRAFT);
		System.out.println(draftCount);
		
		int totalCountofAllAssessments = publishedCount + completedCount + draftCount;
		
		System.out.println("totalAssesmentCount");
		int totalAssesmentCount = assessmentPage.getTotalAssessmentsCount();
		System.out.println(totalAssesmentCount);
		
		System.out.println("VERIFY - Total Assessments on assessment page should be equal to sum of published, completed and draft count.");
		Assert.assertEquals(totalAssesmentCount, totalCountofAllAssessments);
		
		System.out.println("STEP 4 - Go to Dashboard tab");
		dashboardPage.navigateTo("Dashboard");
		
//		dashboardPage.waitForPageLoad();//check if this works
		Thread.sleep(3000);	//want to add wait till any element is displayed	
		
		System.out.println("VERIFY - Total assessments count on dashboard page against Assessments tab.");
		int totalAssesmentCountOnDashboard = dashboardPage.getTotalAssessmentsOnDashboardCount();
		Assert.assertEquals(totalAssesmentCountOnDashboard, totalAssesmentCount);
		
		System.out.println("VERIFY - Recent assessments should be 6 or less than 6");
		System.out.println("Recent assessments are: "+dashboardPage.getRecentAssessmentCount());
	}

	@AfterClass
	public void closeBrowser() {
		ControlActions.quitBrowser();
	}
}
