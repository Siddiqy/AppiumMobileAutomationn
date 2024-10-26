package com.appium.test;

import org.testng.annotations.Test;

import com.appium.main.AndroidAppMain;
import com.appium.main.BaseClass;

public class AndroidAppTest extends BaseClass {
	
	@Test(priority = 0, description="Testing All The Core Functions Of Chaldal App")
	public void chaldalAppFunctionsTest() {
		AndroidAppMain appmain = new AndroidAppMain(driver);
		appmain.verifyChaldalAppFunctions();
	}
}
