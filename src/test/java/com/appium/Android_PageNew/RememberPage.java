package com.appium.Android_PageNew;

import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 *
 */
public class RememberPage extends BaseScreen {

	private static final Properties REMEMBER_PAGE_PROPERTIES = new PropertyUtils()
			.loadProperties("/Android_PageNew/RememberPage.properties");

	public final String userName = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_USERNAME");
	public final String passWord = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_PASSWORD");
	public final String country = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_COUNTRY");
	public final String loginButton = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_LOGINBUTTON");
	public final String loginMsg = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_LOGINMSG");
	public final String options = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_OPTIONS");
	public final String back = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_BACK");
	public final String erroButton = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_ERROR");
	public final String skipTour = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_SKIPTOUR");
	public final String remember = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_REMEMBER");
	public final String logout = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_LOGOUT");
	public final String confirm = REMEMBER_PAGE_PROPERTIES.getProperty("ANDROID_CONFIRM");

	public RememberPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	public RememberPage(RedAndroid screenUtils, Map<String, Object> dataDriver, ExcelUtils excelUtils) {
		screen = screenUtils;
		data = dataDriver;
		excel = excelUtils;
	}

	public void skipTour() {
		if (screen.isExistElement(skipTour)) {
			screen.click(skipTour, "Skip Tour");
		}
	}
		
   public void rememberPasswordA(String userNameData, String passWordData, String countryData) {
		
	   //Step1. userA login and remember password
		screen.input(userName, userNameData, "username");
		screen.input(passWord, passWordData, "password");
		this.onRememberPassword();
		screen.click(loginButton, "login button");
		screen.waitProgress();
		// Step2. userA remember to logout
		screen.click(options, "option button");
		screen.click(logout, "logout button");
		screen.click(confirm, "confirm");
	}
   
   public void rememberPasswordB(String userNameData, String passWordData, String countryData) {
	   
	    //Step1. userB login and not remember password
	    screen.input(userName, userNameData, "username");
		screen.input(passWord, passWordData, "password");
		this.offRememeberPassword();
		screen.click(loginButton, "login button");
		screen.waitProgress();
		screen.click(options, "option button");
		screen.click(logout, "logout button");
		screen.click(confirm, "confirm");
   }
    
   public void rememberPasswordC(String userNameData, String passWordData, String countryData) {
	   
	    //Step1. userA login and not remember password
	    screen.input(userName, userNameData, "username");		
		screen.click(loginButton, "login button");
		screen.waitProgress();
		//Step2. Using remember to login
		screen.click(options, "option button");
		screen.click(logout, "logout button");
		screen.click(confirm, "confirm");
	   
   }

	public void rememberPasswordD(String userNameData, String passWordData, String countryData) {

		//Step1. userB login and not remember password
		screen.input(userName, userNameData, "username");		
		screen.click(loginButton, "login button");
		String msg = screen.getText(loginMsg, "login message");
		screen.click(erroButton, "Error OK");

		Assert.assertEquals(msg, "Please enter both your email and password to log in.");
		//Assert.assertEquals(msg, "Either the email address or password you entered is incorrect.");
	}
	
	public void onRememberPassword() {

		if (!screen.isSelect(remember, "remember password")) {
			screen.click(remember, "remember password");
		}
	}

	public void offRememeberPassword() {

		if (screen.isSelect(remember, "remember password")) {
			screen.click(remember, "remember password");
		}
	}

}
