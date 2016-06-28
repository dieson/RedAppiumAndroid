package com.appium.Android_testcase;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_page.FilterPublicationPage;
import com.appium.Android_page.LoginPage;
import com.appium.driver.RedAndroid;

public class FilterPublicationTest {
	private RedAndroid screen;
	private FilterPublicationPage filter;
	private String userName;
	
	@Test
	public void filterPublication() {
		filter.filterPublication();
	}
	
	@Test
	public void checkExpiredDate(){
		filter.expiredDate(userName);
	}
	
	@Test
	public void checkFTC(){
		filter.checkFTC();
	}
	
	@Parameters({"userName", "passWord", "country"})
	@BeforeTest
	public void beforeTest(String userNameData, String passWordData, String countryData) {
		userName = userNameData;
		screen = new RedAndroid();
		LoginPage loginPage = new LoginPage(screen);
		loginPage.loginAndroid(userNameData, passWordData, countryData);
		filter = new FilterPublicationPage(screen);
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}

}
