package com.cspirat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LetterCombinationsofaPhoneNumber
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 17. Letter Combinations of a Phone Number
 *
 * Note: that 1 does not map to any letter
 */
public class LetterCombinationsofaPhoneNumber {
    /**
     * time : O(3^n)
     * space : O(n)
     */

    /**
     *  Many solutions can be found at https://www.cnblogs.com/lightwindy/p/8495709.html
     *  1. DFS, ArrayList, Recursion
     *  2. BFS, LinkedList
     *  3-1. Recursion, HashMap
     *  3-2. backtrack, HashMap
     *  4. BFS, ArrayList, Iteration, Time: O(3^n), Space: O(1)
     * */

    private String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    // 1. DFS, ArrayList, Recursion
    public List<String> letterCombinations_DFS(String digits) {
        //  ArrayList<>()
        //  優點＊所以當程式或演算法需要大量運用索引來取得或搜尋集合內資料的時候，使用 ArrayList 較為快速！
        //  缺點＊在插入元素或刪除中間元素時，因為需要將後面的資料全部往後或往前移，所以會花費較多時間，
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        DFS(res, digits, "", 0); // 1. 一開始數字digits尚未分析狀態, res無字組
        return res;
    }

    public void DFS(List<String> res, String digits, String s, int index) {
        if (index == digits.length()) { // 5. DFS列舉完畢, 當輸入字元長度與輸出字元長度相等時, 一個字串列舉完畢,加入res ex: 23對應ad,
            res.add(s);
            return;
        }
        String letters = mapping[digits.charAt(index) - '0']; // 2. Hawk: fixed Bug, 數字轉換成字組
        for (int i = 0; i < letters.length(); i++) { // 3. 將所有該一位數字對應到的字組組合一一列舉
            DFS(res, digits, s + letters.charAt(i), index + 1); // 4. 遞迴DFS
        }
    }

    // 2. BFS, LinkedList
    // Tips1: 利用 i != digits.length() 時, 輸出一個String
    public List<String> letterCombinations_BFS(String digits) {
        // LinkedList<>() , 每次增加一個node時, head數字會+1
        // 優點＊當程式需要刪除或插入中間元素的時候，利用 LinkedList 較為迅速！若是需要對集合內容做排序，也是用LinkedList較為迅速，因為插入元素時所花的時間較為短暫！
        // 缺點＊當程式需要依照索引來查詢或存取資料時就比較不方便，因為每個節點並不知道自己所在的位置，所以必須從頭開始一個一個往下找，所花時間也較多。
        LinkedList<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");// 初始化
        for (int i = 0; i < digits.length(); i++) { // 1. LOOP-1: 掃描全部輸入字元
            int num = digits.charAt(i) - '0'; // 2. 分析出第一個數字
            while (res.peek().length() == i) { // 3. 當輸出字串位置idx與掃描圈數idx '不相等時',一個字串列舉完畢, peek() API: Retrieves the head of this list, but does not remove.
                String str = res.remove(); // 從LinkedList中, 刪除head元素, 並且return此被刪除的元素, 類似pop
                for (char c : mapping[num].toCharArray()) { // String to char[]
                    res.add(str + c); // Q: 為什麼不直接add, 而要remove再add? 重新String+c的結合? 因為我們不要中繼節點, 只要尾端節點
                }
            }
        }
        return res;
    }

    // 3-1. Recursion, HashMap
    public ArrayList<String> letterCombinations_HashMap(String digits) {
        ArrayList<String> res = new ArrayList<>();
        if (digits == null || digits.equals("")) {
            return res;
        }

        Map<Character, char[]> map = new HashMap<>();
            map.put('0', new char[] { '0' });
            map.put('1', new char[] { '1' });
            map.put('2', new char[] { 'a', 'b', 'c' });
            map.put('3', new char[] { 'd', 'e', 'f' });
            map.put('4', new char[] { 'g', 'h', 'i' });
            map.put('5', new char[] { 'j', 'k', 'l' });
            map.put('6', new char[] { 'm', 'n', 'o' });
            map.put('7', new char[] { 'p', 'q', 'r', 's' });
            map.put('8', new char[] { 't', 'u', 'v'});
            map.put('9', new char[] { 'w', 'x', 'y', 'z' });
        StringBuilder sb = new StringBuilder();
        helper(map, digits, sb, res);
        return res;
    }

    private void helper(Map<Character, char[]> map, String digits, StringBuilder sb, ArrayList<String> res) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }

        for (char c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            helper(map, digits, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // 3-2. backtrack, HashMap( leetcode solution not support '0','1'
    List<String> res = new ArrayList<String>();
    public List<String> letterCombinations_HashMap2(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
        return res;
    }
    Map<String, String> map = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    public void backtrack(String combination, String next_digits) {
        // if there is no more digits to check
        if (next_digits.length() == 0) {
            // the combination is done
            res.add(combination);
        }
        // if there are still digits to check
        else {
            // iterate over all letters which map
            // the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = map.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = map.get(digit).substring(i, i + 1);
                // append the current letter to the combination
                // and proceed to the next digits
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    // 4. BFS, ArrayList, Iteration, Time: O(3^n), Space: O(1)
    public List<String> letterCombinations_Iterator(String digits) {
        if (digits.isEmpty()) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        res.add("");
        for (char c : digits.toCharArray()) {
            final int n = res.size();
            final int m = mapping[c - '0'].length();

            // resize to n * m
            for (int i = 1; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    res.add(res.get(j));
                }
            }

            for (int i = 0; i < res.size(); ++i) {
                res.set(i, res.get(i) + mapping[c - '0'].charAt(i/n));
            }
        }
        return res;
    }

}
