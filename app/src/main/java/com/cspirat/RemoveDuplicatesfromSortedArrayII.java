package com.cspirat;

/**
 * Created by Edward on 25/07/2017.
 */
public class RemoveDuplicatesfromSortedArrayII {
    public static void main(String[] args) {
        // test1
        int[] inputs = {1,1,1,2,2,3}; // ans: 5 , remove the duplicates in-place(call-by-reference)
        int res = removeDuplicates(inputs);
        System.out.println(res);
    }

    /**
     * 80. Remove Duplicates from Sorted Array II (26. Remove Duplicates from Sorted Array: follow up)
     * Follow up for "Remove Duplicates":
     What if duplicates are allowed at most twice?

     For example,
     Given sorted array nums = [1,1,1,2,2,3],

     Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.

     case : [1,1,1,2,2,3]
            1,1,2,2,3
                    c
                     i
     result : [1,1,2,2,3]

     time: O(n);
     space: O(1);

     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 2)
            return nums.length;
        /*

         i= 0 1 2
           [ ]      從i=0開始, 無法[i-1]以進行兩數比對
           [   ]    從i=1開始, 可以[i-1]以進行兩數比對, 但是因為題目允許每個數字最多重複一次(即兩個相同的數字),所以[0][1]不論是否相同,必定為2
           [ ]-[ ]  從i=2開始, 可以[i-2]以進行兩數比對, KEY:以下範例4組數組可推導出公式
       Ex1= 1 1 1 =len=2
       Ex2= 1 1 2 =len=3
       Ex3= 1 2 2 =len=3
       Ex4= 1 2 3 =len=3


         */
        int count = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[count-2]) { // KEY:由上述範例4組數組可推導出公式, 比對[i]與[i-2]是否相等, 每次的i++只是sliding window而已
                nums[count++] = nums[i];  // Output不僅要len還要call-by-reference

            }
        }
        return count;
    }
}
