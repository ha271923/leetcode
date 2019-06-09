package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RegularExpressionMatching
 * Creator : Edward
 * Date : Dec, 2017
 * Description : 10. Regular Expression Matching
 */
public class RegularExpressionMatching {
    /**
     * Implement regular expression matching with support for '.' and '*'.

     '.' Matches any single character.
     '*' Matches zero or more of the preceding element.

     The matching should cover the entire input string (not partial).

     The function prototype should be:
     bool isMatch(const char *s, const char *p)

     Some examples:
     isMatch("aa","a") → false
     isMatch("aa","aa") → true
     isMatch("aaa","aa") → false
     isMatch("aa", "a*") → true
     isMatch("aa", ".*") → true
     isMatch("ab", ".*") → true  注意!! 兩字不同
     isMatch("aab", "c*a*b") → true  注意!! 消去前面字
     isMatch("mississippi", "mis*is*p*.") → false

     "aa", ".*"
     "ab", ".*"
     "aab", "c*a*b"


     boolean dp[i][j]的含义是s[0-i] 与 p[0-j]是否匹配。

     c* = empty

     1，p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]
     2，If p.charAt(j) == ‘.’ : dp[i][j] = dp[i-1][j-1];
     3，If p.charAt(j) == ‘*’:
         here are two sub conditions: Tips1: (j-1)代表取出前一字元
         1，if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] //in this case, a* only counts as empty
         2，if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == ‘.’:
             dp[i][j] = dp[i][j-1] // in this case, a* counts as single a
             dp[i][j] = dp[i-1][j] // in this case, a* counts as multiple a
             dp[i][j] = dp[i][j-2] // in this case, a* counts as empty

     "aab", "c*aab"

     i = 1 dp[0][2] = true

     time : O(m * n)
     space : O(m * n)

     * @param s
     * @param p
     * @return
     */
    // Tips1: RegEx裡的*跟DOS裡的* , 意義不同
    // Tips2: 匹配真值表 dp[s.len+1]*[p.len+1], X與Y多一行列是因為 a* = null, a, aa, aaa... 有null的可能性
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 1. True if pattern null to null
        dp[0][0] = true; // 靠這個true傳遞下去dp[y][x]  https://youtu.be/l3hda49XcDE?t=303

        // 2. Deals with patterns  like a* a*b* a*b*c*
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '*' && dp[0][i - 1]) { // 掃到* 並且前次相符
                dp[0][i + 1] = true;
            }
        }
        // 3. Deals with other patterns
        for (int i = 0; i < s.length(); i++) { // scan s String
            for (int j = 0; j < p.length(); j++) { // scan p Pattern
                if (p.charAt(j) == s.charAt(i)) { // single char matching
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '.') { // any char matching
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') { //  '*' found
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') { // NOT  aa==a*  or a*==.*
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else { // IS  aa==a*  or a*==.*
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    // Approach 1: Recursion
    // https://leetcode.com/problems/regular-expression-matching/solution/
    public boolean isMatch2(String text, String pattern) {
        if (pattern.isEmpty())
            return text.isEmpty();
        boolean first_match = (
                    !text.isEmpty() &&
                    (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.')
                  );

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    // Approach 2: Dynamic Programming
    // https://leetcode.com/problems/regular-expression-matching/solution/
    enum Result {
        TRUE, FALSE
    }
    Result[][] memo;
    public boolean isMatchDP(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }
    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                        first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    public boolean isMatchDP2(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
