package com.appium.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.appium.utils.Utils;

public class ElementUtils extends FindElement {
	public void input(String locator, Object value, String elementName) {
		WebElement element = findElement(locator);
		try {
			element.clear();
			element.sendKeys(value.toString());
			report.log("[Successful] " + elementName + " input:" + value);
		} catch (Exception e) {
			screenshot(elementName);
			report.errorLog("[Fail] Unable to input");
			report.log(e.toString());
			Assert.fail();
		}
	}

	public void clear(String locator, String elementName) {
		try {
			findElement(locator).clear();
			report.log("[Successful] Clear the " + elementName);
		} catch (Exception e) {
			screenshot(elementName);
			report.errorLog("[Fail] Unable to Clear the " + elementName);
			report.log(e.toString());
			Assert.fail();
		}
	}

	public void click(String locator, String elementName) {
		try {
			findElement(locator).click();
			report.log("[Successful] Click the " + elementName);
		} catch (Exception e) {
			screenshot(elementName);
			report.errorLog("[Fail] Unable to click");
			report.log(e.toString());
			Assert.fail();
		}
	}
	
	public void click(WebElement element, String elementName) {
		try {
			element.click();
			report.log("[Successful] Click the " + elementName);
		} catch (Exception e) {
			screenshot(elementName);
			report.errorLog("[Fail] Unable to click");
			report.log(e.toString());
			Assert.fail();
		}
	}
	
	public void click(WebElement element, String locator, String elementName) {
		try {
			super.findElement(element, locator).click();
			report.log("[Successful] Click the " + elementName);
		} catch (Exception e) {
			screenshot(elementName);
			report.errorLog("[Fail] Unable to click");
			report.log(e.toString());
			Assert.fail();
		}
	}

	public void select(String locator, Object value, String elementName) {
		WebElement element = findElement(locator);

		try {
			Select select = new Select(element);
			select.selectByVisibleText(value.toString());
			report.log("[Successful] Select the " + value);
		} catch (Exception e) {
			screenshot(value.toString());
			report.errorLog("[Fail] Unable to Select");
			report.errorLog(e.toString());
			Assert.fail();
		}
	}

	public String getText(String locator, String elementName) {
		String msg = "";
		try {
			msg = findElement(locator).getText();
			report.log("[Successful] Get the " + elementName);
		} catch (Exception e) {
			screenshot(elementName);
			report.errorLog("[Fail] Get attribute failure");
			report.log(e.toString());
			Assert.fail();
		}
		return msg;
	}
	
	public String getText(WebElement elementHigher, String locator, String elementName) {
		String msg = "";
		try {
			msg = findElement(elementHigher, locator).getText();
			report.log("[Successful] Get the " + elementName);
		} catch (Exception e) {
			screenshot(elementName);
			report.errorLog("[Fail] Get attribute failure");
			report.log(e.toString());
			Assert.fail();
		}
		return msg;
	}
	
	public String getText(WebElement element, String elementName) {
		String msg = "";
		try {
			msg = element.getText();
			report.log("[Successful] Get the " + elementName);
		} catch (Exception e) {
			screenshot(elementName);
			report.errorLog("[Fail] Get attribute failure");
			report.log(e.toString());
			Assert.fail();
		}
		return msg;
	}
	
	public boolean isSelect(String locator, String elementName) {
		WebElement element = findElement(locator);
		boolean isSelected = true;
		try {
			isSelected = element.isSelected();
			
			if (isSelected) {
				report.log("[Successful] " + elementName + " is already selected");
			} else {
				report.log("[Successful] " + elementName + " is not selected");
			}
			
		} catch (Exception e) {
			screenshot(elementName.toString());
			report.errorLog("[Fail] Not to selected");
			report.errorLog(e.toString());
			Assert.fail();
		}
		return isSelected;
	}
	
	public boolean isSelect(WebElement element, String elementName) {
		boolean isSelected = true;
		try {
			isSelected = element.isSelected();
			
			if (isSelected) {
				report.log("[Successful] " + elementName + " is already selected");
			} else {
				report.log("[Successful] " + elementName + " is not selected");
			}
			
		} catch (Exception e) {
			screenshot(elementName.toString());
			report.errorLog("[Fail] Not to selected");
			report.errorLog(e.toString());
			Assert.fail();
		}
		return isSelected;
	}

	public void longPress(String locator, String elementName, int second) {
		WebElement element = findElement(locator);
		int i = 1000 * second;

		try {
			action.longPress(element, i);
			report.log("[Successful] Long press the " + elementName);
		} catch (Exception e) {
			screenshot(elementName);
			report.errorLog("[Fail] Unable to long press");
			Assert.fail();
		}
	}

	public boolean isExistElement(String locator) {
		String locatorType = Utils.getLocatorType(locator);
		String locatorStr = Utils.getLocatorStr(locator);
		boolean exist = true;
		
		try {
			switch (locatorType) {
			case "XPATH":
				driver.findElementByXPath(locatorStr);
				break;
			case "ID":
				driver.findElementById(locatorStr);
				break;
			case "CLASS":
				driver.findElementByClassName(locatorStr);
				break;
			case "NAME":
				driver.findElementByName(locatorStr);
				break;
			case "LINKTEXT":
				driver.findElementByLinkText(locatorStr);
				break;
			case "TAGNAME":
				driver.findElementByTagName(locatorStr);
				break;
			default:
				break;
			}
		} catch (NoSuchElementException e) {
			exist = false;
		}
		return exist;
	}
	
	public boolean isExistElement(WebElement elementHigher, String locator) {
		String locatorType = Utils.getLocatorType(locator);
		String locatorStr = Utils.getLocatorStr(locator);
		boolean exist = true;
		
		try {
			switch (locatorType) {
			case "XPATH":
				elementHigher.findElement(By.xpath(locatorStr));
				break;
			case "ID":
				elementHigher.findElement(By.id(locatorStr));
				break;
			case "CLASS":
				elementHigher.findElement(By.className(locatorStr));
				break;
			case "NAME":
				elementHigher.findElement(By.name(locatorStr));
				break;
			case "LINKTEXT":
				elementHigher.findElement(By.linkText(locatorStr));
				break;
			case "TAGNAME":
				elementHigher.findElement(By.tagName(locatorStr));
				break;
			default:
				break;
			}
		} catch (NoSuchElementException e) {
			exist = false;
		}
		return exist;
	}

}
