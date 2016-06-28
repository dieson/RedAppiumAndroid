package com.appium.utils;

import java.util.ArrayList;

/**
 * 	Get the device information
 * @author Dieson Zuo
 *
 */
public class OsUtils {
	private static ReportUtils report = new ReportUtils();
	
	/**
	 * Get the device name.
	 * @return device name
	 */
	public static String getDeviceName(){
		String deviceName = "";
		ArrayList<String> devices = new ArrayList<String>();
		
		if(Utils.getOs().equals("LINUX")){
			devices = Utils.getExeTerminal("/Users/lexisnexis/Documents/Jenkins/android-sdk-macosx/platform-tools/adb devices");
		}else if(Utils.getOs().equals("WINDOWS")){
			devices = Utils.getExeCmd("adb devices");
		}else{
			report.errorLog("Unable to get the device name");
			return null;
		}
		
		if(devices.get(1).contains("device")){
			String name = devices.get(1);
			deviceName = name.replace("device", "");
			report.log("DeviceName：" + deviceName);
		}else{
			report.errorLog("Unable to get the device name");
			return null;
		}
		return deviceName;
	}
	
	/**
	 * Get the App exist.
	 * @return device name
	 */
	public static boolean isAppExist(String appPackage){
		ArrayList<String> devices = Utils.getExeCmd("adb shell pm list packages | find '" + appPackage + "'");
		String msg = devices.get(0);
		if(msg.contains(appPackage)){
			return true;
		}else{
			return false;
		}
	}
	
}
