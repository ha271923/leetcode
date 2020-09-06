package com.cspirat;

import com.utils.Out;

import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UniqueBinarySearchTrees
 * Creator : Edward
 * Date : Aug, 2017
 * Description : TODO
 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args) {
        int n=3;

        int ret = numTrees(n);
        Out.i("ret="+ret+"  size="+ret);
    }
    /**
     * 96. Unique Binary Search Trees
     * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

     For example,
     Given n = 3, there are a total of 5 unique BST's.

     1         3     3      2      1
      \       /     /      / \      \
       3     2     1      1   3      2
      /     /       \                 \
     2     1         2                 3

     n = 3
     root : 1   left : 0 right : 2   f(0) * f(2);
     root : 2   left : 1 right : 1   f(1) * f(1);
     root : 3   left : 2 right : 0   f(2) * f(0);

     f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

     time : O(n);
     space : O(n);
     * @param n
     * @return
     */

/*
    http://bangbingsyb.blogspot.com/2014/11/leetcode-unique-binary-search-trees-i-ii.html
    思路：唯一二叉搜索樹II
    首先註意這裡是BST而不是普通的二叉樹，所以數字位數插入的位置有影響。此類找組合/排列的題都需要找找規律。
    要求生成所有的唯一BST，類似組合/排列的譯文，可以遞歸構造。

    1.根例程可以任取min〜max（例如min = 1，max = n），假如取定為i。
    2.則left子樹由min〜i-1組成，假設可以有L種可能。right子樹由i + 1〜max組成，假設有R種可能。生成所有可能的left / right子樹。
    3對於每個生成的左子樹/右子樹組合<T_left（p），T_right（q）>，p = 1 ... L，q = 1 ... R，添加上根節點i而組成一顆新樹。

    定義f（n）為唯一BST的數量，
    EX: 以n = 3為例：
    構造的BST的根例程可以取{1，2，3}中的任一數字。
    以1為root，則left subtree只能有0個例程，而right subtree有2，3兩個例程。所以left / right subtree一共的組合數量為：f（0）* f（2）= 2
    以2為root，則left subtree只能為1，右子樹只能為2：f（1）* f（1）= 1
    以3為root，則left subtree有1，2兩個例程，right subtree有0個例程：f（2）* f（0）= 2

    總結規則：
    f（0）= 1
    f（n）= f（0）* f（n-1）+ f（1）* f（n-2）+ ... + f（n-2）* f（1）+ f（n-1 ）* f（0）
*/
    static public int numTrees(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int cur = 1; cur <= n; cur++) { // LOOP1: n=3={1,2,3}=3 times.依次取root數
            for (int prev = 0; prev < cur; prev++) { // LOOP2: n=3={{0},{0,1},{0,1,2}}=6 times
                f[cur] += f[prev] * f[cur - prev - 1]; // KEY: 死背公式! 這是找出算法的規律性, 而非真的經由長出tree來統計數量
            }
        }
        return f[n];
    }
}
