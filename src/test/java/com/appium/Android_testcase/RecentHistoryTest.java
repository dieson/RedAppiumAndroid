package com.appium.Android_testcase;

import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_page.LoginPage;
import com.appium.Android_page.LogoutPage;
import com.appium.Android_page.RecentHistoryPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class RecentHistoryTest {
	private RedAndroid screen;
	private ExcelUtils excel;
	private RecentHistoryPage recentHistory;
	
	@Test(dataProvider = "dp")
	public void recentHistory(Map<String, Object> data) {
		recentHistory = new RecentHistoryPage(screen, data, excel);
		recentHistory.recentHisotry();
		
		/*LogoutPage logoutPage = new LogoutPage(screen);
		logoutPage.logoutAndroid();*/
		
		Assert.assertEquals(data.get("HistoryToc").toString(), data.get("TocName").toString(), "TOCName");
		Assert.assertEquals(data.get("HistoryName").toString(), data.get("TitleName").toString(), "TitleName");
	}
	
	@Test(dependsOnMethods = "recentHistory")
	public void maxHistoryNO(){
		recentHistory.maxHistoryNO();
	}
	
	@DataProvider(name = "dp")
	public Iterator<Object[]> dp() throws Exception {
		excel = new ExcelUtils("Android.xls", "RecentHistory");
		return excel;
	}

	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userNameData, String passWordData, String countryData) {
		screen = new RedAndroid();
		/*LoginPage loginPage = new LoginPage(screen);
		loginPage.loginAndroid(userNameData, passWordData, countryData);*/
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}

}
