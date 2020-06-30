package com.cspirat;

import com.utils.Out;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : DecodeWays
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 91. Decode Ways
 */
public class DecodeWays {
    public static void main(String[] args) {
        int ret = numDecodings_DP("2");
        Out.i("ret="+ret);
    }

    /**
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:

     'A' -> 1
     'B' -> 2
     ...
     'Z' -> 26
     Given an encoded message containing digits, determine the total number of ways to decode it.

     Example 1:
         Input: "12"
         Output: 2
         Explanation: It could be decoded as "AB" (1 2) or "L" (12).

     Example 2:
         Input: "226"
         Output: 3
         Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).


     time : O(n)


     * @param number
     * @return
     */
/*
     A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
     1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26

   分析：
         226 , 每次取一或兩個數字, 注意數字'0'不算
     情況1：
        |2|       'B'
        |22|      'U' = "BU"
     情況2：
         |2|      'B'
         |26|     'Z' = "BZ"
     情況3：
          |6|     'F' = "F"
     一共3種情況, Ans=decode number to 3 strings.

             input="226"
            dp= [0] [1] [2] [3] Length=3
            dp=| 1 |   |   |   |
            dp=| 1 | 1 |   |   |
            dp=| 1 | 1 | 1 |   |
            dp=| 1 | 1 | 2 |   |
            dp=| 1 | 1 | 2 | 3 |

           Ans: max=5


 */
    // space : O(n)
    static public int numDecodings_DP(String number) {
        if (number == null || number.length() == 0)
            return 0;
        int len = number.length();
        int[] dp = new int[len + 1]; // 因為要初始化, 所以+1
        // 如果只有一個數
        dp[0] = 1; // 初始化時, 有數的話至少有一種編碼情況, 所以dp[0]給1
        dp[1] = number.charAt(0) != '0' ? 1 : 0; // 如果唯一的數是'0'

        for (int i = 2; i <= len; i++) { // 如果有兩個以上的數 , 因為algorithm是(i-2), 所以最少要有2
            // select digital by two conditions since A~Z is 1~26.
            int oneDigital  = Integer.valueOf(number.substring(i - 1, i));  // if select One digital, ex" '2'
            int twoDigitals = Integer.valueOf(number.substring(i - 2, i));  // if select Two digitals,ex" '22'
            // Parser digital
            if (oneDigital >= 1 && oneDigital <= 9) {     // 如果只取一數字 from A-I, 'A'=1
                dp[i] += dp[i - 1]; // KEY: 參考上次的結果累加1
            }
            if (twoDigitals >= 10 && twoDigitals <= 26) { // 如果只取兩數字 from J-Z, 'J'=10
                dp[i] += dp[i - 2]; // KEY: 參考上上次的結果累加1
            }
        }
        return dp[len];
    }

    //space : O(1)
    public int numDecodings(String number) {
        if (number == null || number.length() == 0 || number.charAt(0) == '0') {
            return 0;
        }
        int c1 = 1;
        int c2 = 1;
        for (int i = 1; i < number.length(); i++) {
            if (number.charAt(i) == '0') {
                c1 = 0;
            }
            if (number.charAt(i - 1) == '1' || number.charAt(i - 1) == '2' && number.charAt(i) <= '6') {
                c1 = c1 + c2;
                c2 = c1 - c2;
            } else {
                c2 = c1;
            }
        }
        return c1;
    }
}
