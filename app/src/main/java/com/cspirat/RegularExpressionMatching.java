package com.cspirat;

// import com.hawk.leetcode.Basic.data.MyTree;

import com.utils.Out;

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

     {0~x} , {0~y}
     說明! 符號 * 的多種意義 = NOTHING/REPEAT/ANY/DEL
     "a*"  = "aa"              // 1. *: REPEAT:   重複前一字元(重複次數不限),所以結果會與前次dp[y][x-1]相同
     "a*b" = "aaab"            // 2. *: REPEAT:   重複前一字元(重複次數不限),所以結果會與前次dp[y-1][x]相同, 直到 *符號與s的字串比對全部結束
     ".*"  = "aa"              // 3. *: ANY+REPEAT:   重複前一字元(任意字元且重複次數不限),所以結果會與前次dp[y-1][x]相同, 直到 *符號與s的字串比對全部結束
     ".*"->""+"."+".*" = "ab"  // 4. *: NOTHING+ANY+NOTHING, ANY+REPEAT:   重複前一字元(任意字元且重複次數不限),所以結果會與前次dp[y-1][x]相同, 直到 *符號與s的字串比對全部結束
     "a*"  = "a"               // 5. *: NOTHING:  當作無作用的 *符號,所以結果會與前次dp[0~y][x-1]相同
     "c*a*b" = "aab"           // 6. *: DEL:      代表DEL前一字

     HAWK: * 星星符號有多種狀態如上!!

     boolean dp[y][x]的含义是s{0~y} 与 p{0~x}是否匹配。

     c* = NOTHING

     1，p.charAt(x) == s.charAt(y) : dp[y][x] = dp[y-1][x-1]
     2，If p.charAt(x) == ‘.’ : dp[y][x] = dp[y-1][x-1];
     3，If p.charAt(x) == ‘*’:
         here are two sub conditions: Tips1: (x-1)代表取出前一字元
         1，if p.charAt(x-1) != s.charAt(y) :
             dp[y][x] = dp[y][x-2] // in this case, a* only counts as empty
         2，if p.charAt(x-1) == s.charAt(y) or p.charAt(x-1) == ‘.’:
             dp[y][x] = dp[y][x-1] // in this case, a* counts as single a
             dp[y][x] = dp[y-1][x] // in this case, a* counts as multiple a
             dp[y][x] = dp[y][x-2] // in this case, a* counts as empty

     "aab", "c*aab"

     y = 1 dp[0][2] = true

     time : O(m * n)
     space : O(m * n)

     * @param s
     * @param p
     * @return
     */

     /*
     *         s="xaabyc", p="xa*b.*"  --> DP[s][p]
     *        重點在於 * 的規則!!!!
     *                    nil     x      a      *      b      .     *
     *        pattern= X=[0][x] [0][1] [0][2] [0][3] [0][4] [0][5] [0][6]  = "xa*b.*"
     *text= Y=nil[y][0] |  T   |  0   |  0   |  0   |  0   |  0   |  0    |
     *        x  [1][0] |  0   |  T   |  F   |  T=-a|  F   |  F   |  F    |
     *        a  [2][0] |  0   |  F   |  T   |  T=  |  F   |  F   |  F    |
     *        a  [3][0] |  0   |  F   |  F   |  T=+a|  F   |  F   |  F    |
     *        b  [4][0] |  0   |  F   |  F   |  F   |  T   |  F   |  T    |
     *        y  [5][0] |  0   |  F   |  F   |  F   |  F   |  T=>y|  T    |
     *        c  [6][0] |  0   |  F   |  F   |  F   |  F   |  F   | T=.*=c|
     * */

    public static void main(String[] args) {
        String s = "xaabyc";
        String p = "xa*b.*";
        System.out.println("isMatch()="+isMatch(s,p));

    }

    // Tips1: RegEx(Q10)裡的*跟DOS(Q44)裡的* , 意義不同
    // Tips2: 匹配真值表 dp[s.len+1]*[p.len+1], X與Y多一行列是因為 a* = null, a, aa, aaa... 有null的可能性
    static public boolean isMatch(String s, String p) {
        Out.i("Test substring() API=  "+"0123456789".substring(0,3)); // API: substring() 會印出 012 , 不包含3
        if (s == null || p == null)
            return false;
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        // 1. True if pattern=null to string=null
        dp[0][0] = true; // 一開始什麼都沒有(注意! dp[y][0]與dp[0][x]全為false), 所以沒有當然匹配沒有 (s=NULL) == (p=NULL)靠這個true傳遞下去dp[y][x]  https://youtu.be/l3hda49XcDE?t=303


        // KEY: charAt(x)/charAt(y) 比 dp[y+1][x+1] 差了1個位移
        //      也就是說 charAt(y=3),charAt(x=5) 存在 dp[y=4][x=6]的空格

        // 2. Deals with patterns , like a* a*b* a*b*c*
        for (int x = 0; x < p.length(); x++) { // LOOP: DEL
            char c = p.charAt(x); // 注意! 字元是x, 但是紀錄dp[][]時, 多一個位移+1
            if (c == '*' && dp[0][x-1]) { // DEL: 掃到* 就算當DEL使用, 刪除一個字元,前次是true, 所以也會是一直true下去
                Out.i("meet '*' ,dp[0][x-1] = true");
                dp[0][x+1] = true;// 注意! 字元是x, 但是紀錄dp[][]時, 多一個位移+1
            }
        }
        // 3. Deals with other patterns
        // 當前pattern p.charAt(x)  , 當前string s.charAt(y)
        // 當前pattern dp[?][x+1]   , 當前string dp[y+1][?]
        for (int y = 0; y < s.length(); y++) { // LOOP1: 再增加一string字元供比對
            for (int x = 0; x < p.length(); x++) { // LOOP2: 先掃pattern
                Out.i("s="+s.substring(0,y+1)+"    p="+p.substring(0,x+1));
                if (p.charAt(x) == s.charAt(y)) { // 標準單一字元比對
                    Out.i("dp[y+1][x+1] = dp[y][x]");
                    // 不能直接給true, 雖然當前過關, 但是dp是一個有延續的歷史紀錄, 而非真值表,
                    // ex: (aa==aa) -> dp[1][1]=true, 但若為  (ba!=aa) -> dp[1][1]=false, 需參考歷史
                    dp[y+1][x+1] = dp[y][x];
                }
                if (p.charAt(x) == '.') { // One char=ANY: 當遇到任意字元'.'
                    Out.i("meet '.'  ,dp[y+1][x+1] = dp[y][x]");
                    dp[y+1][x+1] = dp[y][x]; // 雖然'.'是任意字元, 但是dp[][]是歷史的累積, ex: (a.==ac) 但若為 (a.!=bc)
                }
                /*
     *        pattern= X=    [?][x-1]     [?][x]    [?][x+1]    [?][x+2]
     *           [y-1][?] |            |           |           |         |
     *string= Y= [y  ][?] |            |           |當前p前次s  |         |
     *           [y+1][?] |當前s前前次p |當前s前次p  | <當前sp>   |         |
     *           [y+2][?] |            |           |           |         |
     *
     * */
                // NOTHING/REPEAT/ADD/DEL: 當遇到多功能字元'*' , 可能有多種處理狀況
                if (p.charAt(x) == '*') { // *的各種處理結果, 將儲存在dp[0~y][*]的一整行
                    if (p.charAt(x - 1) != '.' &&  // 如果前一個pattern字元不是萬用字元'.'
                        p.charAt(x - 1) != s.charAt(y)) { // 並且也不是相同字母,  Ex:  ba* != aaa
                        dp[y+1][x+1] =  dp[y+1][x-1];    // DEL: 參考舊的s字串的結果(p回退2符號), dp歷史紀錄要p往前前[x-1]查, [x-1]不怕OutOfBoundary麼?
                        Out.i("meet '*'  ,dp[y+1][x+1] = dp[y+1][x-1]  MOT '.'");
                    } else { // KEY: 只能死背!
                        dp[y+1][x+1] = (dp[y+1][x  ] ||  // 遇到"*"符號時,當NOTHING用(回退1符號),  dp歷史紀錄要p往前[x  ]查(跟沒*一樣),   EX: a == (a*->a)
                                        dp[y+1][x-1] ||  // 遇到".*" 符號時(回退2符號), dp歷史紀錄要p往前前[x-1]查, EX: bcc != a.* , acc == a.*
                                        dp[y  ][x+1] );  // 遇到"*"符號時,當REPEAT用(回退1字元),   dp歷史紀錄要s往前[y  ]查,   EX: aab->aa == a* ,  aa* == a
                        Out.i("meet '*'  ,dp[y+1][x+1] = dp[y+1][x] || dp[y][x+1] || dp[y+1][x-1]");
                    }
                }
                Out.i("dp["+(y+1)+"]["+(x+1)+"] = " + dp[y+1][x+1]);
                Out.i("----------------------------------------------------");
            }
        }
        Out.print2DArray(dp);
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
        memo = new Result[text.length()+1][pattern.length()+1];
        return dp(0, 0, text, pattern);
    }
    static public boolean dp(int y, int x, String text, String pattern) {
        if (memo[y][x] != null) {
            return memo[y][x] == Result.TRUE;
        }
        boolean ans;
        if (x == pattern.length()){
            ans = y == text.length();
        } else{
            boolean first_match = (y < text.length() &&
                    (pattern.charAt(x) == text.charAt(y) ||
                            pattern.charAt(x) == '.'));

            if (x+1 < pattern.length() && pattern.charAt(x+1) == '*'){
                ans = (dp(y, x+2, text, pattern) ||
                        first_match && dp(y+1, x, text, pattern));
            } else {
                ans = first_match && dp(y+1, x+1, text, pattern);
            }
        }
        memo[y][x] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    static public boolean isMatchDP2(String text, String pattern) {
        boolean[][] dp = new boolean[text.length()+1][pattern.length()+1];
        dp[text.length()][pattern.length()] = true;

        for (int y = text.length(); y >= 0; y--){
            for (int x = pattern.length() - 1; x >= 0; x--){
                boolean first_match = (y < text.length() &&
                        (pattern.charAt(x) == text.charAt(y) ||
                                pattern.charAt(x) == '.'));
                if (x+1 < pattern.length() && pattern.charAt(x+1) == '*'){
                    dp[y][x] = dp[y][x+2] || first_match && dp[y+1][x];
                } else {
                    dp[y][x] = first_match && dp[y+1][x+1];
                }
            }
        }
        return dp[0][0];
    }

}
