package com.appium.iOS_testcase;

import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;
import com.appium.iOS_page.LoginPage;
import com.appium.iOS_page.LogoutPage;

public class LogoutTest {
	private RedAndroid screen;
	private ExcelUtils excel;
	private LoginPage login;
	
	@Test(dataProvider = "dp")
	public void logout(Map<String, Object> data) {
		LogoutPage logout = new LogoutPage(screen, data, excel);
		logout.logoutTestIOS();
		Assert.assertEquals(data.get("msgActual"), data.get("msgExpect"));
	}
	
	@DataProvider(name = "dp")
	public Iterator<Object[]> testData() throws Exception {
		excel = new ExcelUtils("Login.xls", "Logout");
		return excel;
	}

	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userNameData, String passWordData, String countryData) {
		screen = new RedAndroid();
		login = new LoginPage(screen);
		login.loginIOS(userNameData, passWordData, countryData);
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}

}
