package com.seta.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.seta.actiondriver.Action;
import com.seta.base.BaseClass;
import com.seta.locators.Locators.googleSearchPage;
import com.seta.utility.Utility;

public class GoogleSearchPage extends BaseClass implements googleSearchPage {

	// Locators
	@FindBy(xpath = txtGoogleSearchBox)
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

	// Verify google search engine's top three results.
	public void verifyTopThreeSearchResults(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		// Verify top three results
		Action.verifyTopThreeSearchResults(googleSearchPage.uiTopThreeResults, searchKeyword);
	}

	// Verify google search with bad search term.
	public void verifyBadSearchTerm(String badSearchKeyword) throws Exception {
		enterSearchTerm(badSearchKeyword);

		Action.scrollToElement(driver, uiBadSearch);
		Action.logResult(uiBadSearch.isDisplayed(), badSearchKeyword);
		Assert.assertTrue(uiBadSearch.isDisplayed(), "The term '" + badSearchKeyword + "' is not displayed.");
	}

	// Verify google search with special character ($).
	public void verifySpecialCharSearchTerm(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		Action.scrollToElement(driver, specialCharSearchResult);
		String getTextFromUISearch = specialCharSearchResult.getText().toLowerCase();

		Action.logReport(getTextFromUISearch, searchKeyword);
		Assert.assertTrue(getTextFromUISearch.replace("é", "e").contains(searchKeyword.replace("$", "")),
				"The term '" + searchKeyword + "' is not displayed.");
	}

	// Verify google search with alphanumeric search term.
	public void verifyAlphaNumericCharSearchTerm(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		Action.scrollToElement(driver, alphaNumericCharSearchResult);
		String getTextFromUISearch = alphaNumericCharSearchResult.getText().toLowerCase();

		Action.logReport(getTextFromUISearch, searchKeyword);
		Assert.assertTrue(getTextFromUISearch.contains(searchKeyword),
				"The term '" + searchKeyword + "' is not displayed.");
	}

	// Verify google search with misspelled search term.
	public void verifyMisSpelledSearchTermSuggestion(String searchKeyword) throws Exception {
		enterSearchTerm(searchKeyword);

		Action.scrollToElement(driver, misSpelledSearchResult);
		String getTextFromUISearch = misSpelledSearchResult.getText().toLowerCase();

		Action.logReport(getTextFromUISearch, searchKeyword);
		Assert.assertTrue(misSpelledSearchResult.isDisplayed(), "The term '" + searchKeyword + "' is not displayed.");
	}
}