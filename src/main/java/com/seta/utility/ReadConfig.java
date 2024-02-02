package com.seta.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties configProp;

	public ReadConfig() {
		File src = new File("./Configuration/Config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			configProp = new Properties();
			configProp.load(fis);

		} catch (Exception e) {
			System.out.println("Exception is" + e.getMessage());
		}
	}

	// Get URL
	public String getURL() {
		String url = configProp.getProperty("URL");
		return url;
	}

	// Get URL
	public String getYahooURL() {
		String url = configProp.getProperty("YahooURL");
		return url;
	}

	// Get Extent Report Path
	public String getExtentReportPath() {
		String extentReportPath = configProp.getProperty("EXTENT_REPORT_PATH");
		return extentReportPath;
	}

	// Get Chrome Driver Path
	public String getChromeDriverPath() {
		String chromeDriverPath = configProp.getProperty("CHROME_DRIVER");
		return chromeDriverPath;
	}

	// Get Google Search Key word
	public String getSearchKeyword() {
		String searchKeyword = configProp.getProperty("KEYWORD");
		return searchKeyword;
	}

	// Get Google Search With Bad Key word
	public String getBadSearchKeyword() {
		String badSearchKeyword = configProp.getProperty("BadKeyword");
		return badSearchKeyword;
	}

	// Get Google Search With Special Char Key word
	public String getSpecialCharSearchKeyword() {
		String specialCharSearchKeyword = configProp.getProperty("SpecialCharSearchKeyword");
		return specialCharSearchKeyword;
	}

	// Get Google Search With AlphaNumeric Key word
	public String getAlphaNumericCharSearchKeyword() {
		String alphaNumericCharSearchKeyword = configProp.getProperty("AlphaNumericSearchKeyword");
		return alphaNumericCharSearchKeyword;
	}

	// Get Google Search With AlphaNumeric Key word
	public String getMisSpelledCharSearchKeyword() {
		String misSpelledCharSearchKeyword = configProp.getProperty("MisSpelledSearchKeyword");
		return misSpelledCharSearchKeyword;
	}
}
