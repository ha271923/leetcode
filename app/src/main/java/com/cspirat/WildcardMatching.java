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
    // Tips3: 長度上須考量, pattern比string長的邊界情況
    static public boolean isMatch_DP(String s, String p) {
        int yLen = s.length();
        int xLen = p.length();

        boolean[][] dp = new boolean[yLen + 1][xLen + 1]; // Tips: dp[str][pattern], new出來時預設都為false, 所以符合的改為true即可
        dp[0][0] = true; // 因為參考前次結果的公式是 dp[y][x] = dp[y-1][x-1], 所以左上角需先設定

        for (int y = 1; y <= yLen; y++) { // S_LOOP1: 先選一組string, 從y=1開始
            for (int x = 1; x <= xLen; x++) {// P_LOOP2: 再掃描所有pattern, 從x=1開始
                // // Tips: charAt(0) 代表第一個字元
                if (s.charAt(y - 1) == p.charAt(x - 1) || // C1. pattern發現指定字元相同 或 萬用單一'?'字元
                    p.charAt(x - 1) == '?') {
                    dp[y][x] = dp[y - 1][x - 1]; // 因為相同,所以結果不變
                } else if (p.charAt(x - 1) == '*') { // C2. pattern首次發現'*'字元
                    int curChar = y;
                    while (curChar > 0) { // Pstar_LOOP2-1: KEY: *小迴圈, 往回掃描*字串時的 string字串的連續符合字元
                        // 遇到*符號, 參考dp[y-1][x-1]左上一次記錄, 與[y-(2~N)][x-1]正上, 正上上,正上上上,正上上上上...紀錄
                        // *的複數字元替換情況, ex: acd != a*, 但是 ac == a*, 所以 acd == a* , dp[
                        if (dp[curChar - 1][x - 1] == true ) { // KEY: , 一個*符號, 替換多個字元迴圈
                            dp[y][x] = true;
                            break; // KEY: 注意這個break keyword
                        }
                        curChar--;
                    }
                } else {
                    // 以上條件均不符合, 此次dp[y][x]仍是預設值=false
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

    // 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4
    // String =======================
    // L->
    // a c d c b
    // Pattern ======================
    // R->
    // a * c * b
    static public boolean isMatch_recursion(String s, String p) {
        return helper(s, p, 0, 0);
    }
    static boolean helper(String s, String p, int sIdx, int pIdx) {
        if (pIdx == p.length()) // 若pattern已掃到最後了
            return sIdx == s.length(); // 若String已掃到最後了, 則代表p與s均成功掃完, string符合pattern規則無誤

        if (sIdx < s.length() &&  // C1. pattern發現指定字元相同 或 萬用單一'?'字元
                (p.charAt(pIdx) == '?' || s.charAt(sIdx) == p.charAt(pIdx))) {
            return helper(s, p, sIdx + 1, pIdx + 1); // RECURSIVE!! 相符後, 進入下一個字元(Idx+1)與pattern符號比對遞迴
        } else if (p.charAt(pIdx) == '*') { // C2. pattern發現'*'字元
            while (pIdx < p.length() &&
                    p.charAt(pIdx) == '*') {
                pIdx++;   // Move the index at p to a non-start char.
            }
            while (sIdx < s.length()) {
                if (helper(s, p, sIdx, pIdx)) // RECURSIVE!!
                    return true; // Find one match, return true.
                sIdx++; // Try the next one.
            }
            return helper(s, p, sIdx, pIdx); // RECURSIVE!!
        } else {
            return false; // 以上條件均不符合, 直接返回false
        }
    }

    public static void main(String[] args) {
        // case1: pattern len < string len
        String inputStr = "abcv";
        String pattern = "abd*";
        // case2: pattern len > string len
        // String inputStr = "acb";
        // String pattern = "acb*";
        // boolean ret = isMatch_Greedy(inputStr, pattern);
        // boolean ret = isMatch_DP(inputStr, pattern);
        boolean ret = isMatch_recursion(inputStr, pattern);
        System.out.println(ret);
    }
}
