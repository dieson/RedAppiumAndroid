package com.appium.Android_page;

import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Dieson Zuo
 * @date Feb 15, 2016 11:06:36 AM
 */
public class AnnotationsPage extends BaseScreen {
	private static final Properties ANNOTATIONS_PAGE = new PropertyUtils().loadProperties("/Android_page/AnnotationsPage.properties");
	private static String annotations = ANNOTATIONS_PAGE.getProperty("ANDROID_ANNOTATIONS");
	private static String editTag = ANNOTATIONS_PAGE.getProperty("ANDROID_EDITTAG");
	private static String addTag = ANNOTATIONS_PAGE.getProperty("ANDROID_ADDTAG");
	private static String tagName = ANNOTATIONS_PAGE.getProperty("ANDROID_TAGNAME");
	private static String done = ANNOTATIONS_PAGE.getProperty("ANDROID_DONE");
	private static String cancel = ANNOTATIONS_PAGE.getProperty("ANDROID_CANCEL");
	private static String colour = ANNOTATIONS_PAGE.getProperty("ANDROID_COLOUR");
	private static String delete = ANNOTATIONS_PAGE.getProperty("ANDROID_DELETE");
	private static String leftEdit = ANNOTATIONS_PAGE.getProperty("ANDROID_LEFTEDIT");
	private static String all = ANNOTATIONS_PAGE.getProperty("ANDROID_ALL");
	private static String highlights = ANNOTATIONS_PAGE.getProperty("ANDROID_HIGHLIGHTS");
	private static String notes = ANNOTATIONS_PAGE.getProperty("ANDROID_NOTES");
	private static String orphan = ANNOTATIONS_PAGE.getProperty("ANDROID_ORPHANS");
	private static String noAnnotations = ANNOTATIONS_PAGE.getProperty("ANDROID_NOANNOTATIONS");
	
	public AnnotationsPage(RedAndroid screenUtils){
		screen = screenUtils;
	}
	
	public AnnotationsPage(RedAndroid screenUtils, Map<String, Object> dataDriver, ExcelUtils excelUtils){
		screen = screenUtils;
		data = dataDriver;
		excel = excelUtils;
	}
	
	public void add(){
		screen.click(annotations, "Annotations");
		screen.click(editTag, "Edit Tag");
		screen.click(addTag, "Add Tag");
		
		String x =  Integer.toString((int)(1 + Math.random()*(100-1)));
		screen.input(tagName, x, "Tag Name");
		
		screen.click(colour, "Colour");
		screen.click(done, "Done");
		
		boolean element = false;
		for (int i = 0; i < 3 && element == false; i++) {
			String path = "NAME:" + x;
			element = screen.isExistElement(path);
			screen.wait(1);
			screen.swipe(650, 540, 650, 320, "Edit Tags");
		}
		screen.tap(900, 300);
		
		WebElement elementLeft = null;
		String path = "NAME:" + x;
		elementLeft = screen.findElement(path);
		screen.wait(1);
		
		Assert.assertEquals(element, true);
		Assert.assertEquals(elementLeft.equals(null), false);
		
	}
	
	public void edit(){
		screen.click(annotations, "Annotations");
		screen.click(editTag, "Edit Tag");
		screen.swipe(560, 320, 660, 320, "Edit Tags");
		screen.click(leftEdit, "Edit");
		
		String x =  Integer.toString((int)(1 + Math.random()*(100-1)));
		screen.input(tagName, x, "Tag Name");
		
		screen.click(colour, "Colour");
		screen.click(done, "Done");
		
		boolean element = false;
		for (int i = 0; i < 3 && element == false; i++) {
			String path = "NAME:" + x;
			element = screen.isExistElement(path);
			screen.wait(1);
			screen.swipe(650, 540, 650, 320, "Edit Tags");
		}
		screen.tap(900, 300);

		WebElement elementLeft = null;
		String path = "NAME:" + x;
		elementLeft = screen.findElement(path);
		screen.wait(1);
		
		Assert.assertEquals(element, true);
		Assert.assertEquals(elementLeft.equals(null), false);
	}
	
	public void filter(){
		screen.click(annotations, "Annotations");
		screen.click(orphan, "Orphans");
		
		WebElement element = screen.findElement(noAnnotations);
		if(element == null){
			element = screen.findElement("//android.widget.TextView[@contains(text,'● Orphaned')]");
		}
			
		Assert.assertEquals(element.equals(null), false);
	}

}
