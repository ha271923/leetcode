package com.cspirat;

import org.testng.annotations.Test;

import java.util.List;

public class CombinationSumTest {
    @Test
    public void test() {
        int testId = 4;
        System.out.printf("+++++++++++++++++++++++++++++++++\n");
        switch(testId) {
            case 1: // duplicate
                CombinationSum t1 = new CombinationSum();
                int[] num1 = {2, 3, 6, 7};
                int target1 = 7;
                List<List<Integer>> res1 = t1.combinationSum(num1, target1);
                break;
            case 2: // no duplicate
                CombinationSumII t2 = new CombinationSumII();
                int[] num2 = {10, 1, 2, 7, 6, 1, 5};
                int target2 = 8;
                List<List<Integer>> res2 = t2.combinationSum2(num2, target2);
                break;
            case 3: // 1~9
                CombinationSumIII t3 = new CombinationSumIII();
                int numSets3 = 0;
                int target3 = 8;
                List<List<Integer>> res3 = t3.combinationSum3(numSets3, target3);
                break;
            case 4:
                CombinationSumIV t4 = new CombinationSumIV();
                int[] num4 = {1, 2, 3};
                int target4 = 4;
                int res4 = t4.combinationSum42(num4, target4);
                break;
            default:
        }
        System.out.printf("---------------------------------\n");
    }
}
