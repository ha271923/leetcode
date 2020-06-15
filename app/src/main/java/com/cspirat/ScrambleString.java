package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ScrambleString
 * Creator : Edward
 * Date : Dec, 2017
 * Description : 87. Scramble String
 */
public class ScrambleString {

    public static void main(String[] args) {
        // String s1 = "great", s2 = "rgeat"; // T
        // String s1 = "abcde", s2 = "caebd"; // F
        String s1 = "abcdefghijklmnopqrstuvwxyz", s2 = "zyxwvutsrqponmlkjihgfedcba"; //
        boolean ret = isScramble(s1,s2);
    }
    /**

     time : O(n!)
     space : O(n)

     * @param s1
     * @param s2
     * @return
     */
    // https://coordinate.wang/index.php/archives/2108/
    static public boolean isScramble(String s1, String s2) {

        if (s1 == null || s2 == null)
            return false;
        if (s1.equals(s2))
            return true;
        // 字母計數陣列
        int[] letters = new int[26];
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--; // 可以是負數
        }

        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0)
                return false;
        }

        // 遞迴
        for (int i = 1; i < len; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if (isScramble(s1.substring(0, i), s2.substring(len - i))
                    && isScramble(s1.substring(i), s2.substring(0, len - i)))
                return true;
        }
        return false;
    }

   // 这道题也可以用动态规划 Dynamic Programming，
    // 根据以往的经验来说，根字符串有关的题十有八九可以用 DP 来做，那么难点就在于如何找出状态转移方程


}
