package com.cspirat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PermutationsII
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class PermutationsII {
    /**
     * 47. Permutations II
     * For example,
     [1,1,2] have the following unique permutations:

     [
     [1,1,2],
     [1,2,1],
     [2,1,1]
     ]


     * @param nums
     * @return
     */
    // Q: 相較於Permutations, 列舉的元素可能是重複元素
    public static void main(String[] args) {
        List<List<Integer>> res = permute_list(new int[]{1, 1, 2});
        // List<List<Integer>> res = permute_swap(new int[]{1, 1, 2});
        for (List<Integer> l : res)
            System.out.println(l);
    }

    // time : O(n!) space : O(n);
    static public List<List<Integer>> permute_list(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return  res;
        Arrays.sort(nums); // KEY: 排序是為了後面nums[i] == nums[i - 1]的algorithm算法
        dfs(res, new ArrayList<>(), nums, new boolean[nums.length]); // boolean[]  作為列舉紀錄資訊
        return res;
    }

    static public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            if (  used[i] )   // KEY: 元素已列舉過
                continue;
            if ( i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) // KEY: 難理解??? 試著把此判斷式拿掉以了解用途
            {
                System.out.println("  nums["+i+"]="+nums[i]+ " nums["+(i-1)+"]="+nums[i - 1]);
                continue;
            }
            list.add(nums[i]);
            used[i] = true;  // Tips++: 代表nums[i]此元素已列舉過used[i]=true
            dfs(res, list, nums, used);
            used[i] = false; // Tips--: 代表nums[i]消除此元素列舉used[i]=false
            list.remove(list.size() - 1);
        }
    }

    // ----------------------------------------------------------------------------------

    // time : O(n!) space : O(n);
    static public List<List<Integer>> permute_swap(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return  res;
        Arrays.sort(nums); // KEY: 排序是為了後面
        dfs_offset(res, nums, 0);
        return res;
    }
    static public void dfs_offset(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (isUsed(nums,start, i))
                continue;
            swap(nums, start, i);
            dfs_offset(res, nums, start + 1);
            swap(nums, start, i);
        }
    }

    static public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static public boolean isUsed(int[] nums, int i, int j) {
        for (int x = i; x < j; x++) {
            if (nums[x] == nums[j])
                return true;
        }
        return false;
    }
}
