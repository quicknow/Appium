package com.wonderbuy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities; 

import java.io.File;  
import java.net.URL;  
import java.util.List;

public class TestWonderBuy {

	
	 private AppiumDriver driver; 
     @Before
     public void setUp() throws Exception {
    	//apk��ַ������Ҫ��װ�Ļ����в���Ҫ
 		//File app = new File("G:\\WonderBuy_v2.0.1.026_201608021459.apk");
 		DesiredCapabilities capabilities=new DesiredCapabilities();
 		//����Ҫ��װ�Ļ���ȥ�����
 		//capabilities.setCapability("app", app.getAbsolutePath());
 		
 		capabilities.setCapability(CapabilityType.BROWSER_NAME,"");		
 		capabilities.setCapability("deviceName", "erverything");
 		capabilities.setCapability("platformVersion","6.0");
 		capabilities.setCapability("platformName", "Android");
 		
 		//΢��
 		capabilities.setCapability("appPackage", "com.wb.wonderbuy");
 		capabilities.setCapability("appActivity", ".ui.activity.splash.SplashActivity");
 		
 		//֧����������
 		capabilities.setCapability("unicodeKeyboard", "True");
 		capabilities.setCapability("resetKeyboard", "True");
 		
 		//��װʱ ���ò���apk������ǩ���������е�apk����ǩ��֮���޷�����ʹ��
 		capabilities.setCapability("noSign", "True");
 		
 		
 		
 		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
 		
 		   
     }
  
     @Test
     public void addContact(){
    	
  		
  		try {
  			Thread.sleep(5000);
  			login(driver);
  			
  			
  		} catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  		} finally{
  			
  			//driver.quit();
  		}    
     }    
     
     @After
     public void tearDown() throws Exception {
         //driver.quit();
     }
     
     
     public static void login(AppiumDriver driver) throws Exception{
    	 //appium -a 127.0.0.1 -p 4723  �CU  4ca1558c  --no-reset
    	Thread.sleep(3000);
 		driver.findElementByName("��").click();
 		Thread.sleep(1000);
 		driver.findElementByClassName("android.widget.Button").click();
 		Thread.sleep(1000);
 		driver.findElementByName("����").click();
 		Thread.sleep(2000);
 		driver.findElementByName("�人").click(); 		

 	}
}
