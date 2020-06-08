package com.hawk.leetcode.Basic.data.Examples;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample1 {

    public static void main(String[] args)  {
        Comparator c = new Comparator<String>() {
            public int compare(String a, String b) {
                // return a.compareTo(b) * -1; // Using descending order
                return a.compareTo(b) *  1; // Using ascending order
            }
        };
        PriorityQueue<String> pq = new PriorityQueue(3, c);
        pq.offer("c");
        pq.offer("a");
        pq.offer("b");
        String s;
        while ((s = pq.poll()) != null) { // default is alphabets ascending order.
            System.out.print(s + ", ");
        }
    }


}
