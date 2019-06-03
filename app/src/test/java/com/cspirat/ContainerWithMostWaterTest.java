package com.cspirat;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContainerWithMostWaterTest {

    /***
     *
             N              N
             N              N     N
             N  N           N     N
             N  N     N     N     N
             N  N     N  N  N     N
             N  N     N  N  N  N  N
             N  N  N  N  N  N  N  N
          N  N  N  N  N  N  N  N  N
          1  8  6  2  5  4  8  3  7

     */

    @DataProvider(name = "testdata")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{1,8,6,2,5,4,8,3,7}}, // maxArea = 49
        };
    }

    @Test(dataProvider = "testdata")
    public void test(int[] x) {
        ContainerWithMostWater m = new ContainerWithMostWater();
        Assert.assertEquals(49, m.maxArea(x));

    }
}