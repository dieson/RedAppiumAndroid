package com.appium.Android_page;

import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

public class IndexPage extends BaseScreen{
	private static final Properties INDEX_PAGE = new PropertyUtils().loadProperties("/Android_page/IndexPage.properties"); 
	
	private final String indexButton = INDEX_PAGE.getProperty("ANDROID_INDEX");
	private final String noIndexList = INDEX_PAGE.getProperty("ANDROID_NOINDEXLIST");
	private final String noIndexContent = INDEX_PAGE.getProperty("ANDROID_NOCONTENT");
	private final String title = INDEX_PAGE.getProperty("ANDROID_TITLE");
	private final String indexList = INDEX_PAGE.getProperty("ANDROID_INDEXLIST");
	private final String indexContent = INDEX_PAGE.getProperty("ANDROID_CONTENT");
	private final String backButton = INDEX_PAGE.getProperty("ANDROID_BACKBUTTON");
	
	public IndexPage(RedAndroid screenUtils){
		screen = screenUtils;
	}
	
	public IndexPage(RedAndroid screenUtils, Map<String, Object> dataUtils, ExcelUtils excelUtils){
		screen = screenUtils;
		data = dataUtils;
		excel = excelUtils;
	}
	
	public void index(){
		screen.click(title, "Title");
		screen.waitProgress();
		screen.click(indexButton, "Index");
		screen.waitProgress();
		
		String listMsg = "";
		String contentMsg = "";
		if(screen.isExistElement(noIndexList)){
			listMsg = screen.getText(noIndexList, "Index List");
			contentMsg = screen.getText(noIndexContent, "Index Content");
		}else{
			listMsg = screen.getText(indexList, "Index List");
			contentMsg = screen.getText(indexContent, "Index Content");
		}
		
		if(listMsg.equals("There are no index files for this publication") && contentMsg.equals("No index files available.")){
			report.log("[Successfull] Index display is correct");
		}else if(contentMsg.equals("A")){
			report.log("[Successfull] Index display is correct");
		}else {
			report.log("[Fail] Index display is not correct");
			Assert.fail();
		}
		
		screen.click(backButton, "Back Button");
	}
	
	
}
