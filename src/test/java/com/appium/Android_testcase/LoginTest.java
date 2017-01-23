package com.appium.Android_testcase;

import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appium.Android_page.LoginPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class LoginTest {
	private RedAndroid screen;
	private ExcelUtils excel;
	private LoginPage login;
	
	@Test(dataProvider = "dp")
	public void login(Map<String, Object> data) {
		login = new LoginPage(screen, data, excel);
		login.loginAndroidTest();
		Assert.assertEquals(data.get("msgActual"), data.get("msgExpect"));
	}

	@BeforeTest
	public void beforeMethod() {
		screen = new RedAndroid();
	}

	@AfterTest
	public void afterMethod() {
		screen.quit();
	}

	@DataProvider(name = "dp")
	public Iterator<Object[]> testData() throws Exception{
		excel = new ExcelUtils("Android.xls", "Login");
		return excel;
	}
	
}
