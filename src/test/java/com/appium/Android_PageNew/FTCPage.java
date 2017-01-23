package com.appium.Android_PageNew;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Dieson Zuo
 * @date Oct 31, 2016 6:00:42 PM
 */
public class FTCPage extends BaseScreen {

	private static final Properties FTC_PAGE_PROPERTIES = new PropertyUtils()
			.loadProperties("/Android_pageNew/FTCPage.properties");

	private final String option = FTC_PAGE_PROPERTIES.getProperty("ANDROID_OPTION");
	private final String organise = FTC_PAGE_PROPERTIES.getProperty("ANDROID_ORGANISE");
	private final String grouped = FTC_PAGE_PROPERTIES.getProperty("ANDROID_GROUPED");
	private final String list = FTC_PAGE_PROPERTIES.getProperty("ANDROID_LIST");
	private final String titleName = FTC_PAGE_PROPERTIES.getProperty("ANDROID_TITLENAME");
	private final String publication = FTC_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATION");
	private final String publicationList = FTC_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATIONLIST");
	private final String caseIcon = FTC_PAGE_PROPERTIES.getProperty("ANDROID_CASEICON");
	private final String tocList = FTC_PAGE_PROPERTIES.getProperty("ANDROID_TOCLIST");
	private final String backButton = FTC_PAGE_PROPERTIES.getProperty("ANDROID_BACKBUTTON");

	public FTCPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	/**
	 * 调此方法需将+case title调置publication default界面
	 */
	public void ftc() {
		
		// Get the +case title number
		screen.click(option, "Setting");
		screen.click(organise, "Organise Publications");
		if (screen.isSelect(grouped, "Grouped publications")) {
			screen.click(grouped, "Grouped publications");
		}
		List<WebElement> titleNameList = screen.findElements(screen.findElement(list), titleName);
		int i = 0;
		while (!titleNameList.get(i).getText().contains("+Cases")) {
			i++;
		}
		i = i + 1;
		screen.click(publication, "Organise Publications");
		// Get the +case icon
		boolean isIcon = screen.isExistElement(screen.findElement(publicationList + "[" + i + "]"), caseIcon);
		screen.click(publicationList + "[" + i + "]", "+ Case title");
		screen.waitProgress();
		boolean isTOC = false;
		// Get the +case TOC
		for (int j = 1; j < screen.findElements(tocList).size(); j++) {
			WebElement toc = screen
					.findElement("XPATH://android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + (j + 1)
							+ "]/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.TextView");
			if (screen.getText(toc, "TOC Name").equals("+ Cases")) {
				isTOC = true;
			}
		}
		screen.click(backButton, "Back button");
		screen.wait(2);
		Assert.assertEquals(isIcon, true);
		Assert.assertEquals(isTOC, true);
	}

}
