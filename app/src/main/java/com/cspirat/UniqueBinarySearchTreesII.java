package com.cspirat;

import android.content.Context;

import com.utils.Out;

import java.util.ArrayList;
import java.util.List;

import static com.utils.Out.print_ListTreeNode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UniqueBinarySearchTreesII
 * Creator : Edward
 * Date : Aug, 2017
 * Description : TODO
 */
public class UniqueBinarySearchTreesII {
    // Tips: 數字從1開始, 一定是加1, 去累加, 所以 {1,2,3,4......}
    public static void main(String[] args) {
        int n=3;

        // List<TreeNode> ret = generateTrees(n);
        List<TreeNode> ret = generateTrees_recur(n);
        Out.i("ret="+ret+"  size="+ret.size());
        print_ListTreeNode(ret);
    }
    /**
     * 95. Unique Binary Search Trees II
     * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

     BST的TREE的原則一定是比root小左邊, 比root大右邊, root 中間

          中
         curX.
         val          1
         /  \          \
     curX.  curX.       3  比 1大
     LX      RX        /
     小      大        2   比 3小

     左node一定比中node小,重點是定義中node
     右node一定比中node大,重點是定義中node

     For example,
     Given n = 3, your program should return all 5 unique BST's shown below.

     1         3     3      2      1
      \       /     /      / \      \
       3     2     1      1   3      2
      /     /       \                 \
     2     1         2                 3

     time : O(n^2);
     space : O(n);

     * @param n
     * @return
     */


    static public List<TreeNode> generateTrees_recur(int n) {
        if (n == 0)
            return new ArrayList<>(); // return empty value
        return genTreeList(1, n); // Tips: 數組是從1開始
    }

    /*
                s=1,n=3
                  o
                / | \
               /  \  \
              /    \  \
              1     2  3
              x     x  o
                     / | \
                    / /   \
                   1 2     3
                   x o      x

    Rule1: 用過的數值不要再取
    Rule2: 左小右大
    Rule3: n是數字範圍也是層數
    Tips:  list只要紀錄root node
    */
    static public List<TreeNode> genTreeList(int start, int end) {
        Out.i("+++++ s="+start+"  e="+end);
        List<TreeNode> list = new ArrayList<>(); // new 一個List, 裡面裝著不定數量的TreeNode
        if (start > end) { // KEY: 重要檢查式, 因為本algorithm掃描是有序從 start~end, 所以基本條件不成立
            Out.i("list.add(null)");
            list.add(null); // 基本條件不成立的要加上剎車, 以免遞迴繼續往下掃
        }

        // Tips: 將數組start,end  轉換成centerIdx
        for (int centerIdx = start; centerIdx <= end; centerIdx++) { // KEY: LOOP1: 每個數輪流做centerIdx, 終止條件的設計 EX: n=3, 會有三次recuresive ,在第三層時,因為LOOP1的條件不足, 停止往下繼續recursive
            Out.i("+++ centerIdx="+centerIdx);
            // Divide and Conquer
            //      (centerIdx-1)   (centerIdx)   (centerIdx+1)
            //         小       中       大
            List<TreeNode> leftList = genTreeList(start, centerIdx - 1); // KEY:遞迴 L: centerIdx是用過的center數, 左node一定比中node小,重點是定義中node
            List<TreeNode> rightList = genTreeList(centerIdx + 1, end);  // KEY:遞迴 R: centerIdx是用過的center數, 右node一定比中node大,重點是定義中node
            // KEY: 通過 L, R 兩次上述檢查式
            for (TreeNode left : leftList) { // LOOP2: 列舉L,即使為null
                for (TreeNode right : rightList) { // LOOP3: 列舉R,即使為null
                    TreeNode center = new TreeNode(centerIdx); // centerIdx是用過的center數,
                    // 上述通過上面LR兩次genTreeList檢驗的, 代表L與R均有設定過值了(可能是null)
                    center.left = left;   //
                    center.right = right; //
                    Out.i("  list.add("+center.val+")");
                    list.add(center); // n=3, 因為LOOP1條件的關係, 同一個tree也只會add三次
                }
            }
            Out.i("--- centerIdx="+centerIdx);
        }
        Out.i("----- s="+start+"  e="+end);
        return list;
    }



    static public List<TreeNode> generateTrees_clone(int n) {
        List<TreeNode>[] list = new List[n + 1]; // 陣列參考
        list[0] = new ArrayList<>();             // 一組陣列參考 賦予數值ArrayList<List<TreeNode>> 物件
        if (n == 0)
            return list[0];
        list[0].add(null);
        for (int i = 1; i <= n; i++) { // LOOP1: n=3={1,2,3}=3 times
            list[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) { // LOOP2: n=3={{0},{0,1},{0,1,2}}=6 times
                for (TreeNode left : list[j]) { // LOOP3: L node
                    for (TreeNode right : list[i - j - 1]) { // KEY: LOOP4: R node
                        TreeNode root = new TreeNode(j + 1);
                        root.left = left;
                        root.right = clone(right, j + 1);
                        list[i].add(root);
                    }
                }
            }
        }
        return list[n];
    }

    static public TreeNode clone(TreeNode root, int k) {
        if (root == null)
            return root;
        TreeNode cur = new TreeNode(root.val + k);
        cur.left = clone(root.left, k);
        cur.right = clone(root.right, k);
        return cur;
    }

}
