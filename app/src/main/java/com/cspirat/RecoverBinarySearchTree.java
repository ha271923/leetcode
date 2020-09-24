package com.cspirat;

import com.utils.Out;

import java.util.Stack;

/**
 * File Name : RecoverBinarySearchTree
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 99. Recover Binary Search Tree
 */
// Hawk: Very hard????
public class RecoverBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root;
        root = new TreeNode(1);

        root.left  = new TreeNode(3);
        root.right = null;

        root.left.left  = null;
        root.left.right = new TreeNode(2);

        recoverTree(root);
        Out.printTreeNodes(root);
    }
    /**
     * Two elements of a binary search tree (BST) are swapped by mistake.
     Recover the tree without changing its structure. 樹的形狀不變, 節點的數值更改

     數列排序需偵測null
     [root, root.L, root.R, root.?(不是null的L或R).L, root.?(不是null的L或R).R ....]

     Ex1: Input: [1,3,null,null,2]  <- BST search sequence
        1
       /
      3
       \
        2
     Output: [3,1,null,null,2]
       3
      /
     1
      \
       2

     Ex2: Input: [3,1,4,null,null,2]
       3
      / \
     1   4
        /
       2
     Output: [2,1,4,null,null,3]
       2
      / \
     1   4
        /
       3

     time : O(n)
     space : O(n)
     */

    static TreeNode error1 = null;
    static TreeNode error2 = null;
    static TreeNode prevLayer = null;
    // Q: 題目说是其中有两个節點的顺序被调换了
    // Q: 樹的形狀不變, 節點的數值可更改
    static public void recoverTree(TreeNode root) {
        if (root == null)
            return;
        helper_recur(root);
        // swap two error nodes
        int temp = error1.val;
        error1.val = error2.val;
        error2.val = temp;
    }
    static int LAYER=0;
    static public void helper_recur(TreeNode node) {
        if (node == null)
            return;
        Out.i("LAYER="+LAYER++ +"   val="+node.val);
        helper_recur(node.left);

        if (prevLayer != null && prevLayer.val >= node.val) { // KEY: L node recursive進入後, 發現上一層的節點的數值比下一層的大
            if (error1 == null) // 首次發現此狀況,找到錯誤1
                error1 = prevLayer;

            error2 = node;  // 再次發現此狀況,找到錯誤2
        }
        prevLayer = node; // 進入下一層前, 先將當前node備份為prevLayer
        helper_recur(node.right);  // R node recursive
    }

    public void recoverTree_stack(TreeNode root) {
        if (root == null)
            return;
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (prev != null && cur.val <= prev.val) {
                    if (first == null)
                        first = prev;
                    second = cur;
                }
                prev = cur;
                cur = cur.right;
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
