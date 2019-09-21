package com.cspirat;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestSubstringWithoutRepeatingCharacters
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * 3. Longest Substring Without Repeating Characters
     * Examples:

     Given "abcabcbb", the answer is "abc", which the length is 3.

     Given "bbbbb", the answer is "b", with the length of 1.

     Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
     "pwke" is a subsequence and not a substring.


     time : O(n)
     space : O(n)
     * @param s
     * @return
     */

    // subString重複沒關希, 重點是char不能重複, ex: abcabcbb, result = abc = 3
    // Tips1: 這一題subString最長的結果是abcdefghijklmnopqrstuvwxyz = 26
    // Tips2: j代表subString的head在allString中的絕對位置, 而非相對位置, 所以j不需歸0
    // KEY: sliding Window, 字元不用往回掃
    // KEY: 題目要看懂!!! 關鍵是當前一個字元c與前次一個字元c比對, 而非整個字組word比對!
    static public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;

        // 組合超多
        // a, aa, ab, ac.....az, aaa, aab...aaz, aba....abz  ................azzzzzzzzzzzzzzz
        // b, ba, bb, bc.....bz, baa, bab...baz, bba....bbz, ................bzzzzzzzzzzzzzzz
        //    ...
        // z, za, zb, zc.....zz, zaa, zab...zaz, zba....zbz, ................zzzzzzzzzzzzzzzz
        // 掃描所有輸入字元, 若沒有重複, 該subString長度繼續累積+1
        for (int right = 0, left = 0; right < s.length(); right++) { // i作為allString的iterator, j作為subString的第一字元位置
            // 比對有沒有重複
            if (map.containsKey(s.charAt(right)) == true) { // 檢查map中是否已有該字元
                left = Math.max(left, map.get(s.charAt(right)) + 1); // 若有該字元, 代表重複, 所以noRepeatStartPos推進
            } // else {} 的情況代表不更新noRepeatStartPos, 這樣就可計算(scanPos-noRepeatStartPos)=Window寬度
            // KEY: <K,V> = <當前掃到的'一個字元', 當前位置>
            map.put(s.charAt(right), right); // <K,V> , <c1,i1><c2,i2><c3,i3>...<cn,in> = 每個字元char都是一組<K,V>
            res = Math.max(res, right - left + 1); // KEY: sliding window的寬度
        }
        return res;
    }

    // Tips1: 這一題subString最長的結果是abcdefghijklmnopqrstuvwxyz = 26, 所以用SET
    // Tips2: 第二個iterator left代表allString的左端
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;
        HashSet<Character> set = new HashSet<>(); // 這個set裡面存的是全部的已加入char成員, 也就是存滿情況下set = abcdefghijklmnopqrstuvwxyz
        int res = 0;
        for (int right = 0, left = 0; right < s.length(); right++) {
            if (set.contains(s.charAt(right))) {
                char c = s.charAt(left++);// left變數只在此處改變
                set.remove(c); // Q:移除的並不是set內重複的字元????
            } else {
                set.add(s.charAt(right));
                res = Math.max(res, set.size()); // 判斷是否破紀錄需更新res, res為目前找到的最長字的長度, 題目不需要subString, 只需要len, 且set裡面也不會存subString
            }
        }
        return res;
    }
}


class Main {
    // Program to create a generic array in Java
    public static void main(String[] args)
    {
                   // Sliding Window( No loop back )
                   //     >>>>>>>>>[    ]----
        String strTest = "pwwwwwwwwkewccckew"; // len=18
        System.out.println(LongestSubstringWithoutRepeatingCharacters
                .lengthOfLongestSubstring(strTest));
    }
}