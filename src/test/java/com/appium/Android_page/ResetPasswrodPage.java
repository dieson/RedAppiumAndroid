package com.appium.Android_page;

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

public class ResetPasswrodPage extends BaseScreen{
	private static final Properties RESETPASSWORD_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_page/ResetPasswordPage.properties");
	
	private final String resetpassword = RESETPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_RESETPASSWORD");
	private final String email = RESETPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_EMAIL");
	private final String country = RESETPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_COUNTRY");
	private final String send = RESETPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_SEND");
	private final String newpassword = RESETPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_NEWPASSWORD");
	private final String retype = RESETPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_RETYPE");
	private final String done = RESETPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_DONE");
	private final String alert = RESETPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_ALERT");
	private final String ok = RESETPASSWORD_PAGE_PROPERTIES.getProperty("ANDROID_OK");
	
	public ResetPasswrodPage(RedAndroid screenUtils, Map<String, Object> dataDriver, ExcelUtils excelUtils){
		screen = screenUtils;
		data = dataDriver;
		excel = excelUtils;
	}
	
	/**
	 * Reset password
	 */
	public void resetPassword()
	{
		DatabaseUtils sql = new DatabaseUtils("jdbc:jtds:sqlserver://192.168.1.101:1433;DatabaseName=LN_DL_SubMgmt_P2", "sa", "Password1");
		String passwordData = "";
		
		screen.click(resetpassword, "resetPassword");
		screen.input(email, data.get("resetusername").toString(), "Email");
		screen.click(country, "country");
		screen.click("NAME:" + data.get("resetcountry").toString(), "Country");
		screen.click(send, "send");
		screen.sleep(8);
		String resetMsg = screen.getText(alert, "Alert");
		screen.click(ok, "OK");
		writeExcel("resetMsg", resetMsg);
		
		try {
			ResultSet set = sql.querySQL("select * from tblUser where EmailAddress = '" + data.get("resetusername").toString() + "' and IsActive = 1");
			while (set.next()) {
				passwordData = set.getString("password");
			}
			sql.close();
		} catch (SQLException e) {
			report.log("[Fail] Unable get password");
			Assert.fail();
		}
		writeExcel("resetpassword", passwordData);
	}
	
	/**
	 * change the password
	 */
	public void changePassword(){
		screen.input(newpassword, data.get("changepassword").toString(), "newPassword");
		screen.input(retype, data.get("changepassword").toString(), "re-type");
		screen.click(done, "Done");
		String changeMsg = screen.getText(alert, "Alert");
		screen.click(ok, "OK");
		writeExcel("changeMsg", changeMsg);
	}
}
