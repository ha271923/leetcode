package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PalindromeNumber
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class PalindromeNumber {

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
    public boolean isPalindrome(int x) {
        if (x < 0 ||
            x != 0 && x % 10 == 0) // last digital is 0, first is not possible 0.
            return false;
        int input = x;
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + x % 10; // KEY: value revers
            x /= 10;
        }
        return input == rev;
    }
}
