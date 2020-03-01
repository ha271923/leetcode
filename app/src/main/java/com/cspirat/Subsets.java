package com.cspirat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 28/07/2017.
 */
public class Subsets {
    public static void main(String[] args) {
        int[] inputs = {1,2,3};
        List<List<Integer>> res = subsets(inputs);
        System.out.println(res);
    }

    /**
     * 78. Subsets
     * Given a set of distinct integers, nums, return all possible subsets.

     Note: The solution set must not contain duplicate subsets.

     For example,
     If nums = [1,2,3], a solution is:
     [
       [3],
       [1],
       [2],
       [1,2,3],
       [1,3],
       [2,3],
       [1,2],
       []
     ]

     test : [1,2,3]

     []
     [1]
     [1, 2]
     [1, 2, 3]
     [1, 3]
     [2]
     [2, 3]
     [3]

     1 — 2 - 3
     |   |
     2   3
     |
     3



     time : O(2^n);
     space : O(n);
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;

        DFS_recur(res, new ArrayList<>(), nums, 0);
        return res;
    }

    public static void DFS_recur(List<List<Integer>> res, List<Integer> answer, int[] nums, int shift) {
        res.add(new ArrayList<>(answer));  // 加一組答案到res
        for (int i = shift; i < nums.length; i++) {
            answer.add(nums[i]); // PUSH: 每次進入遞迴前, ans加一個數字元
            DFS_recur(res, answer, nums, i + 1); // KEY: 從答案觀察出PUSH/POP與shift規則
            answer.remove(answer.size() - 1); // POP: 每次退出遞迴後, ans退一個數字元
        }
    }
}
