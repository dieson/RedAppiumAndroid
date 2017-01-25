package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.AnnotationEditPage;
import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.driver.RedAndroid;

public class AnnotationEditTest {
	private RedAndroid screen;
	private AnnotationEditPage annotation;
	
	@Test
	public void addAnnotationTest() {
		annotation.addAnnotation();
	}
	
	@Test(dependsOnMethods = "addAnnotationTest")
	public void addNoteTagTest() {
		annotation.addNoteTag();
	}
	
	@Test(dependsOnMethods = "addNoteTagTest")
	public void editNoteTagTest() {
		annotation.editNoteTag();
	}
	
	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userNameData, String passWordData, String countryData) {
		
		screen = new RedAndroid();
		LoginPage loginPage = new LoginPage(screen);
		loginPage.loginAndroid(userNameData, passWordData, countryData);
		annotation = new AnnotationEditPage(screen);
	}

	@AfterTest
	public void afterTest() {
		
		LogoutPage logoutPage = new LogoutPage(screen);
		logoutPage.logoutAndroid();
		screen.quit();
	}

}
