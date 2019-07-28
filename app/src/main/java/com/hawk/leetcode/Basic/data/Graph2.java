package com.hawk.leetcode.Basic.data;

import java.util.ArrayList;
import java.util.List;

// class to represent a graph object
public class Graph2
{
    // A List of Lists to represent an adjacency list
    public List<List<Integer>> adjList = null;
    public int numVertics;

    // Constructor
    public Graph2(List<Edge> edges, int N)
    {
        numVertics = N;
        adjList = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            adjList.add(i, new ArrayList<>());
        }

        // add edges to the undirected graph
        for (int i = 0; i < edges.size(); i++)
        {
            int src = edges.get(i).source;
            int dest = edges.get(i).dest;

            adjList.get(src).add(dest); //  src ---> dest
            adjList.get(dest).add(src); //  src <--- dest
        }
    }
}
