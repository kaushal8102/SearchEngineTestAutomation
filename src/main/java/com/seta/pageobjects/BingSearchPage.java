package com.seta.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.seta.actiondriver.Action;
import com.seta.base.BaseClass;
import com.seta.locators.Locators.bingSearchPage;
import com.seta.utility.Utility;

public class BingSearchPage extends BaseClass implements bingSearchPage {

	// Locators
	@FindBy(name = txtBingSearchBox)
	WebElement txtSearch;
	@FindBy(xpath = uiGoogleSearchResult)
	WebElement uiSearch;
	@FindBy(xpath = uiSpecialCharSearchResult)
	WebElement specialCharSearchResult;
	@FindBy(xpath = uiAlphaNumericCharSearchResult)
	WebElement alphaNumericCharSearchResult;
	@FindBy(xpath = uiMisSpelledSearchResult)
	WebElement misSpelledSearchResult;

	public BingSearchPage() {
		PageFactory.initElements(driver, this);
	}

	// Enter keywords and search.
	public void enterSearchTerm(String searchKeyword) {
		Action.implicitWait(driver, 20);

		Utility.ElementHighlight(driver, txtSearch);
		Action.sendText(txtSearch, searchKeyword);
		Action.pressENTERkey();
	}

	// Verify search in first result.
	public void verifySearchTerm(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		Action.scrollToElement(driver, uiSearch);
		String getTextFromUISearch = uiSearch.getText().toLowerCase();

		Action.logReport(getTextFromUISearch, searchKeyword);
		Assert.assertTrue(getTextFromUISearch.contains(searchKeyword),
				"The term '" + searchKeyword + "' is not displayed.");
	}

	// Verify bing search engine's top three results.
	public void verifyTopThreeSearchResults(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		// Verify top three results
		Action.verifyTopThreeSearchResults(bingSearchPage.uiTopThreeResults, searchKeyword);
	}

	// Verify bing search with special character ($).
	public void verifySpecialCharSearchTerm(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		Action.scrollToElement(driver, specialCharSearchResult);
		String getTextFromUISearch = specialCharSearchResult.getText().toLowerCase();

		Action.logReport(getTextFromUISearch, searchKeyword);
		Assert.assertTrue(getTextFromUISearch.replace("é", "e").contains(searchKeyword.replace("$", "")),
				"The term '" + searchKeyword + "' is not displayed.");
	}

	// Verify bing search with alphanumeric search term.
	public void verifyAlphaNumericCharSearchTerm(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		Action.scrollToElement(driver, alphaNumericCharSearchResult);
		String getTextFromUISearch = alphaNumericCharSearchResult.getText().toLowerCase();

		Action.logReport(getTextFromUISearch, searchKeyword);
		Assert.assertTrue(getTextFromUISearch.contains(searchKeyword),
				"The term '" + searchKeyword + "' is not displayed.");
	}

	// Verify bing search with misspelled search term.
	public void verifyMisSpelledSearchTermSuggestion(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		Action.scrollToElement(driver, misSpelledSearchResult);
		String getTextFromUISearch = misSpelledSearchResult.getText().toLowerCase();

		Action.logReport(getTextFromUISearch, searchKeyword);
		Assert.assertTrue(misSpelledSearchResult.isDisplayed(), "The term '" + searchKeyword + "' is not displayed.");
	}
}