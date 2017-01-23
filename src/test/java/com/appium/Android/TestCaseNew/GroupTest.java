package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.Android_PageNew.PublicationPage;
import com.appium.driver.RedAndroid;

public class GroupTest {

	private RedAndroid screen;
	private LoginPage login;

	@Test
	public void groupTest() {
		
		PublicationPage publication = new PublicationPage(screen);
		publication.groupSingle();
	}

	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userName, String passWord, String country) {

		screen = new RedAndroid();
		login = new LoginPage(screen);
		login.loginAndroid(userName, passWord, country);
	}

	@AfterTest
	public void afterTest() {
		LogoutPage logout = new LogoutPage(screen);
		logout.logoutAndroid();
		screen.quit();
	}

}
