package com.report;

import java.awt.Desktop;
import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import com.constants.Constants;
import com.logger.SuiteLogger;

public class TestNGReport {

	public static void generateReport(Report report) {

		// Create ant project and configure.
		Project project = new Project();
		
		ProjectHelper.configureProject(project, new File(Constants.buildFilePath));
		

		// Set ant project properties
		project.setProperty("testng_report_xml", Constants.testNGReportFilePath);
		project.setProperty("xslpath", Constants.testNGXsltFilePath);
		project.setProperty("output", System.getProperty("user.dir")
				+ "/output");
		project.setProperty("systemInfo",
				String.valueOf(report.getEnvironment()));
		project.setProperty("reportType", "");
		project.setProperty("buildNumber",
				String.valueOf(report.getBuildNumber()));
		project.setProperty("executedOn",
				String.valueOf(report.getExecutionDate()));
		project.setProperty("pass", String.valueOf(report.getTestPassed()));
		project.setProperty("fail", String.valueOf(report.getTestFailed()));
		project.setProperty("skip", String.valueOf(report.getTestSkipped()));
		project.setProperty("executionTime",
				String.valueOf(report.getTotalTime()));
		project.setProperty("totalTests",
				String.valueOf(report.getTotalTests())); 

		// Try to generate the report.
		try {
			project.executeTarget("test");
			SuiteLogger.log.info("Result generated in "
					+ new File("output").getAbsolutePath());

			File htmlFile = new File(new File("output").getAbsolutePath() + "/"
					+ "index.html");
			
			SuiteLogger.log.info("Generating report finished");
			
				// Opening into a default browser
				Desktop.getDesktop().open(htmlFile);
		} catch (Exception e) {
		    SuiteLogger.log.error("Exception : ", e);
		}
	}
}