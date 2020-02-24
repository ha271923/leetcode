package com.hawk.leetcode.Basic.data.Examples;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, String> input = new HashMap<>();
        initHashmap(input);
        System.out.println("res = "+input.get("Taiwan"));
    }

    public static void initHashmap(Map<String, String> map) {
        // Add keys and values (Country, City)
        map.put("Taiwan", "Taipei");
        map.put("England", "London");
        map.put("Germany", "Berlin");
        map.put("Norway", "Oslo");
        map.put("USA", "Washington DC");
        System.out.println(map);
    }
}
