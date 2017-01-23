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

public class RecentHistoryPage extends BaseScreen{
	private static final Properties RECENTHISTORY_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_page/RecentHistoryPage.properties");
	
	private final String titlename = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_TITLE_NAME");
	private final String toclist = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_TOCLIST");
	private final String title = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_TITLE");
	private final String backbutton = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_BACKBUTTON");
	private final String historytoc = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_TOCNAMEACTUAL");
	private final String historyicon = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_HISTORYICON");
	private final String titlepop = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_TITLEPOP");
	private final String tocnamepop = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_TOCNAMEPOP");
	private final String recenthistorypop = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_RECENTHISTORYPOP");
	private final String publication = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATION");
	private final String recenthistoryList = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_RECENTHISTORYLIST");
	private final String firstHistory = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_FIRSTHISTORY");
	private final String tocName = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_TOCNAME");
	private final String less = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_LESS");
	private final String recenthistory = RECENTHISTORY_PAGE_PROPERTIES.getProperty("ANDROID_RECENTHISTORY");
			
	public RecentHistoryPage(RedAndroid screenUtils){
		screen = screenUtils;
	}
	
	public RecentHistoryPage(RedAndroid screenUtils, Map<String, Object> dataDriver, ExcelUtils excelUtils){
		screen = screenUtils;
		data = dataDriver;
		excel = excelUtils;
	}
	
	/**
	 * Verify the toc text
	 */
	public void recentHisotry(){
		String titleName = screen.getText(titlename, "Title Name");
		
		screen.click(publication, "Publication");
		screen.waitProgress();
		
		String tocNameI = "";
		String tocNameV = "1";
		for(int i = 2; !tocNameI.equals(tocNameV); i++){
			List<WebElement> tocListI = screen.findElements(toclist);
			int tocI = tocListI.size();
			int x = (int) (i + Math.random() * (tocI - i));
			tocNameI = screen.getText(screen.findElement(tocListI.get(x-1), tocName), "TOC Name");
			tocListI.get(x-1).click();
			
			List<WebElement> tocListV = screen.findElements(toclist);
			int tocV = tocListV.size();
			if(tocV == tocI) {
				tocNameV = screen.getText(screen.findElement(tocListV.get(x - 1), tocName), "TOC Name");
			}
		}
		
		screen.click(backbutton, "Back Button");
		if(!screen.isExistElement(recenthistoryList)){
			screen.scrollTo("Recent History", "Recent History");
		}
		String historyName = screen.getText(title, "Recent History");
		String historyToc = screen.getText(historytoc, "historyTOC");
		
		writeExcel("TocName", tocNameV);
		writeExcel("TitleName", titleName);
		writeExcel("HistoryName", historyName);
		writeExcel("HistoryToc", historyToc);
		
		screen.click(firstHistory, "First History");
		screen.waitProgress();
		screen.click(historyicon, "History Icon");
		
		String titlePop = screen.getText(titlepop, "HistoryTOC Pop");
		String tocnamepPop = screen.getText(tocnamepop, "TOC Name Pop");
		writeExcel("TitleNamePop", titlePop);
		writeExcel("TOCNamePop", tocnamepPop);
		
		screen.click(recenthistorypop, "Recent History Pop");
		screen.click(backbutton, "Back Button");

		Assert.assertEquals(historyToc,	data.get("TocName").toString(), "TOCName");
		Assert.assertEquals(historyName, data.get("TitleName").toString(), "TitleName");
	}
	
	public void maxHistoryNO(){
		screen.click(publication, "Publication");
		screen.waitProgress();
		
		for(int m = 0; m < 11; m++){
			String tocNameI = "";
			String tocNameV = "1";
			for(int i = 2; !tocNameI.equals(tocNameV); i++){
				List<WebElement> tocListI = screen.findElements(toclist);
				int tocI = tocListI.size();
				int x = (int) (i + Math.random() * (tocI - i));
				tocNameI = screen.getText(screen.findElement(tocListI.get(x-1), tocName), "TOC Name");
				tocListI.get(x-1).click();

				List<WebElement> tocListV = screen.findElements(toclist);
				int tocV = tocListV.size();
				if(tocV == tocI) {
					tocNameV = screen.getText(screen.findElement(tocListV.get(x - 1), tocName), "TOC Name");
				}
			}
			screen.click(less, "Shrink the TOC list");
		}
		
		screen.click(backbutton, "Back Button");
		screen.scrollTo("Recent History", "Recent History");
		List<WebElement> historyList = screen.findElements(recenthistory);
		screen.scrollTo("All", "Publications");
		int historyNO = historyList.size();
		Assert.assertEquals((historyNO < 10 || historyNO == 10), true);
	}
	
}
