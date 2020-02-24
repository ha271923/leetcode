package com.cspirat;

import java.util.HashMap;

/**
 * Created by Edward on 23/07/2017.
 */
public class TwoSum {

    /**
     *  1. Two Sum

     Given an array of integers, return indices of the two numbers such that they add up to a specific target.

     You may assume that each input would have exactly one solution, and you may not use the same element twice.

     Given nums = [2, 7, 11, 15], target = 9,

     Because nums[0] + nums[1] = 2 + 7 = 9,

     return [0, 1].

     time : O(n)
     space : O(n)
     * @param nums
     * @param target
     * @return
     */

    /**
     *   index =    0     1      2      3    Value
     *  target =    9     9      9      9
     *    nums = [  2  ,  7  ,  11  ,  15  ]
     *    diff =    7     2     -2     -6    Key
     *
     */
    public static int[] twoSum(int[] nums, int target) {

       if (nums == null || nums.length < 2) {
           return new int[]{-1, -1};
       }
       // ??? twoSum題目沒說input array是否是sorted
       int[] res = new int[]{-1, -1};
       HashMap<Integer, Integer> map = new HashMap<>();
        // KEY: 原本需要兩個LOOPs=LOOP1+LOOP2=RESULT的演算法找出答案
       for (int i = 0; i < nums.length; i++) { // KEY: 現在利用LOOP1[i]算出答案, LOOP1[i+1]利用hashmap快速查找已記錄的答案
           if (map.containsKey(target - nums[i])) { // 2. SEARCH: Ki
               res[0] = map.get(target - nums[i]);  // 3. LOAD: Ki
               res[1] = i;
               break;
           }
           map.put(nums[i], i); // 1. SAVE: A - B = Ki
       }

       return res;
    }
}
