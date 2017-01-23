package com.appium.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.appium.utils.OsUtils;
import com.appium.utils.ReportUtils;
import com.appium.utils.Utils;

/**
 * Start driver
 * @author Dieson Zuo
 */
public class Driver {
	public AppiumDriver driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	ReportUtils report = new ReportUtils();

	/**
	 * Start driver
	 */
	public Driver() {

		if (Utils.getSystemProperties("SYSTEM").equals("ANDROID")) {
			startAndroid();
		} else if(Utils.getSystemProperties("SYSTEM").equals("IOS")){
			startIOS();
		}else{
			report.log("Unable find the platform!");
			Assert.fail();
		}

	}

	/**
	 * Start Android driver
	 */
	public void startAndroid() {
		try {
			String path = System.getProperty("user.dir") + Utils.getSystemProperties("APPANDROID");

			capabilities.setCapability("deviceName", OsUtils.getDeviceName());
			capabilities.setCapability("platformVersion", Utils.getSystemProperties("PLATFORMVERSIONANDROID"));
			capabilities.setCapability("platformName", Utils.getSystemProperties("PLATFORMNAMEANDROID"));
			capabilities.setCapability("appPackage", Utils.getSystemProperties("APPPACKAGEANDROID"));
			capabilities.setCapability("appActivity", Utils.getSystemProperties("APPACTIVITYANDROID"));
			capabilities.setCapability("app", path);
			capabilities.setCapability("unicodeKeyboard", "true");
			capabilities.setCapability("resetKeyboard", "true");
			
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.rotate(ScreenOrientation.PORTRAIT);
		} catch (Exception e) {
			report.errorLog(e.toString());
		}

	}

	/**
	 * Start IOS driver
	 */
	public void startIOS() {
		try {
			String path = System.getProperty("user.dir") + Utils.getSystemProperties("APPIOS");

			capabilities.setCapability("deviceName", Utils.getSystemProperties("DEVICENAMEIOS"));
			capabilities.setCapability("platformVersion", Utils.getSystemProperties("PLATFORMVERSIONIOS"));
			capabilities.setCapability("platformName", Utils.getSystemProperties("PLATFORMNAMEIOS"));
			capabilities.setCapability("app", path);
			capabilities.setCapability("unicodeKeyboard", "true");
			capabilities.setCapability("resetKeyboard", "true");

			driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (Exception e) {
			report.errorLog(e.toString());
		}
	}

	/**
	 * Quit app.
	 */
	public void quit() {
		try {
			driver.quit();
			report.log("Quit the app.");
		} catch (Exception e) {
			report.errorLog("[Faile] Quit the app.");
			report.errorLog(e.toString());
		}
	}

	/**
	 * Get the driver.
	 * 
	 * @return
	 */
	public AppiumDriver getDriver() {
		return driver;
	}
	
	/**
	 * Wait -- driver
	 * @param i second
	 */
	public void wait(int i){
		driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);  
	}

}
