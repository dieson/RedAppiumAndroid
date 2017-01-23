package com.appium.Android_testcase;

import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_page.LoginPage;
import com.appium.Android_page.LogoutPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class LogoutTest {
	private RedAndroid screen;
	private ExcelUtils excel;
	private LoginPage loginPage;
	
	@Test(dataProvider = "dp")
	public void logout(Map<String, Object> data) {
		LogoutPage logoutPage = new LogoutPage(screen);
		logoutPage.logoutTestAndroid();
	}
	
	@DataProvider(name = "dp")
	public Iterator<Object[]> dp() throws Exception {
		excel = new ExcelUtils("Android.xls", "Logout");
		return excel;
	}

	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userNameData, String passWordData, String countryData) {
		screen = new RedAndroid();
		loginPage = new LoginPage(screen);
		loginPage.loginAndroid(userNameData, passWordData, countryData);
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}

}
