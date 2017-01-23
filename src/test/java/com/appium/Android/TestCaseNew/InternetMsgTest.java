package com.appium.Android.TestCaseNew;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.appium.Android_PageNew.InternetMsgPage;
import com.appium.driver.RedAndroid;

public class InternetMsgTest {
	
	private RedAndroid screen;
	private InternetMsgPage internetMsg;

	@Test
	public void internetMsg() {
		
		internetMsg = new InternetMsgPage(screen);
		internetMsg.skipTour();
		internetMsg.internetMsgTest("nikitta@lexisred.com", "123465");
	}	
	
	@BeforeTest
	public void beforeTest() {
		screen = new RedAndroid();
	}

	@AfterTest
	public void afterTest() {
		screen.quit();
	}

	
}
