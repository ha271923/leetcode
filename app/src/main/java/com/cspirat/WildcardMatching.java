package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WildcardMatching
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 44. Wildcard Matching
 */
public class WildcardMatching {
    /**
     * Implement wildcard pattern matching with support for '?' and '*'.
     * <p>
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * <p>
     * The matching should cover the entire input string (not partial).
     * <p>
     * The function prototype should be:
     * bool isMatch(const char *s, const char *p)
     * <p>
     * Some examples:
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "*") → true
     * isMatch("aa", "a*") → true
     * isMatch("ab", "?*") → true
     * isMatch("aab", "c*a*b") → false
     * <p>
     * "bbarc" match = 3 sp = 3
     * "*c" star = 0 pp = 1
     * <p>
     * time : O(n)
     * space : O(1)
     *
     * @param s
     * @param p
     * @return
     */
    // KEY: 三種解法說明 https://longwayjade.wordpress.com/2015/04/26/leetcode-recursion-dp-greedy-wildcard-matching/
    //
    //    s = "0123456789"
    //         |
    //       sIdx

    //    p = "01*89"
    //         | |
    //       pIdx|
    //        pstarIdx -> starMatched++
    static public boolean isMatch_Greedy(String s, String p) {
        int sIdx = 0;
        int pIdx = 0;
        int pstarIdx = -1; // 發現'*'所在位置
        int sstarIdx = -1; // 一旦發現'*', 每次掃到string中的字元c時,都當作符合

        while (sIdx < s.length()) { // LOOP1: 掃描字串
            if (pIdx < p.length() && // C1. pattern發現指定字元相同 或 萬用單一'?'字元
                    (p.charAt(pIdx) == s.charAt(sIdx) || p.charAt(pIdx) == '?')) {
                pIdx++;
                sIdx++;
            }
            else if (pIdx < p.length() && // C2. pattern首次發現'*'字元
                    p.charAt(pIdx) == '*') {
                pstarIdx = pIdx; // 紀錄'*'所在pattern位置
                sstarIdx = sIdx; // 紀錄當前字元所在string位置
                pIdx++; // KEY: 注意!sIdx並未遞增
            }
            else if (pstarIdx != -1) { // C2-1. pattern正處於非首次發現'*'字元期間
                sstarIdx++;
                sIdx = sstarIdx;
                pIdx = pstarIdx + 1;  // KEY: pIdx原地踏步
            }
            // 4. case 4: they do not match, do not currently at a *, and last matched is not a *, then the answer is false;
            else {
                return false;
            }
        }
        //  KEY: 邊界條件, 當pattern比string還長時, EX: s="acb" , p="acb*"
        while (pIdx < p.length() &&
                p.charAt(pIdx) == '*') {
            pIdx++; // KEY:
        }
        return pIdx == p.length(); // KEY: idx一般而言比length少1, 確認是否有成功掃到最後?(idx超出1)
    }

    /**
     * 注意!!! 這題Q44的 * 符號, 與 Q10: RegularExpressionMatching的功能不同
     *
     * EX: s=ac , p= a*c , 在Q44是false, Q10是true
     *
     * Q44 :Wildcard Matching:
     *   '?' Matches ANY single character.
     *   '*' Matches ANY sequence and length>0 of characters (including the empty sequence).
     *
     *  結果為 T 的條件:
     *   1. 當string與pattern每一位比對時,是相同的
     *
     *                             a     *      c      *      b
     *    inputS=xLen>=x=[0][x] [0][1] [0][2] [0][3] [0][4] [0][5]  string
     * yLen>=y=  [y][0] |  T   |  0   |  0   |  0   |  0   |  0   |
     *         a [1][0] |  0   |  T   |  0   |  0   |  0   |  0   |
     *         c [2][0] |  0   |  0   |  T   |  0   |  0   |  0   |
     *         d [3][0] |  0   |  0   |  T   |  0   |  0   |  0   |
     *         c [4][0] |  0   |  0   |  T   |  T   |  0   |  0   |
     *         b [5][0] |  0   |  0   |  T   |  0   |  T   |  0   |
     *           pattern
     *
     * */

    // Tips1: RegEx(Q10)裡的*跟DOS(Q44)裡的* , 意義不同
    // Tips2: 匹配真值表 dp[s.len+1]*[p.len+1], X與Y多一行列是因為EMPTY==EMPTY
    static public boolean isMatch_DP(String s, String p) {
        int yLen = s.length();
        int xLen = p.length();

        boolean[][] dp = new boolean[yLen + 1][xLen + 1]; // Tips: new出來時預設都為false, 所以符合的改為true即可
        dp[0][0] = true;

        for (int y = 1; y <= yLen; y++) {// LOOP1: 掃描string
            for (int x = 1; x <= xLen; x++) {// LOOP2: 掃描pattern
                if (s.charAt(y - 1) == p.charAt(x - 1) || // C1. pattern發現指定字元相同 或 萬用單一'?'字元
                    p.charAt(x - 1) == '?') {
                    dp[y][x] = dp[y - 1][x - 1]; // KEY: 因為相同,所以結果不變
                } else if (p.charAt(x - 1) == '*') {
                    int cur = y;
                    while (cur > 0) {// LOOP2-1: 掃描*字串時的 string字串
                        if (dp[cur - 1][x - 1] == true ) { // KEY: 因為是任意字元, 所以與取前次結果相同, 設為true
                            dp[y][x] = true;
                            break;
                        }
                        cur--;
                    }
                }
            }
        }
        // Hawk: for debugging, 因為要符合上面的註解, 所以先印 y 再印 x
        for(int y=0; y<= yLen; y++) {
            for(int x=0; x<= xLen; x++) {
                System.out.print("[" + y + "][" + x + "]=" + dp[y][x]);
            }
            System.out.println("");
        }
        return dp[yLen][xLen];
    }


    static public boolean isMatch_recursion(String s, String p) {
        return helper(s, p, 0, 0);
    }
    static boolean helper(String s, String p, int L, int R) {
        if (R == p.length())
            return L == s.length();

        if (p.charAt(R) == '*') {
            while (R < p.length() &&
                    p.charAt(R) == '*')
                R++;   // Move the index at p to a non-start char.
            while (L < s.length()) {
                if (helper(s, p, L, R)) // RECURSIVE!!
                    return true; // Find one match, return true.
                L++; // Try the next one.
            }
            return helper(s, p, L, R); // RECURSIVE!!
        } else if (L < s.length() &&
                (p.charAt(R) == '?' || s.charAt(L) == p.charAt(R))) {
            return helper(s, p, L + 1, R + 1); // RECURSIVE!!
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // case1: pattern len < string len
        String inputStr = "acdcb";
        String pattern = "a*c?b";
        // case2: pattern len > string len
        // String inputStr = "acb";
        // String pattern = "acb*";
        // boolean ret = isMatch_Greedy(inputStr, pattern);
        boolean ret = isMatch_DP(inputStr, pattern);
        // boolean ret = isMatch_recursion(inputStr, pattern);
        System.out.println(ret);
    }
}
