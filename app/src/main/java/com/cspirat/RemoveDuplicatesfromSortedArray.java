package com.cspirat;

/**
 * Created by Edward on 25/07/2017.
 */
//   Do not allocate extra space for another array, you must do this in place with constant memory.
public class RemoveDuplicatesfromSortedArray {
    public static void main(String[] args) {
        // test1
        int[] inputs = {1,1,2,2,3,4,5,6};
        int res = removeDuplicates(inputs);
        System.out.println(res);
    }
    /**
     * 26. Remove Duplicates from Sorted Array
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

     Do not allocate extra space for another array, you must do this in place with constant memory.

     For example,
     Given input array nums = [1,1,2],

     Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

     case : [1,1,2,2,3,4,5,6]
             1,2,3,4,5,6
                 c
                     i
     result : [1,2,3,4,5,6]

     time : O(n);
     space : O(1);

     * @param nums
     * @return
     */
    // Tips: Sorted Array一路向東掃
    // Q: Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory
    // Output有兩個, 1.length   2. int[] nums 這是call-by-reference
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int newLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] != nums[i]) { // Sorted Array 的前後關希
                nums[newLen++] = nums[i]; // KEY:
            }
        }
        return newLen;
    }
}
