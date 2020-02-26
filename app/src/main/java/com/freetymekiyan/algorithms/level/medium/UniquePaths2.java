package com.freetymekiyan.algorithms.level.medium;

import com.utils.DP_utils;

/**
 * Follow up for "Unique Paths":
 * <p>
 * Now consider if some obstacles are added to the grids. How many unique paths
 * would there be?
 * <p>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <p>
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * <p>
 * [
 *  [0,0,0],
 *  [0,1,0],
 *  [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * <p>
 * Note: m and n will be at most 100.
 * <p>
 * Tags: Array, DP
 */
class UniquePaths2 {
    public static void main(String[] args) {
        int[][] obstacleGrid = new int[3][3];
        obstacleGrid[1][1] = 1;
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    /*
     *           xLen>=x=[0][x] [0][1] [0][2] [0][3]  word2
     * yLen>=y=dp[y][0] |  2   |  1   |  1   |  0   |
     *           [1][0] |  1   |  0   |  1   |  0   |
     *           [2][0] |  1   |  1   |  1   |  1   |
     *           [3][0] |  0   |  0   |  0   |  0   |
     *            word1
     *  Ans: 2
     * */
    /**
     * DP, bottom-up approach
     * build from end point to start point
     * for the grid paths at the rth row and cth column
     * paths[r][c] = obstacleGrid[r][c] == 1 ? 0
     * : paths[r + 1][c] + paths[r][c + 1];
     */
    // Q: Now consider if some obstacles are added to the grids. How many unique paths would there be?
    //    障礙物可以多個, 初始路線棋譜請自行輸入int[][] obstacleGrid, 數字1代表障礙,出發(0,0)->目的(X,Y)
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null)
            return 0;
        int y = obstacleGrid.length;
        if (y == 0)
            return 0;
        int x = obstacleGrid[0].length;
        int[][] dp = new int[y + 1][x + 1];

        dp[y - 1][x] = 1;
        for (int r = y - 1; r >= 0; r--) {
            for (int c = x - 1; c >= 0; c--) {
                if(obstacleGrid[r][c] == 1 )
                    dp[r][c] = 0;
                else
                    dp[r][c] = dp[r + 1][c] + dp[r][c + 1]; // KEY:
            }
        }
        DP_utils.print(dp);
        return dp[0][0];
    }
}