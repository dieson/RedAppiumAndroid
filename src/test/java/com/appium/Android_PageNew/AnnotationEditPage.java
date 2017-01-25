package com.appium.Android_PageNew;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;


/**
 * @author Dieson Zuo
 * @date Nov 10, 2016 10:19:19 AM
 */
public class AnnotationEditPage extends BaseScreen {
	public static final Properties ANNOTATION_EDIT_PAGE = new PropertyUtils()
			.loadProperties("/Android_PageNew/AnnotationsEditPage.properties");

	private final String annotationPage = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_ANNOTATIONS");
	private final String publications = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_PUBLICATIONS");
	private final String addNote = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_ADDNOTE");
	private final String noteText = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_NOTETEXT");
	private final String tag = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_TAG");
	private final String addTag = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_ADDTAG");
	private final String annotationList = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_ANNOTATIONLIST");
	private final String annotationItem = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_ANNOTATIONITEM");
	private final String note = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_NOTE");
	private final String tagList = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_TAGLIST");
	private final String tagItem = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_TAGITEM");
	private final String tagSelected = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_TAGSELECTED");
	private final String deleteNote = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_DELETENOTE");
	private final String yes = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_YES");
	private final String deleteAnnotation = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_DELETEANNOTATION");
	private final String publicationItem = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_PUBLICATIONITEM");
	private final String webView = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_WEBVIEW");
	private final String addHighlight = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_ADDHIGHLIGHT");
	private final String backButton = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_BACKBUTTON");
	private final String toclist = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_TOCLIST");
	private final String tocName = ANNOTATION_EDIT_PAGE.getProperty("ANDROID_TOCNAME");

	public AnnotationEditPage(RedAndroid screenUtils) {
		screen = screenUtils;
	}
	
	public void addAnnotation() {
		
		screen.click(publicationItem, "Title");
		screen.waitProgress();
		// Get the WebView point and select the text by point.
		Point view = screen.findElement(webView).getLocation();
		do {
			String tocNameI = "";
			String tocNameV = "1";
			for (int i = 2; !tocNameI.equals(tocNameV); i++) {
				List<WebElement> tocListI = screen.findElements(toclist);
				int tocI = tocListI.size();
				int x = (int) (i + Math.random() * (tocI - i));
				tocNameI = screen.getText(screen.findElement(tocListI.get(x - 1), tocName), "TOC Name");
				tocListI.get(x - 1).click();
				screen.waitProgress();
				List<WebElement> tocListV = screen.findElements(toclist);
				int tocV = tocListV.size();
				if (tocV == tocI) {
					tocNameV = screen.getText(screen.findElement(tocListV.get(x - 1), tocName), "TOC Name");
				}
			}
			screen.tap(view.x + 150, view.y + 180, 3000);
		} while (!screen.isExistElement(addHighlight));
		// Add annotation
		screen.click(addHighlight, "Add Highlight");
		// Get the tag name
		List<WebElement> tags = screen.findElements(tagItem);
		String tagName = screen.getText(tags.get(0), tag, "Tag Name");
		screen.click(screen.findElement(tags.get(0), tagSelected), "Tag");
		// Get the tag name in annotation navigator
		screen.tap(1200, 200);
		screen.click(annotationPage, "Annotations Navigator");
		WebElement tagNavigator = screen.findElement(screen.findElements(annotationItem).get(0), tag);
		String tagNameNavigator = screen.getText(tagNavigator, "Tag Name In Navigator");
		
		screen.click(backButton, "Back Button");
		
		Assert.assertEquals(tagNameNavigator, tagName);
	}

	public void addNoteTag() {

		screen.click(annotationPage, "Annotation Page");
		List<WebElement> annotations = screen.findElements(annotationList, annotationItem);

		// Add note.
		if (screen.isExistElement(addNote)) {

			int i = 0;
			while (!screen.isExistElement(annotations.get(i), addNote)) {
				i++;
			}
			// Add note
			WebElement addnote = screen.findElement(annotations.get(i), addNote);
			screen.click(addnote, "Add Note");
			screen.input(note, "AUTHORS OF COMPANIES COMMENTARY", "Note");
			screen.tap(200, 200);
			// Get note name
			String notetext = screen.getText(annotations.get(i), noteText, "Note Text");
			
			Assert.assertEquals(notetext, "AUTHORS OF COMPANIES COMMENTARY");
		}

		// Add tag.
		if (screen.isExistElement(addTag)) {

			int i = 0;
			while (!screen.isExistElement(annotations.get(i), addTag)) {
				i++;
			}
			// Add tag
			WebElement addtag = screen.findElement(annotations.get(i), addTag);
			screen.click(addtag, "Add Tag");
			List<WebElement> tags = screen.findElements(tagItem);
			String tagName = screen.getText(tags.get(0), tag, "Tag Name");
			screen.click(screen.findElement(tags.get(0), tagSelected), "Tag");

			screen.tap(1200, 200);
			// Get tag name
			String tagname = screen.getText(annotations.get(i), tag, "Tag Name");
			
			Assert.assertEquals(tagname, tagName);
		}

	}

	public void editNoteTag() {

		screen.click(annotationPage, "Annotation Page");
		List<WebElement> annotations = screen.findElements(annotationList, annotationItem);

		// Edit note.
		if (screen.isExistElement(noteText)) {
			
			int i = 0;
			while (!screen.isExistElement(annotations.get(i), noteText)) {
				i++;
			}
			// Edit note
			WebElement editNote = screen.findElement(annotations.get(i), noteText);
			screen.click(editNote, "Edit Note");
			screen.input(note, "Insolvency Practitioners Heading", "Note");
			screen.tap(1200, 200);
			// Get the note text
			String notetext = screen.getText(annotations.get(i), noteText, "Note Text");
			
			Assert.assertEquals(notetext, "Insolvency Practitioners Heading");
		}

		// Edit tag.
		/*for (int j = 0; j < annotations.size(); j++) {
			
			if (!screen.getText(annotations.get(j), tag, "Tag Name").equals("Add a tag...")) {
				// Open the edit pop-up window
				List<WebElement> tagTitles = screen.findElements(annotations.get(j), tag);
				screen.click(tagTitles.get(0), "Edit Tag");
				// Select the tag, get it title name
				List<WebElement> tags = screen.findElements(tagItem);
				List<String> tagTitleSelect = new ArrayList<>();
				for (WebElement webElement : tags) {
					if (!screen.isSelect(screen.findElement(webElement, tagSelected), "Tag")) {
						tagTitleSelect.add(screen.getText(webElement, tag, "Tag Name"));
					}
					screen.click(webElement, tagSelected, "Select Tag");
				}
				screen.tap(1200, 200);
				// Get the tag name of annotations page
				tagTitles = screen.findElements(annotations.get(0), tag);
				List<String> tagTitleAfter = new ArrayList<>();
				for (WebElement webElement : tagTitles) {
					tagTitleAfter.add(screen.getText(webElement, "Tag Name"));
				}
				
				Assert.assertEquals(tagTitleAfter.containsAll(tagTitleSelect), true);
				break;
			}
		}*/

	}

}
