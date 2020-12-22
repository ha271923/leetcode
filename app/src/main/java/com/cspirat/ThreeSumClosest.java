package com.cspirat;

import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ThreeSumClosest
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 16. 3Sum Closest
 */
public class ThreeSumClosest {
    /**
     * For example, given array S = {-1 2 1 -4}, and target = 1.

     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

     time : O(n^2);
     space : O(1);

     * @param nums
     * @param target
     * @return
     */
    public static void main(String[] args) {
        int[] input = {-1, 2, 1, -4};
        int target = 1;
        System.out.println("threeSumClosest ");
        int res = threeSumClosest(input, target);
        System.out.println("res = " + res);
    }

    // Tips1: 一定要3個數字相加
    // Tips2: two loop
    //  A:   for loop --- scanning num[i]
    //  B: while loop --- L or R pointer mover either
    //                       A            B
    //  iL->.......<-R = num[0] + num[1] ~~~ num[n-1]
    //   iL->......<-R = num[1] + num[2] ~~~ num[n-1]
    //    iL->.....<-R = num[2] + num[3] ~~~ num[n-1]
    //     iL->....<-R = num[3] + num[4] ~~~ num[n-1]
    //      iL->...<-R = num[4] + num[5] ~~~ num[n-1]
    //       iL->..<-R = num[5] + num[6] ~~~ num[n-1]
    //        iL->.<-R = num[6] + num[7] ~~~ num[n-1]
    //         iL-><-R = num[7] + num[8] ~~~ num[n-1]
    static public int threeSumClosest(int[] nums, int target) {
        int res = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int L = i + 1;
            int R = nums.length - 1;

            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R]; // KEY: 直接算
                if (sum > target)
                    R--;
                else
                    L++;

                if (Math.abs(sum - target) < Math.abs(res - target)) { // KEY: abs(), closet is small value, not bigger.
                    res = sum;
                }
            }
        }
        return res;
    }


}
