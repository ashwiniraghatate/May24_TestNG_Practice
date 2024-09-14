package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class DashboardPage extends ControlActions{
	
	@FindBy (xpath = "//span[text()='Dashboard']")
	WebElement dashboardElement;

	@FindBy (xpath = "//button[@class='createAssementButton']")
	private WebElement createAssementButton;
	
	@FindBy (xpath = "//button[@class='createQuestionButton']")
	private WebElement createQuestionButton;
	
	@FindBy (xpath = "//div/span[text()='Total Assessments']/b")
	WebElement totalAssessmentsOnDashboardElement;
	
	@FindBy(xpath = "//span[contains(text(),'Recent Assessments (')]")
	private WebElement recentAssessmentElement;
	
	//PageFactory is used for initializing the elements when any method is called on it
	//it is used when @FindBy annotation is used
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isDahsboardPageDisplayed() {
		waitUntilElementIsVisible(dashboardElement); 
		return dashboardElement.isDisplayed(); 
	}
	
	//%s is used for passing string to dynamic path
	//if %s,%d is used then use format method of String class
	private String menuLocator = "//span[(text()='%s')]";
	
	public boolean isMenuDisplayed(String menuText) {
		String locator = String.format(menuLocator, menuText);
		return isElementDisplayed("XPATH", locator, true);
	}

	public void navigateTo(String menuText) {
		String locator = String.format(menuLocator, menuText);
		getElement("XPATH", locator, true).click();
	}
	
	//Enumerations or Java Enum serve the purpose of representing 
	//a group of named constants in a programming language. 
	//Java Enums are used when we know all possible values at compile time, 
	//such as choices on a menu,
	public enum CardOptions{
		INVITESUSED("Invites Used"),
		TOTALASSESSMENT("Total Assessments"),
		TOTALAPPEARED("Total Appeared"),
		TOTALQUALIFIED("Total Qualified");
		
		private final String optionText;
		
		private CardOptions(String optionText) {
			this.optionText = optionText;
		}
		
		String getOptions() {
			return optionText;
		}
	}

	public boolean isCardElementDisplayedUsingText(CardOptions cardOptions) {
		String locator = "//span[text()='"+ cardOptions.getOptions() +"']";
		return isElementDisplayed("xpath", locator, true);	
	}
	
	public boolean isCreateQuestionBtnEnabled() {
		return isElementEnabled(createQuestionButton);
	}
	
	public boolean isCreateAssementBtnEnabled() {
		return isElementEnabled(createAssementButton);
	}
	
	public int getTotalAssessmentsOnDashboardCount() {
		String totalAssesmentStrOnDashboard = totalAssessmentsOnDashboardElement.getText();
		int totalAssessmentsOnDashboardCount = Integer.parseInt(totalAssesmentStrOnDashboard);
		return totalAssessmentsOnDashboardCount;
	}
	
	public void waitForPageLoad() {
		waitUntilElementIsVisible(recentAssessmentElement);
	}
	
	public int getRecentAssessmentCount() {
		String recentAssessmentCountText = recentAssessmentElement.getText();
		String[] arr = recentAssessmentCountText.replace("(", "").replace(")","").trim().split(" ");
		int  recentAssessmentCount = Integer.parseInt(arr[arr.length-1]); // Recent Assessments 2 	
		return recentAssessmentCount;
	}
}
