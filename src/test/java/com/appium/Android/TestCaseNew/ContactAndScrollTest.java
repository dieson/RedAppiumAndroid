package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.driver.RedAndroid;

public class ContactAndScrollTest {
	private RedAndroid screen;
	private LoginPage login;
	
	@Test
	public void contact() {
		login.contactAndScrollTest();
	}

	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeMethod(String userName, String passWord, String country) {
		screen = new RedAndroid();
		login = new LoginPage(screen);
		login.loginAndroid(userName, passWord, country);
	}

	@AfterTest
	public void afterMethod() {
		LogoutPage logout = new LogoutPage(screen);
		logout.logoutAndroid();
		screen.quit();
	}

	
}
