package com.hawk.leetcode.Basic;

/**
 *
 * https://www.programiz.com/dsa/graph-bfs
 *
 *
 * BFS pseudocode
 *   create a queue Q
 *   mark v as visited and put v into Q
 *   while Q is non-empty
 *   remove the head u of Q
 *   mark and enqueue all (unvisited) neighbours of u
 *
 *
 *
 */

import com.hawk.leetcode.Basic.data.Edge;
import com.hawk.leetcode.Basic.data.Graph;

import java.util.Queue;
import java.util.*;

// https://www.techiedelight.com/breadth-first-search/
class BFS_simple2
{
    // Iterative Java implementation of Breadth first search
    public static void main(String[] args)
    {
        /*
         *                    /- 9
         *                   /
         *               /- 5 -- 10
         *              /
         *         /- 2 --- 6
         *        /
         *       /
         *     1 ---- 3
         *       \       /- 8
         *        \     /
         *         \- 4 --- 7 -- 11
         *                   \
         *                    \- 12
         *
         *     0
         *     13
         *     14
         *     vertex 0, 13 and 14 are single nodes, Nodes = 15
         *
         * */
        // List of graph edges as per above diagram
        List<Edge> edges = Arrays.asList(
                new Edge(1, 2), new Edge(1, 3), new Edge(1, 4),
                new Edge(2, 5), new Edge(2, 6), new Edge(5, 9),
                new Edge(5, 10), new Edge(4, 7), new Edge(4, 8),
                new Edge(7, 11), new Edge(7, 12)
                // vertex 0, 13 and 14 are single nodes
        );

        // Set number of vertices in the graph
        final int numVertics = 15;

        // create a graph from edges
        Graph graph = new Graph(edges, numVertics);

        // BFS-1 loop -----------------------------------------------
        BFS_1(graph);

        // BFS-2 recursive -----------------------------------------------
        BFS_2(graph);

        System.out.println();
    }

    // Perform BFS loop on graph
    public static void BFS_1(Graph graph) {
        System.out.println("BFS_loop");
        boolean[] visited = new boolean[graph.numVertics];

        for (int i = 0; i < graph.numVertics; i++) {
            if (visited[i] == false) {
                // start BFS traversal from vertex i
                BFS_loop(graph, i, visited);
            }
        }
        System.out.println();
    }

    public static void BFS_loop(Graph graph, int v, boolean[] visited)
    {
        Queue<Integer> q = new ArrayDeque<>(); // create a queue used to do BFS
        visited[v] = true; // mark source vertex as visited
        q.add(v); // push source vertex into the queue

        // run till queue is not empty
        while (!q.isEmpty())
        {
            // pop front node from queue and print it
            v = q.poll();
            System.out.print(v + " ");

            // do for every edge (v -> u)
            for (int u : graph.adjList.get(v)) {
                if (!visited[u]) {
                    visited[u] = true; // mark it visited and push it into queue
                    q.add(u);
                }
            }
        }
    }

    // Perform BFS recursively on graph
    public static void BFS_2(Graph graph) {
        System.out.println("BFS_recursive");
        boolean[] visited = new boolean[graph.numVertics];
        // create a queue used to do BFS
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < graph.numVertics; i++) {
            if (visited[i] == false)
            {
                visited[i] = true; // mark source vertex as visited
                queue.add(i); // push source vertex into the queue
                BFS_recursive(graph, queue, visited); // start BFS traversal from vertex i
            }
        }
        System.out.println();
    }
    public static void BFS_recursive(Graph graph, Queue<Integer> q, boolean[] visited)
    {
        if (q.isEmpty())
            return;

        // pop front node from queue and print it
        int v = q.poll();
        System.out.print(v + " ");

        // do for every edge (v -> u)
        for (int u : graph.adjList.get(v))
        {
            if (!visited[u])
            {
                visited[u] = true; // mark it visited and push it into queue
                q.add(u);
            }
        }
        BFS_recursive(graph, q, visited);
    }
}