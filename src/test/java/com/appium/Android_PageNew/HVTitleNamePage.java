package com.appium.Android_PageNew;

import java.util.Properties;
import java.util.List;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Nikitta Shen
 * @date Sep 14, 2016
 */
public class HVTitleNamePage extends BaseScreen {

	private static final Properties HVTITLENAME_PAGE_PROPERTIES = new PropertyUtils()
			.loadProperties("/Android_pageNew/HVTitleName.properties");

	public final String groupItem = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_GROUPITEM");
	public final String groupFolder = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_GROUPFOLDER");
	public final String overLayer = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_OVERLAYER");
	public final String downButton = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_DOWNBUTTON");
	public final String downStatus = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_DOWNLOADSTATUS");
	public final String legislationIcon = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_LEGISLATIONICON");
	public final String infoButton = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_INFOBUTTON");
	public final String infoTitle = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_INFOTITLE");
	public final String hvSummary = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_INFOHVSUMMARY");
	public final String infoOpen = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_INFOTOPEN");
	public final String nameOnContent = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_TITLENAMEONCONTENT");
	public final String index = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_INDEX");
	public final String annotations = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_ANNOTATIONS");
	public final String back = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_BACK");
	public final String pub = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATIONS");
	public final String nameOnORG = HVTITLENAME_PAGE_PROPERTIES.getProperty("ANDROID_TITLEONANNOTATIONORG");

	public HVTitleNamePage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	public void hvTitleNameTest() {

		/* Check there is a group, and open the group */
		screen.wait(3);
		// screen.click(groupItem, "Open the group");
		screen.click(groupFolder, "Open the group");
		/* Download the first HV title */
		WebElement overLay = screen.findElement(overLayer);
		List<WebElement> downLoad = screen.findElements(overLay, downButton);
		List<WebElement> downStatu = screen.findElements(overLay, downStatus);
		List<WebElement> infoB = screen.findElements(overLay, infoButton);
		downLoad.get(2).click();
		String downloadStatu = downStatu.get(2).getText();

		do {
			report.log(screen.driver.getOrientation().toString());
			screen.driver.rotate(ScreenOrientation.PORTRAIT);
			screen.driver.rotate(ScreenOrientation.LANDSCAPE);
			screen.wait(1);
			downloadStatu = downStatu.get(2).getText();
			report.log(downloadStatu);
		} while (!downloadStatu.equals("Up to date"));

		/* Open info modal ,check the legislation icon and the title name */
		infoB.get(2).click();
		String infoTitleName = screen.findElement(infoTitle).getText();

		/* Check the HV Summary in the info modal */
		String summary = screen.findElement(hvSummary).getText();
		if (!summary.contains("historical versions of legislative instruments")) {
			Assert.fail();
		}

		/* Open the first HV book */
		screen.tap(100, 100);
		screen.click(legislationIcon, "Open the first HV book");
		screen.waitProgress();
		/* Title name on content */
		String titleOnContent = screen.getText(nameOnContent, "Title name on content.");
		// screen.click(index, "Go to Index page.");
		// String titleOnIndex = screen.getText(nameOnContent, "Title name on
		// Index.");
		screen.click(annotations, "Go to Annotation navigator");
		screen.click(back, "Back to publications");
		screen.click(pub, "Close the group");
		screen.click(annotations, "Go to Annotation Organiazer");
		String titleOnAnno = screen.getText(nameOnORG, "Title name on Annotation");
		Assert.assertEquals(infoTitleName, titleOnContent);
		Assert.assertEquals(titleOnContent, titleOnAnno);
		Assert.assertEquals(titleOnContent, "2010 Legislation: Ritchie’s Uniform Civil Procedure NSW");
		screen.click(pub, "Close the group");

	}
}
