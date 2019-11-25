package com.cspirat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Permutations {
    /**
     * 46. Permutations
     * Given a collection of distinct numbers, return all possible permutations.
     *
     permutations 这个问题，是求一个数组的全排列，思路就是将数组当做一个池子，第一次取出一个数，然
     后在池子里剩下的数中再任意取一个数此时组成两个数，然后再在池子中剩下的数里取数，直到无数可取，
     即取完一次，形成一个排列。

     For example,
     [1,2,3] have the following permutations:
     [
       [1,2,3],
       [1,3,2],
       [2,1,3],
       [2,3,1],
       [3,1,2],
       [3,2,1]
     ]

     time : O(n!)
     space : O(n);

     reference : http://www.1point3acres.com/bbs/thread-117602-1-1.html

     The number of recursive calls, T(n) satisfies the recurrence T(n) = T(n - 1) + T(n - 2) + ... + T(1) + T(0),
     which solves to T(n) = O(2^n). Since we spend O(n) time within a call, the time complexity is O(n2^n);

     * @param nums
     * @return
     */
    // https://github.com/billryan/algorithm-exercise/blob/master/zh-hans/exhaustive_search/permutations.md
    // https://blog.csdn.net/tuantuanls/article/details/8717262
    public static void main(String[] args) {
        int[] input = {1,2,3}; // Ans: 6 種排列組合
        List<List<Integer>> ret;
        // ret = permute_list(input);
        ret = permute_stack(input);
        // ret = permute_swap(input);
        // ret = permute_recursive(input);
        System.out.println("ANS: ret.size()="+ret.size());
    }

/*
 A. 重新填值 = permute_list()

                  /--add(2)--> [1,2] ---add(3)--> [1,2,3]
                 /
             /[1]----add(3)--> [1,3] ---add(2)--> [1,3,2]
            /
           /      /--add(1)--> [2,1] ---add(3)--> [2,1,3]
  {1,2,3}  ---[2]
           \      \--add(3)--> [2,3] ---add(1)--> [2,3,1]
            \
             \[3]----add(1)--> [3,1] ---add(2)--> [3,1,2]
                 \
                  \--add(2)--> [3,2] ---add(1)--> [3,2,1]
*/
    // time : O(n! * n)  ,  space : O(n);
    public static List<List<Integer>> permute_list(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        dfs(res, new ArrayList<>(), nums);
        return res;
    }

    //    void add (int index, E element)
    // boolean add (E e) <------------ call it to add value at end
    //       E remove (int index) <--- call it to remove one of element
    // boolean remove (Object o)
    public static void dfs(List<List<Integer>> res, List<Integer> list, int[] nums) {
        System.out.println(list);
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return; // return-1: 一旦字數量達標時 ++
        }
        // Process
        for (int i = 0; i < nums.length; i++) {
            // 以上是上半場開始 {
            if (list.contains(nums[i])) // KEY: 列舉不同數
                continue;  // O(n)
            list.add(nums[i]);               // ++ add(value)
            // 以上是上半場結束 }
            dfs(res, list, nums);            // recursive
            // 以下是下半場開始 {
            list.remove(list.size() - 1); // -- remove(LastE)
            // 以下是下半場結束 }
        }
        // return-2: 一旦字數量達標時 --
    }

/*
 B: 倆倆互換 = permute_swap()
   PS: sw(l,r) == swap l and r value

                          /- sw(2,2) -> [1,2,3]
                         /
             /----[1,2,3]--- sw(2,3) -> [1,3,2]
         sw(1,1)
        /                 /- sw(2,2) -> [2,1,3]
 [1,2,3]-sw(1,2)--[2,1,3]
        \                 \- sw(2,3) -> [2,3,1]
         sw(1,3)
             \----[3,2,1]--- sw(2,2) -> [3,1,2]
                         \
                          \- sw(2,3) -> [3,2,1]

 */
    // time : O(n!)  ,   space : O(n);
    public static List<List<Integer>> permute_swap(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        dfs_offset(res, 0, nums);
        return res;
    }
    public static void dfs_offset(List<List<Integer>> res, int start, int[] nums) {
        System.out.println(res);
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            dfs_offset(res, start + 1, nums);
            swap(nums, start, i);
        }
    }
    public static void swap(int[] nums, int l, int r) {
        System.out.println("+++ nums+"+Arrays.toString(nums)+" Swap("+l+","+r+")");
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
        System.out.println("--- nums+"+ Arrays.toString(nums)+" Swap("+l+","+r+")");
    }


/*
    C: 遞歸 = recursive
        與題解1基於子集的模板不同，這裡我們直接從全排列的數學定義初始出發，要
        求給定數組的全分開，可將其模擬為某個袋子裡有編號為1到n的球，將其放入n
        個不同的盒子怎麼放？基本思路就是從袋子裡逐個拿球放入盒子，直到袋子裡的
        球拿完為止，拿完時即為一種放法。
*/
    static public List<List<Integer>> permute_recursive(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> numsList = new ArrayList<>();

        if (nums == null) {
            return res;
        } else {
            // convert int[] to List<Integer>
            for (int item : nums)
                numsList.add(item);
        }

        if (nums.length <= 1) {
            res.add(numsList);
            return res;
        }

        for (int i = 0; i < nums.length; i++) {
            int[] numsNew = new int[nums.length - 1];
            System.arraycopy(nums, 0, numsNew, 0, i);
            System.arraycopy(nums, i + 1, numsNew, i, nums.length - i - 1);

            List<List<Integer>> resTemp = permute_recursive(numsNew);
            for (List<Integer> temp : resTemp) {
                temp.add(nums[i]);
                res.add(temp);
            }
        }
        return res;
    }


/*

  A-2: 重新填值 = stack

 */
    static public List<List<Integer>> permute_stack(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        dfs2(result, new Stack<Integer>(), nums);
        return result;
    }

    static private void dfs2(List<List<Integer>> res, Stack<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.push(nums[i]);
            dfs2(res, list, nums);
            list.pop();
        }
    }


}
