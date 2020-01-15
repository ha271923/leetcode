package com.cspirat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ClimbingStairs
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 70. Climbing Stairs
 */
public class ClimbingStairs {
    /**
     * You are climbing a stair case. It takes n steps to reach to the top.

     Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

     Note: Given n will be a positive integer.


     Example 1:

     Input: 2
     Output:  2
     Explanation:  There are two ways to climb to the top.

     1. 1 step + 1 step
     2. 2 steps

     time : O(n)
     space : O(n)/O(1)

     * @param n
     * @return
     */
    public static void main(String[] args) {
        System.out.println("-- climbStairs_down(n~1) ----------------------------------------");
        for(int i=1;i<=10;i++)
            System.out.println("階梯="+i+"   走法="+climbStairs_down(i));
        System.out.println("-- climbStairs_up(1~n)   -----------------------------------------");
        for(int i=1;i<=10;i++)
            System.out.println("階梯="+i+"   走法="+climbStairs_up(i));
        System.out.println("--------------------------------------------------------------");
        System.out.println("階梯="+10+"   走法="+climbStairs_permutation(10));
    }
/*
階梯=2   走法=2
階梯=3   走法=3
階梯=4   走法=5
階梯=5   走法=8
階梯=6   走法=13
階梯=7   走法=21
階梯=8   走法=34
階梯=9   走法=55
階梯=10   走法=89

===============================================================================

                    選擇走二步後/- 1 走法一 2->1
                         /-- 2
                        / (n-2)
 3階=(1種走法+2種走法)  +            /- 1 走法二 1->1->1
    =走法一+走法二+走法三 \      /- 1
    =3種走法              \-- 1
                          (n-1)\- 2 走法三 1->2
                     選擇走一步後
===============================================================================
                                                    /- 1 走法一 1->2->1
                                              /-- 2
                                  選擇走一步後 / (n-2)
                                     /(4-1)=3+          /- 1 走法二 1->1->1->1
                                    / (n-1)  \      /- 1
                                   /          \-- 1
4階=(3階全走法+2階全走法) = 5種走法  +            (n-1)\- 2 走法三 1->1->2
                                   \
                                    \              /- 1 走法四 2->1->1
                                     \        /- 1
                                      \(4-2)=2
                                       (n-2)  \- 2 走法五 2->2
                                   選擇走二步後
===============================================================================
每次的一開始, 依照題目, 可以選擇走一步(n-1)或走二步(n-1), 每走一次又需再做一次重複的抉擇(遞迴)
當n=1時, 只有唯一的一種走法, 當n=2時, 有兩種走法, 最後時一步時, 只會剩下走一階或走兩階  if (n <= 2)
所以遞迴的最後一層是走一階或走兩階,取決於需要走一階或走兩階, climbStairs(int n){... return n}
那最後倒數第二步(遞迴倒數第二層)的走法, 當然需累加走一階情況下, 與走兩階時的走法變化
遞迴最上層就是這些走法到退回來的加總
 */
    // 由最上階(n)往最下階(1)回推
    static public int climbStairs_down(int n) {
        if (n <= 2) {
            return n;
        } else {
            return climbStairs_down(n - 1) + climbStairs_down(n - 2);
        }
    }

    // 由最下階(1)往最上階(n)走
    static public int climbStairs_up(int n) {
        if (n <= 1)
            return 1;
        int oneStep = 1, twoStep = 1, res = 0;
        for (int i = 2; i <= n; i++) { // LOOP: 從兩階開始, 因res=oneStep+twoStep, 2=1+1
            res = oneStep + twoStep; // 當前全部的變化性=走一階的變化性+走二階的變化性
            System.out.println("res="+res+"   oneStep="+oneStep+"   twoStep="+twoStep);
            twoStep = oneStep; // 下次走兩階的變化性=上次只走一階的變化性
            oneStep = res;     // 下次走一階的變化性=上次全部的變化性
        }
        return res;
    }


    ////////////////////////////////////////////////////////////////////////////////////
    // Permutations2 方式無法解答此題, 因為元素只有次序性, 無大小比較性, 且長度不固定
    ////////////////////////////////////////////////////////////////////////////////////
    // Hawk: 以下是錯誤答案
    static public int climbStairs_permutation(int n) {
        return permute_list(n, new int[]{1,2,1,1,1,1,1,1,1}); // if n=9
    }
    // time : O(n!) space : O(n);
    static public int permute_list(int n, int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return  0;
        if(n<2)
            return 1;
        Arrays.sort(nums); // KEY: 排序是為了後面nums[i] == nums[i - 1]的algorithm算法
        dfs(n, res, new ArrayList<>(), nums, new boolean[nums.length]); // boolean[]  作為列舉紀錄資訊
        return res.size();
    }

    static public void dfs(int n, List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {

        if (n < 2) {
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
            n=n-nums[i];
            used[i] = true;  // Tips++: 代表nums[i]此元素已列舉過used[i]=true
            dfs(n, res, list, nums, used);
            used[i] = false; // Tips--: 代表nums[i]消除此元素列舉used[i]=false
            list.remove(list.size() - 1);
        }
    }

}
