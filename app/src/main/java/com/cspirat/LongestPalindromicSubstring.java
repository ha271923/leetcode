package com.cspirat;

import com.utils.Out;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestPalindromicSubstring
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class LongestPalindromicSubstring {
    /**
     * 5. Longest Palindromic Substring
     * Given a string s, find the longest palindromic substring in s.
     * You may assume that the maximum length of s is 1000.

     Example:

     Input: "babad"

     Output: "bab"

     Note: "aba" is also a valid answer.
     Example:

     Input: "cbbd"

     Output: "bb"


     * @param s
     * @return
     */
    // Program to create a generic array in Java
    public static void main(String[] args)
    {
        // Sliding Window( No loop back )
        //     >>>>>>>>>[    ]----
        String strTest = "babad";
        System.out.println(longestPalindrome(strTest));
        // test algor2
        System.out.println(longestPalindrome2(strTest));
    }

    // time : O(n^2) space : O(n^2);
    // 1. 找出字串中的迴文
    // 2. 找出所有迴文中最長的那一段迴文
    static public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return s;
        String res = "";
        // 用2-D DP是因為, abc不是, abcb是, abcba是, 就算之前FALSE, 本來不是的字, 再增加一個字後, 有可能變成TRUE ,
        // 解palindromic問題, 每增加一字重頭掃描之前掃過的一切是必須的
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        // 01234
        // >L R.
        // babad
        // 0      R=0, L=0       b=T --> 1
        // 01     R=1, L=0~1    ba=F
        // 012    R=2, L=0~2   bab=T --> 3
        // 0123   R=3, L=0~3  baba=F
        // 01234  R=4, L=0~4 babad=F
        // KEY: 注意!均是L++與R++, 而非L++與R--, 因為題目要求的是找出字串中的最長迴文, 而非字串整個是否為迴文
        //  L(移動)    R(固定)
        //  |++>       |++>
        for (int R = 0; R < s.length(); R++) { // LOOP1: 右邊界：設定右邊界=right
            for (int L = 0; L <= R; L++) {    // LOOP2: 左往右推進：由左向右推進的left因為要與right進行比較, 所以放在第2層回圈
                System.out.println("L="+L+" , R="+R +" ,  subString="+ s.substring(L,R+1));
                /**                 b      a      d      a      d
                 *            R =[0][j] [0][1] [0][2] [0][3] [0][4]
                 * L=dp[i][0]=b |  T   |  F   |  T   |  F   |  F   |
                 *     [1][0]=a |  F   |  T   |  F   |  T   |  F   |
                 *     [2][0]=d |  F   |  F   |  T   |  F   |  F   |
                 *     [3][0]=a |  F   |  F   |  F   |  T   |  F   |
                 *     [4][0]=d |  F   |  F   |  F   |  F   |  T   |
                 * 先設定R, 因為 L <= R
                 * R的遞增,代表sliding Window的右邊界, 慢慢擴大
                 * L的遞增,代表sliding Window的往右, 慢慢推進
                 */
                // 什麼是Palindrome? 要如何確認?
                //     0 1 2 3 4
                // (A)  L,R       --> L==R==1                --> 總數量為奇數時, 當LR 重疊時               --> (L+1 >= R)
                // (B)   L R      --> L=1 , R=2 --> L+1 == R --> 總數量為偶數時, 當LR 各站一邊             --> (L+1 >= R)
                // (C)   L   R    --> L=1 , R=3 --> L+2 == R --> 總數量為奇數時, 當LR 各站一邊, 中間還有一筆 --> 情況(A)(B)即可應付, 所以L+1即可
                // (D)   L     R  --> L=1 , R=4 --> L+3 == R -x> 總數量為偶數時, 當LR 各站一邊, 中間還有二筆 --> 不符合
                 dp[L][R] = // 當下是否為迴文? 注意此處設計是R先固定,L陸續掃描, 因為是要找整個字串中, 最長的一段迴文
                   // KEY: 邏輯“短路”現象以免OutOfBoundary
                   (s.charAt(L) ==  s.charAt(R))       // boolean A1 = L字元 需等同 R字元 ,ex: {a,aa,aba,a?????a}
                                  &&                   //           並且
                   ((L+1 >= R) || dp[L + 1][R - 1]); // boolean A2 = (LR相近時,無法內縮取得上次的結果,直接比字元 || 上一次比對的結果 =
                //    LR相近    ||   [L+1] ->|內縮|<- [R-1] ==上一次

                /*

                // 測試邏輯小程式
                char a= 'a',b='a', c='c';
                boolean dp[L][R] =
                        ((a == b)   // boolean A1
                           &&       // boolean dp[][] = A3 = A1 && A2
                        (true || true)); // boolean A2 =
                System.out.println(o);
                 */
                if (dp[L][R]) {
                    if (R - L + 1 > max) {
                        max = R - L + 1;
                        res = s.substring(L, R + 1);
                    }
                }
            }
        }
        Out.print2DArray(dp);
        return res;
    }
///////////////////////////////////////////////////////
    static String res = "";
    // time : O(n^2) space : O(1)
    static public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return s;
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }
        return res;
    }

    static public void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if (cur.length() > res.length()) {
            res = cur;
        }
    }
}