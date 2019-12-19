package com.cspirat;

import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SpiralMatrixII
 * Creator : Edward
 * Date : Sep, 2017
 * Description : 59. Spiral Matrix II
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
// Q: SpiralMatrix VS SpiralMatrixII 差異:
// SpiralMatrix =  List<Integer> spiralOrder(int[][] matrix)
// SpiralMatrix2=        int[][] generateMatrix(int n)
// Tips: 正方形
public class SpiralMatrixII {

    /**
     *Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

     For example,
     Given n = 3,

     You should return the following matrix:
     [
     [ 1, 2, 3 ],
     [ 8, 9, 4 ],
     [ 7, 6, 5 ]
     ]

     time : O(n)     n : 总元素个数
     space : O(n)

     * @param n
     * @return
     */
    public static void main(String[] args) {
        int n = 3;
        int[][] ret = generateMatrix(n);
    }
    // Algorithm is same as SpiralMatrix
    static public int[][] generateMatrix(int length) {

        int[][] matrix = new int[length][length];
        int yStart = 0;
        int yEnd = length - 1;
        int xStart = 0;
        int xEnd = length - 1;
        int num = 1;

        while (xStart <= xEnd && yStart <= yEnd) {
            for (int i = xStart; i <= xEnd  ; i++)  // L to R
                matrix[yStart][i] = num++;

            yStart++;
            for (int i = yStart; i <= yEnd  ; i++)  // T to D
                matrix[i][xEnd] = num++;

            xEnd--;
            for (int i =   xEnd; i >= xStart; i--)  // R to L
                matrix[yEnd][i] = num++;

            yEnd--;
            for (int i =   yEnd; i >= yStart; i--)  // D to T
                matrix[i][xStart] = num++;

            xStart++;
        }
        return matrix;
    }
}
