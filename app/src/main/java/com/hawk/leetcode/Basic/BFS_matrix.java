package com.hawk.leetcode.Basic;

import com.hawk.leetcode.BaseClass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// http://leeeyou.xyz/2016/02/23/blog-2016-02-23-%E7%AE%97%E6%B3%95%E4%B9%8B%E5%9B%BE%E7%9A%84%E6%B7%B1%E5%BA%A6%E4%BC%98%E5%85%88%E9%81%8D%E5%8E%86%E5%92%8C%E5%B9%BF%E5%BA%A6%E4%BC%98%E5%85%88%E9%81%8D%E5%8E%86/
/**
 * 代码框架：
 *   BFS多用于寻找最短路径的问题，DFS多用于搜尋全部節點
 *
 *   BFS(){
 *      init queue
 *      while(队列不为空且未找到目标节点){
 *               取队首节点扩展，并将扩展出的节点放入队尾；
 *               必要时要记住每个节点的父节点；
 *       }
 *  }
 *
 * */
// Tips: BFS與DFS是無方向性, 以任一Node起始, 朝四面八方走訪, 而非樹狀往下
// Tips: BFS不使用recursive, 使用looping
// Tips: BFS需要保存搜尋過的狀態, 一般使用一個queue
public class BFS_matrix extends BaseClass {
    private Queue<Node> queue; // KEY: BFS需要保存搜尋過的Temp Node狀態, 一般使用一個queue暫存
    static ArrayList<Node> nodes=new ArrayList<Node>();

    static class Node
    {
        int data;
        boolean visited; // 針對Search, 所設計的flag, 以免重複走訪

        Node(int data)
        {
            this.data=data;
        }
    }

    public BFS_matrix()
    {
        queue = new LinkedList<Node>();
    }

    // 找出與此節點相連接的節點
    // if adjacency_matrix[i][j]==1, then nodes at index i and index j are connected
    public ArrayList<Node> findNeighbours(int adjacency_matrix[][], Node x) { // function is same with DFS_matrix.java
        int nodeIndex = -1; // 因為要探尋ArrayList, 以-1代表陣列中無此node可作為start[?]Node
        for (int i = 0; i < nodes.size(); i++) { // find the matching idx of nodes
            if (nodes.get(i).equals(x)) {
                nodeIndex = i;
                break;
            }
        }

        ArrayList<Node> neighbours = new ArrayList<>();
        if (nodeIndex != -1) { // not found
            for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
                if (adjacency_matrix[nodeIndex][j] == 1) { // 根據adjacency_matrix[Y][X]的定義, 掃到1代表兩個node連結
                    neighbours.add(nodes.get(j));  // 掃到後再將連結nodes, 一一存入ArrayList
                }
            }
        }
        return neighbours;
    }

    // A. Looping BFS
    public void bfs_ArrayList(int adjacency_matrix[][], Node startNode) { // 非遞迴
        queue.add(startNode);
        startNode.visited=true;
        while (!queue.isEmpty())  // KEY: BFS使用 looping
        {
            Node element=queue.remove(); // 使用remove, 以鎖定一個node, 走訪其所有鄰居
            System.out.print(element.data + " > ");
            ArrayList<Node> neighbours=findNeighbours(adjacency_matrix,element); // 找出鄰居們
            for (int i = 0; i < neighbours.size(); i++) { // 一一拜訪
                Node n=neighbours.get(i);
                if(n!=null && !n.visited) // KEY: 避免重複走訪, 使用visited flag, 且順著Node路徑一層一層往下挖
                {
                    queue.add(n); // 沒走訪到的鄰居, 加入queue名單, 下此走訪時的依據
                    n.visited=true;
                }
            }
        }
    }

    public Object test()
    {
        // create each node
        Node node40 =new Node(40);
        Node node10 =new Node(10);
        Node node20 =new Node(20);
        Node node30 =new Node(30);
        Node node60 =new Node(60);
        Node node50 =new Node(50);
        Node node70 =new Node(70);
        // add all nodes to ArrayList for finding neighbours by looping
        nodes.add(node40);
        nodes.add(node10);
        nodes.add(node20);
        nodes.add(node30);
        nodes.add(node60);
        nodes.add(node50);
        nodes.add(node70);

/**
 *    adjacency Map: BFS與DFS是無方向性, 以任一Node起始, 朝四面八方走訪, 而非樹狀往下
 *    40 ---> 20 ---> 50 ---> 70
 *     |      /|\             ^
 *     |     / | \            |
 *     V    /  |  \           /
 *    10 <--   |  --> 60 --->
 *     |       |      ^
 *     |       V      |
 *     | ---> 30 ---> |
 *
 *     Ans: 40 > 10 > 20 > 30 > 60 > 50 > 70 >
 * */
        // describe the connections of all nodes by matrix[Y][X]
        int adjacency_matrix[][] = {
//                Matrix { 1=Nx connected Ny, 0=Nx not connect Ny }
//                N0, N1, N2, N3, N4, N5, N6
                {  0,  1,  1,  0,  0,  0,  0  }, // N0: 40
                {  0,  0,  0,  1,  0,  0,  0  }, // N1 :10
                {  0,  1,  0,  1,  1,  1,  0  }, // N2: 20
                {  0,  0,  0,  0,  1,  0,  0  }, // N3: 30
                {  0,  0,  0,  0,  0,  0,  1  }, // N4: 60
                {  0,  0,  0,  0,  0,  0,  1  }, // N5: 50
                {  0,  0,  0,  0,  0,  0,  0  }, // N6: 70
        };
        System.out.println("The BFS traversal of the graph is ");
        BFS_matrix bfsExample2 = new BFS_matrix();
        bfsExample2.bfs_ArrayList(adjacency_matrix, node40); // start node

        return null;

    }
}

