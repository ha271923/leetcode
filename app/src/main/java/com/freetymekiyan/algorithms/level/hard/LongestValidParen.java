package com.freetymekiyan.algorithms.level.hard;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')'
 * find the length of the longest valid (well-formed) parentheses substring.
 *
 * For "(()", the longest valid parentheses substring is "()", which has length  * = 2.
 * Another example is ")()())", where the longest valid parentheses substring
 * is "()()", which has length = 4.
 *
 * Follow up:
 *
 * What if there are curly bracs and brakets as well? {} []?
 *
 * Tags: DP, String
 */
// QUESTION:
//     題目是求"最長的連續區間"長度,而非累積長度
//     當輸入為"(())))))()())"時,
//     答案為   _2_  或  _4_ , 取大的為4
class LongestValidParen {
    public static void main(String[] args) {
        String[] strInputArr = {
                "(())))))()())", // 4
                "))()", // 2
                "()", // 2
                "(()", // 2
                "((()", // 2
                "(())", // 4
                "()(()", // 注意! Ans= 2, not 4
                "()(()(", // 2
                "()(()((", // 2
                "()(()(((", // 2
                "(((((()", // 2
                ")()())", // 注意! Ans= 4
                "((((((", // 0
                "))))))", // 0
                ")()(())", // 6
                "(())()", // 6
                ")()()" // 4
        };

        for(String s: strInputArr) {
            System.out.println("strInput=" +s);
            // int ret = longestValidParentheses_stack_Edward_BUG(s);
            // int ret = longestValidParentheses_DP(s);
            int ret = longestValidParentheses_stack2(s);
            System.out.println("ret=" + ret);
        }
    }

    // KEY: push進去的不是符號本身, 而是index
    // Tips1: String Empty代表, str="" 或是 str = new String();
    // Tips2: 不管前面多少失敗的))((, 不可能對稱符號, 只要掃到'('下個字元是')'就成立
    // Tips3: 動畫說明 https://leetcode.com/articles/longest-valid-parentheses/
    public static int longestValidParentheses_stack2(String input) {
        int n = input.length();

        // Create a stack and push -1 as initial index to it.
        Stack<Integer> st = new Stack<>();
        // 以免當輸入是"()???????" 在st.peek()操作時, 因為沒有元素, 導致stack null exception,
        // 設為-1是讓1-(-1)=2
        st.push(-1);

        int maxLen = 0;

        /**
         *   char  | 0 | 1 | 2 | 3 |
         *          (    (   )
         *        push push pop        if char-'(' push ,  pop if char=')'
         *                                       |             |
         *                                       |idx=1=(=push |
         *                                      |idx=0=(=push |
         *                                      |_____-1______|   1-(-1)=2
         *                                     stack
         */
        for (int i=0; i<n; i++)  // 掃描每一個字元
        {
            // If opening bracket, push index of it
            if (input.charAt(i) == '(') { // 掃描到的符號是'('
                st.push(i);
            }
            else // 掃描到的符號是')'
            {
                st.pop(); // 100% 是')', 因為題目要的是maxLen, 所以不需要保留pop出來的元素
                if (st.empty()) { // 避免st.peek()發生stack null exception, 所以一發現EMPTY就要塞值
                    st.push(i);
                } else {
                    // 題目是求"最長的連續區間"長度,而非累積長度
                    // 當輸入為"(())))))()())"時,
                    // 答案為   _2_  或  _4_ , 取大的為4
                    maxLen = Math.max(maxLen, i - st.peek()); // 初始化時的st.push(-1)可以達到 1-(-1)=2當輸入為"()"時
                }
            }
        }

        return maxLen;
    }


    // bug
    public static int longestValidParentheses_stack_Edward_BUG(String input) {
        if (input == null)
            return 0;
        Stack<Integer> st = new Stack<Integer>();
        int maxLen = 0;
        int len = 0;

        for (int i = 0; i < input.length(); i++) { // 掃描每一個字元
            if (input.isEmpty()) {
                len = 0;
            } else if (input.charAt(i) == '(') {
                st.push(i); // KEY: push進去的不是符號本身, 而是index
            } else { // 三條件都成立!
                // 1. ')' &&         -> 掃描到的符號是')'
                // 2. not Empty &&   ->
                // 3. peek() == '('  -> 目前stack最上面有一個'('
                if(!st.empty()) {
                    int p = st.peek();
                    System.out.println("p="+p);
                }
                int matchedPos = st.pop();
                int matchedLen = i - matchedPos + 1;
                if (input.isEmpty()) { // ()()
                    len += matchedLen;
                    matchedLen = len;
                } else {
                    if(!st.empty())
                        matchedLen = i - st.peek(); // BUG!!!
                }
                maxLen = Math.max(maxLen, matchedLen);
            }
        }
        return maxLen;
    }

    /**
     * Optimized DP
     * Build a stack for indices of open parentheses
     * Traverse the string, if current is open paren, push to stack
     * Otherwise, its close paren.
     * If stack is empty, no open paren left, reset len to 0.
     * If not, pop the index from stack, matchedLen = current index - index of
     * pop open paren + 1
     * If stack is empty, means this matchedLen can be added to the whole len
     * If not,
     */
    public static int longestValidParentheses_DP(String s) {
        if (s == null || s.length() == 0) return 0;

        Stack<Integer> st = new Stack<>(); // Save indices of '('
        int[] dp = new int[s.length()]; // Store the length of the current longest valid sequence.

        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            }  else if (st.isEmpty()) {
                continue;
            } else if (st.peek() > 0) {
                dp[i] = 2 + dp[st.pop() - 1] + dp[i - 1]; // connect two valid sequences, or increase the length of current valid sequence.
            } else {
                dp[i] = 2 + dp[i - 1]; // leftmost char is a '('
                st.pop();
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }


}
