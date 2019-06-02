package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : StringtoInteger
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 8. String to Integer (atoi)
 */
public class StringtoInteger {
    /**
     * time : O(n)
     * space : O(1)
     * @param str
     * @return
     */

    /**
     Example 1:
      Input: "42"
         Output: 42
     Example 2:
         Input: "   -42"
         Output: -42
     Example 3:
         Input: "4193 with words"
         Output: 4193
     Example 4:
         Input: "words and 987"
         Output: 0
     Example 5:
         Input: "-91283472332"
         Output: -2147483648
     */
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        str = str.trim(); // removed " " at front of str
        char firstChar = str.charAt(0);
        int sign = 1;
        int start = 0;
        long res = 0;
        // parsing sign symbol
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i < str.length(); i++) { // parsing ASCII
            if (!Character.isDigit(str.charAt(i))) { // API: Character.isDigit(char), return if nnot ASCII
                return (int) res * sign;
            }
            res = res * 10 + str.charAt(i) - '0'; // ASCII value to Integer value
            if (sign == 1 && res > Integer.MAX_VALUE) return  Integer.MAX_VALUE;
            if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
        }
        return (int) res * sign;
    }
}
