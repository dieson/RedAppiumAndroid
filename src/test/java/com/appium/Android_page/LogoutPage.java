package com.appium.Android_page;

import java.util.Properties;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;


public class LogoutPage extends BaseScreen{
	private static final Properties LOGOUT_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_page/LogoutPage.properties");

	public final String options = LOGOUT_PAGE_PROPERTIES.getProperty("ANDROID_OPTIONS");
	public final String logout = LOGOUT_PAGE_PROPERTIES.getProperty("ANDROID_LOGOUT");
	public final String msg = LOGOUT_PAGE_PROPERTIES.getProperty("ANDROID_MSG");
	public final String cancel = LOGOUT_PAGE_PROPERTIES.getProperty("ANDROID_CANCEL");
	public final String confirm = LOGOUT_PAGE_PROPERTIES.getProperty("ANDROID_CONFIRM");

	public LogoutPage(RedAndroid screenUtils){
		screen = screenUtils;
	}
	
	/**
	 * Android logout test method
	 */
	public void logoutTestAndroid(){
		screen.click(options, "options");
		screen.click(logout, "lougout");
		screen.click(cancel, "cancel");

		screen.click(options, "options");
		screen.click(logout, "lougout");
		screen.click(confirm, "confirm");
	}
	
	/**
	 * Android logout base method
	 */
	public void logoutAndroid(){
		screen.click(options, "options");
		screen.click(logout, "lougout");
		screen.click(confirm, "confirm");
	}
	
}
