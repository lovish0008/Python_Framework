package com.pojo;

import org.testng.annotations.DataProvider;

/*
 * This class hold test csv data
 */
public class TestNGDataProvider {
    
    public static String[][] testData;
    
    /**
     * Data Provider method.
     * @return Test data
     */
    @DataProvider(name = "TestSuite")
    public static String[][] getData() {

        return testData;
    }
}
