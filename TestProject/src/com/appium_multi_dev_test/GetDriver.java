package com.appium_multi_dev_test;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class GetDriver {
	public static AndroidDriver driver;
	
	//获取SungXing手机Driver对象的封装方法
	public static AndroidDriver getRemotedriver(String devicename,String port) throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, "");// 这句不是必须的
	    capabilities.setCapability("deviceName", devicename); 
	    capabilities.setCapability("platformVersion", "4.3");
	    capabilities.setCapability("platformName", "Android");
	    capabilities.setCapability("appPackage", "com.android.calculator2");
	    capabilities.setCapability("appActivity", ".Calculator");		
		driver = new AndroidDriver(new URL("http://127.0.0.1:"+port+"/wd/hub"),capabilities);
		return driver;
	}
	
	

}

