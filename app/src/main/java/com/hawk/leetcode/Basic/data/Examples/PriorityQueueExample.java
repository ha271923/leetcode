package com.hawk.leetcode.Basic.data.Examples;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample {
    /**
     *   Priority：對資料項目賦予「權重/優先權」，用以表示資料的重要程度。
     *   Queue：隊伍、佇列、順序，意味著把資料整理成「某種順序」的資料結構。
     *
     *   所謂的「某種順序」，可能是「先進先出(First-In-First-Out)」順序：每次要從此資料結構讀取資料時，必定是拿到「先進入」該資料結構的資料。
     *   (請參考：Queue: Intro(簡介))
     *   也有可能是額外賦予資料的「權重/優先權」順序：每次要從此資料結構讀取資料時，必定會拿到具有「最大值/最小值權重」的資料。
     *   如果每次要從Priority Queue讀取資料時，都拿到「權重最大」的資料，則稱此為Max-Priority Queue。
     *   反之，若每次讀取資料，都拿到「權重最小」的資料，則稱此為Min-Priority Queue
     */
    public static void main(String[] args) {
        // A custom comparator that compares two Strings by their length.
        Comparator<String> myStringLengthComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        };

        /*
        The above Comparator can also be created using lambda expression like this =>
            Comparator<String> stringLengthComparator = (s1, s2) -> {
                return s1.length() - s2.length();
            };

        Which can be shortened even further like this =>
            Comparator<String> stringLengthComparator = Comparator.comparingInt(String::length);
        */

        // Create a Priority Queue with a custom Comparator
        PriorityQueue<String> lengthPQ = new PriorityQueue<>(myStringLengthComparator);

        // Add items to a Priority Queue (ENQUEUE)
        lengthPQ.add("Lisa");
        lengthPQ.add("Robert");
        lengthPQ.add("John");
        lengthPQ.add("Chris");
        lengthPQ.add("Angelina");
        lengthPQ.add("Joe");

        // Remove items from the Priority Queue (DEQUEUE)
        while (!lengthPQ.isEmpty()) {
            System.out.println(lengthPQ.remove());
        }
    }
}
