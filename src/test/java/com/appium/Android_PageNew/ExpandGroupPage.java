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
public class ExpandGroupPage extends BaseScreen {
	
	private static final Properties EXPANDGROUP_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_pageNew/ExpandGroup.properties");
	
	
	public final String groupStatus = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_GROUPSTATUS");
	public final String downButton = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_DOWNLOADBUTTON");
	public final String groupName = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_GROUPTITLENAME");
	public final String setting = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_SETTINGS");
	public final String organise = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_ORGANISE");
	public final String groupLine = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_GROUPLINE");
	public final String groupNameOrg = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_GROUPNAME");
	public final String titleName1 = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_TITLENAME1");
	public final String titleName2 = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_TITLENAME2");
	public final String titleName3 = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_TITLENAME3");
	public final String titleName4 = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_TITLENAME4");
	public final String titleName5 = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_TITLENAME5");
	public final String back = EXPANDGROUP_PAGE_PROPERTIES.getProperty("ANDROID_BACK");
	
	
	public ExpandGroupPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	
	public void expandGroupTest(){
		
		/* Check the status of the group and open group by down button */
		screen.wait(3);
		String status = screen.findElement(groupStatus).getText();
		if (status.contains("Downloads available") || status.contains("Download available")) {
			screen.click(downButton, "Open the group by down button");
		}
		String gNameInGroup = screen.findElement(groupName).getText();
		
		/*Close the group and go to organise get the group title name*/
		screen.tap(100, 100);
		screen.click(setting, "Open settings");
		screen.click(organise, "Open organise");
		screen.click(groupLine, "Expand group in organise");
		String gNameInOrg = screen.findElement(groupNameOrg).getText();
		String title1 = screen.findElement(titleName1).getText();
		String title2 = screen.findElement(titleName2).getText();
		String title3 = screen.findElement(titleName3).getText();
		String title4 = screen.findElement(titleName4).getText();
		String title5 = screen.findElement(titleName5).getText();
		report.log(gNameInOrg);
		report.log(title1);
		report.log(title2);
		report.log(title3);
		report.log(title4);
		report.log(title5);
       // Assert.assertEquals(gNameInGroup, "Ritchie’s Uniform Civil Procedure NSW: 5 titles");
       // Assert.assertEquals(gNameInOrg, "Ritchie’s Uniform Civil Procedure NSW");
        Assert.assertEquals(gNameInGroup, gNameInOrg);
		Assert.assertEquals(title1, "Ritchie’s Uniform Civil Procedure NSW");
		Assert.assertEquals(title2, "Ritchie’s Uniform Civil Procedure NSW +Cases");
		Assert.assertEquals(title3, "2010 Legislation: Ritchie’s Uniform Civil Procedure NSW");
		Assert.assertEquals(title4, "2011 Legislation: Ritchie’s Uniform Civil Procedure NSW");
		Assert.assertEquals(title5, "2012 Legislation: Ritchie’s Uniform Civil Procedure NSW");
		screen.click(back, "back to publications");
	}
}

