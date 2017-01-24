package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.driver.RedAndroid;

public class LogoutTest {
	private RedAndroid screen;
	private LoginPage loginPage;
	
	@Test
	public void logout() {
		LogoutPage logoutPage = new LogoutPage(screen);
		logoutPage.logoutTestAndroid();
	}
	
	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userNameData, String passWordData, String countryData) {
		screen = new RedAndroid();
		/*loginPage = new LoginPage(screen);
		loginPage.loginAndroid(userNameData, passWordData, countryData);*/
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}

}
