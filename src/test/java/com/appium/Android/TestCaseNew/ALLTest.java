package com.appium.Android.TestCaseNew;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.AnnotationEditPage;
import com.appium.Android_PageNew.AnnotationsPage;
import com.appium.Android_PageNew.DownloadPage;
import com.appium.Android_PageNew.GoToPage;
import com.appium.Android_PageNew.LoginPage;
import com.appium.Android_PageNew.LogoutPage;
import com.appium.Android_PageNew.PublicationPage;
import com.appium.Android_PageNew.TOCPage;
import com.appium.driver.RedAndroid;

public class ALLTest {

	private RedAndroid screen;
	private TOCPage toc;
	private AnnotationEditPage annotation;

	@Test
	public void download() {

		DownloadPage download = new DownloadPage(screen);
		download.downloadAll();
	}

	@Test(dependsOnMethods = "download")
	public void groupTest() {

		PublicationPage publication = new PublicationPage(screen);
		publication.groupSingle();
	}

	@Test(dependsOnMethods = "groupTest")
	public void hideTOCTest() {
		screen.offGroup();
		toc = new TOCPage(screen);
		toc.hideTOC();
	}

	@Test(dependsOnMethods = "hideTOCTest")
	public void previousNextTest() {
		toc.previousNext();
	}

	@Test(dependsOnMethods = "previousNextTest")
	public void addAnnotationTest() {
		annotation = new AnnotationEditPage(screen);
		annotation.addAnnotation();
	}

	@Test(dependsOnMethods = "addAnnotationTest")
	public void addNoteTagTest() {
		annotation.addNoteTag();
	}

	@Test(dependsOnMethods = "addNoteTagTest")
	public void editNoteTagTest() {
		annotation.editNoteTag();
	}
	
	@Test(dependsOnMethods = "editNoteTagTest")
	public void annotation() {
		AnnotationsPage annotations = new AnnotationsPage(screen);
		annotations.searchAnnotations("updateds");
	}
	
	@Parameters({ "userName", "passWord", "country" })
	@BeforeTest
	public void beforeTest(String userName, String passWord, String country) {

		screen = new RedAndroid();
		LoginPage login = new LoginPage(screen);
		login.loginAndroid(userName, passWord, country);
	}

	@AfterTest
	public void afterTest() {
		LogoutPage logout = new LogoutPage(screen);
		logout.logoutAndroid();
		screen.quit();
	}

}
