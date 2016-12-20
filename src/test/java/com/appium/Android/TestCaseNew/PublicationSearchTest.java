package com.appium.Android.TestCaseNew;

import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.PublicationSearchPage;
import com.appium.Android_page.LogoutPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class PublicationSearchTest {
	
		private RedAndroid screen;
		private ExcelUtils excel;

		@Test(dataProvider = "dp")
		public void searchTest(Map<String, Object> data) {
			
			PublicationSearchPage search = new PublicationSearchPage(screen, data, excel);
			search.search();
		}

		@Parameters({ "userName", "passWord", "country" })
		@BeforeTest
		public void beforeTest(String userName, String passWord, String country) {
			screen = new RedAndroid();
			LoginPage login = new LoginPage(screen);
			login.loginAndroid(userName, passWord, country);
		}

		@AfterTest
		public void afterTest() {
			LogoutPage logout = new LogoutPage(screen);
			logout.logoutAndroid();
			screen.quit();
		}
		
		@DataProvider(name = "dp")
		public Iterator<Object[]> testData() throws Exception {
			excel = new ExcelUtils("Android.xls", "Search");
			return excel;
		}
}
