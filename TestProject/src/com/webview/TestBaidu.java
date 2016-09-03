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

//ʹ���ֻ�chrome������Զ������Ե�����
public class TestBaidu {

	
	 private AppiumDriver driver; 
     @Before
     public void setUp() throws Exception {
    	//apk��ַ������Ҫ��װ�Ļ����в���Ҫ
 		//File app = new File("G:\\WonderBuy_v2.0.1.026_201608021459.apk");
 		DesiredCapabilities capabilities=new DesiredCapabilities();
 		//����Ҫ��װ�Ļ���ȥ�����
 		//capabilities.setCapability("app", app.getAbsolutePath());
 		
 		capabilities.setCapability(CapabilityType.BROWSER_NAME,"Chrome");//����ʹ��android��chrome���������		
 		capabilities.setCapability("deviceName", "erverything");
 		capabilities.setCapability("platformVersion","6.0");
 		capabilities.setCapability("platformName", "Android"); 		
 		
 		
 		//΢��
// 		capabilities.setCapability("appPackage", "com.wb.wonderbuy");
// 		capabilities.setCapability("appActivity", ".ui.activity.splash.SplashActivity");
 		
 		//֧����������
 		capabilities.setCapability("unicodeKeyboard", "True");
 		capabilities.setCapability("resetKeyboard", "True");
 		
 		//��װʱ ���ò���apk������ǩ���������е�apk����ǩ��֮���޷�����ʹ��
 		capabilities.setCapability("noSign", "True"); 		
 		
 		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities); 		
 		   
     }
  
     @Test
     public void TestSearch(){
    	
    	//�򿪰ٶ���ҳ

    	 driver.get("https://www.baidu.com");

    	 //�����Ԫ��

    	 WebElement inputBox = driver.findElement(By.id("index-kw"));

    	 //����JAVA�ؼ���

    	 inputBox.sendKeys("appium�Զ�������");

    	 //�ٶ�һ�°�ť

    	 WebElement searchButton = driver.findElement(By.id("index-bn"));

    	 //����ٶ�һ�°�ť

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
