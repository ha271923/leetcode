package com.hawk.leetcode.Basic;

import com.hawk.leetcode.BaseClass;

import java.util.ArrayList;
import java.util.Stack;

// http://leeeyou.xyz/2016/02/23/blog-2016-02-23-%E7%AE%97%E6%B3%95%E4%B9%8B%E5%9B%BE%E7%9A%84%E6%B7%B1%E5%BA%A6%E4%BC%98%E5%85%88%E9%81%8D%E5%8E%86%E5%92%8C%E5%B9%BF%E5%BA%A6%E4%BC%98%E5%85%88%E9%81%8D%E5%8E%86/
// Tips: BFS與DFS是無方向性, 以任一Node起始, 朝四面八方走訪, 而非樹狀往下
// Tips: DFS不需要保存搜尋過的狀態
// DFS 的Node可能有2個以上的分岔, Binary Search Trees則是只有1~2分岔(左小右大)
public class DFS_matrix extends BaseClass {
    static ArrayList<Node> nodes = new ArrayList<>();

    public static void main(String[] args) {
        // create each node
        Node node40 = new Node(40);
        Node node10 = new Node(10);
        Node node20 = new Node(20);
        Node node30 = new Node(30);
        Node node60 = new Node(60);
        Node node50 = new Node(50);
        Node node70 = new Node(70);
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
 *     Ans: 40 > 10 > 20 > 30 > 60 > 50 > 70 >    走訪全部節點
 * */
        // describe the connections of all nodes by matrix[][]
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

        System.out.println("The DFS traversal of the graph using stack ");
        dfs_Stack(adjacency_matrix, node40); // B. Iterative DFS using stack

        System.out.println();

        clearVisitedFlags(); // reset for testing the next algorithm

        System.out.println("The DFS traversal of the graph using recursion ");
        dfs_ArrayList(adjacency_matrix, node40); // A. Recursive DFS
    }

    static class Node {
        int data;
        boolean visited; // 針對Search, 所設計的flag, 以免重複走訪

        Node(int data) {
            this.data = data;
        }
    }

    // 找出與此節點相連接的節點
    // if adjacency_matrix[i][j]==1, then nodes at index i and index j are connected
    static public ArrayList<Node> findNeighbours(int adjacency_matrix[][], Node x) { // function is same with BFS_matrix.java
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


    // A. Recursive DFS
    static public void dfs_ArrayList(int adjacency_matrix[][], Node node) {
        System.out.print(node.data + " > ");
        ArrayList<Node> neighbours = findNeighbours(adjacency_matrix, node);  // 找出鄰居們
        node.visited = true;
        for (int i = 0; i < neighbours.size(); i++) {  // 一一拜訪
            Node n = neighbours.get(i);
            if (n != null && !n.visited) { // KEY: 避免重複走訪, 使用visited flag, 且順著Node路徑一層一層往下挖
                dfs_ArrayList(adjacency_matrix, n); // KEY: DFS使用recursive
            }
        }
    }

    // B. Iterative DFS using stack
    // 使用Stack是因為DFS是一層一層往下鑽到底, 再往上爬一格去鑽別條支線, 這上到下的過程很像Stack的push/pop
    // 每個點進入 stack 的時刻以左上深色數字表示，每個點離開 stack 的時刻以右下淺色數字表示。
    // 每個點都會進入 stack 一次、離開 stack 一次，不會再有第二次。
    static public void dfs_Stack(int adjacency_matrix[][], Node startNode) {
        Stack<Node> stack = new Stack<>();
        stack.add(startNode);
        startNode.visited = true;
        while (!stack.isEmpty()) {
            Node element = stack.pop();  // 使用pop, 以鎖定一個node, 走訪其所有鄰居
            System.out.print(element.data + " ");

            ArrayList<Node> neighbours = findNeighbours(adjacency_matrix, element); // 找出鄰居們
            for (int i = 0; i < neighbours.size(); i++) { // 一一拜訪
                Node n = neighbours.get(i);
                if (n != null && !n.visited) { // KEY: visited flag, 且順著Node路徑一層一層往下挖
                    stack.push(n);
                    n.visited = true;
                }
            }
        }
    }

    public static void clearVisitedFlags() {
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).visited = false;
        }
    }
}
