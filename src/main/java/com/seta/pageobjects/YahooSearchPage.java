package com.seta.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.seta.actiondriver.Action;
import com.seta.base.BaseClass;
import com.seta.locators.Locators.yahooSearchPage;
import com.seta.utility.Utility;

public class YahooSearchPage extends BaseClass implements yahooSearchPage {

	// Locators
	@FindBy(name = txtYahooSearchBox)
	WebElement txtSearch;
	@FindBy(css = uiYahooSearchResult)
	WebElement uiSearch;
	@FindBy(xpath = uiSuggestionResult)
	WebElement uiBadSearch;
	@FindBy(css = uiAlphaNumericCharSearchResult)
	WebElement alphaNumericCharSearchResult;
	@FindBy(xpath = uiMisSpelledSearchResult)
	WebElement misSpelledSearchResult;

	public YahooSearchPage() {
		PageFactory.initElements(driver, this);
	}

	// Enter keywords and search.
	public void enterSearchTerm(String searchKeyword) throws InterruptedException {
		Action.implicitWait(driver, 20);

		Action.scrollToElement(driver, txtSearch);
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

	// Verify yahoo search engine's top three results.
	public void verifyTopThreeSearchResults(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		// Verify top three results
		Action.verifyTopThreeSearchResults(yahooSearchPage.uiTopThreeResults, searchKeyword);
	}

	// Verify yahoo search with bad search term.
	public void verifyBadSearchTerm(String badSearchKeyword) throws Exception {
		enterSearchTerm(badSearchKeyword);

		Action.scrollToElement(driver, uiBadSearch);
		Action.logResult(uiBadSearch.isDisplayed(), badSearchKeyword);
		Assert.assertTrue(uiBadSearch.isDisplayed(), "The term '" + badSearchKeyword + "' is not displayed.");
	}

	// Verify yahoo search with special character ($).
	public void verifySpecialCharSearchTerm(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		Action.scrollToElement(driver, uiSearch);
		String getTextFromUISearch = uiSearch.getText().toLowerCase();

		Action.logReport(getTextFromUISearch, searchKeyword);
		Assert.assertTrue(getTextFromUISearch.replace("é", "e").contains(searchKeyword.replace("$", "")),
				"The term '" + searchKeyword + "' is not displayed.");
	}

	// Verify yahoo search with alphanumeric search term.
	public void verifyAlphaNumericCharSearchTerm(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		Action.scrollToElement(driver, uiSearch);
		String getTextFromUISearch = uiSearch.getText().toLowerCase();

		Action.logReport(getTextFromUISearch, searchKeyword);
		Assert.assertTrue(getTextFromUISearch.contains(searchKeyword),
				"The term '" + searchKeyword + "' is not displayed.");
	}

	// Verify yahoo search with misspelled search term.
	public void verifyMisSpelledSearchTermSuggestion(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		Action.scrollToElement(driver, misSpelledSearchResult);
		String getTextFromUISearch = misSpelledSearchResult.getText().toLowerCase();

		Action.logReport(getTextFromUISearch, searchKeyword);
		Assert.assertTrue(misSpelledSearchResult.isDisplayed(), "The term '" + searchKeyword + "' is not displayed.");
	}
}