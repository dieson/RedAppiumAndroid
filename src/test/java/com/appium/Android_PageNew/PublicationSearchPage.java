package com.appium.Android_PageNew;

import java.util.Map;
import java.util.Properties;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

public class PublicationSearchPage extends BaseScreen {

	private static final Properties PUBLICATION_SEARCH_PAGE_PROPERTIES = new PropertyUtils()
			.loadProperties("/Android_pageNew/PublicationsSearchPage.properties");

	private final String publication = PUBLICATION_SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATION");
	private final String searchButton = PUBLICATION_SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_SEARCHBUTTON");
	private final String searchText = PUBLICATION_SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_SEARCHTEXT");
	private final String openbook = PUBLICATION_SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_OPENBOOK");

	private final String searchResult = PUBLICATION_SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_SEARCHRESULT");
	private final String searchTOC = PUBLICATION_SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_SEARCHTOC");
	private final String backButton = PUBLICATION_SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_BACKBUTTON");
	private final String searchoneResult = PUBLICATION_SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_SEARCHONERESULT");
	private final String Count = PUBLICATION_SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_COUNT");

	public PublicationSearchPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	public PublicationSearchPage(RedAndroid screenUtils, Map<String, Object> dataUtils, ExcelUtils excelUtils) {
		screen = screenUtils;
		data = dataUtils;
		excel = excelUtils;
	}

	public void search() {

		// Step1. Open a book.
		screen.click(publication, "Publication");
		screen.waitProgress();

		// search for "test" field
		screen.click(searchButton, "search");
		screen.input(searchText, data.get("search_text"), "Search Text");
		// screen.input(searchText, "Test", "Search Text");

		// Enter to confirm search
		screen.sendKey(searchText, 66, "Search Text");
		screen.waitProgress();

		// check the search result
		screen.click(searchResult, "Search Result");
		screen.waitProgress();

		screen.click(searchoneResult, "Search One Result");
		screen.waitProgress();

		screen.click(backButton, "Back Button");
		screen.waitProgress();
	}

}
