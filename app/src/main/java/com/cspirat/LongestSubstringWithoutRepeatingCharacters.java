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

    // Tips1: 這一題subString最長的結果是abcdefghijklmnopqrstuvwxyz = 26
    // Tips2: j代表subString的head在allString中的絕對位置, 而非相對位置, 所以j不需歸0
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) { // i作為allString的iterator, j作為subString的第一字元位置
            if (map.containsKey(s.charAt(i))) { // map中找到找到
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i); // <K,V> , <c1,i1><c2,i2><c3,i3>...<cn,in> = 每個字元char都是一組<K,V>
            res = Math.max(res, i - j + 1); // 因為index由0開始, 但元素數量是1開始, 所以+1, 更新紀錄res目前找到的最長字的長度
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
