package com.cspirat;

import com.utils.Out;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : helperAddresses
 * Creator : Edward
 * Date : Dec, 2017
 * Descrstion : 93. Restore IP Addresses
 */
public class RestoreIPAddresses {
    public static void main(String[] args) {
        String input = "011022033044";
        // String input = "25525511135";
        List<String> ret = restoreIpAddresses(input);
        Out.i("ret="+ret+"  conditions="+ret.size());
    }
    /**
     * Given a string containing only digits, restore it by returning all possible valid s address combinations.

     For example:
     Given "25525511135",

     return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

     time : O(3^4) => O(1) => O(3^n)
     space : O(n)
     
     * @param input
     * @return
     */
    // ??? 不太考
    static public List<String> restoreIpAddresses(String input) {
        List<String> output = new ArrayList<>();
        decodeSegIp_recur(output, input, 0, "", 0);
        return output;
    }
    // 每遞迴一次,代表一次ip區段獲得
    static public void decodeSegIp_recur(List<String> outputs, String input, int chIdx, String resolved, int segCount) {
        if (segCount > 4) // 超過4個節段還有剩下字元, 一定無法成為ip address
            return;
        if (segCount == 4 && chIdx == input.length()) { // KEY: 只有字剛好完全掃完,並且也是4個節段,才是ip address
            outputs.add(resolved);
            return;
        }
        for (int i = 1; i < 4; i++) { // 每區段最多取三個字元
            if (chIdx + i > input.length())
                break;
            // Examples:
            // 111.222.333.444   三個點符號分隔
            // 011022033044 -> 0.110.22.0.33044 錯誤! 題目要0-255所以0開頭的,下個字元需分隔
            String segIpValue = input.substring(chIdx, chIdx + i);
            // KEY: 題目要求 each integer is between 0 and 255
            if ((segIpValue.startsWith("0") && segIpValue.length() > 1) || // '0'開頭start的,後續字串一定是放到下一ip節段(ex: 12.0.33.44,沒有033,)
                (i == 3 && Integer.parseInt(segIpValue) >= 256)) // 取三個字元後的節段數值範圍超過255, 那就不要遞迴了(ex: 286 > 255)
                continue; // i==3時, 下次回圈會直接跳離
            // 第四個區段不用小數點分隔 (segCount == 3 ? "" : ".")
            // 因長度未知,故演算法每取一個數就進入遞迴
            decodeSegIp_recur(outputs, input, chIdx + i, resolved + segIpValue + (segCount == 3 ? "" : "."), segCount + 1);
        }
    }
}
