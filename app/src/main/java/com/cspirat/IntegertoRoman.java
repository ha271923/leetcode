package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : IntegertoRoman
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class IntegertoRoman {
    // Input: num = 1994
    // Output: "MCMXCIV"
    // Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
    public static void main(String[] args) {
        int in = 1994;
        System.out.println("intToRoman()="+intToRoman(in));
    }
    /**
     * 12. Integer to Roman

     time : O(n)
     space : O(n)
     * @param num
     * @return
     */
    // 觀察符號系統的拆解方式
    static public String intToRoman(int num) {
        // Tips: 羅馬數字 高位數的在左邊, 低位數在右邊, IV=4, VI=6=V+I可由5+1組合
        int[] values =  {1000, 900,500,400 ,100,  90, 50,  40, 10,   9,  5,   4, 1 };  // KEY: 最大值在index=0, 設定位階, 從上(大)掃到下(小)
        //                *         *        *        *        *        *        *     // KEY: 題目沒有的IV, VI , 要如何定義位階表, 新增"IV"=4, 忽略"VI"=6
        String[] strs = {"M" ,"CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};  // KEY: 4=IV , 5=V 數字大小與字母數量沒有正相關


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) { // 位階過濾法
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }
}
