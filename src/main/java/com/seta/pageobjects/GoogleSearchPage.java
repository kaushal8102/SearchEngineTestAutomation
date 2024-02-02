package com.seta.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.seta.actiondriver.Action;
import com.seta.base.BaseClass;
import com.seta.locators.Locators.googleSearch;
import com.seta.utility.ExtentManager;
import com.seta.utility.Utility;

public class GoogleSearchPage extends BaseClass implements googleSearch {

	// Locators
	@FindBy(xpath = txtGoogleSearch)
	WebElement txtSearch;
	@FindBy(css = uiGoogleSearchResult)
	WebElement uiSearch;
	@FindBy(xpath = uiSuggestionResult)
	WebElement uiBadSearch;
	@FindBy(css = uiSpecialCharSearchResult)
	WebElement specialCharSearchResult;
	@FindBy(css = uiAlphaNumericCharSearchResult)
	WebElement alphaNumericCharSearchResult;
	@FindBy(xpath = uiMisSpelledSearchResult)
	WebElement misSpelledSearchResult;

	public GoogleSearchPage() {
		PageFactory.initElements(driver, this);
	}

	public void enterSearchTerm(String searchKeyword) {

		Utility.ElementHighlight(driver, txtSearch);
		Action.sendText(txtSearch, searchKeyword);
	}

	public void verifySearchTerm(String searchKeyword) throws Exception {
		Action.implicitWait(driver, 20);

		enterSearchTerm(searchKeyword);
		Action.pressENTERkey();

		Utility.ElementHighlight(driver, uiSearch);
		String getTextFromUISearch = uiSearch.getText().toLowerCase();

		if (getTextFromUISearch != null) {
			ExtentManager.logStat(Status.PASS,
					"The term '" + searchKeyword + "' displayed in the first search result.");
		} else {
			ExtentManager.logStat(Status.FAIL, "The term '" + searchKeyword + "' is not displayed.");
		}

		Assert.assertTrue(getTextFromUISearch.contains(searchKeyword),
				"The term '" + searchKeyword + "' is not displayed.");
	}

	public void verifyBadSearchTerm(String badSearchKeyword) throws Exception {
		Action.implicitWait(driver, 20);

		enterSearchTerm(badSearchKeyword);
		Action.pressENTERkey();

		Utility.ElementHighlight(driver, uiBadSearch);

		if (uiBadSearch.isDisplayed() == true) {
			ExtentManager.logStat(Status.PASS, "The term '" + badSearchKeyword + "' displayed in the search result.");
		} else {
			ExtentManager.logStat(Status.FAIL, "The term '" + badSearchKeyword + "' is not displayed.");
		}

		Assert.assertTrue(uiBadSearch.isDisplayed(), "The term '" + badSearchKeyword + "' is not displayed.");
	}

	public void verifySpecialCharSearchTerm(String searchKeyword) throws Exception {
		Action.implicitWait(driver, 20);

		enterSearchTerm(searchKeyword);
		Action.pressENTERkey();

		Utility.ElementHighlight(driver, specialCharSearchResult);
		String getTextFromUISearch = specialCharSearchResult.getText().toLowerCase();

		if (getTextFromUISearch != null) {
			ExtentManager.logStat(Status.PASS,
					"The term '" + searchKeyword + "' displayed in the first search result.");
		} else {
			ExtentManager.logStat(Status.FAIL, "The term '" + searchKeyword + "' is not displayed.");
		}

		Assert.assertTrue(getTextFromUISearch.contains(searchKeyword),
				"The term '" + searchKeyword + "' is not displayed.");
	}

	public void verifyAlphaNumericCharSearchTerm(String searchKeyword) throws Exception {
		Action.implicitWait(driver, 20);

		enterSearchTerm(searchKeyword);
		Action.pressENTERkey();

		Utility.ElementHighlight(driver, alphaNumericCharSearchResult);
		String getTextFromUISearch = alphaNumericCharSearchResult.getText().toLowerCase();

		if (getTextFromUISearch != null) {
			ExtentManager.logStat(Status.PASS,
					"The term '" + searchKeyword + "' displayed in the first search result.");
		} else {
			ExtentManager.logStat(Status.FAIL, "The term '" + searchKeyword + "' is not displayed.");
		}

		Assert.assertTrue(getTextFromUISearch.contains(searchKeyword),
				"The term '" + searchKeyword + "' is not displayed.");
	}

	public void verifyMisSpelledSearchTermSuggestion(String searchKeyword) throws Exception {
		Action.implicitWait(driver, 20);

		enterSearchTerm(searchKeyword);
		Action.pressENTERkey();

		Utility.ElementHighlight(driver, misSpelledSearchResult);
		String getTextFromUISearch = misSpelledSearchResult.getText().toLowerCase();

		if (getTextFromUISearch != null) {
			ExtentManager.logStat(Status.PASS,
					"The term '" + searchKeyword + "' displayed in the first search result.");
		} else {
			ExtentManager.logStat(Status.FAIL, "The term '" + searchKeyword + "' is not displayed.");
		}

		Assert.assertTrue(misSpelledSearchResult.isDisplayed(), "The term '" + searchKeyword + "' is not displayed.");

		// Click on misspelled suggested word
		misSpelledSearchResult.click();

		// Verify after click on suggestion search key word change to correct word
		Assert.assertTrue(txtSearch.getText().equals(searchKeyword),
				"The term '" + searchKeyword + "' is not displayed.");
	}
}