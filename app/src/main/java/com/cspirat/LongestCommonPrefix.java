package com.cspirat;

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
    // API: indexOf(), substring()
    public static void StringAPIs_test(){
        System.out.println("indexOf()=" + "abc".indexOf("bc"));  // indexof()是search功能: 會回傳 1, 因為abc比對bc後, 發現bc在abc(字典)的idx=1可以找到全符合字串
        System.out.println("indexOf()=" + "abc".indexOf("cbc")); // indexof()是search功能: 會回傳-1, 因為abc比對cbc後, 發現彼此找不到全符合字串
        System.out.println("length()=" + "abc".length()); // 會回傳 3
                      //   substring(起始offset,輸出字元數量) => "abc".substring(1,"abc".length()-1) = "b"
        System.out.println("substring()=" + "abc".substring(0,"abc".length()-1)); // 會回傳ab
        System.out.println("substring()=" + "abc".substring(1,"abc".length()-1)); // 會回傳ab
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        String strResult = strs[0]; // stringA for compare
        for (int i = 1; i < strs.length; i++) { // compare string idx from idx=1...
            // API: int indexOf(String str): strs[i]的字串與strResult進行比對,
            while (strs[i].indexOf(strResult) != 0) { // KEY: 不等於0代表兩字串有相符合的字元
                strResult = strResult.substring(0, strResult.length() - 1);
            }
        }
        return strResult;
    }

    // API: charAt(), substring()
    static public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        if (strs.length == 1) // 至少要1組str以上, 才能比較
            return strs[0];

        int matchCount = 0;
        while (true) { // index透過matchCount來索引每個字元char
            boolean charMatch = true;
            for (int strID = 1; strID < strs.length; strID++) { // 挑字串迴圈
                if (   strs[strID].length() <= matchCount       // 挑選做為比較的字串B不可能破紀錄
                    || strs[strID - 1].length() <= matchCount   // 挑選做為比較的字串A不可能破紀錄
                    || strs[strID].charAt(matchCount) != strs[strID - 1].charAt(matchCount)) { // 任兩個字串AB各挑一個字元比對
                    charMatch = false; // 比對失敗
                    break;
                }
            }
            if (charMatch)
                matchCount++;
            else
                break;
        }
        return strs[0].substring(0, matchCount);
    }

    public static void main(String[] args) {
        String[] input1= {"flower","flow","flight"};  // out="fl"
        String[] input2= {"dog","racecar","car"};     // out=""
        String[] input3= {"aaab","aaaaccc","aaaaccccc", "aaaafffff", "aaaaddddd"}; // out=""
        String output = longestCommonPrefix(input3);
        System.out.println("out="+output);
    }
}
