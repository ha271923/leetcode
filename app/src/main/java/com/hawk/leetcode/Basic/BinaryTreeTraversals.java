package com.hawk.leetcode.Basic;

import com.cspirat.TreeNode;
import com.hawk.leetcode.Basic.data.Node;
import com.utils.Out;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeTraversals {
/**
 * 理論上有四種輸出順序：
 *
 * 1. 前序遍歷 (Preorder Traversal)
 * 2. 中序遍歷 (Inorder Traversal)
 * 3. 後序遍歷 (Postorder Traversal)
 * 4. 層序遍歷 (Level-order Traversal)
 * 但實際上也可歸類為兩種分類方式，深度優先搜尋 DFS、廣度優先搜尋 BFS，只不過節點輸出的順序改變而已。這兩個搜尋會擇日討論。
 *
 * EX:
 *\
 * [((7 + 8) ÷ 5) × (4 + 9 - 3)]
 * 後序 (Postfix)：又稱 逆波蘭表示法 (Reverse Polish notation)。
 * [7 8 + 5 ÷ 4 9 3 - + ×]
 * 拉蒙碎碎念
 * 有關四則運算的部分，可以自己用程式寫寫看。可以搭配之前說過的堆疊(Stack)來實作喔！
 */

    public static void main(String[] args)
    {
        /*
        EX: test input Tree sample
                    1
                   / \
                  2   3
                 / \
                4   5
         */
        TreeNode root   = new TreeNode(1);
        root.left       = new TreeNode(2);
        root.right      = new TreeNode(3);
        root.left.left  = new TreeNode(4);
        root.left.right = new TreeNode(5);

        Out.i("Preorder traversal of binary tree is ");
        printBTreeBy_Preorder(root);

        Out.i("\nInorder traversal of binary tree is ");
        printBTreeBy_Inorder(root);

        Out.i("\nPostorder traversal of binary tree is ");
        printBTreeBy_Postorder(root);

        Out.i("\nLevelorder traversal of binary tree is ");
        levelOrder(root);
    }

    // Given a binary tree, print its nodes in preorder
    // Preorder traversal of binary tree is
    //   1 2 4 5 3
    public static void printBTreeBy_Preorder(TreeNode node)
    {
        if (node == null)
            return;
        System.out.print(node.val + " "); // Pre-order 先印 當前Node值
        printBTreeBy_Preorder(node.left);
        printBTreeBy_Preorder(node.right);
    }

    // Given a binary tree, print its nodes in In-order
    // Inorder traversal of binary tree is
    //   4 2 5 1 3
    public static void printBTreeBy_Inorder(TreeNode node)
    {
        if (node == null)
            return;
        printBTreeBy_Inorder(node.left);
        System.out.print(node.val + " "); // In-order 中印 當前Node值
        printBTreeBy_Inorder(node.right);
    }

    // Given a binary tree, print its nodes in Post-order
    // Postorder traversal of binary tree is
    //   4 5 2 3 1
    public static void printBTreeBy_Postorder(TreeNode node)
    {
        if (node == null)
            return;
        printBTreeBy_Postorder(node.left);
        printBTreeBy_Postorder(node.right);
        System.out.print(node.val + " "); // Post-order 後印 當前Node值
    }

    /*
      https://www.itread01.com/content/1543866426.html
     下面是Java中Queue的一些常用APIs：
        add     新增一個元索 如果佇列已滿，則丟擲一個IIIegaISlabEepeplian異常
        offer   新增一個元素 並返回true 如果佇列已滿，則返回false
        put     新增一個元素 如果佇列滿，則阻塞

        remove  移除並返回佇列頭部的元素 如果佇列為空，則丟擲一個NoSuchElementException異常
        poll    移除並返問佇列頭部的元素 如果佇列為空，則返回null
        take    移除並返回佇列頭部的元素

        element 返回佇列頭部的元素 如果佇列為空，則丟擲一個NoSuchElementException異常
        peek    返回佇列頭部的元素 如果佇列為空，則返回null
    */
    // Levelorder traversal of binary tree is
    //  q=[1]
    //  q=[2, 3]
    //  q=[4, 5]
    // List, Queue, TreeNode 三種data structure
    public static List<List<Integer>> levelOrder(TreeNode root) { // 非遞迴
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;
        // LinkedList與ArrayList一樣實現List介面，只是ArrayList是List介面的'大小可變陣列'的實現，
        // LinkedList是List介面連結'串列'的實現。基於連結串列實現的方式使得LinkedList在插入和刪除時
        // 更優於ArrayList，而隨機訪問則比ArrayList遜色些
        Queue<TreeNode> q = new LinkedList<TreeNode>(); // KEY: Queue<> impl LinkedList<>
        q.offer(root); // 類似 add()
        while (!q.isEmpty()) { // LOOP1:
            List<Integer> list = new ArrayList<Integer>(); // 存這一層的結果
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) { // LOOP2: 掃出本層輸出並準備下層輸入
                TreeNode node = q.poll();  // 取出本層node
                list.add(node.val); // 將本層node存入結果list

                // KEY: 掃出下一層要用的node就是當層每個node的左右節點, 放到queue(先進先出)
                if (node.left != null)
                    q.offer(node.left); // 類似 add(), left 先加
                if (node.right != null)
                    q.offer(node.right); // 類似 add(), right 後加
            }
            result.add(new ArrayList<Integer>(list));
        }
        Out.println_ListList(result);
        return result;
    }
}
