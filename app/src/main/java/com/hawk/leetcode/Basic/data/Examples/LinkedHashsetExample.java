package com.hawk.leetcode.Basic.data.Examples;

import java.util.*;
class LinkedHashsetExample{
    public static void main(String args[]){
        LinkedHashSet al=new LinkedHashSet(); // creating linkedhashset
        al.add("Mariana");                    // adding elements
        al.add("Rick");
        al.add("Sam");
        Iterator itr=al.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}

