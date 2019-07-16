package com.cspirat;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class GenerateParenthesesTest {
    @DataProvider(name = "input")
    public Object[][] getExamples() {

        return new Object[][]{
                new Object[]{3},
        };
    }

    @Test(dataProvider = "input")
    public void test(int n) {
        GenerateParentheses m = new GenerateParentheses();
        List<String> res = m.generateParenthesis(n);

    }
}
