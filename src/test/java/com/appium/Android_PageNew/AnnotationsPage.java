package com.appium.Android_PageNew;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Dieson Zuo
 * @date Feb 15, 2016 11:06:36 AM
 */
public class AnnotationsPage extends BaseScreen {
	private static final Properties ANNOTATIONS_PAGE = new PropertyUtils().loadProperties("/Android_PageNew/AnnotationsPage.properties");
	
	private final String annotations = ANNOTATIONS_PAGE.getProperty("ANDROID_ANNOTATIONS");
	private final String publications = ANNOTATIONS_PAGE.getProperty("ANDROID_PUBLICATIONS");
	private final String editTag = ANNOTATIONS_PAGE.getProperty("ANDROID_EDITTAG");
	private final String addTag = ANNOTATIONS_PAGE.getProperty("ANDROID_ADDTAG");
	private final String tagName = ANNOTATIONS_PAGE.getProperty("ANDROID_TAGNAME");
	private final String done = ANNOTATIONS_PAGE.getProperty("ANDROID_DONE");
	private final String cancel = ANNOTATIONS_PAGE.getProperty("ANDROID_CANCEL");
	private final String colour = ANNOTATIONS_PAGE.getProperty("ANDROID_COLOUR");
	private final String delete = ANNOTATIONS_PAGE.getProperty("ANDROID_DELETE");
	private final String leftEdit = ANNOTATIONS_PAGE.getProperty("ANDROID_LEFTEDIT");
	private final String all = ANNOTATIONS_PAGE.getProperty("ANDROID_ALL");
	private final String highlights = ANNOTATIONS_PAGE.getProperty("ANDROID_HIGHLIGHTS");
	private final String notes = ANNOTATIONS_PAGE.getProperty("ANDROID_NOTES");
	private final String orphan = ANNOTATIONS_PAGE.getProperty("ANDROID_ORPHANS");
	private final String noAnnotations = ANNOTATIONS_PAGE.getProperty("ANDROID_NOANNOTATIONS");
	private final String searchButton = ANNOTATIONS_PAGE.getProperty("ANDROID_SEARCHBUTTON");
	private final String searchText = ANNOTATIONS_PAGE.getProperty("ANDROID_SEARCHTEXT");
	private final String annotationsList = ANNOTATIONS_PAGE.getProperty("ANDROID_ANNOTATIONSLIST");
	private final String publicationTitle = ANNOTATIONS_PAGE.getProperty("ANDROID_PUBLICATIONTITLE");
	private final String tocTitle = ANNOTATIONS_PAGE.getProperty("ANDROID_TOCTITLE");
	private final String titleName = ANNOTATIONS_PAGE.getProperty("ANDROID_TITLENAME");
	private final String backButton = ANNOTATIONS_PAGE.getProperty("ANDROID_BACKBUTTON");
	
	public AnnotationsPage(RedAndroid screenUtils){
		screen = screenUtils;
	}
	
	public void add(){
		screen.click(annotations, "Annotations");
		screen.click(editTag, "Edit Tag");
		screen.click(addTag, "Add Tag");
		
		String x =  Integer.toString((int)(1 + Math.random()*(100-1)));
		String path = "NAME:" + x;
		screen.input(tagName, x, "Tag Name");
		
		screen.click(colour, "Colour");
		screen.click(done, "Done");
		
		boolean element = false;
		for (int i = 0; i < 5 && element == false; i++) {
			element = screen.isExistElement(path);
			screen.wait(1);
			screen.swipe(650, 540, 650, 320, "Edit Tags");
		}
		//Close the tags pane
		screen.tap(900, 300);
		
		boolean elementLeft = false;
		for (int i = 0; i < 3 && elementLeft == false; i++) {
			elementLeft = screen.isExistElement(path);
			screen.wait(1);
			screen.swipe(150, 750, 150, 150, "Edit Tags");
		}
		screen.click(publications, "Publication Screen");
		
		Assert.assertEquals(element, true);
		Assert.assertEquals(elementLeft, true);
		
	}
	
	public void edit(){
		screen.click(annotations, "Annotations");
		screen.click(editTag, "Edit Tag");
		screen.swipe(560, 320, 760, 320, "Edit Tags");
		screen.click(leftEdit, "Edit");
		
		String x =  Integer.toString((int)(1 + Math.random()*(100-1)));
		String path = "NAME:" + x;
		screen.input(tagName, x, "Tag Name");
		
		screen.click(colour, "Colour");
		screen.click(done, "Done");
		
		boolean element = screen.isExistElement(path);
		screen.tap(900, 300);

		boolean elementLeft = screen.isExistElement(path);
		screen.click(publications, "Publication Screen");
		
		Assert.assertEquals(element, true);
		Assert.assertEquals(elementLeft, true);
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
	
	public void searchAnnotations(String text) {
		screen.click(annotations, "Annotations Organise");
		//Search annotations
		screen.click(searchButton, "Search Button");
		screen.input(searchText, text, "Search Text");
		screen.sendKey(searchText, 66, "Search");
		
		String titleNameOrg = "";
		String tocNameOrg = "";
		if (!screen.isExistElement(noAnnotations)) {
			// Search results is not null.
			List<WebElement> antList = screen.findElements(annotationsList);
			for (WebElement webElement : antList) {
				
				if (!screen.getText(webElement, tocTitle, "TOC Name").contains("● Orphaned")) {
					//Get the annotation info of annotations organise
					titleNameOrg = screen.getText(webElement, publicationTitle, "Organise Title Name");
					tocNameOrg = screen.getText(webElement, tocTitle, "Organise TOC Name");
					webElement.click();
					screen.waitProgress();
					//Get the annotation info of content page
					String titleNameContent = screen.getText(titleName, "Title Name Of Content Page");
					String tocNameContent = screen.getText(tocTitle, "TOC Name Of Content Page");
					screen.click(backButton, "Back Button");
				
					Assert.assertEquals(titleNameContent, titleNameOrg);
					Assert.assertEquals(tocNameOrg.contains(tocNameContent), true);
					break;
				}
			}
			
		} else {
			screen.click(publications, "Publication Screen");
		}
		
	}

}
