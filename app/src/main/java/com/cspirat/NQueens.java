package com.cspirat;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : NQueens
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 51. N-Queens
 */
public class NQueens {
    /**
     PASS= 2 solutions
     [
         [
             ".Q..",  // Solution 1
             "...Q",
             "Q...",
             "..Q."
         ]
         ,
         [
             "..Q.",  // Solution 2
             "Q...",
             "...Q",
             ".Q.."
         ]
     ]

     FAILED= 5 cases
     [
         [
             "Q...",
             "..Q.",
             "XXXX", // FAILED 1
         ],
         [
             "Q...",
             "...Q",
             "XXXX", // FAILED 2
         ],
         [
             "...Q",
             "Q...",
             "..Q.",
             ".X.." // FAILED 3
         ],
         [
             "...Q",
             ".Q..",
             "XXXX", // FAILED 4
         ]
         [
             "...Q",
             "Q...",
             "..Q.",
             "XXXX" // FAILED 5
         ]
     ]

     * time : O(n^n) 不是很确定
     * space : O(n)

     * @param n
     * @return
     */
    public static void main(String[] args) {
        List<List<String>> ret;
        ret = solveNQueens(4);
        printAnswer(ret);
    }
    static private void printAnswer(List<List<String>> result){
        for(int i = 0; i< result.size(); i++) {
            for(int j=0; j<result.get(i).size(); j++){
                System.out.println(result.get(i).get(j)+",");
            }
            System.out.println("");
        }
    }

    /*
        if n=4,
        Layer0-Loop , x= 0 -> check_8ways() = OK -> PUSH Recursive
            Layer1-Loop , x= 0 -> check_8ways() = Fail
            Layer1-Loop , x= 1 -> check_8ways() = Fail
            Layer1-Loop , x= 2 -> check_8ways() = OK -> PUSH by Recursive
                Layer2-Loop , x= 0 -> check_8ways() = Fail
                Layer2-Loop , x= 1 -> check_8ways() = Fail
                Layer2-Loop , x= 2 -> check_8ways() = Fail
                Layer2-Loop , x= 3 -> check_8ways() = OK -> PUSH by Recursive
                    Layer3-Loop , x= 0 -> check_8ways() = Fail
                    Layer3-Loop , x= 1 -> check_8ways() = Fail
                    Layer3-Loop , x= 2 -> check_8ways() = Fail
                    Layer3-Loop , x= 3 -> check_8ways() = Fail -> POP by return
        ...

     */
    static public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0)
            return res;
        force_scan_recursive(res, new int[n], 0);
        return res;
    }
    static public void force_scan_recursive(List<List<String>> res, int[] queens, int pos) {
        if (pos == queens.length) { // 注意: pos=0~4, not 0~3
            addSolution(res, queens);
            return;
        }
        for (int x = 0; x < queens.length; x++) { // 該列每一位置
            queens[pos] = x;
            if (check_8Ways(queens, pos)) {
                force_scan_recursive(res, queens, pos + 1);
            }
        }
    }

    static public boolean check_8Ways(int[] queens, int next_row_x) {
        for (int x = 0; x < next_row_x; x++) {
            if (queens[x] == queens[next_row_x]) { // 同一行
                return false;
            }
            // 以下檢查x是否在对角线上發生碰撞? ex: x= 1, next_row_x=0 or 2, abs(0-1)==abs(1-2)
            //                  x= 1
            //                  /  |  \
            //    next_row_x = 0   1   2  3<-(Safe~~)
            //                 X   X   X  Q
            //      abs()     (0 - 1),(1 - 2)
            //                 斜左下 , 斜右下
            else if (Math.abs(queens[next_row_x] - queens[x]) == Math.abs(x - next_row_x)) {
                return false;
            }
        }
        return true;
    }

    static public void addSolution(List<List<String>> res, int[] queens) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < queens.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < queens.length; j++) {
                if (queens[i] == j) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            list.add(sb.toString());
        }
        res.add(list);
    }
}
