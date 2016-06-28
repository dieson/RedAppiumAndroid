package com.appium.driver;

import com.appium.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Find element
 * 
 * @author Dieson Zuo
 */
public class FindElement extends DriverUtils {
	public List<WebElement> findElements(String locator) {
		String locatorType = Utils.getLocatorType(locator);
		String locatorStr = Utils.getLocatorStr(locator);
		List<WebElement> element = null;

		try {
			switch (locatorType) {
			case "XPATH":
				element = driver.findElementsByXPath(locatorStr);
				break;
			case "ID":
				element = driver.findElementsById(locatorStr);
				break;
			case "CLASS":
				element = driver.findElementsByClassName(locatorStr);
				break;
			case "NAME":
				element = driver.findElementsByName(locatorStr);
				break;
			case "LINKTEXT":
				element = driver.findElementsByLinkText(locatorStr);
				break;
			case "TAGNAME":
				element = driver.findElementsByTagName(locatorStr);
				break;
			default:
				break;
			}
			report.log("[Successful] Find the element");
		} catch (Exception e) {
			report.errorLog("[Fail] Unable find element");
			report.errorLog(e.toString());
		}
		return element;
	}
	
	public List<WebElement> findElements(WebElement elementHigher, String locator) {
		String locatorType = Utils.getLocatorType(locator);
		String locatorStr = Utils.getLocatorStr(locator);
		List<WebElement> element = null;

		try {
			switch (locatorType) {
			case "XPATH":
				element = elementHigher.findElements(By.xpath(locatorStr));
				break;
			case "ID":
				element = elementHigher.findElements(By.id(locatorStr));
				break;
			case "CLASS":
				element = elementHigher.findElements(By.className(locatorStr));
				break;
			case "NAME":
				element = elementHigher.findElements(By.name(locatorStr));
				break;
			case "LINKTEXT":
				element = elementHigher.findElements(By.linkText(locatorStr));
				break;
			case "TAGNAME":
				element = elementHigher.findElements(By.tagName(locatorStr));
				break;
			default:
				break;
			}
			report.log("[Successful] Find the element");
		} catch (Exception e) {
			report.errorLog("[Fail] Unable find element");
			report.errorLog(e.toString());
		}
		return element;
	}

	public WebElement findElement(String locator) {
		String locatorType = Utils.getLocatorType(locator);
		String locatorStr = Utils.getLocatorStr(locator);
		WebElement element = null;

		try {
			switch (locatorType) {
			case "XPATH":
				element = driver.findElementByXPath(locatorStr);
				break;
			case "ID":
				element = driver.findElementById(locatorStr);
				break;
			case "CLASS":
				element = driver.findElementByClassName(locatorStr);
				break;
			case "NAME":
				element = driver.findElementByName(locatorStr);
				break;
			case "LINKTEXT":
				element = driver.findElementByLinkText(locatorStr);
				break;
			case "TAGNAME":
				element = driver.findElementByTagName(locatorStr);
				break;
			default:
				break;
			}
			report.log("[Successful] Find the element");
		} catch (Exception e) {
			report.errorLog("[Fail] Unable find element");
			report.errorLog(e.toString());
		}
		return element;
	}
	
	public WebElement findElement(WebElement elementHigher, String locator) {
		String locatorType = Utils.getLocatorType(locator);
		String locatorStr = Utils.getLocatorStr(locator);
		WebElement element = null;

		try {
			switch (locatorType) {
			case "XPATH":
				element = elementHigher.findElement(By.xpath(locatorStr));
				break;
			case "ID":
				element = elementHigher.findElement(By.id(locatorStr));
				break;
			case "CLASS":
				element = elementHigher.findElement(By.className(locatorStr));
				break;
			case "NAME":
				element = elementHigher.findElement(By.name(locatorStr));
				break;
			case "LINKTEXT":
				element = elementHigher.findElement(By.linkText(locatorStr));
				break;
			case "TAGNAME":
				element = elementHigher.findElement(By.tagName(locatorStr));
				break;
			default:
				break;
			}
			report.log("[Successful] Find the element");
		} catch (Exception e) {
			report.errorLog("[Fail] Unable find element");
			report.errorLog(e.toString());
		}
		return element;
	}

}
