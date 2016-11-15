package com.test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.constants.Constants;
import com.logger.SuiteLogger;
import com.pojo.Appointment;
import com.pojo.Attendee;
import com.pojo.TestNGDataProvider;
import com.pojo.UserLink;
import com.utility.FrameworkUtil;
import com.utility.RestWebService;

@Test
public class TestAppointment{


	private String url = Constants.APPLICATION_LINK+Constants.APPOINTMENT;
	private String primaryKey;
	private String apptStartDate;
	private String apptEndDate;
	private String subject;
	private Attendee apptAttendee;
	private String attendanceType;
	private String userPk;

	/**
	 * Post appointment data to create new appointment record
	 * @param params
	 * @throws Exception
	 */
	@Test(priority = 1, enabled=true, dataProviderClass = TestNGDataProvider.class, dataProvider = "TestSuite")
	public void testAppointmentPost(String... params) throws Exception {

		String exceptionString = "Appointment creation failed....";

		apptStartDate = FrameworkUtil.getStartDate();
		apptEndDate = FrameworkUtil.getEndDate();
		attendanceType = params[Constants.APPT_ATTENDANCE_TYPE];
		userPk = params[Constants.APPT_ASSIGNEE_PK];

		UserLink user = new UserLink(userPk);

		Attendee attendee = new Attendee(user, attendanceType);

		subject = params[Constants.APPT_SUBJECT];

		Appointment appointment = new Appointment(subject, apptStartDate, apptEndDate, attendee);

		Appointment result = (Appointment)RestWebService.exchange(url, Constants.REQUEST_METHOD_POST, appointment, Appointment.class);

		primaryKey = result.getPrimaryKey();
		

		ITestResult testResult = Reporter.getCurrentTestResult();

		testResult.setAttribute("Expected Result", subject);
		testResult.setAttribute("Actual Result", result.getSubject());

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(result.getSubject(), subject, exceptionString);
		

	}
	/**
	 *  Get existing appointment record
	 * @throws Exception
	 */

	@Test(priority = 2, enabled=true)
	public void testAppointmentGet() throws Exception {

		String exceptionString = "Getting Appointments details failed....";


		String getURL = url+"/"+primaryKey;

		Appointment response = (Appointment)RestWebService.exchange(getURL, Constants.REQUEST_METHOD_GET, new Appointment(), Appointment.class);

		apptStartDate = response.getStartOn();
		apptEndDate = response.getEndOn();
		apptAttendee = response.getAttendee();
		subject = response.getSubject();

		System.out.println("Get response :: "+response.toString());
		
		ITestResult testResult = Reporter.getCurrentTestResult();

		testResult.setAttribute("Expected Result", subject);
		testResult.setAttribute("Actual Result", response.getSubject());

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response.getSubject(), subject, exceptionString);
		
		

	}
	/**
	 * Update existing appointment record
	 * @param params
	 * @throws Exception
	 */

	@Test(priority = 3, enabled=true, dataProviderClass = TestNGDataProvider.class, dataProvider = "TestSuite")
	public void testAppointmentPut(String... params) throws Exception {


		String exceptionString = "Updating Appointments details failed....";


		String getURL = url+"/"+primaryKey;

		String updatedApptName = params[Constants.APPT_SUBJECT_UPDATED];

		Appointment appt = new Appointment(updatedApptName, apptStartDate, apptEndDate, apptAttendee);

		Appointment response = (Appointment)RestWebService.exchange(getURL, Constants.REQUEST_METHOD_PUT, appt, Appointment.class);
		
		ITestResult testResult = Reporter.getCurrentTestResult();

		testResult.setAttribute("Expected Result", response.getPrimaryKey());
		testResult.setAttribute("Actual Result", response.getSubject());

		Reporter.setCurrentTestResult(testResult);

		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response.getSubject(), response.getPrimaryKey(), exceptionString);
		

	

	}

/**
 * Delete appointment record
 * @throws Exception
 */
	//	@RequestMapping(value= "/DISP/8503/ashima", method=RequestMethod.GET)
	@Test(priority = 4, enabled=true)
	public void testAppointmentDelete() throws Exception {

		String exceptionString = "Appointment deletion failed....";

		SuiteLogger.log.info("primaryKey :: "+primaryKey);
		String deleteUrl = url+"/"+primaryKey;

		String response = (String)RestWebService.exchange(deleteUrl, Constants.REQUEST_METHOD_DELETE, new String("Deleting appointment"), String.class);

		SuiteLogger.log.info("Delete response :: "+response);
		
		ITestResult testResult = Reporter.getCurrentTestResult();

		testResult.setAttribute("Expected Result", "200");
		testResult.setAttribute("Actual Result", response);

		Reporter.setCurrentTestResult(testResult);


		// Assertion, comparing actual response message to expected message
		Assert.assertEquals(response, "200", exceptionString);
		
		
	}


}
