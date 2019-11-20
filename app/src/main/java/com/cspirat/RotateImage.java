package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RotateImage
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 48. Rotate Image
 */
public class RotateImage {
    /**
     * Example 1:

     Given input matrix =
     [
       [1,2,3],
       [4,5,6],
       [7,8,9]
     ],

     rotate the input matrix in-place such that it becomes:
     90 degree * 1
     [
       [7,4,1],
       [8,5,2],
       [9,6,3]
     ]

     90 degree * 2
     [
       [1,4,7],
       [2,5,8],
       [3,6,9]
     ]

     time : O(n * m)
     space : O(1)
     * @param matrix
     */
    // Ans: 僅正方形圖適用
    // [ A. Input Data                                         Target
    //   [  1  ,  2  ,  3  ],    [  1  ,  4  ,  7  ],    [  7  ,  4  ,  1  ],
    //   [  4  ,  5  ,  6  ], -> [  2  ,  5  ,  8  ], -> [  8  ,  5  ,  2  ],
    //   [  7  ,  8  ,  9  ]     [  3  ,  6  ,  9  ]     [  9  ,  6  ,  3  ]
    // ]

    // [ B. Position
    //   [(0,0),(0,1),(0,2)],    [(0,0),(1,0),(2,0)],    [(0,2),(0,1),(0,0)],
    //   [(1,0),(1,1),(1,2)], -> [(0,1),(1,1),(2,1)], -> [(1,2),(1,1),(1,0)],
    //   [(2,0),(2,1),(2,2)]     [(0,2),(1,2),(2,2)]     [(2,2),(2,1),(2,0)]
    // ]

    // [ C. Algorithm                  xyLOOP1                xyLOOP2
    //   [(0,0),(0,1),(0,2)],    [(0,0),swap1,swap2],    [swap1,(0,1),swap1],
    //   [(1,0),(1,1),(1,2)], -> [swap1,(1,1),swap3], -> [swap2,(1,1),swap2],
    //   [(2,0),(2,1),(2,2)]     [swap2,swap3,(2,2)]     [swap3,(2,1),swap3]
    // ]

    public static void main(String[] args) {
        int[][] data = {
          {1,2,3},
          {4,5,6},
          {7,8,9}
        };
        rotate_4loop(data);
        // rotate_2loop(data);
    }
    // Tips: 拿一張紙張, 先對角線為軸翻轉一次, 再中軸線為軸左右翻轉, 即可轉90度
    public static void rotate_4loop(int[][] matrix) {
        int n = matrix.length; // 僅正方形圖適用
        // xyLOOP1: 對角線為軸翻轉
        for (int y = 0; y < n; y++) {
            for (int x = y; x < n; x++) { // KEY: x=y 對角
                int temp = matrix[y][x];
                matrix[y][x] = matrix[x][y];
                matrix[x][y] = temp;
            }
        }
        printMatrix(matrix);
        System.out.println("----------------------");
        // xyLOOP2: 中軸線為軸左右翻轉
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < (n/2); x++) { // KEY: x<(n/2) 中軸線
                int temp = matrix[y][x];
                matrix[y][x] = matrix[y][n - 1 - x];
                matrix[y][n - 1 - x] = temp;
            }
        }
        printMatrix(matrix);
    }
    // debug
    private static void printMatrix(int[][] matrix){
        int n = matrix.length;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                System.out.print(matrix[y][x]+" , ");
            }
            System.out.println("");
        }
    }

    // 直接座標互換
    // https://blog.csdn.net/happyaaaaaaaaaaa/article/details/51563752
    static public void rotate_2loop(int[][] matrix) {
        int n = matrix.length;
        int end = n-1;
        for (int y = 0; y < n / 2; ++y) {
            for (int x = y; x < end - y; ++x) {
                int tmp = matrix[y][x];
                matrix[y][x] = matrix[end - x][y];
                matrix[end - x][y] = matrix[end - y][end - x];
                matrix[end - y][end - x] = matrix[x][end - y];
                matrix[x][end - y] = tmp;
            }
        }
    }
}
