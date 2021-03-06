package com.hawk.leetcode.Basic;

import com.hawk.leetcode.BaseClass;
import com.hawk.leetcode.Basic.data.Node;
import com.hawk.leetcode.Basic.data.Nodes;

import java.util.List;
import java.util.Stack;

/**
 * Compare Backtracking with DFS
 *  A. Backtracking is a more general purpose algorithm.
 *  B. DFS is a specific form of backtracking related to searching tree structures.
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
 * https://www.zybuluo.com/Yano/note/255699
 * LeetCode之Depth-first Search题目汇总
 * Balanced Binary Tree
 * Binary Tree Paths
 * Binary Tree Right Side View
 * Construct Binary Tree from Inorder and Postorder Traversal
 * Construct Binary Tree from Preorder and Inorder Traversal
 * Convert Sorted Array to Binary Search Tree
 * Convert Sorted List to Binary Search Tree
 * Course Schedule
 * Course Schedule II
 * Flatten Binary Tree to Linked List
 * Maximum Depth of Binary Tree
 * Number of Islands
 * Path Sum
 * Path Sum II
 * Populating Next Right Pointers in Each Node
 * Same Tree
 * Symmetric Tree
 * Sum Root to Leaf Numbers
 * Validate Binary Search Tree
 * */
// DFS 的Node可能有2個以上的分岔, Binary Search Trees則是只有1~2分岔(左小右大)
public class DFS extends BaseClass {

    public static void main(String[] args) {
        System.out.println("The DFS traversal of the graph using stack ");
        dfs_stack(Nodes.nodes.get(3)); // start node
        System.out.println(); // Ans1: 40 > 20 > 50 > 70 > 60 > 30 > 10 >

        Nodes.clearVistedFlag();

        System.out.println("The DFS traversal of the graph using recursion ");
        dfs_recursive(Nodes.nodes.get(3)); // start node
        System.out.println(); // Ans2: 40 > 10 > 30 > 60 > 70 > 20 > 50 >

    }

    // Tips1: 全節點探索一次
    // Tips2: 因為Node在connect時, 便是以List資料結構串接, 所以只要一層一層遞迴即可
    static public String dfs_recursive(Node node) {
        String out = "";
        out = node.data + " > ";
        System.out.print(node.data + " > ");
        node.visited = true;
        List<Node> myToNodes = node.getMyToNodes();
        for (int i = 0; i < myToNodes.size(); i++) { // 將該端點相關的端點們, 一個一個列舉出來
            Node n = myToNodes.get(i);
            if (n != null && !n.visited) {
                dfs_recursive(n); // KEY: visited flag, 且順著Node路徑一層一層往下挖
            }
        }
        return out;
    }

    // Iterative DFS using stack
    static public String dfs_stack(Node startNode) {
        String out = "";
        Stack<Node> stack = new Stack<Node>();
        stack.push(startNode);
        startNode.visited = true;
        while (!stack.isEmpty()) {
            Node element = stack.pop();
            out = element.data + " > ";
            System.out.print(element.data + " > ");
            List<Node> neighbours = element.getMyToNodes();
            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.visited) {
                    stack.push(n);
                    n.visited = true;
                }
            }
        }
        return out;
    }

}
