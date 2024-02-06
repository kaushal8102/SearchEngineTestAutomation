package com.seta.locators;

public interface Locators {

	// Locators for Google search page
	public interface googleSearchPage {
		String txtGoogleSearchBox = "*//div//textarea[@class='gLFyf']";
		String uiGoogleSearchResult = "div.ZbhV9d h2";
		String uiSpecialCharSearchResult = "div.VwiC3b span";
		String uiAlphaNumericCharSearchResult = "div.g span";
		String uiSuggestionResult = "//div[@id='OotqVd']";
		String uiMisSpelledSearchResult = "//*[@id='fprsl']";
		String uiTopThreeResults = "//*[@id='b_results']/li[@class='b_algo']/h2/a";
	}

	// Locators for Yahoo search page
	public interface yahooSearchPage {
		String txtYahooSearchBox = "p";
		String uiYahooSearchResult = "#web li.first div h3 a";
		String uiAlphaNumericCharSearchResult = "div.g span";
		String uiSuggestionResult = "//*[@class='first last']/div/div[1]/p";
		String uiMisSpelledSearchResult = "//*[@id='left']/div/ol[1]/li/div/div[1]/span/a";
		String uiTopThreeResults = "//*[@id='web']/ol/li/div/div[2]/p/span";
	}

	// Locators for Bing search page
	public interface bingSearchPage {
		String txtBingSearchBox = "q";
		String uiGoogleSearchResult = "//*[@class='b_algo'][1]/h2/a";
		String uiAlphaNumericCharSearchResult = "//*[@class='b_algo'][1]/div[1]/a";
		String uiMisSpelledSearchResult = "//*[@class='b_ans']/div[1]/a";
		String uiSpecialCharSearchResult = "//*[@class='b_algo'][1]/div/a/div[2]/div[1]";
		String uiTopThreeResults = "//*[@id='b_results']/li[@class='b_algo']/h2/a";
	}
}