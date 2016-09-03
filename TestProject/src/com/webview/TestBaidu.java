package com.webview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities; 
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import bsh.This;

import java.io.File;  
import java.net.URL;  
import java.sql.Driver;
import java.util.List;

//使用手机chrome浏览器自动化测试的样例
public class TestBaidu {

	
	 private AppiumDriver driver; 
     @Before
     public void setUp() throws Exception {
    	//apk地址，不需要安装的话这行不需要
 		//File app = new File("G:\\WonderBuy_v2.0.1.026_201608021459.apk");
 		DesiredCapabilities capabilities=new DesiredCapabilities();
 		//不需要安装的话，去掉这个
 		//capabilities.setCapability("app", app.getAbsolutePath());
 		
 		capabilities.setCapability(CapabilityType.BROWSER_NAME,"Chrome");//设置使用android的chrome浏览器访问		
 		capabilities.setCapability("deviceName", "erverything");
 		capabilities.setCapability("platformVersion","6.0");
 		capabilities.setCapability("platformName", "Android"); 		
 		
 		
 		//微信
// 		capabilities.setCapability("appPackage", "com.wb.wonderbuy");
// 		capabilities.setCapability("appActivity", ".ui.activity.splash.SplashActivity");
 		
 		//支持中文输入
 		capabilities.setCapability("unicodeKeyboard", "True");
 		capabilities.setCapability("resetKeyboard", "True");
 		
 		//安装时 设置不对apk进行重签名，否则有的apk在重签名之后无法正常使用
 		capabilities.setCapability("noSign", "True"); 		
 		
 		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities); 		
 		   
     }
  
     @Test
     public void TestSearch(){
    	
    	//打开百度首页

    	 driver.get("https://www.baidu.com");

    	 //输入框元素

    	 WebElement inputBox = driver.findElement(By.id("index-kw"));

    	 //输入JAVA关键字

    	 inputBox.sendKeys("appium自动化测试");

    	 //百度一下按钮

    	 WebElement searchButton = driver.findElement(By.id("index-bn"));

    	 //点击百度一下按钮

    	 searchButton.click();

    	 try {

    	 Thread.sleep(2000);

    	 } catch (InterruptedException e) {

    	 e.printStackTrace();

    	 }
    
     }    
     
     @After
     public void tearDown() throws Exception {
         //driver.quit();
     }
     
}
