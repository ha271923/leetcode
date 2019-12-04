package com.hawk.leetcode.Basic;

public class Recursive {
    // Recursive跟Backtracking都是遞迴的一種:
    // A. 只是Recursive是一層獲得部粉解答後,再繼續往下一層(Q51. N-Queen), 直到找出答案, 也是窮舉法一種
    // B. Backtracking則是先鑽到最深一層後, 再從最後一層獲得部粉解答,再繼續退回上一層前進(Q50. Pow),逐漸獲得全部解答
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
