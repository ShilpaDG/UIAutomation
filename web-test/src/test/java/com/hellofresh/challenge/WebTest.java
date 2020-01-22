package com.hellofresh.challenge;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.hellofresh.dataprovider.PropertiesRead;
import com.hellofresh.factory.BrowserFactory;
import com.hellofresh.factory.DataProviderFactory;

public class WebTest extends BaseClass{	
	
	public static String PROPERTIES_PATH = System.getProperty("user.dir") + File.separator + "Configuration/";
	static PropertiesRead prop = new PropertiesRead(PROPERTIES_PATH + File.separator + "application.properties");
	WebDriver driver;
	SignUpPage signup;
	CheckOutPage chekout;
	
	/**
	 * Launch Browser with application url
	 * */
	@BeforeClass
	public void launchApplication() {
		driver =BrowserFactory.getBrowswer(prop.getProperty("BrowserType"));
		driver.get(DataProviderFactory.getCofig().getApplicationUrl());
		
	}
	
	/**
	 * Register in Application
	 * @throws IOException 
	 * */
	@Test(priority =1)
	public void signInTest() throws IOException {
		logger=extent.createTest("signIn Test");
		signup = new SignUpPage(driver,logger);
	    signup.signInUser();
	    logger.info("Assertion after Successfull SigIn");
	    signup.SignInSuccessAsserts();
	}
	
	/**
	 * Login With Existing User
	 * @throws IOException 
	 * */
	@Test(priority =2)
	public void LogIntest() throws IOException {
		logger=extent.createTest("Log In Test");
		signup=new SignUpPage(driver,logger);
		logger.info("Log In User ");
		signup.LoginUser();
	}
	
	/**
	 * Check Out test
	 * @throws IOException 
	 * */
	@Test(dependsOnMethods={"LogIntest"})
	public void CheckOuttest() throws IOException {
		logger=extent.createTest("Check Out Test");
		chekout=new CheckOutPage(driver,logger);
		chekout.checkoutTest();
		logger.info("Assertion after Successfull checkOut");
		chekout.checkoutAssertion();
	}
	
	/***
	 * Close brower After execution of class
	 */
	@AfterClass
	public void closeBroswer()
	{
		driver.close();
		driver.quit();
	}

}
