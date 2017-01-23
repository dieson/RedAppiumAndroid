package com.appium.Android_page;

import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

public class GoToPage extends BaseScreen{
	private static final Properties GOTO_PAGE = new PropertyUtils().loadProperties("/Android_page/GoToPage.properties");
	
	private final String gotoButton = GOTO_PAGE.getProperty("ANDROID_GOTOBUTTON");
	private final String one = GOTO_PAGE.getProperty("ANDROID_1");
	private final String tow = GOTO_PAGE.getProperty("ANDROID_2");
	private final String three = GOTO_PAGE.getProperty("ANDROID_3");
	private final String four = GOTO_PAGE.getProperty("ANDROID_4");
	private final String five = GOTO_PAGE.getProperty("ANDROID_5");
	private final String six = GOTO_PAGE.getProperty("ANDROID_6");
	private final String seven = GOTO_PAGE.getProperty("ANDROID_7");
	private final String eight = GOTO_PAGE.getProperty("ANDROID_8");
	private final String nine = GOTO_PAGE.getProperty("ANDROID_9");
	private final String zero = GOTO_PAGE.getProperty("ANDROID_0");
	private final String backButton = GOTO_PAGE.getProperty("ANDROID_BACKBUTTON");
	//private final String deleteButton = GOTO_PAGE.getProperty("ANDROID_DELETE");
	//private final String doneButton = GOTO_PAGE.getProperty("ANDROID_DONE");
	private final String result = GOTO_PAGE.getProperty("ANDROID_RESULT");
	private final String pageNO = GOTO_PAGE.getProperty("ANDROID_PAGENO");
	
	public GoToPage(RedAndroid screenUtils){
		screen = screenUtils;
	}
	
	public GoToPage(RedAndroid screenUtils, Map<String, Object> dataUtils, ExcelUtils excelUtils){
		screen = screenUtils;
		data = dataUtils;
		excel = excelUtils;
	}
	
	/**
	 *	Input the page number
	 * @param number
	 */
	public void inputNumber(String number){
		char[] strChar = number.toCharArray();
		
		for(char NO: strChar){
			switch (NO) {
			case '1':
				screen.click(one, "NO 1");
				break;
			case '2':
				screen.click(tow, "NO 2");
				break;
			case '3':
				screen.click(three, "NO 3");
				break;
			case '4':
				screen.click(four, "NO 4");
				break;
			case '5':
				screen.click(five, "NO 5");
				break;
			case '6':
				screen.click(six, "NO 6");
				break;
			case '7':
				screen.click(seven, "NO 7");
				break;
			case '8':
				screen.click(eight, "NO 8");
				break;
			case '9':
				screen.click(nine, "NO 9");
				break;
			case '0':
				screen.click(zero, "NO 0");
				break;
			}
		}
	}
	
	public void gotoPage(String bookName){
		String locator = "NAME:" + bookName;
		
		screen.findPublication(bookName);
		screen.click(locator, bookName);
		
		screen.waitProgress();
		screen.click(gotoButton, "GoTo Button");
		inputNumber(data.get("PageNO").toString());
		screen.click(result, "Page Result");
		String pageNOVerify = screen.getText(pageNO, "Page No");
		writeExcel("PageNOVerify", pageNOVerify);
		screen.click(backButton, "Back Button");

		Assert.assertEquals(pageNOVerify, data.get("PageNOVerify"));
	}
	
	
	
}
