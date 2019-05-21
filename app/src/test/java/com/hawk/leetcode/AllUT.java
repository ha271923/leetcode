package com.hawk.leetcode;

import org.junit.Test;

import com.hawk.leetcode.Exams.AddTwoNumbers;
import com.hawk.leetcode.Exams.FindFirstAndLastPositionOfElementInSortedArray;
import com.hawk.leetcode.Exams.TwoSum;
import com.hawk.leetcode.CONSTANTS;


public class AllUT extends BaseUT {

    int ut_item = CONSTANTS.ID_Search_for_a_Range; // change the UT case at here

    @Test
    public void runTest() throws Exception {
        BaseClass testObj = null;
        Object out;
        switch(ut_item){
            case CONSTANTS.ID_Two_Sum:
                testObj = new TwoSum();
            break;

            case CONSTANTS.ID_Add_Two_Numbers:
                testObj = new AddTwoNumbers();
            break;

            case CONSTANTS.ID_Search_for_a_Range:
                testObj = new FindFirstAndLastPositionOfElementInSortedArray();
                break;


            default:
            // assertEquals( "BaaaaaaaaaaaaaaaaaaaD string",t1.validate_output_string());
        }
        if( testObj != null ) {
            testObj.test();
            testObj.result();
        }
    }

}
