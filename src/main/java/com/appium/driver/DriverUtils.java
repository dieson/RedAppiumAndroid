package com.appium.driver;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.appium.utils.ReportUtils;
import com.appium.utils.Utils;


public class DriverUtils extends Driver{
	public TouchAction action;
	ReportUtils report = new ReportUtils();
	
	/**
	 * wait -- session
	 * @param i second
	 */
	public void sleep(int i){
		try {
			Thread.sleep(i * 1000);
		} catch (Exception e) {
			report.log(e.toString());
		}
	}
	
	/**
	 * Wait -- driver
	 * @param i second
	 */
	public void wait(int i){
		driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);  
	}
	
	/**
	 * Sreenshot
	 */
	public void screenshot(String imageName) {
		String imagePath = "";
		if (Utils.getOs().equals("LINUX")) {
			imagePath = System.getProperty("user.dir") + "/screenshot/" + imageName + ".png";
		}else{
			imagePath = System.getProperty("user.dir") + "\\screenshot\\" + imageName + ".png";
		}
		
		File imageFile = new File(imagePath);
		// support for scrolling
		File source_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// save image as 
		try {
			FileUtils.copyFile(source_file, imageFile);
			report.screenLog(imagePath);
		} catch (Exception e) {
			e.printStackTrace();
			report.errorLog("[Fail] Screenshot");
		}
	}
	
	/**
	 * Switch to alert
	 * @return
	 */
	public Alert alertSwitch(){
		Alert alert = null;
		try {
			alert = driver.switchTo().alert();
			report.log("[Successful] Switch to alert");
		} catch (Exception e) {
			screenshot("Alert");
			report.errorLog("[Fail] Unable switch to alert");
			report.errorLog(e.toString());
		}
		return alert;
	}
	
	/**
	 * Get alert and accept
	 * @return
	 */
	public String alertGetText(){
		Alert alert = alertSwitch();
		String text = "";
		
		try {
			text = alert.getText();
			report.log("[successful] Get alert: " + text);
		} catch (Exception e) {
			screenshot("Alert");
			report.errorLog("[Fail] Unable get text");
			report.errorLog(e.toString());
		}
		
		try {
			alert.accept();
			report.log("[Successful] Accept");
		} catch (Exception e) {
			screenshot("Alert");
			report.errorLog("[Fail] Unable accept");
			report.errorLog(e.toString());
		}
		return text;
	}
	
	/**
	 * Alert accept
	 */
	public void alertAccept(){
		Alert alert = alertSwitch();
		try {
			alert.accept();
			report.log("[Successful] Accept");
		} catch (Exception e) {
			screenshot("Alert");
			report.errorLog("[Fail] Unable accept");
			report.errorLog(e.toString());
		}
	}
	
	/**
	 * Alert dismiss
	 */
	public void alertDismiss(){
		Alert alert = alertSwitch();
		try {
			alert.dismiss();
			report.log("[Successful] Dismiss");
		} catch (Exception e) {
			screenshot("Alert");
			report.errorLog("[Fail] Unable dismiss");
			report.errorLog(e.toString());
		}
	}
	
	/**
	 * Slide
	 * @param xStart	Start the x axis
	 * @param yStart	Start the y axis
	 * @param xEnd		End the x axis
	 * @param yEnd		End the y axis
	 * @param comment	
	 */
	public void swipe(int xStart, int yStart, int xEnd, int yEnd, String comment){
		try{
			driver.swipe(xStart, yStart, xEnd, yEnd, 3000);
			report.log("[Successful] Slide: " + comment + " ("+xStart+","+yStart+") => ("+xEnd+","+yEnd+")");
		}catch(Exception e){
			screenshot(comment);
			report.errorLog("[Fail] Unable slide");
			report.errorLog(e.toString());
		}
	}
	
	public void scrollTo(String text, String comm){
		try {
			driver.scrollTo(text);
			report.log("[Successful] Slide: Scroll to " + text);
		} catch (Exception e) {
			screenshot(comm);
			report.errorLog("[Fail] Unable slide");
			report.errorLog(e.toString());
		}
	}
	
	public void sendKey(String locator, int value, String elementName) {
		try {
			((AndroidDriver)driver).sendKeyEvent(value);
			report.log("[Successful] " + elementName + " send:" + value);
		} catch (Exception e) {
			screenshot(elementName);
			report.errorLog("[Fail] Unable to send key");
			Assert.fail();
		}
	}
	
	public void press(int x, int y){
		try {
			action = new TouchAction(driver);
			action.press(x, y);
			report.log("[Successful] Click the coordinate X:" + x + " Y:" + y);
		} catch (Exception e) {
			screenshot("Press");
			report.errorLog("[Fail] Unable to click the coordinate");
			Assert.fail();
		}
	}
	
	public void tap(int x, int y){
		try {
			driver.tap(1, x, y, 200);
			report.log("[Successful] Click the coordinate X:" + x + " Y:" + y);
		} catch (Exception e) {
			/*screenshot("Press");
			report.errorLog("[Fail] Unable to click the coordinate");
			Assert.fail();*/
		}		
	}
}
