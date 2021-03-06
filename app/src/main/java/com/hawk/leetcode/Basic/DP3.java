package com.hawk.leetcode.Basic;

import com.utils.DP_utils;

/**
 *  例題3:矩陣取數問題
 *  一個N*N矩陣中有不同的正整數，經過這個格子，就能獲得相應價值的獎勵，
 *  從左上(start)走到右下(end)，只能向下或向右走(有去無回)，求能夠獲得的最大價值。
 *
 *  例如：3 * 3的方格。
 *
 *  1 3 3
 *  2 1 3
 *  2 2 1
 *
 * 能夠獲得的最大價值為：11 = 1+3+3+3+1。
 */

/*
 * yLen = kitten  = 6
 * xLen = sitting = 7
 *           xLen>=x=[0][x] [0][1] [0][2] [0][3]  width
 * yLen>=y=dp[y][0] |  0   |  0   |  0   |  0   |
 *           [1][0] |  0   |  1   |  3   |  5   |
 *           [2][0] |  0   |  4   |  5   |  7   |
 *           [3][0] |  0   |  7   | 10   | 11   |
 *           height
 *      Ans: maxCoins=11
 * */
public class DP3 {
    public static void main(String[] args) {
        // int[] coinArr = {1,3,3,2,1,3,2,2,1};
        // int[] coinArr = {1,2,2,3,1,2,3,3,1};
        int[][] coinArr = {
                {1,3,3},
                {2,1,3},
                {2,2,1}
        };
        int ret = maxCoins(coinArr);
        System.out.println("maxCoins=" + ret);
    }

    public static int maxCoins(int[][] coinArray) {

        int xLen = coinArray[0].length;
        int yLen = coinArray.length;
        int[][] dp = new int[yLen + 1][xLen + 1]; // 一開始的new int[xLen+1][yLen+1], 是為了讓下面dp的[x-1][y-1]左上取值算法能通用所有情況
        // for (int y = 0; y <= yLen; y++) {
        //     dp[y][0] = 0;
        // }
        // for (int x = 0; x <= xLen; x++) {
        //     dp[0][x] = 0;
        // }
        // 填充dp矩阵
        int coin = 0;
        for (int x = 1; x <= xLen; x++) { // 往右走一步
            for (int y = 1; y <= xLen; y++) { // 往下走一步
                // KEY: 本題的algorithm +++++++++++++
                coin = coinArray[x-1][y-1]; // 為了讓dp的左上取值算法[x-1][y-1]能通用所有情況, 所以一開始的new int[xLen+1][yLen+1]
                // 如果走到這一步累積的錢最多是dp[y][x] = 比較 max( dp[y][x-1]若從左邊往右走 , dp[y-1][x]若從上面走下來 ) + 現在的錢
                dp[y][x] = Math.max(dp[y][x-1], dp[y-1][x]) + coin;
                // KEY: 本題的algorithm -------------
            }
        }

        DP_utils.print(dp);
        return dp[yLen][xLen]; // 可獲得最多錢的全部紀錄矩陣dp[][]
    }
}