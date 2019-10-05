package com.cspirat;

/**
 * Created by Edward on 28/07/2017.
 */
public class ValidPalindrome {
    /**
     * 125. Valid Palindrome
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

     For example,
     "A man, a plan, a canal: Panama" is a palindrome.
     "race a car" is not a palindrome.

     Note:
     Have you consider that the string might be empty? This is a good question to ask during an interview.

     For the purpose of this problem, we define empty string as valid palindrome.

     case : "A man, a plan, a canal: Panama"

     time : O(n)
     space : O(1);

     * @param s
     * @return
     */
    // Tips1: Character.isLetterOrDigit(str) 這支API的使用
    // Tips2: L++, R--, L<R 這些palindrome的原則
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int L = 0;
        int R = s.length() - 1;
        while (L < R) {
            while (L < R && !Character.isLetterOrDigit(s.charAt(L))) {
                L++;
            }
            while (L < R && !Character.isLetterOrDigit(s.charAt(R))) {
                R--;
            }
            if (Character.toLowerCase(s.charAt(L)) != Character.toLowerCase(s.charAt(R))) {
                return false;
            }
            L++;
            R--;
        }
        return true;
    }
    public static void main(String[] args) {
        boolean res = isPalindrome("aba");
        System.out.println("res = " + res);
    }
}
