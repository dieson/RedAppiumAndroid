package com.appium.Android_PageNew;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Nikitta Shen
 * @date Sep 12, 2016
 */
public class DocumentNamePage extends BaseScreen {

	private static final Properties DOCUMENTNAME_PAGE_PROPERTIES = new PropertyUtils()
			.loadProperties("/Android_pageNew/DocumentName.properties");

	private final String toclist = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_TOCLIST");
	private final String bookCover = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_BOOKCOVER");
	private final String tocText = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_TOCNAME");
	private final String tocTitle = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_TOCTITLE");
	private final String index = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_INDEX");
	private final String back = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_BACK");

	public DocumentNamePage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	/* This method to compare the list when select a toc node randomly */
	public boolean compareList(String[] list1, String[] list2) {
		boolean equal = false;
		if (list1.length != list2.length) {
			return equal;
		}
		for (int i = 0; i < list1.length; i++) {
			if (!list1[i].equalsIgnoreCase(list2[i])) {
				return equal;
			}
		}
		equal = true;
		return equal;

	}

	public void documentNameTest() {

		/* Open the first book */
		screen.click(bookCover, "Book Cover of the first book");
		screen.waitProgress();
		
		String tocNameI = "";
		String tocNameV = "1";
		for (int i = 2; !tocNameI.equals(tocNameV); i++) {
			List<WebElement> tocListI = screen.findElements(toclist);
			int tocI = tocListI.size();
			int x = (int) (i + Math.random() * (tocI - i));
			tocNameI = screen.getText(screen.findElement(tocListI.get(x - 1), tocText), "TOC Name");
			tocListI.get(x - 1).click();

			List<WebElement> tocListV = screen.findElements(toclist);
			int tocV = tocListV.size();
			if (tocV == tocI) {
				tocNameV = screen.getText(screen.findElement(tocListV.get(x - 1), tocText), "TOC Name");
			}
		}
		String tocTitleName = screen.getText(tocTitle, "TOC title");
		
		/* Compare the name in TOC and up the content */
		Assert.assertEquals(tocTitleName, tocNameV);
		screen.click(back, "Back to publications");
	}

}
