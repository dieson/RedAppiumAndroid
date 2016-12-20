package com.appium.Android_PageNew;

import java.util.Properties;

import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Dieson Zuo
 * @date Nov 1, 2016 3:44:51 PM
 */
public class IndexPage extends BaseScreen {

	private static final Properties INDEX_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_pageNew/IndexPage.properties");

	private final String option = INDEX_PAGE_PROPERTIES.getProperty("ANDROID_OPTION");
	private final String organise = INDEX_PAGE_PROPERTIES.getProperty("ANDROID_ORGANISE");
	private final String grouped = INDEX_PAGE_PROPERTIES.getProperty("ANDROID_GROUPED");
	private final String titleList = INDEX_PAGE_PROPERTIES.getProperty("ANDROID_TITLELIST");
	private final String publication = INDEX_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATION");
	private final String publicationList = INDEX_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATIONLIST");
	private final String index = INDEX_PAGE_PROPERTIES.getProperty("ANDROID_INDEX");
	private final String indexList = INDEX_PAGE_PROPERTIES.getProperty("ANDROID_INDEXLIST");
	private final String backButton = INDEX_PAGE_PROPERTIES.getProperty("ANDROID_BACKBUTTON");

	public IndexPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	/**
	 * 选择包括index的title，调用此方法前将index title调整到当前publication界面可见位置
	 * @param titleName
	 */
	public void index(String titleName) {
		
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
		// Open the index page
		screen.click(publicationList + "[" + i + "]", "Publication");
		screen.waitProgress();
		screen.click(index, "Index Button");
		screen.waitProgress();
		boolean isIndex = false;
		isIndex = screen.isExistElement(indexList);
		screen.click(backButton, "Back button");
		
		Assert.assertEquals(isIndex, true);
	}

}
