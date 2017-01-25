package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.Android_PageNew.TOCPage;
import com.appium.driver.RedAndroid;

public class TOCTest {
	
	private RedAndroid screen;
	private TOCPage toc;
	
	@Test
	public void hideTOCTest() {
		toc.hideTOC();
	}
	
	@Test(dependsOnMethods = "hideTOCTest")
	public void previousNextTest() {
		toc.previousNext();
	}

	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userName, String passWord, String country) {
		
		screen = new RedAndroid();
		LoginPage login = new LoginPage(screen);
		login.loginAndroid(userName, passWord, country);
		screen.offGroup();
		toc = new TOCPage(screen);
		
	}

	@AfterTest
	public void afterTest() {
		LogoutPage logout = new LogoutPage(screen);
		logout.logoutAndroid();
		screen.quit();
	}
	
}
