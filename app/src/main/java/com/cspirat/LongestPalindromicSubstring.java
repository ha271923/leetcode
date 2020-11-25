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
        String strTest = "ba";
        Out.i("Input=" + strTest);
        Out.i(longestPalindrome(strTest));
        // test algor2
        // System.out.println(longestPalindrome2(strTest));
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
        // 每次輸入的檢驗字串不是比對"字串相同", 而是比對是否符合迴文
        // 0      R=0, L=0       b=T --> 1
        // 01     R=1, L=0~1    ba=F
        // 012    R=2, L=0~2   bab=T --> 3
        // 0123   R=3, L=0~3  baba=F
        // 01234  R=4, L=0~4 babad=F
        // KEY: 注意!均是L++與R++, 而非L++與R--, 因為題目要求的是找出字串中的最長迴文, 而非字串整個是否為迴文
        //  L(移動)    R(固定)
        //  |++>       |++>
        for (int R = 0; R < s.length(); R++) { // LOOP1: 右邊界：設定右邊界=right
            for (int L = 0; L <= R; L++) {     // LOOP2: 左往右推進：由左向右推進的left因為要與right進行比較, 所以放在第2層回圈
                /** KEY: 先用筆把DP[][] table, 全填出來, 再想程式要怎麼寫, 才能同表上的結果
                 *       只要有一個字元相同, 那就一定是迴文, 只是迴文len=1, 所以標示為True
                 *       要做到兩個字元以上的迴文處理, 那就要DP才能參考上次的紀錄,
                 *       要看上個的迴文比對結果, 而非字元是否相同, 所以是有限定方向的, 也就是[L+1][R-1]的內縮限定方向
                 *       內縮到(L+1 >= R)時, 代表沒有辦法再內縮查上次的DP紀錄, 所以只要字元相同即可
                 *
                 *                     X= 慢慢增加數量的迴文輸入字串
                 *                  b      a      b      a      d (欲比對字串)
                 *            R =[0][j] [0][1] [0][2] [0][3] [0][4]
                 * L=dp[i][0]=b |  T   |  F   |  T   |  F   |  F   |  全R 跟L的 b 比對
                 *     [1][0]=a |      |  T   |  F   |  T   |  F   |  全R 跟L的 ba 比對
                 *     [2][0]=b |      |      |  T   |  F   |  F   |  全R 跟L的 bab 比對
                 *     [3][0]=a |      |      |      |  T   |  F   |  全R 跟L的 baba 比對
                 *     [4][0]=d |      |      |      |      |  T   |  全R 跟L的 babad 比對
                 * 挑L(L<=R) 跟R的  b比   ba比   bab比  baba比 babad比(所挑出的字元,並offset欲比對字串的起始點)
                 *     Y列舉字元從 X迴文輸入字串內 選取一定字數範圍 , 因為兩字重疊性, 所以DP二維
                 * 先設定R, 因為 L <= R
                 */
                // "aacabdkacaa" <- 這不是迴文歐
                // 什麼是Palindrome? 要如何確認? 為何是 (L+1 >= R) , 受限於LOOP2條件的關係, L不可能大於R
                 dp[L][R] = // 當下是否為迴文? 注意此處設計是R先固定,L陸續掃描, 因為是要找整個字串中, 最長的一段迴文
                   // KEY: 邏輯“短路”現象以免OutOfBoundary
                   (s.charAt(L) ==  s.charAt(R))       // L字元 需等同 R字元 ,ex: {a,aa,aba,a?????a}
                                  &&                   //           並且
                   ((L+1 >= R) || dp[L + 1][R - 1]);   // 死背KEY:(LR很接近時,無法內縮取得上次的結果,直接比字元 || LR不接近時, 因為題目要求的是最長迴文, 有連續性, 所以要上一次的結果(內縮[L+1][R-1])才知道是否為持續增加的迴文
                // A. 缺少(L+1 >= R)時,遇輸入"babad",將誤判將誤判"babad"為迴文
                // B. 缺少dp[L+1][R-1]時,遇輸入"cbbd",將誤判"cb"為迴文
                // C. 缺少A&B時, 遇輸入"aacabdkacaa",將誤判aacabdkacaa"為迴文
                // LR相距1字元內||上次[L+1] ->|內縮|<- [R-1] ==要上一次的值, 要用內縮去查, 因為每次都是外擴LR+1(Ex: babad, 取baba時, 非Palindrome, 取aba時, 是Palindrome)
                // (L+1 >= R)  || 以下兩種情況才可能是dp[L+1][R-1]的LR數值情況
                //                1. (L+1+n >= R)  LR相距2字元外, L小於R
                //                2. (L+1 < R)     LR相距2字元外, L小於R
                //       0 1 2 3 4
                //   (A)  L,R       --> L==R==1                --> 迴文字數是奇數時, 當LR 重疊時,              --> (L+1 >= R)
                //   (B)   L R      --> L=1 , R=2 --> L+1 == R --> 迴文字數是偶數時, 當LR 各站一邊,            --> (L+1 >= R)
                //   (C)   L   R    --> L=1 , R=3 --> L+2 == R -x> 迴文字數是奇數時, 當LR 各站一邊, 中間還有一筆 --> 上述情況(A)(B)即足夠應付迴文字數為奇數或偶數時, 所以L+1即足夠, (C)(D)用不到
                //   (D)   L     R  --> L=1 , R=4 --> L+3 == R -x> 迴文字數是偶數時, 當LR 各站一邊, 中間還有二筆 --> 不符合
                Out.i("dp["+L+"]["+R +"]="+dp[L][R]+" ,  subString="+ s.substring(L,R+1));
                // 只要是true代表他是迴文, 接著馬上計算長度, 並保留最長的長度
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
    static String sResult = "";
    // time : O(n^2) space : O(1)
    static public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0)
            return s;
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }
        return sResult;
    }

    static public void helper(String s, int L, int R) {
        while (L >= 0 && R < s.length() &&
               s.charAt(L) == s.charAt(R) ) { // 避免 OutOfBoundary
            L--;
            R++;
        }
        String cur = s.substring(L + 1, R);
        if (cur.length() > sResult.length()) {
            sResult = cur;
        }
    }
}