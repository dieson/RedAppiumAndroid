package com.appium.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Utility class
 * 
 * @author Dieson Zuo
 * 
 */
public class Utils {
	private static ReportUtils report = new ReportUtils();

	/**
	 * Get execution cmd result
	 * 
	 * @param script
	 * @return The execution result
	 */
	public static ArrayList<String> getExeCmd(String script) {
		BufferedReader br = null;
		ArrayList<String> result = new ArrayList<String>();
		try {
			Process p = exeShell(script);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				result.add(line);
			}
		} catch (Exception e) {
			report.log(e.toString());
		}
		return result;
	}

	/**
	 * Get execution terminal result
	 * 
	 * @param script
	 * @return
	 */
	public static ArrayList<String> getExeTerminal(String script) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Process process = exeShell(script);

			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);

			String line;
			while ((line = input.readLine()) != null) {
				result.add(line);
			}
		} catch (IOException e) {
			report.log(e.toString());
		}
		return result;
	}

	/**
	 * execution shell
	 * 
	 * @param script
	 */
	public static Process exeShell(String script) {
		Process process = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(script);
		} catch (Exception e) {
			report.log(e.toString());
		}
		return process;
	}

	/**
	 * Get the configuration
	 * 
	 * @param key
	 * @return
	 */
	public static String getSystemProperties(String key) {
		InputStream inputStream = null;
		String value = "";

		if (getOs().equals("WINDOWS")) {
			inputStream = FileUtils.class
					.getResourceAsStream("/appiumWindows.properties");
		} else if (getOs().equals("LINUX")) {
			inputStream = FileUtils.class
					.getResourceAsStream("/appiumLinux.properties");
		}

		if (inputStream == null) {
			report.log("appium.properties does not exist!");
			return null;
		}
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
			value = properties.getProperty(key);
		} catch (IOException e) {
			report.log(e.toString());
		}
		return value;
	}

	/**
	 * Get system type
	 * 
	 * @return
	 */
	public static String getOs() {
		String osStr = StringUtils.trimToEmpty(System.getProperty("os.name"))
				.toLowerCase();
		if (osStr.startsWith("win")) {
			return "WINDOWS";
		}
		if (osStr.contains("linux") || osStr.contains("solaris")
				|| osStr.contains("unix") || osStr.contains("mac")) {
			return "LINUX";
		}

		return "OTHER";

	}

	/**
	 * Get the locator type
	 * 
	 * @param locator
	 * @return
	 */
	public static String getLocatorType(String locator) {
		if (locator.contains("XPATH:")) {
			return "XPATH";
		} else if (locator.contains("ID:")) {
			return "ID";
		} else if (locator.contains("CLASS:")) {
			return "CLASS";
		} else if (locator.contains("TAGNAME:")) {
			return "TAGNAME";
		} else if (locator.contains("LINKTEXT:")) {
			return "LINKTEXT";
		} else if (locator.contains("NAME:")) {
			return "NAME";
		} else {
			report.log("[Fail] Unable get locator type");
			return null;
		}
	}

	/**
	 * Get locator string
	 * 
	 * @param locator
	 * @return
	 */
	public static String getLocatorStr(String locator) {
		if (locator.contains("XPATH:")) {
			return locator.replaceFirst("XPATH:", "");
		} else if (locator.contains("ID:")) {
			return locator.replaceFirst("ID:", "");
		} else if (locator.contains("CLASS:")) {
			return locator.replaceFirst("CLASS:", "");
		} else if (locator.contains("TAGNAME:")) {
			return locator.replaceFirst("TAGNAME:", "");
		} else if (locator.contains("LINKTEXT:")) {
			return locator.replaceFirst("LINKTEXT:", "");
		} else if (locator.contains("NAME:")) {
			return locator.replaceFirst("NAME:", "");
		} else {
			report.log("[Fail] Unable get locator string");
			return locator;
		}
	}

	public static void killApp(String packageName) {
		exeShell("adb shell 'am force-stop " + packageName + "'");
	}

	/**
	 * The string of ' replace ''
	 * 
	 * @param str
	 * @return
	 */
	public static String singleReplace(String str) {
		if (str.contains("'")) {
			return str.replaceAll("'", "''");
		} else {
			return str;
		}
	}

	/**
	 * Datetime format switch to English
	 * 
	 * @param str
	 * @return
	 */
	public static String datetimeToEnglish(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		Date date = null;
		String dateString = "";

		try {
			date = format.parse(str);
			dateString = formatter.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateString;
	}

	/**
	 * Get the expired time
	 * @param time
	 * @return
	 */
	public static int getExpiredTime(String time) {
		try {
			if (time == null || time.equals("")) {
				return -1;
			} else if (time.equals("00000000")) {
				return Integer.MAX_VALUE;
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				double dayc = (dateFormat.parse(time).getTime() - System
						.currentTimeMillis()) / (double) (1000 * 60 * 60 * 24);
				int day = (int) dayc;
				if (dayc > 0) {
					day = day + 1;
				}
				return day;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Integer.MAX_VALUE;
	}

}
