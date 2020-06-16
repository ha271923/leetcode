package com.cspirat;

import com.utils.Out;

import java.util.Arrays;

/**
 *
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 *
 * Given two sorted integer arrays nums1 and nums2, merge nums2
 * into nums1 as one sorted array.
 *
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MergeSortedArray
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 88. Merge Sorted Array
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0}; // KEY: 注意輸入 ArrayIndexOutOfBoundsException , max = 6
        int[] nums2 = new int[]{2,5,6};

        merge(nums1, nums1.length, nums2, nums2.length);
    }
    /**

     time : O(m + n)
     space : O(1)

     */
    static public void merge(int[] nums1, int m, int[] nums2, int n) {
        //start from last initialized number
        int nums1Index = m-1;
        int nums2Index = n-1;
        int nums1LastIndex = nums1.length -1;

        while(nums1Index>=0 && nums2Index>=0) {
            //if element in nums1 is greater
            if(nums1[nums1Index] > nums2[nums2Index] ) {
                nums1[nums1LastIndex] = nums1[nums1Index];
                nums1Index--;
                nums1LastIndex--;
            } else if (nums1[nums1Index]==0 ) {
                nums1Index--;
            } else if (nums2[nums2Index]==0) {
                nums2Index--;
            } else {
                nums1[nums1LastIndex] = nums2[nums2Index];
                nums2Index--;
                nums1LastIndex--;
            }
        }
        //Case where all elements in nums1 are greater than nums2
        while(nums2Index>=0) {
            nums1[nums1LastIndex] = nums2[nums2Index];
            nums2Index--;
            nums1LastIndex--;
        }
        Out.i("END");
    }

    static public void merge3(int[] array1, int arr1_len, int[] array2, int arr2_len) {
        //  int[] mergedArray = new int[array1.length + array2.length]; // KEY: 大部分的solution都會出現ArrayIndexOutOfBoundsException, 因為array1的length
        int arr1_Idx = arr1_len - 1;
        int arr2_Idx = arr2_len - 1;
        int arr3_Idx = arr1_Idx + arr2_len - 1;

        while (arr1_Idx >= 0 && arr2_Idx >= 0) {
            array1[arr3_Idx--] =
                    array1[arr1_Idx] >= array2[arr2_Idx] ?
                            array1[arr1_Idx--] : array2[arr2_Idx--];
        }
        while (arr2_Idx >= 0) {
            array1[arr3_Idx--] =
                    array2[arr2_Idx--];
        }
        Out.i("END");
    }

    static void merge4(int[] nums1, int m, int[]  nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            nums1[k--] = (i >= 0 && nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        }
        Out.i("END");
    }

    static public void merge6(int[] nums1, int m, int[] nums2, int n) {
        for(int i = m, ctr = 0; i <nums1.length;i++)
            nums1[i] = nums2[ctr++];
        Arrays.sort(nums1);
        Out.i("END");
    }
}
