package com.freetymekiyan.algorithms.level.hard;

import com.hawk.leetcode.Basic.DP_utils;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example:
 * Given array jumpArr = [2,3,1,1,4] , total steps= 4
 * 
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the last index.)
 * 
 * Tags: Array, Greedy, DP
 */
/*
    i= 0 1 2 3 4
    Q=[2,3,1,1,4] STEPS==idx
START| 2 +      |   1
     | 2 + +    |   1
     |   3 +    |   2
     |   3 + +  |   2
     |   3 + + +|   2  <- Ans: Reach at 2 steps
     |     1 +  |   3
     |       1 +|   3
     |         4|+ + + +
     |          |GOAL

 KEY: 最大範圍 = 當前最佳位置 + 接下來可前進的最大範圍
      兩個獨立數組區間,ex:[2,3]={1}+{1,2,3}=max=4 或 =(2}+{1}=max=3 , 比較後4>3
      所以當前最佳位置選{1}+接下來可前進的最大範圍是{3}

 ps: prevPos不須留意, 因為跳躍範圍非常數, 而是可以選擇的

* */

// Question: 類似跳棋,  [2,3,1,1,4] 第一個2代表一開始最多可以跳1或2格,
//           若跳2格,會跳到1位置,再跳1格,再跳1格就到達終點4, 共花 3步
//           若跳1格,會跳到3位置,再跳3格,就到達終點4, 共花 2步, Ans: min=2步
// Tips: 跳超過也可以
// Ans: 這個題只要我們求跳數，怎麼跳，最後距離是多少，都沒讓求的。概念上是爭取每跳最遠的greedy
// https://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
class JumpGame2 {
    public static void main(String[] args) {
        // int[] jumpArr = {2,3,1,1,4};
        int[] jumpArr = {5,1,1,2,1,1,1,1,8,1,1,1,1,1}; // A[0]~[13] Ans: 2, 確認算法的數據觀察遠見
        // int[] jumpArr = {5,1,1,10,1,1,1,1,1,1,1,1,1,1}; // A[0]~[13] Ans: 2, 確認算法的數據觀察遠見
        int ret;
        ret = jump_1Loop(jumpArr);
        // ret = jump_2ptr(jumpArr);
        // ret = jump_DP(jumpArr);
        // ret = jump_Greedy(jumpArr);
        System.out.println("ANS: minSteps="+ret);
    }
    
    /**
     * Use curMaxArea to store how far we already can reach
     * Compare curMaxArea with distance:
     *   If we run out of it, update and add 1 more step to result
     *   Return if jumped is already bigger than or equal to the length
     *   Use allMaxNext to store how far we can reach for the next step
     */
    // Tips: position == index of nums[]
    // Q: 為啥要用jumped，直接用maxNext跳不就行了。直接用maxNext跳那每次都是跳最遠的，但是最優路徑不不一定是這樣。
    // Time: O(n), Space: O(1)
    static public int jump_1Loop(int[] nums) {
        if(nums == null || nums.length <2 )
            return 0;

        int res = 0;
        int distance = nums.length -1 ;
        // Tips: 以下兩個變數均是累積值而非當前值: curMaxArea, allMaxNext
        int curMaxArea = 0; // how far we already can reach
        int allMaxNext = 0; // how far can we reach for next res


        System.out.println("distance="+distance);

        // LOOP: 把兩個目標放在一個for中, 這樣就可以達到O(n)的時間複雜度, 已知每一格都有大於1的前進步數,
        //       所以每次的i++, 代表就算當前最大可前進3步, 我們也只前先進1步, 以此掃描最大步數的最小數量組合數組
        // INDEX: i有兩個意義: current Position 與 index of next Max steps, i++代表僅一步一步前進
        //
        for (int i = 0; i < distance; i++) { // Tips: (nums.length-1) is all steps from begin to goal.
            System.out.println("++  i="+i+"  curMaxArea="+curMaxArea+"  allMaxNext="+allMaxNext+"  res="+res);
            if (i > curMaxArea) {  // 已經超過此位置的最遠可達範圍
                res++; // 需要再走一步, 才可能到達
                curMaxArea = allMaxNext; // 新的一步最遠可以走到
                if (curMaxArea >= distance) { // 如果新的一步可以走的比總距離還遠 int[] jumpArr = {2,4,1,1,4};因為distance=4, 若數組中有其中一個數, 直接大於distance, 那就不用再往後搜尋了
                    System.out.println("curMaxArea >= nums.length");
                    return res;
                }
            }
            allMaxNext = Math.max(allMaxNext, i + nums[i]); // KEY: 當前哪個跳比較遠, 就讓他當下一步, i+nums[i]=現在位置+這次要跳的步數
            System.out.println("--                     allMaxNext="+allMaxNext+"  res="+res);
        }
        return res;
    }

    /*
Wihtin farest we can go, renew farest we can go, renew number of steps.
http://blog.csdn.net/havenoidea/article/details/11853301
    图解：
    http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
 */
    // Greedy. Within max range we can jump to, do another loop to renew the max we can go.
    static public int jump_Greedy(int[] nums) {
        if (nums == null || nums.length <= 1)
            return 0;

        int step = 0;
        int i = 0;
        int curMaxArea = 0;
        int allMaxNext = 0;
        int distance = nums.length-1;

        while (i < nums.length) {
            if (curMaxArea >= distance) {
                break;
            }
            while (i <= curMaxArea) {
                allMaxNext = Math.max(allMaxNext, i + nums[i]); //
                i++;
            }
            curMaxArea = allMaxNext;
            step++;
        }
        return step;
    }

    /*
    Thinking process:
    0.   Use two pointers pStart and pEnd to track the potential locations we can move to.
         Consider a range from current spot to the farthest spot: try to find a max value from this range, and see if the max can reach the tail of array.
          -If no max can read the tail of array, that means we need to move on. At this point, let pStart = pEnd + 1. At same time, move pEnd to the max spot we can go to. Since pEnd moves forward, we could step++
          -If max reach the tail of array, return the steps.
    */
    // Time: O(n), Space: O(1)
    static public int jump_2ptr(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        int start = 0, end = 0, steps = 0;
        while (end < n - 1) {
            steps++; //Cound step everytime when pEnd is moving to the farthest.
            int farthest = 0;
            //Find farest possible and see if reach the tail
            for (int i = start; i <= end; i++) {
                farthest = Math.max(farthest, i + nums[i]);
                if (farthest >= n - 1) {
                    return steps;
                }
            }
            //Re-select pointer position for start and end
            start = end + 1;
            end = farthest;
        }
        return 0;  //This is the case where no solution can be found.
    }

    // DP, coordinate DP, timeout, O(n^2)
    static public int jump_DP(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] != Integer.MAX_VALUE && j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        DP_utils.print(dp);
        return dp[n - 1];
    }

}
