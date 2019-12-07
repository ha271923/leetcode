package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : JumpGame
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class JumpGame {
/*
    i= 0 1 2 3 4
    Q=[3,2,1,0,4] STEPS==idx
START| 3 +      |   1
     | 3 + +    |   1
     | 3 + + +  |   1
     |   2 +    |   2
     |   2 + +  |   2
     |     1 +  |   3
     |       0  |   3
     |         X|
     |          |GOAL

 KEY: 最大範圍 = 當前最佳位置 + 接下來可前進的最大範圍

* */

// Question: 類似跳棋,  [3, 2, 1, 0, 4] 第一個3代表一開始最多可以跳1或2或3格,
//           若跳1格,會跳到2位置,再跳1格,再跳1格就到達0, 止步於0, 無法到達終點
//           若跳2格,會跳到1位置,再跳1格, 止步於0, 無法到達終點
//           若跳3格,會跳到0位置, 止步於0, 無法到達終點
// Tips: 跳超過也可以
// Ans: 這個題只問我們是否跳的到終點
    public static void main(String[] args) {
        JumpGame j = new JumpGame();
        int[] A = {3, 2, 1, 0, 4};
        int[] B = {2, 3, 1, 1, 4};
        int[] C = {0};
        int[] D = {2, 5, 0, 0};
        System.out.println(j.canJump(A)); // F
        System.out.println(j.canJump(B)); // T
        System.out.println(j.canJump(C)); // T
        System.out.println(j.canJump(D)); // T
    }

    /**
     * 55. Jump Game
     * For example:
     nums = [2,3,1,1,4], return true.

     nums [3,2,1,0,4], return false.

     time : O(n)
     space : O(1)
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) // KEY: 代表就算後面還有max+N個數組, 但是你最多只能走到max步, 就停了
                return false;
            max = Math.max(nums[i] + i, max); // 找到更多步了
        }
        // 全部數組都掃完了!
        return true;
    }
}
