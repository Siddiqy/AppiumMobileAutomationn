package com.appium.main;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {
	
    public AndroidDriver driver;
    AppiumDriverLocalService service;
    
	By exitAlert = By.xpath("//android.widget.TextView[@resource-id=\"android:id/alertTitle\"]");
    By exitAppBtn = By.id("android:id/button2");
    
    @BeforeMethod
    public void setupEnv() {
    	UiAutomator2Options options = new UiAutomator2Options()
    			.setAutomationName("UiAutomator2")
    			.setUdid("localhost:5555")
    			.setPlatformName("Android")
    			.setPlatformVersion("9")
    			.setAppPackage("com.chaldal.poached")
    			.setAppActivity("com.chaldal.poached.MainActivity")
    			.setNewCommandTimeout(Duration.ofSeconds(60))
    			.setAutoGrantPermissions(true);
    	
    	service = new AppiumServiceBuilder()
    		       .withIPAddress("127.0.0.1")
    		       .usingPort(4723)
    		       .build();
    	service.start();
    	
    	this.driver = new AndroidDriver(service.getUrl(), options);
    }
    
    @AfterMethod
    public void exitEnv() {
    	driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    	new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(exitAlert));
    	driver.findElement(exitAppBtn).click();
    }
}
