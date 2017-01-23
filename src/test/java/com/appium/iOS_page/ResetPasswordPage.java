package com.appium.iOS_page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

import com.appium.datautils.DatabaseUtils;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

public class ResetPasswordPage extends BaseScreen{
	private static final Properties RESETPASSWORD_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/iOS_page/ResetPasswordPage.properties");

	private static final String resetpassword = RESETPASSWORD_PAGE_PROPERTIES.getProperty("IOS_RESETPASSWORD");
	private static final String email = RESETPASSWORD_PAGE_PROPERTIES.getProperty("IOS_EMAIL");
	private static final String country = RESETPASSWORD_PAGE_PROPERTIES.getProperty("IOS_COUNTRY");
	private static final String send = RESETPASSWORD_PAGE_PROPERTIES.getProperty("IOS_SEND");
	private static final String newpassword = RESETPASSWORD_PAGE_PROPERTIES.getProperty("IOS_NEWPASSWORD");
	private static final String retype = RESETPASSWORD_PAGE_PROPERTIES.getProperty("IOS_RETYPE");
	private static final String done = RESETPASSWORD_PAGE_PROPERTIES.getProperty("IOS_DONE");
	
	public ResetPasswordPage(RedAndroid screenUtils, Map<String, Object> dataDriver, ExcelUtils excelUtils){
		screen = screenUtils;
		data = dataDriver;
		excel = excelUtils;
	}
	
	/**
	 * Reset password
	 */
	public void resetPassword(){
		DatabaseUtils sql = new DatabaseUtils("jdbc:jtds:sqlserver://192.168.1.101:1433;DatabaseName=LN_DL_SubMgmt_P2", "sa", "Password1");
		String passwordData = "";
		
		screen.click(resetpassword, "resetpassword");
		screen.input(email, data.get("userName"), "Email");
		screen.input(country, data.get("country"), "country");
		screen.click(send, "send");
		screen.sleep(8);
		String resetMsg = screen.alertGetText();
		writeExcel("resetMsg", resetMsg);
		
		try {
			ResultSet set = sql.querySQL("select * from tblUser where EmailAddress = 'ran.zuo@lexisnexis.com' and IsActive = 1");
			while (set.next()) {
				passwordData = set.getString("Password");
			}
			sql.close();
		} catch (SQLException e) {
			report.log("[Fail] Unable get password");
			Assert.fail();
		}
		writeExcel("resetPassword", passwordData);
	}
	
	/**
	 * Change password
	 */
	public void changePassword(){
		screen.input(newpassword, data.get("password"), "newPassword");
		screen.input(retype, data.get("password"), "re-type");
		screen.click(done, "Done");
		
		String changeMsg = screen.alertGetText();
		writeExcel("changeMsg", changeMsg);
	}

}
