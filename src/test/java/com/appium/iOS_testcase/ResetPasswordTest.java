package com.appium.iOS_testcase;

import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;
import com.appium.iOS_page.LoginPage;
import com.appium.iOS_page.LogoutPage;
import com.appium.iOS_page.ResetPasswordPage;

public class ResetPasswordTest {
	private RedAndroid screen;
	private ExcelUtils excel;
	

	@Test(dataProvider = "dp")
	public void resetPassword(Map<String, Object> data) throws Exception{
		//Reset password
		ResetPasswordPage reset = new ResetPasswordPage(screen, data, excel);
		reset.resetPassword();
		
		//use reset password to login
		LoginPage login = new LoginPage(screen, data, excel);
		login.loginIOS(data.get("userName").toString(), data.get("resetPassword").toString(), data.get("country").toString());
		
		//change password
		reset.changePassword();
		Thread.sleep(3000);
		
		//logout
		LogoutPage logout = new LogoutPage(screen, data, excel);
		logout.logoutIOS();
		Thread.sleep(3000);
		
		//use changed password to login
		login.loginIOS(data.get("userName").toString(), data.get("password").toString(), data.get("country").toString());
		Thread.sleep(3000);
		logout.logoutIOS();
		
		//verify
		Assert.assertEquals(data.get("resetMsg"), data.get("resetMsgExpect"));
		Assert.assertEquals(data.get("changeMsg"), data.get("changeMsgExpect"));
	}
	
	@DataProvider(name = "dp")
	public Iterator<Object[]> testData() throws Exception {
		excel = new ExcelUtils("Login.xls", "ResetPassword");
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
