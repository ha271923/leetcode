package com.cspirat;

import java.util.Arrays;
import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaximalRectangle
 * Creator : Edward
 * Date : Dec, 2017
 * Description : 85. Maximal Rectangle
 */
public class MaximalRectangle {
    /**
     * Given a 2D binary matrix filled with 0's and 1's,
     * find the largest rectangle containing only 1's and return its area.

     For example, given the following matrix:

     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
     Return 6.


     left[] ：从左到右，出现连续‘1’的string的第一个座标
     right[] ：从右到左, 出现连续‘1’的string的最后一个座标，
     height[] ： 从上到下的高度。
     res ： (right[j] - left[j]) * heights[j]
     
     height:
     1 0 1 0 0
     2 0 2 1 1
     3 1 3 2 2
     4 0 0 3 0

     left:
     0 0 2 0 0
     0 0 2 2 2
     0 0 2 2 2
     0 0 0 3 0

     right:
     1 5 3 5 5
     1 5 3 5 5
     1 5 3 5 5
     1 5 5 4 5

     time : O(m * n)
     space : O(n)

     * @param matrix
     * @return
     */
    public static void main(String[] args) {
        char[][] input = {
                   {'1','0','1','0','0'},
                   {'1','0','1','1','1'},
                   {'1','1','1','1','1'},
                   {'1','0','0','1','0'}
                 };

        // int ret = maximalRectangle(input);
        int ret = maximalRectangle_mix(input);
        System.out.println(ret);
    }
/*
         input = {
                   {'1','0','1','0','0'},
                   {'1','0','1','1','1'},
                   {'1','1','1','1','1'},
                   {'1','0','0','1','0'}
                 };
    針對每一列ROW, 掃描
 */
    // https://www.youtube.com/watch?v=2Yk3Avrzauk
    static public int maximalRectangle_mix(char[][] matrix) {
        int H = matrix.length;
        if (matrix == null || H == 0)
            return 0;
        int W = matrix[0].length;
        int area = 0;
        int[] heights = new int[W]; // 在COLUMN=x時, 某期間連續累積的柱高
        for (int y = 0; y < H; y++) { // scan each Row
            for (int x = 0; x < W; x++) { // scan heights of COLUMN
                if(matrix[y][x] == '1')
                    heights[x]++;
                else
                    heights[x] = 0;
            }
            area = Math.max(area ,
                    LargestRectangleinHistogram.largestRectangleArea_stack(heights)
            );
        }
        return area;
    }


    static public int maximalRectangle(char[][] matrix) {
        int H = matrix.length;
        if (matrix == null || H == 0)
            return 0;
        int W = matrix[0].length;
        int area = 0;
        int[] heights = new int[W]; // 在COLUMN=x時, 某期間連續累積的柱高
        int[]    left = new int[W]; // 某ROW, 由左往右掃, 最右邊(min)1當作左邊界
        int[]   right = new int[W]; // 某ROW, 由右往左掃, 最左邊(max)1當作右邊界
        Arrays.fill(right, W);

        for (int y = 0; y < H; y++) { // scan each Row
            int curLeft = 0, curRight = W;

            // find max width of ROW
            for (int x = 0; x < W; x++) { // scan heights of COLUMN
                if (matrix[y][x] == '1')
                    heights[x]++; // y = 0 ~ H , 一直累加height[x]
                else
                    heights[x] = 0; // KEY: 遇到一次'0', 該行所有累加height歸0
            }
            for (int x = 0; x < W; x++) { // find left[x] of ROW
                if (matrix[y][x] == '1') {
                    left[x] = Math.max(curLeft, left[x]);  // max  ???
                } else {
                    left[x] = 0;
                    curLeft = x + 1;
                }
            }
            for (int x = W - 1; x >= 0; x--) { // find right[x] of ROW
                if (matrix[y][x] == '1') {
                    right[x] = Math.min(curRight, right[x]);  // min  ???
                } else {
                    right[x] = W;
                    curRight = x;
                }
            }
            for (int x = 0; x < W; x++) { // calc & compare area
                area = Math.max(area, (right[x] - left[x]) * heights[x]);
            }
        }
        return area;
    }

    /**
     time : O(m * n)
     space : O(n)

     * @param matrix
     * @return
     */

    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix[0].length;
        int[] height = new int[n + 1];
        height[n] = 0;
        int res = 0;

        for (int row = 0; row < matrix.length; row++) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n + 1; i++) {
                if (i < n) {
                    if (matrix[row][i] == '1') {
                        height[i]++;
                    } else height[i] = 0;
                }
                if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                    stack.push(i);
                } else {
                    while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
                        int cur = height[stack.pop()] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                        res = Math.max(res, cur);
                    }
                    stack.push(i);
                }
            }
        }
        return res;
    }
}
