package com.cspirat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringtoIntegerTest {
    @DataProvider(name = "StringtoIntegerTest")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"42"},
                new Object[]{"   -42"},
                new Object[]{"4193 with words"},
                new Object[]{"words and 987"},
                new Object[]{"-91283472332"},
        };
    }

    @Test(dataProvider = "StringtoIntegerTest")
    public void test(String s) {
        StringtoInteger m = new StringtoInteger();
        m.myAtoi(s);

    }
}