package com.appium.Android.TestCaseNew;


import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.DeleteGroupPage;
import com.appium.Android_PageNew.DocumentNamePage;
import com.appium.Android_PageNew.ExpandGroupPage;
import com.appium.Android_PageNew.FilterPubInGroupPage;
import com.appium.Android_PageNew.HVTitleNamePage;
import com.appium.Android_PageNew.InternetMsgPage;
import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.PublicationPage;
import com.appium.Android_PageNew.PublicationSearchPage;
import com.appium.Android_PageNew.RemPasswordInChangePage;
import com.appium.Android_PageNew.RememberPage;
import com.appium.Android_PageNew.RememberPasswordPage;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.RedAndroid;

public class ALLTest {
	
	private RedAndroid screen;
	private ExcelUtils excel;
	private InternetMsgPage internetMsg;
	private RememberPasswordPage rememberPassword;
	private LoginPage login;
	private RememberPage remember;
	private RemPasswordInChangePage remPasswordInChange;
	private DocumentNamePage documentName;


	@Test (priority = 1)
	public void internetMsg() {
		
		internetMsg = new InternetMsgPage(screen);
		internetMsg.skipTour();
		internetMsg.internetMsgTest("nikitta@lexisred.com", "123465");
	
	}
	
	@Test (priority = 2)
	public void remPasswordTest() {
		
		
		rememberPassword = new RememberPasswordPage(screen);
		rememberPassword.remPasswordTest("kenny@lexisred.com", "1234");
	}
	
	//@Test (priority = 3)
	//public void rememberPasswordDeselect() {
		
		//login = new LoginPage(screen);
		//login.skipTour();
		//login.remember("dieson@lexisred.com", "dieson", "Australia");
	//}
	
	
	@Test (priority = 4)
	public void rememberPasswords() {
		
		login = new LoginPage(screen);
		remember = new RememberPage(screen);
		login.skipTour();
		remember.rememberPasswordA("echo.hu@lexisnexis.com", "qwer", "Australia");
		remember.rememberPasswordB("nikitta@lexisred.com", "123465", "Australia");
		remember.rememberPasswordC("echo.hu@lexisnexis.com", "qwer", "Australia");
		remember.rememberPasswordD("nikitta@lexisred.com", "123465", "Australia");
	}
	
	@Test (priority = 5)
	public void remPasswordInChangeTest() {
		
		
		remPasswordInChange = new RemPasswordInChangePage(screen);
		remPasswordInChange.remPasswordInChangeTest("kenny@lexisred.com","81dc9bdb52d04dc20036dbd8313ed055","1234");
	}
	
	@Test (priority = 6)
	public void documentNameTest() {
		
		documentName = new DocumentNamePage(screen);
		documentName.documentNameTest();
	
	}
	
	//@Test (priority = 7)
	//public void previousNextTest() {
		
		//TOCPage toc = new TOCPage(screen);
		//toc.previousNext();
	//}
	
	@Test(priority = 8, dataProvider = "dp")
	public void searchTest(Map<String, Object> data) {
		
		PublicationSearchPage search = new PublicationSearchPage(screen, data, excel);
		search.search();
	}
	
	@Test (priority = 9)
	public void groupTest() {
		
		PublicationPage publication = new PublicationPage(screen);
		publication.checkSingle();
		publication.checkGroup();
	}
	
	@Test (priority = 10)
	public void hvTitlNameTest() {
		
		
		HVTitleNamePage hvTitleName = new HVTitleNamePage(screen);
		hvTitleName.hvTitleNameTest();
	}
	
	@Test (priority = 11)
	public void expandGroupTest() {
		
		
		ExpandGroupPage expandGroup = new ExpandGroupPage(screen);
		expandGroup.expandGroupTest();
	}
	
	@Test (priority = 12)
	public void filterPubInGroupTest() {
		
		
		FilterPubInGroupPage filterG = new FilterPubInGroupPage(screen);
		filterG.filterPubInGroupTest();
	}
	
	@Test (priority = 13)
	public void deletegroup() {
		
		login = new LoginPage(screen);
		login.skipTour();
		DeleteGroupPage deletegroup = new DeleteGroupPage(screen);
		deletegroup.deletegroup();
	}
	
	@BeforeTest
	public void beforeMethod() {
		
		screen = new RedAndroid();
	}

	@AfterTest
	public void afterMethod() {
		screen.quit();
	}

	@DataProvider(name = "dp")
	public Iterator<Object[]> testData() throws Exception {
		excel = new ExcelUtils("Android.xls", "Search");
		return excel;
	}
}
