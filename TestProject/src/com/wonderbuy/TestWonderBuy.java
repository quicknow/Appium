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
    	//apk地址，不需要安装的话这行不需要
 		//File app = new File("G:\\WonderBuy_v2.0.1.026_201608021459.apk");
 		DesiredCapabilities capabilities=new DesiredCapabilities();
 		//不需要安装的话，去掉这个
 		//capabilities.setCapability("app", app.getAbsolutePath());
 		
 		capabilities.setCapability(CapabilityType.BROWSER_NAME,"");		
 		capabilities.setCapability("deviceName", "erverything");
 		capabilities.setCapability("platformVersion","6.0");
 		capabilities.setCapability("platformName", "Android");
 		
 		//微信
 		capabilities.setCapability("appPackage", "com.wb.wonderbuy");
 		capabilities.setCapability("appActivity", ".ui.activity.splash.SplashActivity");
 		
 		//支持中文输入
 		capabilities.setCapability("unicodeKeyboard", "True");
 		capabilities.setCapability("resetKeyboard", "True");
 		
 		//安装时 设置不对apk进行重签名，否则有的apk在重签名之后无法正常使用
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
    	
    	//显示等待5s
    	isElementToBeClickable(driver,By.name("我"),5);
    	driver.findElementByName("我").click();
    	
 		
    	//第二种写法
//    	By elementnameBy=By.name("我");
//    	driver.findElement(elementnameBy).click();
 	
 		//显示等待5s
    	//isElementToBeClickable(driver,By.className("android.widget.Button"),5);
    	
    	//选择国家
 		/*driver.findElementByClassName("android.widget.Button").click();
 		
 		//显示等待5s
    	isElementToBeClickable(driver,By.name("国家"),5);
 		driver.findElementByName("国家").click();
 		
 		//此处需增加 滑动操作 		
 		By find_element_by=By.name("franch");
 		//显示等待5s
    	isElementToBeClickable(driver,By.className("android.widget.ListView"),5);
		WebElement swip_element_by=driver.findElementByClassName("android.widget.ListView");
 		swipeUtilElementAppear(driver,find_element_by,swip_element_by,"Up",500);
 		
 		isElementToBeClickable(driver,By.name("好的"),5);
 		driver.findElementByName("好的").click();
 		//返回登陆页
 		isElementToBeClickable(driver,By.xpath("//android.widget.TextView[@text='设置']/preceding-sibling::android.widget.FrameLayout/android.widget.ImageView"),5);
 		driver.findElementByXPath("//android.widget.TextView[@text='设置']/preceding-sibling::android.widget.FrameLayout/android.widget.ImageView").click();
 		*/
 		isElementExist(driver,By.xpath("//android.widget.TextView[@text='用户名']/following-sibling::android.widget.EditText"),5);
 		driver.findElementByXPath("//android.widget.TextView[@text='用户名']/following-sibling::android.widget.EditText").sendKeys("xiaoping");
 		
 		isElementExist(driver,By.xpath("//android.widget.TextView[@text='密码']/following-sibling::android.widget.EditText"),5);
 		driver.findElementByXPath("//android.widget.TextView[@text='密码']/following-sibling::android.widget.EditText").sendKeys("88888888");
 		
 		isElementToBeClickable(driver,By.name("登录"),5);
 		driver.findElementByName("登录").click();		
 		
 	}
     
   //实现在某元素上滑动 duration 默认为毫秒
     public static void swipeOnElement(AppiumDriver driver,WebElement element,String direction,int duration){
    		//x,y分别为元素的起始坐标点
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
    			//System.out.println("执行了Up");
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
     
     
   //在某方向上滑动直至期望的元素出现
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
     
     //判断元素是否存在
     public static void isElementExist(AppiumDriver driver,By by,int timeout){
    		try{
    			new WebDriverWait(driver,timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    			//return true;
    		} catch(Exception e){
    			e.printStackTrace();
    			//return false;
    		}
    	}
     
    //判断元素是否能点击
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
