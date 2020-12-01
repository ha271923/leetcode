package com.cspirat;

import com.utils.Out;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PalindromeNumber
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        boolean ret = isPalindrome(121);
        Out.i("ret="+ret);
    }
    /**
     * 9. Palindrome Number

     time : O(n) space : O(1)
     * @param x
     * @return
     *
     * Example 1:
     *   Input: 121
     *   Output: true
     * Example 2:
     *   Input: -121
     *   Output: false
     *   Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
     * Example 3:
     *   Input: 10
     *   Output: false
     * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
     */
    static public boolean isPalindrome(int x) {
        if (x < 0 ||
            x != 0 && x % 10 == 0) // last digital is 0, first is not possible 0.
            return false;
        int input = x;
        int rev = 0;
        while (x > 0) { // 把整個reverse後的值, 求出後, 數字比對
            rev = rev * 10 + x % 10; // KEY: value revers
            x /= 10;
        }
        return input == rev;
    }



    // Bug: 把數字當文字比對, 太慢
    static public boolean isPalindrome_Hawk(int x) {
        boolean res = true;
        int len = 0;
        int L=0,R=0;
        if(x<0)
            return false;
        int temp =x;
        while( temp!=0 ) { // calc len
            temp = x /= 10;
            len++;
        }

        if(len == 1) // only 1
          return true;

        while(len/2 >= 1) { // above 1
            R = x % 10;
            L = x / 10^len;
            x = ( x - L*10^(len-1) - R )/ 10 ;
            len--;
            if( L != R )
                return false;
        }
        return res;
    }
}
