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
import com.appium.Android_page.TOCPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class TOCTest {
	private RedAndroid screen;
	private ExcelUtils excel;
	
	@Test(dataProvider = "dp")
	public void TOC(Map<String, Object> data) {
		TOCPage toc = new TOCPage(screen, data, excel);
		toc.TOCTest();
		
		Assert.assertEquals(data.get("TOC").toString(), data.get("TOCVerify").toString(), "TOCName");
	}
	
	@Test
	public void TOCHide(){
		TOCPage toc = new TOCPage(screen);
		toc.TOCHide();
	}
	
	@DataProvider(name = "dp")
	public Iterator<Object[]> dp() throws Exception {
		excel = new ExcelUtils("Android.xls", "TOC");
		return excel;
	}

	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userNameData, String passWordData, String countryData) {
		screen = new RedAndroid();
		LoginPage loginPage = new LoginPage(screen);
		loginPage.loginAndroid(userNameData, passWordData, countryData);
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}

}
