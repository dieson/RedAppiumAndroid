package com.appium.test;

import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_page.DownloadPage;
import com.appium.Android_page.LoginPage;
import com.appium.Android_page.LogoutPage;
import com.appium.Android_page.RecentHistoryPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class TestA{
	private RedAndroid screen;
	private ExcelUtils excel;
	
	@Test(dataProvider = "dp")
	public void resentHistory(Map<String, Object> data) {
		DownloadPage download = new DownloadPage(screen);
		download.download();
		
		RecentHistoryPage recentHistory = new RecentHistoryPage(screen, data, excel);
		recentHistory.recentHisotry();
		
		LogoutPage logoutPage = new LogoutPage(screen);
		logoutPage.logoutTestAndroid();
	}
	
	@DataProvider(name = "dp")
	public Iterator<Object[]> dp() throws Exception {
		excel = new ExcelUtils("Android.xls", "ResentHistory");
		return excel;
	}

	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userNameData, String passWordData, String countryData) {
		screen = new RedAndroid();
		LoginPage loginPage = new LoginPage(screen);
		loginPage.loginAndroid(userNameData, passWordData, countryData);
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}

}
