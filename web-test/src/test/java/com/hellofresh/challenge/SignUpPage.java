package com.hellofresh.challenge;

import static junit.framework.TestCase.assertTrue;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.hellofresh.Utils.Constants;
import com.hellofresh.Utils.WebDriverUtils;
import com.hellofresh.factory.DataProviderFactory;

public class SignUpPage {

	WebDriver driver;
	public ExtentTest logger;
	WebDriverUtils webDriverUtils;
	Constants constants =new Constants();
    private static final String CHAR_LIST = 
	        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    private static final int RANDOM_STRING_LENGTH = 3;
	
	 public SignUpPage(WebDriver driver, ExtentTest logger) {
		 this.logger= logger;
		 this.driver = driver;
		 }
	 
    public static By SIGN_UP = new By.ByXPath("//a[@title='Log in to your customer account']");
	 public static By SIGN_OUT = new By.ByXPath("//a[@title='Log me out']");
	public static By EMAIL_CREATE = new By.ById("email_create");
	public static By SUBMIT_CREATION = new By.ById("SubmitCreate");
	public static By GENDER_2 = new By.ById("id_gender2");
	public static By FIRSTNAME = new By.ById("customer_firstname");
	public static By LASTNAME = new By.ById("customer_lastname");
	public static By PASSOWRD = new By.ById("passwd");
	public static By DAYS = new By.ById("days");
	public static By MONTHS = new By.ById("months");
	public static By YEARS = new By.ById("years");
	public static By COMPANY = new By.ById("company");
	public static By ADDRESS1 = new By.ById("address1");
	public static By ADDRESS2 = new By.ById("address2");
	public static By CITY = new By.ById("city");
	public static By STATE = new By.ById("id_state");
	public static By POSTCODE = new By.ById("postcode");
	public static By OTHERS = new By.ById("other");
	public static By PHONE = new By.ById("phone");
	public static By MOBILE = new By.ById("phone_mobile");
	public static By ALIAS = new By.ById("alias");
	public static By SUBMITACCOUNT = new By.ById("submitAccount");
	public static By ACCOUNT = new By.ByClassName("account");
	public static By INFA_ACCOUNT = new By.ByClassName("info-account");
	public static By lOGOUT = new By.ByClassName("logout");
	public static By MY_ACCOUNT = new By.ByCssSelector("h1");
	public static By EMAIL_LOGIN = new By.ById("email");
	public static By SUBMITLOGIN = new By.ById("SubmitLogin");

	public void signInUser() throws IOException {
		
		  
		 
		  webDriverUtils = new WebDriverUtils(driver,logger);
		  webDriverUtils.click(SIGN_UP,1000);
		  webDriverUtils.sendKeys(EMAIL_CREATE, "Germany"+generateRandomString()+"@gamil.com");
		  webDriverUtils.click(SUBMIT_CREATION); 
		  webDriverUtils.click(GENDER_2);
		  webDriverUtils.sendKeys(FIRSTNAME, "Germany"+generateRandomString()+"");
		  webDriverUtils.sendKeys(LASTNAME, "DG"); 
		  webDriverUtils.sendKeys(PASSOWRD,"Qwerty"); webDriverUtils.selectDropDownValueByIndex(DAYS,"1");
		  webDriverUtils.selectDropDownValueByIndex(MONTHS,"1");
		  webDriverUtils.selectDropDownValueByIndex(YEARS,"2000");
		  webDriverUtils.sendKeys(COMPANY, "HelloFresh");
		  webDriverUtils.sendKeys(ADDRESS1, "Qwerty, 123");
		  webDriverUtils.sendKeys(ADDRESS2, "zxcvb");
		  webDriverUtils.sendKeys(ADDRESS2,"zxcvb"); webDriverUtils.sendKeys(CITY, "Qwerty");
		  webDriverUtils.selectDropDownValueByText(STATE,"Colorado");
		  webDriverUtils.sendKeys(POSTCODE, "12345"); 
		  webDriverUtils.sendKeys(OTHERS,"Qwerty, 123"); webDriverUtils.sendKeys(PHONE, "12345123123");
		  webDriverUtils.sendKeys(MOBILE, "12345123123");
		  webDriverUtils.sendKeysWithClear(ALIAS, "hf");
		  webDriverUtils.click(SUBMITACCOUNT);
		 
	}

  public void SignInSuccessAsserts()throws IOException  {
	  webDriverUtils = new WebDriverUtils(driver,logger);
	  Assert.assertEquals(webDriverUtils.getText(MY_ACCOUNT),constants.MYACCOUNT);
	  Assert.assertTrue(webDriverUtils.getText(INFA_ACCOUNT).contains(constants.WELCOME));
	  Assert.assertTrue(webDriverUtils.isDisplayed(lOGOUT));
	  Assert.assertTrue(driver.getCurrentUrl().contains(constants.SIGN_IN_WELCOM_URL)); 
	  webDriverUtils.click(SIGN_OUT,1000);
  }
  
  public void LoginUser()throws IOException  {
	    String fullName = "Joe Black";
	    webDriverUtils = new WebDriverUtils(driver,logger);
	    webDriverUtils.click(SIGN_UP,1000);
		webDriverUtils.sendKeys(EMAIL_LOGIN,DataProviderFactory.getExcelconfig().getData("LogIN", 1, 0));
		webDriverUtils.sendKeys(PASSOWRD,DataProviderFactory.getExcelconfig().getData("LogIN", 1, 1));
		webDriverUtils.click(SUBMITLOGIN);
		Assert.assertEquals(webDriverUtils.getText(ACCOUNT),fullName);

  }
  
  
	     
	    /**
	     * This method generates random string
	     * @return
	     */
	    public String generateRandomString(){
	         
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
	    private int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }
  
}
