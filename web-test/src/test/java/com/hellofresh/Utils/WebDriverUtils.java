package com.hellofresh.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class WebDriverUtils {

	WebDriver driver;
	static WebElement locator = null;
	private static int longTimeout = 30;
	public ExtentTest logger;

	public WebDriverUtils(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	public void click(By by) throws IOException {
		WebElement locator = null;
		try {
			locator = waitForElementToBeClickable(by);
			if (null != locator) {
				logger.info("Clicking the element: " + by);
				locator.click();
				waitForPageLoad();

			}
		} catch (Exception e) {
			logger.fail("Exception in the click method: ",MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
	}

	public boolean isDisplayed(By by) throws IOException {
		WebElement locator = null;
		try {
			locator = waitForElementToBeClickable(by);
			if (null != locator) {
				logger.info("Clicking the element: " + by);
				locator.isDisplayed();
				waitForPageLoad();
				return true;
			}
		} catch (Exception e) {
			logger.fail("Exception in the click method: ",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
			return false;
		}
		return true;
	}

	public void sendKeys(By by, String text) throws IOException {
		locator = null;
		try {
			locator = waitForElementToBeVisible(by);
			if (null != locator) {
				logger.info("Entering the text in the element");
				locator.sendKeys(text);
				waitForPageLoad();
			}
		} catch (Exception e) {
			logger.fail("Issue in sendKeys Method: ",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
	}

	/*
	 * public void sendKeys(By by, String text, int timeout) throws IOException {
	 * locator = null; try { locator = waitForElementToBeVisible(by, timeout); if
	 * (null != locator) { logger.info("Entering the text in the element");
	 * locator.sendKeys(text); waitForPageLoad(); } } catch (Exception e) {
	 * logger.fail("Issue in sendKeys Method: ",
	 * MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
	 * } }
	 */

	public WebElement waitForElementToBeVisible(By locator) throws IOException {
		WebElement element = null;
		try {
			if (locator != null) {
				WebDriverWait wait = new WebDriverWait(driver, longTimeout);
				element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				if (element != null) {
					logger.info("Element is present: " + locator);
				} else {
					logger.info("Element does not present: " + locator);
				}
			}
		} catch (Exception e) {
			logger.fail("Exception in waitForElementToBeVisible method:",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
		return element;
	}

	public WebElement waitForElementToBeVisible(By locator, int timeOut) throws IOException {
		WebElement element = null;
		try {
			if (locator != null) {
				WebDriverWait wait = new WebDriverWait(driver, timeOut);
				if (null != wait.until(ExpectedConditions.presenceOfElementLocated(locator))) {
					logger.info("Element is present");
					element = driver.findElement(locator);
				}
			}
		} catch (Exception e) {
			logger.fail("Exception in waitForElementToBeVisible method:",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
		return element;
	}

	public boolean waitForPageLoad() {
		int leadsZero = longTimeout;
		while (leadsZero != 0) {
			Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
			if (isJqueryCallDone) {
				return true;
			}
			try {
				Thread.sleep(1000);
				leadsZero--;
			} catch (Exception e) {
				logger.info("Page could not load in the specified time: " + longTimeout);
			}
			System.out.println("jQuery is loading...");
		}
		return false;
	}

	public WebElement waitForElementToBeClickable(By locator) throws IOException {
		WebElement element = null;
		try {
			if (locator != null) {
				WebDriverWait wait = new WebDriverWait(driver, longTimeout);
				if (null != wait.until(ExpectedConditions.visibilityOfElementLocated(locator))) {
					logger.info("Element is clikable");
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				}
			}
		} catch (Exception e) {
			logger.fail("Exception in waitForElementToBeClickable method:",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
		return element;
	}

	public void click(By by, int timeout) throws IOException {
		WebElement locator = null;
		try {
			locator = waitForElementToBeClickable(by, timeout);
			if (null != locator) {
				logger.info("Clicking the element");
				locator.click();
				waitForPageLoad();
			}
		} catch (Exception e) {
		}
	}

	public void selectDropDownValueByIndex(By by, String number) throws IOException {
		try {
			Select select = new Select(driver.findElement(by));
			select.selectByValue(number);
		} catch (Exception e) {
			logger.fail("Exception in the dropdown method: ",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
	}

	public void selectDropDownValueByText(By by, String text) throws IOException {
		try {
			Select select = new Select(driver.findElement(by));
			select.selectByVisibleText(text);
		} catch (Exception e) {
			logger.fail("Exception in the dropdown method: "
					+ MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
	}

	public WebElement waitForElementToBeClickable(By locator, int timeout) throws IOException {
		WebElement element = null;
		try {
			if (locator != null) {
				WebDriverWait wait = new WebDriverWait(driver, timeout);
				if (null != wait.until(ExpectedConditions.elementToBeClickable(locator))) {
					logger.info("Element is clikable");
					element = driver.findElement(locator);
				}
			}
		} catch (Exception e) {
			logger.fail("Exception in waitForElementToBeClickable method:",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
		return element;
	}

	public void sendKeysWithClear(By by, String text) throws IOException {
		locator = null;
		try {
			locator = waitForElementToBeVisible(by);
			if (null != locator) {
				logger.info("Entering the text in the element");
				locator.clear();
				locator.sendKeys(text);
				waitForPageLoad();
			}
		} catch (Exception e) {
			logger.fail("Issue in sendKeys Method: ",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
	}

	public String getText(By by) throws IOException {
		WebElement locator = null;
		String actualtitle = "";
		try {
			locator = waitForElementToBeVisible(by);
			if (null != locator) {
				actualtitle = locator.getText().trim();
			}
		} catch (Exception e) {
			logger.fail("Exception in the verifyTitle method for the expected string: " + "======",
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot()).build());
		}
		return actualtitle;
	}

	public String captureScreenshot() {

		String filePath = System.getProperty("user.dir") + File.separator + "/Sreenshots/ScreenShot" + surrentDataTime()
				+ ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(src, new File(filePath));
		} catch (IOException e) {

		}
		return filePath;
	}

	public String surrentDataTime() {
		String pattern = "HHmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		return date;
	}

}
