package com.appium.Android_testcase;

import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appium.Android_page.LoginPage;
import com.appium.Android_page.LogoutPage;
import com.appium.Android_page.ResetPasswrodPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class ResetPasswordTest {
	private RedAndroid screen;
	private ExcelUtils excel;
	
	@Test(dataProvider = "dp")
	public void resetPassword(Map<String, Object> data) throws Exception {
		//relieve the device limit
		LoginPage login = new LoginPage(screen);
		LogoutPage logout = new LogoutPage(screen);
		login.loginAndroid(data.get("resetusername").toString(), data.get("resetpassword").toString(), data.get("country").toString());
		logout.logoutAndroid();
		
		//reset password
		ResetPasswrodPage reset = new ResetPasswrodPage(screen, data, excel);
		reset.resetPassword();
		
		//use reset password to login
		login.loginAndroid(data.get("resetusername").toString(), data.get("resetpassword").toString(), data.get("country").toString());
		
		//change password
		reset.changePassword();
		Thread.sleep(3000);
		
		//logout
		logout.logoutAndroid();
		Thread.sleep(3000);
		
		//use change password to login
		login.loginAndroid(data.get("resetusername").toString(), data.get("changepassword").toString(), data.get("country").toString());
		Thread.sleep(3000);
		logout.logoutAndroid();
		
		//verify
		Assert.assertEquals(data.get("resetMsg"), data.get("resetMsgExpect"));
		Assert.assertEquals(data.get("changeMsg"), data.get("changeMsgExpect"));
	}
	
	@DataProvider(name = "dp")
	public Iterator<Object[]> testData() throws Exception{
		excel = new ExcelUtils("Android.xls", "ResetPassword");
		return excel;
	}

	@BeforeTest
	public void beforeTest() {
		screen = new RedAndroid();
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}

}
