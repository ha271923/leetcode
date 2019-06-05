package com.hawk.leetcode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hawk.leetcode.Basic.BFS;
import com.hawk.leetcode.Basic.BFS_matrix;
import com.hawk.leetcode.Basic.DFS;
import com.hawk.leetcode.Basic.DFS_matrix;
import com.hawk.leetcode.Basic.DFS_simple;
import com.hawk.leetcode.Exams.AddTwoNumbers;
import com.hawk.leetcode.Exams.FindFirstAndLastPositionOfElementInSortedArray;
import com.hawk.leetcode.Exams.TwoSum;

public class AllUT extends BaseUT {

    int ut_item = CONSTANTS.ID_BASIC_BFS; // change the UT case at here

    @DataProvider(name = "MedianOfTwoSortedArrays") // data for: void testExamples(int[] nums1, int[] nums2, double expected) API
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{1, 2, 3, 5, 7}, new int[]{8, 9, 10, 11, 12}, 7.5},
                new Object[]{new int[]{1, 3, 8, 9, 15}, new int[]{7, 11, 18, 19, 21, 25}, 11.0},
                new Object[]{new int[]{}, new int[]{}, 0.0},
                new Object[]{new int[]{2}, new int[]{}, 2.0},
                new Object[]{new int[]{}, new int[]{2}, 2.0},
                new Object[]{new int[]{1, 2, 3, 4, 5}, new int[]{2, 4, 5, 6, 7}, 4.0},
                new Object[]{new int[]{1, 2, 3, 4, 5}, new int[]{2, 4, 5, 6, 7, 8}, 4.0},
        };
    }

    // @Test(dataProvider = "MedianOfTwoSortedArrays")
    @Test
    public void runTest() throws Exception {
        BaseClass testObj = null;
        Object out;
        switch(ut_item){
            case CONSTANTS.ID_BASIC_DFS:
                testObj = new DFS();
                break;

            case CONSTANTS.ID_BASIC_DFS_MATRIX:
                testObj = new DFS_matrix();
                break;
            case CONSTANTS.ID_BASIC_DFS_SIMPLE:
                testObj = new DFS_simple();
                break;

            case CONSTANTS.ID_BASIC_BFS:
                testObj = new BFS();
                break;

            case CONSTANTS.ID_BASIC_BFS_MATRIX:
                testObj = new BFS_matrix();
                break;

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
