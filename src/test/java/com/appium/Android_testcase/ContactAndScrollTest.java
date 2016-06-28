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
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class ContactAndScrollTest {
	private RedAndroid screen;
	private ExcelUtils excel;
	private LoginPage login;
	private String userName;
	private String passWord;
	private String country;
	
	@Test(dataProvider = "dp")
	public void contact(Map<String, Object> data) {
		login = new LoginPage(screen, data, excel);
		login.loginAndroid(userName, passWord, country);
		login.contactAndScrollTest(userName, passWord, country);
		
		LogoutPage logout = new LogoutPage(screen);
		logout.logoutAndroid();
		
		Assert.assertEquals(data.get("contactIcon"), data.get("contactIconExpect"));
		Assert.assertEquals(data.get("phone"), data.get("phoneExpect"));
		Assert.assertEquals(data.get("call"), data.get("callExpect"));
		Assert.assertEquals(data.get("fax"), data.get("faxExpect"));
		Assert.assertEquals(data.get("email"), data.get("emailExpect"));
		Assert.assertEquals(data.get("post"), data.get("postExpect"));
		Assert.assertEquals(data.get("postDx"), data.get("postDxExpect"));
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
		excel = new ExcelUtils("Android.xls", "Contact");
		return excel;
	}
	
}
