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

public class InformationPage extends BaseScreen {
	private static final Properties INFORMATION_PAGE = new PropertyUtils()
			.loadProperties("/Android_page/DeletePage.properties");

	private final String filter = INFORMATION_PAGE
			.getProperty("ANDROID_FILTER");
	private final String filterSub = INFORMATION_PAGE
			.getProperty("ANDROID_FILTERSUB");
	private final String filterloan = INFORMATION_PAGE
			.getProperty("ANDROID_FILTERLOAN");
	private final String downloadStatus = INFORMATION_PAGE
			.getProperty("ANDROID_DOWNLOADSTATUS");
	private final String title = INFORMATION_PAGE
			.getProperty("ANDROID_TITLE");
	private final String whatNew = INFORMATION_PAGE
			.getProperty("ANDROID_NEW");
	private final String version = INFORMATION_PAGE
			.getProperty("ANDROID_VERSION");
	private final String openButton = INFORMATION_PAGE
			.getProperty("ANDROID_OPENBUTTON");
	private final String infoTitle = INFORMATION_PAGE
			.getProperty("ANDROID_INFOTITLE");
	private final String downloadDate = INFORMATION_PAGE
			.getProperty("ANDROID_DOWNLOADDATE");
	private final String infoButton = INFORMATION_PAGE
			.getProperty("ANDROID_INFOBUTTON");
	private final String contentTitle = INFORMATION_PAGE
			.getProperty("ANDROID_CONTENTTITLE");
	private final String back = INFORMATION_PAGE
			.getProperty("ANDROID_BACK");

	public InformationPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	public InformationPage(RedAndroid screenUtils,
			Map<String, Object> dataDriver, ExcelUtils excelUtils) {
		screen = screenUtils;
		data = dataDriver;
		excel = excelUtils;
	}

	public void informationSub() {
		// Subscription information
		screen.click(filter, "Filter Options");
		screen.click(filterSub, "Filter Subscription");
		String titleNameSub = screen.getText(title, "Book Name");

		if (screen.getText(downloadStatus, "Download Status").equals("Up to date")) {
			screen.click(infoButton, "Information Model");
		} else {
			report.log("Did not download!");
			Assert.fail();
		}

		String titleVerifySub = screen.getText(infoTitle, "Information Title");
		String versionVerifySub = screen.getText(version, "Information Version");
		String dateVerifySub = screen.getText(downloadDate,	"Information Install date");
		String infoNewSub = screen.getText(whatNew, "What's New");

		screen.click(openButton, "Information Open");
		screen.waitProgress();
		String contentTitleVerifySub = screen.getText(contentTitle, "Content Page Name");
		screen.clear(back, "Back Button");
	}

	public void informationLoan() {
		// Loan information
		screen.click(filter, "Filter Options");
		screen.click(filterloan, "Filter Subscription");
		String titleNameLoan = screen.getText(title, "Book Name");

		if (screen.getText(downloadStatus, "Download Status").equals(
				"Up to date")) {
			screen.click(infoButton, "Information Model");
		} else {
			report.log("Did not download!");
			Assert.fail();
		}

		String titleVerifyLoan = screen.getText(infoTitle, "Information Title");
		String versionVerifyLoan = screen.getText(version,
				"Information Version");
		String dateVerifyLoan = screen.getText(downloadDate,
				"Information Install date");
		String infoNewLoan = screen.getText(whatNew, "What's New");

		screen.click(openButton, "Information Open");
		screen.waitProgress();
		String contentTitleVerifyLoan = screen.getText(contentTitle,
				"Content Page Name");
		screen.clear(back, "Back Button");
	}
	
	public String getVersion(String title){
		//"SELECT LastUpdatedDate FROM tblDL where DLTitle = '" + title + "' and IsActive = 1"
		//SELECT UserID FROM tblUser where EmailAddress = ''
		DatabaseUtils sql = new DatabaseUtils("jdbc:jtds:sqlserver://192.168.1.101:1433;DatabaseName=LN_DL_SubMgmt_P2", "sa", "Password1");
		String version = "";
		try {
			ResultSet set = sql.querySQL("SELECT LastUpdatedDate FROM tblDL where DLTitle = '" + title + "' and IsActive = 1");
			while (set.next()) {
				version = set.getString("LastUpdatedDate");
			}
			sql.close();
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return version;
	}

}
