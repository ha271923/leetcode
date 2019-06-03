package com.cspirat;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegularExpressionMatchingTest {
    /**
     '.' : 比對任何一個字元（但換行符號不算）
     '*' : 比對'前一個字元'零次或更多次 , *等價於{0,} ==> {n,} = n是一個非負整數。至少匹配前一字元n次, 0次代表該字元可以被刪除     Example 1:
         s = "aa"
         p = "a"
         Output: false
         Explanation: "a" does not match the entire string "aa".
     Example 2:
         s = "aa"
         p = "a*"
         Output: true
         Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
     Example 3:
         s = "ab"
         p = ".*"
         Output: true
         Explanation: ".*" means "zero or more (*) of any character (.)".
     Example 4:
         s = "aab"
         p = "c*a*b" -> a*b -> aab -> match=true
         Output: true
         Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
     Example 5:
         s = "mississippi"
         p = "mis*is*p*."
         Output: false
     */
    // 理解RegEx: https://www.regextester.com/
    @DataProvider(name = "test data")
    public Object[][] getExamples() {
        return new Object[][]{
                // 'mis*is*ip*.' is true and
                // 'mis*is*p*.' is false because i is missing after s*
                new Object[]{"mississippi","mis*is*p*."}, // f   Q:??? A: *代表比對'前一個字元'零次或更多次
                new Object[]{"aa","a"}, // f
                new Object[]{"aa","a*"}, // t
                new Object[]{"ab",".*"}, // t
                new Object[]{"aab","c*a*b"}, // t
                new Object[]{"mississippi","mis*is*.p*."}, // t  符合的RegEx pattern
        };
    }

    @Test(dataProvider = "test data")
    public void test(String s, String p) {
        RegularExpressionMatching m = new RegularExpressionMatching();
        boolean res = m.isMatch(s,p);
        Assert.assertTrue(res);

    }
}