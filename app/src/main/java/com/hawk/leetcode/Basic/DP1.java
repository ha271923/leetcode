package com.hawk.leetcode.Basic;

/**
 * 例題1: 數塔取數問題
 * 一個高度為N的由正整陣列成的三角形，從上走到下，求經過的數字和的最小值。
 * 每次只能走到下一層相鄰的數上，例如從第3層的6向下走，只能走到第4層的2或9上。
 *
 * 該三角形第n層有n個數字，例如：
 *      5
 *     8 4
 *    3 6 9
 *   7 2 9 5
 *
 * 最優方案是：5 4 6 2 = 17
 * 狀態定義: Fi，j是第i行j列項最大取數和，求第n行Fn，m（0 < m < n）中最大值。
 * 狀態轉移方程：Fi，j = max{Fi-1,j-1,Fi-1,j} Ai,j
 */
public class DP1 {
    public static void main(String[] args) {
        int[][] input = {
                             {5},
                            {8, 4},
                           {3, 6, 9},
                          {7, 2, 9, 5}
                        };
        int minVal = minimumTotal(input);
        System.out.println(minVal);
    }
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    static public int minimumTotal(int[][] triangle) {
        // write your code here
        if(triangle==null){
            return 0;
        }
        if(triangle.length==0){
            return 0;
        }
        int m=triangle.length;
        int n=m;
        int dp[][]=new int[m][n];
        dp[0][0]=triangle[0][0];
        for(int i=1;i<m;i++){
            for(int j=0;j<=i;j++){
                if(j==0){
                    dp[i][j]=dp[i-1][j]+triangle[i][j];
                }else if(j==i){
                    dp[i][j]=dp[i-1][j-1]+triangle[i][j];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j], dp[i-1][j-1])+triangle[i][j];
                }
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            min=Math.min(min, dp[m-1][i]);
        }
        return min;
    }
}
