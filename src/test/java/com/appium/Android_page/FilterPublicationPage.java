package com.appium.Android_page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.datautils.DatabaseUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;
import com.appium.utils.Utils;

public class FilterPublicationPage extends BaseScreen {
	private static final Properties FILTERPUBLICATION_PAGE_PROPERTIES = new PropertyUtils()
			.loadProperties("/Android_page/FilterPublicationPage.properties");

	private final String filterList = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_FILTERLIST");
	private final String loan = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_LOAN");
	private final String subscription = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_SUBSCRIPTION");
	private final String publicationList = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_PUBLICATIONLIST");
	private final String loanIcon = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_LOANICON");
	private final String expiredDate = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_EXPIREDDATE");
	private final String titleName = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_TITLENAME");
	private final String ftcIcon = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_FTCICON");
	private final String tocList = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_TOCLIST");
	private final String backButton = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_BACKBUTTON");
	private final String tocName = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_TOCNAME");
	private final String all = FILTERPUBLICATION_PAGE_PROPERTIES
			.getProperty("ANDROID_ALL");

	public FilterPublicationPage(RedAndroid screenUtil) {
		screen = screenUtil;
	}

	/**
	 * Filter the type of the publication
	 * 
	 * @return
	 */
	public void filterPublication() {
		screen.click(filterList, "Filter List");
		screen.click(loan, "loan the book");
		List<WebElement> loanList = screen.findElements(
				screen.findElement(publicationList), loanIcon);

		screen.click(filterList, "Filter List");
		screen.click(subscription, "subscription the book");
		List<WebElement> subscriptionList = screen.findElements(
				screen.findElement(publicationList), loanIcon);
		
		screen.click(filterList, "Filter List");
		screen.click(all, "all the publications");

		Assert.assertEquals(loanList.isEmpty(), false);
		Assert.assertEquals(subscriptionList.isEmpty(), true);
	}

	/**
	 * Check the expired date
	 */
	public void expiredDate(String userName) {
		Map<String, String> dateActual = new HashMap<>();
		Map<String, String> dateExpect = new HashMap<>();
		List<String> bookName = new ArrayList<>();
		screen.click(filterList, "Filter List");
		screen.click(loan, "loan the book");

		// Get expiration date on the screen
		List<WebElement> bookList = screen.findElements(publicationList);
		for (WebElement element : bookList) {
			report.log("Book Name:"
					+ screen.getText(screen.findElement(element, titleName),
							"Title Name"));
			report.log("Expired Time:"
					+ screen.getText(screen.findElement(element, expiredDate),
							"Expired Time"));
			bookName.add(screen.getText(screen.findElement(element, titleName),
					"Title Name"));
			dateActual.put(screen.getText(
					screen.findElement(element, titleName), "Title Name"),
					screen.getText(screen.findElement(element, expiredDate),
							"Expired Time"));
		}
		screen.click(filterList, "Filter List");
		screen.click(all, "all the publications");

		// Get database of the expiration date
		DatabaseUtils sql = new DatabaseUtils(
				"jdbc:jtds:sqlserver://192.168.1.101:1433;DatabaseName=LN_DL_SubMgmt_P2",
				"sa", "Password1");
		try {
			for (int i = 0; i < bookName.size(); i++) {
				String sqlStr = "select top 1 ValidTo from tblEntitlements where UserID in (select UserID from tblUser where EmailAddress = '"
						+ userName
						+ "' and IsActive = '1')"
						+ " and DLID in (SELECT DLID FROM tblDL where DLTitle = '"
						+ Utils.singleReplace(bookName.get(i))
						+ "' and IsActive = '1')"
						+ " order by EntitlementId desc";
				ResultSet set = sql.querySQL(sqlStr);
				while (set.next()) {
					report.log("Book Name:" + bookName.get(i));
					report.log("Database Time:" + set.getString("ValidTo"));
					dateExpect.put(bookName.get(i), set.getString("ValidTo"));
				}
			}
			sql.close();
		} catch (SQLException e) {
			report.log("[Fail] Unable get Expired Date");
			Assert.fail();
		}

		// Check the expired time
		for (String name : bookName) {
			String dataTime = Utils.getExpiredTime(dateExpect.get(name)) + "";
			Assert.assertEquals(dateActual.get(name).contains(dataTime), true);
		}
	}

	/**
	 * Check the FTC publication
	 */
	public void checkFTC() {
		// Get the FTC publication
		String xpath = "";
		boolean isCase = false;
		WebElement FTC = null;
		List<WebElement> bookList = screen.findElements(publicationList);
		for (int i = 0; i < bookList.size(); i++) {
			if (screen.getText(screen.findElement(bookList.get(i), titleName),
					"Book Name").contains("+ Cases")) {
				xpath = "XPATH://android.support.v7.widget.RecyclerView/android.widget.LinearLayout["+ ( i + 1 )+ "]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ImageView";
				FTC = bookList.get(i);
			}
		}

		// Check the FTC icon
		if (screen.isExistElement(FTC, ftcIcon)) {
			report.log("[Successful] The publication has FTC icon");
		} else {
			report.log("[Fail] The publication has no FTC icon");
			Assert.fail();
		}

		// Get the +Cases TOC
		screen.click(xpath, "FTC Title");
		screen.waitProgress();
		List<WebElement> tocListI = screen.findElements(tocList);
		for (WebElement element : tocListI) {
			if (screen
					.getText(screen.findElement(element, tocName), "TOC name")
					.contains("+ Cases")) {
				isCase = true;
			}
		}

		// Check the TOC
		if (isCase) {
			report.log("[Successful] The TOC list has FTC +Cases");
		} else {
			report.log("[Fail] The TOC list has no FTC +Cases");
			Assert.fail();
		}
		screen.click(backButton, "Back Button");
	}

}
