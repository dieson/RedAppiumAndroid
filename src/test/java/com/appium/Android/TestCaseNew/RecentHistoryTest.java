package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.Android_PageNew.RecentHistoryPage;
import com.appium.driver.RedAndroid;

public class RecentHistoryTest {
	private RedAndroid screen;
	private RecentHistoryPage recentHistory;
	
	@Test
	public void recentHistory() {
		recentHistory.recentHisotry();
	}
	
	@Test(dependsOnMethods = "recentHistory")
	public void maxHistoryNO(){
		recentHistory.maxHistoryNO();
	}
	
	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userNameData, String passWordData, String countryData) {
		screen = new RedAndroid();
		LoginPage loginPage = new LoginPage(screen);
		loginPage.loginAndroid(userNameData, passWordData, countryData);
		screen.offGroup();
		recentHistory = new RecentHistoryPage(screen);
	}

	@AfterTest
	public void afterTest() {
		LogoutPage logoutPage = new LogoutPage(screen);
		logoutPage.logoutAndroid();
		screen.quit();
	}

}
