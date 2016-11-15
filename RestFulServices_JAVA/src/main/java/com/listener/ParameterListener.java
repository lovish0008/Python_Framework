package com.listener;

import java.util.Date;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.constants.Constants;
import com.logger.SuiteLogger;
import com.pojo.TestNGDataProvider;
import com.report.Report;
import com.utility.FrameworkUtil;


/*
 *  This class contain listeners for test suit
 */
public class ParameterListener extends TestListenerAdapter {

	private static int failedTestcases=0;
	private static int passedTestcases=0;
	private static int skippedTestcases=0;
	private static int totalTests=0;
	public static Report report = new Report();
	private long startTime; 
	private long endTime;
	private boolean secureTokenFail;

	//static int testNo = 0;
	/*
	 * This method execute on start of test suite
	 * @param test suite information
	 */
	@Override
	public void onStart( ITestContext testContext  ) {

		System.out.println("In OnStart....");

		// getting test name to use as test data file name
		String testName = testContext.getName();
		startTime = System.currentTimeMillis();

		SuiteLogger.log.info(" <=== test : "+ testName +" : begin ===> ");

		int testNo = 0;

		TestNGDataProvider.testData = FrameworkUtil.getData( testNo );

		report = new Report();
	}


	/*
	 * This method execute before test methods
	 * @param test information
	 */
	@Override
	public void onTestStart( ITestResult test ) {

		SuiteLogger.log.info(" -- test method : "+ test.getName()+" : started --");
		Reporter.log("Test Scenario : "+ test.getName()+" : started --"); 
		
	}

	/*
	 * This method execute on successful completion of test method
	 * @param test result
	 */
	@Override
	public void onTestSuccess( ITestResult test ) {
		SuiteLogger.log.info(" -- test method : "+ test.getName()+" : succeeded --"+"\n");
		
			
		String expectedResult = (String)test.getAttribute("Expected Result");
		String actualResult = (String)test.getAttribute("Actual Result");
		
		Reporter.log("Expected Result : "+ expectedResult); 
		Reporter.log("Actual Result : "+ actualResult);
		Reporter.log("Test Scenario Result : "+ test.getName()+" : SUCCESS"); 

		passedTestcases++;

	}

	/*
	 * This method execute on failure of test method
	 * @param test result
	 */
	@Override
	public void onTestFailure( ITestResult test ) {

		SuiteLogger.log.error(" -- test method : "+ test.getName()+" : Failed --"+test.getThrowable()+"\n");
		
		String expectedResult = (String)test.getAttribute("Expected Result");
		String actualResult = (String)test.getAttribute("Actual Result");
		
		Reporter.log("Expected Result : "+ expectedResult); 
		Reporter.log("Actual Result : "+ actualResult);
		Reporter.log("Test Scenario Result : "+ test.getName()+" : FAILED :: "+test.getThrowable()); 
		failedTestcases++;
	}

	/*
	 * This method execute if test method is skipped
	 * @param test result
	 */
	@Override
	public void onTestSkipped( ITestResult test ) {
		SuiteLogger.log.warn(" -- test method : "+ test.getName()+" : skipped --"+test.getThrowable()+"\n");
		
		String expectedResult = (String)test.getAttribute("Expected Result");
		String actualResult = (String)test.getAttribute("Actual Result");
		
		Reporter.log("Expected Result : "+ expectedResult); 
		Reporter.log("Actual Result : "+ actualResult);
		Reporter.log("Test Scenario : "+ test.getName()+" : SKIPPED :: "+test.getThrowable()); 
		
		skippedTestcases++;
	}

	/*
	 * This method execute on finish of test suite
	 * @param test suite result
	 */
	@Override
	public void onFinish(ITestContext testContext) {

		totalTests = (failedTestcases + skippedTestcases + passedTestcases);
		endTime = System.currentTimeMillis();
		SuiteLogger.log.info(" <=== test : " + testContext.getName()
		+ " : finished ===> \n");

		SuiteLogger.log.info("FailedTestcases : "+failedTestcases);
		SuiteLogger.log.info("SkippedTestcases : "+skippedTestcases);
		SuiteLogger.log.info("PassedTestcases : "+passedTestcases);
		SuiteLogger.log.info("TotalTests : "+totalTests);
		SuiteLogger.log.info("TotalTime : "+ (endTime - startTime));


		report.setTestFailed(failedTestcases);
		report.setTestSkipped(skippedTestcases);
		report.setTestPassed(passedTestcases);
		report.setTotalTests(totalTests);
		report.setTotalTime((endTime - startTime));
		report.setBuildNumber(Constants.buildNumber);
		report.setEnvironment(System.getProperty( "os.name" ));
		report.setExecutionDate(new Date());
	}

}
