package com.cspirat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SubstringwithConcatenationofAllWords
 * Creator : Edward
 * Date : Dec, 2017
 * Description : 30. Substring with Concatenation of All Words
 */
public class SubstringwithConcatenationofAllWords {
    /**
     * You are given a string, s, and a list of words, words, that are all of the same length.
     * Find all starting indices of substring(s) in s that is a concatenation of each word
     * in words exactly once and without any intervening characters.

     For example, given:
     s: "barfoothefoobarman"  注意:barXXXfoo 就不算concatenation
     words: ["foo", "bar"]

     You should return the indices: [0,9].
     (order does not matter).

     time : O(n ^ 2)
     space : O(n)

     * @param s
     * @param words
     * @return
     */
    // Tips0: 给定一个长字符串，再给定几个长度相同的单词，让我们找出串联给定所有单词的子串的起始位置
    public List<Integer> findSubstring(String sentence, String[] words) {
        if (sentence == null || words == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        int numOfWords = words.length; // LeetCode Q: a list of words, number of list item.
        int oneWordLength = words[0].length(); // LeetCode Q: Words are all of the same length.
        HashMap<String, Integer> map = new HashMap<>();

        for (String str : words) { // put all words in hashmap, V的預設值設為 V=1
            map.put(str, map.getOrDefault(str, 0) + 1); // KEY: 我們定義的flag V=1:代表還沒找過, V=0, -1利用get()的特性, 找到跟沒找到
        }

        for (int i = 0; i <= sentence.length() - numOfWords * oneWordLength; i++) { // 最後字尾少於n*m時, 可以避免算法在字元不足時的邊際效應, 數量不足時不須再掃, i是最終一定要掃完的圈數
            HashMap<String, Integer> copy = new HashMap<>(map); // 要掃的字單
            int needToMatch = numOfWords; // KEY:變數宣告在此, 可以在每次不匹配時, 重置掃描環境
            int shift = i; // 每次右移一個字元
            while (needToMatch > 0) { // 每次要掃numOfWords的字彙量
                String compareStr = sentence.substring(shift, shift + oneWordLength); // 每次用固定長度, 取出新字元
                if (!copy.containsKey(compareStr) || copy.get(compareStr) < 1) { // 沒找到! containsKey==false, 我們定義的flag V=1:代表還沒找過, V=0, -1利用get()的特性, 找到跟沒找到, get()=(1-1)=0是我們定義找到時的值
                    break; // 沒找到, 跳出while
                }
                copy.put(compareStr, copy.get(compareStr) - 1); // KEY: 找到了一個word! V=(1-1)
                needToMatch--; // 匹配了一個word
                shift += oneWordLength; // 下次匹配起點
            }
            if (needToMatch == 0) res.add(i); // 全部match時, 將idx加入output
        }
        return res;
    }
}
