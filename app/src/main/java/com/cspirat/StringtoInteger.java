package com.cspirat;

import com.utils.Out;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : StringtoInteger
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 8. String to Integer (atoi)
 */
public class StringtoInteger {
    public static void main(String[] args) {
        int res;
        res = myAtoi_Hawk("-91283472332");
        Out.i("res="+res);
    }
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
    static public int myAtoi(String str) {
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

    static public int myAtoi_Hawk(String str) {
        long temp = 0;
        int res = 0;
        int num = 0;
        int len = str.length();
        int sign = 1;
        char ch;
        int  chIdx = 0;
        ch = str.charAt(0);
        while ( ch == ' ' ) {
            ch = str.charAt(chIdx);
            chIdx++;
        }

        ch = str.charAt(chIdx);
        if(ch == '-') // Bug: 符號為'+'也應該處理
            sign = -1;
        else if( ch <'0' || ch > '9' )
            return res;

        for (int i = 0; i < len ; i++) { // Bug: len不應包含所有字串
            ch = str.charAt(i);

            if(ch >= '0' && ch <='9') {
                num = ch - '0';
                temp= temp*10 + num;
            }

            if(temp > Integer.MAX_VALUE) // Bug: temp還沒乘以sign
                return Integer.MAX_VALUE;
            else if(temp < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            else
                ;
        }
        res = (int) temp;
        return res * sign;

    }

}
