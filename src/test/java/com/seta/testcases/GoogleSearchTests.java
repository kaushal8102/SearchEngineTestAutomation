package com.seta.testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.seta.base.BaseClass;
import com.seta.pageobjects.GoogleSearchPage;
import com.seta.utility.ExtentManager;
import com.seta.utility.ReadConfig;
import com.seta.utility.Utility;

public class GoogleSearchTests extends BaseClass {

	ReadConfig readPro = new ReadConfig();
	GoogleSearchPage googleSearchPage;
	String url = readPro.getURL();
	String searchTerm = readPro.getSearchKeyword();
	String badSearchTerm = readPro.getBadSearchKeyword();
	String specialCharSearchTerm = readPro.getSpecialCharSearchKeyword();
	String alphaNumericCharSearchTerm = readPro.getAlphaNumericCharSearchKeyword();
	String misSpelledCharSearchTerm = readPro.getMisSpelledCharSearchKeyword();

	@BeforeMethod
	public void beforeMethod() throws Exception {
		ExtentManager.initialisation("Google_Search_Test");
		// Launch URL
		Utility.launchApp(url);
	}

	@Test
	public void verifyGoogleSearchFirstLinkTest() throws Exception {
		googleSearchPage = new GoogleSearchPage();
		googleSearchPage.verifySearchTerm(searchTerm);
	}

	@Test
	public void verifyGoogleSearchWithBadTermTest() throws Exception {
		googleSearchPage = new GoogleSearchPage();
		googleSearchPage.verifyBadSearchTerm(badSearchTerm);
	}

	@Test
	public void verifyGoogleSearchWithSpecialCharTermTest() throws Exception {
		googleSearchPage = new GoogleSearchPage();
		googleSearchPage.verifySpecialCharSearchTerm(specialCharSearchTerm);
	}

	@Test
	public void verifyGoogleSearchWithAlphaNumericCharTermTest() throws Exception {
		googleSearchPage = new GoogleSearchPage();
		googleSearchPage.verifyAlphaNumericCharSearchTerm(alphaNumericCharSearchTerm);
	}

	@Test
	public void verifyGoogleSearchWithMisSpelledCorrectionTermTest() throws Exception {
		googleSearchPage = new GoogleSearchPage();
		googleSearchPage.verifyMisSpelledSearchTermSuggestion(misSpelledCharSearchTerm);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}
	}
}
