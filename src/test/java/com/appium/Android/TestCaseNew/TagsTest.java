package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.AnnotationsPage;
import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.driver.RedAndroid;

public class TagsTest {
	private RedAndroid screen;
	private AnnotationsPage annotations;
	
	@Test
	public void addTags() {
		annotations.add();
	}
	
	@Test(dependsOnMethods = "addTags")
	public void editTags() {
		annotations.edit();
	}
	
	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userNameData, String passWordData, String countryData) {
		screen = new RedAndroid();
		annotations = new AnnotationsPage(screen);
		LoginPage loginPage = new LoginPage(screen);
		loginPage.loginAndroid(userNameData, passWordData, countryData);
	}

	@AfterTest
	public void afterTest() {
		LogoutPage logoutPage = new LogoutPage(screen);
		logoutPage.logoutAndroid();
		screen.quit();
	}

}
