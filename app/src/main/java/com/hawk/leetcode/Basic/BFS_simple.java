package com.hawk.leetcode.Basic;
import com.hawk.leetcode.Basic.data.Graph;
import java.util.*;

// BFS 的Node可能有2個以上的分岔, Binary Search Trees則是只有1~2分岔(左小右大)
public class BFS_simple {
    public static void main(String[] args) {
    /**
     *    0 ------> 1 <--
     *    ^ \       |    \
     *     \ \      |     \
     *      \ \     |      \
     *       \ \    V       \
     *        \ \->(2) ----> 3
     *         \----|
     */
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        int startVertex = 2;
        System.out.println("BFS start from "+startVertex);
        BFS(g, startVertex);
    }

    static void BFS(Graph graph, int vertex)
    {
        graph.visited[vertex]=true;
        // queue example: dequeue <== |S| a|b|c| aa|ab| ba|bb|bc|ca| aaa|aab|aac| aba|abb|...
        LinkedList<Integer> queue = new LinkedList<Integer>(); // 1. BFS queue
        queue.add(vertex);

        while (queue.size() != 0) // KEY: 用queue迴圈
        {
            vertex = queue.poll(); // Retrieves and removes the head (first element) of this list.
            System.out.print(vertex +" > ");

            Iterator<Integer> it = graph.adjLists[vertex].listIterator(); // 以此接點的連接列表的Iterator
            while (it.hasNext()) // 一個接一個掃出列表中的項目
            {
                int n = it.next();
                if (!graph.visited[n])
                {
                    graph.visited[n] = true;
                    queue.add(n); // KEY: 不是遞迴, 是add queue
                }
            }
        }
    }
}