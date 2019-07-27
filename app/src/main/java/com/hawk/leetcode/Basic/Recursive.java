package com.hawk.leetcode.Basic;

public class Recursive {

    public static void main(String[] args) {
        int sum;
        recur(1);
        System.out.println();
    }

    static int recur(int a){
        if(a<100) {
            System.out.print(a);
            System.out.print(',');
            return recur(++a);
        }
        else
            return a;
    }

}
