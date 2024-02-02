package com.seta.base;

import java.time.Duration;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.seta.utility.ExtentManager;
import com.seta.utility.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	static ReadConfig readConfig = new ReadConfig();
	public static WebDriver driver;
	public static String chromeDriver = readConfig.getChromeDriverPath();

	// Driver initialization
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Duration timeout = Duration.ofSeconds(40);
		driver.manage().timeouts().pageLoadTimeout(timeout);
	}

	// Quit the driver
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	// Extent Report initialization
	@BeforeSuite
	public void beforeSuite() {
		ExtentManager.startUp();
		DOMConfigurator.configure("log4j.xml");
	}

	// End Extent Report Session
	@AfterSuite
	public void afterSuite() {
		ExtentManager.endTest();
	}
}