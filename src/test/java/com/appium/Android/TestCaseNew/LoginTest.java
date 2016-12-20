package com.appium.Android.TestCaseNew;

import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.LoginPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class LoginTest {
	
	private RedAndroid screen;
	private ExcelUtils excel;
	private LoginPage login;
	String userName;
	String passWord;
	String country;
	
	@Test(priority = 0, dataProvider = "dp")
	public void login(Map<String, Object> data) {
		
		login = new LoginPage(screen, data, excel);
		login.loginAndroidTest();
	}
	
	@Test(priority = 1)
	public void rememberPassword() {
		
		login = new LoginPage(screen);
		login.skipTour();
		login.remember(userName, passWord, country);
	}
	
	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeMethod(String userName, String passWord, String country) {
		
		this.userName = userName;
		this.passWord = passWord;
		this.country = country;
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
