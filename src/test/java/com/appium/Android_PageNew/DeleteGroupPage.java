package com.appium.Android_PageNew;

import java.util.Properties;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

public class DeleteGroupPage extends BaseScreen {
private static final Properties DELETE_GROUP_TITLE_PAGE = new PropertyUtils().loadProperties("/Android_PageNew/DeleteGroupPage.properties");
	
	private final String options = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_OPTIONS");
	private final String organise = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_ORGANISE");
	private final String group = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_GROUPED");
	private final String isgroup = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_ISGROUPED");
	private final String book = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_BOOK");
	private final String delete = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_DELETE");
	private final String back = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_BACK");
	private final String confirmdelete = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_DELETEBUTTON");
    private final String folder = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_FOLDER");
	private final String title = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_TITLE");
	private final String publication = DELETE_GROUP_TITLE_PAGE.getProperty("ANDROID_PUBLICATION");
	
	
	public DeleteGroupPage(RedAndroid screenUtils){
		screen = screenUtils;
	}	
		
	public void deletegroup(){
		
		screen.click(options, "Options");
		screen.click(organise, "Organise");
		//String titleName = screen.getText(book, "Book");
		this.onGroup();
		this.offGroup();
		this.onGroup();

	    //screen.tap(654, 106);
		//screen.swipe(654, 155, 520, 155, "Delete"); 
		screen.swipe(1000, 500, 800, 500, "Delete"); 
		screen.wait(3);
		screen.click(delete,"Delete");
		screen.click(confirmdelete, "Delete");
		//screen.click(confirmdelete, "Delete");  
		screen.click(back,"back");	
		//Assert.assertEquals(publication, publication);
		
		//List<WebElement> allTitle = screen.findElements(title);
		//boolean existTitle = true;
		//for(WebElement element : allTitle){
			//if(element.getText() == titleName){
				//existTitle = false;
			//}
		//}
		//return existTitle;
	}
  
	public void onGroup() {

		if (!screen.isSelect(isgroup, "grouped publications")) {
			screen.click(isgroup, "grouped publications");
		}
	}
	
	public void offGroup() {

		if (screen.isSelect(isgroup, "grouped publications")) {
			screen.click(isgroup, "grouped publications");
		}
	}
	

	
}
