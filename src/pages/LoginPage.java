package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class LoginPage extends ControlActions{
	@FindBy (xpath = "//button[contains(text(), 'Login')]")
	WebElement loginBtnLocator;
	
	@FindBy (xpath = "//input[@placeholder='Enter email']")
	WebElement emailFieldLocator;
	
	@FindBy (xpath = "//input[@placeholder='Enter password']")
	WebElement passwordFieldLocator;
	
	@FindBy (xpath = "//div[contains(@class,'Toastify__toast-container')]")
	WebElement popUpMessageElement;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isLoginBtnDisplayed() {
		WebElement loginBtn = ControlActions.waitUntilElementIsVisible(loginBtnLocator);
		return loginBtn.isDisplayed();
	}
	
	public boolean isEmailFieldDisplayed() {
		WebElement emailField = ControlActions.waitUntilElementIsVisible(emailFieldLocator);
		return emailField.isDisplayed();
	}
	
	public boolean isPasswordFieldDisplayed() {
		WebElement passwordField = ControlActions.waitUntilElementIsVisible(passwordFieldLocator);
		return passwordField.isDisplayed();
	}
	
	public void setEmailId(String emailId) {
		WebElement emailElement = ControlActions.waitUntilElementIsVisible(emailFieldLocator);
		emailElement.sendKeys(emailId);
	}
	
	public void setPassword(String password) {
		WebElement passwordElement = ControlActions.waitUntilElementIsVisible(passwordFieldLocator);
		passwordElement.sendKeys(password);
	}
	
	public void login(String emailId, String password) {
		setEmailId(emailId);
		setPassword(password);
		clickOnLoginButton();
	}
	
	public String getPopUpMessage() {
		return waitUntilElementIsVisible(popUpMessageElement).getText();
	}
	
	public void clickOnLoginButton() {
		loginBtnLocator.click();
	}
}
