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
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bsh.This;

import java.io.File;  
import java.net.URL;  
import java.sql.Driver;
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
    	
    	//��ʾ�ȴ�5s
    	isElementToBeClickable(driver,By.name("��"),5);
    	driver.findElementByName("��").click();
    	
 		
    	//�ڶ���д��
//    	By elementnameBy=By.name("��");
//    	driver.findElement(elementnameBy).click();
 	
 		//��ʾ�ȴ�5s
    	//isElementToBeClickable(driver,By.className("android.widget.Button"),5);
    	
    	//ѡ�����
 		/*driver.findElementByClassName("android.widget.Button").click();
 		
 		//��ʾ�ȴ�5s
    	isElementToBeClickable(driver,By.name("����"),5);
 		driver.findElementByName("����").click();
 		
 		//�˴������� �������� 		
 		By find_element_by=By.name("franch");
 		//��ʾ�ȴ�5s
    	isElementToBeClickable(driver,By.className("android.widget.ListView"),5);
		WebElement swip_element_by=driver.findElementByClassName("android.widget.ListView");
 		swipeUtilElementAppear(driver,find_element_by,swip_element_by,"Up",500);
 		
 		isElementToBeClickable(driver,By.name("�õ�"),5);
 		driver.findElementByName("�õ�").click();
 		//���ص�½ҳ
 		isElementToBeClickable(driver,By.xpath("//android.widget.TextView[@text='����']/preceding-sibling::android.widget.FrameLayout/android.widget.ImageView"),5);
 		driver.findElementByXPath("//android.widget.TextView[@text='����']/preceding-sibling::android.widget.FrameLayout/android.widget.ImageView").click();
 		*/
 		isElementExist(driver,By.xpath("//android.widget.TextView[@text='�û���']/following-sibling::android.widget.EditText"),5);
 		driver.findElementByXPath("//android.widget.TextView[@text='�û���']/following-sibling::android.widget.EditText").sendKeys("xiaoping");
 		
 		isElementExist(driver,By.xpath("//android.widget.TextView[@text='����']/following-sibling::android.widget.EditText"),5);
 		driver.findElementByXPath("//android.widget.TextView[@text='����']/following-sibling::android.widget.EditText").sendKeys("88888888");
 		
 		isElementToBeClickable(driver,By.name("��¼"),5);
 		driver.findElementByName("��¼").click();		
 		
 	}
     
   //ʵ����ĳԪ���ϻ��� duration Ĭ��Ϊ����
     public static void swipeOnElement(AppiumDriver driver,WebElement element,String direction,int duration){
    		//x,y�ֱ�ΪԪ�ص���ʼ�����
    		int x = element.getLocation().getX();
    		int y = element.getLocation().getY();
    		int elementWidth = element.getSize().getWidth();
    		int elementHight= element.getSize().getHeight();
    		switch(direction){
    		case "Up":
    			int startx = x+elementWidth/2;
    			int starty = y+elementHight*4/5;
    			int endy=y+elementHight*1/5;
    			driver.swipe(startx,starty,startx,endy,duration);
    			//System.out.println("ִ����Up");
    			break;
    		case "Down":
    			startx = x+elementWidth/2;
    			starty = y+elementHight*1/5;
    			endy = y + elementHight*4/5;
    			driver.swipe(startx,starty,startx,endy,duration);
    			break;
    		case "Light":
    			starty = x+elementHight/2;
    			startx = y+elementWidth*4/5;
    			int endx=y + elementWidth*1/5;
    			driver.swipe(startx,starty,endx,starty,duration);
    			break;
    		case "Right":
    			starty = x+elementHight/2;
    			startx = y+elementWidth*1/5;
    			endx=y + elementWidth*4/5;
    			driver.swipe(startx,starty,endx,starty,duration);
    			break;
    		default:
    			break;
    		}
     }
     
     
   //��ĳ�����ϻ���ֱ��������Ԫ�س���
     public static void swipeUtilElementAppear(AppiumDriver driver,By by, WebElement element, String direction, int duration){
     	boolean flag=true;     	
     	while(flag){
     		try{
     			driver.findElement(by).click();
     			flag=false;
     		   } catch(Exception e){
     			swipeOnElement(driver,element,direction,duration);
     		   }
     	}


     }
     
     //�ж�Ԫ���Ƿ����
     public static void isElementExist(AppiumDriver driver,By by,int timeout){
    		try{
    			new WebDriverWait(driver,timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    			//return true;
    		} catch(Exception e){
    			e.printStackTrace();
    			//return false;
    		}
    	}
     
    //�ж�Ԫ���Ƿ��ܵ��
     public static void isElementToBeClickable(AppiumDriver driver,By by,int timeout){
 		try{
 			new WebDriverWait(driver,timeout).until(ExpectedConditions.presenceOfElementLocated(by));
 			//return true;
 		} catch(Exception e){
 			e.printStackTrace();
 			//return false;
 		}
 	}
     
}
