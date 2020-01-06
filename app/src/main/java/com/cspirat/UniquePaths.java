package com.cspirat;

import com.hawk.leetcode.Basic.DP_utils;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UniquePaths
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 62. Unique Paths
 */
public class UniquePaths {
    public static void main(String[] args) {
        int ret;
        ret = uniquePaths_DP(2,3);  // Ans: 3
        ret = uniquePaths_DP(3,7);  // Ans: 28
        System.out.println(ret);
    }

    // time : O(h*w)
    // space : (h*w)
    /*
      DP[][]儲存著匯流至此的走法有幾種, 每一個位置x,y的走法, 取決於上方可能的走法,與左方可能的走法, 的和
      因為方向只有, 右與下兩種, 所以最上一列DP[0][x]與作左一行DP[y][0]必定無其他方向匯流至此,僅唯一一種走法
                width>=w=dp[y][x-1] [y][x]
      height>=h=dp[y-1][x] |       | A1   |
                  [y  ][x] |  A2   | A1+A2|

      algorithm = DP[y][x] = A1+X2 = DP[y-1][x]+DP[y][x-1]
      ------------------------------------------------------------------
      EX1: uniquePaths_DP(2,3);
               width>=w=[0][w] [0][1] [0][2]
    height>=h=dp[h][0] |  1   |  1   |  1   |
                [1][0] |  1   |  2   |  3   |
      Ans: 3, 有3種走到右下角的路線
      ------------------------------------------------------------------
      EX2: uniquePaths_DP(3,7);
               width>=x=[0][x] [0][1] [0][2] [0][3] [0][4] [0][5] [0][6]
    height>=h=dp[y][0] |  1   |  1   |  1   |  1   |  1   |  1   |  1  |
                [1][0] |  1   |  2   |  3   |  4   |  5   |  6   |  7  |
                [2][0] |  1   |  3   |  6   | 10   | 15   | 21   | 28  |
       Ans: 28, 有28種走到右下角的路線
      ------------------------------------------------------------------
     */
    static public int uniquePaths_DP(int h, int w) {
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
                DP[y][x] = DP[y - 1][x] + DP[y][x - 1]; // 上方可能的走法 + 左方可能的走法
            }
        }
        DP_utils.print(DP);
        return DP[h - 1][w - 1];
    }

    // time : O(n * m) space : O(n)
    public int uniquePaths2(int m, int n) {
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[j] = res[j] + res[j - 1];
            }
        }
        DP_utils.print(res);
        return res[n - 1];
    }

    /**
     * Combination(Count, k) = count! / (k!*(count - k)!)
     * C = (count - k + 1) * (count - k + 2) .../k!
     * time : O(m)
     * space : (1)
     * @param m
     * @param n
     * @return
     */

    public int uniquePaths3(int m, int n) {
        int count = m + n - 2;
        int k = m - 1;
        double res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (count - k + 1) / i;
        }
        return (int)res;
    }
}
