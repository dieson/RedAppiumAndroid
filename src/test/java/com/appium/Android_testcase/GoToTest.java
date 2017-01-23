package com.appium.Android_testcase;

import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_page.GoToPage;
import com.appium.Android_page.LoginPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class GoToTest {
	private RedAndroid screen;
	private ExcelUtils excel;
	private LoginPage login;
	private String titleName;
	
	@Test(dataProvider = "dp")
	public void gotoPageTest(Map<String, Object> data) {
		GoToPage gotoPage = new GoToPage(screen, data, excel);
		gotoPage.gotoPage(titleName);
		Assert.assertEquals(data.get("PageNO"), data.get("PageNOVerify"));
	}

	@Parameters({ "userName", "passWord", "country", "titleName" })
	@BeforeTest
	public void beforeMethod(String userName, String passWord, String country, String titleName) {
		this.titleName = titleName;
		screen = new RedAndroid();
		login = new LoginPage(screen);
		login.loginAndroid(userName, passWord, country);
	}

	@AfterTest
	public void afterMethod() {
		screen.quit();
	}

	@DataProvider(name = "dp")
	public Iterator<Object[]> testData() throws Exception{
		excel = new ExcelUtils("Android.xls", "GoToPage");
		return excel;
	}
}
