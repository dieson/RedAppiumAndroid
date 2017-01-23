package com.appium.Android_PageNew;

import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Dieson Zuo
 * @date Sep 5, 2016 4:24:09 PM
 */
public class LoginPage extends BaseScreen {

	private static final Properties LOGIN_PAGE_PROPERTIES = new PropertyUtils()
			.loadProperties("/Android_PageNew/LoginPage.properties");

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
	public final String remember = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_REMEMBER");
	public final String logout = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_LOGOUT");
	public final String confirm = LOGIN_PAGE_PROPERTIES.getProperty("ANDROID_CONFIRM");

	public LoginPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	public LoginPage(RedAndroid screenUtils, Map<String, Object> dataDriver, ExcelUtils excelUtils) {
		screen = screenUtils;
		data = dataDriver;
		excel = excelUtils;
	}

	public void skipTour() {
		if (screen.isExistElement(skipTour)) {
			screen.click(skipTour, "Skip Tour");
		}
	}

	public void loginAndroid(String userNameData, String passWordData, String countryData) {
		this.skipTour();
		screen.input(userName, userNameData, "username");
		screen.input(passWord, passWordData, "password");
		screen.click(country, "country");
		screen.click("NAME:" + countryData, countryData);
		screen.click(loginButton, "login");
		screen.waitProgress();
	}
	
	public void loginAndroidTest(){
		this.skipTour();
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
	
	/**
	 * verify contact us page
	 */
	public void contactAndScrollTest(){
		screen.click(options, "Options");
		screen.click(contactUs, "Contact Us");
		String contactIconStr = screen.getText(contactIcon, "Contact Icon");
		String phoneStr = screen.getText(phone, "Phone Number");
		String callStr = screen.getText(call, "Call Number");
		String faxStr = screen.getText(fax, "Fax Number");
		String emailStr = screen.getText(email, "Email Address");
		String postStr = screen.getText(post, "Post Address");
		String postDxStr = screen.getText(postDx, "PostDx Address");
		screen.click(back, "Back");

		Assert.assertEquals(contactIconStr, "Contact us");
		Assert.assertEquals(phoneStr, "1800 999 906");
		Assert.assertEquals(callStr, "+61 2 9422 2174");
		Assert.assertEquals(faxStr, "02 9422 2405");
		Assert.assertEquals(emailStr, "techsupport@lexisnexis.com.au");
		Assert.assertEquals(postStr.replaceAll("\\s*", ""), "Level 9 Locked Bag 2222 Chatswood Delivery Centre Chatswood NSW 2067".replaceAll("\\s*", ""));
		Assert.assertEquals(postDxStr.replaceAll("\\s*", ""), "Street Address: LexisNexis DX 29590 Chatswood Tower 2, 475-495 Victoria Avenue Chatswood NSW 2067".replaceAll("\\s*", ""));
	}

	public void remember(String userNameData, String passWordData, String countryData) {

		// Step1. Login and select remember
		screen.input(userName, userNameData, "username");
		screen.input(passWord, passWordData, "password");
		this.onRememberPassword();
		screen.click(loginButton, "login button");
		screen.waitProgress();
		// Step2. Using remember to login
		screen.click(options, "option button");
		screen.click(logout, "logout button");
		screen.click(confirm, "confirm");
		this.offRememeberPassword();
		screen.click(loginButton, "login button");
		screen.waitProgress();
		// Step3. Deselect remember
		screen.click(options, "option button");
		screen.click(logout, "logout button");
		screen.click(confirm, "confirm");
		screen.click(loginButton, "login button");
		String msg = screen.getText(loginMsg, "login message");
		screen.click(erroButton, "Error OK");

		Assert.assertEquals(msg, "Please enter both your email and password to log in.");
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
