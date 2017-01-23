package com.appium.Android_testcase;

import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_page.DownloadPage;
import com.appium.Android_page.FilterPublicationPage;
import com.appium.Android_page.IndexPage;
import com.appium.Android_page.LoginPage;
import com.appium.Android_page.LogoutPage;
import com.appium.Android_page.RecentHistoryPage;
import com.appium.Android_page.SearchPage;
import com.appium.Android_page.TOCPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;
import com.appium.utils.ReportUtils;

public class Demo {
	private RedAndroid screen;
	private ExcelUtils excel;
	private LoginPage login;
	private String userName;
	private String passWord;
	private String country;
	private String titleName;
	ReportUtils report = new ReportUtils();

	@Parameters({ "userName", "passWord", "country", "titleName" })
	@BeforeTest
	public void beforeTest(String userName, String passWord, String country,
			String titleName) {
		screen = new RedAndroid();
		this.userName = userName;
		this.passWord = passWord;
		this.country = country;
		this.titleName = titleName;
	}

	@DataProvider(name = "dp")
	public Iterator<Object[]> testData() throws Exception {

		excel = new ExcelUtils("Android.xls", "All");
		return excel;
	}

	@Test(dataProvider = "dp")
	public void login(Map<String, Object> data) {
		report.log("=========================Login=============================");
		login = new LoginPage(screen, data, excel);
		login.skipTour();
		login.loginAndroidTest();
	}

	@Test(dependsOnMethods = "login")
	public void download() {
		report.log("=========================Download=============================");
		login.loginAndroid(userName, passWord, country);
		DownloadPage download = new DownloadPage(screen);
		download.downloadAll();
	}

	@Test(dataProvider = "dp", dependsOnMethods = "download")
	public void contact(Map<String, Object> data) {
		if (data.get("contactIcon").toString() != null
				&& data.get("contactIcon").toString() != "") {
			report.log("=========================Contact=============================");
			login.contactAndScrollTest(userName, passWord, country);
		}
	}

	@Test(dependsOnMethods = "contact")
	public void filterPublication() {
		report.log("=========================Filter Publication=============================");
		FilterPublicationPage filter = new FilterPublicationPage(screen);
		filter.filterPublication();
	}

	/*@Test(dependsOnMethods = "filterPublication")
	public void checkExpiredDate() {
		report.log("=========================Check Expired Date=============================");
		FilterPublicationPage filter = new FilterPublicationPage(screen);
		filter.expiredDate(userName);
	}

	@Test(dependsOnMethods = "login")
	public void checkFTC() {
		report.log("=========================Check FTC=============================");
		FilterPublicationPage filter = new FilterPublicationPage(screen);
		filter.checkFTC();
	}

	@Test(dataProvider = "dp", dependsOnMethods = "login")
	public void gotoPageTest(Map<String, Object> data) {
		if (data.get("PageNO").toString() != null
				&& data.get("PageNO").toString() != "") {
			report.log("=========================Go to page=============================");
			GoToPage gotoPage = new GoToPage(screen, data, excel);
			gotoPage.gotoPage(titleName);
		}
	}*/

	@Test(dependsOnMethods = "filterPublication")
	public void index() {
		report.log("=========================index=============================");
		IndexPage index = new IndexPage(screen);
		index.index();
	}

	@Test(dataProvider = "dp", dependsOnMethods = "index")
	public void recentHistory(Map<String, Object> data) {
		if (data.get("TocName").toString() != null
				&& data.get("TocName").toString() != "") {
			report.log("=========================ReCent History=============================");
			RecentHistoryPage recentHistory = new RecentHistoryPage(screen,
					data, excel);
			recentHistory.recentHisotry();
		}
	}

	@Test(dependsOnMethods = "recentHistory")
	public void TOCHide() {
		TOCPage toc = new TOCPage(screen);
		toc.TOCHide();
	}

	@Test(dataProvider = "dp", dependsOnMethods = "TOCHide")
	public void search(Map<String, Object> data) {
		if (data.get("search_text").toString() != null
				&& data.get("search_text").toString() != "") {
			report.log("=========================search=============================");
			SearchPage search = new SearchPage(screen, data, excel);
			search.searchTest();
		}
	}
	
	/*@Test(dependsOnMethods = "search")
	public void addAnnotations() {
	report.log("=========================Add Annotations=============================");
		AnnotationsPage annotations = new AnnotationsPage(screen);
		annotations.add();
	}

	@Test(dependsOnMethods = "addAnnotations")
	public void filterAnnotaions() {
		report.log("=========================Filter Annotaions=============================");
		AnnotationsPage annotations = new AnnotationsPage(screen);
		annotations.filter();
	}*/

	@Test(dependsOnMethods = "search")
	public void logout() {
		report.log("=========================logout=============================");
		LogoutPage logoutPage = new LogoutPage(screen);
		logoutPage.logoutTestAndroid();
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}

}
