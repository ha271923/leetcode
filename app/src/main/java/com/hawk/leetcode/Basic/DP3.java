package com.hawk.leetcode.Basic;

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
        for (int y = 0; y <= yLen; y++) {
            dp[y][0] = 0;
        }
        for (int x = 0; x <= xLen; x++) {
            dp[0][x] = 0;
        }
        // 填充dp矩阵
        int coin = 0;
        for (int x = 1; x <= xLen; x++) { // 往右走
            for (int y = 1; y <= xLen; y++) { // 往下走
                coin = coinArray[x-1][y-1]; // 為了讓dp的左上取值算法[x-1][y-1]能通用所有情況, 所以一開始的new int[xLen+1][yLen+1]
                dp[y][x] = Math.max(dp[y][x-1], dp[y-1][x]) + coin; // 小問題的結果更新於dp
            //  新的錢    = 哪邊錢較多(  往下走  或  往右走   ) + 上次的錢
            }

        }
        System.out.println("max Coins=" + dp[yLen][xLen]);
        return dp[yLen][xLen];
    }
}