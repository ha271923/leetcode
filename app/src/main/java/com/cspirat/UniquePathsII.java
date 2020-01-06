package com.cspirat;

import com.hawk.leetcode.Basic.DP_utils;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UniquePathsII
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 63. Unique Paths II
 */
public class UniquePathsII {
    /**
     * Follow up for "Unique Paths":

     Now consider if some obstacles are added to the grids. How many unique paths would there be?

     An obstacle and empty space is marked as 1 and 0 respectively in the grid.

     For example,
     There is one obstacle in the middle of a 3x3 grid as illustrated below.

     [
       [0,0,0],
       [0,1,0],
       [0,0,0]
     ]
     The total number of unique paths is 2.

     time : O(m * n)
     space : O(n)

     * @param obstacleGrid
     * @return
     */
    public static void main(String[] args) {

        int[][] obstacleGrid = new int[3][3];
        obstacleGrid[1][1] = 1;
        // int[][] obstacleGrid = new int[7][3];
        // obstacleGrid[1][1] = 1;
        // obstacleGrid[2][2] = 1;

        System.out.println(uniquePathsWithObstacles(obstacleGrid));
        System.out.println(uniquePathsWithObstacles_DP(obstacleGrid));
    }
    /*
      DP[][]儲存著匯流至此的走法有幾種, 每一個位置x,y的走法, 取決於上方可能的走法,與左方可能的走法, 的和
      因為方向只有, 右與下兩種, 所以最上一列DP[0][x]與作左一行DP[y][0]必定無其他方向匯流至此,僅唯一一種走法
                width>=w=dp[y][x-1] [y][x]
      height>=h=dp[y-1][x] |       | A1   |
                  [y  ][x] |  A2   | A1+A2|

      algorithm = DP[y][x] = A1+X2 = DP[y-1][x]+DP[y][x-1]
     */
    // Tips: 多了一個障礙陣列, 只要遇到障礙陣列, 可能走法重設為0
    // Tips: 與leetcode的uniquePaths_DP程式碼進行比較, 只有一個小差異
    static public int uniquePathsWithObstacles_DP(int[][] obstacleGrid) {
        int h = obstacleGrid.length;
        int w = obstacleGrid[0].length;

        int[][] DP = new int[h][w];
        // 初始化: 因為方向只有, 右與下兩種, 所以最上一列與作左一行必定無其他方向匯流至此
        for (int i = 0; i < h; i++) {
            DP[i][0] = 1;
        }
        for (int i = 0; i < w; i++) {
            DP[0][i] = 1;
        }
        // 開始走: algorithm = DP[y][x] = A1+X2 = DP[y-1][x]+DP[y][x-1]
        for (int y = 1; y < h; y++) { // 掃描y
            for (int x = 1; x < w; x++) { // 掃描x
                if (obstacleGrid[y][x] == 1) // KEY: 一旦發現障礙物,則此路不通, 可能走法重設為0
                    DP[y][x] = 0;
                else
                    DP[y][x] = DP[y - 1][x] + DP[y][x - 1]; // 上方可能的走法 + 左方可能的走法
            }
        }
        DP_utils.print(DP);
        return DP[h - 1][w - 1];
    }

    static public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int h = obstacleGrid.length;
        int w = obstacleGrid[0].length;
        int[] DP = new int[w];
        DP[0] = 1;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (obstacleGrid[y][x] == 1)
                    DP[x] = 0;
                else if (x > 0)
                    DP[x] += DP[x - 1];
            }
        }
        DP_utils.print(DP);
        return DP[w - 1];
    }
}
