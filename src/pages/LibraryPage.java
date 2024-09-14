package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class LibraryPage extends ControlActions{
	
	@FindBy(xpath = "//p[text()='Create Question']")
	WebElement createQuestionBtn;
	
	public LibraryPage() {
		PageFactory.initElements(driver, this);
	}
	
	public QuestionPage clickOnCreateQuestionBtn() {
		clickOnElement(createQuestionBtn, true);
		return new QuestionPage();
	}
}
