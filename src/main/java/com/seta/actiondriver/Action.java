package com.seta.actiondriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.seta.base.BaseClass;
import com.seta.utility.ExtentManager;
import com.seta.utility.Utility;

public class Action extends BaseClass {

	static Actions actions;
	public static int PAGE_LOAD_TIMEOUT = 60;

	// Find an element
	public static boolean findWebElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		implicitWait(driver, 20);
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			// System.out.println("Location not found: "+locatorName);
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element at : " + ele);

			} else {
				System.out.println("Unable to locate element at : " + ele);
			}
		}
		return flag;
	}

	// Check if an element is displayed
	public static boolean isElementDisplayed(WebDriver driver, WebElement ele) {
		boolean flag = false;
		implicitWait(driver, 20);
		flag = findWebElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed" + ele);
			} else {
				System.out.println("The element is not Displayed" + ele);
			}
		} else {
			System.out.println("Not displayed ");
		}
		return flag;
	}

	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Select option by sending keys
	public static boolean selectBySendkeys(String value, WebElement ele) {
		boolean flag = false;
		try {
			ele.sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Selected value from the DropDown");
			} else {
				System.out.println("Not Selected value from the DropDown");
			}
		}
	}

	// Switch to a frame by index
	public static void switchToFrameByIndex(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	// Switch to the default content
	public static void switchToDefaultFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public static String getTitle(WebDriver driver) {
		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			System.out.println("Title of the page is: \"" + text + "\"");
		}
		return text;
	}

	public static String getCurrentURL(WebDriver driver) {
		boolean flag = false;

		String text = driver.getCurrentUrl();
		if (flag) {
			System.out.println("Current URL is: \"" + text + "\"");
		}
		return text;
	}

	public static void implicitWait(WebDriver driver, int timeOut) {
		Duration timeout = Duration.ofSeconds(10);
		driver.manage().timeouts().pageLoadTimeout(timeout);
	}

	public static void explicitWait(WebDriver driver, WebElement element, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitFor(int miliSecond) {
		try {
			Thread.sleep(miliSecond);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void click(WebDriver driver, WebElement ele) {
		actions = new Actions(driver);
		actions.moveToElement(ele).click().build().perform();

	}

	public static void exceptionOnFailure(String message, Exception e) {
		RuntimeException ex = new RuntimeException(message + " : " + e.getMessage());
		System.out.println("Exception Logging For: " + message);
		ex.setStackTrace(e.getStackTrace());
		throw ex;
	}

	public static void sendText(WebElement inputField, String text) {
		try {
			inputField.click();
			clearInputField(inputField);
			inputField.sendKeys(text);
		} catch (Exception e) {
			String ExceptionMessage = "Enter Text is failed: " + inputField;
			exceptionOnFailure(ExceptionMessage, e);
		}
	}

	public static void clearInputField(WebElement ele) {
		ele.sendKeys(Keys.CONTROL + "a");
		waitFor(1000);
		ele.sendKeys(Keys.DELETE);
	}

	public static void clearInputField(By locator) {
		WebElement ele = getElement(locator);
		ele.sendKeys(Keys.CONTROL + "a");
		waitFor(1000);
		ele.sendKeys(Keys.DELETE);
		waitFor(2000);
	}

	public static WebElement getElement(By value) {
		WebElement ele = null;
		try {
			ele = getElement(value);
		} catch (Exception e) {
			String ExceptionMessage = "Get Element is failed: " + value;
			exceptionOnFailure(ExceptionMessage, e);
		}
		return ele;
	}

	public static void waitUntilElementPresent(By locator) {
		System.out.println("Waiting for : " + locator);
		getElement(locator);
	}

	public static void waitUntilElementDisplay(By locator) {
		System.out.println("Waiting for : " + locator);
		getElement(locator);
	}

	public static String getText(WebElement element) {
		return element.getText();
	}

	public static List<WebElement> getElements(By value) {
		try {
			waitUntilElementDisplay(value);
			return driver.findElements(value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public WebElement getElementByText(String name) {
		String xpath = String.format("//element[text()='%s']", name); // Replace "element" with the actual tag name
		return driver.findElement(By.xpath(xpath));
	}

	public static void pressENTERkey() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
		Duration timeout = Duration.ofSeconds(30);
		driver.manage().timeouts().pageLoadTimeout(timeout);
	}

	// Scroll to the specified element using JavaScript
	public static void scrollToElement(WebDriver driver, WebElement element) throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

		// Highlight element
		Utility.ElementHighlight(driver, element);
	}

	public static void logReport(String expectedSearch, String actualSearch) {
		if (expectedSearch != null) {
			ExtentManager.logStat(Status.PASS, "The term '" + actualSearch + "' displayed in the first search result.");
		} else {
			ExtentManager.logStat(Status.FAIL, "The term '" + actualSearch + "' is not displayed.");
		}
	}

	public static void logResult(boolean expectedSearch, String actualSearch) {
		if (expectedSearch == true) {
			ExtentManager.logStat(Status.PASS, "The term '" + actualSearch + "' displayed in the first search result.");
		} else {
			ExtentManager.logStat(Status.FAIL, "The term '" + actualSearch + "' is not displayed.");
		}
	}

	public static void verifyTopThreeSearchResults(String locator, String expectedSearch) throws Exception {
		// Find all search result links
		List<WebElement> searchTopResults = driver.findElements(By.xpath(locator));

		// Iterate through the top three search results
		int count = Math.min(searchTopResults.size(), 3);
		for (int i = 0; i < count; i++) {
			WebElement searchResult = searchTopResults.get(i);
			String searchExpectedText = searchResult.getText();
			Assert.assertTrue(searchExpectedText.toLowerCase().contains(expectedSearch));
		}
	}
}