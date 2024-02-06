package com.seta.testcases;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.seta.base.BaseClass;
import com.seta.pageobjects.BingSearchPage;
import com.seta.utility.ExtentManager;
import com.seta.utility.ReadConfig;
import com.seta.utility.Utility;

public class BingSearchTests extends BaseClass {

	ReadConfig readPro = new ReadConfig();
	BingSearchPage bingSearchPage;
	String url = readPro.getBingURL();
	String searchTerm = readPro.getSearchKeyword();
	String badSearchTerm = readPro.getBadSearchKeyword();
	String specialCharSearchTerm = readPro.getSpecialCharSearchKeyword();
	String alphaNumericCharSearchTerm = readPro.getAlphaNumericCharSearchKeyword();
	String misSpelledCharSearchTerm = readPro.getMisSpelledCharSearchKeyword();
	String topThreeSearch = readPro.getTopThreeSearchKeyword();

	@BeforeMethod
	public void beforeMethod() throws Exception {
		ExtentManager.initialisation("Bing Search Tests");
		// Launch URL
		Utility.launchApp(url);
	}

	@Test
	public void verifyBingSearchFirstLinkTest() throws Exception {
		bingSearchPage = new BingSearchPage();
		bingSearchPage.verifySearchTerm(searchTerm);
	}

	@Test
	public void verifyBingTopThreeTermSearchResultsTest() throws Exception {
		bingSearchPage = new BingSearchPage();
		bingSearchPage.verifyTopThreeSearchResults(topThreeSearch);
	}

	@Test
	public void verifyBingSearchWithSpecialCharTermTest() throws Exception {
		bingSearchPage = new BingSearchPage();
		bingSearchPage.verifySpecialCharSearchTerm(specialCharSearchTerm);
	}

	@Test
	public void verifyBingSearchWithAlphaNumericCharTermTest() throws Exception {
		bingSearchPage = new BingSearchPage();
		bingSearchPage.verifyAlphaNumericCharSearchTerm(alphaNumericCharSearchTerm);
	}

	@Test
	public void verifyBingSearchWithMisSpelledCorrectionTermTest() throws Exception {
		bingSearchPage = new BingSearchPage();
		bingSearchPage.verifyMisSpelledSearchTermSuggestion(misSpelledCharSearchTerm);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
		}
	}
}
