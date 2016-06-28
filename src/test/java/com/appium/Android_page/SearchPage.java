package com.appium.Android_page;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

public class SearchPage extends BaseScreen{
	private static final Properties SEARCH_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_page/SearchPage.properties");
	
	private final String searchButton = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_SEARCHBUTTON");
	private final String searchText = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_SEARCHTEXT");
	private final String searchResult = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_SEARCHRESULT");
	private final String searchTOC = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_SEARCHTOC");
	private final String backButton = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_BACKBUTTON");
	private final String guidcardVerify = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_GUIDVERIFY");
	private final String resultPublication = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_RESULTPUBLICATION");
	private final String historyName = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_HISTORYNAME");
	private final String historytoc = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_TOCNAMEACTUAL");
	private final String publication = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATION");
	private final String recenthistoryList = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_RECENTHISTORYLIST");
	private final String firstHistory = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_FIRSTHISTORY");
	private final String toc = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_TOCLIST");
	private final String tocBackGround = SEARCH_PAGE_PROPERTIES.getProperty("ANDROID_TOCBACKGROUND");
	
	public SearchPage(RedAndroid screen){
		this.screen = screen;
	}
	
	public SearchPage(RedAndroid screen, Map<String, Object> data, ExcelUtils excel){
		this.screen = screen;
		this.data = data;
		this.excel = excel;
	}
	
	public void searchTest(){
		screen.click(publication, "Publication");
		screen.waitProgress();
		screen.click(searchButton, "Search Button");
		screen.input(searchText, data.get("search_text"), "Search Text");
		screen.sendKey(searchText, 66, "Search Text");
		String publicationIcon = screen.getText(resultPublication, "Result Publication");
		writeExcel("result_publication", publicationIcon);
		
		String resultGuideCarde = screen.getText(guidcardVerify, "Result Guide Card");
		writeExcel("result_guide_card", resultGuideCarde);
		
		String resultTOC = screen.getText(searchTOC, "Result TOC");
		writeExcel("result_TOC", resultTOC);
		
		screen.click(searchResult, "Search Result");
		screen.waitProgress();
		
		String openTOC = "";
		List<WebElement> tocList = screen.findElements(toc);
		for(int i = 0; i < tocList.size(); i++){
			WebElement element = screen.findElement(tocList.get(i), tocBackGround);
			if(element.getAttribute("selected").equals("true")){
				openTOC = screen.getText(screen.findElement(element, searchTOC), "TOC Text");
			}
		}
		writeExcel("open_TOC", openTOC);
		
		screen.click(backButton, "Back Button");
		
		if(!screen.isExistElement(recenthistoryList)){
			screen.scrollTo("Recent History", "Recent History");
		}
		
		String Name = screen.getText(historyName, "Resent History");
		writeExcel("history_name", Name);
		
		String historyToc = screen.getText(historytoc, "historyToc");
		writeExcel("history_TOC", historyToc);
		
		screen.click(firstHistory, "First History");
		screen.waitProgress();
		screen.click(backButton, "Back Button");

		if (publicationIcon.equals("Publication")) {
			Assert.assertEquals(historyToc, data
					.get("open_TOC").toString());
			Assert.assertEquals(resultTOC, data
					.get("open_TOC").toString());
		} else {
			Assert.assertEquals(historyToc, data
					.get("open_TOC").toString());
		}
	}
	
}
