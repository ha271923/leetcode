package com.hawk.leetcode.Basic;

/**
 * Branch-and-Bound 演算法是一個相當簡單的方法。在问题的解空间树上搜索问题的解的方法。
 * 作用
 * ‧用來改良回溯演算法。
 * <p>
 * 特點
 * ‧使用狀態空間樹來解決問題。
 * ‧不限制要怎麼走訪整棵樹。(回溯法有限制)
 * ‧只用來解決最佳化問題。
 * ‧除了使用 bound 值來決定一個節點是不是 promising 之外，還必須加以比較每個子節點
 * 的 bound 值，然後挑選具有最佳 bound 值的子節點進行走訪。
 * 簡單的說，它利用樹狀結構將所有可能的組合產生出來，當目前的最佳解（upperbound）小於目前所在節點的總
 * 長度時（lower bound）時，就將該節點給bound 掉。
 * Branch 的規則：銷售員將所有可能的路徑以tree 的結構展開，而且此樹會是一棵多元樹。
 * Search 的策略：depth-first search。
 * Bound 的技巧：所生成的每一個節點都會紀錄從起點到此點的總長度，也就是lower bound。當某一分支走到底
 * 後便得到一個可行解，若該解小於目前的upperbound，則將該值設定給upper bound。
 * 分支定界法的思想是：首先确定目标值的上下界，边搜索边减掉搜索树的某些支，提高搜索效率。
 * <p>
 * void BFS_BranchAndBound(tree T, number& best) { // 非遞迴BFS
 * queue_of_node Q; // 宣告一個先進先出的Queue
 * node u,v;
 * initialize(Q); //初始時將Queue清空
 * v = root of T; //走訪根節點
 * best = value(v) ;  //初始化目前最佳值
 * enqueue(Q,v); //將根節點存入Queue尾端
 * while(!empty(Q)) { //當Queue中有節點時
 * dequeue(Q,v); //從Queue前端取出一個節點 v
 * for(each child u of v) { //走訪 v 的每個子節點 u
 * if(value(u) is better than best)
 * best = value(u); //若找到更好的結果就更新目前最佳值
 * if(bound(u) is better than best)
 * enqueue(Q,u);    //若一個節點是 promising 就把它加入Queue尾端，以待下一層走訪時使用
 * }
 * }
 * }
 * <p>
 * LeetCode:
 * Different Ways to Add Parentheses
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * // queue, insert(), remove()
 * void bfs_branch_and_bound(state_space_tree T, number& best) {
 * priority_queue_of_node PQ;   //使用優先權佇列來存放欲走訪的節點
 * node u, v;
 * initialize(PQ);
 * v = root of T;        //先走訪根節點，並初設目前最佳值 best
 * best = value(v);
 * insert(PQ, v);      //將根節點插入優先權佇列中
 * while (!empty(PQ)) {   //只要優先權佇列中還有未走訪節點就繼續做
 * remove(PQ, v);       //取出佇列中優先權 (即 bound) 最高的節點
 * if (bound(v) is better than best ) {   // KEY: 若它仍然還是 promising 則往
 * for (each child u of v ) {  //下層走訪它的每個子節點
 * if (value(u) is better than best )
 * best = value(u);  //更新最佳值
 * if (bound(u) is better than best )
 * insert(PQ, u);    //若 u 為 promising，則將
 * }                                                         // 它插入佇列中
 * }
 * }
 * }
 * <p>
 * void knapsack3(int n, const int p[], const int w[], int W, int& maxprofit) {
 * priority_queue_of_node PQ;
 * node u, v;
 * initialize(PQ);
 * v.level = 0; v.profit = 0; v.weight = 0;
 * maxprofit = 0;
 * v.bound = bound(v);      //走訪根節點，並將它加入優先權佇列中
 * insert(PQ, v);
 * while (!empty(PQ, v)) {
 * remove(PQ, v);    //將 bound 值最高的節點取出來
 * if (v.bound > maxprofit) {   //如果它仍然是 promising 就往下層展開
 * u.level = v.level + 1;
 * u.weight = v.weight + w[u.level];
 * u.profit = v.profit + p[u.level];        //先走訪左子節點
 * if (u.weight <= W && u.profit > maxprofit)
 * maxprofit = u.profit;           //更新最佳值
 * }
 * }
 * }
 */
/*
public class BranchAndBound4 {

    public static void main(String[] args) {
        //new BranchAndBound3().testKnapsack();
    }

    void travel2(int n, const number W[][], ordered_set& opttour, number& minlength) {
        priority_queue_of_node PQ;
        node u, v;
        initialize(PQ);
        v.level = 0;
        v.path = [1];          // 令頂點 1 為起點 (即根節點)
        v.bound = bound(v);        //計算根節點的 bound 值
        minlength = 無限大;          //初始化最佳值
        insert(PQ, v);
        while (!empty(PQ)) {
            remove(PQ, v);      //取出下一個要展開的節點
            if (v.bound < minlength) {    //如果它仍然是 promising
                u.level = v.level + 1;  //開始計算它所有下一層節點
                for (all i such that 2 <= i <= n && i is not in v.path ){
                    u.path = v.path;
                    put i at the end of u.path; //將 u 的路徑寫入

                }
                if (u.level == n - 2) {   //如果 u 在倒數兩層就直接處理
                    put index of only vertex not in u.path
                    at the end of u.path; //將最後一頂點加入路徑
                    put 1 at the end of u.path;  //把頂點 1 加入
                    if (length(u) < minlength) {
                        minlength = length(u);   //更新最佳值
                        opttour = u.path;      //更新最佳路徑
                    }
                } else {   //還不到旅程的終點
                    u.bound = bound(u);    //計算 bound 值
                    if (u.bound < minlength)  //若 promising
                        insert(PQ, u);   //放入優先權佇列中
                }
            }
        }
    }
}
*/

/**
// C# code required to adjust to JAVA
public class BranchAndBound4 {

    public class node {
        public int level, bound;
        public String path;
        public boolean[] isUse;
    }

    int[] W;
    public node init;
    public int minlength;
    public String opttour;
    int num;

    public static void main(String[] args) {
        new BranchAndBound4().Travel();
    }

    public void Travel(int[] W, int minlength) {
        this.W = W;
        num = W.length;

        init = new node();
        init.isUse = new boolean[num];

        init.level = 0;
        init.path = "0";
        init.isUse[0] = true;
        init.bound = bound(init);

        this.minlength = minlength;
    }

    public void solve(node n) {
        Queue<node> PQ = new LinkedList<node>();
        node v = new node();


        if (n.bound < minlength) {
            if (n.level == num - 2) {
                int i;
                for (i = 0; n.isUse[i]; i++) ;
                n.path += String.format(",{0}", i);

                int temp = length(n);
                if (temp < minlength) {
                    minlength = temp;
                    opttour = n.path;
                }

            } else {
                for (int i = 1; i < num; i++) {
                    if (n.isUse[i]) continue;

                    v.level = n.level + 1;
                    v.path = n.path + String.format(",{0}", i);
                    v.isUse = new boolean[num];
                    for (int j = 0; j < num; j++)
                        v.isUse[j] = n.isUse[j];

                    v.isUse[i] = true;
                    v.bound = bound(v);

                    PQ.add(v);
                    for (int j = PQ.Count - 1; j >= 1; j--)
                        if (PQ[j].bound < PQ[j - 1].bound) {
                            node temp = PQ[j];
                            PQ[j] = PQ[j - 1];
                            PQ[j - 1] = temp;
                        } else break;
                }
            }


            for (int i = 0; i < PQ.Count; i++)
                solve(PQ[i]);
        }
    }

    public int bound(node n) {
        int intBound = 0;
        int[] aIntPath = readPath(n.path);
        int intPathLen = aIntPath.Length;

        for (int i = 0; i < intPathLen - 1; i++)
            intBound += W[aIntPath[i], aIntPath[i + 1]];

        for (int i = 0; i < num; i++) {
            if (n.isUse[i] && i != aIntPath[intPathLen - 1])
                continue;

            int min = int.MaxValue;

            for (int j = 0; j < num; j++) {
                if (intPathLen > 1 && aIntPath[intPathLen - 1] == i
                        && aIntPath[0] == j) continue;

                if (n.isUse[j] && j != aIntPath[0]) continue;

                if (i != j && W[i,j] <min)
                min = W[i, j];
            }
            intBound += min;
        }
        aIntPath = null;
        return intBound;
    }

    public int length(node n) {
        int intLength = 0;
        int[] aIntPath = readPath(n.path);
        int intPathLen = aIntPath.Length;

        for (int i = 0; i < intPathLen - 1; i++)
            intLength += W[aIntPath[i], aIntPath[i + 1]];

        intLength += W[aIntPath[intPathLen - 1], aIntPath[0]];

        return intLength;
    }

    int[] readPath(String strPath) {
        String[] aStrPath = strPath.Split(new char[]{','},
                StringSplitOptions.RemoveEmptyEntries);

        int intPathLen = aStrPath.Length;

        int[] aIntPath = new int[intPathLen];
        for (int i = 0; i < intPathLen; i++)
            aIntPath[i] = Convert.ToInt32(aStrPath[i]);

        aStrPath = null;
        return aIntPath;
    }
}

*/


// Debugging ***************************