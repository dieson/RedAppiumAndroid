package com.appium.Android_PageNew;

import java.util.Properties;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;

/**
 * @author Nikitta Shen
 * @date Sep 12, 2016
 */
public class DocumentNamePage extends BaseScreen {
	
	private static final Properties DOCUMENTNAME_PAGE_PROPERTIES = new PropertyUtils().loadProperties
			("/Android_pageNew/DocumentName.properties");

	
	public final String country = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_COUNTRY");
	public final String australia = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_AUSTRALIA");
	public final String userName = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_USERNAME");
	public final String password = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_PASSWORD");
	public final String loginButton = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_LOGINBUTTON");
	
    public final String download = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_DOWNLOAD");
    public final String info = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_INFO");
    public final String downloadStatus = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_DOWNLOADSTATUS");
    //private final String filterList = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_FILTERLIST");
   // private final String filterAll = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_ALL");
    //private final String filterLoan = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_LOAN");
    
	public final String bookCover = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_BOOKCOVER");
	public final String tocListLocation = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_TOCLIST");
	public final String documentName = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_DOCUMENTNAME");
	public final String tocText = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_TOCNAME");
	public final String tocTitle = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_TOCTITLE");
	public final String index = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_INDEX");
	public final String annotations = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_ANNOTATIONNAV");
	public final String back = DOCUMENTNAME_PAGE_PROPERTIES.getProperty("ANDROID_BACK");
	
	public DocumentNamePage(RedAndroid screenUtils) {
		screen = screenUtils;
	}

	/* This method to compare the list when select a toc node randomly*/
	public boolean compareList(String[] list1, String[] list2){
		boolean equal = false;
		if(list1.length != list2.length){
			return equal;
		}
		for (int i = 0; i < list1.length; i++){
			if(!list1[i].equalsIgnoreCase(list2[i])){
				return equal;
			}
		}
		equal = true;
	    return equal;
        
	}

	public void documentNameTest(){
		
		String infoStatu = screen.findElement(info).getAttribute("selected");

		do {
		    report.log(screen.driver.getOrientation().toString());
			screen.driver.rotate(ScreenOrientation.PORTRAIT);
			screen.driver.rotate(ScreenOrientation.LANDSCAPE);
			screen.wait(1);
			infoStatu = screen.findElement(info).getAttribute("selected");
			report.log(infoStatu);
			
	    }while (!infoStatu.equals("true"));
		
		/*Open the first book*/
		screen.click(bookCover, "Book Cover of the first book");
		screen.waitProgress();
		
	   /* Sellect a toc node randomly*/
		int tocI;
		int tocJ;
		List<WebElement> tocListI;
		List<WebElement> tocListJ;
		String tocNameListI[];
		String tocNameListJ[];
		String tocName;
        int x = 1;
        Random random = new Random();
		do {
		    tocListI = screen.findElements(tocListLocation);
			screen.wait(3);
			tocI = tocListI.size();
	        report.log("The size of tocI is " + tocI);
			tocNameListI = new String[tocI];
			for (int k = 0; k <tocI; k++){
				tocNameListI[k] = screen.getText(screen.findElement(tocListI.get(k), tocText), "toc name");
				report.log(tocNameListI[k] );
			}
			int randomNum = random.nextInt(tocI- x) + x;
		    report.log(Integer.toString(randomNum));
		    //Click a random toc to check the toclist before and after
		    tocListI.get(randomNum).click();
		    screen.waitProgress();
			tocName = tocNameListI[randomNum];
			report.log(tocName);
			x++;
			
			tocListJ = screen.findElements(tocListLocation);
			screen.wait(3);
			tocJ = tocListJ.size();
			report.log("The size of tocJ is " + tocJ);
			tocNameListJ = new String[tocJ];
			for (int n = 0; n <tocJ; n++){
				tocNameListJ[n] = screen.getText(screen.findElement(tocListJ.get(n), tocText), "toc name");
				report.log(tocNameListJ[n]);
			}
		}while(!compareList(tocNameListI,tocNameListJ)); 
		/* Get the toc name in 'Table of Contents' */
		//tocName = tocNameListJ[tocJ - 1];
		report.log("The toc name is " + tocName);
        String tocTitleUp = screen.getText(tocTitle, "Toc title up content");
        /* Compare the name in TOC and up the content */
        Assert.assertEquals(tocName, tocTitleUp);
        
        /* Go to annotation navigator and compare the toc title up the content with the name in TOC */
        screen.click(annotations, "Go to annotation");
        screen.waitProgress();
        Assert.assertEquals(tocName, tocTitleUp);
        
        /* Go to index screen and check there is no toc title up the content pane */
		if (screen.isExistElement(index)){
			screen.click(index, "If there is index, go to index screen");
			screen.waitProgress();
			if(screen.isExistElement(tocTitle)){
				Assert.fail("There is toc title up the index pane. It should not be there.");
			} else {
				report.log("There is no toc title up the index pane");
			}
		}
       screen.click(back, "Back to publications");
	}
	

}
