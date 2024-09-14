package pages;

import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class QuestionPage extends ControlActions{

	public QuestionPage() {
		PageFactory.initElements(driver, this);
	}
	
	public enum QuestionType{
		MCQ,CODE;
	}
	
	public void chooseQuestionType(QuestionType type) {
		String locator = "//label[text()='"+type+"']";
		clickOnElement("XPATH",locator, true);
	}
}
