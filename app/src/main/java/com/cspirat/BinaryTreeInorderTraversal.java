package com.cspirat;

import com.utils.Out;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** 
 * 描述建構  r,  L ,R,L
 * Input:  [1,null,2,3]
 *       1
 *     /   \
 *   null   2
 *   / \   / \
 *  A   B 3   C
 *
 * Output: [A,null,B,1,3,2,C]<- 上述建構出的Tree的Traversal次序, 若以 inorder方式進行後的次序
 *         因為A,B,C,null均不存在, 所以去掉後如下:
 * Output: [1,3,2] <- 上述建構出的Tree的Traversal次序, 若以 inorder方式進行後的次序
 *          L,C,R
 */
/*
   答案要的是遍歷的順序,而非建立

        4
       / \
      2   6
     / \ / \
    1  3 5  7
 ->|由左往右掃
Ans:12 3456 7

    三種 Interative Binary Tree Traversal
    A. preorder:  中->左->右，4213657 (起始在最上子節點，先拜訪最上父節點再拜訪子節點)
    B. inorder:   左->中->右，1234567 (起始在最左子節點，先拜訪左子節點，再拜訪父節點，最後拜訪右子節點)
    C. postorder: 左->右->中，1325764 (起始在最左子節點，先拜訪左右子節點，最後拜訪父節點)
 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNodeLR root;
            root = new TreeNodeLR(4);

          root.L = new TreeNodeLR(2);
          root.R = new TreeNodeLR(6);

        root.L.L = new TreeNodeLR(1);
        root.L.R = new TreeNodeLR(3);

        root.R.L = new TreeNodeLR(5);
        root.R.R = new TreeNodeLR(7);

        List<Integer> ret = inorderTraversal(root);
        Out.i("ret="+ret+"  size="+ret.size());
    }

    /**
     * 94. Binary Tree Inorder Traversal
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * time : O(n)
     * space : O(n)
     * @param root
     * @return
     */

    public static List<Integer> inorderTraversal(TreeNodeLR root) {
       List<Integer> res = new ArrayList<>();
       if (root == null)
           return res;
        inorderTraversal_recur(res, root);
       return res;
    }

    /*
    // DFS 的Node可能有2個以上的分岔, Binary Search Trees則是只有1~2分岔(左小右大)

        2
       curX.
       val
       /  \
   curX.  curX.
     LX    RX
     1     3
    */
    // Tips: 誰來當cur node, cur是不是null, cur.val要不要add res
    public static void inorderTraversal_recur(List<Integer> res, TreeNodeLR cur) {
        if (cur == null) // KEY:
            return;
        // 遞迴的目標是 curX.L/R, 加入list的則是curX.val
        inorderTraversal_recur(res, cur.L); // 先靠不斷遞迴找到最左節點, 當作出發點
        res.add(cur.val); // KEY: 思考時常常會想到左與右均沒有時, 才add res,但是此處巧妙的加入val?誰來add? 注意: inorder是紀錄每個最node.L,而非LR均為null
        inorderTraversal_recur(res, cur.R); // 遞迴掃右節點,cur.R節點當作下一層遞迴的cur節點
    }

     /*
     *
     * push -->      --> pop
     *         | 9 |<-- peak
     *         | 8 |
     *         |_4_|
     *         empty
     *
         */
    public static List<Integer> inorderTraversal_stack(TreeNodeLR root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<TreeNodeLR> stack = new Stack<>();
        TreeNodeLR cur = root;
        while (cur != null || !stack.isEmpty()) { // LOOP:
            while (cur != null) {
                stack.push(cur); // KEY: 利用stack特性: 一開始最上的node最先push, 探索到最左下層的node最先pop & add
                cur = cur.L;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.R;
        }
        return res;
    }
}
