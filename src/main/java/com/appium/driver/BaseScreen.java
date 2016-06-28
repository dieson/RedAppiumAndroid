package com.appium.driver;

import java.util.HashMap;
import java.util.Map;

import com.appium.datautils.ExcelUtils;
import com.appium.utils.ReportUtils;

/**
 * Base screen
 * @author Dieson Zuo
 */
public class BaseScreen {
	public ReportUtils report = new ReportUtils();
	public ExcelUtils excel;
	public RedAndroid screen;
	public Map<String, Object> data = new HashMap<String, Object>();
	
	protected void writeExcel(String titleName, String value){
		excel.write(titleName, value);
		report.log(titleName + ":" + value);
		data.put(titleName, value);
	}
}
