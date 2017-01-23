package com.appium.Android_testcase;

import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_page.LoginPage;
import com.appium.Android_page.LogoutPage;
import com.appium.Android_page.SearchPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class SearchTest {
	private RedAndroid screen;
	private ExcelUtils excel;

	@Test(dataProvider = "dp")
	public void search(Map<String, Object> data) {
		SearchPage search = new SearchPage(screen, data, excel);
		search.searchTest();

		LogoutPage logout = new LogoutPage(screen);
		logout.logoutAndroid();
		
		if(data.get("result_publication").toString().equals("Publication")){
			Assert.assertEquals(data.get("history_TOC").toString(), data.get("open_TOC").toString());
			Assert.assertEquals(data.get("result_TOC").toString(), data.get("open_TOC").toString());
		}else {
			Assert.assertEquals(data.get("history_TOC").toString(), data.get("open_TOC").toString());
		}
	}

	@BeforeTest
	@Parameters({ "userName", "passWord", "country" })
	public void beforeMethod(String userName, String passWord, String country) {
		screen = new RedAndroid();
		LoginPage login = new LoginPage(screen);
		login.loginAndroid(userName, passWord, country);
	}

	@AfterTest
	public void afterMethod() {
		screen.quit();
	}

	@DataProvider(name = "dp")
	public Iterator<Object[]> testData() throws Exception {
		excel = new ExcelUtils("Android.xls", "Search");
		return excel;
	}
}
