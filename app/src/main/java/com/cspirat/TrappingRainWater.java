package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : TrappingRainWater
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 42. Trapping Rain Water
 */
public class TrappingRainWater {
    /**
     *
     time : O(n)
     space : O(1)

     0,1,0,2,1,0,1,3,2,1,2,1

                   |
           | Y2Y3Y4| | Y5|
       | Y1| | Y3| | | | | |
     0 1 2 3 4 5 6 7 8 9 0 1
                 L R
     Ans:  TrapA = 1+1+2+1+1= 6
     */
    // Tips: 把積水想成一根一根的柱子, 全部積水就是每根柱子的高度和
    static public int trap(int[] height) {
        int L = 0;
        int LMax = 0;
        int R = height.length - 1;
        int RMax = 0;
        int res = 0;
        while (L < R) { // 左邊index不超過右邊index, 這樣可以形成一個container
            // KEY: x軸永遠為1, TrapA=A1+A2+...Ax的總和, 而Ax=Yx*1, 因此只要算出Yx即可,
            //      Yx=Ymax-Ycurrent, 另外Ymax需分Lmax與Rmax兩種, 因為積水是取決較低的那一側
            if (height[L] < height[R]) { // 高 = 左邊y軸或是右邊y軸, 最低的那一個高度是y軸
                // L側比較低
                LMax = Math.max(height[L], LMax);
                res += LMax - height[L]; // 每次算出1柱高度
                L++;
            } else {
                // R側比較低
                RMax = Math.max(height[R], RMax);
                res += RMax - height[R];
                R--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
           int[] input =     {0,1,0,2,1,0,1,3,2,1,2,1};   // Ans: 6
        // int[] input = {100,0,1,0,2,1,0,1,3,2,1,2,1};   // Ans: 17
        // int[] input = {100,0,1,0,2,1,0,1,3,2,1,2,100}; // Ans: 1186
        int ret = trap(input);
        System.out.println(ret);
    }
}
