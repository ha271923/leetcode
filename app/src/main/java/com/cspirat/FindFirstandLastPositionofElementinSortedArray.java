package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SearchforaRange
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class FindFirstandLastPositionofElementinSortedArray {
    /**
     * 34. Search for a Range
     * Given an array of integers sorted in ascending order, find the starting and ending
     * position of a given target value.

     Your algorithm's runtime complexity must be in the order of O(log n).

     If the target is not found in the array, return [-1, -1].

     For example,
     Given [5, 7, 7, 8, 8, 10] and target value 8,
     return [3, 4].

     time : O(logn)
     space : O(1);
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int start = findFirst(nums, target);
        if (start == -1) return new int[]{-1, -1};
        // int end = findLast(nums, target); // csiration
        int end = findLast(nums, target, start); // Hawk
        return new int[]{start, end};
    }

    public int findFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) { // 3個(含)以上元素才進來用二分法分析, 因為二分法需要{start,mid,end}三元素
            int mid = (end - start) / 2 + start;
            if (nums[mid] < target) { // 中間值比目標值小
                start = mid; // 下次應從2分後的 右半部開始找
            } else {
                end = mid;   // 下次應從2分後的 左半部開始找
            }
        }
        if (nums[start] == target) return start; // 因為是findFirst, 先檢查start
        if (nums[end] == target) return end; // 2個元素時,無法二分,故直接暴力法比對
        return -1; // 沒找到
    }
    // csiration
    // 與findFirst差異, 先檢查end再start
    // 因為是sorted array, 只要從後找的到, 即是end, 因為sorted過的值不會中斷, ex: ...787.....是不可能的
    public int findLast(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) { // 3個(含)以上元素才進來用二分法分析, 因為二分法需要{start,mid,end}三元素
            int mid = (end - start) / 2 + start;
            if (nums[mid] > target) { // Diff: 變大於符號
                end = mid; //  Diff: 先檢查end再start
            } else {
                start = mid;
            }
        }
        if (nums[end] == target) return end; //  Diff: 先檢查end再start
        if (nums[start] == target) return start;
        return -1;
    }

    // Hawk algorithm: find repeat count in the sorted array 暴力法, O(n)
    public int findLast(int[] nums, int target, int start) {
        int repeatCount = 0;
        if(nums.length - 1 == start) // start at num end
            return start;
        while(nums[start+1+repeatCount] == target) {
            repeatCount++;
            if((start+repeatCount) == nums.length -1 ) // idx  to num end
                return start+repeatCount;
            if((start+repeatCount) > nums.length -1 )
                return start;
        }
        return start+repeatCount;
    }
}
