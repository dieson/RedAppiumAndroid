package com.appium.Android_page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.appium.datautils.DatabaseUtils;
import com.appium.datautils.ExcelUtils;
import com.appium.driver.BaseScreen;
import com.appium.driver.RedAndroid;
import com.appium.utils.PropertyUtils;
import com.appium.utils.Utils;

public class DownloadPage extends BaseScreen {
    private static final Properties DOWNLOAD_PAGE_PROPERTIES = new PropertyUtils().loadProperties("/Android_page/DownloadPage.properties");

    private final String download = DOWNLOAD_PAGE_PROPERTIES.getProperty("ANDROID_DOWNLOAD");
    private final String info = DOWNLOAD_PAGE_PROPERTIES.getProperty("ANDROID_INFO");
    private final String stop = DOWNLOAD_PAGE_PROPERTIES.getProperty("ANDROID_STOP");
    private final String confirm = DOWNLOAD_PAGE_PROPERTIES.getProperty("ANDROID_CONFIRM");
    private final String publicationList = DOWNLOAD_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATIONLIST");
    private final String downloadIcon = DOWNLOAD_PAGE_PROPERTIES.getProperty("ANDROID_DOWNLOADICON");
    private final String currencyDate = DOWNLOAD_PAGE_PROPERTIES.getProperty("ANDROID_CURRENCYDATE");
    private final String publicationName = DOWNLOAD_PAGE_PROPERTIES.getProperty("ANDROID_PUBLICATIONNAME");
    private final String filterList = DOWNLOAD_PAGE_PROPERTIES.getProperty("ANDROID_FILTERLIST");
    private final String filterAll = DOWNLOAD_PAGE_PROPERTIES.getProperty("ANDROID_ALL");

    public DownloadPage(RedAndroid screenUtils) {
        screen = screenUtils;
    }

    public DownloadPage(RedAndroid screenUtils, Map<String, Object> dataDriver, ExcelUtils excelUtils) {
        screen = screenUtils;
        data = dataDriver;
        excel = excelUtils;
    }

    public void downloadAll() {
        List<Integer> bookNO = new ArrayList<>();
        List<String> bookName = new ArrayList<>();
        Map<String, String> currencyScreen = new HashMap<>();
        //Get books name and start download
        for (int s = 0; s < 2; s++) {
            for (int i = 1; screen.isExistElement(publicationList + "[" + i + "]"); i++) {
                String bookIName = screen.getText(screen.findElement(screen.findElement(publicationList + "[" + i + "]"), publicationName), "Title Name");
                report.log("Book name '" + bookIName + "'");
                bookName.add(bookIName);
                bookNO.add(i);
                if (screen.getText(screen.findElement(screen.findElement(publicationList + "[" + i + "]"), downloadIcon), "Download status").equals("Download")) {
                    screen.click(publicationList + "[" + i + "]", "Download");
                }
            }
            //Waiting for the download completed
            for (int i = 0; i < bookName.size(); i++) {
                report.log("Wait for the '" + bookName.get(i) + "' download completed");
                while (screen.getText(screen.findElement(screen.findElement(publicationList + "[" + bookNO.get(i) + "]"), downloadIcon), "Download status").equals("Downloading")) {
                    screen.click(filterList, "Filter List");
                    screen.click(filterAll, "loan the book");
                    report.log(screen.getText(screen.findElement(screen.findElement(publicationList + "[" + bookNO.get(i) + "]"), downloadIcon), "Download status"));
                    screen.wait(1);
                }
            }
            //Get the screen Currency Date
            for (int i = 0; i < bookName.size(); i++) {
                if (!currencyScreen.containsKey(bookName.get(i))) {
                    String date = screen.getText(screen.findElement(screen.findElement(publicationList + "[" + bookNO.get(i) + "]"), currencyDate), "'" + bookName.get(i) + "' Currency Date");
                    currencyScreen.put(bookName.get(i), date);
                }
            }
            screen.swipe(1000, 400, 100, 400, "Publication");
        }

        //Get the database currency date
        /*Map<String, String> currencyData = new HashMap<>();
        for (String name : bookName) {
            String nameReplace = Utils.singleReplace(name);
            DatabaseUtils sql = new DatabaseUtils("jdbc:jtds:sqlserver://192.168.1.101:1433;DatabaseName=LN_DL_SubMgmt_P2", "sa", "Password1");
            String currencyBase = "";
            try {
                ResultSet set = sql.querySQL("SELECT LastUpdatedDate FROM tblDL where DLTitle = '" + nameReplace + "' and IsActive = 1");
                while (set.next()) {
                    currencyBase = set.getString("LastUpdatedDate");
                }
                sql.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Assert.fail();
            }
            currencyBase = Utils.datetimeToEnglish(currencyBase);
            currencyData.put(name, currencyBase);
        }
        //Check the currency date
        for (String name : bookName) {
            report.log("Actual:" + name + ":" + currencyScreen.get(name).toString());
            report.log("Expect:" + name + ":" + currencyData.get(name).toString());
            // Assert.assertEquals(currencyScreen.get(name).toString().contains(currencyData.get(name).toString()), true);
        }*/
    }

    /**
     * Download and verify currency date
     */
    public void download() {
        //boolean isDownload = true;
        screen.waitProgress();
        //Get all did not download the title
        List<WebElement> bookList = screen.findElements(publicationList);
        List<Integer> undownloadNO = new ArrayList<Integer>();
        for (int i = 0; i < bookList.size(); i++) {
            String status = screen.getText(screen.findElement(bookList.get(i), downloadIcon), "Download status");
            if (status.equals("Download")) {
                undownloadNO.add(i + 1);
            }
        }

        //Get did not download title XPATH
        String xpath = "XPATH://android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(0) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView";
        report.log(xpath);
        //Click the download button
        screen.click(xpath, "download button");
        //Check whether download is completed

		/*for(int i = 0; i < 100; i++){
            report.log(screen.driver.findElementByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(0) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView").getText());
			if(i<100){
				screen.wait(1);
			}else{
				isDownload = false;
				break;
			}
		}
		if (isDownload) {
			report.log("[Successful] Download is completed");
		} else {
			report.errorLog("[Fail] Download is failed");
			Assert.fail();
		}*/

        for (int i = 0; i < 50; i++) {
            report.log(screen.driver.findElementByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(0) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView").getAttribute("text"));
            screen.wait(1);
        }


        //Get publication name and currency date
        report.log("XPATH://android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(0) + "]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.TextView");
        String publication = screen.getText("XPATH://android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(0) + "]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.TextView", publicationName);
        String currency = screen.getText(xpath + "[2]", "Currency Date");
        report.log(xpath + "[2]");
        //Get data base date.
        publication = Utils.singleReplace(publication);
        DatabaseUtils sql = new DatabaseUtils("jdbc:jtds:sqlserver://192.168.1.101:1433;DatabaseName=LN_DL_SubMgmt_P2", "sa", "Password1");
        String currencyBase = "";
        try {
            ResultSet set = sql.querySQL("SELECT LastUpdatedDate FROM tblDL where DLTitle = '" + publication + "' and IsActive = 1");
            while (set.next()) {
                currencyBase = set.getString("LastUpdatedDate");
            }
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail();
        }

        currencyBase = Utils.datetimeToEnglish(currencyBase);
        writeExcel("dataExpect", currencyBase);
        writeExcel("dataActual", currency);
    }

    /**
     * At the same time download tow books
     */
    public void downloadTowBooks() {
        //boolean isDownload = true;
        screen.waitProgress();
        //Get all did not download the title
        List<WebElement> bookList = screen.findElements(publicationList);
        List<Integer> undownloadNO = new ArrayList<Integer>();
        for (int i = 0; i < bookList.size(); i++) {
            String status = screen.getText(screen.findElement(bookList.get(i), downloadIcon), "Download status");
            if (status.equals("Download")) {
                undownloadNO.add(i + 1);
            }
        }

        //Get did not download title XPATH
        String xpathA = "XPATH://android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(0) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView";
        String xpathB = "XPATH://android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(1) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView";
        report.log(xpathA);
        report.log(xpathB);
        //Click the download button
        screen.click(xpathA, "download button");
        screen.click(xpathB, "download button");
        //Check whether download is completed
		
		/*String statusA = "";
		String statusB = "";
		do {
			statusA = screen.driver.findElementByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(0) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView").getText();
			statusB = screen.driver.findElementByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(1) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView").getText();
			report.log(screen.driver.findElementByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(0) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView").getText());
			report.log(screen.driver.findElementByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(1) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView").getText());
			int i = 0;
			if(i < 100){
				screen.wait(1);
			}else {
				isDownload = false;
				break;
			}
			i++;
		} while (!statusA.equals("Up to date") || !statusB.equals("Up to date"));
		if (isDownload) {
			report.log("[Successful] Download is completed");
		} else {
			report.errorLog("[Fail] Download is failed");
			Assert.fail();
		}*/

        for (int i = 0; i < 50; i++) {
            report.log(screen.driver.findElementByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(0) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView").getText());
            report.log(screen.driver.findElementByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[" + undownloadNO.get(1) + "]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView").getText());
            screen.wait(1);
        }
    }

    public boolean downloadCancel() {
        screen.click(download, "Download");
        screen.click(stop, "Stop");
        screen.click(confirm, "Confirm");

        if (screen.isExistElement(info)) {
            return false;
        } else {
            return true;
        }
    }

}
