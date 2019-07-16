package com.cspirat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 27/07/2017.
 */
public class GenerateParentheses {
    /**
     * 22. Generate Parentheses
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

     For example, given n = 3, a solution set is:
     [
     "((()))",
     "(()())",
     "(())()",
     "()(())",
     "()()()"
     ]


     time : O(n!) (2^n)
     space : O(n)

     卡特兰数：
     (0,n-1) (1,n-2) (2,n-3) ... (n-1,0)

     * @param n
     * @return
     */
    /*
    [ L='(' ,  R=')' ]

                  /N2LN1LN0L = ((( = P0L
             N2LN1L
            /     \N2LN1LN0R = (() = P1L,P2L
         N2L
        /   \     /N2LN1RN0L = ()( = P3L,P4L
       /     N2LN1R
      /           \N2LN1RN0R = ()) = P1R,P3R
 '(' N3
      \           /N2RN1LN0L = )(( = pruning避開! L端非以'('開頭
       \     N2RN1L
        \   /     \N2RN1LN0R = )() = P2R,P4R
         N2R
            \     /N2RN1RN0L = ))( = pruning避開! L端非以'('開頭
             N2RN1R
                  \N2RN1RN0R = ))) = P0R

                                        0 = "(((+)))" = N2LN1LN0L + N2RN1RN0R
                                        1 = "(()+())" = N2LN1LN0R + N2LN1RN0R
                                        2 = "(()+)()"
                                        3 = "()(+())"
                                        4 = "()(+)()"
    * */
    // Tips: pair數字大小, 越大代表越上層, 越小代表越下層
    // Tips: 未填入左括号數超过右括号數，它肯定不是合法序列
    public static List<String> generateParenthesis(int pair) {
        List<String> res = new ArrayList<>();
        if (pair == 0) return res;
        helper(res, "", pair, pair);
        return res;
    }
    // Backtracking是一種窮舉搜尋的演算法，目標是找尋所有可能的答案，可分為兩個概念，分別是enumerate(枚舉)與pruning(剪枝)
    //  (1)enumerate(枚舉):每一步列出所有可能的下一步一一測試
    //  (2)pruning(剪枝):遇到不符合條件的下一步便省略，不再繼續枚舉
    public static void helper(List<String> res, String s, int left, int right) {
        if (left > right) { // KEY: pruning, 如果未填入左括号數超过右括号數，它肯定不是合法序列, ex: ())不合法, (()合法
            return;
        }
        if (left == 0 && right == 0) { // 左右括号均填畢
            res.add(s);
            return;
        }
        if (left > 0) {
            helper(res, s + "(", left - 1, right);  // 注意!遞迴函數無分左右 helper
        }
        if (right > 0) {
            helper(res, s + ")", left, right - 1); // 注意!遞迴函數無分左右 helper
        }
    }
}
