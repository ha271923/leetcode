package com.cspirat;

/**
 * Created by Edward on 28/07/2017.
 */
public class AddBinary {
    /**
     * 67. Add Binary
     * Given two binary strings, return their sum (also a binary string).

     For example,
     a = "11"
     b = "1"
     Return "100".

     time : O(n);
     space : O(n);
     * @param a
     * @param b
     * @return
     */
    public static void main(String[] args) {

        // System.out.println(addBinary("11","1"));
        System.out.println(addBinary("1010","1011"));
    }
/*
   LOOP:   9876543210
           ----------
   sBin1         1010
   sBin2         1011
           ----------
                    1
                  10    => 2 -> sum=a[1]+b[1]+carry, carry=sum/2=1, sb=sum%2=0
                  0
                10      => 2 -> sum=a[3]+b[3]+carry, carry=sum/2=1, sb=sum%2=0
           ----------
     Ans        10101

 */
    public static String addBinary(String sBin1, String sBin2) {
        StringBuilder sb = new StringBuilder();
        int i1 = sBin1.length() - 1;
        int i2 = sBin2.length() - 1;
        int carry = 0; // 餘數
        while (i1 >= 0 || i2 >= 0) { // LOOP: i1與i2長度可能不相等
            int sum = carry;
            if (i1 >= 0) // 此時i2可能已經 < 0
                sum += sBin1.charAt(i1) - '0';
            if (i2 >= 0) // 此時i1可能已經 < 0
                sum += sBin2.charAt(i2) - '0';
            // KEY: algorithm
            carry = sum / 2;
            sb.append(sum % 2);

            System.out.println("i1="+i1+"  i2="+i2+" sb="+sb +"  sum="+sum);
            i1--;i2--;
        }
        if (carry != 0) { // Tips: 最後的餘數
            sb.append(carry);
        }
        return sb.reverse().toString(); // KEY: 輸出前將append後的次序,反轉
    }
}
