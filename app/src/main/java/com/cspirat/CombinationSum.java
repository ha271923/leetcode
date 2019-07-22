package com.cspirat;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CombinationSum
 * Creator : Edward
 * Date : Aug, 2017
 * Description : TODO
 */
public class CombinationSum {

    /**
     * 39. Combination Sum
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
     * find all unique combinations in C where the candidate numbers sums to T.

     The same repeated number may be chosen from C unlimited number of times.

     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [2, 3, 6, 7] and target 7,
     A solution set is:
     [
       [7],
       [2, 2, 3]
     ]

     time : O(2^n)
     space : O(n)
     * @param candidates
     * @param target
     * @return
     */
    // Tips: Backtrack
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        helper(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if (target < 0) // 6a.跳出条件
            return;
        if (target == 0) {
            res.add(new ArrayList<>(list)); // 6b.答案, 完成(往回退一步)条件
            return;
        }

        for (int i = start; i < candidates.length; i++) { // 1. 遍历池子中的数
            // 2. 取过的数不再取
            // TODO: 因此題取过的数仍可再取,所以不用remove

            list.add(candidates[i]); // 3. 取出一个数
            helper(res, list, candidates, target - candidates[i], i); // 4. 进行下一个位置的取数，pos不須+1, 因為此題:取过的数仍可再取
            list.remove(list.size() - 1); // 5. 重要！！遍历过此节点后，要回溯到上一步，因此要把加入到结果中的此点去除掉！
        }
    }
}
