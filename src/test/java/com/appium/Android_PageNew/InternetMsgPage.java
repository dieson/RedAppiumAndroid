package com.appium.Android_PageNew;

import java.util.Properties;

import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Nikitta Shen
 * @date Sep 9, 2016
 */
public class InternetMsgPage extends BaseScreen {
	
	private static final Properties INTERNET_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_pageNew/InternetMsg.properties");
	
	
	public final String skipTour = INTERNET_PAGE_PROPERTIES.getProperty("ANDROID_SKIPTOUR");
	public final String internetMsg = INTERNET_PAGE_PROPERTIES.getProperty("ANDROID_INTERNETCONNECTIONWARNING");
    
	public final String country = INTERNET_PAGE_PROPERTIES.getProperty("ANDROID_COUNTRY");
	public final String australia = INTERNET_PAGE_PROPERTIES.getProperty("ANDROID_AUSTRALIA");
	public final String userName = INTERNET_PAGE_PROPERTIES.getProperty("ANDROID_USERNAME");
	public final String password = INTERNET_PAGE_PROPERTIES.getProperty("ANDROID_PASSWORD");
	public final String loginButton = INTERNET_PAGE_PROPERTIES.getProperty("ANDROID_LOGINBUTTON");
	
	public final String settings = INTERNET_PAGE_PROPERTIES.getProperty("ANDROID_SETTINGS");
	public final String logout = INTERNET_PAGE_PROPERTIES.getProperty("ANDROID_LOGOUT");
	public final String confirm = INTERNET_PAGE_PROPERTIES.getProperty("ANDROID_CONFIRM");

	
	public InternetMsgPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	
	public void skipTour(){
		screen.waitProgress();
		if(screen.isExistElement(skipTour)){
			screen.click(skipTour, "Skip Tour");
		}
	}
	
	public void internetMsgTest(String userNameData, String passwordData){
		
		/*First time there is the message*/
		
		String msg = screen.getText(internetMsg, "internet connection message for the first time");
		Assert.assertEquals(msg, "Please make sure you are connected to the Internet the first time you log in.");
		
		/*Login, then Logout and on login screen for the second time, there is no internet waring message*/
		screen.click(country, "country");
		screen.click(australia, "Australia");
		screen.input(userName, userNameData, "username");
		screen.input(password, passwordData, "password");
		screen.click(loginButton, "login");
		screen.waitProgress();
		
		screen.click(settings, "Settings button");
		screen.click(logout, "Logout");
		screen.click(confirm, "Confirm logout");
		
		if(screen.isExistElement(internetMsg)){
		   Assert.fail("There is also the internet message on login screen for the second time.");
		}
	
	}
	

}
