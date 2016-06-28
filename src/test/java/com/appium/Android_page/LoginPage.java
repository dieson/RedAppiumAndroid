package com.appium.Android_page;

import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;


public class LoginPage extends BaseScreen{
	private static final Properties LOGIN_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_page/LoginPage.properties");
	
	public final String userName = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_USERNAME");
	public final String passWord = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_PASSWORD");
	public final String country = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_COUNTRY");
	public final String loginButton = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_LOGINBUTTON");
	public final String loginMsg = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_LOGINMSG");
	public final String options = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_OPTIONS");
	public final String contactUs = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_CONTACTUS");
	public final String contactIcon = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_CONTACTICON");
	public final String phone = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_PHONE");
	public final String call = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_CALL");
	public final String fax = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_FAX");
	public final String email = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_EMAIL");
	public final String post = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_POST");
	public final String postDx = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_POSTDX");
	public final String back = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_BACK");
	public final String erroButton = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_ERROR");
	public final String skipTour = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_SKIPTOUR");
	
	public LoginPage(RedAndroid screenUtils){
		screen = screenUtils;
	}
	
	public LoginPage(RedAndroid screenUtils, Map<String, Object> dataDriver, ExcelUtils excelUtils){
		screen = screenUtils;
		data = dataDriver;
		excel = excelUtils;
	}
	
	/**
	 * Android login
	 * @param userNameData
	 * @param passWordData
	 * @param countryData
	 */
	public void loginAndroid(String userNameData, String passWordData, String countryData){
		screen.input(userName, userNameData, "username");
		screen.input(passWord, passWordData, "password");
		screen.click(country, "country");
		screen.click("NAME:" + countryData, countryData);
		screen.click(loginButton, "login");
		screen.waitProgress();
	}
	
	public void loginAndroidTest(){
		screen.input(userName, data.get("username").toString(), "username");
		screen.input(passWord, data.get("password").toString(), "password");
		screen.click(country, "country");
		screen.click("NAME:" + data.get("country").toString(), data.get("country").toString());
		screen.click(loginButton, "login");
		screen.waitProgress();
		String message = screen.getText(loginMsg, "loginMsg");
		writeExcel("msgActual", message);
		screen.click(erroButton, "Error OK");
		screen.wait(3);
		
		Assert.assertEquals(message, data.get("msgExpect"));
	}
	
	public void skipTour(){
		if(screen.isExistElement(skipTour)){
			screen.click(skipTour, "Skip Tour");
		}
	}
	
	/**
	 * verify contact us page
	 * @param userNameData
	 * @param passWordData
	 * @param countryData
	 */
	public void contactAndScrollTest(String userNameData, String passWordData, String countryData){
		screen.click(options, "Options");
		screen.click(contactUs, "Contact Us");
		String contactIconStr = screen.getText(contactIcon, "Contact Icon");
		String phoneStr = screen.getText(phone, "Phone Number");
		String callStr = screen.getText(call, "Call Number");
		String faxStr = screen.getText(fax, "Fax Number");
		String emailStr = screen.getText(email, "Email Address");
		String postStr = screen.getText(post, "Post Address");
		String postDxStr = screen.getText(postDx, "PostDx Address");
		writeExcel("contactIcon", contactIconStr);
		writeExcel("phone", phoneStr);
		writeExcel("fax", faxStr);
		writeExcel("email", emailStr);
		writeExcel("post", postStr);
		writeExcel("postDx", postDxStr);
		screen.click(back, "Back");

		Assert.assertEquals(contactIconStr,	data.get("contactIconExpect"));
		Assert.assertEquals(phoneStr, data.get("phoneExpect"));
		Assert.assertEquals(faxStr, data.get("faxExpect"));
		Assert.assertEquals(emailStr, data.get("emailExpect"));
		Assert.assertEquals(postStr, data.get("postExpect"));
		Assert.assertEquals(postDxStr, data.get("postDxExpect"));
	}
}
