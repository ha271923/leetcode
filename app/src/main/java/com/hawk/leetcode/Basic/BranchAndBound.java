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
 * // queue, enqueue(), dequeue()
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
 * 01背包问题
 * 最大团
 * 单源最短路径
 * 装载问题
 * 布线问题
 *
 *
 * 基本思想:
 * 分支限界法与回溯法求解目标不同 : 回溯法的求解目标是找出解空间中满足约束条件的所有解，而分支限界法的求解目标是找出满足约束条件的一个解，或者是在满足约束条件的解中找出使某一个目标函数值达到极大或者极小的解，即某种意义下的最优解
 * 搜索方式不同 : 回溯法以深度优先搜索的方式进行搜索，而分支限界法使用广度优先搜索或者最小耗费优先的方式进行搜索解空间，其策略是 : 在扩展结点处，先生成其所有的儿子结点(分支)， 然后从当前的活结点表中选择下一个扩展结点。
 * 计算一个函数值(限界) : 为了加速搜索的过程，在每一个活结点处，计算一个函数值，并根据函数值，从当前活结点表中选择一个最有利的结点作为扩展结点，使得搜索朝着解空间上最优解的分支进行推进，一遍尽快的找出一个最优解。
 * 每个活结点只有一次机会成为扩展结点，一旦成为扩展结点，就一次性产生所有的儿子结点，在儿子结点中，导致不可行解或者导致非最优解的儿子结点被舍弃，其余的加入到活结点表中。
 * 活结点表有两种框架：(1) 队列式分支限界法。(2) 优先队列式分支限界法 (主要是确定优先级的选取)
 *
 * 一：剪枝策略的寻找的方法
 * 1）微观方法：从问题本身出发，发现剪枝条件。
 * 2）宏观方法：从整体出发，发现剪枝条件。
 * 3）注意提高效率，这是关键，最重要的。
 * */
public class BranchAndBound {

    public static void main(String[] args) {
        new BranchAndBound().testKnapsack();
    }

    class Fruit {
        String name;
        int weight;
        int price;

        Fruit(String name, int weight, int price) {
            this.name = name;
            this.weight = weight;
            this.price = price;
        }

        public String toString() {
            return String.format("(%s, %d, %d)", name, weight, price);
        }
    }

    public static List<Fruit> knapsack(List<Fruit> fruits, int limit) {
        int[] values = new int[limit + 1];
        int[]  items = new int[limit + 1];

        for (int i = 0; i < fruits.size(); i++) {
            for (int w = fruits.get(i).weight; w <= limit; w++) {
                int p = w - fruits.get(i).weight;
                int newValue = values[p] + fruits.get(i).price;
                if (newValue > values[w]) {
                    values[w] = newValue;
                    items[w] = i;
                }
            }
        }
        List<Fruit> solution = new ArrayList<>();
        // JDK8 Lambda
        int min = Collections.min(fruits
                , (f1, f2) -> f1.weight - f2.weight).weight;
        for (int i = limit; i >= min; i -= fruits.get(items[i]).weight) {
            solution.add(fruits.get(items[i]));
        }
        return solution;
    }


    public void testKnapsack() {
        int MAX_WEIGHT=8;
        List<Fruit> fruits = knapsack(Arrays.asList(
                new Fruit("李子", 4, 4500),
                new Fruit("蘋果", 5, 5700),
                new Fruit("橘子", 2, 2250),
                new Fruit("草莓", 1, 1100),
                new Fruit("甜瓜", 6, 6700)),
                MAX_WEIGHT);
        System.out.println(fruits);
        int total=0;
        for(Fruit fruit : fruits)
            total+=fruit.price;
        System.out.println("Total="+total);
    }
}



/**
    void bfs_branch_and_bound(state_space_tree T, number&best) {
        queue_of_node Q;   //宣告一個佇列用來擺放欲走訪的節點
        node u, v;
        initialize(Q);       //啟始時將佇列清空
        v = root of T;
        enqueue(Q, v);  //走訪 root 節點，並將它加入佇列尾端
        best = value(v);  //初始化目前最佳值
        while (!empty(Q)) {
            dequeue(Q, v);          //從佇列前端取出一個節點  v
            for (each child u of v ){      //走訪 v 的每個子節點 u
                if (value(u) is better than best )
                    best = value(u);  //若找到更好的結果就更新目前最佳值
                if (bound(u) is better than best )
                    enqueue(Q, u);   //若一個節點是 promising 就把它加入佇列
            }                                           //尾端，以待下一層走訪時使用
        }
    }

    void knapsack2(int n, const int p[], const int w[], int W, int& maxprofit) {
        queue_of_node Q;  //宣告一個要存放欲走訪節點的佇列
        node u, v;
        initialize(Q);      //初始時將佇列清空
        v.level = 0; v.profit = 0; v.weight = 0;  //將根節點初始化，並走訪它
        maxprofit = 0;      //初始化目前最佳值
        enqueue(Q, v);   //將根節點加入佇列尾端
        while (!empty(Q)) {   //只要佇列中還有節點未走訪，就一直做下去
            dequeue(Q, v);      //從佇列前端取出欲走訪子節點的父節點
            u.level = v.level + 1;     //將 u 設成 v 的下一層子節點其中之一
            u.weight = v.weight + w[u.level];  //將 u 設成拿取下一層物品的子節點
            u.profit = v.profit + p[u.level];
            if (u.weight <= W && u.profit > maxprofit)  //若背包沒有爆掉，且獲得利益值大於最佳值，則更新它
                maxprofit = u.profit;
            if (bound(u) > maxprofit)
                enqueue(Q, u);  //檢查 u 是否 promising
            u.weight = v.weight;
            u.profit = v.profit;   //將 u 設成不拿取下一層物品
            if (bound(u) > maxprofit)
                enqueue(Q, u);   //的子節點，並檢查它是否 promising
        }
    }

    float bound(node u)  //回傳值就是 u 的 bound 值，呼叫者再把該值和目前最
    {
        index j, k;                //佳值進行比較，以決定 u 是否為 promising
        int totweight;
        float result;
        if (u.weight >= W) return 0;     //背包爆掉則傳回 bound = 0
        else {
            result = u.profit;  //先把 bound 值初設為到 u 節點為止的總共獲益值
            j = u.level + 1;
            totweight = u.weight;
            while (j <= n && totweight + w[j] <= W) {
                totweight = totweight + w[j];     //盡可能多拿取一些物品
                result = result + p[j];           //直到物品都拿光或是背包爆掉為止
                j++;
            }
            k = j;
            if (k <= n) result = result + (W - totweight) * (p[k] / w[k]);  //加上第 k 個
            return result;                                                                //物品的部份利益
        }
    }

    void bfs_branch_and_bound(state_space_tree T, number& best) {
        priority_queue_of_node PQ;   //使用優先權佇列來存放欲走訪的節點
        node u, v;
        initialize(PQ);
        v = root of T;        //先走訪根節點，並初設目前最佳值 best
        best = value(v);
        insert(PQ, v);      //將根節點插入優先權佇列中
        while (!empty(PQ)) {   //只要優先權佇列中還有未走訪節點就繼續做
            remove(PQ, v);       //取出佇列中優先權 (即 bound) 最高的節點
            if (bound(v) is better than best )   //若它仍然還是 promising 則往
            for (each child u of v ) {  //下層走訪它的每個子節點
                if (value(u) is better than best )
                    best = value(u);  //更新最佳值
                if (bound(u) is better than best )
                    insert(PQ, u);    //若 u 為 promising，則將
            }                                                         // 它插入佇列中
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

