package com.cspirat;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 23/07/2017.
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{  2  ,  7  ,  11  ,  15  };
        int   target = 9;
        int[] res = twoSum(nums, target);

        System.out.println(res[0]);
        System.out.println(res[1]);
    }

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
     *
     *
     *   Ans:
     *    inputs=[  2  ,  7  ,  11  ,  15  ] Value[i]
     *    --------------------------------------
     *  diff=9 |    7     2     -2     -6   <- diffs[i]
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
           // 2. i=i+1
           // TRUE == map.containsKey(V)  return boolean type, not int.
           if (map.containsKey(target - nums[i])) { // KEY: 3. SEARCH ALL by contains: 比對快取, 尋找是否有所缺少的差值 , KEY: 在已儲存的資料中查找
               // V = map.get(K)
               res[0] = map.get(target - nums[i]);  // 4. LOAD: 該差值的V就是index_1
               res[1] = i; // 4. LOAD: 當前的index就是index_2
               break;
           }
           // map.put(K,V)
           map.put(nums[i], i); // 1. KEY: CACHE: 將掃過的數值存入快取, 利用HashMap的 Time-Complexity 優勢
       }

       return res;
    }
}
