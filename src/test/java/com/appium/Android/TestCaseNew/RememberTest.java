package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.RememberPage;
import com.appium.driver.RedAndroid;

public class RememberTest {
	
	private RedAndroid screen;
	private LoginPage login;
	private RememberPage remember;
	
	@Test
	public void rememberPassword() {
		
		login = new LoginPage(screen);
		remember = new RememberPage(screen);
		login.skipTour();
		remember.rememberPasswordA("echo.hu@lexisnexis.com", "qwer", "Australia");
		remember.rememberPasswordB("nikitta@lexisred.com", "123465", "Australia");
		remember.rememberPasswordC("echo.hu@lexisnexis.com", "qwer", "Australia");
		remember.rememberPasswordD("nikitta@lexisred.com", "123465", "Australia");
	}

	@BeforeTest
	public void beforeTest() {
		screen = new RedAndroid();
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}
}
