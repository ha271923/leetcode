package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FirstMissingPositive
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 41. First Missing Positive
 */
public class FirstMissingPositive {
    /**
     * Given an unsorted integer array, find the first missing positive integer.

     For example,
     Given [1,2,0] return 3,
     and [3,4,-1,1] return 2.

     Your algorithm should run in O(n) time and uses constant space.

     time : O(n)
     space : O(1)

     * @param nums
     * @return
     */
    // Tips: 未經排序過的數組, 找出某一數
    static public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0    // Q: MissingPositive, 必須是正數
                && nums[i] <= nums.length // Q: First, 需從數字'正1'開始
                && nums[nums[i] - 1] != nums[i])  //
            {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) { // KEY: 答案必須是1,2,3,4,5,6,7.....N的順序
            if (nums[i] != i + 1) { // 元素內容與數組{1,2,3,4,5,6,7.....N}一一比對
                return i + 1; // 發現數值不匹配, 此數即為不連續數字
            }
        }
        return nums.length + 1; // 沒有發現中間有不連續, 那就是為尾端
    }


    public static void main(String[] args) {
        // int[] inputNums = {1,2,3};  // ret=1
        int[] inputNums = {1,2,0};  // ret=3
        // int[] inputNums = {3,4,-1,1;  // ret=2
        // int[] inputNums = {7,8,9,11,12};  // ret=1
        int ret = firstMissingPositive(inputNums);
        System.out.println(ret);
    }
}
