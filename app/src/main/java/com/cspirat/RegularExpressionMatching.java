package com.cspirat;

// import com.hawk.leetcode.Basic.data.MyTree;

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
     Implement regular expression matching with support for '.' and '*'.
     注意!!! 這題Q10的 * 符號, 與 Q44: WildcardMatching的功能不同

      EX: s=ac , p= a*c , 在Q44是false, Q10是true

     Q10:
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

     說明! 符號 * 的多種意義
     "a*"  = "aa"           // 1. *: REPEAT: 代表與前一字元相同且重複次數不限
     "a*b" = "aaab"         // 2. *: REPEAT: 代表與前一字元相同且重複次數不限
     ".*"  = "aa"           // 3. *: REPEAT: 代表與前一字元相同
     ".*"->"."+".*" = "ab"  // 4. *: ANY:    代表與前一字元相同, 若前一字元是 . , 代表可以是任意字元
     "c*a*b" = "aab"        // 5. *: DEL:    代表DEL前一字元

     HAWK: * 星星符號有多種狀態如上!!

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

     /*
     *         s="xaabyc", p="xa*b.*"  --> DP[s][p]
     *        重點在於 * 的規則!!!!
     *                            x      a      *      b      .     *
     *        pattern= X=[0][x] [0][1] [0][2] [0][3] [0][4] [0][5] [0][6]  = "xa*b.*"
     *text= Y=   [y][0] |  T   |  0   |  0   |  0   |  0   |  0   |  0    |
     *        x  [1][0] |  0   |  T   |  0   |  T=-a|  0   |  0   |  0    |
     *        a  [2][0] |  0   |  0   |  T   |  T=  |  0   |  0   |  0    |
     *        a  [3][0] |  0   |  0   |  0   |  T=+a|  0   |  0   |  0    |
     *        b  [4][0] |  0   |  0   |  0   |  0   |  T   |  0   |  0    |
     *        y  [5][0] |  0   |  0   |  0   |  0   |  0   |  T=>y|  0    |
     *        c  [6][0] |  0   |  0   |  0   |  0   |  0   |  0   | T=.*=c|
     * */

    public static void main(String[] args) {
        String s = "aab";
        String p = "a*b";
        System.out.println("isMatch()="+isMatch(s,p));
    }

    // Tips1: RegEx(Q10)裡的*跟DOS(Q44)裡的* , 意義不同
    // Tips2: 匹配真值表 dp[s.len+1]*[p.len+1], X與Y多一行列是因為 a* = null, a, aa, aaa... 有null的可能性
    static public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 1. True if pattern null to null
        dp[0][0] = true; // 靠這個true傳遞下去dp[y][x]  https://youtu.be/l3hda49XcDE?t=303

        // 2. Deals with patterns  like a* a*b* a*b*c*
        for (int x = 0; x < p.length(); x++) {
            char c = p.charAt(x);
            if (c == '*' && dp[0][x - 1]) { // 掃到* 並且前次相符
                dp[0][x + 1] = true;
            }
        }
        // 3. Deals with other patterns
        for (int y = 0; y < s.length(); y++) { // scan s String
            for (int x = 0; x < p.length(); x++) { // scan p Pattern
                if (p.charAt(x) == s.charAt(y)) { // single char matching
                    dp[y + 1][x + 1] = dp[y][x];
                }
                if (p.charAt(x) == '.') { // any char matching
                    dp[y + 1][x + 1] = dp[y][x];
                }
                if (p.charAt(x) == '*') { //  '*' found
                    if (p.charAt(x - 1) != s.charAt(y) && p.charAt(x - 1) != '.') { // NOT  aa==a*  or a*==.*
                        dp[y + 1][x + 1] = dp[y + 1][x - 1];
                    } else { // IS  aa==a*  or a*==.*
                        dp[y + 1][x + 1] = (dp[y + 1][x] || dp[y][x + 1] || dp[y + 1][x - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    // Approach 1: Recursion
    // https://leetcode.com/problems/regular-expression-matching/solution/
    static public boolean isMatch2(String text, String pattern) {
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
    static Result[][] memo;
    static public boolean isMatchDP(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }
    static public boolean dp(int i, int j, String text, String pattern) {
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

    static public boolean isMatchDP2(String text, String pattern) {
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
