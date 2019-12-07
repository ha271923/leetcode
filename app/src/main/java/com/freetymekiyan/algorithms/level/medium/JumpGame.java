package com.freetymekiyan.algorithms.level.medium;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that
 * position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * For example:
 * A = [2,3,1,1,4], return true.
 * <p>
 * A = [3,2,1,0,4], return false.
 * <p>
 * Tags: Array, Greedy, DP
 */
class JumpGame {
    public static void main(String[] args) {
        JumpGame j = new JumpGame();
        int[] A = {3, 2, 1, 0, 4}; // F
        int[] B = {2, 3, 1, 1, 4}; // T
        int[] C = {0};             // T
        int[] D = {2, 5, 0, 0};    // T
        System.out.println(j.canJump(A));
        System.out.println(j.canJump(B));
        System.out.println(j.canJump(C));
        System.out.println(j.canJump(D));
    }

    /**
     * Dynamic Programming
     * Keep track of the maximum of jumps we left
     * Initialized as nums[0]
     * Traverse from second to second last
     * Reduce 1 every time we jump
     * maxJump should be max of maxJump - 1 and nums[i]
     * if maxJump reduces to zero, we are not able to reach anymore
     */
    public boolean canJump(int[] nums) {
        // Corner cases
        if (nums == null || nums.length == 0)
            return false;
        if (nums.length == 1) // 當輸入是 int[] C = {0}; 的情況
            return true; // already reach last index
        if (nums[0] == 0) // 當輸入是 int[] C = {0, 9, 7, 5}; 的情況
            return false; // note its important cause we start from 1

        // Algorithm
        // 只要記住最大值即可, 例如: 3 = {1,2,3} 可選擇三種跳躍情況, 所以只要記住最大3,不須記住與下一部可產生最大值的(2再去加上未來的下一步)
        int maxJump = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            maxJump = Math.max(maxJump - 1, nums[i]);
            if (maxJump == 0)
                return false;
        }
        return true;
    }

    public boolean canJump2(int[] nums) {
        int len = nums.length;
        int i = 0;
        for (int reach = 0; i < len && i <= reach; i++) {
            reach = Math.max(nums[i] + i, reach);
            if (reach >= len - 1)
                return true;
        }
        return false;
    }
}
