package com.hawk.leetcode.Basic;
import com.hawk.leetcode.Basic.data.Graph;
import java.util.*;

public class DFS_simple {

    public static void main(String[] args) {
        new DFS_simple().test();
    }

    static void DFS(Graph graph, int vertex)
    {
        graph.visited[vertex] = true;
        System.out.print(vertex + " > ");

        Iterator<Integer> it = graph.adjLists[vertex].listIterator(); // 以此接點的連接列表的Iterator
        while (it.hasNext()) // 一個接一個掃出列表中的項目
        {
            int n = it.next();
            if (!graph.visited[n])
                DFS(graph, n); // KEY: 遞迴往下層鑽
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
        DFS(g, startVertex);
        return null;
    }
}