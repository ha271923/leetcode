package com.hawk.leetcode.Basic.data.Examples;

import java.util.*;
class TreeSetExample{
    public static void main(String args[]){
        TreeSet al=new TreeSet();  // creating treeSet
        al.add("John");                            // adding elements
        al.add("Sam");
        al.add("Rick");
        Iterator itr=al.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
