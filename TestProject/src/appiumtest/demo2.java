package appiumtest;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//注意测试时需将 计算器打开
public class demo2 {

private AndroidDriver driver;

@Before
public void calc() throws Exception {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.BROWSER_NAME, "");// 这句不是必须的
    capabilities.setCapability("deviceName", "VKAMHEKRK7DAGIE6"); //VKAMHEKRK7DAGIE6 Android Emulator
    capabilities.setCapability("platformVersion", "4.3");
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("appPackage", "com.android.calculator2");
    capabilities.setCapability("appActivity", ".Calculator");
    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
  

}

@After
public void tearDown() throws Exception {
    driver.quit();
}

@Test
public void add() {
    driver.findElementByName("1").click();
    driver.findElementByName("+").click();
    driver.findElementByName("2").click();
    driver.findElementByName("=").click();
    driver.quit();
    }
}