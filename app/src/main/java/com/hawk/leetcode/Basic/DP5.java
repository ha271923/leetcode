package com.hawk.leetcode.Basic;

import com.utils.DP_utils;

/**
 * 1-D dynamic program
 * 例題5:最長遞增子序列
 * 給出長度為N的陣列，找出這個陣列的最長遞增子序列。
 * (遞增子序列是指，子序列的元素是遞增的）
 * 例如：5 1 6 8 2 4 5 10，最長遞增子序列是1 2 4 5 10。
 *        ^     ^ ^ ^  ^
 * Scan:
 *          column
 *      5                row=0
 *      5 1
 *      5 1 6
 *      5 1 6 8
 *      5 1 6 8 2
 *      5 1 6 8 2 4
 *      5 1 6 8 2 4 5
 *      5 1 6 8 2 4 5 10
 *
 * Compare
 *          column
 *      5                row=0 取下第一個數字5, 並與左方已知數字一一比較後, 結果更新於dp[]
 *      d 1                    取下第二個數字1, 並與左方已知數字一一比較後, 結果更新於dp[]
 *      d d 6                                          ..
 *      d d d 8                                        ..
 *      d d d d 2                                      ..
 *      d d d d d 4                                    ..
 *      d d d d d d 5                                  ..
 *      d d d d d d d 10       取下最後一個數字10, 並與左方已知數字一一比較後, 結果更新於dp[]
 *      把數字一個一個取下來, 比對每一個數字
 *
 * Solve:
 *          column
 *      5 1 6 8 2 4 5 10
 *     -----------------
 *  5 | 1 x x x x x x x  row=0
 *  1 | 1 1 x x x x x x  dp小問題
 *  6 | 1 1 2 x x x x x
 *  8 | 1 1 2 3 x x x x
 *  2 | 1 1 2 3 2 x x x
 *  4 | 1 1 2 3 2 3 x x
 *  5 | 1 1 2 3 2 3 4 x
 * 10 | 1 1 2 3 2 3 4 5  dp小問題*N row*N column = O(N^2) , Space=O(N)
 * Ans: longest sequence number length is 5.
 *
 *  On-way的走法只需1-D dp[x], 不同階梯問題有2種走法(正上/左上)需要2-D dp[x][y], 所以1-D array
 *
 *  https://qiemengdao.iteye.com/blog/1660229
 */

/*
         input=[ 5 , 1 , 6 , 8 , 2 , 4 , 5 ,10 ]
       xLen>=x= [x] [1] [2] [3] [4] [5] [6] [7]  Length
           ans=| 1 | 1 | 2 | 3 | 2 | 3 | 4 | 5 |

           Ans: max=5
 */


// KEY: 最長遞增子序列求的是數字組的quantity, 不是total
public class DP5 {
    public static void main(String[] args) {
    int[] input  = {5,1,6,8,2,4,5,10};
    int[] input2 = {5,6,7,1,2,8};
    int ret = solve_DP(input);
        System.out.println("Longest number sequence =" + ret);
    }

    static int solve_DP(int[] number)
    {
        int len = number.length;
        int[] dp = new int[len]; // 1-D dp[] array, 因為On-way的走法只需1-D dp[x], 不同階梯問題有2種走法(正上/左上)需要2-D dp[x][y]
        for (int row=0; row<len; row++ )
            dp[row] = 1; // 最少有一個數字

        for (int row=1; row<len; row++ ) { // next num
            for (int column=0; column<row; column++ ) { // num[0] to  current num[row]
                // KEY: 本題的algorithm +++++++++++++
                //    數字是    升冪              且   是漸進(1階1階,故+1)式遞增 (總是與該row的最末dp[row]進行比對, 結果是否符合遞增)
                if (number[row] > number[column] && (dp[column] + 1) > dp[row] ) { // KEY:注意dp[column]+1 > dp[row]這個條件，不能省略, 因為遞增意義:除了數字是增加的之外, 數的排列也必須是增加的
                    dp[row] = dp[column] + 1; // 確實符合遞增, 故結果+1
                }
                // KEY: 本題的algorithm -------------
            }
        }
        // find Max
        int max = 0;
        for (int i=0; i<len; i++ ) {
            if (dp[i] > max)
                max = dp[i];  //dp[i]中找出最大值
        }
        DP_utils.print(dp);
        return max;
    }
}
