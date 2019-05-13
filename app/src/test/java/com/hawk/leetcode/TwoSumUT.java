package com.hawk.leetcode;

import org.junit.Test;

import com.hawk.leetcode.Exams.TwoSum;


public class TwoSumUT extends BaseUT {

    @Test
    public void runTest() throws Exception {
        BaseClass testObj = new TwoSum();
        int[] out = (int[])testObj.test(TwoSum.input, TwoSum.target);
        // assertEquals( "BaaaaaaaaaaaaaaaaaaaD string",t1.validate_output_string());
    }

}
