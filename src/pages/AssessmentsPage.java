package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class AssessmentsPage extends ControlActions{

	@FindBy (xpath = "//label[contains(text(),'Assessments')]")
	WebElement totalAssessmentField;
	
	public AssessmentsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void waitForPageLoad() {
		waitUntilElementIsVisible(totalAssessmentField);
	}
	
	public int getTotalAssessmentsCount() {
		String assesment = totalAssessmentField.getText();
		//int totalAssesmentCount = Integer.parseInt(assesment.split("\\(")[1].replace(")", "").trim());
		int totalAssesmentCount = Integer.parseInt(assesment.split(" ")[1].replace("(", "").replace(")", ""));
		return totalAssesmentCount;
	}
	
	public enum AssessmentStatus{
		PUBLISHED("published"),
		COMPLETED("completed"),
		DRAFT("draft");
		
		private final String statusOfAssessmentText;
		
		private AssessmentStatus(String statusOfAssessmentText) {
			this.statusOfAssessmentText = statusOfAssessmentText;
		}
		
		String getStatus() {
			return statusOfAssessmentText;
		}
	}
	
	public int getCountBasedOnAssessmentStatus(AssessmentStatus statusOfAssessment) {
		String assessmentStatusCountString = getElement("xpath", "//div/span[contains(text(), '"+statusOfAssessment.getStatus()+"')]", true).getText();
		int assessmentStatusCount = Integer.parseInt(assessmentStatusCountString.split(" ")[1].replace("(", "").replace(")", ""));
		return assessmentStatusCount;
	}
}
