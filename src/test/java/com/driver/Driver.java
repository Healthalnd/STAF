package com.driver;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.common.BusinessActionCareTool;
import com.common.CommonActions;
import com.common.Util;

import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class driver.
 */
public class Driver extends CommonActions{
	
	public static WebDriver mD;
	/** The method. */
	static CommonActions method = new CommonActions();
	
	/** The uMethod. */
	static Util uMethod = new Util();
	
	/** The BAmethod. */
	static BusinessActionCareTool BAmethod = new BusinessActionCareTool();
	
	/** The test name. */
	private String testName;
	
	/** The file name. */
	public String fileName;

	/**
	 * MethodName: testcase()
	 * Objective: This method is used to fetch the read the datas from excelsheet during the execution.
	 * @param testname denotes the test case name
	 * @param testData denotes the test data
	 * @throws Exception Signals that the FileNotFoundException,IOException has occured.
	 */
	@Test(dataProvider="data")
	public void testcase(String testname,HashMap<String,String> testData) throws Exception{
		APPLICATION_LOGS.debug("Entry: testcase");
		testName=testname;
		
		
		Reporter.log(testName.toUpperCase());
		APPLICATION_LOGS.debug("*******Start TestCaseName*******" + testName.toUpperCase());
		//System.out.println("vSheetname" + testData.get("vSheetname")) ;
		String stepsheetpath = foldname + "/" + testData.get("stepsheet") + ".xls";
		method.FolderCreate(testName);
		if((testName.toUpperCase()).contains("_TD"))
		{
			method.xlR_TD((String) testData.get("sheet"),(String) testData.get("vTCID"));
			int tdi = Integer.parseInt((String) testData.get("tdi"));
			method.xlR_StepSheet(stepsheetpath);
			for (int z = 1; z < xTStep_rows ; z++) 
			{
				vTSID = xStepSheet[z][0];
				if (vTCID.equals(vTSID))
				{
					String TestDataVal = xStepSheet[z][7];
					String ff = method.firstchar(TestDataVal);
					if (ff.equals("&"))
					{
						int colid = method.converttoInt(xStepSheet[z][8]);
						String dd = xTD[tdi][colid];
						String qq = method.firstchar(dd);
						if (qq.equals("!"))
						{
							String[] gvar = dd.split("!");
							String data = method.getTestData(gvar[1]);
							method.writetoXLCell(stepsheetpath, data , z, 7);
						}
						else
						{
							method.writetoXLCell(stepsheetpath, dd , z, 7);
						}
					}//if ff loop ends
					else 
					{	if(ff.equals("#"))
					{
						String[] gvar = TestDataVal.split("#");
						String data = method.getEnvProp(gvar[1]);
						if(null!= System.getProperty("env")){
							data= getEnvProp(System.getProperty("env"));
						}
						method.writetoXLCell(stepsheetpath, data, z, 7);
						//System.out.println("Environment logic");
					}
					else if (ff.equals("!"))
					{
						String[] gvar = TestDataVal.split("!");
						String data = method.getTestData(gvar[1]);
						method.writetoXLCell(stepsheetpath, data , z, 7);
					}
					}	
				}
			}
			method.xlR_StepSheet(stepsheetpath);
			for (k = 1; k < xTStep_rows; k++)
			{
				vTSID = xStepSheet[k][0];
				if (vTCID.equals(vTSID))
				{
					ts_result = "Pass";
					ts_error = "No Error";
					vKeyword = xStepSheet[k][4];
					vObject = method.getObjectDef(xStepSheet[k][5],xStepSheet[k][1]) ;//Multiple OR
					vLocatorType = xStepSheet[k][6];
					vTestData = xStepSheet[k][7];
					try
					{
						ts_result = keyword_executor();
						if (!vKeyword.equals("waitTime")){
							method.takeScreenShot_TestNG(tdi,(String)testData.get("vTSuiteID"),vTSID, testName);
						}
						method.writetoXLCell(stepsheetpath, ts_result, k, 9);
						method.writetoXLCell(stepsheetpath, ts_error, k, 10);

						if(ts_result == "Fail")
						{
							throw new Exception();
						}
					}
					catch(Exception e)
					{
						ts_result = "Fail";
						method.Log(String.valueOf(testData.get("vTSuiteID")),ts_result);
						System.out.println(e.getLocalizedMessage());
						if(ts_result == "Fail")
						{
							method.writetoXLCell(stepsheetpath, ts_result, k, 9);
							String arr = (e.getLocalizedMessage());
							method.writetoXLCell(stepsheetpath, arr, k, 10);
							if(!(mD==null))
							{
								method.closeBrowser();
							}
							//    break;
							Assert.fail("TJ Failed on Stepid[" + k + "]" + " : Action[ " +vKeyword + "]"  + ",Element[" + vObject + "],Testdata(if any)[" + vTestData + "]"  + arr );
						}
					}//Catch loop ends
				}//if (vTCID.equals(vTSID)) loop ends
			}
		}    
		else 
		{
			//Else, in case DataPool is set to 'No' for execution
			method.xlR_StepSheet(stepsheetpath);
			for (l = 1; l < xTStep_rows; l++){
				vTSID = xStepSheet[l][0];
				if (vTCID.equals(vTSID)){
					ts_result = "Pass";
					ts_error = "No Error";
					vKeyword = xStepSheet[l][4];
					vObject = method.getObjectDef(xStepSheet[l][5],xStepSheet[l][1]) ; //Multiple OR
					vLocatorType = xStepSheet[l][6];
					String TestDataVal = xStepSheet[l][7];
					String ff = method.firstchar(TestDataVal);
					if (ff.equals("#")){
						String[] gvar = TestDataVal.split("#");
						String data = method.getEnvProp(gvar[1]);
						if(null!= System.getProperty("env")){
							data= getEnvProp(System.getProperty("env"));
						}
						method.writetoXLCell(stepsheetpath, data, l, 7);
					}//if ff loop ends
					else {
						if (ff.equals("!")){
							String[] gvar = TestDataVal.split("!");
							String data = method.getTestData(gvar[1]);
							method.writetoXLCell(stepsheetpath, data, l, 7);
						}
						else
						{
							vTestData = xStepSheet[l][7];
						}
					}
					try{
						ts_result = keyword_executor();
						if (!vKeyword.equals("waitTime"))
							method.takeScreenShot((String)testData.get("vTSuiteID"),vTSID, testName);
						method.writetoXLCell(stepsheetpath, ts_result, l, 9);
						method.writetoXLCell(stepsheetpath, ts_error, l, 10);
						if(ts_result == "Fail"){
							throw new Exception();
						}
					}catch(Exception e){
						ts_result = "Fail";
						method.Log(String.valueOf(testData.get("vTSuiteID")),ts_result);
						if(ts_result =="Fail"){
							method.writetoXLCell(stepsheetpath, ts_result, l, 9);
							String arr = (e.getLocalizedMessage());
							method.writetoXLCell(stepsheetpath, arr, l, 10);
							if(!(mD==null)){
								method.closeBrowser();
							}
							//    break;

							// method.Log(String.valueOf(testData.get("vTSuiteID")),ts_result);
							Assert.fail("TJ Failed on Stepid[" + l + "]" + " : Action[ " +vKeyword + "]"  + ",Element[" + vObject + "]" + "Testdata(if any)[" + vTestData + "]"  + arr );
						}
					}//Catch loop ends
				}//if loop ends
			}//for l loop ends
		}
		//  APPLICATION_LOGS.debug("*******End*******" + testName.toUpperCase() +": " +"Pass");
		
		method.Log(String.valueOf(testData.get("vTSuiteID")),ts_result);
		APPLICATION_LOGS.debug("Exit: testcase");
	}

@AfterSuite
public static void browserClose()
{
	CommonActions.mD.close();
}

	/**
	 * MethodName: data()
	 * Objective: This method is used to read the testsuite datas.
	 * @return the object[][]
	 * @throws Exception the exception
	 */
	@DataProvider
	public static Object[][] data() throws Exception {
		APPLICATION_LOGS.debug("Entry: Object[][] data");
		uMethod.ReadTestSuite();
		//uMethod.ReadTestSuiteHHSmoke();
		method.FolderCreate();
		Object[][] data=null;
		ArrayList<HashMap<String, String>> TestCaseList = new ArrayList<HashMap<String, String>>();
		//System.out.println("--------------------------------------------");
		method.CreateSheet(TestSuiteResultFile, xTSuite, xTSuite_rows, xTSuite_cols);
		for (int i = 1; i < xTSuite_rows; i++){
			if (xTSuite[i][2].equals("Y")){
				vTSuiteID = xTSuite[i][1];
				String vSheetname = xTSuite[i][1];
				String sheet = TestRepoFolder + vSheetname + ".xls";
				//System.out.println("Sheet Name is "+ sheet);
				method.xlR_TC(sheet);
				method.xlR_TS(sheet);
				method.CreateSheet(vSheetname+"_"+"TestCasesResults", xTC, xTC_rows, xTC_cols);
				String testsuiteresultspath = foldname + "/" + TestSuiteResultFile + ".xls";
				String TestCasesResultpath = foldname + "/" + vSheetname+"_"+"TestCasesResults"+".xls";
				// Go to each Test Case row
				for (int tci = 1; tci < xTC_rows; tci++) {
					//tc_total++;
					if(xTC[tci][3].equals("Y")){ // Only if TC is set to Y
						//                tc_executed++;
						vTCID = xTC[tci][0]; // Get TCID
						//               tc_result = "Pass";
						if (xTC[tci][4].equals("Y")){     // if DataPool is 'Y'
							method.xlR_TD(sheet, vTCID);
							for (int tdi = 1; tdi < xTD_rows; tdi++){
								vTDID = xTD[tdi][0]; //Get TDID
								if(xTD[tdi][1].equals("Y")){        // If TDID is "Y"
									stepsheet = vSheetname+"_"+vTCID + "_" + vTDID;
									method.CreateSheet(stepsheet, xTS, xTS_rows, xTS_cols);
									stepsheetpath = foldname + "/" + stepsheet + ".xls";
									HashMap<String,String> testcase = new HashMap() ;
									testcase.put("vTSuiteID", vTSuiteID);
									testcase.put("vSheetname", vSheetname);
									testcase.put("vTCID", vTCID);
									testcase.put("vTDID", vTDID);
									testcase.put("tdi", String.valueOf(tdi));
									testcase.put("sheet", sheet);
									testcase.put("stepsheet", stepsheet);
									TestCaseList.add(testcase);
								}
								else {
								}
							}
						}
						else {         //Else, in case DataPool is set to 'No' for execution
							stepsheet = vTCID+"_"+vSheetname;
							stepsheet = vTCID+"_"+vSheetname;
							method.CreateSheet(stepsheet, xTS, xTS_rows, xTS_cols);
							String stepsheetpath = foldname + "/" + stepsheet + ".xls";
							HashMap<String,String> testcase = new HashMap() ;
							testcase.put("vTSuiteID", vTSuiteID);
							testcase.put("vSheetname", vSheetname);
							testcase.put("vTCID", vTCID);
							testcase.put("vTDID", vTDID);
							testcase.put("stepsheet", stepsheet);
							testcase.put("sheet", sheet);
							TestCaseList.add(testcase);
						}//else loop ends
					}
					else {                //Else, in case TestCase is set to 'No' for execution
					}
				}
			}
			else {                        //Else, in case TestSheet in TestSuite is set to 'No' for execution
			}
		}
		int sizeofarraylist = TestCaseList.size() ;
		data= new Object[sizeofarraylist][2];    //The column will be 1 only as we will put data in hash table.
		for(int i=0; i<data.length;i++){
			data[i][0] = (TestCaseList.get(i)).get("stepsheet") ;
			data[i][1] = TestCaseList.get(i) ;
		}
		//return Arrays.asList(data);
		APPLICATION_LOGS.debug("Exit: Object[][] data");
		return data;
	
	}



	/*public driver(String testName, HashMap testData) {
		this.testName= testName;
		this.testData= testData;
	}*/


	/**
	 * MethodName: tearDown()
	 * Objective : This method will be invoked once the execution gets completed.
	 * 
	 */
	@AfterClass
	public static void tearDown() {
		System.out.println("All done");
		APPLICATION_LOGS.debug("All Done"); 
	}
	
	/**
	 * MethodName : keyword_executor()
	 * Objective : Based on the keywords, the java methods will be invoked.
	 * @return the string
	 * @throws Exception signals that NoSuchKeyword present.
	 */
	public static String keyword_executor() throws Exception {
		APPLICATION_LOGS.debug("Entry: keyword_executor");
		Val = "Pass";
		switch(vKeyword){
		case "navigateTo":
			method.navigateTo(vTestData);
			break;
		case "pageRefresh":
			method.pageRefresh();
			break;
		case "enterText":
			method.enterText(vLocatorType, vObject, vTestData);
			break;
		case "clickElement":
			method.clickElement(vLocatorType, vObject);
			break;
		case "verifyAllDropDownvalues":
			method.verifyAllDropDownvalues(vLocatorType, vObject, vTestData);
			break;
		case "verifyAllDropDownvalueByText":
			method.verifyAllDropDownvalueByText(vLocatorType, vObject, vTestData);
			break;
		case "clickElementbyId":
			method.clickElementbyId( vObject);
			break;
		case "successMessage":
			BAmethod.successMessage(vTestData);
			break;
		case "highlightElement":
			method.highlightElement(vLocatorType, vObject);
			break;
		case "clearText":
			method.clearText(vLocatorType, vObject);
			break;
		case "clickLink":
			method.clickLink(vLocatorType, vObject);
			break;
		case "getText":
			method.getText(vLocatorType, vObject, vTestData);
			break;
		case "VerifyAlerttextCareplan":
			BAmethod.VerifyAlerttextCareplan();
			break;
		case "AlertAcceptCarePlanClosetheWindow":
			BAmethod.AlertAcceptCarePlanClosetheWindow();
			break;
		case "scrollDown500":
			BAmethod.scrollDown500();
			break;
		case "PassSingleTab":
			BAmethod.PassSingleTab();
			break;
		case "PassSingleEnter":
			BAmethod.PassSingleEnter();
			break;
		case "PassSingleDownArrow":
			BAmethod.PassSingleDownArrow();
			break;	
		case "selectDropDown":
			BAmethod.selectDropDown(vLocatorType, vObject, vTestData);
			break;		
	 	
		case "checkPrePopulatedText":
			Val = method.checkPrePopulatedText(vLocatorType, vObject, vTestData);
			break;
		case "clickCVDemoIcon":
			BAmethod.clickCVDemoIcon();
			break;
		case "verifyText":
			Val = method.verifyText(vLocatorType, vObject, vTestData);
			break;
		case "verifyTextIgnoreCase":
			Val = method.verifyTextIgnoreCase(vLocatorType, vObject, vTestData);
			break;
		case "selectCheckbox":
			method.selectCheckbox(vLocatorType, vObject);
			break;
		case "unselectCheckbox":
			method.unselectCheckbox(vLocatorType, vObject);
			break;
		case "selectRadiobutton":
			method.selectRadiobutton(vLocatorType, vObject);
			break;
		case "getAttribute":
			method.getAttribute(vLocatorType, vObject, vTestData);
			break;
		case "verifyAttribute":
			splitData = method.split1(vTestData);
			Val = method.verifyAttribute(vLocatorType, vObject, splitData[0], splitData[1]);
			break;
		case "closeBrowser":
			method.closeBrowser();
			break;
		case "validateRunTimeText":
			BAmethod.validateRunTimeText(vLocatorType, vObject, vTestData);
			break;
		case "isElementDisabled":
			method.isElementDisabled(vLocatorType, vObject);
			break;
		case "waitTime":
			int time = method.converttoInt(vTestData);
			method.waitTime(time);
			break;
		case "verifyElementPresent":
			Val = method.verifyElementPresent(vLocatorType, vObject);
			break;
		case "verifyElementNotPresent":
			Val = method.verifyElementNotPresent(vLocatorType, vObject);
			break;
		case "verifyLinkPresent":
			Val = method.verifyLinkPresent(vLocatorType,vObject);
			break;
		case "selectDate":
			splitData = method.split(vTestData);
			int count = method.converttoInt(splitData[0]);
			method.selectDate( vObject, count, splitData[1]);
			break;
		case "acceptPopup":
			method.acceptPopup( vTestData);
			break;
		case "dismissPopup":
			method.dismissPopup( vTestData);
			break;
		case "scrollDownCreateContact":
			BAmethod.scrollDownCreateContact();
			break;
		case "scrollDownProviderContact":
			BAmethod.scrollDownProviderContact();
			break;
		case "scrollDownGroundsUp":
			BAmethod.scrollDownGroundsUp();
			break;
		case "SearchPatientMedicaid":
			BAmethod.SearchPatientMedicaid(vTestData);
			break;
		case "scrollDown":
			BAmethod.scrollDown();
			break;
		case "scrollUp":
			BAmethod.scrollUp();
			break;
		case "scrollToObject":
			BAmethod.scrollToObject(vLocatorType, vObject);
			break;
		case "scrollDownMedication":
			BAmethod.scrollDownMedication(vTestData);
			break;
		case "scrollDownClinicalMedication":
			BAmethod.scrollDownClinicalMedication();
			break;
		case "transitionofCare":
			BAmethod.transitionofCare();
			break;
		case "selectSubmenu":
			method.selectSubmenu( vObject, vTestData);
			break;
		case "SearchPatientMRN":
			BAmethod.SearchPatientMRN( vTestData);
			break;
		case "NavigateAddAppointment":
			BAmethod.NavigateAddAppointment();
			break;
		case "containsText":
			Val= method.containsText(vLocatorType, vObject, vTestData);
			break;
		case "verifyBreadcrumb":
			Val = BAmethod.verifyBreadcrumb(vTestData);
			break;
		case "treenodecount":
			Val = BAmethod.treenodecount(vTestData);
			break;
		case "SearchPatientLastName":
			BAmethod.SearchPatientLastName(vTestData);
			break;
		case "enterAssessPatientDate":
			BAmethod.enterAssessPatientDate(vTestData);
			break;
		case "createNote":
			BAmethod.createNote(vTestData);
			break;
		case "scrollDownToObject":
			BAmethod.scrollDownToObject(vLocatorType, vObject);
			break;
		case "switchtoFrame":
			method.switchtoFrame();
			break;
		case "getwindowHandle":
			method.getwindowHandle(vTestData);
			break;
		case "getTodaysDate":
			method.getTodaysDate(vTestData);
			break;
		case "pageContainsText":
			method.pageContainsText( vTestData);
			break;
		case "pageContainsMedicaid":
			method.pageContainsMedicaid();
			break;
		case "verifyCVDataFrame":
			BAmethod.verifyCVDataFrame();
			break;
		case "getTable":
			method.getTable(vLocatorType, vObject);
			break;
		case "pageNotContainsText":
			Val =method.pageNotContainsText(vLocatorType, vObject,vTestData);
			break;
		case "mouseHover":
			method.mouseHover(vLocatorType, vObject);
			break;
		case "maximise":
			method.maximise();
			break;
		case "SearchPatientFirstName":
			BAmethod.SearchPatientFirstName( vTestData);
			break;
		case "scrollviewtrue":
			BAmethod.scrollviewtrue(vLocatorType, vObject);
			break;
		case "verifyChangeStatusSuccessMessage":
			BAmethod.verifyChangeStatusSuccessMessage(vTestData);
			break;
		case "mouseHoverForCINRadioButton":
			BAmethod.mouseHoverForCINRadioButton(vObject);
			break;
		case "retrieveDataHH":
			BAmethod.retrieveDataHH();
			break;
		case "retrieveDataPBCM":
			BAmethod.retrieveDataPBCM();
			break;
		case "scrollDownMedicationDraft":
			BAmethod.scrollDownMedicationDraft(vTestData);
			break;
		case "enrollmentDate":
			BAmethod.enrollmentDate(vLocatorType, vObject, vTestData);
			break;
		case "mandatoryFieldCheck":
			Val = BAmethod.mandatoryFieldCheck(vLocatorType,vObject);
			break;
		case "printPDF":
			BAmethod.printPDF();
			break;
		case "dateOfBirthMatch":
			method.getAlertText();
			break;
		case "getAlertText":
			BAmethod.dateOfBirthMatch();
			break;
		case "clickElementbyDataValue":
			BAmethod.clickElementbyDataValue(vTestData);
			break;
		case "clickElementbyDataValuePL":
			BAmethod.clickElementbyDataValuePL(vTestData);
			break;	
		case "FactAssessmentAlertAccept1":
			BAmethod.FactAssessmentAlertAccept1();
			break;
		case "AlertAcceptCarePlanSave":
			BAmethod.AlertAcceptCarePlanSave();
			break;
		case "AlertAcceptCarePlanSaveWithoutDomain":
			BAmethod.AlertAcceptCarePlanSaveWithoutDomain();
			break;
		case "GetTextCarePlan":
			BAmethod.GetTextCarePlan(vLocatorType, vObject,vTestData);
			break;	
		case "CompareWithTodaysDate":
			BAmethod.CompareWithTodaysDate(vLocatorType, vObject);
			break;
		case "SwitchToFramebyID":
			int ss = method.converttoInt(vTestData);
			BAmethod.SwitchToFramebyID(ss);
			break;
		case "CarePlanlistOrder":
			BAmethod.CarePlanlistOrder();
			break;
		case "waitForCareToolToReload":
			BAmethod.waitForCareToolToReload();
			break;
		case "retrieveDataHHFromDB":
			BAmethod.retrieveDataHHFromDB(vTestData);
			break;
		case "searchPatient":
			BAmethod.searchPatient(vTestData);
			break;
		case "searchPatientUsingDatabase":
			BAmethod.searchPatientUsingDatabase(vTestData);
			break;
		case "getColumnValue":
			splitData = method.split1(vTestData);
			String query = splitData[0];
			String columnName= splitData[1];
			BAmethod.getColumnValue(query, columnName);
			break;
		case "verifyDatabaseColumnValue":
			splitData = method.split1(vTestData);
			query = splitData[0];
			String[] columnNames= splitData[1].trim().split(",");
			String[] expectedColumnValues= splitData[2].trim().split(",");
			Map<String, String> map = new HashMap<String, String>();
			if(columnNames.length==expectedColumnValues.length){
				for(int i=0; i<columnNames.length; i++){
					map.put(columnNames[i], expectedColumnValues[i]);
				}
			}else{
				System.out.println("Error in entering test data for column names and expected values.");
			}
			Val=BAmethod.verifyDatabaseColumnValue(query,map);
			break;
		case "verifyPatientId":
			Val=BAmethod.verifyPatientId(vLocatorType, vObject);
			break;
		case "verifyActionsTabIsActive":
			Val=BAmethod.verifyActionsTabIsActive(vLocatorType, vObject);
			break;
		case "switchToOrigin":
			method.switchToOrigin();
			break;
		case "switchToActiveFrame":
			BAmethod.switchToActiveFrame();
			break;
		case "generateAndEnterPatientId":
			splitData = method.split1(vTestData);
			String programName = splitData[0];
			int length= Integer.parseInt(splitData[1]);
			identifierValue=BAmethod.generateAndEnterPatientId(programName, length);
			break;
		case "verifyGrounUpCreatedPatient":
			Val=BAmethod.verifyGrounUpCreatedPatient();
			break;
		case "waitForCarePlanPage":
			BAmethod.waitForCarePlanPage();
			break;
		case "waitForCHHWorkflowScreen":
			BAmethod.waitForCHHWorkflowScreen();
			break;
		case "waitForCarePlanListBoxToLoad":
			BAmethod.waitForCarePlanListBoxToLoad();
			break;
		case "verifyAlertTextAndAccept":
			Val=method.verifyAlertTextAndAccept(vTestData);
			break;
		case "switchtoFrameCarePlanRemarks":
			BAmethod.switchtoFrameCarePlanRemarks();
			break;
		case "switchtoFrameCarePlanRemarksResult":
			BAmethod.switchtoFrameCarePlanRemarksResult();
			break;
		case "verifyTextHidden":
			Val=BAmethod.verifyTextHidden(vLocatorType, vObject, vTestData);
			break;
		case "switchToAssessmentsFrame":
			BAmethod.switchToAssessmentsFrame();
			break;
		case "expandProgramTree":
			BAmethod.expandProgramTree();
			break;
		case "verifyMsgText":
			Val=BAmethod.verifyMsgText(vTestData);
			break;
		case "clickBtnAndVerifyMsgText":
			Val=BAmethod.clickBtnAndVerifyMsgText(vLocatorType, vObject,vTestData);
			break;
		case "selectAddressType":
			BAmethod.selectAddressType(vTestData);
			break;
		case "selectEmailType":
			BAmethod.selectEmailType(vTestData);
			break;
		case "enterPhoneDetails":
			splitData = method.split1(vTestData);
			String phoneType = splitData[0];
			String phoneNumber= splitData[1];
			BAmethod.enterPhoneDetails(phoneType, phoneNumber);
			break;
		default:
			System.out.println("Keyword not found");
			APPLICATION_LOGS.debug("Keyword not found");
			Val = "Fail";		
		}
		return Val;
	}
}