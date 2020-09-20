package com.cspirat;

import com.utils.Out;

/**
 * Created by Edward on 23/07/2017.
 */
public class SameTree {
    public static void main(String[] args) {
        TreeNode root1, root2;
        root1 = new TreeNode(1);
        root1.left  = new TreeNode(3);
        root1.right = null;
        root1.left.left  = null;
        root1.left.right = new TreeNode(2);

        root2 = new TreeNode(1);
        root2.left  = new TreeNode(3);
        root2.right = null;
        root2.left.left  = null;
        root2.left.right = new TreeNode(2);

        boolean ret = isSameTree(root1,root2);
        Out.i("ret="+ret);
    }
    /**
     * 100. Same Tree

     Given two binary trees, write a function to check if they are equal or not.

     Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

     time : O(n);
     space : O(n);
     * @param p
     * @param q
     * @return
     */
    // Tips: recursive 遞迴解
    // 遞迴先鑽到最下層的最左node, 然後一一比對後, 返回
    // 三個if的寫法
    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;

        return     isSameTree(p.left, q.left)     // cmp L node
                && isSameTree(p.right, q.right);  // cmp R node
    }

    /*
     Bug:
        p= [1,2,3]
        q= [1,2,3]
        Output = false
        Expected = true
    */
    public static boolean isSameTree_hawk(TreeNode p, TreeNode q) { // Bug

        if (p.val != q.val) {
            return false;
        }

        do {
            p = p.left;
            q = q.left;
            if ( p.left != null && p.right != null) {
                if (p.val == q.val) {
                    p = p.right;
                    q = q.right;
                } else
                    return false;
            } else
                return false;
            if ( p.left != null && p.right != null) {
                if (p.val == q.val) {

                } else
                    return false;
            } else
                return false;
        } while( p.left != null && p.right != null);

        return true;
    }
}
