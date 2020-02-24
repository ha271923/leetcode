package com.hawk.leetcode.Basic.data.Examples;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * ashmap vs Hashtable
 * 1. HashMap is non synchronized. It is not-thread safe and can’t be shared between many threads without proper synchronization code whereas Hashtable is synchronized. It is thread-safe and can be shared with many threads.
 * 2. HashMap allows one null key and multiple null values whereas Hashtable doesn’t allow any null key or value.
 * 3. HashMap is generally preferred over HashTable if thread synchronization is not needed
 */
public class HashTableExample {
    public static void main(String[] args) {
        Hashtable<String, String> input = new Hashtable<>();
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
        mapVStable();
    }

    public static void mapVStable()
    {
        System.out.println("-------------------------------------");
        //----------hashtable -------------------------
        Hashtable<Integer,String> ht=new Hashtable<Integer,String>();
        ht.put(101,"ONE");
        ht.put(101,"ONE-1");
        ht.put(102,"TWO");
        ht.put(103,"THREE");
        System.out.println("-------------Hash table--------------");
        for (Map.Entry m:ht.entrySet()) {
            System.out.println(m.getKey()+" "+m.getValue());
        }

        //----------------hashmap--------------------------------
        HashMap<Integer,String> hm=new HashMap<Integer,String>();
        hm.put(101,"ONE");
        hm.put(101,"ONE-1");
        hm.put(102,"TWO");
        hm.put(103,"THREE");
        System.out.println("-----------Hash map-----------");
        for (Map.Entry m:hm.entrySet()) {
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }
}
