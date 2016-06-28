package com.appium.Android_testcase;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_page.AnnotationsPage;
import com.appium.Android_page.LoginPage;
import com.appium.driver.RedAndroid;

public class AnnotationsTest {
	private RedAndroid screen;
	private LoginPage login;
	private AnnotationsPage annotations;

	@Test
	public void addAnnotations() {
		//annotations.add();
	}

	@Test(dependsOnMethods = "addAnnotations")
	public void editAnnotations() {
		//annotations.edit();
	}

	@Test(dependsOnMethods = "editAnnotations")
	public void filterAnnotaions() {
		annotations.filter();
	}

	@Parameters({ "userName", "passWord", "country" })
	@BeforeTest
	public void beforeMethod(String userName, String passWrod, String country) {
		screen = new RedAndroid();
		/*login = new LoginPage(screen);
		login.loginAndroid(userName, passWrod, country);*/
		
		annotations = new AnnotationsPage(screen);
	}

	@AfterTest
	public void afterMethod() {
		screen.quit();
	}

}
