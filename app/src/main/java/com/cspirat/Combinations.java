package com.cspirat;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Combinations
 * Creator : Edward
 * Date : Aug, 2017
 * Description : TODO
 */
public class Combinations {
    public static void main(String[] args) {
        List<List<Integer>> res = combine( 4, 2);
        for (List<Integer> l : res)
            System.out.println(l);
    }
    /**
     * 77. Combinations
     * Given two integers nums and count, return all possible combinations of count numbers out of 1 ... nums.

     For example,
     If nums = 4 and count = 2, a solution is:

     [
        [2,4],
        [3,4],
        [2,3],
        [1,2],
        [1,3],
        [1,4],
     ]

     ANS:
     [[1,2],[1,3],[1,4],
      [2,3],[2,4],
     [3,4]]

     time : O(nums^min{count,nums-count})
     space : O(nums);
     http://stackoverflow.com/questions/31120402/complexity-when-generating-all-combinations
     * @param nums
     * @param count
     * @return
     */

    static public List<List<Integer>> combine(int nums, int count) {
        List<List<Integer>> res = new ArrayList<>();
        dfs_recur(res, new ArrayList<>(), nums, count, 1);
        return res;
    }
    /*
       If nums = 4 and count = 2, a solution is:
             LOOP1    LOOP2
                 count-1
                 shift+1
                       |--> [1,2]  dfs_recur(nums,count-1,shift+1)
               |---[1]<+--> [1,3]  dfs_recur(nums,count-1,shift+1)
               |       |--> [1,4]  dfs_recur(nums,count-1,shift+1)
               |
               |
               |---[2]<+--> [2,3]  dfs_recur(nums,count-1,shift+1)
               |       |--> [2,4]  dfs_recur(nums,count-1,shift+1)
  {1,2,3,4}    |
               |
               |---[3]<+
               |       |--> [3,4]  dfs_recur(nums,count-1,shift+1)
               |
               |
               |
               |---[4]<+ BREAK! since (i+1 > nums)



     */

    // 不使用visited Flag 的DFS, 注意這題的答案規則, 很規律
    static public void dfs_recur(List<List<Integer>> res, List<Integer> list, int nums, int count, int shift) {
        if (count == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = shift; i <= nums; i++) {
            list.add(i);
            dfs_recur(res, list, nums, count-1, i+1); // KEY: 注意此處的輸入參數
            list.remove(list.size() - 1);
        }
    }
}
