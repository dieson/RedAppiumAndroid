package com.appium.Android_PageNew;

import java.util.Properties;

import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

public class GoToPage extends BaseScreen{
	private static final Properties GOTO_PAGE = new PropertyUtils().loadProperties("/Android_PageNew/GoToPage.properties");
	
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
	private final String option = GOTO_PAGE.getProperty("ANDROID_OPTION");
	private final String organise = GOTO_PAGE.getProperty("ANDROID_ORGANISE");
	private final String grouped = GOTO_PAGE.getProperty("ANDROID_GROUPED");
	private final String titleList = GOTO_PAGE.getProperty("ANDROID_TITLELIST");
	private final String publication = GOTO_PAGE.getProperty("ANDROID_PUBLICATION");
	private final String publicationList = GOTO_PAGE.getProperty("ANDROID_PUBLICATIONLIST");
	


	
	public GoToPage(RedAndroid screenUtils){
		screen = screenUtils;
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
	
	/**
	 *	调此方法需将PBO title调置publication default界面
	 * @param titleName
	 */
	public void gotoPage(String titleName){
		
		// Get the title number
		screen.click(option, "Setting");
		screen.click(organise, "Organise Publications");
		if (screen.isSelect(grouped, "Grouped publications")) {
				screen.click(grouped, "Grouped publications");
		}
		int i = 0;
		while (i < screen.findElements(titleList).size()) {
			String title = screen.getText(titleList + "[" + (i+1) + "]/android.widget.LinearLayout/android.widget.TextView", "Title Name");
			if (title.equals(titleName)) {
				i += 1;
				break;
			}
			i++;
		}
		screen.click(publication, "Organise Publications");
		// Open the PBO title
		screen.click(publicationList + "[" + i + "]", "Publication");
		screen.waitProgress();
		// Go to page
		screen.click(gotoButton, "GoTo Button");
		String number = Integer.toString((int)(1 + Math.random()*(100-1)));
		this.inputNumber(number);
		screen.click(result, "Page Result");
		String pageNOVerify = screen.getText(pageNO, "Page No");
		
		screen.click(backButton, "Back Button");

		Assert.assertEquals(pageNOVerify, number);
	}
	
	
	
}
