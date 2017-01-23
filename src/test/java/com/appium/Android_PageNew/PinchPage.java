package com.appium.Android_PageNew;

import java.util.Properties;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

/**
 * @author Nikitta Shen
 * @date Sep 28, 2016
 */
public class PinchPage extends BaseScreen {
	
	private static final Properties PINCH_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_pageNew/Pinch.properties");

	public final String bookCover = PINCH_PAGE_PROPERTIES.getProperty("ANDROID_BOOKCOVER");
	public final String settings = PINCH_PAGE_PROPERTIES.getProperty("ANDROID_SETTINGS");
	public final String publicationText = PINCH_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATIONTEXT");
	public final String small = PINCH_PAGE_PROPERTIES.getProperty("ANDROID_SMALL");
	public final String normal = PINCH_PAGE_PROPERTIES.getProperty("ANDROID_NORMAL");
    public final String large = PINCH_PAGE_PROPERTIES.getProperty("ANDROID_LARGE");
    public final String cancel = PINCH_PAGE_PROPERTIES.getProperty("ANDROID_CANCEL");
    public final String content = PINCH_PAGE_PROPERTIES.getProperty("ANDROID_CONTENT");
    
	public PinchPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}
	
	public void PinchTest(){
		screen.wait(5);
		screen.click(bookCover, "Open the book");
		screen.waitProgress();
		//WebElement el = screen.findElement(content);
		//el.click();
		//Dimension dimensions = el.getSize();
		//int width = dimensions.getWidth();
		//int hight = dimensions.getHeight();
		//int x = el.getLocation().getX();
		//int y = el.getLocation().getY();
		//report.log(String.valueOf(width));
		//report.log(String.valueOf(hight));
		//report.log(String.valueOf(x));
		//report.log(String.valueOf(y));
		//screen.driver.pinch(el);
		//screen.driver.pinch(el);
		MultiTouchAction multiTouch = new MultiTouchAction(screen.driver);
	    TouchAction action0 = new TouchAction(screen.driver).longPress(1200, 800, 5).moveTo(1200, 1200).release();
	    TouchAction action1 = new TouchAction(screen.driver).longPress(1200, 1600, 5).moveTo(1200, 1200).release();

	    multiTouch.add(action0).add(action1);

	    multiTouch.perform();
		//screen.driver.tap(1, 1200, 1100, 5);
		//screen.driver.tap(1, 1200, 1300, 5);
		//screen.driver.pinch(1200, 1200);
		
		screen.wait(5);
		//screen.click(settings, "Open settings");
		//screen.click(publicationText, "Text radio buttons");
		//if (screen.findElement(normal).getAttribute("checked").equals("true")) {
			//screen.click(cancel, "close text setting window");
			/*Pinch to small font*/
			//screen.driver.pinch(1600, 1000);
			//screen.wait(2);
			//screen.driver.pinch(1600, 1000);
			//WebElement el = screen.findElement(content);
			//el.click();
			//screen.driver.pinch(el);
			//screen.wait(5);
			//screen.driver.pinch(el);
			//screen.driver.pinch(el);
			//screen.click(settings, "Open settings");
			//screen.click(publicationText, "Text radio buttons");
			//if (screen.findElement(small).getAttribute("checked").equals("true")) {
				//screen.click(cancel, "close text setting window");
				/*Zoom to large font*/
				//screen.driver.zoom(1000, 1000);
				//screen.click(settings, "Open settings");
				//screen.click(publicationText, "Text radio buttons");
				//if (screen.findElement(large).getAttribute("checked").equals("false")) {
					//Assert.fail();
					//report.log("zoom font size failed");
				//} else {
					
				//}
				
			//}
		//}

		
		
		
           
		
	}
}

