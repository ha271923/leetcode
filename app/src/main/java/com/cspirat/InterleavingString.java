package com.cspirat;

import com.utils.DP_utils;
import com.utils.Out;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : InterleavingString
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 97. Interleaving String
 */
public class InterleavingString {

    public static void main(String[] args) {
        String s1;
        String s2;
        String s3;

        // Ex1: true
        s1 = "aabcc";
        s2 = "dbbca";
        s3 = "aadbbcbcac";

        // Ex2: false
        s1 = "aabcc";
        s2 = "dbbca";
        s3 = "aadbbbaccc";

        // Ex3: true
        s1 = "";
        s2 = "";
        s3 = "";

        // Ex4: special case,  expect=false
        s1="aabd";
        s2="abdc";
        s3="aabdbadc";

        // Ex5: special case,  expect=true
        s1="aabcc";
        s2="dbbca";
        s3="aadbbcbcac";

        boolean ret = false;
        // ret = isInterleave_DP(s1,s2,s3);
        ret = isInterleave_hawk(s1,s2,s3);
        Out.i("ret="+ret);
    }
    /**
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

     For example,
     Given:
     s1 = "aabcc",
     s2 = "dbbca",

     EX: When s3 = "aadbbcbcac", return true.
     [true,  true,  true,  false, false, false]
     [false, false, true,  true,  false, false]
     [false, false, true,  true,  true,  false]
     [false, false, true,  false, true,  true]
     [false, false, true,  true,  true,  false]
     [false, false, false, false, true,  true]

     EX: When s3 = "aadbbbaccc", return false.
     [true,  true,  true,  false, false, false]
     [false, false, false, false, false, false]
     [false, false, false, false, false, false]
     [false, false, false, false, false, false]
     [false, false, false, false, false, false]
     [false, false, false, false, false, false]



     time : O(m * n)
     space : O(m * n)

     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    /*
                  第一個T是因為,Ex3說s1,s2都為""時,return返回T
                               a      a      b    c       c
               xLen>=x=[0][x] [0][1] [0][2] [0][3] [0][4] [0][5]  s1
  s2=yLen>=y=  [y][0] |   T  |   T  |   T  |      |      |      |
             a [1][0] |      |      |   T  |  T   |      |      |
             b [2][0] |      |      |   T  |  T   |  T   |      |
             b [3][0] |      |      |   T  |      |  T   |  T   |
             c [4][0] |      |      |   T  |  T   |  T   |      |
             a [5][0] |      |      |      |      |  T   |  T   |

                             s3= aadbbcbcac
     */
    // 字元順序姓
    // 字元數量正確
    // 遇到大部分的string matching，基本上大部分都可以用DP來做，其難點在於找到dp的transfer formula.
    // BUG! 必須使用DP的主因是因為比較字元時, 若s1與s2均有該字元,應該選s1還是s2呢? 答案會因此在後續的字元比對有截然不同的結果
    public static boolean isInterleave_DP(String s1, String s2, String s3) {
        if ((s1.length() + s2.length()) != s3.length())
            return false;

        boolean[][] DP = new boolean[s2.length() + 1][s1.length() + 1];
        DP[0][0] = true; // KEY: ture是因為算法是用 && , 必需當下跟上一次都符合條件

        // 從 x,y =1 開始索引, 是因為DP[0][0]已經設過
        // Q: 為什麼每次都要 (前一次DP的結果 與 當下結果)==都符合, 才是true?
        for (int x = 1; x < DP[0].length; x++) { // LOOP_x: init first row, 如果s2=null, 只有s1跟s3比較
            DP[0][x] = (DP[0][x - 1] && (s1.charAt(x - 1) == s3.charAt(x - 1)));
        }

        for (int y = 1; y < DP.length; y++) { // LOOP_y: init first column, 如果s1=null, 只有s2跟s3比較
            DP[y][0] = (DP[y - 1][0] && (s2.charAt(y - 1) == s3.charAt(y - 1)));
        }

        for (int y = 1; y < DP.length; y++) { // LOOP_xy: fill others fields.
            for (int x = 1; x < DP[0].length; x++) {
                DP[y][x] = (DP[y - 1][x] && s2.charAt(y - 1) == s3.charAt(y + x - 1))
                        || (DP[y][x - 1] && s1.charAt(x - 1) == s3.charAt(y + x - 1));
            }
        }
        Out.print2DArray(DP);
        return DP[s2.length()][s1.length()];
    }

    /*
        s1="aabcc";
        s2="dbbca";
        s3="aadbbcbcac";
    會算成false,但其實是true
     */
    // BUG! 不用DP解不出來
    // 必須使用DP的主因是因為比較字元時, 若s1與s2均有該字元(如:'a'),應該選s1的'a'還是s2的'a'呢?
    // 答案會因此處的選擇, 在後續的字元比對有截然不同的結果
    public static boolean isInterleave_hawk(String s1, String s2, String s3) {
        boolean ret=false;
        if ((s1.length() + s2.length()) != s3.length())
            return false;

        // Q: 為什麼每次都要 (前一次DP的結果 與 當下結果)==都符合, 才是true?
        int s1Idx=0, s2Idx=0;
        for (int i = 0; i < s3.length(); i++) { // LOOP_xy: fill others fields.
            if(s1Idx < s1.length() && s3.charAt(i) == s1.charAt(s1Idx))
                s1Idx++;
            else if(s2Idx < s2.length() && s3.charAt(i) == s2.charAt(s2Idx))
                s2Idx++;
            else
                Out.i("Bug !!!!!!!!!!!!!!!");
        }
        if( (s1Idx+s2Idx)==s3.length() )
            ret = true;
        return ret;
    }

}
