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
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String res = strs[0]; // compare
        for (int i = 1; i < strs.length; i++) { // compare idx from idx=1...
            while (strs[i].indexOf(res) != 0) { // KEY: compare by indexOf API input/return
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }
}
