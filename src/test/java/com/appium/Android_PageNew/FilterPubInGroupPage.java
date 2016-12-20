package com.appium.Android_PageNew;

import java.util.Properties;
import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Nikitta Shen
 * @date 11st Oct, 2016
 */
public class FilterPubInGroupPage extends BaseScreen {
	
	private static final Properties FILTERPUBINGROUP_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_pageNew/FilterPubInGroup.properties");
	
	
	public final String filterButton = FILTERPUBINGROUP_PAGE_PROPERTIES.getProperty("ANDROID_FILTERLIST");
	public final String all = FILTERPUBINGROUP_PAGE_PROPERTIES.getProperty("ANDROID_ALL");
	public final String subscription = FILTERPUBINGROUP_PAGE_PROPERTIES.getProperty("ANDROID_SUBSCRIPTION");
	public final String loan = FILTERPUBINGROUP_PAGE_PROPERTIES.getProperty("ANDROID_LOAN");
	public final String groupFolder = FILTERPUBINGROUP_PAGE_PROPERTIES.getProperty("ANDROID_GROUPFOLDER");
	public final String numFolder = FILTERPUBINGROUP_PAGE_PROPERTIES.getProperty("ANDROID_NUMFOLDER");

	
	
	public FilterPubInGroupPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	
	public void filterPubInGroupTest(){
		
		/* Check the title numbers of the group */
		screen.wait(3);
		String numGroup = screen.findElement(numFolder).getText();
		int numTotal = Integer.parseInt(numGroup);
		report.log(numGroup);
		screen.click(groupFolder, "Open the group");
	    screen.tap(200, 200);
	    screen.click(filterButton, "Open filter");
	    screen.click(loan, "filter to Loan");
		String numLoan = screen.findElement(numFolder).getText();
		int numL = Integer.parseInt(numLoan);
		screen.click(groupFolder, "Open the group");
	    screen.tap(200, 200);
	    screen.click(filterButton, "Open filter");
	    screen.click(subscription, "filter to Subscriptions");
		String numSub = screen.findElement(numFolder).getText();
		int numS = Integer.parseInt(numSub);
		screen.click(groupFolder, "Open the group");
	    screen.tap(200, 200);
	    report.log(numLoan);
	    report.log(numSub);
	    Assert.assertEquals(numTotal, numL + numS);
	    screen.click(filterButton, "Open filter");
	    screen.click(all, "filter to all");


		
	}
}

