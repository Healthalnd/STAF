package com.common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

// TODO: Auto-generated Javadoc
/**
 * The Class BusinessActionCareTool.
 */
public class BusinessActionCareTool extends CommonActions {

	/**
	 * MethodName: SearchPatientMRNFromClinicalViewer() Objective : This method
	 * is used to search a patient from clinical viewer using MRN.
	 * 
	 * @param fMRN
	 *            denotes the medicaid of clinical Viewer
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public void SearchPatientMRNFromClinicalViewer(String fMRN) throws Exception {
		APPLICATION_LOGS.debug("Entry: SearchPatientMRNFromClinicalViewer");
		clickElement("xpath", "//input[@id='nav-search-input']");
		clearText("xpath", "//input[@id='nav-search-input']");
		enterText("xpath", "//input[@id='nav-search-input']", fMRN);
		clickElement("xpath", "//i[@id='clMpSearchFilter']");
		Thread.sleep(3000);
		APPLICATION_LOGS.debug("Exit: SearchPatientMRNFromClinicalViewer");
	}

	/**
	 * MethodName : SearchPatientMRN() Objective : This method is used to search
	 * a patient in using MRN.
	 * 
	 * @param fMRN
	 *            denotes the medicaid of the caretool program.
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public void SearchPatientMRN(String fMRN) throws Exception {
		APPLICATION_LOGS.debug("Entry: SearchPatientMRN");
		clickElement("xpath", "//input[@id='nav-search-input']");
		clearText("xpath", "//input[@id='nav-search-input']");
		enterText("xpath", "//input[@id='nav-search-input']", fMRN);
		clickElement("xpath", "//i[@id='clMpSearchFilter']");
		Thread.sleep(3000);
		APPLICATION_LOGS.debug("Exit: SearchPatientMRN");
	}

	/**
	 * MethodName: SearchPatientLastName Objective: This method is used to
	 * search a patient using their Last Name.
	 * 
	 * @param fLastName
	 *            denotes the last name of the patient to be entered in the
	 *            textbox.
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public void SearchPatientLastName(String fLastName) throws Exception {
		APPLICATION_LOGS.debug("Entry: SearchPatientLastName");
		clickElement("xpath", "//input[@id='nav-search-input']");
		clearText("xpath", "//input[@id='nav-search-input']");
		enterText("xpath", "//input[@id='nav-search-input']", fLastName);
		clickElement("xpath", "//i[@id='clMpSearchFilter']");
		Thread.sleep(3000);
		APPLICATION_LOGS.debug("Exit: SearchPatientLastName");
	}

	/**
	 * MethodName : SearchPatientMedicaid Objective: This method is used to
	 * search a patient using their Medicaid no.
	 * 
	 * @param medicaid
	 *            denotes the medicaid of the caretool program.
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public void SearchPatientMedicaid(String medicaid) throws Exception {
		APPLICATION_LOGS.debug("Entry: SearchPatientMedicaid");
		clickElement("xpath", "//input[@id='nav-search-input']");
		clearText("xpath", "//input[@id='nav-search-input']");
		enterText("xpath", "//input[@id='nav-search-input']", medicaid);
		if (!locateElement("cssSelector", ".js-exact-search-button").getAttribute("class")
				.contains("active blue")) {
			Thread.sleep(3000);
			clickElement("cssSelector", ".js-exact-search-button>.faicon-exact");
		}
		mouseHover("id", "clMpSearchFilter");
		clickElement("xpath", "//i[@id='clMpSearchFilter']");
		waitForCareToolToReload();
		Thread.sleep(3000);
		APPLICATION_LOGS.debug("Exit: SearchPatientMedicaid");
	}

	/**
	 * This method is used to search patient by any criteria
	 * 
	 * @param searchText
	 * @throws Exception
	 */
	public void searchPatient(String searchText) throws Exception {
		APPLICATION_LOGS.debug("Entry: searchPatient");
		clickElement("id", "nav-search-input");
		clearText("id", "nav-search-input");
		enterText("id", "nav-search-input", searchText);
		clickElement("id", "clMpSearchFilter");
		waitForCareToolToReload();
		APPLICATION_LOGS.debug("Exit: searchPatient");
	}

	/**
	 * This method is used to search patient by any criteria
	 * 
	 * @param searchText
	 * @throws Exception
	 */
	public void searchPatientUsingDatabase(String query) throws Exception {
		APPLICATION_LOGS.debug("Entry: searchPatientUsingDatabase");
		String patientId = null;
		try {
			ResultSet resultSet = getQueryResult(query);
			while (resultSet.next()) {
				patientId = resultSet.getString("IdentifierValue");
				identifierValue = patientId;
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (null != patientId) {
			clickElement("id", "nav-search-input");
			clearText("id", "nav-search-input");
			enterText("id", "nav-search-input", patientId);
			if (!locateElement("cssSelector", ".js-exact-search-button").getAttribute("class")
					.contains("active blue")) {
				Thread.sleep(3000);
				clickElement("cssSelector", ".js-exact-search-button>.faicon-exact");
			}
			mouseHover("id", "clMpSearchFilter");
			Thread.sleep(3000);
			clickElement("id", "clMpSearchFilter");
			waitForCareToolToReload();
			APPLICATION_LOGS.debug("Exit: searchPatientUsingDatabase");
		} else {
			APPLICATION_LOGS.debug("Test data(Patient ID) does not exists. Query returned 0 rows");
			System.out.println("Test data(Patient ID) does not exists. Query returned 0 rows");
		}

	}

	/**
	 * MethodName : NavigateAddAppointment() Objective :This method is used to
	 * navigate to Add Appointment screen in HH,open the link and then verify
	 * all the web elements present in the same. These elements are Appointment
	 * Table,Appointment Type, Call back date calender,Appt Phone,
	 * Location,Role, Party and Remark.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void NavigateAddAppointment() throws Exception {
		APPLICATION_LOGS.debug("Entry: NavigateAddAppointment");
		scrollDownToObject("xpath", "//table[@id='appointmentTable']");
		clickElement("xpath", "//table[@id='appointmentTable']");// By Sneha
		Thread.sleep(2000);
		verifyElementPresent("xpath", "//input[@id='apptType_input--3']");
		verifyElementPresent("xpath", "//input[@id='outreach-callback-date-input--3']");
		verifyElementPresent("xpath", "//input[@id='apptPhone--3']");
		verifyElementPresent("xpath", "//input[@id='apptLocation--3']");
		verifyElementPresent("xpath", "//input[@id='apptRole--3']");
		verifyElementPresent("xpath", "//input[@id='apptParty--3']");
		verifyElementPresent("xpath", "//input[@id='apptRemark--3']");
		APPLICATION_LOGS.debug("Exit: NavigateAddAppointment");
	}

	/**
	 * MethodName: scrollDownCreateContact() Objective: This method is used in
	 * BPCI to scroll down in Create Contact page and then click on Save button.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void scrollDownCreateContact() throws Exception {
		APPLICATION_LOGS.debug("Entry: scrollDownCreateContact");
		WebElement scroll = mD.findElement(By.id("cnctInputSaveBtn"));
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		scroll.click();
		Thread.sleep(2000);
		APPLICATION_LOGS.debug("Exit: scrollDownCreateContact");
	}

	/**
	 * MethodName: scrollDownProviderContact() Objective: This method is used in
	 * BPCI to scroll down in Provider Contact page and then click on Save
	 * button.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void scrollDownProviderContact() throws Exception {
		APPLICATION_LOGS.debug("Entry: scrollDownProviderContact");
		WebElement scroll = mD.findElement(By.id("changeStatusBtn"));
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		scroll.click();
		Thread.sleep(2000);
		APPLICATION_LOGS.debug("Exit: scrollDownProviderContact");
	}

	/**
	 * MethodName: scrollDownGroundsUp() Objective: This method is used in BPCI
	 * to scroll down in Provider Contact page and then click on Save button.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void scrollDownGroundsUp() throws Exception {
		APPLICATION_LOGS.debug("Entry: scrollDownGroundsUp");
		WebElement scroll = mD.findElement(By.xpath("//div[@class='js-groundUp-form-add']"));
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		scroll.click();
		Thread.sleep(2000);
		APPLICATION_LOGS.debug("Exit: scrollDownGroundsUp");
	}

	/**
	 * MethodName: scrollDown() Objective: This method is used to scroll the Web
	 * page down by a specified pixel.
	 */
	public static void scrollDown() {
		APPLICATION_LOGS.debug("Entry: scrollDown");
		JavascriptExecutor jse = (JavascriptExecutor) mD;
		jse.executeScript("window.scrollBy(0,1000)", "");
		APPLICATION_LOGS.debug("Exit: scrollDown");
	}

	/**
	 * MethodName: scrollUp() Objective: This method is used to scroll the Web
	 * page up by a specified pixel.
	 */
	public static void scrollUp() {
		APPLICATION_LOGS.debug("Entry: scrollUp");
		JavascriptExecutor jse = (JavascriptExecutor) mD;
		jse.executeScript("window.scrollBy(0,-250)", "");
		APPLICATION_LOGS.debug("Exit: scrollUp");
	}
	/**
	 * MethodName: scrollUp() Objective: This method is used to scroll the Web
	 * page up by a specified pixel.
	 * @throws AWTException 
	 */
	public static void scrollDown500() throws AWTException {
		APPLICATION_LOGS.debug("Entry: scrollDowm");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		/*JavascriptExecutor jse = (JavascriptExecutor) mD;
		jse.executeScript("scroll(0, 500);");*/
		System.out.println("ScrollDown");
		APPLICATION_LOGS.debug("Exit: scrollUp");
	}

	/**
	 * MethodName: scrollDownClinicalMedication() Objective : This method is
	 * used to scroll down in Clinical medication frame of BPCI and then click
	 * on the Save button.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void scrollDownClinicalMedication() throws Exception {
		APPLICATION_LOGS.debug("Entry: scrollDownClinicalMedication");
		WebElement scroll = mD.findElement(By.id("cinInputSaveBtn"));
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		scroll.click();
		Thread.sleep(2000);
		APPLICATION_LOGS.debug("Exit: scrollDownClinicalMedication");
	}

	/**
	 * MethodName: scrollDownMedication() Objective: This method is used to
	 * scroll down in Medication list frame of BPCI and then click on the Save
	 * button.
	 * 
	 * @param successMessage
	 *            denotes the success message
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void scrollDownMedication(String successMessage) throws Exception {
		APPLICATION_LOGS.debug("Entry: scrollDownMedication");
		WebElement scroll = mD.findElement(By.xpath("//div[@class='webgrid-selected-row simple-border']/button[3]"));
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		scroll.click();
		Thread.sleep(2000);
		successMessage(successMessage);
		APPLICATION_LOGS.debug("Exit: scrollDownMedication");
	}

	/**
	 * MethodName: verifyBreadcrumb() Objective: This method is used to compare
	 * the result of actual breadcrumb text derived from HealthHome with that to
	 * the expected breadcrumb of a bundle.
	 * 
	 * @param expBreadCrumb
	 *            the exp bread crumb
	 * @return the string
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public String verifyBreadcrumb(String expBreadCrumb) throws Exception {
		APPLICATION_LOGS.debug("Entry: verifyBreadcrumb");
		String actBreadCrumb = mD.findElement(By.xpath("//ul[@id='mybread_crumb']")).getText();
		APPLICATION_LOGS.debug(actBreadCrumb);
		if (expBreadCrumb.contains(actBreadCrumb)) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + actBreadCrumb + " matched " + expBreadCrumb + "");
			return "Pass";
		} else {
			ts_error = "Text not found";
			APPLICATION_LOGS.debug("" + actBreadCrumb + " does not matched " + expBreadCrumb + "");
			APPLICATION_LOGS.debug("Exit: verifyBreadcrumb");
			return "Fail";
		}
	}

	/**
	 * MethodName: treenodecount() Objective: This method is used to count and
	 * compare the results of tree node with the expected breadcrumb of
	 * HealthHome to that retrieved by clicking actual breadcrumb.
	 * 
	 * @param expBreadCrumb
	 *            the String given in the excelsheet
	 * @return the string
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public String treenodecount(String expBreadCrumb) throws Exception {
		APPLICATION_LOGS.debug("Entry: treenodecount");
		String actBreadCrumb = mD.findElement(By.xpath("//ul[@id='mybread_crumb']")).getText();
		APPLICATION_LOGS.debug(actBreadCrumb);
		if (expBreadCrumb.contains(actBreadCrumb)) {
			String delims = " ";
			StringTokenizer st = new StringTokenizer(actBreadCrumb, delims);
			while (st.hasMoreElements()) {
				APPLICATION_LOGS.debug("" + st.nextElement());
			}
			String[] tokens = actBreadCrumb.split(delims);
			int tokenCount = tokens.length;
			APPLICATION_LOGS.debug("tokenCount" + tokenCount);
			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + actBreadCrumb + " matched " + expBreadCrumb + "");
			return "Pass";
		} else {
			ts_error = "Text not found";
			APPLICATION_LOGS.debug("" + actBreadCrumb + " does not matched " + expBreadCrumb + "");
			APPLICATION_LOGS.debug("Exit: treenodecount");
			return "Fail";
		}
	}

	/**
	 * MethodName: enterAssessPatientDate() Objective: This method is used to
	 * enter Assessment date in Assessment frame of HealthHome page.
	 *
	 * @param assessDate
	 *            the assess date
	 * @throws Exception
	 *             Signals that the NoSuchElementException,NoSuchFrameException
	 *             has occurred.
	 */
	public void enterAssessPatientDate(String assessDate) throws Exception {
		APPLICATION_LOGS.debug("Entry: enterAssessPatientDate");
		mD.switchTo().frame(locateElement("cssSelector",".assessments__media"));
		locateElement("xpath", "//input[@class='nslijhs-assessment-text-question-field' and @type='text' and @placeholder='Date (e.g. MM/DD/YYYY)']").sendKeys(assessDate);
		locateElement("xpath", "//i[@class='nslijhs-assessment-radio-question-field fa fa-circle-o' and @value='Discharge']").click();
		Thread.sleep(2000);
		APPLICATION_LOGS.debug("Exit: enterAssessPatientDate");
	}

	/**
	 * MethodName: createNote Objective : This method is used to create the
	 * notes for BPCI program.
	 * 
	 * @param Text
	 *            the text
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void createNote(String Text) throws Exception {
		APPLICATION_LOGS.debug("Entry: createNote");
		List<WebElement> iframe = mD.findElements(By.name("hiddenCallType"));
		mD.switchTo().frame(0);
		// Thread.sleep(1500);
		clickElement("xpath", "//input[@id='noteTitle']");
		// Thread.sleep(1500);
		clickLink("linkText", "Create Note");
		// Thread.sleep(1000);
		clickElement("xpath", "//a[contains(text(),'Create Note')]");
		// Thread.sleep(3000);
		// WebElement scroll
		// =mD.findElement(By.xpath("//input[@id='noteInputFormNoteTitle']"));
		WebElement scroll = locateElement("xpath", "//input[@id='noteInputFormNoteTitle']");
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		scroll.sendKeys(Text);
		enterText("xpath", "//textarea[@id='noteInputFormNotetext']", Text);
		clickElement("xpath", "//button[@id='noteInputFormSave']");
		APPLICATION_LOGS.debug("Exit: createNote");
	}

	/**
	 * MethodName: successMessage() Objective : This method is used to verify
	 * the Success Message retrieved from the Web element with that to the input
	 * string successMessage.
	 * 
	 * @param successMessage
	 *            the success message
	 * @return the string
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public String successMessage(String successMessage) throws Exception {
		APPLICATION_LOGS.debug("Entry: successMessage");
		// Thread.sleep(1500);
		// String expMessage = mD.findElement(By.xpath("//div[contains(@class,
		// 'toast-message')]")).getText();
		String expMessage = locateElement("cssSelector", "div.toast-message").getText();
		if (expMessage.equals(successMessage)) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + expMessage + " matched " + successMessage + "");
			return "Pass";
		} else {
			ts_error = "Text not found";
			APPLICATION_LOGS.debug("" + expMessage + " does not matched " + successMessage + "");
			APPLICATION_LOGS.debug("Exit: successMessage");
			return "Fail";
		}

	}

	/**
	 * MethodName: verifyChangeStatusSuccessMessage() Objective : This method is
	 * used to verify the Success Message retrieved from the Web element with
	 * that to the input string successMessage.
	 * 
	 * @param successMessage
	 *            the success message
	 * @return the string
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public String verifyChangeStatusSuccessMessage(String successMessage) throws Exception {
		APPLICATION_LOGS.debug("Entry: verifyChangeStatusSuccessMessage");
		// Thread.sleep(1500);
		// String expMessage = mD.findElement(By.xpath("//div[contains(@class,
		// 'toast-message')]")).getText();
		String expMessage = locateElement("cssSelector", "div.toast-message").getText();

		if (expMessage.contains(successMessage)) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + expMessage + " matched " + successMessage + "");
			return "Pass";
		} else {
			ts_error = "Text not found";
			APPLICATION_LOGS.debug("" + expMessage + " does not matched " + successMessage + "");
			APPLICATION_LOGS.debug("Exit: verifyChangeStatusSuccessMessage");
			return "Fail";
		}

	}

	/**
	 * MethodName: transitionofCare() Objective: This method is used in
	 * Transition of Care frame of BPCI to scroll down and click on Save button.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public void transitionofCare() throws Exception {
		APPLICATION_LOGS.debug("Entry: transitionofCare");
		// WebElement scroll =
		// mD.findElement(By.xpath("//button[@id='tocInputFinalizeBtn']"));
		WebElement scroll = locateElement("xpath", "//button[@id='tocInputFinalizeBtn']");
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		scroll.click();
		APPLICATION_LOGS.debug("Exit: transitionofCare");
	}

	/**
	 * MethodName: clickCVDemoIcon() Objective : This method is used in Clinical
	 * Viewer to switch frames and then click on Demographic icon of the
	 * patient.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException,NoSuchFrameException,
	 *             InterruptedException has occurred.
	 */
	public void clickCVDemoIcon() throws Exception {
		APPLICATION_LOGS.debug("Entry: clickCVDemoIcon");
		mD.switchTo().frame("TRAK_main");
		Thread.sleep(1000);
		mD.switchTo().frame("TRAK_main");
		Thread.sleep(1000);
		mD.switchTo().frame("patframe");
		Thread.sleep(1000);
		mD.findElement(By.xpath("//img[@title='Patient Demographic']")).click();
		Thread.sleep(1000);
		APPLICATION_LOGS.debug("Exit: clickCVDemoIcon");
	}

	/**
	 * MethodName: verifyCVDataFrame() Objective : This method is used in
	 * Clinical Viewer to switch frames and then verify the Patient Details
	 * present in that frame.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException,NoSuchFrameException,
	 *             InterruptedException has occurred.
	 */
	public void verifyCVDataFrame() throws Exception {
		APPLICATION_LOGS.debug("Entry: verifyCVDataFrame");
		Thread.sleep(2000);
		mD.switchTo().frame("TRAK_main");
		Thread.sleep(2000);
		mD.switchTo().frame("TRAK_main");
		Thread.sleep(2000);
		mD.switchTo().frame("maindata");
		Thread.sleep(2000);
		mD.switchTo().frame("dataframe");
		Thread.sleep(2000);
		verifyElementPresent("xpath", "//div[@id='aagformsection']//h2[contains(text(),'Patient Details')]");
		highlightElement("xpath", "//div[@id='aagformsection']//h2[contains(text(),'Patient Details')]");

		verifyElementPresent("xpath", "//div[@id='aagsection']//h2[contains(text(),'Emergency Contact/Next of Kin')]");
		highlightElement("xpath", "//div[@id='aagsection']//h2[contains(text(),'Emergency Contact/Next of Kin')]");

		scrollDown();

		verifyElementPresent("xpath", "//div[@id='aagsection']//h2[contains(text(),'Patient Identifiers')]");
		highlightElement("xpath", "//div[@id='aagsection']//h2[contains(text(),'Patient Identifiers')]");

		verifyElementPresent("xpath", "//div[@id='aagsection']//h2[contains(text(),'Insurance Details')]");
		highlightElement("xpath", "//div[@id='aagsection']//h2[contains(text(),'Insurance Details')]");

		APPLICATION_LOGS.debug("Exit: verifyCVDataFrame");
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

			APPLICATION_LOGS.debug("Exit: getTable ");
		}
	}

	/**
	 * MethodName: SearchPatientFirstName() Objective: This method is used to
	 * search a patient using their First Name.
	 * 
	 * @param fFirstName
	 *            denotes the first name of the patient
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void SearchPatientFirstName(String fFirstName) throws Exception {
		APPLICATION_LOGS.debug("Entry: SearchPatientFirstName");
		clickElement("xpath", "//input[@id='nav-search-input']");
		clearText("xpath", "//input[@id='nav-search-input']");
		enterText("xpath", "//input[@id='nav-search-input']", fFirstName);
		clickElement("xpath", "//i[@id='clMpSearchFilter']");
		// Thread.sleep(3000);
		APPLICATION_LOGS.debug("Exit: SearchPatientFirstName");
	}

	/**
	 * MethodName: scrollviewtrue() Objective: This method scrolls the current
	 * element into the visible area of the browser window.
	 * 
	 * @param fxPath
	 *            the fx path
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void scrollviewtrue(String locatorType, String locatorObject) throws Exception {
		APPLICATION_LOGS.debug("Entry: scrollviewtrue");
		WebElement scroll = locateElement(locatorType, locatorObject);
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		scroll.click();
		Thread.sleep(2000);
		APPLICATION_LOGS.debug("Exit: scrollviewtrue");
	}

	/**
	 * MethodName: mouseHoverForCINRadioButton() Objective: This method is used
	 * to perform mouse hover function in Clinical Interaction Note of
	 * HealthHome.
	 * 
	 * @param fxPath
	 *            denotes the Xpath of the element.
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public void mouseHoverForCINRadioButton(String fxPath) throws Exception {
		APPLICATION_LOGS.debug("Entry: mouseHoverForCINRadioButton");
		WebElement searchBtn = mD.findElement(By.xpath(fxPath));

		Actions action = new Actions((mD));
		action.moveToElement(searchBtn);
		action.click();
		action.perform();
		APPLICATION_LOGS.debug("Exit: mouseHoverForCINRadioButton");
	}

	/**
	 * MethodName: retrieveDataHH() Objective: This method is used to retrieve
	 * the NY Medicaid of an Outreach patient in
	 * HealthHome-NSLIJHS-QUEENS-OUTREACH-Outreach node.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred. 22-Aug-2016: Satheesh: Changed Xpath from
	 *             //span[@id='tocCriticalElementsMRN'] to
	 *             (//span[@id='tocCriticalElementsMRN'])[1]
	 */

	public void retrieveDataHH() throws Exception {
		APPLICATION_LOGS.debug("Entry: retrieveDataHH");
		clickElement("xpath", "//li[@data-nodeid='HealthHome-NSLIJHS-NASSAU-OUTREACH-Outreach']");
		Thread.sleep(10000);
		String medicId = mD.findElement(By.id("tocCriticalElementsMRN")).getText();
		clickElement("xpath", "//input[@id='nav-search-input']");
		clearText("xpath", "//input[@id='nav-search-input']");
		enterText("xpath", "//input[@id='nav-search-input']", medicId);
		clickElement("xpath", "//i[@id='clMpSearchFilter']");
		Thread.sleep(3000);
		APPLICATION_LOGS.debug("Exit: retrieveDataHH");
	}

	/**
	 * MethodName: retrieveDataPBCM() Objective: This method is used to retrieve
	 * the NY Medicaid of an Inactive patient in PBCM-SIUH
	 * MAP-HEALTHFIRST-Inactive node.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void retrieveDataPBCM() throws Exception {
		APPLICATION_LOGS.debug("Entry: retrieveDataPBCM");
		clickElement("xpath", "//li[@data-nodeid='PBCM-NON EMBEDDED-HEALTHFIRST-Inactive']");
		Thread.sleep(10000);
		String medicId = mD.findElement(By.xpath("//span[@id='tocCriticalElementsMRN']")).getText();
		clickElement("xpath", "//input[@id='nav-search-input']");
		clearText("xpath", "//input[@id='nav-search-input']");
		enterText("xpath", "//input[@id='nav-search-input']", medicId);
		clickElement("xpath", "//i[@id='clMpSearchFilter']");
		Thread.sleep(3000);
		APPLICATION_LOGS.debug("Exit: retrieveDataPBCM");
	}

	/**
	 * MethodName: retrieveDataBPCI() Objective: This method is used to retrieve
	 * the NY Medicaid of an Identification patient in FHH facility.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void retrieveDataBPCI() throws Exception {
		APPLICATION_LOGS.debug("Entry: retrieveDataBPCI");
		clickElement("xpath",
				"//a[@data-status='Identification' and @data-subprogram='COPD' and @data-facility='FHH' and @data-program='BPCI']");
		Thread.sleep(10000);
		APPLICATION_LOGS.debug("Exit: retrieveDataBPCI");

	}

	/**
	 * MethodName: scrollDownMedicationDraft() Objective: This method scrolls
	 * the current element into the visible area of the browser window.
	 * 
	 * @param successMessage
	 *            the success message
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void scrollDownMedicationDraft(String successMessage) throws Exception {
		APPLICATION_LOGS.debug("Entry: scrollDownMedicationDraft");
		WebElement scroll = mD.findElement(By.xpath("//div[@class='webgrid-selected-row simple-border']/button[2]"));
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		scroll.click();
		Thread.sleep(2000);
		successMessage(successMessage);
		APPLICATION_LOGS.debug("Exit: scrollDownMedicationDraft");
	}

	/**
	 * MethodName:enrollmentDate() Objective: This method is used to enter
	 * Assessment date in Assessment frame of HealthHome page.
	 * 
	 * @param fxPath
	 *            denotes the Xpath of the element
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public void enrollmentDate(String locatorType, String locatorObject, String fText) throws Exception {
		APPLICATION_LOGS.debug("Entry: enrollmentDate");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();

		if (fText.equals("TodaysDate")) {
			cal.setTime(date);
			fText = dateFormat.format(date);
			locateElement(locatorType, locatorObject).sendKeys(dateFormat.format(cal.getTime()));
		}
		APPLICATION_LOGS.debug("Exit: enrollmentDate");
	}

	/**
	 * MethodName: mandatoryFieldCheck() Objective: This method is used to find
	 * the mandatory field with * symbol.
	 * 
	 * @param fxPath
	 *            denotes the Xpath of the element
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public String mandatoryFieldCheck(String locatorType, String locatorObject) throws Exception {
		APPLICATION_LOGS.debug("Entry: mandatoryFieldCheck");
		String fTextOut = locateElement(locatorType, locatorObject).getText();
		System.out.println(fTextOut);
		if (fTextOut.contains("*")) {

			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + fTextOut + "is mandatory ");
			return "Pass";
		} else {
			ts_error = "text verification failed";
			APPLICATION_LOGS.debug("" + fTextOut + "is not mandatory ");
			APPLICATION_LOGS.debug("Exit: mandatoryFieldCheck");
			return "Fail";
		}
	}

	/**
	 * MethodName: dateOfBirthMatch() Objective: This method is used to verify
	 * the DOB match.
	 * 
	 * @return the string
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public String dateOfBirthMatch() throws Exception {
		APPLICATION_LOGS.debug("Entry: dateOfBirthMatch");
		String fTextcaseviewer = mD.findElement(By.xpath("(//li[@class='push-quarter--bottom'])[2]")).getText();
		System.out.println(fTextcaseviewer);
		String fTextcasedetils = mD
				.findElement(
						By.xpath("(//div[@class='caseListItem__header']//span[@class='caseListItem__titleMeta'])[1]"))
				.getText();
		System.out.println(fTextcaseviewer);

		if (fTextcaseviewer.contains(fTextcasedetils)) {

			ts_error = "No Error";
			APPLICATION_LOGS.debug("Birth date is correct");
			return "Pass";
		} else {
			ts_error = "text verification failed";
			APPLICATION_LOGS.debug("Birth date is incorrect");
			APPLICATION_LOGS.debug("Exit: dateOfBirthMatch");
			return "Fail";
		}
	}

	/**
	 * MethodName: printPDF() Objective: This method is used to print the
	 * assessment in PDF format.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public void printPDF() throws Exception {
		APPLICATION_LOGS.debug("Entry: printPDF");
		Thread.sleep(2000);

		Robot r = new Robot();

		r.delay(2000);

		try {
			Actions builder = new Actions(mD);
			builder.keyDown(Keys.CONTROL);
			builder.keyDown(Keys.SHIFT);
			builder.sendKeys("S");
			builder.keyUp(Keys.CONTROL);
			builder.keyUp(Keys.SHIFT);
			Thread.sleep(1000);
			builder.build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR" + e);

		}
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		APPLICATION_LOGS.debug("Exit: printPDF");
	}

	/**
	 * MethodName : validateRunTimeText() Objective: This method is used to
	 * validate the run time error message in Grounds Up Form.
	 * 
	 * @param fxPath
	 *            denotes the Xpath of the element
	 * @param fText
	 *            denotes the Value to be entered in the Textbox
	 * @throws Exception
	 *             Signals that the NoSuchElementException has occurred.
	 */
	public void validateRunTimeText(String locatorType, String locatorObject, String fText) throws Exception {
		APPLICATION_LOGS.debug("Entry: validateRunTimeText");
		String getDOB = locateElement(locatorType, locatorObject).getText();

		if (getDOB.equals(fText)) {
			APPLICATION_LOGS.debug("Pass");
		} else {
			APPLICATION_LOGS.debug("Fail");
		}
		APPLICATION_LOGS.debug("Exit: validateRunTimeText");
	}

	/**
	 * MethodName: clickElementbyDataValue() Objective: This method is used to
	 * click on the change status dropdown value.
	 * 
	 * @param fText
	 *            denotes the value available in dropdown
	 * @throws InterruptedException
	 * @throws Exception
	 *             Signals that the InterruptedException has occurred.
	 */
	public void clickElementbyDataValue(String fText) throws InterruptedException {
		APPLICATION_LOGS.debug("Entry: clickElementbyDataValue");
		// Thread.sleep(2000);
		new WebDriverWait(mD, 10).until(ExpectedConditions.elementToBeClickable(By.linkText(fText))).click();
		// mD.findElement(By.linkText(fText)).click();
		APPLICATION_LOGS.debug("Exit: clickElementbyDataValue");
	}
	
	public void clickElementbyDataValuePL(String fText) throws InterruptedException {
		APPLICATION_LOGS.debug("Entry: clickElementbyDataValue");
		// Thread.sleep(2000);
		new WebDriverWait(mD, 10).until(ExpectedConditions.elementToBeClickable(By.partialLinkText(fText))).click();
		// mD.findElement(By.linkText(fText)).click();
		APPLICATION_LOGS.debug("Exit: clickElementbyDataValue");
	}


	/**
	 * MethodName: scrollDownClinicalMedication() Objective : This method is
	 * used to scroll down in Clinical medication frame of BPCI and then click
	 * on the Save button.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void scrollDownToObject(String locatorType,String xpath) throws Exception {
		APPLICATION_LOGS.debug("Entry: scrollDownProviderContact");
		WebElement scroll =locateElement(locatorType, xpath);
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		scroll.click();
		// Thread.sleep(2000);
		APPLICATION_LOGS.debug("Exit: scrollDownProviderContact");
	}
	
	/**
	 * Scrolls to the specified element.
	 * @param locatorType
	 * @param locatorObject
	 * @throws Exception
	 */
	public void scrollToObject(String locatorType,String locatorObject) throws Exception {
		APPLICATION_LOGS.debug("Entry: scrollDownProviderContact");
		WebElement scroll =locateElement(locatorType, locatorObject);
		JavascriptExecutor executor = (JavascriptExecutor) mD;
		executor.executeScript("arguments[0].scrollIntoView(true)", scroll);
		APPLICATION_LOGS.debug("Exit: scrollDownProviderContact");
	}

	// This method id used to get the text from attribute 'value'
	public void GetTextCarePlan(String locatorType, String locatorObject, String ftextin) {

		String Ftextout = locateElement(locatorType, locatorObject).getAttribute("value");

		if ((Ftextout.trim()).equals(ftextin.trim())) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + Ftextout + " matched with " + ftextin + " successfully");

		} else {
			ts_error = "text verification failed";
			APPLICATION_LOGS.debug("" + Ftextout + " not matched with " + ftextin + " successfully");
			APPLICATION_LOGS.debug("Exit: verifyText");

		}

	}

	/*
	 * This method is used to verify the order of date in ascending order in the
	 * Care plan drop down list box. Before using this method, ensure the xpath
	 * of drop down list
	 */
	public void CarePlanlistOrder() {

		int i;
		String[] s = new String[10];
		String[] date = new String[10];
		String dd, mm, yyyy;
		Boolean k = null;
		for (i = 1; i < 1000; i++) {
			String xpath = "html/body/div[2]/div/ul/li["; // xpath of first
															// option of
															// dropdown list
															// without "x]"
			String xpath2 = xpath + i + "]";
			try {
				k = locateElementByXpath(xpath2).isDisplayed();
			} catch (NoSuchElementException | TimeoutException e) {
				APPLICATION_LOGS.debug("Invalid xpath");
				k = false;
			}
			if (k == true) {
				try {
					s[i] = locateElementByXpath(xpath2).getText();
					s[i] = s[i].substring(0, 10);
					mm = s[i].substring(0, 2); // getting month part alone
					dd = s[i].substring(3, 5); // getting date part alone
					yyyy = s[i].substring(6, 10); // getting Year part alone
					date[i] = yyyy + mm + dd;
				} catch (NullPointerException e) {
					APPLICATION_LOGS.debug("Drop down List completed");
				}
			} else {
				break;
			}
		}
		int si[] = new int[i];
		for (int h = 1; h < i; h++)// converting to Integers
		{
			try {
				date[h] = date[h].replace(" ", "");
			} catch (NumberFormatException | NullPointerException e) {
				APPLICATION_LOGS.debug("Invalid date");
			}
			try {
				si[h] = Integer.valueOf(date[h]);
			} catch (NumberFormatException | NullPointerException e) {
				APPLICATION_LOGS.debug("Invalid Number");

			}
		}
		for (int j = 1; j < i - 1; j++) {
			if (si[j] < si[j + 1]) // deciding that whether date are in correct
									// order
			{
				APPLICATION_LOGS.debug("Data are in Ascending Order");
			} else
				APPLICATION_LOGS.debug("Data are NOT in Ascending Order");
		}
	}

	// This method used to accept the alert while saving the Fact Assessment
	public void FactAssessmentAlertAccept1() throws InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

		try {
			mD.switchTo().alert().accept();
		} catch (UnhandledAlertException f) {

			Alert alert = mD.switchTo().alert();
			String warningText = alert.getText();
			alert.accept();
			Assert.assertEquals(warningText,
					"Are you sure you want to do a finalized save on this assessment? The responses in a finalized assessment are not editable.");
		}
	}

	// This Method is used to compare the given date to current date.
	public void CompareWithTodaysDate(String locatorType, String locatorObject) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// get current date time with Date()
		Date date = new Date();
		String Today = dateFormat.format(date);

		String DateInElement = locateElement(locatorType, locatorObject).getText();

		if (DateInElement.contains(Today)) {
			APPLICATION_LOGS.debug("It is Today's date");
		} else {
			APPLICATION_LOGS.debug("It is Not Today's date");
		}
	}

	/**
	 * This method will wait until the Care toll application completely loads
	 * 
	 * @param maxWaitTime
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public void waitForCareToolToReload() throws InterruptedException {
		APPLICATION_LOGS.debug("Entry: waitForCareToolToReload");
		long maxWaitTime = 180;
		try {
			new WebDriverWait(mD, maxWaitTime)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader.loader--text")));
			new WebDriverWait(mD, maxWaitTime)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader.loader--text")));
			new WebDriverWait(mD, maxWaitTime)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader-progress")));
			new WebDriverWait(mD, maxWaitTime)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader.loader--text")));
			new WebDriverWait(mD, maxWaitTime)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader.loader--text")));
			new WebDriverWait(mD, maxWaitTime).until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector(".case-header div[id*='CaseEditor_caseInfo']")));
			APPLICATION_LOGS.debug("Exit: waitForCareToolToReload");
		} catch (TimeoutException e) {

		}
	}

	/**
	 * MethodName: retrieveDataHH() Objective: This method is used to retrieve
	 * the NY Medicaid of an Outreach patient in
	 * HealthHome-NSLIJHS-QUEENS-OUTREACH-Outreach node.
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public void retrieveDataHHFromDB(String query) throws Exception {
		String medicId = null;
		APPLICATION_LOGS.debug("Entry: retrieveDataHHFromDB");
		clickElement("xpath", "//li[@data-nodeid='HealthHome-NSLIJHS-NASSAU-OUTREACH-Outreach']");
		Thread.sleep(10000);
		Connection conn = DBConnection.getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		System.out.println("Query:" + query);
		while (rs.next()) {
			System.out.println("First Identifier value: " + rs.getString("IdentifierValue"));
			medicId = rs.getString("IdentifierValue");
			if (null != medicId) {
				setTestData("HHOutreachMedicaId", medicId);
			}
			break;
		}
		clickElement("xpath", "//input[@id='nav-search-input']");
		enterText("xpath", "//input[@id='nav-search-input']", medicId);
		clickElement("xpath", "//i[@id='clMpSearchFilter']");
		Thread.sleep(3000);
		APPLICATION_LOGS.debug("Exit: retrieveDataHHFromDB");
	}

	/**
	 * MethodName: getQueryResult()
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public ResultSet getQueryResult(String query) {
		Connection conn = DBConnection.getConnection();
		Statement statement;
		ResultSet rs = null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * MethodName: getQueryResult()
	 * 
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public String getColumnValue(String query, String columnName) {
		String columnValue = null;
		try {
			ResultSet resultSet = getQueryResult(query);
			while (resultSet.next()) {
				columnValue = resultSet.getString(columnName.trim());
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnValue;
	}

	/**
	 * MethodName: getQueryResult()
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 *             Signals that the NoSuchElementException, InterruptedException
	 *             has occurred.
	 */
	public String verifyDatabaseColumnValue(String query, Map<String, String> map) {
		String result = "Fail";
		String updatedQuery;
		updatedQuery = String.format(query, identifierValue);
		System.out.println("Updated Query:" + updatedQuery);
		for (String column : map.keySet()) {
			String columnValue = getColumnValue(updatedQuery, column);
			if (columnValue != null && map.get(column).equals(columnValue)) {
				result = "Pass";
			} else {
				result = "Fail";
			}
		}
		return result;
	}
	
	/**
	 * This method is to verify the Patient Id.
	 * @param locatorType
	 * @param locatorObject
	 * @return
	 * @throws NoSuchElementException
	 */
	public String verifyPatientId(String locatorType, String locatorObject) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: verifyTextIgnoreCase");
		String fTextOut = locateElement(locatorType, locatorObject).getText();
		if ((fTextOut.trim()).equalsIgnoreCase(identifierValue.trim())) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug("" + fTextOut + " matched with " + identifierValue + " successfully");
			return "Pass";
		} else {
			ts_error = "text verification failed";
			APPLICATION_LOGS.debug("" + fTextOut + " not matched with " + identifierValue + " successfully");
			APPLICATION_LOGS.debug("Exit: verifyText");
			return "Fail";
		}
	}

	// This method is used to accept the alert when save the careplan without
	// any problem data
	public void VerifyAlerttextCareplan() throws InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

		try {
			mD.switchTo().alert().accept();

		} catch (UnhandledAlertException f) {
			Alert alert = mD.switchTo().alert();
			String warningText = alert.getText();
			alert.accept();
			Assert.assertEquals(warningText,
					"The Care Plan is currently in an invalid state for saving. Reason(s): Missing a problem");
			System.out.println("Verified and accepted alert");
		}

	}

	// This method is used to accept the alert when save the careplan with
	// problem data
	public void AlertAcceptCarePlanSave() throws InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

		try {
			mD.switchTo().alert().accept();

		} catch (UnhandledAlertException f) {
			Alert alert = mD.switchTo().alert();
			String warningText = alert.getText();
			alert.accept();
			Assert.assertEquals(warningText, "Successfully Created Care Plan. Click to Retrieve Saved Care Plan.");
		}

	}

	// This method is used to accept the alert when save the careplan without
	// Domain data
	public void AlertAcceptCarePlanSaveWithoutDomain() throws InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

		try {
			mD.switchTo().alert().accept();

		} catch (UnhandledAlertException f) {
			Alert alert = mD.switchTo().alert();
			String warningText = alert.getText();
			alert.accept();
			Assert.assertEquals(warningText,
					"The Care Plan is currently in an invalid state for saving. Reason(s):Missing a domain");
		}

	}

	// This method is used to verify the specified tab is active
	public String verifyActionsTabIsActive(String locatorType, String locatorObject) {
		String Ftextout = locateElement(locatorType, locatorObject).getAttribute("class");
		if (null != Ftextout && (Ftextout.trim()).contains("active")) {
			ts_error = "No Error";
			APPLICATION_LOGS.debug("Specified Actions tab is active");
			return "Pass";
		} else {
			ts_error = "Specified Actions tab is not active";
			APPLICATION_LOGS.debug("Specified Actions tab is not active");
			APPLICATION_LOGS.debug("Exit: verifyActionTabIsActive");
			return "Fail";
		}

	}

	// This method is used to switch to active frame .
	public void switchToActiveFrame() {
		mD.switchTo().frame(locateElement("cssSelector",
				"div[id*='Modal_view']>div[aria-hidden='false'] iframe.modal-body.js-body"));
		APPLICATION_LOGS.debug("Switched to Frame");
	}
	
	// This method is used to switch to Assessments frame .
		public void switchToAssessmentsFrame() {
			mD.switchTo().frame(locateElement("cssSelector",
					"iframe.assessments__media"));
			APPLICATION_LOGS.debug("Switched to Assessments Frame");
		}
		
	// This method is used to accept the alert when close the care plan window
	public void AlertAcceptCarePlanClosetheWindow() throws InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

		try {
			mD.switchTo().alert().accept();

		} catch (UnhandledAlertException f) {
			Alert alert = mD.switchTo().alert();
			String warningText = alert.getText();
			alert.accept();
			Assert.assertEquals(warningText,
					"The Care Plan is currently in an invalid state for saving. Reason(s):Missing a domain");
		}

	}

	public void PassSingleTab() throws Exception {

		APPLICATION_LOGS.debug("Enter: Tab Action performed");

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		APPLICATION_LOGS.debug("Exit: Tab Action performed");

	}

	public void  PassSingleEnter() throws Exception {

        APPLICATION_LOGS.debug("Enter: Enter Action performed");
         
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
          
        APPLICATION_LOGS.debug("Exit: Enter Action performed");
    
}	
	/**
	 * This method is used to generate the patient id randomly
	 * @param programeName
	 * @param charLength
	 * @return
	 * @throws Exception
	 */
	public String generateAndEnterPatientId(String programeName,int charLength) throws Exception {
		String patientId= null;
		WebElement alertMsg=null;
		switch(programeName){
		case "Health Home":
			patientId="HH"+String.valueOf(charLength < 1 ? 0 : new Random()
	                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
	                + (int) Math.pow(10, charLength - 1));
			System.out.println("Patient ID:"+patientId);
			clearText("name", "Patient ID");
			enterText("name", "Patient ID", patientId);
			clickElement("xpath", "//h3[contains(text(),'Ground Up')]");
			new WebDriverWait(mD, 120).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Performing external patient search')]")));
			break;
		case "Health First":
			patientId="HF"+String.valueOf(charLength < 1 ? 0 : new Random()
	                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
	                + (int) Math.pow(10, charLength - 1));
			System.out.println("Patient ID:"+patientId);
			clearText("name", "Patient ID");
			enterText("name", "Patient ID", patientId);
			clickElement("xpath", "//h3[contains(text(),'Ground Up')]");
			new WebDriverWait(mD, 120).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Performing external patient search')]")));
			break;
		case "Care Connect":
			patientId="CC"+String.valueOf(charLength < 1 ? 0 : new Random()
	                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
	                + (int) Math.pow(10, charLength - 1));
			System.out.println("Patient ID:"+patientId);
			clearText("name", "Patient ID");
			enterText("name", "Patient ID", patientId);
			clickElement("xpath", "//h3[contains(text(),'Ground Up')]");
			new WebDriverWait(mD, 120).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Performing external patient search')]")));
			break;
		case "Childrens Health Home":
			patientId="CHH"+String.valueOf(charLength < 1 ? 0 : new Random()
	                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
	                + (int) Math.pow(10, charLength - 1));
			System.out.println("Patient ID:"+patientId);
			clearText("name", "Patient ID");
			enterText("name", "Patient ID", patientId);
			clickElement("xpath", "//h3[contains(text(),'Ground Up')]");
			new WebDriverWait(mD, 120).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Performing external patient search')]")));
			break;
		default:
			patientId="CT"+String.valueOf(charLength < 1 ? 0 : new Random()
	                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
	                + (int) Math.pow(10, charLength - 1));
			System.out.println("Patient ID:"+patientId);
			clearText("name", "Patient ID");
			enterText("name", "Patient ID", patientId);
			clickElement("xpath", "//h3[contains(text(),'Ground Up')]");
			new WebDriverWait(mD, 120).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Performing external patient search')]")));
			
		}
		Thread.sleep(3000);
		alertMsg = mD.findElement(By.cssSelector(".js-groundUp-alert .js-alert-message-content"));
		while (alertMsg.isDisplayed() && alertMsg.getText().contains("Please give a new Patient id to continue")) {
			patientId = generateAndEnterPatientId(programeName, charLength);
			System.out.println("Patient ID:" + patientId);
			clearText("name", "Patient ID");
			enterText("name", "Patient ID", patientId);
			clickElement("xpath", "//h3[contains(text(),'Ground Up')]");
			new WebDriverWait(mD, 120).until(ExpectedConditions.invisibilityOfElementLocated(
					By.xpath("//div[contains(text(),'Performing external patient search')]")));
			Thread.sleep(3000);
		}
        return  patientId;
    }
	
	/**
	 * This method verifies whether the patient created by Ground Up flow is present in Care Tool or not
	 * @return
	 * @throws Exception
	 */
	public String verifyGrounUpCreatedPatient() throws Exception {
		APPLICATION_LOGS.debug("Entry: verifyGrounUpCreatedPatient");
		clickElement("id", "nav-search-input");
		clearText("id", "nav-search-input");
		enterText("id", "nav-search-input", identifierValue);
		clickElement("id", "clMpSearchFilter");
		waitForCareToolToReload();
		Thread.sleep(3000);
		String testStatus=verifyPatientId("id", "tocCriticalElementsMRN");
		APPLICATION_LOGS.debug("Exit: verifyGrounUpCreatedPatient");
		return testStatus;
	}
	
	/**
	 * This method will wait until the Care toll application completely loads
	 * 
	 * @param maxWaitTime
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public void waitForCarePlanPage() throws InterruptedException {
		APPLICATION_LOGS.debug("Entry: waitForCarePlanPage");
		long maxWaitTime = 180;
		try {
			new WebDriverWait(mD, maxWaitTime)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Initializing the Care Plan UI')]")));
			new WebDriverWait(mD, maxWaitTime)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Loading Care Plan')]")));
			new WebDriverWait(mD, maxWaitTime).until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector(".l-care-plan__header-title")));
			Thread.sleep(3000);
			APPLICATION_LOGS.debug("Exit: waitForCarePlanPage");
		} catch (TimeoutException e) {

		}
	}
	
	/**
	 * This method will wait until the Care toll application completely loads
	 * 
	 * @param maxWaitTime
	 * @throws TimeoutException
	 * @throws InterruptedException
	 */
	public void waitForCarePlanListBoxToLoad() throws InterruptedException {
		APPLICATION_LOGS.debug("Entry: waitForCarePlanListBoxToLoad");
		long maxWaitTime = 180;
		try {
			new WebDriverWait(mD, maxWaitTime)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".k-icon.k-loading")));
			new WebDriverWait(mD, maxWaitTime).until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector(".k-icon.k-i-arrow-s")));
			Thread.sleep(2000);
			APPLICATION_LOGS.debug("Exit: waitForCarePlanListBoxToLoad");
		} catch (TimeoutException e) {

		}
	}
	
	/*
	 * This method is used to switch to Remarks frame in the care plan
	*/
	public void switchtoFrameCarePlanRemarks( ) throws NoSuchFrameException {

        APPLICATION_LOGS.debug("Entry: switchtoFrame");

        mD.switchTo().frame(locateElement("cssSelector", "div.js-careplan-remarks-container iframe[title='Editable area. Press F10 for toolbar.']"));

        APPLICATION_LOGS.debug("Exit: switchtoFrame");

        }
	/*
	 * This method is used to switch to Remarks Result frame in the care plan.
	*/
	public void switchtoFrameCarePlanRemarksResult( ) throws NoSuchFrameException {

        APPLICATION_LOGS.debug("Entry: switchtoFrame");

//        mD.switchTo().frame(locateElement("cssSelector", "div.js-careplan-remarks-container .js-care-plan-remarks-panel-list-container iframe[title='Editable area. Press F10 for toolbar.']"));

        mD.switchTo().frame(locateElement("cssSelector", "div.js-careplan-remarks-container .js-care-plan-remarks-panel-list-container iframe[title='Editable area. Press F10 for toolbar.']"));

        APPLICATION_LOGS.debug("Exit: switchtoFrame");

        }
	
	/*
	 * This method is used to get the value from hidden attribute.
	 * @param fTextOut
	*/
	 public String verifyTextHidden(String locatorType, String locatorObject, String fText)
			throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: verifyTextIgnoreCase");
			
		String fTextOut = locateElement(locatorType, locatorObject).getAttribute("value");
		
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
	 
	 /**
		 * This method will wait until the CHH Workflow Screen completely loads
		 * 
		 * @param maxWaitTime
		 * @throws TimeoutException
		 * @throws InterruptedException
		 */
		public void waitForCHHWorkflowScreen() throws InterruptedException {
			APPLICATION_LOGS.debug("Entry: waitForCHHWorkflowScreen");
			long maxWaitTime = 180;
			try {
				new WebDriverWait(mD, maxWaitTime)
						.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Initializing workflow')]")));
				new WebDriverWait(mD, maxWaitTime)
						.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Obtaining activity instance')]")));
				new WebDriverWait(mD, maxWaitTime)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Obtaining activities')]")));
				new WebDriverWait(mD, maxWaitTime)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Loading case information ')]")));
				new WebDriverWait(mD, maxWaitTime).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Loading consent data')]")));
				new WebDriverWait(mD, maxWaitTime)
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Loading consent data')]")));
				new WebDriverWait(mD, maxWaitTime).until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector(".l-chh-workflow__header-title")));
				Thread.sleep(3000);
				APPLICATION_LOGS.debug("Exit: waitForCHHWorkflowScreen");
			} catch (TimeoutException e) {

			}
		}
	
	/**
	 * This method expands the program tree in Case list	
	 * @throws InterruptedException
	 */
	public void expandProgramTree() {
		try {
			WebElement caret = new WebDriverWait(mD, 10)
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa-caret-right")));
			do {
				if (null != caret && caret.isDisplayed()) {
					caret.click();
					Thread.sleep(3000);
				}
				caret = new WebDriverWait(mD, 10)
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa-caret-right")));
			} while (null != caret && caret.isDisplayed());
		} catch (Exception e) {

		}
	}
	
	/**
	 * Verify the notification message after Save
	 * @param fText
	 * @return 
	 * @throws NoSuchElementException
	 */
	public String verifyMsgText(String fText) throws NoSuchElementException {
		APPLICATION_LOGS.debug("Entry: getText");
		String fTextOut = new WebDriverWait(mD, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.toast-message"))).getText();
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
	
	/**
	 * Save  and verify the notification message after Save
	 * @param locatorType, locatorObject, fText
	 * @return 
	 * @throws NoSuchElementException
	 * @throws InterruptedException 
	 */
	public String clickBtnAndVerifyMsgText(String locatorType, String locatorObject, String fText) throws NoSuchElementException, InterruptedException {
		APPLICATION_LOGS.debug("Entry: SaveAndVerifyMsgText");
		Thread.sleep(5000);
		WebElement locateElement = locateElement(locatorType, locatorObject);
		WebElement elementToClick = new WebDriverWait(mD, 30).until(ExpectedConditions.elementToBeClickable(locateElement));
		elementToClick.click();
		String fTextOut = new WebDriverWait(mD, 60).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.toast-message"))).getText();
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
			APPLICATION_LOGS.debug("Exit: SaveAndVerifyMsgText");
			return "Fail";
		}
	}
	
	/**
	 * Select the address type
	 * @param addressType
	 * @throws InterruptedException
	 */
	public void selectAddressType(String addressType) throws InterruptedException{
		APPLICATION_LOGS.debug("Entry: selectAddressType");
		try{
			new WebDriverWait(mD, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("AddressType_0")));
			new WebDriverWait(mD, 5).until(ExpectedConditions.elementToBeClickable(By.id("AddressType_0")));
			Select addressTypeDropDown = new Select(locateElement("id", "AddressType_0"));
			Thread.sleep(2);
			addressTypeDropDown.selectByVisibleText(addressType);
			
		}catch(WebDriverException e){
			WebElement addAddressBtn = locateElement("xpath", "//ul[@data-section-partial='addressRow']/parent::div/button");
			WebElement elementToClick = new WebDriverWait(mD, 5).until(ExpectedConditions.elementToBeClickable(addAddressBtn));
			elementToClick.click();
			Select addressTypeDropDown = new Select(locateElement("id", "AddressType_0"));
			Thread.sleep(2);
			addressTypeDropDown.selectByVisibleText(addressType);
		}
	}
	
	
	/**
	 * Enter the phone details
	 * @param phoneType
	 * @param phoneNumber
	 * @throws InterruptedException
	 */
	public void enterPhoneDetails(String phoneType, String phoneNumber) throws InterruptedException{
		APPLICATION_LOGS.debug("Entry: enterPhoneDetails");
		try{
			new WebDriverWait(mD, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("PhoneType_0")));
			new WebDriverWait(mD, 5).until(ExpectedConditions.elementToBeClickable(By.id("PhoneType_0")));
			Select phoneTypeDropDown = new Select(locateElement("id", "PhoneType_0"));
			Thread.sleep(2);
			phoneTypeDropDown.selectByVisibleText(phoneType);
			WebElement phoneNumTextBox = locateElement("id", "PhoneNumber_0");
			WebElement phoneRemarkTextBox = locateElement("id", "PhoneRemarks_0");
			phoneNumTextBox.clear();
			phoneNumTextBox.sendKeys(phoneNumber);
			phoneRemarkTextBox.clear();
			phoneRemarkTextBox.sendKeys("Phone number updated.");
		}catch(WebDriverException e){
			WebElement addPhoneBtn = locateElement("xpath", "//ul[@data-section-partial='phoneRow']/parent::div/button");
			WebElement elementToClick = new WebDriverWait(mD, 5).until(ExpectedConditions.elementToBeClickable(addPhoneBtn));
			elementToClick.click();
			Select phoneTypeDropDown = new Select(locateElement("id", "PhoneType_0"));
			Thread.sleep(2);
			phoneTypeDropDown.selectByVisibleText(phoneType);
			locateElement("id", "PhoneNumber_0").sendKeys(phoneNumber);
			locateElement("id", "PhoneRemarks_0").sendKeys("Phone number added.");
		}
	}
	
	/**
	 * Select the email type
	 * @param emailType
	 * @throws InterruptedException
	 */
	public void selectEmailType(String emailType) throws InterruptedException{
		APPLICATION_LOGS.debug("Entry: selectEmailType");
		try{
			new WebDriverWait(mD, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("EmailType_0")));
			new WebDriverWait(mD, 5).until(ExpectedConditions.elementToBeClickable(By.id("EmailType_0")));
			Select addressTypeDropDown = new Select(locateElement("id", "EmailType_0"));
			Thread.sleep(2);
			addressTypeDropDown.selectByVisibleText(emailType);
			
		}catch(WebDriverException e){
			WebElement addEmailBtn = locateElement("xpath", "//ul[@data-section-partial='emailRow']/parent::div/button");
			WebElement elementToClick = new WebDriverWait(mD, 5).until(ExpectedConditions.elementToBeClickable(addEmailBtn));
			elementToClick.click();
			Select emailTypeDropDown = new Select(locateElement("id", "EmailType_0"));
			Thread.sleep(2);
			emailTypeDropDown.selectByVisibleText(emailType);
		}
	}
}
	

