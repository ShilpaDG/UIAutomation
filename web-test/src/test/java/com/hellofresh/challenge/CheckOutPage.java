package com.hellofresh.challenge;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.hellofresh.Utils.Constants;
import com.hellofresh.Utils.WebDriverUtils;

public class CheckOutPage {
	WebDriver driver;
	public ExtentTest logger;
	WebDriverUtils webDriverUtils;
	Constants constants =new Constants();
	
	 public CheckOutPage(WebDriver driver, ExtentTest logger) {
		 this.logger= logger;	
		 this.driver = driver;
		 }
	 
	    public static By FADED = new By.ByXPath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li");
		public static By WOMEN = new By.ByLinkText("Women");
		public static By SUBMIT = new By.ByName("Submit");
		public static By LAYER_CART = new By.ByXPath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']");
		public static By CART_NAVIGATION = new By.ByXPath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']");
		public static By PROCESS_ADDRESS = new By.ByName("processAddress");
		public static By UNIFORM = new By.ById("uniform-cgv");
		public static By PROCESS_CARRIER = new By.ByName("processCarrier");
		public static By BANKWIRE = new By.ByClassName("bankwire");
		public static By CART_nAVIGATION_BUTTON = new By.ByXPath("//*[@id='cart_navigation']/button");
		public static By MY_ACCOUNT = new By.ByCssSelector("h1");
		public static By STEP_ONE = new By.ByXPath("//li[@class='step_done step_done_last four']");
		public static By STEP_END = new By.ByXPath("//li[@id='step_end' and @class='step_current last']");
		public static By CHEQUEs = new By.ByXPath("//*[@class='cheque-indent']/strong");
		
		 
		    public void checkoutTest() throws IOException {
			 webDriverUtils = new WebDriverUtils(driver,logger);
			 webDriverUtils.click(WOMEN);
			 webDriverUtils.click(FADED);
			 webDriverUtils.click(FADED);
			 webDriverUtils.click(SUBMIT);
		     Assert.assertEquals(webDriverUtils.getText(MY_ACCOUNT),constants.MYACCOUNT);
			 webDriverUtils.click(LAYER_CART);
			 webDriverUtils.click(CART_NAVIGATION);
			 webDriverUtils.click(PROCESS_ADDRESS);
			 webDriverUtils.click(UNIFORM);
			 webDriverUtils.click(PROCESS_CARRIER);
			 webDriverUtils.click(BANKWIRE);
			 webDriverUtils.click(CART_nAVIGATION_BUTTON);
			 webDriverUtils.click(WOMEN);
			 
		    }
		    
		    public void checkoutAssertion() throws IOException {
		    	webDriverUtils = new WebDriverUtils(driver,logger);
		      Assert.assertEquals(webDriverUtils.getText(MY_ACCOUNT),constants.MYACCOUNT);
		  	  Assert.assertTrue(webDriverUtils.isDisplayed(STEP_ONE));
		  	 Assert.assertTrue(webDriverUtils.isDisplayed(STEP_END));
		  	  Assert.assertTrue(webDriverUtils.getText(CHEQUEs).contains(constants.ORDER_CONFORMATION_MSG));  
		  	 Assert.assertTrue(driver.getCurrentUrl().contains(constants.ORDER_CONFORMATION_URL));  
		    }
		 
}
