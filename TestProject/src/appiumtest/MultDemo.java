package appiumtest;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.*;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class MultDemo {
	
	private AndroidDriver driver;	
	public static String baseUrl ="http://www.sogou.com/";	

	@Parameters({"remoteNodeUrl","devicename"})	
	@BeforeClass
	public void beforeTest(String remoteNodeUrl,String devicename) throws MalformedURLException{
		
		if(devicename.equalsIgnoreCase("192.168.245.102:5555")){
			driver=GetDriver.getRemoteFlash(remoteNodeUrl);
		}else if(devicename.equalsIgnoreCase("192.168.245.101:5555")){
			driver=GetDriver.getRemoteSungXing(remoteNodeUrl);
		}
	}
	
	
	@Test
	public void add() {
		
	    driver.findElementByName("1").click();
	    driver.findElementByName("+").click();
	    driver.findElementByName("2").click();
	    driver.findElementByName("=").click();
	    driver.quit();
	    
	}	
	
	
	@AfterClass
	public void afterTest(){
		//所有测试用例执行后，再关闭浏览器
		driver.quit();
	}
	
	
}