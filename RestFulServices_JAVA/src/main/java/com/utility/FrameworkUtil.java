package com.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;

import com.constants.Constants;
import com.logger.SuiteLogger;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;

public class FrameworkUtil {

	public static ArrayList< String > dataList = new ArrayList< String >();
	private static String csvSpliter;
	private static LocalDateTime startDate;
	private static LocalDateTime endDate;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");

	/**
	 * This method reads csv for test data
	 */
	public static void loadCSV() {

		int lineNo = 0;
		String line;

		try {

			File dataFile = new File( Constants.DATA_FILE );

			// check if file exists
			if( !dataFile.exists() )
				SuiteLogger.log.error( "CSV file : " + Constants.DATA_FILE + " : does not exist" );

			// data file reader
			FileReader dataFileReader = new FileReader( dataFile );

			BufferedReader dataFileBuffReader = new BufferedReader( dataFileReader );

			while( ( line = dataFileBuffReader.readLine() ) != null ) {

				SuiteLogger.log.info("line :: "+line);

				if( lineNo == 0 ) {

					if( line.startsWith( "\"" ) ) 
						line = line.substring( 1 );
					if( line.endsWith( "\"" ) ) 
						line = line.substring( 0 , line.length() - 1 );
					if( line.contains( ";" ) )
						csvSpliter = ";";
					else
						csvSpliter = ",";

					lineNo++;

					continue;
				}

				dataList.add( line );

				SuiteLogger.log.info("dataList size :: "+dataList.size());

			}

			// closing streams
			dataFileReader.close();
			dataFileBuffReader.close();

			getData(1);

		} catch( Exception e ) {
			SuiteLogger.log.error( "Exception while reading csv : " , e );
		}

	}
	/**
	 * This method loads constants from  config file
	 */
	public static void loadConstants() {

		try {
			FileReader reader = new FileReader( "config/config.properties" );

			Properties prop = new Properties();

			prop.load( reader );

			Constants.APPLICATION_LINK = prop.getProperty( "APPLICATION_LINK" );
			Constants.DISP_PROJECT = prop.getProperty( "DISP_PROJECT" );
			Constants.APPOINTMENT = prop.getProperty( "APPOINTMENT" );
			Constants.TASK = prop.getProperty( "TASK" );
			Constants.DOCUMENT = prop.getProperty( "DOCUMENT" );

			Constants.LOGIN_CREDENTIALS = prop.getProperty( "LOGIN_CREDENTIALS" );

			Constants.TESTNG_XML_SUITE = prop.getProperty( "TESTNG_XML_SUITE" );
			Constants.TESTNG_OUTPUT_FILE_DIRECTORY = prop.getProperty( "TESTNG_OUTPUT_FILE_DIRECTORY" );

			Constants.DATA_FILE = prop.getProperty( "DATA_FILE" );

			Constants.buildFilePath = prop.getProperty( "buildFilePath" );
			Constants.testNGReportFilePath = prop.getProperty( "testNGReportFilePath" );
			Constants.testNGXsltFilePath = prop.getProperty( "testNGXsltFilePath" );


			prop.clear();
			reader.close();

		} catch( IOException e ) {
			SuiteLogger.log.error( "Unable to load application constants, using default" , e );
		}
	}

	public static String[][] getData( int rowNo ) {

		String[][] csvDataArray = new String [1][1];

		int lineNo = 0;

		for( Iterator iterator = dataList.iterator(); iterator.hasNext(); ) {

			String line = (String) iterator.next();

			SuiteLogger.log.info("Into get data Line:: "+line);

			if( lineNo != rowNo ) {
				lineNo++;
				continue;
			}	

			csvDataArray[0] = line.split( csvSpliter );

			break;
		}

		return csvDataArray;
	}


	public static String getStartDate(){
		
		LocalDateTime startTime = LocalDateTime.now();
		
		startDate = startTime.plusHours(3);
		
		String startOn = startDate.format(formatter);
		
		SuiteLogger.log.info("Start On Date:: "+startOn);
		return startOn;

	}

	public static String getEndDate(){

		LocalDateTime endTime = startDate;
		
		endDate = endTime.plusHours(1);
		
		String endOn = endDate.format(formatter);
		SuiteLogger.log.info("End On Date:: "+endOn);
		return endOn;
	}
}