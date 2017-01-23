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

public class TOCPage extends BaseScreen{
	private static final Properties TOC_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_page/TOCPage.properties");
	
	private final String toclist = TOC_PAGE_PROPERTIES.getProperty("ANDROID_TOCLIST");
	private final String backbutton = TOC_PAGE_PROPERTIES.getProperty("ANDROID_BACKBUTTON");
	private final String hideButton = TOC_PAGE_PROPERTIES.getProperty("ANDROID_HIDEBUTTON");
	private final String publication = TOC_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATION");
	private final String tocName = TOC_PAGE_PROPERTIES.getProperty("ANDROID_TOCNAME");
	
	public TOCPage(RedAndroid screenUtils){
		screen = screenUtils;
	}
	
	public TOCPage(RedAndroid screenUtils, Map<String, Object> dataUtils, ExcelUtils excelUtils){
		screen = screenUtils;
		data = dataUtils;
		excel = excelUtils;
	} 
	
	public Map TOCTest(){
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
		writeExcel("TOC", tocNameI);
		writeExcel("TOCVerify", tocNameV);
		
		screen.click(backbutton, "Back Button");

		return data;
	}
	
	/** 
	 * Check the TOC hide function
	 */
	public void TOCHide(){
		screen.waitProgress();
		screen.click(publication, "Publication");
		
		screen.waitProgress();
		screen.click(hideButton, "Hide Button");
		if(screen.isExistElement(toclist)){
			report.log("[Fail] Unable to hide");
			Assert.fail();
		}else{
			report.log("[Successful] Hide the TOC list");
		}
		
		screen.click(hideButton, "Hide Button");
		if(screen.isExistElement(toclist)){
			report.log("[Successful] Show the TOC list");
		}else{
			report.log("[Fail] Unable to show");
			Assert.fail();
		}
		screen.click(backbutton, "Back Button");
	}
	
}
