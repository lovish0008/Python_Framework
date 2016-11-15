/**
 * 
 */
package com.test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.constants.Constants;
import com.pojo.Document;
import com.pojo.TestNGDataProvider;
import com.utility.RestWebService;

/**
 * @author asingla
 *
 */
@Test
public class TestDocument {

	private String url = Constants.APPLICATION_LINK+Constants.DOCUMENT;
	private String primaryKey;
	private String name;
	private String type;


	/**
	 * Post Document data to create new Document folder
	 * @param params
	 * @throws Exception
	 */
	@Test(priority = 1, enabled=true, dataProviderClass = TestNGDataProvider.class, dataProvider = "TestSuite")
	public void testDocumentPost(String... params) throws Exception {

		String exceptionString = "Document creation failed....";

		name = params[Constants.DOCUMENT_NAME];
		type = params[Constants.DOCUMENT_TYPE];

		Document document = new Document(name, type);

		Document result = (Document)RestWebService.exchange(url, Constants.REQUEST_METHOD_POST, document, Document.class);

		primaryKey = result.getPrimaryKey();
		
		ITestResult testResult = Reporter.getCurrentTestResult();


		testResult.setAttribute("Expected Result", name);
		testResult.setAttribute("Actual Result", result.getName());

		Reporter.setCurrentTestResult(testResult);


		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(result.getName(), name, exceptionString);
		
		
	}



	/**
	 * Get existing document folder
	 * @throws Exception
	 */
	@Test(priority = 2, enabled=true)
	public void testDocumentGet() throws Exception {

		String exceptionString = "Getting Documents details failed....";


		String getURL = url+"/"+primaryKey;

		Document response = (Document)RestWebService.exchange(getURL, Constants.REQUEST_METHOD_GET, new Document(), Document.class);

		System.out.println("Get response :: "+response);
		
		ITestResult testResult = Reporter.getCurrentTestResult();
		testResult.setAttribute("Expected Result", type);
		testResult.setAttribute("Actual Result", response.getName());

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response.getName(), type, exceptionString);
		
		

	}

	/**
	 * Update existing Document folder
	 * @param params
	 * @throws Exception
	 */
	@Test(priority = 3, enabled=true, dataProviderClass = TestNGDataProvider.class, dataProvider = "TestSuite")
	public void testDocumentPut(String... params) throws Exception {

		String exceptionString = "Updating Document details failed....";
		String getURL = url+"/"+primaryKey;
		String updatedName = params[Constants.DOCUMENT_NAME_UPDATED];

		Document document = new Document(updatedName, type);

		Document response = (Document)RestWebService.exchange(getURL, Constants.REQUEST_METHOD_PUT, document, Document.class);

		System.out.println("Get response :: "+response);
		
		ITestResult testResult = Reporter.getCurrentTestResult();
		testResult.setAttribute("Expected Result", updatedName);
		testResult.setAttribute("Actual Result", response.getName());

		Reporter.setCurrentTestResult(testResult);


		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response.getName(), updatedName, exceptionString);
		
	}

	/**
	 * Delete Document folder
	 * @throws Exception
	 */

	@Test(priority = 4, enabled=true)
	public void testDocumentDelete() throws Exception {

		String exceptionString = "Document deletion failed....";

		String deleteUrl = url+"/"+primaryKey;
	
		String response = (String)RestWebService.exchange(deleteUrl, Constants.REQUEST_METHOD_DELETE, new String("Deleting document"), String.class);

		System.out.println("Delete response :: "+response);
		
		ITestResult testResult = Reporter.getCurrentTestResult();
		testResult.setAttribute("Expected Result", "200");
		testResult.setAttribute("Actual Result", response);

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response, "200", exceptionString);
		
		




	}





}
