package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : EditDistance
 * Creator : Edward
 * Date : Dec, 2017
 * Description : 72. Edit Distance
 */
public class EditDistance {

    public static void main(String[] args) {
        System.out.println(minDistance("kitten", "sitting"));
    }

    /**
     * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
     * (each operation is counted as 1 step.)

     You have the following 3 operations permitted on a word:

     a) Insert a character
     b) Delete a character
     c) Replace a character

     abcd -> aef

     dp[i][j]表示的是，从字符串1的i的位置转换到字符串2的j的位置，所需要的最少步数。


     1,字符串中的字符相等: dp[i][j] = dp[i - 1][j - 1]
     2,字符串中的字符不等:
         insert: dp[i][j] = dp[i][j - 1] + 1;
         replace: dp[i][j] = dp[i - 1][j - 1] + 1;
         delete: dp[i][j] = dp[i - 1][j] + 1;

           a  b  c  d
        0  1  2  3  4
     a  1  0  1  2  3
     e  2  1  1  2  3
     f  3  2  2  2  3

     time : O(m * n)
     space : O(m * n)

     * @param word1
     * @param word2
     * @return
     */
    // DP2.java
    static public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        // 因為此類題目需比對前次結果, 所以算法必有[x-1]或[y-1]的推算,避免overflow,需要在初始化時, 預先X,Y各增一行列, 並完成初始化
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }
        //
        for (int y = 1; y <= len1; y++) {
            for (int x = 1; x <= len2; x++) {
                if (word1.charAt(y - 1) == word2.charAt(x - 1)) { // 字一樣
                    dp[y][x] = dp[y - 1][x - 1]; // 紀錄不變
                } else {
                    dp[y][x] = Math.min(
                                Math.min(dp[y][x - 1], dp[y - 1][x]),  // DEL , INSERT
                               dp[y -1][x - 1]); // REPLACE
                }
            }
        }
        return dp[len1][len2];
    }
}
