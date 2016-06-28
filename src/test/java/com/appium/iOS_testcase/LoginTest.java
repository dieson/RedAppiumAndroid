package com.appium.iOS_testcase;

import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.Driver;
import com.appium.driver.RedAndroid;
import com.appium.iOS_page.LoginPage;

public class LoginTest {
	private Driver appium;
	private RedAndroid screen;
	private LoginPage login;
	
	@Test(dataProvider = "dp")
	public void login(Map<String, Object> data) {
		login.loginTestIOS();
		Assert.assertEquals(data.get("msgActual"), data.get("msgExpect"));
	}

	@BeforeTest
	public void beforeMethod() {
		appium = new Driver();
		login = new LoginPage(screen);
	}

	@AfterTest
	public void afterMethod() {
		appium.quit();
	}

	@DataProvider(name = "dp")
	public Iterator<Object[]> testData() throws Exception{
		return new ExcelUtils("Login.xls", "Login");
	}
	
}
