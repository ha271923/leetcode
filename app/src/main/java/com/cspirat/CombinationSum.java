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
    /**
     *
     A. 數字可重複時, 此例recursive深度最高為 4, 迴圈數 4^2
       r0=[2, 3, 6, 7]
       r1=[2, 3, 6, 7]
       r2=[2, 3, 6, 7]
       r3=[2, 3, 6, 7]

     B. 數字不可重複時, 此例recursive深度最高為 4, 迴圈數 4!
       r0=[2, 3, 6, 7]
       r1=[   3, 6, 7]
       r2=[      6, 7]
       r3=[         7]

     PS:適當的剪枝可以減少不必要的計算
       1. if (target < 0)
       2. hashMap, 當只要數量不需數組時, 這時候不需往下列舉出所有數組
     */
    // Tips: Backtrack
    // Tips: 思考上, 此問題須不斷地列舉出所有數組, 數組內又包含一個一個的數字, 所以最小的問題是數字, 次問題是數組, 原問題是全數組
    //       1. 我們需要一個迴圈一一列舉所有的數字(暴力法)
    //       2. 列舉出來的數字, 需篩選掉不合題意的
    //       3. 原問題\子問題\孫問題\曾孫問題\.....
    //       4. 把不必要的剪枝也可加速
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
            res.add(new ArrayList<>(list)); // 6b. 獲得此一微小問題答案, 完成(往回退一步)条件
            return;
        }

        for (int i = start; i < candidates.length; i++) { // 1. 依序列舉所有的數
            // 2. 取过的数不再取
            // TODO: 因此題取过的数仍可再取,所以不用remove

            list.add(candidates[i]); // 3. 取出一个数
            helper(res, list, candidates, target - candidates[i], i); // 4. 持續縮小問題, 此題往下遍歷, 並提供正確參數, ，pos不須+1, 因為此題:取过的数仍可再取
            list.remove(list.size() - 1); // 5. 重要！！遍历过此节点后，要回溯到上一步，因此要把加入到结果中的此点去除掉！
        }
    }
}
