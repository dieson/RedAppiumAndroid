package com.appium.Android_testcase;

import java.util.Iterator;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_page.DownloadPage;
import com.appium.Android_page.LoginPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;
public class DownloadTest {
	private RedAndroid screen;
	private LoginPage login;
	private ExcelUtils excel;	
	private DownloadPage download;
	
	/*@Test(dataProvider = "dp")
	public void download(Map<String, Object> data) {
		download = new DownloadPage(screen, data, excel);
		download.download();
		Assert.assertEquals(data.get("dataActual").toString().contains(data.get("dataExpect").toString()), true);
	}
	
	@Test
	public void download() {
		download = new DownloadPage(screen);
		download.downloadTowBooks();
	}*/
	
	@Test
	public void download() {
		download = new DownloadPage(screen);
		download.downloadAll();
	}
	

	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userName, String passWrod, String country) {
		screen = new RedAndroid();
		login = new LoginPage(screen);
		login.loginAndroid(userName, passWrod, country);
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}
	
	@DataProvider(name = "dp")
	public Iterator<Object[]> dp() throws Exception{
		excel = new ExcelUtils("Android.xls", "Download");
		return excel;
	}
}
