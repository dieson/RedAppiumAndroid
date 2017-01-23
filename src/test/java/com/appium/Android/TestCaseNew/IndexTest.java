package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.IndexPage;
import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_page.LogoutPage;
import com.appium.driver.RedAndroid;

public class IndexTest {

	private String titleName;
	private RedAndroid screen;

	@Test
	public void indexTest() {
		IndexPage index = new IndexPage(screen);
		index.index(titleName);
	}

	@Parameters({ "userName", "passWord", "country", "titleName" })
	@BeforeTest
	public void beforeTest(String userName, String passWord, String country, String titleName) {
		this.titleName = titleName;
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
