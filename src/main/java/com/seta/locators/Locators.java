package com.seta.locators;

public interface Locators {

	public interface googleSearch {
		String txtGoogleSearch = "*//div//textarea[@class='gLFyf']";
		String uiGoogleSearchResult = "div.g h3";
		String uiSpecialCharSearchResult = "div.g em";
		String uiAlphaNumericCharSearchResult = "div.g span";
		String uiSuggestionResult = "//div[@id='OotqVd']";
		String uiMisSpelledSearchResult = "//*[@id='fprsl']";
	}
}