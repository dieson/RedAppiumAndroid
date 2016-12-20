package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.FilterPubInGroupPage;
import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.driver.RedAndroid;

public class FilterPubInGroupTest {
	
	private RedAndroid screen;
	private FilterPubInGroupPage filterG;

	@Test
	public void filterPubInGroupTest() {
		
		filterG = new FilterPubInGroupPage(screen);
		filterG.filterPubInGroupTest();
	}
	
	
	@Parameters({ "userName", "passWord", "country" })
	@BeforeTest
	public void beforeTest(String userName, String passWord, String country) {

		screen = new RedAndroid();
		LoginPage login = new LoginPage(screen);
		login.loginAndroid(userName, passWord, country);
	}

	@AfterTest
	public void afterTest() {
		LogoutPage logout = new LogoutPage(screen);
		logout.logoutAndroid();
		screen.quit();
	}

	
}
