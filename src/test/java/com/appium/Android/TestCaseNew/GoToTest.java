package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.GoToPage;
import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.driver.RedAndroid;

public class GoToTest {
	private RedAndroid screen;
	private String titleName;
	
	@Test
	public void gotoPageTest() {
		GoToPage gotoPage = new GoToPage(screen);
		gotoPage.gotoPage(titleName);
	}

	@Parameters({ "userName", "passWord", "country", "titleName" })
	@BeforeTest
	public void beforeMethod(String userName, String passWord, String country, String titleName) {
		this.titleName = titleName;
		screen = new RedAndroid();
		LoginPage login = new LoginPage(screen);
		login.loginAndroid(userName, passWord, country);
	}

	@AfterTest
	public void afterMethod() {
		LogoutPage logout = new LogoutPage(screen);
		logout.logoutAndroid();
		screen.quit();
	}

}
