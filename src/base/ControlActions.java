package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ControlActions {

	protected static WebDriver driver;
	private static WebDriverWait wait;
	protected static JavascriptExecutor js;
	
	public static void start(String url) {
		System.out.println("Step - Launch Broswer and open the url");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		System.out.println("Step - Load Url");
		driver.get(url);
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		js = (JavascriptExecutor) driver;
	}
	
	public static WebElement getElement(String locatorType, String locatorValue,
			boolean isWaitRequired) {
		WebElement e = null;
		WebDriverWait wait = null;
		if (isWaitRequired) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		}
		
		switch(locatorType.toUpperCase()) {
			case "XPATH":
				if (isWaitRequired) {
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				}else {
					e = driver.findElement(By.xpath(locatorValue));
				}
				break;
			case "ID":
				if (isWaitRequired) {
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				}else {
					e = driver.findElement(By.id(locatorValue));
				}
				break;
			case "NAME":
				if (isWaitRequired) {
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				}else {
					e = driver.findElement(By.name(locatorValue));
				}
				break;
			case "CLASSNAME":
				if (isWaitRequired) {
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				}else {
					e = driver.findElement(By.className(locatorValue));
				}
				break;
			case "CSSSELECTOR":
				if (isWaitRequired) {
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				}else {
					e = driver.findElement(By.cssSelector(locatorValue));
				}
				break;
			case "LINKTEXT":
				if (isWaitRequired) {
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				}else {
					e = driver.findElement(By.linkText(locatorValue));
				}
				break;
			case "PARTIALLINKTEXT":
				if (isWaitRequired) {
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
				}else {
					e = driver.findElement(By.partialLinkText(locatorValue));
				}
				break;
			case "TAGNAME":
				if (isWaitRequired) {
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
				}else {
					e = driver.findElement(By.tagName(locatorValue));
				}
			default:
	            // Throw an object of user defined exception
				throw new InvalidLocatorTypeException ("Valid locators are ID, NAME, XPATH, TAGNAME, PARTIALLINKTEXT, "
						+ "LINKTEXT, CSSSELECTOR, CLASSNAME but you have passed " + locatorType +
						" which is not supported");
		}		
		return e;
	}
	
	public static WebElement waitUntilElementIsVisible(WebElement e) {
		return wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	protected static void scrollTillElement(WebElement e) {
		js.executeScript("arguments[0].scrollIntoView(true)", e);

	}
	
	protected static boolean isElementDisplayed(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		try {
			if (e.isDisplayed()) {
				return true;
			}else {
				scrollTillElement(e);
				return e.isDisplayed();
			}
		}catch(NoSuchElementException |TimeoutException ne) {
			return false;
		}		
	}
	
	protected boolean isElementDisplayed(WebElement e, boolean isWaitRequired) {
		try {
			if (isWaitRequired)
				waitUntilElementIsVisible(e);
			return e.isDisplayed();
		}catch(NoSuchElementException |TimeoutException ne) {
			return false;
		}		
	}
	
	protected boolean isElementEnabled(WebElement e) {
		return e.isEnabled();
	}
	
	public static void quitBrowser() {
		System.out.println("CLEAN UP - Close the browser");
		driver.quit();
	}
	
	protected void clickOnElementUsingJs(WebElement e) {
		js.executeScript("arguments[0].click()", e);
	}
	
	protected void clickOnElement(WebElement e, boolean isWaitRequired) {
		if(isWaitRequired)
			waitUntilElementIsVisible(e);
		try {
			e.click();
		}catch(ElementClickInterceptedException ec) {
			clickOnElementUsingJs(e);
		}
	}
	
	protected void clickOnElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		clickOnElement(e, false);
	}
}
