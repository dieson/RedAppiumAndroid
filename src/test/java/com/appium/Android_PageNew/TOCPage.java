package com.appium.Android_PageNew;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

public class TOCPage extends BaseScreen {
	private static final Properties TOC_PAGE_PROPERTIES = new PropertyUtils()
			.loadProperties("/Android_PageNew/TOCPage.properties");

	private final String toclist = TOC_PAGE_PROPERTIES.getProperty("ANDROID_TOCLIST");
	private final String backbutton = TOC_PAGE_PROPERTIES.getProperty("ANDROID_BACKBUTTON");
	private final String hideButton = TOC_PAGE_PROPERTIES.getProperty("ANDROID_HIDEBUTTON");
	private final String publication = TOC_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATION");
	private final String tocName = TOC_PAGE_PROPERTIES.getProperty("ANDROID_TOCNAME");
	private final String tocTitle = TOC_PAGE_PROPERTIES.getProperty("ANDROID_TOCTITLE");
	private final String previous = TOC_PAGE_PROPERTIES.getProperty("ANDROID_PREVIOUS");
	private final String next = TOC_PAGE_PROPERTIES.getProperty("ANDROID_NEXT");

	public TOCPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	public void hideTOC() {
		
		screen.click(publication, "Publication");
		screen.waitProgress();
		
		//Get the TOC status
		boolean appear = screen.isExistElement(toclist);
		// Full screen
		screen.click(hideButton, "Hide Button");
		boolean hide = screen.isExistElement(toclist);
		screen.click(backbutton, "back to publication");
		
		Assert.assertEquals(appear, true);
		Assert.assertEquals(hide, false);
	}

	public void previousNext() {

		// Step1. Open a book.
		screen.click(publication, "Publication");
		screen.waitProgress();

		// Step2. Generate two TOC titles.
		String firstTOC = screen.getText(tocTitle, "TOC title");

		String tocNameI = "";
		String tocNameV = "1";
		for (int i = 2; !tocNameI.equals(tocNameV); i++) {
			List<WebElement> tocListI = screen.findElements(toclist);
			int tocI = tocListI.size();
			int x = (int) (i + Math.random() * (tocI - i));
			tocNameI = screen.getText(screen.findElement(tocListI.get(x - 1), tocName), "TOC Name");
			tocListI.get(x - 1).click();

			List<WebElement> tocListV = screen.findElements(toclist);
			int tocV = tocListV.size();
			if (tocV == tocI) {
				tocNameV = screen.getText(screen.findElement(tocListV.get(x - 1), tocName), "TOC Name");
			}
		}
		String secoundTOC = screen.getText(tocTitle, "TOC title");

		// Step3. Forward and backward.
		screen.click(previous, "previous");
		String checkFirstTOC = screen.getText(tocTitle, "TOC title");
		screen.click(next, "next");
		String checkSecoundTOC = screen.getText(tocTitle, "TOC title");
		screen.click(backbutton, "back to publication");

		Assert.assertEquals(checkFirstTOC, firstTOC);
		Assert.assertEquals(checkSecoundTOC, secoundTOC);
	}

}
