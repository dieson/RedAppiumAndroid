package com.appium.iOS_page;

import java.util.Map;
import java.util.Properties;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;


public class LogoutPage extends BaseScreen{
	private static final Properties LOGOUT_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/iOS_page/LogoutPage.properties");
	
	public final String setting = LOGOUT_PAGE_PROPERTIES.getProperty("IOS_SETTING");
	public final String logout = LOGOUT_PAGE_PROPERTIES.getProperty("IOS_LOGOUT");
	
	public LogoutPage(RedAndroid screenUtils, Map<String, Object> dataDriver, ExcelUtils excelUtils){
		screen = screenUtils;
		data = dataDriver;
		excel = excelUtils;
	}
	
	/**
	 * iOS logout test method
	 */
	public void logoutTestIOS(){
		screen.click(setting, "Setting");
		screen.click(logout, "Logout");
		screen.alertDismiss();
		
		screen.click(setting, "Setting");
		screen.click(logout, "Logout");
		String msg = screen.alertGetText();
		writeExcel("msgActual", msg);
	}
	
	/**
	 * iOS logout base method
	 */
	public void logoutIOS(){
		screen.click(setting, "Setting");
		screen.click(logout, "Logout");
		screen.alertAccept();
	}
}
