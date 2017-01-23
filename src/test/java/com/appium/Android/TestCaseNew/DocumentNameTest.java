package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.DocumentNamePage;
import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.driver.RedAndroid;

public class DocumentNameTest {
	
	private RedAndroid screen;
	private DocumentNamePage documentName;

	@Test
	public void documentNameTest() {
		
		documentName = new DocumentNamePage(screen);
		documentName.documentNameTest();
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
