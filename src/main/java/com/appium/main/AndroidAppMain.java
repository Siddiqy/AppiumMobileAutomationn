package com.appium.main;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

public class AndroidAppMain {
	
	AndroidDriver driver;
	WebDriverWait wait;
	
	By menu1 = By.xpath("//android.widget.TextView[@text=\"Grocery\"]");
	By searchBtn = By.xpath("//android.widget.TextView[@text=\"Search\"]");
	By searchFld = By.xpath("//android.widget.EditText[@text=\"Search Products\"]");
	By results = By.xpath("//android.widget.TextView[@text=\"Showing results for\"]");
	By item = By.xpath("//android.widget.TextView[@text=\"ProDentalB Attitude Toothbrush\"]");
	By itemTitle = By.xpath("(//android.widget.TextView[@text=\"ProDentalB Attitude Toothbrush\"])[2]");
	By pdPrice = By.xpath("//android.widget.TextView[@text=\"৳78\"]");
	By pdpOrigin = By.xpath("//android.widget.TextView[@text=\"Product of Malaysia 🇲🇾\"]");
	By plusBtn = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup");
	By plusBtnM = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup");
	By pdCount = By.xpath("(//android.widget.TextView[@text=\"1\"])[2]");
	By closeBtn = By.xpath("//android.widget.TextView[@text=\"Close\"]");
	By cartBtn = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup");
	By delivery = By.xpath("//android.widget.TextView[@text=\"Express Delivery\"]");
	By bagCount = By.xpath("(//android.widget.TextView[@text=\"3\"])[2]");
	By minusBtn = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup");
	By cartMsg = By.xpath("//android.widget.TextView[@text=\"Nothing to see here\"]");
	By backBtn = By.xpath("//android.widget.TextView[@text=\"\"]");
	
	String menuTxt = "Grocery";
	String itemName = "ProDentalB Attitude Toothbrush";
	String resultsTite = "Showing results for";
	String pdpOriginTxt = "Product of Malaysia 🇲🇾";
	String deliveryTxt = "Express Delivery";
	String cartMsgTxt = "Nothing to see here";
	
	public AndroidAppMain(AndroidDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	public void waitFor(int seconds)  {
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	
	public void verifyChaldalAppFunctions() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(menu1));
		String menu = driver.findElement(menu1).getText();
		Assert.assertEquals(menu, menuTxt);
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
		driver.findElement(searchBtn).click();
		waitFor(2);
		WebElement element = driver.findElement(searchFld);
		element.sendKeys("toothbrush");
		waitFor(2);
		String header = driver.findElement(results).getText();
		Assert.assertEquals(header, resultsTite);
		wait.until(ExpectedConditions.elementToBeClickable(item));
		
		waitFor(2);
		driver.findElement(item).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(itemTitle));
		String title = driver.findElement(itemTitle).getText();
		Assert.assertEquals(title, itemName);
		String origin = driver.findElement(pdpOrigin).getText();
		Assert.assertEquals(origin, pdpOriginTxt);

		driver.findElement(plusBtn).click();
		waitFor(1);
		driver.findElement(plusBtnM).click();
		waitFor(1);
		driver.findElement(plusBtnM).click();
		
		waitFor(2);
		driver.findElement(closeBtn).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
		driver.findElement(cartBtn).click();
		String cartItem = driver.findElement(itemTitle).getText();
		Assert.assertEquals(cartItem, itemName);
		String deliveryType = driver.findElement(delivery).getText();
		Assert.assertEquals(deliveryType, deliveryTxt);
		
		for(int i = 0; i < 3; i++) {
			driver.findElement(minusBtn).click();
			waitFor(1);
		}
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bagCount));
		String cartTitle = driver.findElement(cartMsg).getText();
		Assert.assertEquals(cartTitle, cartMsgTxt);
		for(int i = 0; i < 2; i++) {
			driver.findElement(backBtn).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(menu1));
	}
	
}
