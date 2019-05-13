package com.hawk.leetcode;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 * <b href="https://developer.android.com/training/testing/unit-testing/local-unit-tests.html">Building Local Unit Tests</b>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    // Right-Click on Project\ExampleUnitTest.java file and Click on Run 'ExampleUnitTest'
    @Test
    public void check_output_str() throws Exception {
        test1 t1 = new test1();
        assertEquals( "BaaaaaaaaaaaaaaaaaaaD string",t1.validate_output_string());
    }

}