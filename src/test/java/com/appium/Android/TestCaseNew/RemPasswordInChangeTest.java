package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.RemPasswordInChangePage;
import com.appium.Android_page.LogoutPage;
import com.appium.driver.RedAndroid;

public class RemPasswordInChangeTest {
	
	private RedAndroid screen;
	private RemPasswordInChangePage remPasswordInChange;

	@Test //(invocationCount= 100)
	public void remPasswordInChangeTest() {
		
		remPasswordInChange = new RemPasswordInChangePage(screen);
		//remPasswordInChange.remPasswordInChangeTest("nikitta.shen@lexisnexis.com","81dc9bdb52d04dc20036dbd8313ed055","123465");
		remPasswordInChange.remPasswordInChangeTest("kenny@lexisred.com","81dc9bdb52d04dc20036dbd8313ed055","1234");
	}
	
	@BeforeTest
	public void beforeTest() {
		screen = new RedAndroid();
	}

	@AfterTest
	public void afterTest() {
		LogoutPage logout = new LogoutPage(screen);
		logout.logoutAndroid();
		screen.quit();
	}

	
}
