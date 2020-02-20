package com.cspirat;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SortColors
 * Creator : Edward
 * Date : Dec, 2017
 * Description : 75. Sort Colors
 */
public class SortColors {
    public static void main(String[] args) {
        int[] input = {2,0,2,1,1,0};
         sortColors(input);
        System.out.println("input=" + input); // Ans: [0,0,1,1,2,2]
    }

    /**
     * Given an array with n objects colored red, white or blue,
     * sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

     Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

     time : O(n)
     space : O(1)

     * @param nums
     */

    private static final int RED = 0;
    private static final int WHITE = 1;
    private static final int BLUE = 2;

    /*
   Ans1:Hawk突發奇想,直接計數數字陣列,每個數字出現的次數,並填回去複寫 ==>失敗,因為題目要one-pass algorithm
   Ans2:Hawk突發奇想,直接sort題目的數字陣列 ==>失敗,因為題目要one-pass algorithm
     Note: one-pass algorithm代表一個迴圈搞定, 所以需要思考合併迴圈的解法

   Ans:我們將數字一一挑出來, 並塞入適合的區段, 過程中需好好控制P1與P3的區段範圍
     若有[2,0,1]三個數字要排序, 只要把最小的0排左邊,最大的2排右邊, 自然可排好次序
     現在題目Q將三個數字變成三個partition, 形成下圖區段
      0～P1.end         P3.start～end
      |   P1  |   P2   |      P3    |


        |=> i++
      redEnd++    blueStart--
        |              |
        V              V
       [0][1][2][3][4][5]
  nums=[2, 0, 2, 1, 1, 0]  nums[0]=2  redEnd=0  blueStart=5
   swap(0,             5)
  nums=[0, 0, 2, 1, 1, 2]  nums[0]=0  redEnd=0  blueStart=4
  nums=[0, 0, 2, 1, 1, 2]  nums[1]=0  redEnd=1  blueStart=4
  nums=[0, 0, 2, 1, 1, 2]  nums[2]=2  redEnd=2  blueStart=4
   swap(      2,    4)
  nums=[0, 0, 1, 1, 2, 2]  nums[2]=1  redEnd=2  blueStart=3
     i++
  nums=[0, 0, 1, 1, 2, 2]  nums[3]=1  redEnd=2  blueStart=3
     i++

     */
    static public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int redEnd = 0;
        int blueStart = nums.length - 1;
        int i = 0;
        // KEY: i不是每次回圈就遞增, i++, redEnd++, blueStart--是後置運算
        while (i <= blueStart) { // KEY: 並非每一回圈都會進行 i++
            System.out.println("nums="+ Arrays.toString(nums)+"  nums["+i+"]="+nums[i]+"  redEnd="+redEnd+"  blueStart="+blueStart);
            if (nums[i] == RED) {
                swap(nums, i++, redEnd++); // append [i] to redEnd
            } else if (nums[i] == BLUE) {
                swap(nums, i  , blueStart--); // KEY: 沒有i++, append [i] to blueStart, and keep same i for checking if swap again for RED.
            } else { // WHITE
                System.out.println("i++");
                i++;
            }
        }
    }

    static public void swap(int[] nums, int i, int j) {
        if(i==j)
            return;
        System.out.println("swap("+i+","+j+")");
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Two pointers. One-pass.
     * Similar to find minimum and second minimum.
     * Remember the count of red, and count of red + white.
     * Loop through the array.
     * For each color, we get its value, and overwrite it with blue.
     * Then check if it's red,
     * Note:
     * Why do we update WHITE first?
     * Since whiteEnd and redEnd can be at the same position.
     * what ought to be RED can be overrode by WHITE.
     */
    static public void sortColors1(int[] nums) {
        int redEnd = -1; // Ending index of red
        int whiteEnd = -1; // Ending index of white

        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            nums[i] = BLUE;
            if (v == RED) {
                nums[++whiteEnd] = WHITE; // Update white first
                nums[++redEnd] = RED; // If there is no white yet, will overwrite white.
            } else if (v == WHITE) {
                nums[++whiteEnd] = WHITE;
            }
        }
    }

    /**
     * 3-way Partitioning. Two pointers. One-pass.
     * One pointer redEnd for the end of red.
     * The other blueStart for the start of blue from the end.
     * If its blue, swap with the end.
     * If its red, swap with the start.
     * All whites will remain in the middle.
     * <p>
     * Implementation:
     * redEnd = 0, blueStart = n-1
     * For each i from 0 to blueStart:
     * | While nums[i] is blue and i < blueStart:
     * |   Swap i with blueStart.
     * |   Update blueStart to blueStart - 1.
     * | While nums[i] is red and i > redEnd:
     * |   Swap i with redEnd.
     * |   Update redEnd to redEnd + 1.
     * Note:
     * Why swap BLUE first not RED? If swap RED first what would happen?
     * If we swap RED first, when we swap with BLUE, the element resulting at i can be 0, 1, 2, anything.
     * We must take care of 0's further.
     * If we swap with BLUE first, 0's are taken care of when we swap with RED later.
     * The elements before i are already in order, so they are either 0 or 1. There won't be a 2 to further swap.
     */
    static public void sortColors2(int nums[]) {
        int redEnd = 0, blueStart = nums.length - 1;
        for (int i = 0; i <= blueStart; i++) {  //Note: one-pass algorithm代表一個迴圈搞定, 所以需要思考合併迴圈的解法
            while (nums[i] == BLUE && i < blueStart) { // Move all BLUEs to the end.
                swap(nums, i, blueStart--);
            }
            while (nums[i] == RED && i > redEnd) { // Move all REDs to the front.
                swap(nums, i, redEnd++);
            }
        }
    }

    /**
     * 3-way partitioning. Standard.
     * Move red to the front, blue to the end. Keep white in the middle.
     * One pointer to the end of red, redEnd, starting from 0.
     * Another pointer to the start of blue, blueStart, starting from n-1, go backwards.
     * For i = 0 to blueStart:
     * | If nums[i] is RED:
     * |   Swap nums[i] with nums[redEnd].
     * |   Move redEnd.
     * |   Can move i too because the color swapped to i won't be red or blue.
     * | If nums[i] is BLUE:
     * |   Swap nums[i] with nums[blueStart].
     * |   Move blueStart.
     * |   No need to move i since the color swapped to i can be blue or red.
     * | If nums[i] is WHITE:
     * |   i++, just skip.
     */
    static public void sortColors3(int[] nums) {
        int redEnd = 0;
        int blueStart = nums.length - 1;
        int i = 0;
        while (i <= blueStart) { // i stops at blueStart, no blueStart - 1.
            if (nums[i] == RED) {
                swap(nums, i++, redEnd++);
            } else if (nums[i] == BLUE) {
                swap(nums, i, blueStart--);
            } else {
                i++;
            }
        }
    }

    /**
     *  Note: one-pass algorithm代表一個迴圈搞定, 所以需要思考合併迴圈的解法
     *
     * Counting sort. Two-pass.
     * First iterate through the array to find each color's count.
     * Then iterate again and write colors to array.
     */
    static public void sortColors4(int[] nums) {
        int red = 0;
        int white = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == RED) {
                red++;
            } else if (nums[i] == WHITE) {
                white++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < red) {
                nums[i] = RED;
            } else if (i < red + white) {
                nums[i] = WHITE;
            } else {
                nums[i] = BLUE;
            }
        }
    }
}
