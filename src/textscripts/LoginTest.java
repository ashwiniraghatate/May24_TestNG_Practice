/*"STEP 1 - Launch Chrome browser
STEP 2 - Hit https://staging.dev.theeliteqa.com URL
VERFIY - Login page is visible [Login button is displayed, useremail is enabled, password is enabled]
STEP 3 - Enter valid username
STEP 4 - Enter valid password
STEP 5 - Click on Login button
VERIFY - Dashboard page is displayed"
*/
package textscripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.ControlActions;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest {

	WebDriver driver;
	
	@BeforeClass
	public void launchBrowser() {
		ControlActions.start("https://staging.dev.theeliteqa.com");
	}
	
	@Test
	public void loginPage() {
		LoginPage loginPage = new LoginPage();
		System.out.println("VERFIY - Login page is visible [Login button is displayed, useremail is enabled, password is enabled]");
		System.out.println("Verify - Login button is displayed");
		boolean loginBtnFlag = loginPage.isLoginBtnDisplayed();
		Assert.assertTrue(loginBtnFlag, "Login button was not displayed");
		
		System.out.println("Verify - useremail is enabled");
		boolean emailFieldFlag = loginPage.isEmailFieldDisplayed();
		Assert.assertTrue(emailFieldFlag, "Email field was not displayed");
		
		System.out.println("Verify - password is enabled");
		boolean passwordFlag = loginPage.isPasswordFieldDisplayed();
		Assert.assertTrue(passwordFlag, "Password field is not displayed");
		
		System.out.println("STEP 3 - Enter valid username");
		loginPage.setEmailId("ashwinimay24@gmail.com");
		
		System.out.println("STEP 4 - Enter valid password");
		loginPage.setPassword("May@123");
		
		System.out.println("STEP 5 - Click on Login button");
		loginPage.clickOnLoginButton();
		
		
		System.out.println("VERIFY - Dashboard page is displayed"); 
		//WebElement inviteUsed = driver.findElement(By.xpath("//span[text()='Invites Used']"));
		DashboardPage dashboardPage = new DashboardPage();
		boolean dahboardFlag = dashboardPage.isMenuDisplayed("Dashboard");
		Assert.assertTrue(dahboardFlag, "Dashboard is not displayed");
		 
	}
	
	@Test
	public void validateInvalidDetails() {
		LoginPage loginPage = new LoginPage();
		
		System.out.println("STEP 3 - Enter valid username");
		loginPage.setEmailId(".com");
		
		System.out.println("STEP 4 - Enter Invalid password");
		loginPage.setPassword("May@1234");
		
		System.out.println("STEP 5 - Click on Login button");
		loginPage.clickOnLoginButton();
		
		System.out.println("VERIFY - Popup with the error message "
				+ "'Authentication failed, please check your username and password.' should be displayed");

		String actualText = loginPage.getPopUpMessage();
		SoftAssert softAssert = new SoftAssert();
		String expectedText = "Authentication failed, please check your username and password.";
		softAssert.assertEquals(actualText, expectedText);
	}
	
	@AfterClass
	public void closeBrowser() {
		ControlActions.quitBrowser();
	}
}
