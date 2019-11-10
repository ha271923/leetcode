package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : JumpGameII
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
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
// Ans: 這個題只要我們求跳數，怎麼跳，最後距離是多少，都沒讓求的。
public class JumpGameII {
    /**
     * 45. Jump Game II
     * For example:
     Given array A = [2,3,1,1,4]

     The minimum number of jumps to reach the last index is 2. (Jump 1 res from index 0 to 1,
     then 3 ress to the last index.)

     * @param nums
     * @return
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
// Ans: 這個題只要我們求跳數，怎麼跳，最後距離是多少，都沒讓求的。
    public static void main(String[] args) {
        // int[] jumpArr = {2,3,1,1,4};
        int[] jumpArr = {5,1,1,2,1,1,1,1,8,1,1,1,1,1}; // A[0]~[13] Ans: 2, 確認算法的數據觀察遠見
        int ret;
        ret = jump(jumpArr);

        System.out.println("ANS: minSteps="+ret);
    }
    //time : O(n) space : O(1)
    static public int jump(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        int res = 0;
        int distance = nums.length - 1;
        // Tips: 以下兩個變數均是累積值而非當前值: curMaxArea, allMaxNext
        int curMaxArea = 0;
        int allMaxNext = 0;

        // LOOP: 把兩個目標放在一個for中, 這樣就可以達到O(n)的時間複雜度, 已知每一格都有大於1的前進步數,
        //       所以每次的i++, 代表就算當前最大可前進3步, 我們也只前先進1步, 以此掃描最大步數的最小數量組合數組
        // INDEX: i有兩個意義: current Position 與 index of next Max steps, i++代表僅一步一步前進
        //
        for (int i = 0; i < distance; i++) {
            allMaxNext = Math.max(allMaxNext, i + nums[i]);
            if (i == curMaxArea) { // 已經到達最遠可達範圍
                res++; // 需要再走一步, 才可能到達
                curMaxArea = allMaxNext;
                if (curMaxArea >= distance) { // int[] jumpArr = {2,4,1,1,4};因為distance=4, 若數組中有其中一個數, 直接大於distance, 那就不用再往後搜尋了
                    return res;
                }
            }
        }
        return res;
    }

    /**
     [2,3,1,1,4]

     level = 2
     cur = 2
     max = 4
     i = 1

     * @param nums
     * @return
     */
    // time : O(n)  space : O(1)
    static public int jump2(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        int level = 0;
        int curMaxArea = 0;
        int allMaxNext = 0;
        int distance = nums.length - 1;
        int i = 0;
        while (curMaxArea - i + 1 > 0) {
            level++;
            for (; i <= curMaxArea; i++) {
                allMaxNext = Math.max(allMaxNext, nums[i] + i);
                if (allMaxNext >= distance) {
                    return level;
                }
            }
            curMaxArea = allMaxNext;
        }
        return 0;
    }
}
