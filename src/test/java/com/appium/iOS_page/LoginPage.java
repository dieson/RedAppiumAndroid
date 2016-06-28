package com.appium.iOS_page;

import java.util.Map;
import java.util.Properties;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;


public class LoginPage extends BaseScreen{
	private static final Properties LOGIN_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/iOS_page/LoginPage.properties");
	
	//IOS elements
	public final String usernameLocation = LOGIN_PAGE_PROPERTIES.getProperty("IOS_USERNAME");
	public final String passwordLocation = LOGIN_PAGE_PROPERTIES.getProperty("IOS_PASSWORD");
	public final String countryLocation = LOGIN_PAGE_PROPERTIES.getProperty("IOS_COUNTRY");
	public final String loginButtonLocation = LOGIN_PAGE_PROPERTIES.getProperty("IOS_LOGINBUTTON");
	public final String loginMsgLocation = LOGIN_PAGE_PROPERTIES.getProperty("IOS_LOGINMSG");
	
	public LoginPage(RedAndroid screenUtils){
		screen = screenUtils;
	}
	
	public LoginPage(RedAndroid screenUtils, Map<String, Object> dataDriver, ExcelUtils excelUtils){
		screen = screenUtils;
		data = dataDriver;
		excel = excelUtils;
	}
	
	/**
	 * IOS login test
	 * @param userNameData
	 * @param passWordData
	 * @param countryData
	 */
	public void loginTestIOS(){
		screen.input(usernameLocation, data.get("username"), "username");
		screen.input(passwordLocation, data.get("password"), "password");
		screen.input(countryLocation, data.get("country"), "country");
		screen.click(loginButtonLocation, "login");
		String loginMsg = screen.alertGetText();
		writeExcel("msgActual", loginMsg);
		screen.wait(3);
	}
	
	/**
	 * IOS login
	 * @param userName
	 * @param password
	 * @param county
	 */
	public void loginIOS(String userName, String passWord, String county){
		screen.input(usernameLocation, userName, "username");
		screen.input(passwordLocation, passWord, "password");
		screen.input(countryLocation, county, "country");
		screen.click(loginButtonLocation, "login");
		screen.wait(3);
	}
	
}
