package com.cspirat;

import com.utils.Out;

/**
 * Created by Edward on 25/07/2017.
 */
public class LongestCommonPrefix {
    /**
     * 14. Longest Common Prefix
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     *
     * case : "edwardshi", "edward", "edwar", "edwardshidd"
     * time : O(n);
     * space : O(1);
     *
     * @param strs
     * @return
     */
    // Question: 不是要找到match subString, 而是要prefixString, index必須從0開始
    public static void main(String[] args) {
        // String[] input= {"flower","flow","flight"};  // out="fl"
        // String[] input= {"dog","racecar","car"};     // out=""
        // String[] input= {"aaab","aaaaccc","aaaaccccc", "aaaafffff", "aaaaddddd"}; // out="aaa"
        // String[] input= {"01234","0123","01"}; // out="01"
        String[] input= {"ab","a"}; // out="a"
        // String[] input= {"ab",""}; // out=""

        String output = longestCommonPrefix(input);
        // String output = longestCommonPrefix_indexOf(input);
        // String output = longestCommonPrefix_hawk(input);
        System.out.println("out="+output);
    }

    // API: charAt(), substring()
    static public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        if (strs.length == 1) // 至少要1組str以上, 才能比較
            return strs[0];

        int matchCount = 0;
        while (true) { // LOOP1: KEY: index透過matchCount來索引每個字元char
            boolean charMatch = true;
            for (int strID = 1; strID < strs.length; strID++) { // LOOP2: 每挑一個字元, 掃所有的字串組合
                Out.i("strID="+strID+"   A="+strs[strID]+"   B="+strs[strID-1]+"    matchCount="+matchCount);
                if ( strs[strID].length()     <= matchCount     // 挑選做為比較的字串B不可能破紀錄
                  || strs[strID - 1].length() <= matchCount     // 挑選做為比較的字串A不可能破紀錄
                  || strs[strID].charAt(matchCount) != strs[strID - 1].charAt(matchCount)) // 任兩個字串AB各挑一個字元比對
                {
                    charMatch = false; // 一發現一個字元比對失敗, 立即停止比對, 送出答案!
                    break;
                }
            }
            if (charMatch)
                matchCount++; // 下次比對下一個字元
            else
                break;
            Out.i("    matchCount="+matchCount);
        }
        return strs[0].substring(0, matchCount); // 三個字串從index=0開始的最長重疊區段
    }

    // Tips: Pefix必須從index=0 開始的SubString, 而非中間段落的某個longest Common Prefix
    public static String longestCommonPrefix_indexOf(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        String strResult = strs[0]; // i=0在此
        for (int i = 1; i < strs.length; i++) { // LOOP1: 從i=1開始
            // KEY: indexOf API的特性, 必須:
            //          "長字串".indexOf("短字串")  , ex: "012".indexOf("0") = 0
            // API: indexof() API,將參數字串在整組字串的第一次出現位置回傳 "012 3".indexOf("12")回傳 1
            // API: indexof() API,將參數字串在整組字串的第一次出現位置回傳 "12".indexOf("012 3")回傳 -1
            // API: indexof() API,將參數字串在整組字串的第一次出現位置回傳 "123".indexOf("")回傳 0
            // TEST: Out.i("index="+"012 3".indexOf("12")); // index=1
            Out.i("ret="+"ab".indexOf("abc")); // index= -1 , of裡面的字串要比較短
            Out.i("strs["+i+"]="+strs[i]+".indexOf("+strResult+")    ret="+strs[i].indexOf(strResult));
            while (strs[i].indexOf(strResult) != 0) { // LOOP2: KEY: 使用indexOf()搜尋當作比對字串, 當不符合時, -1
                strResult = strResult.substring(0, strResult.length() - 1); // KEY: indexOf API的參數長度要比較短, 縮短1個字元再呼叫一次indexOf看看
            }
        }
        return strResult;
    }

     /*
        Input:   ["dog","racecar","car"]
        Output:   "do"
        Expected: ""
     */
     // Hawk: ERROR! Index計算錯誤, 小BUG一堆, 驗證失敗
    public static String longestCommonPrefix_hawk(String[] strs) {
        int shortest_len = 0;
        if(strs.length == 0)
            return "";

        if(strs.length >=2 ) {
            shortest_len = strs[0].length();
            for (int i = 1; i < strs.length; i++) {
                if(strs[i].equals("") || strs[0].equals(""))
                    return "";

                shortest_len = Math.min(shortest_len, strs[i].length());
            }
        } else {
            return strs[0];
        }
        if(shortest_len == 0)
            return "";
        int endCharIdx = longestCommonPrefix_hawk_Idx(strs, shortest_len);
        // int endCharIdx = longestCommonPrefix_hawk_Crop(strs, shortest_len);
        if(endCharIdx != 0)
            return strs[0].substring(0, endCharIdx);
        else
            return "";
    }

    public static int longestCommonPrefix_hawk_Idx(String[] strs, int shortest_len) {
        char ch= strs[0].charAt(0);
        int  endCharIdx = 0;
        for(int charIdx = 0; charIdx< shortest_len; charIdx++) {
            ch= strs[0].charAt(charIdx);
            for(int strArrIdx = 0; strArrIdx< strs.length; strArrIdx++) {
                if(ch != strs[strArrIdx].charAt(charIdx)) {
                    return charIdx;
                }
            }
            endCharIdx = charIdx;
        }
        return endCharIdx;
    }

    // ERROR: input=["caa","","a","acb"]
    public static int longestCommonPrefix_hawk_Crop(String[] strs, int shortest_len) {
        int count = 0;
        for(int i=0; i< shortest_len ; i++) {
            for(int j=1; j< strs.length ; j++) {
                if(strs[j-1].charAt(i) != strs[j].charAt(i)){
                    return count;
                }
            }
            count++;
        }
        return count;
    }

    // API: indexOf(), substring()
    public static void TEST_StringAPIs(String[] args) {
        System.out.println("indexOf()=" + "abc".indexOf("bc"));  // indexof()是search功能: 會回傳 1, 因為abc比對bc後, 發現bc在abc(字典)的idx=1可以找到全符合字串
        System.out.println("indexOf()=" + "abc".indexOf("cbc")); // indexof()是search功能: 會回傳-1, 因為abc比對cbc後, 發現彼此找不到全符合字串
        System.out.println("length()=" + "abc".length()); // 會回傳 3
        //   substring(起始offset,輸出字元數量) => "abc".substring(1,"abc".length()-1) = "b"
        System.out.println("substring()=" + "abc".substring(0,"abc".length()-1)); // 會回傳ab
        System.out.println("substring()=" + "abc".substring(1,"abc".length()-1)); // 會回傳ab
    }

}
