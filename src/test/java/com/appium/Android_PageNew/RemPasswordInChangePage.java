package com.appium.Android_PageNew;

import java.util.Properties;

import com.appium.datautils.DatabaseUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Nikitta Shen
 * @date 10th Oct, 2016
 */
public class RemPasswordInChangePage extends BaseScreen {
	
	private static final Properties REMPINCHANGE_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_pageNew/RemPasswordInChange.properties");
	
	public final String forgotButton = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_FORGOT");
	public final String submitButton = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_SUBMIT");
	public final String okButton = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_OK");
	public final String country = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_COUNTRY");
	public final String australia = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_AUSTRALIA");
	public final String email = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_USERNAME");
	public final String password = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_PASSWORD");
	public final String loginButton = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_LOGINBUTTON");
	public final String newPwd = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_NEWPASSWORD");
	public final String retypePwd = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_RETYPENEW");
	public final String rem = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_REMBOX");
	public final String send = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_SEND");
	public final String settings = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_SETTINGS");
	public final String logout = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_LOGOUT");
	public final String confirm = REMPINCHANGE_PAGE_PROPERTIES.getProperty("ANDROID_CONFIRM");
	
	public RemPasswordInChangePage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	public void remPasswordInChangeTest(String userName, String tempMD5, String newPassword){
		
		/*Go to forgot screen to send a request to database*/
		screen.click(forgotButton, "Go to forgot password screen");
		screen.input(email, userName, "input the email for forgot password");
		screen.click(submitButton, "submit button");
		screen.waitProgress();
		//screen.click(okButton, "OK button");
		//screen.click(submitButton, "submit button");
		//screen.waitProgress();
		screen.click(okButton, "OK button");
		
		/*Update the temp MD5 password '1234' to the user*/
		DatabaseUtils sql = new DatabaseUtils("jdbc:jtds:sqlserver://192.168.1.101:1433;DatabaseName=LN_DL_SubMgmt_P2", "sa", "Password1");
		String updateSQL = "UPDATE tblUser SET password = '" + tempMD5 + "' where EmailAddress = '" + userName + "' and IsActive = 1";
		sql.updateSQL(updateSQL);
		report.log("Update temp password successfully");
		sql.close();
		/*Use the temp MD5 password to login */
		screen.click(country, "country");
		screen.click(australia, "Australia");
		screen.input(email, userName, "username");
		screen.input(password, "1234", "password");
		screen.click(loginButton, "login");
		screen.waitProgress();
		/*Remember new password input to changge box*/
		screen.input(newPwd, newPassword, "input the new password");
		screen.input(retypePwd, newPassword, "retype the new password");
		//screen.click(rem, "select remember password");
		screen.click(send, "Send the new password");
		screen.waitProgress();
		screen.click(okButton, "OK button");
		//screen.click(send, "Send the new password");
		//screen.waitProgress();
		//screen.click(okButton, "OK button");
        /* Logout */
		screen.waitProgress();
		screen.click(settings, "Settings button");
		screen.click(logout, "Logout");
		screen.click(confirm, "Confirm logout");
		/*Login again without input password*/
		screen.click(loginButton, "login");
		screen.waitProgress();
		
		screen.click(settings, "Settings button");
		screen.click(logout, "Logout");
		screen.click(confirm, "Confirm logout");
	}
	
	public void tempTest(String userName) {
		
		/*Go to forgot screen to send a request to database*/
		screen.click(forgotButton, "Go to forgot password screen");
		screen.input(email, userName, "input the email for forgot password");
		screen.click(submitButton, "submit button");
		screen.waitProgress();
		screen.click(okButton, "OK button");
		//screen.click(submitButton, "submit button");
		//screen.waitProgress();
		//screen.click(okButton, "OK button");
		
		
		//DatabaseUtils sql = new DatabaseUtils("jdbc:jtds:sqlserver://192.168.1.101:1433;DatabaseName=LN_DL_SubMgmt_P2", "sa", "Password1");
		//String selectSQL = "select * from tblUser where EmailAddress = 'nikitta@lexisred.com' and IsActive = 1";
		//String tempPassword = sql.querySQL(selectSQL).getString("Password");
		//if (tempPassword.contains("") || tempPassword.contains("") || tempPassword.contains("") || tempPassword.contains(""));
		//{
			//Assert.fail();
			//report.log("test failed");
		//}

	}

}
