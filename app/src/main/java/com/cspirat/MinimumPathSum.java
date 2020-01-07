package com.cspirat;

import com.hawk.leetcode.Basic.DP_utils;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MinimumPathSum
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 64. Minimum Path Sum
 */
public class MinimumPathSum {
    /**
     * Given a m x n grid filled with non-negative numbers,
     * find a path from top left to bottom right which minimizes the sum of all numbers along its path.

     Note: You can only move either down or right at any point in time.

     Example 1:
     [[1,3,1],
     [1,5,1],
     [4,2,1]]
     Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.

     time : O(m * n)
     space : O(1)

     * @param grid
     * @return
     */
    /*
        Input:
        [
          [1,3,1],
          [1,5,1],
          [4,2,1]
        ]
     */
    public static void main(String[] args) {
        int[][] input =
        { { 1 , 3 , 1 },
          { 1 , 5 , 1 },
          { 4 , 2 , 1 } };

        int[][] input2 =
        { { 1, 2, 3 },
          { 4, 5, 6 },
          { 7, 8, 9 } };
        System.out.println(minPathSum1(input));
    }

    static public int minPathSum1(int[][] grid) {
        int[][] DP = grid;
        int W = DP[0].length;
        int H = DP.length;
        // 暴力法, 把每一格答案掃出來
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (y == 0 && x != 0) // 往右走到底,除[0][0]以外
                    DP[0][x] += DP[0][x - 1];
                if (y != 0 && x == 0) // 往下走到底,除[0][0]以外
                    DP[y][0] += DP[y - 1][0];

                if (y != 0 && x != 0) { // 從上往下走 或 從左往右走 哪個較近?
                    DP[y][x] += Math.min(DP[y - 1][x], DP[y][x - 1]);
                }
            }
        }
        DP_utils.print(DP);
        return DP[H - 1][W - 1]; // 右下角
    }
}
