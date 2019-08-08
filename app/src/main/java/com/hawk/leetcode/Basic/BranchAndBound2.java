package com.hawk.leetcode.Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


// Debugging ***************************
public class BranchAndBound2 {
    final int maxW = 80;
    final int typeObj = 4;

    public static void main(String[] args) {
        int[] p = new int[] {4, 6, 8, 15};
        int[] w = new int[] {10, 20, 30, 50};

        BranchAndBound2 t =new BranchAndBound2();
        t.knapsack2(p,w);
        System.out.println();
    }

    public class Node {
        int  level ;   //該節點在樹中的層級
        int  profit ;  //目前累積的利益值
        int  weight ;  //目前累積的物品重量值
    }

    void knapsack2(int[] p, int[] w) {
        Queue<Node> Q;  //宣告一個要存放欲走訪節點的佇列
        Node child, parent;
        Q = new LinkedList<>();      //初始時將佇列清空
        parent = new Node(); child = new Node();
        parent.level = 0; parent.profit = 0; parent.weight = 0;  //將根節點初始化，並走訪它
        int maxprofit = 0;      //初始化目前最佳值
        Q.add(parent);   //將根節點加入佇列尾端
        while (!Q.isEmpty()) {   //只要佇列中還有節點未走訪，就一直做下去
            Q.remove(parent);      //從佇列前端取出欲走訪子節點的父節點
            child.level = parent.level + 1;     //將 u 設成 v 的下一層子節點其中之一
            child.weight = parent.weight + w[child.level];  //將 u 設成拿取下一層物品的子節點
            child.profit = parent.profit + p[child.level];
            if (child.weight <= maxW && child.profit > maxprofit)  //若背包沒有爆掉，且獲得利益值大於最佳值，則更新它
                maxprofit = child.profit;
            if (bound(child, p, w) > maxprofit)
                Q.add(child);  //檢查 u 是否 promising
            child.weight = parent.weight;
            child.profit = parent.profit;   //將 u 設成不拿取下一層物品
            if (bound(child, p, w) > maxprofit)
                Q.add(child);   //的子節點，並檢查它是否 promising
        }
    }

    float bound(Node u, int[] p, int[] w)  //回傳值就是 u 的 bound 值，呼叫者再把該值和目前最佳值進行比較，以決定 u 是否為 promising
    {
        int j, k;
        int totweight;
        float result;
        if (u.weight >= maxW)
            return 0; //背包爆掉則傳回 bound = 0
        else {
            result = u.profit;  //先把 bound 值初設為到 u 節點為止的總共獲益值
            j = u.level + 1;
            totweight = u.weight;
            while (j <= typeObj && totweight + w[j] <= maxW) {
                totweight = totweight + w[j];     //盡可能多拿取一些物品
                result = result + p[j];           //直到物品都拿光或是背包爆掉為止
                j++;
            }
            k = j;
            if (k <= typeObj)
                result = result + (maxW - totweight) * (p[k] / w[k]);  //加上第 k 個
            return result;                                                                //物品的部份利益
        }
    }


}
