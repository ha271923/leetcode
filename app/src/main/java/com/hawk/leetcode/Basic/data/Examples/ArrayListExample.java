package com.hawk.leetcode.Basic.data.Examples;

import java.util.*;
class ArrayListExample{
    public static void main(String args[]){

        ArrayList arr=new ArrayList();  // creating array list
        arr.add("Jack");                // adding elements
        arr.add("Tyler");
        Iterator itr=arr.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
