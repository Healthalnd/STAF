package com.common;

// TODO: Auto-generated Javadoc
/**
 * The Class util.
 */
public class Util extends CommonActions {

	/** The Action method. */
	static CommonActions ActionMethod = new CommonActions();

	/**
	 * MethodName :ReadTestSuite() Objective : This method is used to read the
	 * testsuite
	 *
	 * @throws Exception
	 *             Signals that the FileNotFoundException,IOException has
	 *             occured.
	 */
	public void ReadTestSuite() throws Exception {

		if (System.getProperty("suite") == null) {
			TestSuiteResultFile = "TestSuiteResults";
			ActionMethod.xlR_TSuite(TestSuitePath);
		} else {
			String suite = System.getProperty("suite");
			switch (suite) {

			case "REG":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteRegression.xls";
				TestSuiteResultFile = "TestSuiteRegressionResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "FUNC":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteFunc.xls";
				TestSuiteResultFile = "TestSuiteFuncResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// Clinical Viewer Smoke in PROD
			case "CVPRODSmk":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteCVSmkPROD.xls";
				TestSuiteResultFile = "TestSuiteFuncResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// Clinical Viewer Smoke in iDEV
			case "CViDEVSmk":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteCVSmkIDev.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// CareTool HealthFirst Smoke
			case "CTSmkHF":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteCTSmkHF.xls";
				TestSuiteResultFile = "TestSuiteSmokeHFResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
				
			// CareTool Care Connect Smoke
			case "CTSmkCC":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteCTSmkCC.xls";
				TestSuiteResultFile = "TestSuiteSmokeCCResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// CareTool HealthFirst Regression
			case "CTRegHF":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteRegressionHealthFirst.xls";
				TestSuiteResultFile = "TestSuiteRegresionHFResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// CareTool HealthHome Smoke
			case "CTSmkHH":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteCTSmkHH.xls";
				TestSuiteResultFile = "TestSuiteSmokeHHResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
			
			// CareTool STAR Smoke
			case "CTSmkSTAR":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteCTSmkSTAR.xls";
				TestSuiteResultFile = "TestSuiteSmokeSTARResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
			
			// CareTool Regression QA env
			case "CTRegQA":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteRegressionCareTool.xls";
				TestSuiteResultFile = "TestSuiteSmokeRegressionCTResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
			
			// CareTool Regression BugFix env
			case "CTRegBugFix":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteRegressionCareTool_BugFix.xls";
				TestSuiteResultFile = "TestSuiteSmokeRegressionCT_BugFixResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
			
			// CareTool Regression PREPROD env
		   case "CTRegPREPROD":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteRegressionCareTool_PREPROD.xls";
			TestSuiteResultFile = "TestSuiteSmokeRegressionCT_PREPRODResults";
			ActionMethod.xlR_TSuite(TestSuitePath);
			break;
			
			// CareTool Regression Training env
		   case "CTRegTraining":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteRegressionCareTool_Training.xls";
			TestSuiteResultFile = "TestSuiteSmokeRegressionCT_TrainingResults";
			ActionMethod.xlR_TSuite(TestSuitePath);
			break;
			// CareTool Regression UAT env
		   case "CTRegUAT":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteRegressionCareTool_UAT.xls";
			TestSuiteResultFile = "TestSuiteSmokeRegressionCT_UATResults";
			ActionMethod.xlR_TSuite(TestSuitePath);
			break;
			
			// CareTool Regression on IE 11 browser
			case "CTRegQAIE":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteRegressionCareTool_IE.xls";
				TestSuiteResultFile = "TestSuiteSmokeRegressionCT_IEResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
			// CareTool Smoke
				
			case "CTSmkCT":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteSmokeCareTool.xls";
				TestSuiteResultFile = "TestSuiteSmokeCTResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// CareTool Test Data Generator

			case "CTTestDataGen":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteTestDataGenCareTool.xls";
				TestSuiteResultFile = "TestSuiteTestDataGenCTResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
				
			// CareTool HealthHome Regression
			case "CTRegHH":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteRegressionHealthHome.xls";
				TestSuiteResultFile = "TestSuiteRegressionHHResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
				
			// CareTool HHAR Regression
			case "CTRegHHAR":
					TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteRegressionHHAR.xls";
					TestSuiteResultFile = "TestSuiteRegressionHHARResults";
					ActionMethod.xlR_TSuite(TestSuitePath);
					break;
				
			// CareTool CareConnect Regression
			case "CTRegCC":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteRegressionCareConnect.xls";
				TestSuiteResultFile = "TestSuiteRegressionCCResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
			
			// CareTool CCM Regression
			case "CTRegCCM":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteRegressionCCM.xls";
				TestSuiteResultFile = "TestSuiteRegressionCCMResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// CareTool MSU Regression
			case "CTRegMSU":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteRegressionMSU.xls";
				TestSuiteResultFile = "TestSuiteRegressionMSUResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
			
			// CareTool STAR Regression
			case "CTRegSTAR":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteRegressionSTAR.xls";
				TestSuiteResultFile = "TestSuiteRegressionSTARResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;	
			
				// CareTool Care Plan Regression
			case "CTRegCP":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteRegressionCarePlan.xls";
				TestSuiteResultFile = "TestSuiteRegressionCarePlanResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// CareTool BPCI Smoke
			case "CTSmkBPCI":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteCTSmkBPCI.xls";
				TestSuiteResultFile = "TestSuiteSmokeBPCIResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// CareTool BPCI Regression
			case "CTRegBPCI":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteCTRegBPCI.xls";
				TestSuiteResultFile = "TestSuiteRegressionBPCIResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// Functional HealthHome
			case "CTFuncHH_ALL":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncHH_ALL.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncHH_CL":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncHH_CL.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncHH_CP":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncHH_CP.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncHH_CT":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncHH_CT.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncHH_CST":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncHH_CST.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncHH_PC":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncHH_PC.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncHH_CIN":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncHH_CIN.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncHH_ON":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncHH_ON.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			// Functional BPCI
			case "CTFuncBPCI_ALL":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_ALL.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_AF":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_AF.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_Stroke":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_Stroke.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_SB":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_SB.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_CF":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_CF.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_CV":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_CV.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_DAN":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_DAN.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_TC":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_TC.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_CIN":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_CIN.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_ML":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_ML.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_CN":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_CN.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_ACP":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_ACP.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_ACC":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_ACC.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_CS":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_CS.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncBPCI_CL":
				TestSuitePath = System.getProperty("user.dir")
						+ "/src/test/java/com/testsuites/TestSuiteCTFuncBPCI_CL.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncSneha":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteSneha.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncSuganya":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteSuganya.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncTarun":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteTarun.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;

			case "CTFuncJPost":
				TestSuitePath = System.getProperty("user.dir") + "/src/test/java/com/testsuites/TestSuiteJPost.xls";
				TestSuiteResultFile = "TestSuiteSmokeResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
				
			default:
				TestSuiteResultFile = "TestSuiteResults";
				ActionMethod.xlR_TSuite(TestSuitePath);
				break;
			}
		}
	}
}
