package com.hawk.leetcode.Basic;
import java.io.*;
import java.util.*;

public class BFS_simple {
    public static void main(String[] args) {
        new BFS_simple().test();
    }

    class Graph
    {
        private LinkedList<Integer> adjLists[];
        private boolean visited[];

        public Graph(int vertices)
        {
            adjLists = new LinkedList[vertices];
            visited = new boolean[vertices];

            for (int i = 0; i < vertices; i++)
                adjLists[i] = new LinkedList<Integer>();
        }
        void addEdge(int src, int dest)
        {
            adjLists[src].add(dest);
        }

        void BFS(int vertex)
        {
            // Create a queue for BFS
            LinkedList<Integer> queue = new LinkedList<Integer>();

            // Mark the current node as visited and enqueue it
            visited[vertex]=true;
            queue.add(vertex);

            while (queue.size() != 0)
            {
                // Dequeue a vertex from queue and print it
                vertex = queue.poll();
                System.out.print(vertex +" ");

                Iterator<Integer> it = adjLists[vertex].listIterator();
                while (it.hasNext())
                {
                    int n = it.next();
                    if (!visited[n])
                    {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
        }
    }



    /**
     *    0 ------> 1 <--
     *    ^ \       |    \
     *     \ \      |     \
     *      \ \     |      \
     *       \ \    V       \
     *        \ \->(2) ----> 3
     *         \----|
     */

    public Object test()
    {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        int startVertex = 2;
        System.out.println("DFS start from "+startVertex);

        g.BFS(startVertex);
        return null;
    }
}