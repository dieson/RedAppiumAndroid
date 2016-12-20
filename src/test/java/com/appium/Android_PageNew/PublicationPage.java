package com.appium.Android_PageNew;

import java.util.Properties;

import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Dieson Zuo
 * @date Sep 5, 2016 5:52:09 PM
 */
public class PublicationPage extends BaseScreen {
	private static final Properties PUBLICATION_PAGE_PROPERTIES = new PropertyUtils()
			.loadProperties("/Android_PageNew/Publication.properties");

	private final String option = PUBLICATION_PAGE_PROPERTIES.getProperty("ANDROID_OPTIONS");
	private final String organise = PUBLICATION_PAGE_PROPERTIES.getProperty("ANDROID_ORGANISE");
	private final String grouped = PUBLICATION_PAGE_PROPERTIES.getProperty("ANDROID_GROUPED");
	private final String back = PUBLICATION_PAGE_PROPERTIES.getProperty("ANDROID_BACK");
	private final String group = PUBLICATION_PAGE_PROPERTIES.getProperty("ANDROID_ISGROUPED");

	public PublicationPage(RedAndroid screenUtils) {

		screen = screenUtils;
	}

	public void groupSingle() {

		// Step1. Check whether there are grouped.
		this.checkGroup();

		// Step2. Check another status of group.
		this.checkSingle();
	}

	public void checkGroup() {

		screen.click(option, "options");
		screen.click(organise, "organise publications");
		this.onGroup();
		boolean isSelected = screen.isSelect(grouped, "grouped publications");

		boolean isGroup = screen.isExistElement(group);
		screen.click(back, "back to publication");
		screen.wait(2);

		boolean isFolder = screen.findGroup();

		Assert.assertEquals(isFolder, isSelected);
		Assert.assertEquals(isGroup, isSelected);
	}

	public void checkSingle() {

		screen.click(option, "options");
		screen.click(organise, "organise publications");
		this.offGroup();
		boolean isSelected = screen.isSelect(grouped, "grouped publications");

		boolean isGroup = screen.isExistElement(group);
		screen.click(back, "back to publication");
		screen.wait(2);

		boolean isFolder = screen.findGroup();

		Assert.assertEquals(isFolder, isSelected);
		Assert.assertEquals(isGroup, isSelected);
	}

	public void onGroup() {

		if (!screen.isSelect(grouped, "grouped publications")) {
			screen.click(grouped, "grouped publications");
		}
	}

	public void offGroup() {

		if (screen.isSelect(grouped, "grouped publications")) {
			screen.click(grouped, "grouped publications");
		}
	}

}
