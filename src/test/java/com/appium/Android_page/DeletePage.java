package com.appium.Android_page;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

public class DeletePage extends BaseScreen{
	private static final Properties DELETE_TITLE_PAGE = new PropertyUtils().loadProperties("/Android_page/DeletePage.properties");
	
	private final String options = DELETE_TITLE_PAGE.getProperty("ANDROID_OPTIONS");
	private final String organise = DELETE_TITLE_PAGE.getProperty("ANDROID_ORGANISE");
	private final String book = DELETE_TITLE_PAGE.getProperty("ANDROID_PUBLICATION");
	private final String delete = DELETE_TITLE_PAGE.getProperty("ANDROID_DELETE");
	private final String confirm = DELETE_TITLE_PAGE.getProperty("ANDROID_CONFIRM");
	private final String title = DELETE_TITLE_PAGE.getProperty("ANDROID_TITLE");
	private final String publication = DELETE_TITLE_PAGE.getProperty("ANDROID_PUBLICATION");
	
	public DeletePage(RedAndroid screenUtils){
		screen = screenUtils;
	}
	
	/**
	 * return true： delete successful
	 * return false: delete fail
	 * @return
	 */
	public boolean delete(){
		screen.click(options, "Options");
		screen.click(organise, "Organise");
		String titleName = screen.getText(book, "Book");
		screen.swipe(284, 245, 353, 245, "Delete");
		screen.click(delete, "Delete");
		screen.click(confirm, "Confirm");
		screen.click(publication, "Publication");
		
		List<WebElement> allTitle = screen.findElements(title);
		boolean existTitle = true;
		for(WebElement element : allTitle){
			if(element.getText() == titleName){
				existTitle = false;
			}
		}
		return existTitle;
	}
	
}
