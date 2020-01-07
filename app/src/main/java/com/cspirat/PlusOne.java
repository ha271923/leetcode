package com.cspirat;

/**
 * Created by Edward on 24/07/2017.
 */
public class PlusOne {
    /**
     * 66. Plus One
     * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

     You may assume the integer do not contain any leading zero, except the number 0 itself.

     The digits are stored such that the most significant digit is at the head of the list.

     case1 : 1011 1012
     case2 : 1099 1100
     case3 : 9999 10000

     time : O(n);
     space : O(n);
     * @param digits
     * @return
     */

    public static void main(String[] args) {
        int[] input ={ 1, 9 }; // Ans: 20

        System.out.println(plusOne(input));
    }

    // 陣列
    public static int[] plusOne(int[] digits) {

        if (digits == null || digits.length == 0)
            return digits;

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++; // 一定會加1, 只是是 個位數 還是 十位數
                return digits;
            } else { // digits[i] == 9 , 考慮進位後必為0, 因為一定只加1
                digits[i] = 0;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;

        return res;
    }
}
