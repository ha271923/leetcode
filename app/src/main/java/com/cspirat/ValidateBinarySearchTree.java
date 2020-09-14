package com.cspirat;

import com.utils.Out;

/**
 * Created by Edward on 24/07/2017.
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root;
        root = new TreeNode(5);

        root.left  = new TreeNode(1);
        root.right = new TreeNode(4);

        root.left.left  = null;
        root.left.right = null;

        root.right.left  = new TreeNode(3);
        root.right.right = new TreeNode(6);
        Out.i("ret="+isValidBST(root));
    }

    /**
     * 98. Validate Binary Search Tree
     * Given a binary tree, determine if it is a valid binary search tree (BST).

     Assume a BST is defined as follows:

     The left subtree of a node contains only nodes with keys less than the node's key.
     The right subtree of a node contains only nodes with keys greater than the node's key.
     Both the left and right subtrees must also be binary search trees.

     time : O(n)
     space : O(n)
     * @param root
     * @return
     */

    public static boolean isValidBST(TreeNode root) {

        if (root == null)
            return true;
        return helper_recur(root, null, null);
    }

    public static boolean helper_recur(TreeNode node, Integer min, Integer max) {
        if( node != null )
            Out.i("    val="+node.val+"   min="+min+"   max="+max);
        else
            Out.i("    k,val=null    min="+min+"   max="+max);

        // KEY: 檢驗BST的條件式
        if (node == null)
            return true;
        if (min != null && node.val <= min)
            return false;
        if (max != null && node.val >= max)
            return false;

        return helper_recur(node.left , min     , node.val)
            && helper_recur(node.right, node.val, max     );
    }
}
