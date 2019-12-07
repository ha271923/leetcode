package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaximumSubarray
 * Creator : Edward
 * Date : Aug, 2017
 * Description : TODO
 */
public class MaximumSubarray {
    /**
     * 53. Maximum Subarray
     *
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

     For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
     the contiguous subarray [4,-1,2,1] has the largest sum = ANS: 6.

     * @param nums
     * @return
     */

    // Q: 數字群需要連續, 題目求的是可獲得最大數值的"連續"數字群
    public static void main(String[] args) {
        int[] inputs = {-2,1,-3,4,-1,2,1,-5,4};

        int res;
        res = maxSubArray(inputs);
        // res = maxSubArray_DP(inputs);
        System.out.println("res="+res);
    }

    /*
        RESULT:
        init i=0  nums[0]=-2 seqSum=-2  record=-2
             i=1  nums[1]= 1 seqSum= 1  record= 1
             i=2  nums[2]=-3 seqSum=-2  record= 1
             i=3  nums[3]= 4 seqSum= 4  record= 4
             i=4  nums[4]=-1 seqSum= 3  record= 4
             i=5  nums[5]= 2 seqSum= 5  record= 5
             i=6  nums[6]= 1 seqSum= 6  record= 6
             i=7  nums[7]=-5 seqSum= 1  record= 6
             i=8  nums[8]= 4 seqSum= 5  record= 6
        res=6
     */

    //       -2  1 -3  4 -1  2  1 -5  4
    //    Val[0][1][2][3][4][5][6][7][8]
    //        ^  ^
    //        |  |
    //      compare ( 1 or (-2+1) )
    //          ^
    //          |
    //       seqSum ( bigger= 1 )
    //
    // time : O(n) space : O(1);
    static public int maxSubArray(int[] nums) {
        int record = nums[0]; // 當前最高紀錄
        int seqSum = nums[0]; //
        System.out.println("init i="+0+"  nums["+0+"]="+nums[0]+" seqSum="+seqSum+"  record="+record);
        for (int i = 1; i < nums.length; i++) { // 由左往右掃過去
            seqSum = Math.max(nums[i], seqSum + nums[i]); // 以nums[i]重新開始還是連同前一數(seqSum+nums[i])連續較好? 
            record = Math.max(record, seqSum); // 新的數組的合, 有沒有打破之前連續數組合的紀錄
            System.out.println("i="+i+"  nums["+i+"]="+nums[i]+" seqSum="+seqSum+"  record="+record);
        }
        return record;
    }

    // time : O(n) space : O(n);
    static public int maxSubArray_DP(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
