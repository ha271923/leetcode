package com.hawk.leetcode.Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Branch-and-Bound 演算法是一個相當簡單的方法。在问题的解空间树上搜索问题的解的方法。
 * 作用
 * 	‧用來改良回溯演算法。
 *
 * 特點
 * 	‧使用狀態空間樹來解決問題。
 * 	‧不限制要怎麼走訪整棵樹。(回溯法有限制)
 * 	‧只用來解決最佳化問題。
 * 	‧除了使用 bound 值來決定一個節點是不是 promising 之外，還必須加以比較每個子節點
 * 	  的 bound 值，然後挑選具有最佳 bound 值的子節點進行走訪。
 * 簡單的說，它利用樹狀結構將所有可能的組合產生出來，當目前的最佳解（upperbound）小於目前所在節點的總
 * 長度時（lower bound）時，就將該節點給bound 掉。
 * Branch 的規則：銷售員將所有可能的路徑以tree 的結構展開，而且此樹會是一棵多元樹。
 * Search 的策略：depth-first search。
 * Bound 的技巧：所生成的每一個節點都會紀錄從起點到此點的總長度，也就是lower bound。當某一分支走到底
 * 後便得到一個可行解，若該解小於目前的upperbound，則將該值設定給upper bound。
 * 分支定界法的思想是：首先确定目标值的上下界，边搜索边减掉搜索树的某些支，提高搜索效率。
 *
 * void BFS_BranchAndBound(tree T, number& best) { // 非遞迴BFS
 * 	   queue_of_node Q; // 宣告一個先進先出的Queue
 * 	   node u,v;
 * 	   initialize(Q); //初始時將Queue清空
 * 	   v = root of T; //走訪根節點
 * 	   best = value(v) ;  //初始化目前最佳值
 * 	   enqueue(Q,v); //將根節點存入Queue尾端
 * 	   while(!empty(Q)) { //當Queue中有節點時
 * 	   	   dequeue(Q,v); //從Queue前端取出一個節點 v
 * 	   	   for(each child u of v) { //走訪 v 的每個子節點 u
 *             if(value(u) is better than best)
 *                 best = value(u); //若找到更好的結果就更新目前最佳值
 *             if(bound(u) is better than best)
 *                 enqueue(Q,u);    //若一個節點是 promising 就把它加入Queue尾端，以待下一層走訪時使用
 *         }
 *     }
 * }
 *
 * LeetCode:
 * Different Ways to Add Parentheses
 * */

/**
    // queue, insert(), remove()
    void bfs_branch_and_bound(state_space_tree T, number& best) {
        priority_queue_of_node PQ;   //使用優先權佇列來存放欲走訪的節點
        node u, v;
        initialize(PQ);
        v = root of T;        //先走訪根節點，並初設目前最佳值 best
        best = value(v);
        insert(PQ, v);      //將根節點插入優先權佇列中
        while (!empty(PQ)) {   //只要優先權佇列中還有未走訪節點就繼續做
            remove(PQ, v);       //取出佇列中優先權 (即 bound) 最高的節點
            if (bound(v) is better than best ) {   // KEY: 若它仍然還是 promising 則往
                for (each child u of v ) {  //下層走訪它的每個子節點
                    if (value(u) is better than best )
                        best = value(u);  //更新最佳值
                    if (bound(u) is better than best )
                        insert(PQ, u);    //若 u 為 promising，則將
                }                                                         // 它插入佇列中
            }
        }
    }

    void knapsack3(int n, const int p[], const int w[], int W, int& maxprofit) {
        priority_queue_of_node PQ;
        node u, v;
        initialize(PQ);
        v.level = 0; v.profit = 0; v.weight = 0;
        maxprofit = 0;
        v.bound = bound(v);      //走訪根節點，並將它加入優先權佇列中
        insert(PQ, v);
        while (!empty(PQ, v)) {
            remove(PQ, v);    //將 bound 值最高的節點取出來
            if (v.bound > maxprofit) {   //如果它仍然是 promising 就往下層展開
                u.level = v.level + 1;
                u.weight = v.weight + w[u.level];
                u.profit = v.profit + p[u.level];        //先走訪左子節點
                if (u.weight <= W && u.profit > maxprofit)
                    maxprofit = u.profit;           //更新最佳值
            }
        }
    }
*/

public class BranchAndBound3 {
    // Debugging ***************************
    public static void main(String[] args) {
        //new BranchAndBound3().testKnapsack();
    }
}