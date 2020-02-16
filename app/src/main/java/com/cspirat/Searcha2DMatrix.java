package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : Searcha2DMatrix
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 74. Search a 2D Matrix
 */
public class Searcha2DMatrix {
    public static void main(String[] args) {
        int[][] input =
        {
          {1,   3,  5,  7},
          {10, 11, 16, 20},
          {23, 30, 34, 50}
        };
        int target = 3;
        boolean ret = searchMatrix(input, target);
        System.out.println("ret=" + ret);
    }
    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix.
     * This matrix has the following properties:

     Integers in each row are sorted from left to right.
     The first integer of each row is greater than the last integer of the previous row.
     For example,

     Consider the following matrix:

     [
     [1,   3,  5,  7],
     [10, 11, 16, 20],
     [23, 30, 34, 50]
     ]
     Given target = 3, return true.

     end = 11 mid = 5 col = 4

     time : O(log(n * m));
     space : O(1)

     * @param matrix
     * @param target
     * @return
     */
    static public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int beginIdx = 0;
        int endIdx = row * col - 1; //  將2D陣列轉換成 1D陣列,最後一個元素idx

        // 用1D搜尋
        while (beginIdx <= endIdx) {
            int midIdx = (endIdx - beginIdx) / 2 + beginIdx;
            /*
             *              2D array                  ==>                1D array
             *     x0     x1     x2     x3    col=4
             * y0|  1beg|  3   |  5   |  7   |              b             m                  e
             * y1| 10   | 11mid| 16   | 20   |        ==> [ 1, 3, 5, 7,10,11,16,20,23,30,34,50]
             * y2| 23   | 30   | 34   | 50end|            |   col=4   |   col=4   |   col=4   | 數組可切三段
             * raw=3
             * midIdx=(11-0)/2+0=5
             */
            System.out.println("midIdx="+ midIdx+"  col="+col);
            int midVal = matrix[midIdx / col][midIdx % col]; // KEY: 1D轉2D取中間值值 algorithm
            // 比對target
            // begin ---------- midVal -------------- end
            if (midVal == target) {
                return true;
            } else if (midVal < target) { // 中值 小於 目標值, 搜尋區段為中值以右 區間
                beginIdx = midIdx + 1;
            } else {  // 中值 大於 目標值, 搜尋區段為中值以左 區間
                endIdx = midIdx - 1;
            }
        }
        return false;
    }
}
