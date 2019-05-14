package com.hawk.leetcode;

import org.junit.Test;

import com.hawk.leetcode.Exams.AddTwoNumbers;
import com.hawk.leetcode.Exams.TwoSum;
import com.hawk.leetcode.CONSTANTS;


public class AllUT extends BaseUT {

    int ut_item = CONSTANTS.ID_Add_Two_Numbers; // change the UT case at here

    @Test
    public void runTest() throws Exception {
        BaseClass testObj;
        Object out;
        switch(ut_item){
            case CONSTANTS.ID_Two_Sum:
                testObj = new TwoSum();
                int[] out1 = (int[])testObj.test(TwoSum.input, TwoSum.target);
            break;

            case CONSTANTS.ID_Add_Two_Numbers:
                testObj = new AddTwoNumbers();
                testObj.test();
                testObj.result();
            break;

            default:
                    ;

            // assertEquals( "BaaaaaaaaaaaaaaaaaaaD string",t1.validate_output_string());
        }
    }

}
