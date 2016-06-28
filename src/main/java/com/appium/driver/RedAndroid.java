package com.appium.driver;

import org.openqa.selenium.WebElement;

public class RedAndroid extends ElementUtils{
	public void waitProgress(){
		WebElement element = null;
		//system progress bar id is 'android:id/body'
		try {
			do{
				element = driver.findElementById("android:id/body");
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
			driver.swipe(1200, 400, 100, 400, 3000);
		}
	}
	
}
