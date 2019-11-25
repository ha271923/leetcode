package com.cspirat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Edward on 28/07/2017.
 */
public class GroupAnagrams {
    /**
     * 49. Group Anagrams
     * Given an array of strings, group anagrams together.

     For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
     Return:
     [
        ["ate", "eat","tea"],
        ["nat","tan"],
        ["bat"]
     ]


     * @param strs
     * @return
     */
    // 1.  判斷'唯一性': 忽略順序時, 檢查字元是否重複 ==> HashMap
    // 2a. 既有: 將重複的放同一列 ==> res.get(map.get(s)).add(str)
    // 2b. 全新: 紀錄此重複, 並創造出新一列 ==> new ArrayList<String>
    public static void main(String[] args) {
        List<List<String>> ret;
        String[] input={"eat", "tea", "tan", "ate", "nat", "bat"};
        // ret = groupAnagrams_charArray(input);
        ret = groupAnagrams_charInt(input);
        printAnswer(ret);
        System.out.println("ANS: ret.size()="+ret.size());
    }

    static private void printAnswer(List<List<String>> result){
        for(int i = 0; i< result.size(); i++) {
            for(int j=0; j<result.get(i).size(); j++){
                System.out.print(result.get(i).get(j)+",");
            }
            System.out.println("");
        }
    }

    // time : O(m * n * logn) space : O(n)
    static public List<List<String>> groupAnagrams_charArray(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0)
            return res;
        HashMap<String, Integer> map = new HashMap<>(); // 下面會利用HashMap, 來判斷字串的'唯一性'
        for (String str : strs) { // LOOP1: 列舉每一次字串
            char[] ch = str.toCharArray(); // Tips: 將String轉為char[]並排序, 來判斷字串的'唯一性'
            Arrays.sort(ch);
            String K = new String(ch); // 將排序過的char[]轉換回String, 來判斷字串的'唯一性'
            if (map.containsKey(K)) { // '唯一性': 利用HashMap, 來判斷字串是否唯一 ==> 已重複
                List<String> list = res.get(map.get(K)); // 把重複的字放在同一列List<String>
                list.add(str);
            } else { // '唯一性': 利用HashMap, 來判斷字串是否唯一 ==> 尚未重複
                List<String> list = new ArrayList<>(); // 創建新的一列List<String>
                list.add(str);
                map.put(K, res.size());
                res.add(list); // 加到res
            }
        }
        return res;
    }

    // time : O(m * n) space : O(n)  counting sort
    // 自定義資料結構給K, 以判斷唯一性
    // String.valueOf() API的使用
    static public List<List<String>> groupAnagrams_charInt(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) { // LOOP1: 列舉每一次字串
            int[] count = new int[26]; // 英文26個字母, 每字元出現計次
            for (Character ch : str.toCharArray()) { // LOOP2: 列舉該字串的每一次字元char
                count[ch - 'a']++;
            }
            String K = "";
            for (int i = 0; i < count.length; i++) {  // 自訂一個新的資料組合紀錄: ["ate", "eat","tea"] = "1a1e1t" = K , 以判斷'唯一性'
                if (count[i] != 0) {
                    System.out.println("String.valueOf(count["+i+"]="+String.valueOf(count[i])+")");
                    System.out.println("String.valueOf((char)("+i+" + 'a']="+String.valueOf((char)(i + 'a')+")"));
                    K += String.valueOf(count[i]) + String.valueOf((char)(i + 'a'));
                    System.out.println("K="+K);
                }
            }
            if (map.containsKey(K)) { // '唯一性': 利用HashMap, 來判斷字串是否唯一 ==> 已重複
                List<String> list = map.get(K);
                list.add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(K, list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
