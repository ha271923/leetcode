package com.hawk.leetcode.Basic;

/**
 * 分支定界法（branch and bound）是一种求解离散数据组合的最优化问题。
 * 分支定界 (branch and bound) 算法是一种在问题的解空间树上搜索问题的解的方法。
 * 但与回溯算法不同，分支定界算法采用广度优先(BFS)或最小耗费优先的方法搜索解空间树，并且，在分支定界算法中，
 * 每一个活结点只有一次机会成为扩展结点。
 *
 * 分支限界法的设计原理
 * 在设计一个分支限界算法时，通常按照以下步骤进行：
 * 　　（1）针对所给问题，定义问题的解空间；
 * 　　（2）确定易于搜索的解空间结构；
 * 　　（3）以广度优先方式搜索解空间，并在搜索过程中用剪枝函数避免无效搜索。
 *
 * 回溯法采用DFS搜索解空间，而分支限界法一般采用BFS或以最小耗费优先的方式搜索解空间。
 *
 *
 * Different Ways to Add Parentheses
 * */
public class BranchAndBound {

    public static void main(String[] args) {

    }

}
