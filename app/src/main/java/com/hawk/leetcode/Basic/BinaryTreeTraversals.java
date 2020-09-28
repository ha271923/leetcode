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
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        Out.i("Preorder traversal of binary tree is ");
        Out.printBTreeByPreorder(root);

        Out.i("\nInorder traversal of binary tree is ");
        Out.printBTreeByInorder(root);

        Out.i("\nPostorder traversal of binary tree is ");
        Out.printBTreeByPostorder(root);

        Out.i("\nLevelorder traversal of binary tree is ");
        levelOrder(root);
    }


    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> list = new ArrayList<Integer>();
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                TreeNode node = q.poll();
                list.add(node.val);
                // push child node into queue
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(new ArrayList<Integer>(list));
        }
        Out.println_ListList(result);
        return result;
    }
}
