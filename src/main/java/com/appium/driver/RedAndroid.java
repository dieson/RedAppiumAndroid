package com.appium.driver;

import org.openqa.selenium.WebElement;

public class RedAndroid extends ElementUtils{
	
	public void waitProgress(){
		WebElement element = null;
		//system progress bar id is 'android:id/body'
		try {
			do{
				element = driver.findElementById("au.com.lexisnexis.lexisred.preview:id/pbLoading");
				wait(1);
			}while(element != null);
		} catch (Exception e) {
		}
	}
	
	public boolean isDownloaded(){
		if(isExistElement("XPATH://android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ImageView[2]")){
			//did not download
			return false;
		}else {
			//downloaded
			return true;
		}
	}
	
	public void waitLogin(){
		WebElement element = null;
		try{
			do{
				element = driver.findElementById("LexisNexis.Red.Android:id/btnLogin");
				wait(1);
			}while(element == null);
		}catch (Exception e) {
		}
	}
	
	public void findPublication(String titleName){
		while(!isExistElement("NAME:" + titleName)){
			super.swipe(2400, 800, 240, 800, "Find Publication");
		}
	}
	
	public boolean findGroup() {
		
		boolean isExist = false;
		int i = 0;
		do {
			if(!super.isExistElement("ID:au.com.lexisnexis.lexisred.preview:id/ivFolder")) {
				super.swipe(2400, 800, 240, 800, "Find Group");
			} else {
				isExist = true;
				break;
			}
			i++;
		} while (i < 3);
		
		return isExist;
	}
	
	public void onGroup() {
		report.log("Open group mode.");
		driver.findElementByXPath("//android.support.v7.widget.LinearLayoutCompat/android.widget.ImageView").click();
		driver.findElementByXPath("//android.widget.LinearLayout[4]").click();
		if (!driver.findElementById("au.com.lexisnexis.lexisred.preview:id/ivCheckBox").isSelected()) {
			driver.findElementById("au.com.lexisnexis.lexisred.preview:id/ivCheckBox").click();
		}
		driver.findElementByXPath("//android.widget.LinearLayout/android.view.View/android.widget.ImageButton").click();
	}
	
	public void offGroup() {
		report.log("Off group mode.");
		driver.findElementByXPath("//android.support.v7.widget.LinearLayoutCompat/android.widget.ImageView").click();
		driver.findElementByXPath("//android.widget.LinearLayout[4]").click();
		if (driver.findElementById("au.com.lexisnexis.lexisred.preview:id/ivCheckBox").isSelected()) {
			driver.findElementById("au.com.lexisnexis.lexisred.preview:id/ivCheckBox").click();
		}
		driver.findElementByXPath("//android.widget.LinearLayout/android.view.View/android.widget.ImageButton").click();
	}
}
