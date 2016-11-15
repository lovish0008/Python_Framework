package com.test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.constants.Constants;
import com.pojo.Task;
import com.pojo.TestNGDataProvider;
import com.utility.RestWebService;

@Test
public class TestTask{
	
	private String url = Constants.APPLICATION_LINK+Constants.TASK;
	private String primaryKey;
	private String shortDescription;

	/**
	 * Post task data to create new task record
	 * @param params
	 * @throws Exception
	 */
	@Test(priority = 1, enabled=true, dataProviderClass = TestNGDataProvider.class, dataProvider = "TestSuite")
	public void testTaskPost(String... params) throws Exception {

		String exceptionString = "Task creation failed....";
		
		shortDescription = params[Constants.TASK_SUBJECT];

		Task task = new Task(shortDescription);

		Task result = (Task)RestWebService.exchange(url, Constants.REQUEST_METHOD_POST, task, Task.class);

		primaryKey = result.getPrimaryKey();
		
		ITestResult testResult = Reporter.getCurrentTestResult();

		testResult.setAttribute("Expected Result", shortDescription);
		testResult.setAttribute("Actual Result", result.getShortDescription());

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(result.getShortDescription(), shortDescription, exceptionString);
		
		

	}
	
	/**
	 * Get existing task record
	 * @throws Exception
	 */
	@Test(priority = 2, enabled=true)
	public void testTaskGet() throws Exception {

		String exceptionString = "Getting Tasks details failed....";


		String getURL = url+"/"+primaryKey;

		Task response = (Task)RestWebService.exchange(getURL, Constants.REQUEST_METHOD_GET, new Task(), Task.class);

		System.out.println("Get response :: "+response);
		
		ITestResult testResult = Reporter.getCurrentTestResult();

		testResult.setAttribute("Expected Result", shortDescription);
		testResult.setAttribute("Actual Result", response.getShortDescription());

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response.getShortDescription(), shortDescription, exceptionString);
		
		

	}
	
	/**
	 * Update existing task record
	 * @param params
	 * @throws Exception
	 */
	@Test(priority = 3, enabled=true, dataProviderClass = TestNGDataProvider.class, dataProvider = "TestSuite")
	public void testTaskPut(String... params) throws Exception {

		String exceptionString = "Updating Tasks details failed....";


		String getURL = url+"/"+primaryKey;
		String updatedTaskSubject = params[Constants.TASK_SUBJECT_UPDATED];

		Task task = new Task(updatedTaskSubject);

		Task response = (Task)RestWebService.exchange(getURL, Constants.REQUEST_METHOD_PUT, task, Task.class);

		System.out.println("Get response :: "+response);
		
		ITestResult testResult = Reporter.getCurrentTestResult();

		testResult.setAttribute("Expected Result", updatedTaskSubject);
		testResult.setAttribute("Actual Result", response.getShortDescription());

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response.getShortDescription(), updatedTaskSubject, exceptionString);
		
		

	}

	/**
	 * Delete task record
	 * @throws Exception
	 */

	@Test(priority = 4, enabled=true)
	public void testTaskDelete() throws Exception {

		String exceptionString = "Task deletion failed....";

		String deleteUrl = url+"/"+primaryKey;
	
		String response = (String)RestWebService.exchange(deleteUrl, Constants.REQUEST_METHOD_DELETE, new String("Deleting Task"), String.class);

		System.out.println("Delete response :: "+response);
		
		ITestResult testResult = Reporter.getCurrentTestResult();

		testResult.setAttribute("Expected Result", "200");
		testResult.setAttribute("Actual Result", response);

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response, "200", exceptionString);
		
	



	}




}
