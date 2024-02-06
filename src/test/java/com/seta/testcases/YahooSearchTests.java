package com.seta.testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.seta.base.BaseClass;
import com.seta.locators.Locators.yahooSearchPage;
import com.seta.pageobjects.GoogleSearchPage;
import com.seta.pageobjects.YahooSearchPage;
import com.seta.utility.ExtentManager;
import com.seta.utility.ReadConfig;
import com.seta.utility.Utility;

public class YahooSearchTests extends BaseClass implements yahooSearchPage {

	ReadConfig readPro = new ReadConfig();
	GoogleSearchPage googleSearchPage;
	YahooSearchPage yahooSearchPage;
	String url = readPro.getYahooURL();
	String searchTerm = readPro.getSearchKeyword();
	String badSearchTerm = readPro.getBadSearchKeyword();
	String specialCharSearchTerm = readPro.getSpecialCharSearchKeyword();
	String alphaNumericCharSearchTerm = readPro.getAlphaNumericCharSearchKeyword();
	String misSpelledCharSearchTerm = readPro.getMisSpelledCharSearchKeyword();
	String topThreeSearch = readPro.getTopThreeSearchKeyword();

	@BeforeMethod
	public void beforeMethod() throws Exception {
		ExtentManager.initialisation("Yahoo Search Tests");
		// Launch URL
		Utility.launchApp(url);
	}

	@Test
	public void verifyYahooSearchFirstLinkTest() throws Exception {
		yahooSearchPage = new YahooSearchPage();
		yahooSearchPage.verifySearchTerm(searchTerm);
	}

	@Test
	public void verifyYahooSearchTopThreeTermResultsTest() throws Exception {
		yahooSearchPage = new YahooSearchPage();
		yahooSearchPage.verifyTopThreeSearchResults(topThreeSearch);
	}

	@Test
	public void verifyYahooSearchWithBadTermTest() throws Exception {
		yahooSearchPage = new YahooSearchPage();
		yahooSearchPage.verifyBadSearchTerm(badSearchTerm);
	}

	@Test
	public void verifyYahooSearchWithSpecialCharTermTest() throws Exception {
		yahooSearchPage = new YahooSearchPage();
		yahooSearchPage.verifySpecialCharSearchTerm(specialCharSearchTerm);
	}

	@Test
	public void verifyYahooSearchWithAlphaNumericCharTermTest() throws Exception {
		yahooSearchPage = new YahooSearchPage();
		yahooSearchPage.verifyAlphaNumericCharSearchTerm(alphaNumericCharSearchTerm);
	}

	@Test
	public void verifyYahooSearchWithMisSpelledCorrectionTermTest() throws Exception {
		yahooSearchPage = new YahooSearchPage();
		yahooSearchPage.verifyMisSpelledSearchTermSuggestion(misSpelledCharSearchTerm);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}
	}
}