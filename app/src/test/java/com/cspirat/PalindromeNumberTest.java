package com.cspirat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PalindromeNumberTest {
    @DataProvider(name = "isPalindrome")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{121},
                new Object[]{-121},
                new Object[]{10},
        };
    }

    @Test(dataProvider = "isPalindrome")
    public void test(int x) {
        PalindromeNumber m = new PalindromeNumber();
        m.isPalindrome(x);

    }
}