package com.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomReporter_Detailed.
 */
public class CustomReporter_Detailed implements IReporter {

	/** The out. */
	// mvn clean compile test "-Dselenide.screenshots=false"
	// "-Dselenide.reports=Screenshots" "-Dcustomreport=Y"
	private PrintWriter out;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IReporter#generateReport(java.util.List, java.util.List,
	 * java.lang.String) MethodName : generateReport() Objective : This method
	 * is used to generate the report. throws IOException.
	 */
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		new File(outputDirectory).mkdirs();
		try {
			out = new PrintWriter(
					new BufferedWriter(new FileWriter(new File(outputDirectory, "custom-report_detailed.html"))));
		} catch (IOException e) {
			System.out.println("Error in creating writer: " + e);
		}

		int failedCount = 0;
		int passedCount = 0;
		int skippedCount = 0;

		long totaltimems = 0;
		List<Date> starttimedates = new ArrayList();
		String starttime = null;
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (String testName : suiteResults.keySet()) {
				ISuiteResult suiteResult = suiteResults.get(testName);
				ITestContext testContext = suiteResult.getTestContext();
				failedCount = testContext.getFailedTests().size();

				IResultMap failedResult = testContext.getFailedTests();
				Set<ITestResult> testsFailed = failedResult.getAllResults();
				for (ITestResult testResult : testsFailed) {
					// print(" " + testResult.getName());
					// print(" " + testResult.getThrowable());
					long timeTaken = testResult.getEndMillis() - testResult.getStartMillis();

					starttimedates.add(new Date(testResult.getStartMillis()));
					totaltimems = totaltimems + timeTaken;

				}
				IResultMap passResult = testContext.getPassedTests();
				Set<ITestResult> testsPassed = passResult.getAllResults();
				// print(" Passed>" + testsPassed.size());

				passedCount = testsPassed.size();
				for (ITestResult testResult : testsPassed) {
					// print(" " + testResult.getName() + ">took " +
					// (testResult.getEndMillis() - testResult
					// .getStartMillis()) + "ms");
					long timeTaken = testResult.getEndMillis() - testResult.getStartMillis();
					totaltimems = totaltimems + timeTaken;
					starttimedates.add(new Date(testResult.getStartMillis()));

				}
				IResultMap skippedResult = testContext.getSkippedTests();
				Set<ITestResult> testsSkipped = skippedResult.getAllResults();
				skippedCount = testsSkipped.size();
				Collections.sort(starttimedates);
				starttime = starttimedates.get(0).toString();

			}
		}

		startHtml(failedCount, passedCount, skippedCount, totaltimems, starttime);
		// print("Suites run: " + suites.size());

		for (ISuite suite : suites) {
			// print("Suite>" + suite.getName());

			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (String testName : suiteResults.keySet()) {
				// print(" Test>" + testName);
				ISuiteResult suiteResult = suiteResults.get(testName);
				ITestContext testContext = suiteResult.getTestContext();

				ITestResult tr = Reporter.getCurrentTestResult();

				IResultMap failedResult = testContext.getFailedTests();

				Set<ITestResult> testsFailed = new TreeSet(failedResult.getAllResults());

				print("<table width=100% bgcolor=#F7F2E0 border=1 cellpadding=3 cellspacing=0>");

				print("<tr><td bgcolor=#FF0000 width = 100%>FAILED TESTACASES</td></tr>");
				print("<table width=100% bgcolor=#FFFFFF border=1 cellpadding=3 cellspacing=0>");
				print("<tr><td bgcolor=#FFCC66 width = 60%>" + "Name" + "</td><td bgcolor=#FFCC66 width = 20%>"
						+ "Status" + "</td><td bgcolor=#FFCC66 width = 20%>" + "Time Taken" + "</td></tr>");

				for (ITestResult testResult : testsFailed) {

					print("<table width=100% bgcolor=#FFFFFF border=1 cellpadding=3 cellspacing=0>");
					List<String> msgs = Reporter.getOutput(testResult);
					long timeTaken = testResult.getEndMillis() - testResult.getStartMillis();
					String messageTotal = "";
					for (int msgcount = 0; msgcount < msgs.size(); msgcount++) {

						if (msgcount == 1) {
							messageTotal = messageTotal + "---------------------" + "<br />";
							messageTotal = messageTotal + "TEST STEPS" + "<br />";
							messageTotal = messageTotal + "---------------------" + "<br />";
						}

						messageTotal = messageTotal + msgs.get(msgcount) + "<br />";
					}
					// messageTotal= messageTotal + "<a href=\""+ "Screenshot
					// Link Path" + ".html\">Screenshot Link</a>" ;
					// messageTotal= messageTotal + "Harpreat" ;

					Date date = new Date();
					SimpleDateFormat ft = new SimpleDateFormat("Eyyyy.MM.ddhhmmssazzz");
					String fileName = ft.format(date);

					// screenshot(fileName);

					// String actualFileName = System.getProperty("user.dir") +
					// "\\build\\reports\\tests\\" + fileName + ".png" ;
					String actualFileName;
					if (System.getProperty("selenide.reports") != null) {

						actualFileName = System.getProperty("user.dir") + "\\Screenshots\\" + fileName + ".png";
						// System.out.println("***************" +
						// actualFileName);

					} else {
						actualFileName = System.getProperty("user.dir") + "\\build\\reports\\tests\\" + fileName
								+ ".png";
						// System.out.println("********" + actualFileName);
					}

					// String actualFileName = strReportFilepath + "\\" +
					// fileName + ".png" ;

					// messageTotal= messageTotal+ "<font color=\"red\">" +
					// testResult.getThrowable() + "</font>" + "<br />"+ "<a
					// style=\"color: #F7F2E0\" href=" + actualFileName + ">
					// Screenshot</a>" ;

					// messageTotal= messageTotal+ "<font style=\"color:
					// rgb(255,0,0)\">" + testResult.getThrowable() + "</font>"
					// ;

					messageTotal = messageTotal + "<font style=\"color: rgb(255,0,0)\">" + "</font>";

					// for(int msgcount=0;msgcount<msgs.size();msgcount++){

					print("<tr><td bgcolor=#F7F2E0 width = 60%><font face=\"Calibri\"><i>" + messageTotal
							+ "</i></font></td><td bgcolor=#F7F2E0 width = 20%>" + "FAILED"
							+ "</td><td bgcolor=#F7F2E0 width = 20%>" + (timeTaken / 1000) + " seconds "
							+ "</td></tr>");
					// print("<tr><td bgcolor=#F7F2E0 width = 60%><font
					// face=\"Calibri\"><i>" + messageTotal +
					// "</i></font></td><td bgcolor=#F7F2E0 width = 20%>" +
					// "FAILED" + "</td><td bgcolor=#F7F2E0 width = 20%>" +
					// (timeTaken/60000) + " minutes " + "</td></tr>");
					// print("<tr><td bgcolor=#F7F2E0 width = 60%><font
					// face=\"Calibri\"><i>" + messageTotal +
					// "</i></font></td><td bgcolor=#F7F2E0 width = 20%>" +
					// "FAILED" + "</td><td bgcolor=#F7F2E0 width = 20%>" +
					// ((new SimpleDateFormat("mm:ss")).format(new
					// Date(timeTaken))) + " Minutes:Seconds " + "</td></tr>");
					// print("<tr><td bgcolor=#F7F2E0 width = 100%>" +
					// msgs.get(msgcount) + "</td></tr>");

					// }

					print("<table width=100% bgcolor=#FFFFFF border=0 cellpadding=3 cellspacing=0>");

				}

				IResultMap passResult = testContext.getPassedTests();
				Set<ITestResult> testsPassed = new TreeSet(passResult.getAllResults());

				print("<table width=100% bgcolor=#F7F2E0 border=1 cellpadding=3 cellspacing=0>");

				print("<tr><td bgcolor=#04B404 width = 100%>PASSED TESTCASES</td></tr>");
				print("<table width=100% bgcolor=#F7F2E0 border=1 cellpadding=3 cellspacing=0>");
				print("<table width=100% bgcolor=#FFFFFF border=1 cellpadding=3 cellspacing=0>");
				print("<tr><td bgcolor=#FFCC66 width = 60%>" + "Name" + "</td><td bgcolor=#FFCC66 width = 20%>"
						+ "Status" + "</td><td bgcolor=#FFCC66 width = 20%>" + "Time Taken" + "</td></tr>");

				for (ITestResult testResult : testsPassed) {
					print("<table width=100% bgcolor=#FFFFFF border=1 cellpadding=3 cellspacing=0>");
					List<String> msgs = Reporter.getOutput(testResult);
					long timeTaken = testResult.getEndMillis() - testResult.getStartMillis();
					// print("<tr><td bgcolor=#F7F2E0 width = 33%>" +
					// msgs.get(0) + "</td><td bgcolor=#F7F2E0 width = 33%>" +
					// "PASSED" + "</td><td bgcolor=#F7F2E0 width = 33%>" +
					// (timeTaken/60000) + " minutes (" + timeTaken + " ms)" +
					// "</td></tr>");
					// print("<table width=100% bgcolor=#FFFFFF border=0
					// cellpadding=3 cellspacing=0>");
					String messageTotal = "";
					for (int msgcount = 0; msgcount < msgs.size(); msgcount++) {

						if (msgcount == 1) {
							messageTotal = messageTotal + "---------------------" + "<br />";
							messageTotal = messageTotal + "TEST STEPS" + "<br />";
							messageTotal = messageTotal + "---------------------" + "<br />";
						}

						messageTotal = messageTotal + msgs.get(msgcount) + "<br />";
					}

					// for(int msgcount=0;msgcount<msgs.size();msgcount++){
					print("<tr><td bgcolor=#F7F2E0 width = 60%>" + messageTotal
							+ "</td><td bgcolor=#F7F2E0 width = 20%>" + "PASSED"
							+ "</td><td bgcolor=#F7F2E0 width = 20%>" + (timeTaken / 1000) + " seconds "
							+ "</td></tr>");
					// print("<tr><td bgcolor=#F7F2E0 width = 60%>" +
					// messageTotal + "</td><td bgcolor=#F7F2E0 width = 20%>" +
					// "PASSED" + "</td><td bgcolor=#F7F2E0 width = 20%>" +
					// (timeTaken/60000) + " minutes " + "</td></tr>");
					// print("<tr><td bgcolor=#F7F2E0 width = 60%>" +
					// messageTotal + "</td><td bgcolor=#F7F2E0 width = 20%>" +
					// "PASSED" + "</td><td bgcolor=#F7F2E0 width = 20%>" +
					// ((new SimpleDateFormat("mm:ss")).format(new
					// Date(timeTaken))) + " Minutes:Seconds " + "</td></tr>");
					// print("<tr><td bgcolor=#F7F2E0 width = 100%>" +
					// msgs.get(msgcount) + "</td></tr>");

					// }

					print("<table width=100% bgcolor=#FFFFFF border=0 cellpadding=3 cellspacing=0>");
				}
				IResultMap skippedResult = testContext.getSkippedTests();
				Set<ITestResult> testsSkipped = skippedResult.getAllResults();
				for (ITestResult testResult : testsSkipped) {
					// print(" " + testResult.getName());
				}

			}
		}
		endHtml();
		out.flush();
		out.close();
	}

	/**
	 * MethodName: print() Objective: This method is used to the print the text.
	 * 
	 * @param text
	 *            the text
	 */
	private void print(String text) {
		out.println(text);

	}

	/**
	 * MethodName: startHtml() Objective: This method is used to start the html
	 * 
	 * @param failedCount
	 *            denotes the failed count of the report generated
	 * @param passedCount
	 *            denotes the passed count of the report generated
	 * @param skippedCount
	 *            denotes the skipped count of the report generated
	 * @param totaltimems
	 *            denotes the totaltimems of the report generated
	 * @param starttime
	 *            denotes the starttime of the report generated
	 */
	private void startHtml(int failedCount, int passedCount, int skippedCount, long totaltimems, String starttime) {

		out.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		out.println(
				"<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>TestNG Report</title><style type=\"text/css\">");
		out.println("<tr bgcolor=#CBD9F4><div align=center><p><strong>SUMMARY OF RESULTS </strong></p></div></tr>\n");
		out.println(
				"table {margin-bottom:10px;border-collapse:collapse;empty-cells:show;style:text-align:center}th,td {border:1px solid #009;padding:.25em .5em}th {vertical-align:bottom}td {vertical-align:top}table a {font-weight:bold}.stripe td {background-color: #E6EBF9}.num {text-align:right}.passedodd td {background-color: #3F3}.passedeven td {background-color: #0A0}.skippedodd td {background-color: #DDD}.skippedeven td {background-color: #CCC}.failedodd td,.attn {background-color: #F33}.failedeven td,.stripe .attn {background-color: #D00}.stacktrace {white-space:pre;font-family:monospace}.totop {font-size:85%;text-align:center;border-bottom:2px solid #000}</style></head><body><table><tr><th># Passed</th><th># Skipped</th><th># Failed</th><th>Total Time</th><th>Start Time</th></tr></td><td class=\"num\">"
						+ passedCount + "</td><td class=\"num\">" + skippedCount + "</td><td class=\"num attn\">"
						+ failedCount + "</td><td class=\"num\">"
						+ String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(totaltimems),
					            TimeUnit.MILLISECONDS.toMinutes(totaltimems) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totaltimems)),
					            TimeUnit.MILLISECONDS.toSeconds(totaltimems) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totaltimems))) + " Hours:Minutes:Seconds "
						//+ ((new SimpleDateFormat("hh:mm:ss")).format(new Date(totaltimems))) + " Hours:Minutes:Seconds "
						+ "</td><td class=\"num\">" + starttime + "</td></tr></table>");

	}

	/**
	 * MethodName: endHtml() Objective: This method is used to end the html
	 */
	private void endHtml() {
		// out.println("</body>");
	}
}