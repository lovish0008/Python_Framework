package com.test;

import java.util.Set;

import org.testng.Assert;
import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.constants.Constants;
import com.logger.SuiteLogger;
import com.pojo.Project;
import com.pojo.TestNGDataProvider;
import com.utility.RestWebService;


@Test
public class TestProject {

	private String url = Constants.APPLICATION_LINK+Constants.DISP_PROJECT;

	private String primaryKey;
	private String projectEntityType;
	private String projectName;
	private String projectNumber;

	/**
	 * Post project data to create new project record
	 * @param params
	 * @throws Exception
	 */
	@Test(priority = 1, enabled=true, dataProviderClass = TestNGDataProvider.class, dataProvider = "TestSuite" )
	public void testProjectPost(String... params) throws Exception {

		String exceptionString = "Project creation failed....";

		projectEntityType = params[Constants.ENTITY_TYPE];
		
		projectName = params[Constants.PROJECT_NAME];
		
		projectNumber = params[Constants.PROJECT_NUMBER];

		Project project = new Project(projectEntityType, projectName, projectNumber);

		Project result = (Project)RestWebService.exchange(url, Constants.REQUEST_METHOD_POST, project, Project.class);

		primaryKey = result.getPrimaryKey();
		
		ITestResult testResult = Reporter.getCurrentTestResult();


		testResult.setAttribute("Expected Result", projectName);
		testResult.setAttribute("Actual Result", result.getName());

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(result.getName(), projectName, exceptionString);

		


	}
	/**
	 * Get existing project record
	 * @throws Exception
	 */
	@Test(priority = 2, enabled=true)
	public void testProjectGet() throws Exception {

		String exceptionString = "Getting Projects details failed....";

		String getURL = url+"/"+primaryKey;

		Project response = (Project)RestWebService.exchange(getURL, Constants.REQUEST_METHOD_GET, new Project(), Project.class);

		projectEntityType = response.getEntityType();
		projectNumber = response.getIdNumber();


		System.out.println("Get response :: "+response);

		ITestResult testResult = Reporter.getCurrentTestResult();


		testResult.setAttribute("Expected Result", response.getIdNumber());
		testResult.setAttribute("Actual Result", response.getName());

		Reporter.setCurrentTestResult(testResult);
		
		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response.getName(), response.getIdNumber(), exceptionString);

		
	}
	/**
	 * Update existing project record
	 * @param params
	 * @throws Exception
	 */
	@Test(priority = 3, enabled=true, dataProviderClass = TestNGDataProvider.class, dataProvider = "TestSuite")
	public void testProjectPut(String... params) throws Exception {

		String exceptionString = "Updating Project details failed....";

		String getURL = url+"/"+primaryKey;

		String updatedName = params[Constants.PROJECT_NAME_UPDATED];

		Project project = new Project(projectEntityType,updatedName, projectNumber);

		Project response = (Project)RestWebService.exchange(getURL, Constants.REQUEST_METHOD_PUT, project, Project.class);

		System.out.println("Get response :: "+response);
		
		ITestResult testResult = Reporter.getCurrentTestResult();


		testResult.setAttribute("Expected Result", projectNumber);
		testResult.setAttribute("Actual Result", response.getIdNumber());

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response.getIdNumber(), projectNumber, exceptionString);
		
		

	}

	/**
	 * Delete project record
	 * @throws Exception
	 */

	@Test(priority = 4, enabled=true)
	public void testProjectDelete() throws Exception {

		String exceptionString = "Project deletion failed....";

		String deleteUrl = url+"/"+primaryKey;

		String response = (String)RestWebService.exchange(deleteUrl, Constants.REQUEST_METHOD_DELETE, new String("Deleting project"), String.class);

		System.out.println("Delete response :: "+response);
		
		ITestResult testResult = Reporter.getCurrentTestResult();


		testResult.setAttribute("Expected Result", "200");
		testResult.setAttribute("Actual Result", response);

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response, "200", exceptionString);
		
		


	}


}
