package com.hawk.leetcode.Basic.data.Examples;

import java.util.*;
class HashsetExample{
    public static void main(String args[]){

        HashSet hashSet;
        hashSet=new HashSet(); // creating hashSet
        hashSet.add("Rachit"); // adding elements
        hashSet.add("Amit");
        hashSet.add("Amit"); // Set 不會有重複
        hashSet.add("jack");
        Iterator itr;
        itr=hashSet.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}

