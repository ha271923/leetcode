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
        int l = 0, r = height.length - 1; // l,r 為height[]的左右index
        while (l < r) { // 左邊index不超過右邊index, 這樣可以形成一個container
            int lowestSide = Math.min(height[l], height[r]); // 高 = 左邊y軸或是右邊y軸, 最低的那一個高度是y軸
            int area =  lowestSide * (r - l); // 寬 = ( 右邊x軸 - 左邊x軸 ), 面積=x*y
            res = Math.max(res, area); // 破紀錄?
            if (height[l] < height[r]) { // KEY: 長邊不動, 短邊動
                l++;
            } else
                r--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] in = {1,8,6,2,5,4,8,3,7};
        System.out.println("maxArea()="+maxArea(in));
    }
}
