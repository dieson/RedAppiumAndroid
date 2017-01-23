package com.appium.Android.TestCaseNew;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.RememberPasswordPage;
import com.appium.driver.RedAndroid;

public class RememberPasswordTest {
	
	private RedAndroid screen;
	private RememberPasswordPage rememberPassword;

	@Test
	public void remPasswordTest() {
		
		
		rememberPassword = new RememberPasswordPage(screen);
		//rememberPassword.remPasswordTest("nikitta.shen@lexisnexis.com", "123465");
		rememberPassword.remPasswordTest("kenny@lexisred.com", "1234");
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
