package com.cspirat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LongestCommonPrefixTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"abcabcbb", "abddd", "abffff"},
                new Object[]{"bbbbb"},
                new Object[]{"pwwkew"},
        };
    }

    @Test(dataProvider = "examples")
    public void testExamples(String[] s) {
        LongestCommonPrefix m = new LongestCommonPrefix();
        String res = m.longestCommonPrefix(s);

    }
}
