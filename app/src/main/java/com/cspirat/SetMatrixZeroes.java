package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SetMatrixZeroes
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 73. Set Matrix Zeroes
 */
public class SetMatrixZeroes {

    public static void main(String[] args) {
        int[][] input =
        {
          {1, 0, 1, 1},
          {1, 1, 0, 0},
          {0, 1, 1, 1},
          {1, 1, 1, 1}
        };
        setZeroes(input);
        System.out.println("input=" + input);
    }


    /**

     [
      [1, 0, 1, 1],
      [1, 1, 0, 0],
      [0, 1, 1, 1],
      [1, 1, 1, 1]
     ]

     time : O(n * m)
     space : O(1)

     * @param matrix
     */
    // Tips: 兩種0, 一種是題目陣列中的0, 另一種是自己寫入的0
    // 困難之處字在於區分出兩者
    // matrix的最上一列[0][x]與最左一直行[y][0], 當作FLAG bits使用
    static public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;
        int yLen = matrix.length;
        int xLen = matrix[0].length;
        boolean row = false;
        boolean col = false;
        //  掃描 x,y
        for (int y = 0; y < yLen; y++) { // LOOP: 掃描 y
            for (int x = 0; x < xLen; x++) { // LOOP: 掃描 x
                if (matrix[y][x] == 0) { // 當掃到該位置有 0 時
                    matrix[0][x] = 0;    // 該位置的最上方一點, 設為0 FLAG, 代表整行須設為0
                    matrix[y][0] = 0;     // 與該位置的最左方一點, 設為0 FLAG, 代表整列須設為0
                    // 那FLAG本身呢?
                    if (y == 0) // y[0]是FLAG列, 本身有0, 所以最後處理完時,要把此列設成全0
                      row = true;
                    if (x == 0)
                      col = true;
                }
            }
        }
        // 根據FLAG設matrix值
        for (int y = 1; y < yLen; y++) {
            if (matrix[y][0] == 0) { // 掃FLAG, 如果最左方一行有0
                for (int x = 1; x < xLen; x++) {
                    matrix[y][x] = 0;
                }
            }
        }
        for (int x = 1; x < xLen; x++) {
            if (matrix[0][x] == 0) { // 掃FLAG, 如果最上方一列有0
                for (int y = 1; y < yLen; y++) {
                    matrix[y][x] = 0;
                }
            }
        }
        // FLAG自身的值
        if (row) { // 整列設為0
            for (int x = 0; x < xLen; x++) {
                matrix[0][x] = 0;
            }
        }
        if (col) { // 整行設為0
            for (int y = 0; y < yLen; y++) {
                matrix[y][0] = 0;
            }
        }
    }
}
