package com.seta.utility;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.seta.base.BaseClass;

public class Utility extends BaseClass {

	// Capture Screenshot
	public static void captureScreenshot(WebDriver driver, String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshot/" + screenshotName + ".png"));
			System.out.println("Screenshot taken");
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
	}

	// Highlight Elements
	public static void ElementHighlight(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background:none;border:3px solid red;')", ele);
	}

	// Open URL
	public static void launchApp(String url) {
		driver.get(url);
		Duration timeout = Duration.ofSeconds(20);
		driver.manage().timeouts().pageLoadTimeout(timeout);
	}

	// Close Browser
	public static void closeBrowser() {
		driver.close();
	}
}