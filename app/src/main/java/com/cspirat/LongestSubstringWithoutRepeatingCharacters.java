package com.cspirat;

import com.utils.Out;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestSubstringWithoutRepeatingCharacters
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class LongestSubstringWithoutRepeatingCharacters {
    // Program to create a generic array in Java
    public static void main(String[] args)
    {
        // Sliding Window( No loop back )
        //     >>>>>>>>>[    ]----
        // String strTest = "pwwwwwwwwkewccckew"; // len=18
        String strTest = "pwwkew"; // len=18
        int res;
        res = lengthOfLongestSubstring_hashmap(strTest);
        // res = lengthOfLongestSubstring_HashSet(strTest);
        System.out.println(res);
    }
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

    // subString重複沒關希, 重點是所選subString裡的char不能重複, ex: abcabcdbb, result = abcd = 4
    // Tips1: 這一題subString最長的結果是abcdefghijklmnopqrstuvwxyz = 26
    // Tips2: j代表subString的head在allString中的絕對位置, 而非相對位置, 所以j不需歸0
    // KEY: sliding Window, 字元不用往回掃
    // KEY: 題目要看懂!!! 關鍵是當前一個字元c與前次一個字元c比對, 而非整個字組word比對!
    static public int lengthOfLongestSubstring_hashmap(String s) {
        if (s == null || s.length() == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int width = 0;

        // 組合超多
        // a, aa, ab, ac.....az, aaa, aab...aaz, aba....abz  ................azzzzzzzzzzzzzzz
        // b, ba, bb, bc.....bz, baa, bab...baz, bba....bbz, ................bzzzzzzzzzzzzzzz
        //    ...
        // z, za, zb, zc.....zz, zaa, zab...zaz, zba....zbz, ................zzzzzzzzzzzzzzzz
        /*
        ex: pwwkew
                p     w    w      k    e      w
               [?0]  [?1]  [?2]  [?3]  [?4]  [?5]
            K   p     w     w     k     e     w
            V   0     1     2     3     4     5
         */
        // 掃描所有輸入字元, 若沒有重複, 該subString長度繼續累積+1
        for (int L = 0, R = 0; R < s.length(); R++) { // LOOP: right作為allString的iterator, left作為subString的第一字元位置
            // 比對有沒有重複, 26個字元char=KEY, 都有唯一的left, 當遇到重複char時, 就要更新對應的left, 以再次計算width
            if (map.containsKey(s.charAt(R)) == true) { // 掃到map已有該字元, 設定right待會計算width使用,因為重複區段需捨棄,left要重設
                Out.i(""+map.get(s.charAt(R))+"=map.get(s.charAt("+R+"))");
                // 重新設定下次left, 在重複字元位置的+1位置
                L = Math.max(L, map.get(s.charAt(R)) + 1); // KEY_get: 若有該字元, 代表重複, 所以noRepeatStartPos(left)往前推進, 依靠之前put(right)的值, +1是因為這是在新LOOP發現的字元
            } // else {} 的情況代表不更新noRepeatStartPos, 這樣就可計算(scanPos-noRepeatStartPos)=Window寬度
            // KEY: <K,V> = <當前掃到的'一個字元', 當前位置> = <char,pos>
            map.put(s.charAt(R), R); // KEY_put: put<K,V> 作為left遞增, <c1,i1><c2,i2><c3,i3>...<cn,in> = 每個字元char都是一組<K,V>   <字元,右邊界>
            Out.i("right="+R);
            // 基於新left, right算出來的res跟原紀錄比較, 看有沒有破紀錄, +1是因為剛剛 減數left加過1, 所以要再回補 被減數right, 故+1
            width = Math.max(width, R - L + 1); // KEY: 不斷的計算width, sliding window的寬度依靠left與right的異動, 之前紀錄比較大還是此次的
        }
        printAllHashMapValues(map);
        return width;
    }

    // Tips1: 這一題subString最長的結果是abcdefghijklmnopqrstuvwxyz = 26, 所以用SET
    // Tips2: 第二個iterator left代表allString的左端
    static public int lengthOfLongestSubstring_HashSet(String s) {
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

    static void printAllHashMapValues(HashMap<Character, Integer> hashMap) {
        for(Character key : hashMap.keySet()) {
            Out.i("KEY="+key+"   VALUE="+hashMap.get(key));
        }
    }

}
