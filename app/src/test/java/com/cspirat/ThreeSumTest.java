package com.cspirat;


import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ThreeSumTest {

    @Test
    public void testThreeSum() {
        ThreeSum t = new ThreeSum();
        int[] numbers = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>>  res = t.threeSum(numbers);

    }
}