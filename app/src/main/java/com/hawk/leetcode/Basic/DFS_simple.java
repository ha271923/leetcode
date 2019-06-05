package com.hawk.leetcode.Basic;

/**
 *
 * https://www.programiz.com/dsa/graph-dfs
 *
 *
 * DFS(G, u)
 *     u.visited = true
 *     for each v ∈ G.Adj[u]
 *         if v.visited == false
 *             DFS(G,v)
 *
 * init() {
 *     For each u ∈ G
 *         u.visited = false
 *      For each u ∈ G
 *        DFS(G, u)
 * }
 *
 *
 */

import com.hawk.leetcode.BaseClass;

import java.util.*;

public class DFS_simple extends BaseClass {

    class Graph
    {
        private int numVertices;
        private LinkedList<Integer> adjLists[];
        private boolean visited[];

        Graph(int vertices)
        {
            numVertices = vertices;
            adjLists = new LinkedList[vertices];
            visited = new boolean[vertices];

            for (int i = 0; i < vertices; i++)
                adjLists[i] = new LinkedList<Integer>();
        }

        void addEdge(int src, int dest)
        {
            adjLists[src].add(dest);
        }

        void DFS(int vertex)
        {
            visited[vertex] = true;
            System.out.print(vertex + " > ");

            Iterator ite = adjLists[vertex].listIterator();
            while (ite.hasNext())
            {
                int adj = (int)ite.next();
                if (!visited[adj])
                    DFS(adj);
            }
        }
    }
    /**
     *    0 ------> 1
     *      \       |
     *       \      |
     *        \     |
     *         \    V
     *          \->(2) ----> 3
     */

    public Object test()
    {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        // route(1) = 0 -> 1 ->2
        // route(2) = 0 -> 2 ->3
        System.out.println("Following is Depth First Traversal");

        g.DFS(0);
        return null;
    }
}