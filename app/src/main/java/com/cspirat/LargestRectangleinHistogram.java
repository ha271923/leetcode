package com.cspirat;

import com.utils.Out;

import java.util.Stack;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LargestRectangleinHistogram
 * Creator : Edward
 * Date : Dec, 2017
 * Description : 84. Largest Rectangle in Histogram
 */
public class LargestRectangleinHistogram {
    /**
     * For example,
     Given heights = [2,1,5,6,2,3],
     return 10.

     stack : 1，升序，2，小于，计算
      0,1,2,3,4,5,6
     [2,1,5,6,2,3,0]

     stack : 1
     2 : push
     1 : height = 2 start = -1 res = 2
     5 : push
     6 : push
     2 : height = 6 start = 2 area = 6 res = 6
         height = 5 start = 1 area = 10 res = 10
     3 push
     0 : height = 2 start = 1 area = 8
         height = 1 start = -1 area = 6

     res = 10

     time : O(n)
     space : O(n)


     * @param heights
     * @return
     */
    // Tip1: maxArea: 面積一定要連續的區域, 也就是說一定是相鄰的數值段
    // Tip2: minHeightForRect: 規劃Rectangle面積, 需要捨棄多餘的多邊形區域
    // Tip3: Width=連續幾次長方條
    public static void main(String[] args) {
        int[] height = { 2, 1, 5, 6, 2, 3 };
        int[] height2 = { 1, 2, 3, 4, 5, 6 };

        int ret = largestRectangleArea_burst(height);
        System.out.println(ret);
    }
/*
                 _
               _| |
              |* *|
              |* *|  _
           _  |* *|_| |
          | |_|* *    |
        __|____*_*____|__
  nums[i]= 2 1 5 6 2 3
       i = 0 1 2 3 4 5
 */
    // https://youtu.be/KkJrGxuQtYo?list=PLH8TFsY0qnE2R9kf_9vahNY6pG9601z_4&t=481
    // 1. 由往右掃
    // 1-1. 以nums[i]當中柱
    // 1-2. 左半部比nums[i]小的當左邊界,leftMostIdx(用stack解法時,)
    // 1-3. 右半部比nums[i]小的當右邊界,rightMostIdx
    // 1-4. area=W*H=(rightMostIdx-leftMostIdx-1)*rangeLow=(4-1-1)*5=10
    static public int largestRectangleArea_stack(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // KEY: 為什麼用Stack?
        Stack<Integer> stack = new Stack<>(); // stack存的是i, 不是nums[i], 因為width=(rightMostIdx-leftMostIdx-1)

        int maxArea = 0;
        int i = 0; //

        while (i < nums.length) { // LOOP1: 每次以nums[i]為左柱時, 尋找其右柱idx, 或直接計算Area
            // 新的上升右柱讓Area一定會變更大, 不用馬上算面積, 放入stack, 待會看下一次的右柱
            if (stack.isEmpty() || nums[i] >= nums[stack.peek()]) { // 相同或是遞增:代表新的右柱一定讓Area會變大
                stack.push(i); // 放入stack, 之後給LOOP2計算
                i++;
            } else { // 遞減:代表新的下降右柱不一定讓Area會變大, 要算一下面積確認Area看有沒有破紀錄才知道:
                int p = stack.pop();
                int h = nums[p];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1; // KEY: width=(rightMostIdx-leftMostIdx-1)=(i-stack.peek()-1)
                maxArea = Math.max(h * w, maxArea);
            }
        }

        while (!stack.isEmpty()) { // LOOP2: 將所有stack的上升右柱一一取出, 並跟左柱計算Area
            int p = stack.pop();
            int h = nums[p];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1; // 確認Area看有沒有破紀錄
            maxArea = Math.max(h * w, maxArea);
        }

        return maxArea;
    }


    // https://dev.twsiyuan.com/2018/02/leetcode-largest-rectangle-in-histogram.html
    static public int largestRectangleArea_burst(int[] heights) {
        int maxArea = 0;
        int curArea = 0;

        // Tips:
        for (int i = 0; i < heights.length ; i++) { // LOOP1: 取一根基準柱heights[i], 每次往右換一根heights[i++]
            curArea = heights[i];
            Out.i("heights["+i+"]="+heights[i]);
            for (int L = i-1; L >= 0; L--) {  // LOOP1_1: 從基準柱heights[i]往左擴展
                if( heights[L] >= heights[i]) { // 升階,新的左柱讓Area一定會變更大,
                    curArea += heights[i];
                }else{
                    Out.i("L break");
                    break; // leave this for-loop
                }
            }

            for (int R = i+1; R < heights.length ; R++) {  // LOOP1_2: 從基準柱heights[i]往右擴展
                if (heights[R] >= heights[i]) { // 升階,新的右柱讓Area一定會變更大, 算面積
                    curArea += heights[i];
                }else{
                    Out.i("R break");
                    break; // leave this for-loop
                }
            }

            if (curArea > maxArea){ // 有沒有破紀錄
                maxArea = curArea;
            }
        }

        return maxArea;
    }

    static public int largestRectangleArea2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        Stack<Integer> stack = new Stack<>(); // stack存的是i, 不是nums[i]
        int res = 0;
        for (int i = 0; i <= nums.length; i++) { // LOOP1: 從左至右掃數值群
            int curNum = (i == nums.length)? 0 : nums[i];
            // peek(): Get the top of this stack without removing it from the stack.
            while (!stack.isEmpty() && curNum < nums[stack.peek()]) { // KEY: 因為是從左至右掃數值群, 只有遞增curNum<prevNum才可能比上一次面積還大
                int height = nums[stack.pop()];
                int start = (stack.isEmpty())? -1 : stack.peek(); // ???
                int area = height * (i - start - 1); // width = (i - start - 1)
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }
}
