package com.cspirat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FourSum
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 18. 4Sum
 */
public class FourSum {

    /**
     time : O(n^3);
     space : O(n);
     * @param nums
     * @param target
     * @return
     */
    // Reference: https://zhuanlan.zhihu.com/p/53066205
    static public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) { // 與3Sum不同處為多掃描一個數字
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            // 以下類似ThreeSum解法 +++++++++++++++++++++++++++++
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int low = j + 1, high = nums.length - 1;
                while (low < high) {
                    int sum = nums[i] + nums[j] + nums[low] + nums[high]; // FourSum=A+B+C+D
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    } else if (sum < target) {
                        low++;
                    } else high--;
                }
            }
            // 以上類似ThreeSum解法 -----------------------------
        }
        return res;
    }


    public static void main(String[] args) {
        int[] input = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println("threeSum ");
        List<List<Integer>> res = fourSum(input,target);
        for( List<Integer> value: res) {
            System.out.println("value = " + value);
        }
    }
}
