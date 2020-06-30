package com.cspirat;

import com.utils.Out;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ScrambleString
 * Creator : Edward
 * Date : Dec, 2017
 * Description : 87. Scramble String
 */
public class ScrambleString {
    /*
     題目是混雜的字符串。一個字符串s1可以表達成一棵二叉樹的形式，從而把一棵二叉樹的所有葉子節點從左到右遍歷一遍
     就能得到源字符串。現在我們要做一些翻轉二叉樹的操作，即把某些位置的二叉樹進行翻轉，這樣從左到右的葉子節點又
     會串成另外一個字符串s2。
     現在要我們判斷給定s1能不能通過翻轉某些位置的二叉樹形式得到另外一個字符串s2.


    s1 = "great"

        great
       /    \
      gr    eat
     / \    /  \
    g   r  e   at
               / \
              a   t

                         s2 = "rgeat" ==> TRUE <== SWAP(r,g)
                             rgeat
                            /    \
                           rg    eat
                          / \    /  \
                         r   g  e   at
                                    / \
                                   a   t

                         s2 = "rgtae" ==> TRUE <== SWAP(r,g),SWAP(a,t)
                             rgtae
                            /    \
                           rg    tae
                          / \    /  \
                         r   g  ta  e
                                / \
                               t   a

    =================================================================

    s1 = "abcde"
        abcde
       /    \
      ab    cde
     / \    /  \
    a   b  cd   e
           / \
          c   d

                         s2 = "caebd" ==> FALSE <== SWAP(?,?)
                             caebd
                            /    \
                           ab    cde
                          / \    /  \
                         a   b  cd   e
                                / \
                               c   d


     */
    public static void main(String[] args) {
        // String s1 = "great", s2 = "rgeat"; // TRUE

        // String s1 = "abcde", s2 = "caebd"; // FALSE
        // String s1 = "abcde", s2 = "edcba"; // TRUE
        // String s1 = "abcde", s2 = "bcdea"; // TRUE
        String s1 = "abcde", s2 = "abcdea"; // FALSE, 多一個字元無法靠翻轉
        boolean ret = isScramble(s1,s2);
        Out.i(""+ret);
    }
    /**

     time : O(n!)
     space : O(n)

     s1 = "abcdefghijklmnopqrstuvwxyz"     s2 = "zyxwvutsrqponmlkjihgfedcba";  // TRUE
     s1 = "great", s2 = "rgeat"; // TRUE
     s1 = "abcde", s2 = "caebd"; // FALSE

     * @param s1
     * @param s2
     * @return
     */
    // https://coordinate.wang/index.php/archives/2108/
    // ???
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
        // substring API:
        // "unhappy".substring(2) returns "happy"
        // "hamburger".substring(3, 7) returns "burg"
        //   0 1 2 3 4
        //  |a b c d e|
        //  |  i      |
        //  |0~i      |
        //  |0~i      |
        //  |    len-i|
        //  |  i      |
        for (int i = 1; i < len; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))  // ???
             && isScramble(s1.substring(i   ), s2.substring(i   ))  )
                return true;
            if (isScramble(s1.substring(0, i), s2.substring(len - i          ))   // ???
             && isScramble(s1.substring(i   ), s2.substring(0       , len - i))  )
                return true;
        }
        return false;
    }

   // 这道题也可以用动态规划 Dynamic Programming，
   // 根据以往的经验来说，根字符串有关的题十有八九可以用 DP 来做，那么难点就在于如何找出状态转移方程


}
