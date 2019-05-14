package com.common;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * 
 * The Class actions.
 */
public class CommonActions  {

	/** The Constant TodaysDate. */
	private static final String TodaysDate = null;

	/** The Constant PastDate. */
	private static final String PastDate = null;

	/** The Constant FutureDate. */
	private static final String FutureDate = null;

	/** The m d. */
	public static WebDriver mD;

	/** The Test Suite Path. */

	public static String TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuite.xls";

	/** The Test repo folder. */
	public static String TestRepoFolder = System.getProperty("user.dir") + "/src/test/java/com/testRepository/";

	/** The Test object repository. */
	public static String TestObjectRepository = System.getProperty("user.dir") + "/src/test/resources/com/resources/";

	/** The Test suite result file. */
	public static String foldname, TestSuiteResultFile;
	
	/** The Webelement variable **/
	
	public static WebElement From, To;

	/** The x env. */
	public static String xTC[][], xTS[][], xTD[][], xTSuite[][], xStepSheet[][], xEnv[][]; // 2-D
																							// array
																							// to
																							// hold
																							// Test
																							// Cases
																							// and
																							// Steps.
																							// GLOBAL.

	/** The x t step_cols. */
	public static int xTC_rows, xTC_cols, xTS_rows, xTS_cols, xTD_rows, xTD_cols, xTSuite_rows, xTSuite_cols, xEnv_rows,
			xEnv_cols, xTStep_rows, xTStep_cols;

	/** The v obj test suite. */
	public static String vKeyword, vObject, vLocatorType, vTestData, vIP1, vIP2, vIP3, vtemp_return, Val, testStep,
			ts_result, ts_error, tc_result, vObjTestSuite;

	/** The v sleep. */
	public static Long vSleep;

	/** The v error. */
	public static String vResult, vError;

	/** The xl path. */
	public static String xlPath;

	/** The stepsheet. */
	public static String vTCID, vTSID, vTDID, vTSuiteID, stepsheet;

	/** The stepsheetpath. */
	public static String vTC_flag, stepsheetpath;

	/** The l. */
	public static int vTCStartRow, vTCEndRow, tc_fail, tc_pass, tc_total, tc_executed, k, l;

	/** The split data. */
	public static String[] splitData;

	/** The application logs. */
	public static Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");

	/** The env data. */
	HashMap<String, String> envData = new HashMap();
	
	/** The variable used to store the identifierValue fetched from DB. */
	public static String identifierValue;

	 
	/**
	 * MethodName: converttoInt() Objective: Converting the string value to
	 * integer.
	 * 
	 * @param val
	 *            gets the string value
	 * @return the integer value
	 * @throws NumberFormatException
	 *             Signals that the string does not have the appropriate format.
	 */
	public int converttoInt(String val) throws NumberFormatException {
		APPLICATION_LOGS.debug("Entry: converttoInt");
		double c = Double.parseDouble(val);
		int t = (int) c;
		APPLICATION_LOGS.debug("Exit: converttoInt");
		return t;
	}
	
	
 
	public void PassSingleTab() throws Exception {

		APPLICATION_LOGS.debug("Enter: Tab Action performed");

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		APPLICATION_LOGS.debug("Exit: Tab Action performed");

	}

	public void PassSingleDownArrow() throws Exception {

		APPLICATION_LOGS.debug("Enter: DownArrow Action performed");

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);

		APPLICATION_LOGS.debug("Exit: DownArrow Action performed");

	}
	
	
	public void  enterTab(String locatorType, String locatorObject ) throws NumberFormatException {
		APPLICATION_LOGS.debug("Enter: Tab Action performed");
		System.out.println("Action performed"); 
		WebElement webElement = locateElement(locatorType, locatorObject);//You can use xpath, ID or name whatever you like
		webElement.sendKeys(Keys.TAB);
		webElement.sendKeys(Keys.ENTER);
		APPLICATION_LOGS.debug("Exit: Tab Action performed");
		System.out.println("Action done");
	}
	 

	/**
	 * MethodName:navigateTo() Objective : This method is used to navigate to
	 * the specified URL.
	 * 
	 * @param fURL
	 *            fetches the care tool application URL.
	 * @throws Exception
	 *             :Signals that the WebDriverException might have occured.
	 */
	public void navigateTo(String fURL) throws Exception {

		APPLICATION_LOGS.debug("Entry: navigateTo");
		if ((System.getProperty("Browser") == null)) {
			String Browser = getEnvProp("Browser");

			switch (Browser) {
			case "IE":
				File file = new File(System.getProperty("user.dir") + "/BrowserDrivers/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				//Runtime.getRuntime().exec(System.getProperty("user.dir") + "/lib/test.exe");
				//Thread.sleep(10000);
				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
				dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
				dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
				//dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); 
				dc.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.TOP); 
				dc.setJavascriptEnabled(true);
				dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.IGNORE);
				mD = new InternetExplorerDriver(dc);
				break;

			case "FF":
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("Automation");
				mD = new FirefoxDriver(myprofile);
				break;

			case "Safari":
				mD = new SafariDriver();
				break;

			case "Chrome":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("--disable-extensions");
				options.addArguments("no-sandbox");
				options.addArguments("ignore-certificate-errors");
				options.addArguments("--allow-running-insecure-content");
				options.addArguments("chrome.switches");
				options.addArguments("--test-type");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.binary",
						System.getProperty("user.dir") + "/BrowserDrivers/chromedriver.exe");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/BrowserDrivers/chromedriver.exe");
				mD = new ChromeDriver(options);
				APPLICATION_LOGS.debug("Browser Launched successfully");
				break;
			}
		}

		else {
			String Browser = System.getProperty("Browser");

			switch (Browser) {
			case "IE":
				File file = new File(System.getProperty("user.dir") + "/BrowserDrivers/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				//Runtime.getRuntime().exec(System.getProperty("user.dir") + "/lib/test.exe");
				//Thread.sleep(10000);
				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
				dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
				dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
				//dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); 
				dc.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.TOP); 
				dc.setJavascriptEnabled(true);
				dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				mD = new InternetExplorerDriver(dc);
				break;

			case "FF":
				mD = new FirefoxDriver();
				break;

			case "Safari":
				mD = new SafariDriver();
				break;

			case "Chrome":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				options.addArguments("--disable-extensions");
				options.addArguments("no-sandbox");
				options.addArguments("ignore-certificate-errors");
				options.addArguments("--allow-running-insecure-content");
				options.addArguments("chrome.switches");
				options.addArguments("--test-type");
				options.addArguments("start-maximized");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.binary",
						System.getProperty("user.dir") + "/BrowserDrivers/chromedriver.exe");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/BrowserDrivers/chromedriver.exe");
				mD = new ChromeDriver(options);
				APPLICATION_LOGS.debug("Browser Launched successfully");
				break;
			}

		}
		
		mD.manage().window().maximize();
		//mD.manage().window().setSize(new Dimension(300,500));

		if ((System.getProperty("env") == null)) {
			mD.navigate().to(fURL);
		}

		else {
			String env = System.getProperty("env");
			switch (env) {

			case "CViDEV":
				fURL = getEnvProp("CViDEV");
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;

			case "CVQA":
				fURL = getEnvProp("CVQA");
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;

			case "CVPROD":
				fURL = getEnvProp("CVPROD");
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			
			case "CTBPCIQA":
				fURL = getEnvProp("CTBPCIQA");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;

			case "CTBPCIUAT":
				fURL = getEnvProp("CTBPCIUAT");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;	
			
			case "CTHHQA":
				fURL = getEnvProp("CTHHQA");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
				
			case "CTHFQA":
				fURL = getEnvProp("CTHFQA");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			
			case "CTHHARQA":
				fURL = getEnvProp("CTHFQA");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;

			case "CTCCQA":
				fURL = getEnvProp("CTCCQA");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
				
			case "CTQA":
				fURL = getEnvProp("CTQA");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;

			case "CTUAT":
				fURL = getEnvProp("CTUAT");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			
			case "CTDEV":
				fURL = getEnvProp("CTDEV");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;	
			
			case "CTBUGFIX":
				fURL = getEnvProp("CTBUGFIX");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			
			case "CTTraining":
				fURL = getEnvProp("CTTraining");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			
			case "CTPREPROD":
				fURL = getEnvProp("CTPREPROD");
				System.out.println("Application URL:"+fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			
			case "CTQA_IE":
				fURL = getEnvProp("CTQA_IE");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(30000);
				break;
				
			case "CTUAT_IE":
				fURL = getEnvProp("CTUAT_IE");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(30000);
				break;
			
			case "CTiDev":
				fURL = getEnvProp("CTiDev");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			
			case "CTAppSrvr1":
				fURL = getEnvProp("CTAppSrvr1");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			
			case "CTAppSrvr2":
				fURL = getEnvProp("CTAppSrvr2");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			
			case "CTAppDRSrvr":
				fURL = getEnvProp("CTAppDRSrvr");
				System.out.println(fURL);
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			
			default:
				System.out.println("Invalid URL");
				mD.navigate().to(fURL);
				Thread.sleep(2000);
				break;
			}
			APPLICATION_LOGS.debug("Exit: navigateTo");
		}
	}

	public WebElement locateElementByXpath(String fxPath) {
		APPLICATION_LOGS.debug("Entry: locateElement");
		WebDriverWait wait = new WebDriverWait(mD, 180);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fxPath)));
		APPLICATION_LOGS.debug(fxPath + " element identified successfully");
		APPLICATION_LOGS.debug("Exit: locateElement");
		return element;

	}

	/**
	 * MethodName: locateElement() Objective : This method is used to locate
	 * Element for some time until visibility of element conditions meet.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @return the web element
	 * @throws ElementNotVisibleException
	 *             Signals that the element is not visible on the page.
	 */
	public WebElement locateElement(String locatorType, String locatorObject) {
		APPLICATION_LOGS.debug("Entry: locateElement");
		WebDriverWait wait = new WebDriverWait(mD, 180);
		WebElement element = null;
		APPLICATION_LOGS.debug(locatorObject + " element identified successfully");
		APPLICATION_LOGS.debug("Exit: locateElement");
		switch (locatorType) {
		case "id":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorObject)));
			APPLICATION_LOGS.debug(locatorObject + " element identified successfully");
			APPLICATION_LOGS.debug("Exit: locateElement");
			break;
		case "name":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorObject)));
			APPLICATION_LOGS.debug(locatorObject + " element identified successfully");
			APPLICATION_LOGS.debug("Exit: locateElement");
			break;
		case "linkText":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorObject)));
			APPLICATION_LOGS.debug(locatorObject + " element identified successfully");
			APPLICATION_LOGS.debug("Exit: locateElement");
			break;
		case "partialLinkText":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorObject)));
			APPLICATION_LOGS.debug(locatorObject + " element identified successfully");
			APPLICATION_LOGS.debug("Exit: locateElement");
			break;
		case "className":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorObject)));
			APPLICATION_LOGS.debug(locatorObject + " element identified successfully");
			APPLICATION_LOGS.debug("Exit: locateElement");
			break;
		case "cssSelector":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorObject)));
			APPLICATION_LOGS.debug(locatorObject + " element identified successfully");
			APPLICATION_LOGS.debug("Exit: locateElement");
			break;
		case "xpath":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorObject)));
			APPLICATION_LOGS.debug(locatorObject + " element identified successfully");
			APPLICATION_LOGS.debug("Exit: locateElement");
			break;
		case "tagName":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorObject)));
			APPLICATION_LOGS.debug(locatorObject + " element identified successfully");
			APPLICATION_LOGS.debug("Exit: locateElement");
			break;
		default:
			System.out.println("Invalid locator type defined. Please provide correct locator type.");
			break;
		}
		return element;
	}

	/**
	 * MethodName: enterText() Objective: This method is used to enter Text in
	 * web element usually textbox.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @param locatorType
	 * @param locatorObject
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public void enterText(String locatorType, String locatorObject, String fText) throws NoSuchElementException 
	{
		APPLICATION_LOGS.debug("Entry: enterText");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();

		if (fText.equals("TodaysDate")) {
			cal.setTime(date);
			fText = dateFormat.format(date);
			locateElement(locatorType, locatorObject).sendKeys(dateFormat.format(cal.getTime()));
		}

		else if (fText.equals("PastDate")) {
			cal.add(Calendar.DATE, -2);
			Date dateBefore2Days = cal.getTime();
			String fTextPast = dateFormat.format(dateBefore2Days);
			locateElement(locatorType, locatorObject).sendKeys(fTextPast);
		}

		else if (fText.equals("FutureDate")) {

			cal.add(Calendar.DATE, +2);
			Date dateafter2Days = cal.getTime();
			fText = dateFormat.format(dateafter2Days);
			locateElement(locatorType, locatorObject).sendKeys(fText);
		}
		else if (fText.equals("StartDate")) {
			String startDate= locateElement("id", "tocCriticalElementsStartDate").getText().trim();
			locateElement(locatorType, locatorObject).sendKeys(startDate);
		}

		else
			
			locateElement(locatorType, locatorObject).sendKeys(fText);
		APPLICATION_LOGS.debug(fText + " entered successfully in TextBox");
		APPLICATION_LOGS.debug("Exit: enterText");
		
	}

	/**
	 * MethodName: clickElement() Objective:This method is used to click
	 * respective web element.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public void clickElement(String locatorType, String locatorObject) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: clickElement");
		try {
			WebElement locateElement = locateElement(locatorType, locatorObject);
			WebElement elementToClick = new WebDriverWait(mD, 120).until(ExpectedConditions.elementToBeClickable(locateElement));
			elementToClick.click();
			APPLICATION_LOGS.debug(locatorObject + " element Clicked successfully");
		} catch (NoSuchElementException e) {
			APPLICATION_LOGS.debug(e.getMessage());
		}
		APPLICATION_LOGS.debug("Exit: clickElement");
	}

	/**
	 * Hover mouse pointer
	 * @param locatorType
	 * @param locatorObject
	 * @throws NoSuchElementException
	 */
	public void mouseHover(String locatorType, String locatorObject) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: mouseHover");
		try {
			WebElement element = locateElement(locatorType, locatorObject);
			Actions action = new Actions(mD);
			action.moveToElement(element).perform();
		} catch (Exception e) {
			APPLICATION_LOGS.debug("fxPath: Mouse Hover function does not work");
		}
		APPLICATION_LOGS.debug("Exit: mouseHover");
	}

	/**
	 * MethodName: clickElementbyId() Objective:This method is used to click
	 * respective web element using Id as the locator.
	 *
	 * @param fxId
	 *            denotes Id of the WebElement.
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public void clickElementbyId(String fxId) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: clickElementbyId");
		try {
			mD.findElement(By.id(fxId)).click();
			APPLICATION_LOGS.debug(fxId + " Element Clicked successfully");
		} catch (Exception e) {
			APPLICATION_LOGS.debug("Element not found");
		}
		APPLICATION_LOGS.debug("Exit: clickElementbyId");
	}

	/**
	 * MethodName: clearText() Objective:This method is used to clear the Text
	 * from the Web Element
	 * 
	 * @param locatorType
	 * @param locatorObject
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public void clearText(String locatorType, String locatorObject) {
		APPLICATION_LOGS.debug("Entry: clearText");
		locateElement(locatorType, locatorObject).clear();
		APPLICATION_LOGS.debug("Text Cleared successfully");
		APPLICATION_LOGS.debug("Exit: clearText");
	}

	/**
	 * MethodName: clickLink() Objective:This method uses partial Link Text
	 * locator to click on Web Element.
	 *
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public void clickLink(String locatorType, String locatorObject) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: clickLink");
		try {
			locateElement(locatorType, locatorObject).click();
			APPLICATION_LOGS.debug(locatorObject + " link Clicked successfully");
		} catch (Exception e) {
			APPLICATION_LOGS.debug(locatorObject + " link not Clicked successfully");

		}
		APPLICATION_LOGS.debug("Exit: clickLink");
	}

	/**
	 * MethodName: getText() Objective:This method is used to retrieve text from
	 * a given web element.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @return the text
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public void getText(String locatorType, String locatorObject, String fText) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: getText");
		String var = locateElement(locatorType, locatorObject).getText();
		if (fText.contains("^")) {
			envData.put(fText.replace("^", ""), var);
		}
		APPLICATION_LOGS.debug("Exit: getText");
	}

	/**
	 * MethodName: verifyText() Objective:This method is used to verify Text by
	 * comparing data from the webelement with the data provided in the test
	 * sheet.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @return the string
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public String verifyText(String locatorType, String locatorObject, String fText) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: verifyText");
		String fTextOut = locateElement(locatorType, locatorObject).getText();
		if (fText.contains("^")) {
			fText = envData.get(fText.replace("^", ""));
		}
		if ((fTextOut.trim()).equals(fText.trim())) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + fTextOut + " matched with " + fText + " successfully");
			return "Pass";
		} else {
			ts_error = "text verification failed";
			APPLICATION_LOGS.debug("" + fTextOut + " not matched with " + fText + " successfully");
			APPLICATION_LOGS.debug("Exit: verifyText");
			return "Fail";
		}
	}

	public String verifyTextIgnoreCase(String locatorType, String locatorObject, String fText)
			throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: verifyTextIgnoreCase");
		String fTextOut = locateElement(locatorType, locatorObject).getText();
		if ((fTextOut.trim()).equalsIgnoreCase(fText.trim())) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + fTextOut + " matched with " + fText + " successfully");
			return "Pass";
		} else {
			ts_error = "text verification failed";
			APPLICATION_LOGS.debug("" + fTextOut + " not matched with " + fText + " successfully");
			APPLICATION_LOGS.debug("Exit: verifyText");
			return "Fail";
		}
	}

	/**
	 * MethodName: containsText() Objective:This method is used to verify that
	 * the data retrieved from the web element contains data provided in the
	 * test sheet.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @return the string
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public String containsText(String locatorType, String locatorObject, String fText) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: containsText");
		if (fText.equals("TodaysDate")) {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			fText = dateFormat.format(date);
			String expText = fText.toLowerCase();
			String fTextOut = locateElement(locatorType, locatorObject).getText().toLowerCase();
			if (fTextOut.contains(expText)) {
				ts_error = "No Error";
				APPLICATION_LOGS.debug("" + fTextOut + " contains " + fText + "");
				return "Pass";
			} else {
				ts_error = "Text not found";
				APPLICATION_LOGS.debug("" + fTextOut + " does not contains " + fText + "");
				APPLICATION_LOGS.debug("Exit: containsText");
				return "Fail";
			}
			
		}else{
		String expText = fText.toLowerCase();
		String fTextOut = locateElement(locatorType, locatorObject).getText().toLowerCase();
		if (fTextOut.contains(expText)) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + fTextOut + " contains " + fText + "");
			return "Pass";
		} else {
			ts_error = "Text not found";
			APPLICATION_LOGS.debug("" + fTextOut + " does not contains " + fText + "");
			APPLICATION_LOGS.debug("Exit: containsText");
			return "Fail";
		}
		}
	}

	/**
	 * MethodName: selectCheckbox() Objective:This method is used to select the
	 * checkbox of the webelement.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @throws ElementNotVisibleException
	 *             Signals that the element is not visible on the page.
	 */
	public void selectCheckbox(String locatorType, String locatorObject) throws ElementNotVisibleException {
		APPLICATION_LOGS.debug("Entry: selectCheckbox");
		if (!locateElement(locatorType, locatorObject).isSelected()) {
			locateElement(locatorType, locatorObject).click();
		}
		APPLICATION_LOGS.debug("Exit: selectCheckbox");
	}

	/**
	 * MethodName: unselectCheckbox() Objective:This method is used to unselect
	 * the checkbox of the webelement.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @throws ElementNotVisibleException
	 *             Signals that the element is not visible on the page.
	 */
	public void unselectCheckbox(String locatorType, String locatorObject) throws ElementNotVisibleException {
		APPLICATION_LOGS.debug("Entry: unselectCheckbox");
		locateElement(locatorType, locatorObject).click();
		APPLICATION_LOGS.debug("Exit: unselectCheckbox");
	}

	/**
	 * MethodName: selectRadiobutton() Objective:This method is used to select
	 * the radiobutton of the webelement.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @throws ElementNotVisibleException
	 *             Signals that the element is not visible on the page.
	 */
	public void selectRadiobutton(String locatorType, String locatorObject) throws ElementNotVisibleException {
		APPLICATION_LOGS.debug("Entry: selectRadiobutton");
		if (!locateElement(locatorType, locatorObject).isSelected()) {
			locateElement(locatorType, locatorObject).click();
		}
		APPLICATION_LOGS.debug("Exit: selectRadiobutton");
	}

	/**
	 * MethodName: verifyElementPresent() Objective:This method is used to
	 * verify a web element present on the given xpath.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @return the string
	 * @throws ElementNotFoundException
	 *             Signals that the element is not visible on the page.
	 * @throws InterruptedException
	 *             Signals that the given time has been out/expired.
	 */
	public String verifyElementPresent(String locatorType, String locatorObject)
			throws ElementNotFoundException, InterruptedException {
		APPLICATION_LOGS.debug("Entry: verifyElementPresent");
		Thread.sleep(2000);// By Sneha
		if (locateElement(locatorType, locatorObject).isDisplayed()) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug(locatorObject + " element is present");
			return "Pass";
		} else {
			ts_error = "Element verification failed : Element not present";
			APPLICATION_LOGS.debug(locatorObject + " element is not present");
			APPLICATION_LOGS.debug("Exit: verifyElementPresent");
			return "Fail";
		}
	}
	
	/**
	 * MethodName: verifyTextPresent() Objective:This method is used to
	 * verify a web element present on the given xpath.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @return the string
	 * @throws ElementNotFoundException
	 *             Signals that the Text is not visible on the page.
	 * @throws InterruptedException
	 *             Signals that the given time has been out/expired.
	 */
	public String verifyTextPresent(String locatorType, String locatorObject)
			throws ElementNotFoundException, InterruptedException {
		APPLICATION_LOGS.debug("Entry: verifyTextPresent");
		Thread.sleep(2000);// By Sneha
		if ((locateElement(locatorType, locatorObject).getText()!=" ")||(locateElement(locatorType, locatorObject).getText()!="null")) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug(locatorObject + " Text is present");
			return "Pass";
		} else {
			ts_error = "Element verification failed : Text not present";
			APPLICATION_LOGS.debug(locatorObject + " Text is not present");
			APPLICATION_LOGS.debug("Exit: verifyTextPresent");
			return "Fail";
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * MethodName: verifyElementNotPresent() Objective:This method is used to
	 * verify a web element is not present on the given xpath.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @return the string
	 * @throws Exception
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public String verifyElementNotPresent(String locatorType, String locatorObject) throws Exception {
		try {

			Thread.sleep(2000);
			APPLICATION_LOGS.debug("Entry: verifyElementNotPresent");
			if ((!locateElement(locatorType, locatorObject).isDisplayed())) {
				ts_error = "No Error";
				APPLICATION_LOGS.debug(locatorObject + " element is not present");
				return "Pass";
			} else {
				ts_error = "Element verification failed : Element is present";
				APPLICATION_LOGS.debug(locatorObject + " element is present");
				APPLICATION_LOGS.debug("Exit: verifyElementNotPresent");
				return "Fail";
			}
		} catch (TimeoutException | NoSuchElementException e) {
			return "Pass";
		}

	}

	/**
	 * MethodName: maximise() Objective:This method is used to maximise the
	 * window.
	 */
	public void maximise() {
		APPLICATION_LOGS.debug("Entry: maximise");
		mD.manage().window().maximize();
		APPLICATION_LOGS.debug("Window maximized");
		APPLICATION_LOGS.debug("Exit: maximise");
	}

	/**
	 * MethodName:verifyLinkPresent() Objective:This method is used to verify
	 * whether a given Link is present or not.
	 * 
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @return the string
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public String verifyLinkPresent(String locatorType, String locatorObject) throws Exception {
		APPLICATION_LOGS.debug("Entry: verifyLinkPresent");
		Thread.sleep(1500);// By Sneha
		if (locateElement(locatorType, locatorObject).isDisplayed()) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug(locatorObject + " link is present");
			APPLICATION_LOGS.debug("Exit: ");
			return "Pass";
		} else {
			ts_error = "Link verification failed : Link not present";
			APPLICATION_LOGS.debug(locatorObject + " link is not present");
			APPLICATION_LOGS.debug("Exit: verifyLinkPresent");
			return "Fail";
		}
	}

	/**
	 * MethodName: getAttribute() Objective:This method returns the value of a
	 * specified attribute on the element.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @param fAttribute
	 *            the f attribute
	 * @return the attribute
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public void getAttribute(String locatorType, String locatorObject, String fAttribute)
			throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: getAttribute");
		String storeText = locateElement(locatorType, locatorObject).getAttribute(fAttribute);
		APPLICATION_LOGS.debug("Exit: getAttribute");
	}

	/**
	 * MethodName: verifyAttribute() Objective: This command is useful for
	 * verification of values of an attribute of any node on the page.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @param fAttribute
	 *            denotes the f attribute
	 * @param fText
	 *            the Value to be entered in the Textbox
	 * @return the string
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public String verifyAttribute(String locatorType, String locatorObject, String fAttribute, String fText)
			throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: verifyAttribute");
		String fTextOut = locateElement(locatorType, locatorObject).getAttribute(fAttribute);
		if (fTextOut.equals(fText)) {

			ts_error = "No Error";

			return "Pass";
		} else {
			ts_error = "attribute verification failed : Attirbute not present";
			APPLICATION_LOGS.debug("Exit: verifyAttribute");
			return "Fail";
		}

	}

	/**
	 * MethodName: acceptPopup() Objective:This method is used to accept pop-up
	 * provided by the browser.
	 *
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @throws NoAlertPresentException
	 *             Signals that alert did not appear.
	 * @throws InterruptedException
	 *             Signals that the given time has been out/expired.
	 */
	public void acceptPopup(String fText) {
		APPLICATION_LOGS.debug("Entry: acceptPopup");
		try {
			if (fText.equals("OK")) {
				WebDriverWait wait = new WebDriverWait(mD, 20);
				wait.until(ExpectedConditions.alertIsPresent());
				mD.switchTo().alert().accept();
				Thread.sleep(5000);
				APPLICATION_LOGS.debug("Exit: acceptPopup");
			}

		} catch (NoAlertPresentException | UnhandledAlertException | InterruptedException e) {
			APPLICATION_LOGS.debug("Error on Handling Alert");
		}
	}

	/**
	 * MethodName: dismissPopup() Objective:This method is used to dismiss
	 * pop-up provided by the browser.
	 *
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @throws InterruptedException
	 *             Signals that the given time has been out/expired.
	 */
	public void dismissPopup(String fText) throws InterruptedException {
		APPLICATION_LOGS.debug("Entry: dismissPopup");
		if (fText.equals("Cancel"))
			mD.switchTo().alert().dismiss();
		APPLICATION_LOGS.debug("Exit: dismissPopup");

	}

	/**
	 * MethodName: selectDate() Objective:This method is used to select a date
	 * from Date Picker calender.
	 * 
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @param count
	 *            denotes the count
	 * @param prevNext
	 *            the prev next
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void selectDate(String fxPath, int count, String prevNext) throws Exception {// ,
																						// String
																						// fText)
																						// {
		APPLICATION_LOGS.debug("Entry: selectDate");
		if (count == 0) {
			locateElementByXpath(fxPath).click();
		} else {
			for (int i = 0; i < count; i++) {
				locateElementByXpath(prevNext).click();
				Thread.sleep(2000);
			}
			locateElementByXpath(fxPath).click();
		}
		APPLICATION_LOGS.debug("Exit: selectDate");
	}

	/**
	 * MethodName: selectSubmenu() Objective:This method is used to select a sub
	 * menu from the Top level menu.
	 * 
	 * @param fxMenu
	 *            denotes the fx menu
	 * @param fxSubmenu
	 *            denotes the fx submenu
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void selectSubmenu(String fxMenu, String fxSubmenu) throws Exception {
		APPLICATION_LOGS.debug("Entry: selectSubmenu");
		Actions builder = new Actions(mD);
		WebElement myM = locateElementByXpath(fxMenu);
		WebElement myS = locateElementByXpath(fxSubmenu);

		Action selectMultiple = builder.moveToElement(myM).click(myS).build();
		selectMultiple.perform();
		APPLICATION_LOGS.debug("Exit: selectSubmenu");
	}

	/**
	 * MethodName: highlightElement() Objective:This method is used to highlight
	 * the web element for which Xpath is provided.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @throws ElementNotVisibleException
	 *             Signals that the element is not visible on the page.
	 */
	public void highlightElement(String locatorType, String locatorObject) throws ElementNotVisibleException {
		APPLICATION_LOGS.debug("Entry: highlightElement");
		WebElement element = locateElement(locatorType, locatorObject);
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) mD;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: blue; border: 2px solid yellow;");
		}
		APPLICATION_LOGS.debug(locatorObject + " element highlighted successfully");
		APPLICATION_LOGS.debug("Exit: highlightElement");
	}

	/**
	 * MethodName: closeBrowser() Objective:This method is used to close the
	 * browser.
	 *
	 * @throws WebDriverException
	 *             it will handle the web driver exception
	 */
	public void closeBrowser() throws WebDriverException {
		APPLICATION_LOGS.debug("Entry: closeBrowser");
		mD.close();
		APPLICATION_LOGS.debug("Browser Closed successfully");
	}

	/**
	 * MethodName: waitTime() Objective:This method is used to command the web
	 * browser to wait for a specified time.
	 *
	 * @param fWait
	 *            denotes the f wait
	 * @throws TimeoutException
	 *             - Thrown when a command does not complete in enough time
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void waitTime(int fWait) throws TimeoutException, InterruptedException {
		APPLICATION_LOGS.debug("Entry: waitTime");
		Thread.sleep(fWait);
		APPLICATION_LOGS.debug("Exit: waitTime");
	}

	public void PageLoadWaitTime(int fWait) throws TimeoutException, InterruptedException {
		APPLICATION_LOGS.debug("Entry: waitTime");
		mD.manage().timeouts().pageLoadTimeout(fWait, TimeUnit.SECONDS);
		APPLICATION_LOGS.debug("Exit: waitTime");
	}

	/**
	 * MethodName: xlR_TSuite() Objective:This method is used to locate and read
	 * the Test Suite in which all testcases are present for execution.
	 * 
	 * @param TestSuitePath
	 *            denotes the test suite path
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void xlR_TSuite(String TestSuitePath) throws Exception {
		APPLICATION_LOGS.debug("Entry: xlR_TSuite");
		try {
			File myxl = new File(TestSuitePath);
			FileInputStream myStream = new FileInputStream(myxl);
			HSSFWorkbook myWB = new HSSFWorkbook(myStream);
			HSSFSheet mySheet = myWB.getSheetAt(0); // Referring to 1st sheet
			xTSuite_rows = mySheet.getLastRowNum() + 1;
			xTSuite_cols = mySheet.getRow(0).getLastCellNum();
			xTSuite = new String[xTSuite_rows][xTSuite_cols]; // Giving the size
																// of the array
			for (int i = 0; i < xTSuite_rows; i++) {
				HSSFRow row = mySheet.getRow(i);
				for (int j = 0; j < xTSuite_cols; j++) {
					HSSFCell cell = row.getCell(j); // To read value from each
													// col in each row
					String value = cellToString(cell);
					xTSuite[i][j] = value;
				}
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		APPLICATION_LOGS.debug("Exit: xlR_TSuite");
	}

	/**
	 * MethodName: xlR_TC() Objective: This method is used to open the testcase
	 * and to read the values from the rows thereafter.
	 * 
	 * @param sPath
	 *            denotes the s path
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void xlR_TC(String sPath) throws Exception {
		APPLICATION_LOGS.debug("Entry: xlR_TC");
		try {
			File myxl = new File(sPath);
			FileInputStream myStream = new FileInputStream(myxl);
			HSSFWorkbook myWB = new HSSFWorkbook(myStream);
			HSSFSheet mySheet = myWB.getSheetAt(0); // Referring to 2nd sheet
			xTC_rows = mySheet.getLastRowNum() + 1;
			xTC_cols = mySheet.getRow(0).getLastCellNum();
			xTC = new String[xTC_rows][xTC_cols]; // Giving the size of the
													// array
			for (int i = 0; i < xTC_rows; i++) {
				HSSFRow row = mySheet.getRow(i);
				for (int j = 0; j < xTC_cols; j++) {
					HSSFCell cell = row.getCell(j); // To read value from each
													// col in each row
					String value = cellToString(cell);
					xTC[i][j] = value;
				}
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		APPLICATION_LOGS.debug("Exit: xlR_TC");
	}

	/**
	 * MethodName: xlR_TS() Objective:This method is used to read the values
	 * from Test Sheet in the Testcase.
	 * 
	 * @param sPath
	 *            denotes the s path
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void xlR_TS(String sPath) throws Exception {
		APPLICATION_LOGS.debug("Entry: xlR_TS");
		try {
			File myxl = new File(sPath);
			FileInputStream myStream = new FileInputStream(myxl);
			HSSFWorkbook myWB = new HSSFWorkbook(myStream);
			HSSFSheet mySheet = myWB.getSheetAt(1); // Referring to 3rd sheet
			xTS_rows = mySheet.getLastRowNum() + 1;
			xTS_cols = mySheet.getRow(0).getLastCellNum();
			xTS = new String[xTS_rows][xTS_cols]; // Giving the size of the
													// array
			for (int i = 0; i < xTS_rows; i++) {
				HSSFRow row = mySheet.getRow(i);
				for (int j = 0; j < xTS_cols; j++) {
					HSSFCell cell = row.getCell(j); // To read value from each
													// col in each row
					String value = cellToString(cell);
					xTS[i][j] = value;
				}
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		APPLICATION_LOGS.debug("Exit: xlR_TS");
	}

	/**
	 * MethodName: xlR_StepSheet() Objective:This method is used to read value
	 * from each column in each row.
	 * 
	 * @param sPath
	 *            denotes the s path
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void xlR_StepSheet(String sPath) throws Exception {
		APPLICATION_LOGS.debug("Entry: xlR_StepSheet");
		try {
			File myxl = new File(sPath);
			FileInputStream myStream = new FileInputStream(myxl);
			HSSFWorkbook myWB = new HSSFWorkbook(myStream);
			HSSFSheet mySheet = myWB.getSheetAt(0); // Referring to 3rd sheet
			xTStep_rows = mySheet.getLastRowNum() + 1;
			xTStep_cols = mySheet.getRow(0).getLastCellNum();
			xStepSheet = new String[xTStep_rows][xTStep_cols]; // Giving the
																// size of the
																// array
			for (int i = 0; i < xTStep_rows; i++) {
				HSSFRow row = mySheet.getRow(i);
				for (int j = 0; j < xTStep_cols; j++) {
					HSSFCell cell = row.getCell(j); // To read value from each
													// col in each row
					String value = cellToString(cell);
					xStepSheet[i][j] = value;
				}
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		APPLICATION_LOGS.debug("Exit: xlR_StepSheet");
	}

	/**
	 * MethodName: xlR_TD() Objective:This method used to read the values from
	 * Test Sheet in the Testcase.
	 * 
	 * @param sPath
	 *            denotes the s path
	 * @param Sheetname
	 *            the sheetname
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void xlR_TD(String sPath, String Sheetname) throws Exception {
		APPLICATION_LOGS.debug("Entry: xlR_TD");
		try {
			File myxl = new File(sPath);
			FileInputStream myStream = new FileInputStream(myxl);
			HSSFWorkbook myWB = new HSSFWorkbook(myStream);
			HSSFSheet mySheet = myWB.getSheet(Sheetname);
			xTD_rows = mySheet.getLastRowNum() + 1;
			xTD_cols = mySheet.getRow(0).getLastCellNum();
			xTD = new String[xTD_rows][xTD_cols]; // Giving the size of the
													// array
			for (int i = 0; i < xTD_rows; i++) {
				HSSFRow row = mySheet.getRow(i);
				for (int j = 0; j < xTD_cols; j++) {
					HSSFCell cell = row.getCell(j); // To read value from each
													// col in each row
					String value = cellToString(cell);
					xTD[i][j] = value;
				}
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		APPLICATION_LOGS.debug("Exit: xlR_TD");
	}

	/**
	 * MethodName: cellToString() Objective:This function converts an object of
	 * type excel cell to a string value.
	 *
	 * @param cell
	 *            denotes the cell value
	 * @return the string
	 * @throws Exception
	 *             - it will capture all types of exception
	 */
	public static String cellToString(HSSFCell cell) throws Exception {
		APPLICATION_LOGS.debug("Entry: cellToString");
		// This function will convert an object of type excel cell to a string
		// value
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 0
			result = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING: // 1
			result = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case HSSFCell.CELL_TYPE_BLANK: // 3
			result = "-";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: // 4
			result = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR: // 5
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type: " + type);
		}
		APPLICATION_LOGS.debug("Exit: cellToString");
		return result.toString();
	}

	/**
	 * MethodName: xlwrite_tc() Objective:This method is used to write test case
	 * result in Excel sheet.
	 * 
	 * @param xlPath
	 *            denotes the xl path
	 * @param xldata
	 *            denotes the xldata
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void xlwrite_tc(String xlPath, String[][] xldata) throws Exception {
		APPLICATION_LOGS.debug("Entry: xlwrite_tc");
		try {
			File outFile = new File(xlPath);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet osheet = wb.createSheet("TestCasesResults");
			
			for (int myrow = 0; myrow < xTC_rows; myrow++) {
				
				HSSFRow row = osheet.createRow(myrow);
				
				for (int mycol = 0; mycol < xTC_cols; mycol++) {
					
					HSSFCell cell = row.createCell(mycol);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(xldata[myrow][mycol]);
				}
				
				FileOutputStream fOut = new FileOutputStream(outFile);
				
				wb.write(fOut);
				fOut.flush();
				fOut.close();
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		APPLICATION_LOGS.debug("Exit: xlwrite_tc");
	}

	/**
	 * MethodName: xlwrite_ts() Objective:This method is used to write the
	 * TestSteps result one by one in Result Sheet.
	 * 
	 * @param xlPath
	 *            denotes the xl path
	 * @param xldata
	 *            denotes the xldata
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void xlwrite_ts(String xlPath, String[][] xldata) throws Exception {
		APPLICATION_LOGS.debug("Entry: xlwrite_ts");
		try {
			File outFile = new File(xlPath);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet osheet = wb.createSheet("TestStepsResults");
			for (int myrow = 0; myrow < xTS_rows; myrow++) {
				HSSFRow row = osheet.createRow(myrow);
				for (int mycol = 0; mycol < xTS_cols; mycol++) {
					HSSFCell cell = row.createCell(mycol);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(xldata[myrow][mycol]);
				}
				FileOutputStream fOut = new FileOutputStream(outFile);
				wb.write(fOut);
				fOut.flush();
				fOut.close();
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		APPLICATION_LOGS.debug("Exit: xlwrite_ts");
	}

	/**
	 * MethodName: CreateSheet() Objective:This method is used to create a new
	 * Excel Sheet in specified Folder name.
	 * 
	 * @param filename
	 *            the filename
	 * @param xldata
	 *            denotes the xldata
	 * @param rownum
	 *            denotes the rownum
	 * @param colnum
	 *            denotes the colnum
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void CreateSheet(String filename, String[][] xldata, int rownum, int colnum) throws Exception {
		APPLICATION_LOGS.debug("Entry: CreateSheet");
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet osheet = wb.createSheet(filename);

			for (int myrow = 0; myrow < rownum; myrow++) {
				HSSFRow row = osheet.createRow(myrow);
				for (int mycol = 0; mycol < colnum; mycol++) {
					HSSFCell cell = row.createCell(mycol);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(xldata[myrow][mycol]);
				}
				FileOutputStream fOut = new FileOutputStream(foldname + "/" + filename + ".xls");
				wb.write(fOut);
				fOut.flush();
				fOut.close();
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		APPLICATION_LOGS.debug("Exit: CreateSheet");
	}

	/**
	 * MethodName: FolderCreate() Objective:This generic method is used to
	 * create a new Folder and place the same in User directory Results location
	 * with a specified Date Format name(current date-time).
	 *
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void FolderCreate() throws Exception {
		APPLICATION_LOGS.debug("Entry: FolderCreate with no parameter");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int mnt = cal.get(Calendar.MONTH) + 1;
		int yr = cal.get(Calendar.YEAR);
		int hr = date.getHours();
		int min = date.getMinutes();
		int sec = date.getSeconds();
		foldname = System.getProperty("user.dir") + "\\Results\\" + yr + "_" + mnt + "_" + day + "_" + hr + "_" + min
				+ "_" + sec;
		File f = new File(foldname);
		try {
			if (f.exists() == false) {
				f.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		APPLICATION_LOGS.debug("Exit: FolderCreate with no parameter");
	}

	/**
	 * MethodName: FolderCreate() Objective:This generic method is used to
	 * create a new Folder and place the same in User directory Results location
	 * with a specified Date Format name(current date-time).
	 *
	 * @param testcasename
	 *            denotes the testcasename
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void FolderCreate(String testcasename) throws Exception {
		APPLICATION_LOGS.debug("Entry: FolderCreate with single parameter");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int mnt = cal.get(Calendar.MONTH) + 1;
		int yr = cal.get(Calendar.YEAR);
		int hr = date.getHours();
		int min = date.getMinutes();
		int sec = date.getSeconds();
		String testcasefoldname = foldname + "\\" + testcasename;
		File f = new File(testcasefoldname);
		try {
			if (f.exists() == false) {
				f.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		APPLICATION_LOGS.debug("Exit: FolderCreate with single parameter");
	}

	/**
	 * MethodName:takeScreenShot_TestNG() Objective:This generic method is used
	 * to take Screenshot using TestNG.
	 *
	 * @param tdi
	 *            denotes the tdi
	 * @param vTSuiteID
	 *            denotes the v t suite id
	 * @param vTSID
	 *            denotes the v tsid
	 * @param testname
	 *            denotes the testname
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void takeScreenShot_TestNG(int tdi, String vTSuiteID, String vTSID, String testname) throws Exception {
		APPLICATION_LOGS.debug("Entry: takeScreenShot_TestNG");
		String Path;
		try {
			Thread.sleep(2000);
			Path = foldname + "\\" + testname + "\\" + vTSuiteID + "_" + vTSID + "_" + "TD00" + tdi + "_" + "step " + k
					+ ".jpg";
			File scrFile = ((TakesScreenshot) mD).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(Path));
		} catch (InterruptedException | WebDriverException e) {
			APPLICATION_LOGS.debug("Error while taking screenshot due to presence of Alert.");
			/*
			 * Path = foldname + "\\" +  testname+ "\\" + vTSuiteID + "_
			 * " + vTSID + "_" + "TD00" + tdi + "_" + "step " + k + ".jpg";
			 * BufferedImage image = new Robot().createScreenCapture(new
			 * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			 * ImageIO.write(image, "png", new File(Path));
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		APPLICATION_LOGS.debug("Exit: takeScreenShot_TestNG");
	}

	/**
	 * MethodName: takeScreenShot() Objective:This generic method is used to
	 * take Screenshot
	 *
	 * @param vTSuiteID
	 *            denotes the v t suite id
	 * @param vTSID
	 *            denotes the v tsid
	 * @param testname
	 *            denotes the testname
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void takeScreenShot(String vTSuiteID, String vTSID, String testname) throws Exception {
		APPLICATION_LOGS.debug("Entry: takeScreenShot");
		String Path;
		try {
			Thread.sleep(2000);
			Path = foldname + "\\" + testname + "\\" + vTSuiteID + "_" + vTSID + "_" + "step " + l + ".jpg";
			File scrFile = ((TakesScreenshot) mD).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(Path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		APPLICATION_LOGS.debug("Exit: takeScreenShot");
	}

	/**
	 * MethodName: firstchar() Objective:This method is used to get the value of
	 * character at a location in String format.
	 *
	 * @param s
	 *            denotes the s
	 * @return the string
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public String firstchar(String s) throws Exception {
		APPLICATION_LOGS.debug("Entry: firstchar");
		char kk = s.charAt(0);
		String a = String.valueOf(kk);
		APPLICATION_LOGS.debug("Exit: firstchar");
		return a;
	}

	/**
	 * MethodName: writetoXLCell() Objective:This method is used to write values
	 * to Excel Cell.
	 * 
	 * @param xlPath
	 *            the xl path
	 * @param data
	 *            gets the data
	 * @param rowid
	 *            gets the rowid
	 * @param colid
	 *            gets the colid
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void writetoXLCell(String xlPath, String data, int rowid, int colid) throws Exception {
		APPLICATION_LOGS.debug("Entry: writetoXLCell");
		try {
			File myxl = new File(xlPath);
			FileInputStream myStream = new FileInputStream(myxl);
			HSSFWorkbook myWB = new HSSFWorkbook(myStream);
			HSSFSheet osheet = myWB.getSheetAt(0); // Referring to 2nd sheet
			for (int myrow = rowid; myrow <= rowid; myrow++) {
				HSSFRow row = osheet.getRow(myrow);
				for (int mycol = colid; mycol <= colid; mycol++) {
					HSSFCell cell = row.createCell(colid);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(data);
				}
				FileOutputStream fOut = new FileOutputStream(xlPath);
				myWB.write(fOut);
				fOut.flush();
				fOut.close();
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		APPLICATION_LOGS.debug("Exit: writetoXLCell");
	}

	/**
	 * MethodName: xlR_Env() Objective:This method is used to read the values
	 * from each column in each row of TestSuite.
	 * 
	 * @param TestSuitePath
	 *            denotes the test suite path
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void xlR_Env(String TestSuitePath) throws Exception {
		APPLICATION_LOGS.debug("Entry: xlR_Env");
		try {
			File myxl = new File(TestSuitePath);
			FileInputStream myStream = new FileInputStream(myxl);
			HSSFWorkbook myWB = new HSSFWorkbook(myStream);
			HSSFSheet mySheet = myWB.getSheetAt(1); // Referring to 2nd sheet
			xEnv_rows = mySheet.getLastRowNum() + 1;
			xEnv_cols = mySheet.getRow(0).getLastCellNum();
			xEnv = new String[xEnv_rows][xEnv_cols]; // Giving the size of the
														// array
			for (int i = 0; i < xEnv_rows; i++) {
				HSSFRow row = mySheet.getRow(i);
				for (int j = 0; j < xEnv_cols; j++) {
					HSSFCell cell = row.getCell(j); // To read value from each
													// col in each row
					String value = cellToString(cell);
					xEnv[i][j] = value;
				}
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		APPLICATION_LOGS.debug("Exit: xlR_Env");
	}

	/**
	 * MethodName: split() Objective:This method is used to split a string into
	 * substrings based on the strings in an array.
	 *
	 * @param split
	 *            gets the string
	 * @return the string[]
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public String[] split(String split) throws Exception {
		APPLICATION_LOGS.debug("Entry: ");
		String a = vTestData;
		String b = a.trim();
		String specialChar = xEnv[2][1];
		String[] arr = b.split(specialChar);
		APPLICATION_LOGS.debug("Exit: split");
		return arr;
	}
	
	public String[] split1(String split) throws Exception {
		APPLICATION_LOGS.debug("Entry: ");
		String a = vTestData;
		String b = a.trim();
		//String specialChar = xEnv[2][1];
		String[] arr = b.split(":");
		APPLICATION_LOGS.debug("Exit: split");
		return arr;
	}

	/**
	 * MethodName: Log() Objective:This method is used to write periodic logs of
	 * a successful or unsuccessful execution.
	 * 
	 * @param suiteName
	 *            denotes the suite name
	 * @param ts_result
	 *            denotes the ts_result
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void Log(String suiteName, String ts_result) throws IOException {
		APPLICATION_LOGS.debug("Entry: Log");
		String fileName = "Logs.txt";
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		File file = new File(System.getProperty("user.dir") + "/successlogs/" + fileName);
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.newLine();
			bufferedWriter.write(suiteName);
			bufferedWriter.write(" ");
			bufferedWriter.write(dateFormat.format(date));
			bufferedWriter.write(" ");
			bufferedWriter.write(ts_result);
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		APPLICATION_LOGS.debug("Exit: Log");
	}

	/**
	 * MethodName: getObjectDef() Objective:This method is used to get the OR
	 * (Object Repository) name.
	 * 
	 * @param ObjectName
	 *            denotes the object name
	 * @param ORName
	 *            denotes the OR name
	 * @return the object def
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String getObjectDef(String ObjectName, String ORName) throws IOException {
		APPLICATION_LOGS.debug("Entry: getObjectDef");
		String filepath = System.getProperty("user.dir") + "/src/test/resources/com/resources/" + ORName
				+ ".properties";
		APPLICATION_LOGS.debug("Exit: getObjectDef");
		return getProperty(ObjectName, filepath);
	}

	/**
	 * MethodName: getEnvProp() Objective:This method is used to get the Env
	 * Properties.
	 * 
	 * @param PropName
	 *            gets the prop name
	 * @return the env prop
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String getEnvProp(String PropName) throws IOException {
		APPLICATION_LOGS.debug("Entry: getEnvProp");
		String filepath = System.getProperty("user.dir") + "/src/test/resources/com/resources/env.properties";
		APPLICATION_LOGS.debug("Exit: getEnvProp");
		return getProperty(PropName, filepath);

	}

	/**
	 * MethodName: getTestData() Objective:This method is used to get the values
	 * from testData.properties.
	 * 
	 * @param TDName
	 *            denotes the TD name
	 * @return the test data
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String getTestData(String TDName) throws IOException {
		APPLICATION_LOGS.debug("Entry: getTestData");
		File f = new File("//SYKTPCETHN01V/AutomationTestData/testData.properties");
		if (f.exists()) {
			String filepath = "//SYKTPCETHN01V/AutomationTestData/testData.properties";
			APPLICATION_LOGS.debug("Exit: ");
			return getProperty(TDName, filepath);
		} else {
			String filepath = System.getProperty("user.dir") + "/src/test/resources/com/resources/testData.properties";
			APPLICATION_LOGS.debug("Exit: getTestData");
			return getProperty(TDName, filepath);
		}

	}
	
	/**
	 * MethodName: setTestData() Objective:This method is used to set the values
	 * in the testData.properties.
	 * 
	 * @param TDName
	 *            denotes the TD name
	 * @return the test data
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void setTestData(String TDName, String TDValue) throws IOException {
		APPLICATION_LOGS.debug("Entry: setTestData");
		File f = new File("//SYKTPCETHN01V/AutomationTestData/testData.properties");
		if (f.exists()) {
			String filepath = "//SYKTPCETHN01V/AutomationTestData/testData.properties";
			APPLICATION_LOGS.debug("Exit: ");
			setProperty(TDName, TDValue, filepath);
		} else {
			String filepath = System.getProperty("user.dir") + "/src/test/resources/com/resources/testData.properties";
			APPLICATION_LOGS.debug("Exit: setTestData");
			setProperty(TDName, TDValue, filepath);
		}
	}

	/**
	 * MethodName: getProperty() Objective:This generic method is used to get
	 * the Property values.
	 * 
	 * @param propertyname
	 *            the propertyname
	 * @param filepath
	 *            gets the filepath
	 * @return the property
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String getProperty(String propertyname, String filepath) throws IOException {
		APPLICATION_LOGS.debug("Entry: getProperty");
		Properties prop = new Properties();
		InputStream input = new FileInputStream(filepath);
		prop.load(input);
		APPLICATION_LOGS.debug("Exit: getProperty");
		return prop.getProperty(propertyname);
	}
	
	/**
	 * MethodName: setProperty() Objective:This generic method is used to set
	 * the Property values.
	 * 
	 * @param propertyname
	 *            the propertyname
	 * @param filepath
	 *            gets the filepath
	 */
	public void setProperty(String propertyname, String propertyValue, String filepath) {
		APPLICATION_LOGS.debug("Entry: setProperty");
		PropertiesConfiguration config;
		try {
			config = new PropertiesConfiguration(filepath);
			config.setProperty(propertyname, propertyValue);
			config.save();
			APPLICATION_LOGS.debug("Exit: setProperty");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}


	/**
	 * MethodName: getwindowHandle() Objective:This method is used to return a
	 * set of window handles which can be used to iterate over all open windows
	 * of the WebDriver instance.
	 *
	 * @param winid
	 *            gets the winid
	 * @return the window handle
	 * @throws NoSuchWindowException
	 *             Signals that the window could not be found.
	 * @throws StaleElementReferenceException
	 *             Signals that the referenced element is no longer attached to
	 *             the DOM.
	 */
	public void getwindowHandle(String winid) throws NoSuchWindowException, StaleElementReferenceException {
		APPLICATION_LOGS.debug("Entry: getwindowHandle");
		Set<String> winId = null;
		Iterator<String> winIds = null;
		List<String> widL = null;
		winId = mD.getWindowHandles();
		winIds = winId.iterator();
		int ww = winId.size();
		widL = new ArrayList<String>();
		for (int i = 0; i < winId.size(); i++) {
			widL.add(winIds.next());
		}
		mD.switchTo().window(widL.get(Integer.parseInt(winid)));
		String rlsstr = mD.getCurrentUrl();
		APPLICATION_LOGS.debug("Exit: getwindowHandle");
	}
	/**
	 * MethodName: dragandDrop() Objective:This method is used to drag the file from source location 
	 * to destination location
	 
	 */
	public WebElement draganddropFromElement(String locatorType, String locatorObject ) throws NoSuchWindowException, StaleElementReferenceException {
		APPLICATION_LOGS.debug("Entry: getwindowHandle");
		From = locateElement(locatorType, locatorObject);
		return From;
		 
	}
	
	public WebElement draganddropToElement(String locatorType, String locatorObject ) throws NoSuchWindowException, StaleElementReferenceException {
		APPLICATION_LOGS.debug("Entry: getwindowHandle");
		To = locateElement(locatorType, locatorObject);
		return To;
		 
	}

	public WebElement draganddrop(String locatorType, String locatorObject ) throws NoSuchWindowException, StaleElementReferenceException {
		APPLICATION_LOGS.debug("Entry: getwindowHandle");
		Actions builder = new Actions(mD);

		Action dragAndDrop = builder.clickAndHold(From) .moveToElement(To) .release(To).build();
		dragAndDrop.perform();
		return To;
		 
	}

	/**
	 * MethodName: switchtoFrame() Objective:This method is used to return a set
	 * of window handles which can be used to iterate over all open windows of
	 * the WebDriver instance.
	 *
	 * @param framename
	 *            the framename
	 * @throws NoSuchFrameException
	 *             Signals that the frame could not be found.
	 */
	public void switchtoFrame( ) throws NoSuchFrameException {
		APPLICATION_LOGS.debug("Entry: switchtoFrame");
		List<WebElement> iframe = mD.findElements(By.tagName("iframe"));
		int pt = iframe.size();
		for (int z = 0; z < pt; z++) {
			mD.switchTo().frame(0);
			 
		}
		APPLICATION_LOGS.debug("Exit: switchtoFrame");
	}

	/**
	 * MethodName: getTodaysDate() Objective:This method is used to get Todays
	 * date.
	 *
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @return the todays date
	 * @throws ElementNotVisibleException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public void getTodaysDate(String fText) throws ElementNotVisibleException {
		APPLICATION_LOGS.debug("Entry: switchtoFrame");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get current date time with Date()
		Date date = new Date();
		envData.put(fText.replace("^", ""), dateFormat.format(date));
		APPLICATION_LOGS.debug("Exit: switchtoFrame");
	}

	/**
	 * MethodName: isElementDisabled Objective: This method is used to verify
	 * whether a web element is enabled or disabled.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @return the string
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public String isElementDisabled(String locatorType, String locatorObject) {
		APPLICATION_LOGS.debug("Entry: isElementDisabled");
		WebElement element = locateElement(locatorType, locatorObject);
		if (!element.isEnabled() || element.getAttribute("readonly").equalsIgnoreCase("true") || element.getAttribute("disabled").equalsIgnoreCase("disabled")||element.getAttribute("disabled").equalsIgnoreCase("")||element.getAttribute("readonly").equalsIgnoreCase("")||element.getAttribute("readonly").equalsIgnoreCase("true")) {
			APPLICATION_LOGS.debug("Element is disabled");
			return "true";

		}
		APPLICATION_LOGS.debug("Element is enabled");
		APPLICATION_LOGS.debug("Exit: isElementDisabled");
		return "false";
	}

	/**
	 * MethodName: checkPrePopulatedText() Objective: This method is used to
	 * verify prepoulated text in the WebElement.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @return the string
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public String checkPrePopulatedText(String locatorType, String locatorObject, String fText)
			throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: checkPrePopulatedText");
		WebElement element = locateElement(locatorType, locatorObject);
		if (element.getAttribute("value").trim().equalsIgnoreCase(fText)) {
			APPLICATION_LOGS.debug("Element is read-only with value");
			return "true";
		}
		APPLICATION_LOGS.debug("Element is not read-only with no value");
		APPLICATION_LOGS.debug("Exit: checkPrePopulatedText");
		return "false";
	}

	/**
	 * MethodName: verifyAllDropDownvalues() Objective: This method is used to
	 * verify all Dropdown values.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @return the array list
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public ArrayList<String> verifyAllDropDownvalues(String locatorType, String locatorObject, String fText)
			throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: verifyAllDropDownvalues");
		WebElement element = locateElement(locatorType, locatorObject);
		Select sel = new Select(element);
		List<WebElement> option = sel.getOptions();
		ArrayList<String> optionValues = new ArrayList<String>();
		for (WebElement opt : option) {
			String a = opt.getText();
			optionValues.add(a);
		}
		APPLICATION_LOGS.debug(optionValues);
		sel.selectByValue(fText); // For Reusable CIN modified the selectByValue
		APPLICATION_LOGS.debug("Exit: verifyAllDropDownvalues");
		return optionValues;

	}
	
	public ArrayList<String> verifyAllDropDownvalueByText(String locatorType, String locatorObject, String fText)
			throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: verifyAllDropDownvalues");
		WebElement element = locateElement(locatorType, locatorObject);
		Select sel = new Select(element);
		List<WebElement> option = sel.getOptions();
		ArrayList<String> optionValues = new ArrayList<String>();
		for (WebElement opt : option) {
			String a = opt.getText().trim();
			optionValues.add(a);
		}
		APPLICATION_LOGS.debug(optionValues);
		sel.selectByVisibleText(fText.trim()); // For Reusable CIN modified the selectByValue
		APPLICATION_LOGS.debug("Exit: verifyAllDropDownvalues");
		return optionValues;

	}

	/**
	 * MethodName: selectDropDown()
	 *
	 * @param fxPath
	 *            denotes the fx path
	 * @param fText
	 *            denotes the f text
	 * @return the array list
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public ArrayList<String> selectDropDown(String locatorType, String locatorObject, String fText)
			throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: selectDropDown");
		WebElement element = locateElement(locatorType, locatorObject);
		Select sel = new Select(element);
		List<WebElement> option = sel.getOptions();
		ArrayList<String> optionValues = new ArrayList<String>();
		for (WebElement opt : option) {
			String a = opt.getText();
			optionValues.add(a);
		}
		APPLICATION_LOGS.debug(optionValues);
		sel.selectByValue(fText);
		APPLICATION_LOGS.debug("Exit: selectDropDown");
		return optionValues;
	}

	/**
	 * MethodName: getTable() Objective: This method is used to get the Table
	 * from HTML and highlight the respective Xpath.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @return the table
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 * 
	 */
	public void getTable(String locatorType, String locatorObject) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: getTable");
		int index = 0;
		WebElement baseTable = locateElement(locatorType, locatorObject);
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		tableRows.get(index).getText();
		ArrayList<String> optionValues = new ArrayList<String>();
		for (WebElement opt : tableRows) {
			String a = opt.getText();
			highlightElement(locatorType, locatorObject);
			optionValues.add(a);

			APPLICATION_LOGS.debug("Exit: getTable");
		}
	}

	/**
	 * MethodName: pageContainsText() Objective: This method is used to verify
	 * that a given text is present anywhere on the web page.
	 *
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @return the string
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public String pageContainsText(String fText) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry:pageContainsText ");
		if (mD.getPageSource().contains(fText)) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug(" Page contains " + fText + "");
			return "Pass";
		} else {
			ts_error = "Text not found on Page";
			APPLICATION_LOGS.debug(" Page does not contains " + fText + "");
			APPLICATION_LOGS.debug("Exit: pageContainsText");
			return "Fail";
		}
	}
	
	/**
	 * MethodName: pageContainsText() Objective: This method is used to verify
	 * that a given text is present anywhere on the web page.
	 *
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @return the string
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public String pageContainsMedicaid() throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry:pageContainsText ");
		if (mD.getPageSource().contains(identifierValue)) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug(" Page contains " + identifierValue + "");
			return "Pass";
		} else {
			ts_error = "Text not found on Page";
			APPLICATION_LOGS.debug(" Page does not contains " + identifierValue + "");
			APPLICATION_LOGS.debug("Exit: pageContainsText");
			return "Fail";
		}
	}

	/**
	 * MethodName : pageNotContainsText() Objective:This method is used to
	 * verify that a given Text is not present on a specified location.
	 *
	 * @param fxPath
	 *            denotes the XPath of the Element
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @return the string
	 * @throws NoSuchElementException
	 *             Signals that for the given search parameter, the element
	 *             could not be located on the page.
	 */
	public String pageNotContainsText(String locatorType, String locatorObject, String fText)
			throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: pageNotContainsText");
		String expText = fText.toLowerCase();
		String fTextOut = locateElement(locatorType, locatorObject).getText();
		if (!(fTextOut.contains(expText))) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + fTextOut + " contains " + fText + "");
			return "Pass";
		} else {
			ts_error = "Text not found on Page";
			APPLICATION_LOGS.debug(" Page does not contains " + fText + "");
			return "Fail";
		}

	}

	/**
	 * MethodName: pageRefresh() Objective:This method is used to refresh the
	 * present webpage.
	 * 
	 */
	public void pageRefresh() {
		APPLICATION_LOGS.debug("Entry: pageRefresh");
		mD.navigate().refresh();
		APPLICATION_LOGS.debug("Exit: pageRefresh");
	}

	/**
	 * MethodName : getAlertText() Objective:This method is used to get the Text
	 * from the Alert box
	 *
	 * @return the alert text
	 * @throws NoAlertPresentException
	 *             Signals that alert did not appear.
	 * 
	 */
	public void getAlertText() throws NoAlertPresentException {
		APPLICATION_LOGS.debug("Entry: getAlertText");
		Alert alert = mD.switchTo().alert();
		String AlertText = alert.getText();
		System.out.println(AlertText);
		if (AlertText.isEmpty()) {
			ts_error = " Error";
			APPLICATION_LOGS.debug(" Page does not contains " + "AlertText");
		} else {

			ts_error = alert.getText();
			APPLICATION_LOGS.debug(" Page contains " + alert.getText());
		}
		APPLICATION_LOGS.debug("Exit: getAlertText");
	}

	// SwitchToFrame method is used to switch to frame by ID.
	public void SwitchToFramebyID(int ss) {
		mD.switchTo().frame(ss);
		APPLICATION_LOGS.debug("Switched to Frame");
	}
	
	public void switchToOrigin() {
		APPLICATION_LOGS.debug("Entry: switchToOrigin");
		mD.switchTo().defaultContent();
		APPLICATION_LOGS.debug("Exit: switchToOrigin");
	}
	
	/**
	 * This method verifies the alert text and accepts the alert.
	 * @param fText
	 * @return
	 */
	public String verifyAlertTextAndAccept(String fText) {
		APPLICATION_LOGS.debug("Entry: verifyAlertTextAndAccept");
		try {
				WebDriverWait wait = new WebDriverWait(mD, 20);
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alert= mD.switchTo().alert();
				String AlertText = alert.getText();
				//System.out.println("Alert text:"+AlertText);
				if (!AlertText.isEmpty() && null!=AlertText && AlertText.equalsIgnoreCase(fText)) {
					APPLICATION_LOGS.debug(" Alert contains expected text "+ alert.getText());
					alert.accept();
					APPLICATION_LOGS.debug(" Accepted the alert.");
					return "Pass";
				} else {
					APPLICATION_LOGS.debug("Alert does not contains any text" );
					return "Fail";
				}		

		} catch (NoAlertPresentException | UnhandledAlertException e) {
			APPLICATION_LOGS.debug("Error on Handling Alert");
			return "Fail";
		}
	}
	
}