package com.appium.Android_PageNew;

import java.util.Properties;



import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Nikitta Shen
 * @date Sep 29, 2016
 */
public class RememberPasswordPage extends BaseScreen {
	
	private static final Properties REMEMBERPASSWORD_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_pageNew/RememberPassword.properties");
    
	public final String country = REMEMBERPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_COUNTRY");
	public final String australia = REMEMBERPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_AUSTRALIA");
	public final String userName = REMEMBERPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_USERNAME");
	public final String password = REMEMBERPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_PASSWORD");
	public final String remBox = REMEMBERPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_REMBOX");
	public final String loginButton = REMEMBERPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_LOGINBUTTON");
	public final String settings = REMEMBERPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_SETTINGS");
	public final String logout = REMEMBERPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_LOGOUT");
	public final String confirm = REMEMBERPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_CONFIRM");
	
	public RememberPasswordPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}
	
	public void remPasswordTest(String userNameData, String passwordData){
		
		/*Login, then Logout and on login screen for the second time directly without input password*/
		screen.click(country, "country");
		screen.click(australia, "Australia");
		screen.input(userName, userNameData, "username");
		screen.input(password, passwordData, "password");
		screen.click(remBox, "Rememeber Password check box");
		screen.click(loginButton, "login");
		screen.waitProgress();
		
		screen.click(settings, "Settings button");
		screen.click(logout, "Logout");
		screen.click(confirm, "Confirm logout");
		
		screen.click(loginButton, "login");
		screen.waitProgress();
		
		screen.click(settings, "Settings button");
		screen.click(logout, "Logout");
		screen.click(confirm, "Confirm logout");
	}
	
}
