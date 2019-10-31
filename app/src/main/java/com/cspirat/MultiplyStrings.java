package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MultiplyStrings
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 43. Multiply Strings
 */
public class MultiplyStrings {
    /**
     * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

     Note:

     The length of both num1 and num2 is < 110.
     Both num1 and num2 contains only digits 0-9.
     Both num1 and num2 does not contain any leading zero.
     You must not use any built-in BigInteger library or convert the inputs to integer directly.

     time : O(n * m)
     space : O(n + m)

     * @param num1
     * @param num2
     * @return
     */
    // Tips: 使用int[], 手繪乘法運作圖
    static public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null)
            return "0";
        int[] digits = new int[num1.length() + num2.length()]; // KEY: 因為int有數值上限, 改用陣列數值
        for (int i = num1.length() - 1; i >= 0; i--) { // 從個位數開始取出計算
            for (int j = num2.length() - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = product + digits[p2]; // KEY: 每次的sum需取出前次的進位值digits[p2],
                digits[p1] += sum / 10; // KEY: 乘法在每次進位時, 需累加前次結果
                digits[p2] = sum % 10;
            }
        }
        // 把int[] digits轉換成字串
        StringBuilder res = new StringBuilder();
        for (int digit : digits) {
            if (!(digit == 0 && res.length() == 0)) {
                res.append(digit);
            }
        }
        return res.length() == 0 ? "0" : res.toString();
    }

    public static void main(String[] args) {
        String input1 ="333333333333";
        String input2 ="222222222222";
        String ret = multiply(input1, input2); // ans: 74074074073925925925926
        System.out.println("   "+input1);
        System.out.println(" X "+input2);
        System.out.println("________________________________________________________________________");
        System.out.println(ret);
    }
}
