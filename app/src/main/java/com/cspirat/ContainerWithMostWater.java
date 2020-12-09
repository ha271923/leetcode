package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ContainerWithMostWater
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 11. Container With Most Water
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] in = {1,8,6,2,5,4,8,3,7};
        System.out.println("maxArea()="+maxArea(in));
    }
    /**
     * time : O(n)
     * space : O(1)
     * @param height
     * @return
     */
    // Tips1: Animation at https://leetcode.com/problems/container-with-most-water/solution/
    //        https://leetcode.windliang.cc/leetCode-11-Container-With-Most-Water.html
    // Tips2: 長邊不動, 短邊動
    static public int maxArea(int[] height) {
        int res = 0;
        int L = 0, R = height.length - 1; // L,R 為height[]的左右 X軸index
        while (L < R) { // 掃描時 左邊index不得超過(或重疊)右邊index, 這樣可以形成一個container
            int lowestSide = Math.min(height[L], height[R]); // 高 = 左邊y軸或是右邊y軸, 最低的那一個高度是y軸
            int area =  lowestSide * (R - L); // 寬 = ( 右邊x軸 - 左邊x軸 ), 面積=x*y
            res = Math.max(res, area); // 破紀錄?
            if (height[L] < height[R]) { // KEY: 長邊不動, 短邊動
                L++;
            } else
                R--;
        }
        return res;
    }

    static public int maxArea_hawk(int[] height) {
        int L = 0;
        int R = height.length-1;
        int area = 0, res = 0;
        while(L < R) {
            area = height[L]*height[R];  // ERROR: a stupid fail 疏忽疏忽疏忽!
            res = Math.max(res, area);
            if(height[L] > height[R])
                R--;
            else
                L++;
        }
    }
}
