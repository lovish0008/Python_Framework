package execution;

import java.io.File;
import java.util.Collection;

import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import com.constants.Constants;
import com.listener.ParameterListener;
import com.logger.SuiteLogger;
import com.report.TestNGReport;
import com.utility.FrameworkUtil;

public class Execution {

	/**
	 * Method to initialize constants and CSV file
	 */
	public void init(){

		FrameworkUtil.loadConstants();
		FrameworkUtil.loadCSV();
	}

	/**
	 * Method to execute test suites
	 */
	public void execute() {

		// Read csv to get test suite iteration count, test data rows = test suite iteration
		int count = FrameworkUtil.dataList.size();

		try {
			for( int i = 0; i < count; i++ ) {

				// Gets the file name of this URL
				String testSuitXml = Constants.TESTNG_XML_SUITE;

				SuiteLogger.log.info( "testSuitXml :: " +testSuitXml );

				TestNG testSuite = new TestNG();

				// parse testing xml to suites
				Collection< XmlSuite > suites = new Parser( new File( testSuitXml ).getAbsolutePath() ).parse();

				testSuite.setXmlSuites( Lists.newArrayList( suites ) );

				File testResultLoc = new File( Constants.TESTNG_OUTPUT_FILE_DIRECTORY);

				testSuite.setOutputDirectory( testResultLoc.getAbsolutePath() );

				testSuite.run();

			}

		} catch( Exception ex ) {
			SuiteLogger.log.error( "Unable to run test suit " , ex );
		}

	}

	/**
	 * Method to generate report
	 */
	public void generateReport() {
		
		TestNGReport.generateReport( ParameterListener.report );
	}
}
