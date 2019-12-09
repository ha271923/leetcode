package com.cspirat;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SpiralMatrix
 * Creator : Edward
 * Date : Sep, 2017
 * Description : 54. Spiral Matrix
 */
public class SpiralMatrix {
    /**
     * For example,
     Given the following matrix:

     [
       [ 1, 2, 3 ],
       [ 4, 5, 6 ],
       [ 7, 8, 9 ]
     ]
     You should return [1,2,3,6,9,8,7,4,5].

     time : O(n * m)     n * m : 总元素个数
     space : O(n * m)

     * @param matrix
     * @return
     */
/*
     [
       [ 0, 1, 2 ],
       [ 7, 8, 3 ],
       [ 6, 5, 4 ]
     ]
           ||
     [
       [ (0,0), (0,1), (0,2) ],
       [ (1,0), (1,1), (1,2) ],
       [ (2,0), (2,1), (2,2) ],
     ]
           ||
     [
       [ (0,0), (0,1), (0,2) ],  A. scan X (0 ~ n-1 , 0      )
       [      ,      , (1,2) ],  B. scan Y (n-1     , 1 ~ n-1)
       [      ,      , (2,2) ],
       [ (2,0), (2,1),       ],  C. scan X (n-1 ~ 0 , n-1    )
       [ (1,0),      ,       ],  D. scan Y (0       , n-1 ~ 1)
                        ~~~~  繞完一圈  ~~~~
       [      , (1,1),       ],  scan X (1 ~ n-2 , n-2    )
     ]

 */
    public static void main(String[] args) {
        int[][] input = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        List<Integer> ret;
        ret = spiralOrder(input);
        System.out.println("ANS: ret="+ret);
    }

    static public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }

        int xStart = 0;
        int xEnd = matrix[0].length - 1;
        int yStart = 0;
        int yEnd = matrix.length - 1;

        while (xStart <= xEnd && yStart <= yEnd ) {
            System.out.println(res);
            // A. scan X (0 ~ n-1 , 0      )  方向往右
            for (int x = xStart; x <= xEnd; x++) {
                res.add(matrix[yStart][x]);
            }

            // B. scan Y (n-1     , 1 ~ n-1)  方向往下
            yStart++; // 從下一列開始
            for (int y = yStart; y <= yEnd; y++) {
                res.add(matrix[y][xEnd]);
            }
            // C. scan X (n-1 ~ 0 , n-1    )  方向往左
            xEnd--; //
            if (yStart <= yEnd) { // KEY: 若是{1,2,3,4}數字方陣, 則掃到
                for (int x = xEnd; x >= xStart; x--) {
                    res.add(matrix[yEnd][x]);
                }
            }
            // D. scan Y (0       , n-1 ~ 1)  方向往上
            yEnd--; //
            if (xStart <= xEnd) { // KEY: 若數字方陣是{1,2,3,4}時, 則掃到(x=0,y=1)時,無法再往上掃
                for (int y = yEnd; y >= yStart; y--) {
                    res.add(matrix[y][xStart]);
                }
            }
            // 繞ABCD四邊, 掃完一圈, xStart往右移1
            xStart++;
        }

        return res;
    }
}
