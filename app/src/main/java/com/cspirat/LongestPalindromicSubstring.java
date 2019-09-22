package com.cspirat;

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

    /**
     * 在運用邏輯運算子進行相關的操作，就不得不說“短路”現象。程式碼如下：
     *
     * if(1==1 && 1==2 && 1==3){  }
     *
     * 程式碼從左至右執行，
     * 執行第一個邏輯表示式後：true && 1==2 && 1==3
     * 執行第二個邏輯表示式後：true && false && 1==3
     * 因為其中有一個表示式的值是false，可以判定整個表示式的值是false，就沒有必要執行第三個表示式了，
     * 所以java虛擬機器不執行1==3程式碼，就好像被短路掉了。
     *
     * 邏輯或也存在“短路”現象，當執行到有一個表示式的值為true時，整個表示式的值就為true，後面的程式碼就不執行了。
     * “短路”現象在多重判斷和邏輯處理中非常有用。
     *
     */
    // time : O(n^2) space : O(n^2);
    static public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()]; // 用DP是因為, abc不是, abcb是, abcba是, 就算之前不是, 之後仍要重頭掃描之前掃過的一切
        int max = 0;
        for (int right = 0; right < s.length(); right++) {
            for (int left = 0; left <= right; left++) {
                /**
                 *          R =[0][j] [0][1] [0][2] [0][3] [0][4]
                 * L=dp[i][0] |  T   |  ?   |  ?   |  ?   |  ?   |
                 *     [1][0] |  ?   |  ?   |  ?   |  ?   |  ?   |
                 *     [2][0] |  ?   |  ?   |  ?   |  ?   |  ?   |
                 *     [3][0] |  ?   |  ?   |  ?   |  ?   |  ?   |
                 *     [4][0] |  ?   |  ?   |  ?   |  ?   |  ?   |
                 * L的遞增,代表sliding Window的往右 推進
                 * R的遞增,代表sliding Window的往右 擴展
                 */
                // 什麼是Palindrome? 要如何確認?
                dp[left][right] =
                                  // KEY: 下述方程式難以理解?????
                                  s.charAt(left) ==  s.charAt(right)  // boolean A1 = 最前字元 需等同 最後字元
                                                 &&                   // boolean dp[][] = A3 = A1 && A2
                                  ((right - left <= 2) || dp[left + 1][right - 1]); // boolean A2 = ((3字以內必為true,因A1已經比對) |
                /*

                // 測試邏輯小程式
                char a= 'a',b='a', c='c';
                boolean dp[left][right] =
                        a == b   // boolean A1
                          &&     // boolean dp[][] = A3 = A1 && A2
                        true == true; // boolean A2 =
                System.out.println(o);
                 */
                if (dp[left][right]) {
                    if (right - left + 1 > max) {
                        max = right - left + 1;
                        res = s.substring(left, right + 1);
                    }
                }
            }
        }
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

    // Program to create a generic array in Java
    public static void main(String[] args)
    {
        // Sliding Window( No loop back )
        //     >>>>>>>>>[    ]----
        String strTest = "babad";
        System.out.println(LongestPalindromicSubstring
                .longestPalindrome(strTest));
        // test algor2
        System.out.println(LongestPalindromicSubstring
                .longestPalindrome2(strTest));
    }
}