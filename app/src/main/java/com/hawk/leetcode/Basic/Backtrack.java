package com.hawk.leetcode.Basic;

import java.util.ArrayList;

/**
 * Compare Backtracking with DFS
 *  A. Backtracking is a more general purpose algorithm.
 *  B. DFS is a specific form of backtracking related to searching tree structures.
 *
 *    boolean solve(Node n) {
 *       if n is a goal node, return true
 *
 *       foreach option O possible from n {
 *           if solve(O) succeeds,  // KEY: 遞迴在此!!!!!!!!!!!!!!!
 *               return true
 *       }
 *       return false
 *   }
 *
 *
 *   boolean pathFound(Position p) {
 *      if (p is finish)
 *          return true;
 *
 *      foreach option O from p {
 *          boolean isThereAPath = pathFound(O);  // KEY: 遞迴在此!!!!!!!!!!!!!!!
 *          if (isThereAPath)
 *              return true; // We found a path using option O
 *      }
 *      // We have tried all options from this position and none of the options lead to finish.
 *      // Hence there is no solution possible to finish
 *      return false;
 *   }
 *
 * Backtracking
 * 回溯法通常用遞歸實現，因為換條路繼續走的時候換的那條路又是一條新的子路。
 * 高人說，如果你發現問題如果不窮舉一下就沒辦法知道答案，就可以用回溯了。
 * 一般回溯問題分三種：
 *   1. Find a path to success 有沒有解
 *   2. Find all paths to success 求所有解
 *     求所有解的個數
 *     求所有解的具體信息
 *   3. Find the best path to success 求最優解
 * 回溯法采用DFS搜索解空间，而分支限界法一般采用用BFS或以最小耗费优先的方式搜索解空间。
 * backtracking（回溯算法）也叫试探法或穷举法，它是一种系统地搜索问题的解的方法。
 * 回溯算法的基本思想是：
 * 从一条路往前走，能进则进，不能进则退回来，换一条路再试(N-Queen)，可以看作蛮力法穷举搜索的改进。
 * Q1: permutations
 * 首先看 permutations 这个问题，是求一个数组的"全排列"，思路就是将数组当做一个池子，第一次取出一个数，
 * 然后在池子里剩下的数中再任意取一个数此时组成两个数，然后再在池子中剩下的数里取数，直到无数可取，即取
 * 完一次，形成一个排列。
 * Q2: subsets
 * 其次再来看Subsets问题，是取"一个数组的组合"而不是全排列，基本代码结构都很相似，不同的有：
 * 不是在结果长度等于数组长度时才将结果加入总结果中，而是在每次递归中都将当前组合加入结果中，因为求的是
 * 子集而不是全排列。
 * 每次递归不是在池子中随便取一个数加入当前结果，因为此题要求的是子集，[1,3]和[3,1]是相同的，要求的是[1,3]，因此每次在取数时，都要从其位置开始取后面的数，防止取到[3,1]这样的结果。
 *
 * Backtracking是一種窮舉搜尋的演算法，目標是找尋所有可能的答案，可分為兩個概念，
 * 分別是enumerate(枚舉)與pruning(剪枝)
 * (1)enumerate(枚舉):每一步列出所有可能的下一步一一測試
 * (2)pruning(剪枝):遇到不符合條件的下一步便省略，不再繼續枚舉
 *
 *   http://www.csie.ntnu.edu.tw/~u91029/Backtracking.html
 *
 *  // 这是最原始的模板，根据题要求的不同会增加参数或各种判断条件，但都离不开这个框架。
 *
 *
 *    // 0. list<TYPE> s用來記憶已取出過的数，nums是原始数组，pos是当前取第几个位置的数
 *    public void backtrack(List<Integer> s,int[] nums,int pos) {
 *        // 6.跳出条件
 *        if(……) {
 *            ……
 *            return;
 *        }
 *        // 1. 依序列舉所有的數
 *        for(int i=0; i<nums.length; i++) {
 *            int num = nums[i];
 *            // 2. 取过的数不再取
 *            if(s.contains(num)) {
 *                continue;
 *            }
 *            // 3. 取出一个数
 *            s.add(num);
 *            // 4. 持續縮小問題, 此題往下遍歷, 並提供正確參數, pos+1
 *            backtrack(s,nums,pos+1); // KEY: 遞迴在此!!!!!!!!!!!!!!!!!!!!!!
 *            // 5. 重要！！遍历过此节点后，要回溯到上一步，因此要把加入到结果中的此点去除掉！
 *            s.remove(s.size()-1);
 *        }
 *    }
 *
 *
 *  在leetcode中比较经典的backtracking问题有以下几个：
 *    39. Combination Sum
 *    40. Combination Sum II
 *    216. Combination Sum III
 *    46. Permutations
 *    47. Permutations II
 *    51. N-Queens
 *    52. N-Queens II
 *    78. Subsets
 *    90. Subsets II
 *    131. Palindrome Partitioning
 *
 *
 * 【10】Regular Expression Matching
 * 【17】Letter Combinations of a Phone Number
 * 【22】Generate Parentheses
 * 【37】Sudoku Solver
 * 【39】Combination Sum
 * 【40】Combination Sum II
 * 【44】Wildcard Matching
 * 【46】Permutations
 * 【47】Permutations II
 * 【51】N-Queens
 * 【52】N-Queens II
 * 【60】Permutation Sequence
 * 【77】Combinations
 * 【78】Subsets （相似的題目見 90 題）
 * 【79】Word Search
 * 【89】Gray Code
 * 【90】Subsets II （演算法群，2018年11月21日）
 * 【93】Restore IP Addresses
 * 【126】Word Ladder II
 * 【131】Palindrome Partitioning
 * 【140】Word Break II（2018年12月19日，演算法群，類似題目 472. DFS專題）
 * 【211】Add and Search Word - Data structure design
 * 【212】Word Search II
 * 【216】Combination Sum II
 * 【254】Factor Combinations
 * 【267】Palindrome Permutation II
 * 【291】Word Pattern II
 * 【294】Flip Game II
 * 【306】Additive Number
 * 【320】Generalized Abbreviation
 * 【351】Android Unlock Patterns
 * 【357】Count Numbers with Unique Digits
 * 【401】Binary Watch
 * 【411】Minimum Unique Word Abbreviation
 * 【425】Word Squares
 * 【526】Beautiful Arrangement
 * 【691】Stickers to Spell Word
 * 【784】Letter Case Permutation
 * 【842】Split Array into Fibonacci Sequence
 **/
public class Backtrack {
    // Recursive跟Backtracking都是遞迴的一種:
    // A. 只是Recursive是一層獲得部粉解答後,再繼續往下一層(Q51. N-Queen), 直到找出答案, 也是窮舉法一種
    // B. Backtracking則是先鑽到最深一層後, 再從最後一層獲得部粉解答,再繼續退回上一層前進(Q50. Pow),逐漸獲得全部解答
    public static void main(String[] args) {
        backtrack(1);
        System.out.println();
    }

    static int backtrack(int a){
        if(a%10 == 0) // pruning
            System.out.println();

        if(a<100) {
            System.out.print(a);
            System.out.print(',');
            return backtrack(++a);
        }
        else
            return a;
    }

}
