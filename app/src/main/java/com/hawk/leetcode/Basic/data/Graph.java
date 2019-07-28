package com.hawk.leetcode.Basic.data;

import java.util.LinkedList;

public class Graph
{
    public LinkedList<Integer> adjLists[]; // KEY: 圖的線段描述, 用連接點的"陣列", 每個點src都用一組LinkedList<Integer>來記錄dest
    public boolean visited[];

    public Graph(int vertices) // 這個圖形有幾個端點
    {
        adjLists = new LinkedList[vertices]; // ex: graph 2個點(src), n+m個dest
        visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<Integer>();
    }
    public void addEdge(int src, int dest)
    {
        adjLists[src].add(dest); // 描述線段的src到dest接法
    }
}
