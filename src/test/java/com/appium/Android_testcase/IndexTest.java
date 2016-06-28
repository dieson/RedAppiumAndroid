package com.appium.Android_testcase;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_page.IndexPage;
import com.appium.Android_page.LoginPage;
import com.appium.driver.RedAndroid;

public class IndexTest {
	private LoginPage login;
	private RedAndroid screen;
	
	@Test
	public void index() {
		IndexPage index = new IndexPage(screen);
		index.index();
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
		screen.quit();
	}

}
