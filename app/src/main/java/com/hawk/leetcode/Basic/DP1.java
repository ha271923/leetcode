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

// Tips1: DP每次都利用DP[x-1]或[y-1]的方式, 取得前一次計算結果
// Tips2: DP有時候會先擴展X或是Y
// Tips3: DP利用min/max將有分岔的結果比對出來後, 存入DP[][]之後就不會更動了, 也就是說永遠只看的到當前與前一步
// Tips4: DP最困難點在於找出符合題義的min/max的algorithm
public class DP1 {
    public static void main(String[] args) {
        int[][] input = {
                             {5},           //  ==>       {5}            ==>         ^
                            {8, 4},         //  ==>     {13, 9}          ==>       /   \
                           {3, 6, 9},       //  ==>   {16, 15, 18}       ==>      /  V  \
                          {7, 2, 9, 5}      //  ==>  {23, 17, 27, 23}    ==>     / V   V \
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
        int layer=triangle.length;
        int width=layer; // 本解法只適合正三角形, width==layer
        int dp[][]=new int[layer][width];
        dp[0][0]=triangle[0][0];
        /*
         * 初始化矩阵
         *                                                        dp[0][x] [0][1] [0][2] [0][3] element
         *     {5},      ==>  {   5,   0,   0,   0}  ==>  dp[y][0] |  5   |  0   |  0   |  0   |
         *    {8, 4},    ==>  { 5+8, 5+4,   0,   0}  ==>    [1][0] | 13   |  9   |  0   |  0   |
         *   {3, 6, 9},  ==>  {13+3, 9+6, 9+9,   0}  ==>    [2][0] | 16   | 15   | 18   |  0   |
         *  {7, 2, 9, 5} ==>  {16+7,15+2,18+9,18+5}  ==>    [3][0] | 23   | 17   | 24   | 23   |
         *       Ans: 17                                     layer
         * */
        // KEY: algorithm
        for (int y = 1; y < layer; y++) { // 從第1列開始
            for (int x = 0; x <= y; x++) { // 因為本題是正三角形
                // KEY: 本題的algorithm +++++++++++++
                if (x == 0) { // 左斜邊路線 = { 5,13, 16, 23 }
                    dp[y][x] = dp[y - 1][x] + triangle[y][x];   // 上回+本次
                } else if (x == y) { // 右斜邊路線 = { 5,9, 18, 23 }
                    dp[y][x] = dp[y - 1][x - 1] + triangle[y][x]; // 上回+本次
                } else { // 非左右斜邊路線 = {13+6vs9+6, 16+2vs15+2, 15+9vs18+9} min => {15, 17, 24}
                    dp[y][x] = Math.min(dp[y - 1][x], dp[y - 1][x - 1]) + triangle[y][x]; // min(上回右上,上回左上) + 本次 , 比較後較小的可以入列DP
                }
                // KEY: 本題的algorithm -------------
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<width;i++){
            min=Math.min(min, dp[layer-1][i]);
        }
        DP_utils.print(dp);
        return min;
    }
}
