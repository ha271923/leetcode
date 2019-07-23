package com.cspirat;

import java.util.HashMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CombinationSumIV
 * Creator : Edward
 * Date : Jan, 2018
 * Description : 377. Combination Sum IV
 */
// https://blog.csdn.net/ffj0721/article/details/84065758
//  这里数组的元素可以重复，且组合的元素也能相同，但是顺序要不同。
//  一开始当然就是无脑循环了，然后满足条件就加上一，结果就是超时……
//  说明这么暴力不行的……
//  后来一想，无脑循环就相当于每一次都从头进行循环算了一遍，很多都是重复的计算。
//  对了，DP 浮现在了脑中，在那飘啊飘
public class CombinationSumIV {
    /**
     * nums = [1, 2, 3]
     target = 4

     The possible combination ways are:
     [
       [1, 1, 1, 1],
       [1, 1, 2],  注意: 數組相同, 順序不同, 視為不同答案
       [1, 2, 1],
       [1, 3],
       [2, 1, 1],
       [2, 2],
       [3, 1]
     ]
     Note that different sequences are counted as different combinations.

     Therefore the output is 7. 這才是要的答案, 找組合的"數量"

     1, DP : res[i] += res[i - num];
     2, DFS + Memoization : HashMap<Integer, Integer>

     * @param nums
     * @param target
     * @return
     */
    // Tips: output代表意義
    // time : (n * k) space : O(k)
    public int combinationSum4(int[] nums, int target) {
        int[] res = new int[target + 1];
        res[0] = 1;
        for (int i = 1; i < res.length; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    res[i] += res[i - num];
                }
            }
        }
        return res[target];
    }

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
     2. hashMap, 當只要數量不需數組時, 這時候不需往下列舉出所有數組, 因為答案不需要知道數組
     */
    //time : < O(2^n) space : O(n)
    public int combinationSum42(int[] nums, int target) {
        if (nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        return helper(nums, target, map);
    }

    private int helper(int[] nums, int target, HashMap<Integer, Integer> map) {
        if (target < 0) // 6a.跳出条件
            return 0;
        if (target == 0) // 6b. 獲得此一微小問題答案, 完成(往回退一步)条件
            return 1;
        // KEY: 因為此例可以使用重複的數, 所以列舉次數是n*n=n^2, 要減少時間複雜度, 因題
        // 目要的輸出值是數量, 可利用hashmap來SAVE/LOAD checkpoint, 只要前半數組不同, 後半
        // 數組只要有同值存在hashmap中, 就不需往下列舉
        if (map.containsKey(target)) { // ??? <K,V> 意義??
            return map.get(target);    // LOAD
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += helper(nums, target - nums[i], map);
        }
        map.put(target, res); // SAVE
        return res;
    }
}
