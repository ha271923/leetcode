package com.cspirat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ThreeSum
 * Creator : Edward
 * Date : Aug, 2017
 * Description : TODO
 */
public class ThreeSum {
    /**
     * 15. 3Sum
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.

     Note: The solution set must not contain duplicate triplets.

     For example, given array S = [-1, 0, 1, 2, -1, -4],

     A solution set is:
     [
     [-1, 0, 1],
     [-1, -1, 2]
     ]

     time : O(n^2);
     space : O(n);
     * @param nums
     * @return
     */
    /**
     * https://www.cnblogs.com/grandyang/p/4481576.html
     * 这道题让我们求三数之和，比之前那道Two Sum要复杂一些，
     * A1: 博主考虑过先fix一个数，然后另外两个数使用Two Sum那种HashMap的解法，
     * 但是会有重复结果出现，就算使用set来去除重复也不行，会TLE(Time Limit Exceed: 超過執行時間)，看来此题并不是考我们Two Sum的解法。
     *
     * 那么我们来分析一下这道题的特点，
     * 要我们找出三个数且和为0，那么除了三个数全是0的情况之外，肯定会有负数和正数，我们还是要先fix一个数，然后去找另外两个数，
     * 我们只要找到两个数且和为第一个fix数的相反数就行了，既然另外两个数不能使用Two Sum的那种解法来找，如果能更有效的定位呢？
     * 我们肯定不希望遍历所有两个数的组合吧，所以如果[数组是有序]的，那么我们就可以用双指针以线性时间复杂度来遍历所有满足题意的两
     * 个数组合。
     *
     * A2: 我们对原数组进行排序，然后开始遍历排序后的数组，这里注意不是遍历到最后一个停止，而是到倒数第三个 (nums.length-2) 就可以了。
     * 这里我们可以先做个剪枝优化，就是当遍历到正数的时候就break，为啥呢，因为我们的数组现在是有序的了，
     * 因為数组进行排序過，如果第一个要fix的数就是正数了，那么后面的数字就都是正数，就永远不会出现和为0的情况了。
     * 然后我们还要加上重复就跳过的处理，处理方法是从第二个数开始，如果和前面的数字相等，就跳过，因为我们不想把相同的数字fix两次。
     * 对于遍历到的数，用0减去这个fix的数得到一个target，然后只需要再之后找到两个数之和等于target即可。
     * 我们用两个指针分别指向fix数字之后开始的数组首尾两个数，如果两个数和正好为target，则将这两个数和fix的数一起存入结果中。
     * 然后就是跳过重复数字的步骤了，两个指针都需要检测重复数字。如果两数之和小于target，则我们将左边那个指针i右移一位，使得指向的数字增大一些。
     * 同理，如果两数之和大于target，则我们将右边那个指针j左移一位，使得指向的数字减小一些，
     *
    * */

    // Tips1: A+B+C=0  -->  A+B=-C
    // Tips2: 用TwoSum會有重複答案
    // Tips3: 數組劃分成左右兩半, 左半由low指標管理, 右半由high指標管理, 所以(low < high）左右逼近必須成立
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 重新排序過的數, 方便搜尋,  O(n log(n)),  sorted nums[]=[-1, 0, 1, 2, -1, -4] =>  [-4, -1, -1, 0, 1, 2]
        for (int i = 0; i < nums.length - 2; i++) { // 每次拿出3個數字的數組迴圈, 次數上應該要做(nums.length-2)
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 第二次回圈開始, 假使上次取出的數組中的元素A與此次的元素B一樣的話, 因為結果數組不想要重複, 所以不管是否是正確或是錯誤數組, 不用往下運算
            int low = i + 1, high = nums.length - 1, sum = 0 - nums[i]; // ex: sorted nums[]=[-4, -1, -1, 0, 1, 2] , idx={low=1,high=5}, 每次我們取出數組內的3個數, 第一個數存到sum=0-(-4)=4 將初始值被0減去,可以創造出A+B=-C的公式, 然後尋找剩下的兩個數num[low],num[high]
            while (low < high) { //　 idx={low-->往左逼近 往右逼近<--high} , 各自管理各自的數組範圍
                if (nums[low] + nums[high] == sum) { // 找到了
                    res.add(Arrays.asList(nums[i], nums[low], nums[high])); // 將結果存入
                    while (low < high && nums[low] == nums[low + 1]) // 遇到重複數字，避免結果是重複數組，不斷跳過重複的數，找下一個左半部不重複數
                        low++;
                    while (low < high && nums[high] == nums[high - 1]) // 遇到重複數字，避免結果是重複數組，不斷跳過重複的數，找下一個右半部不重複數
                        high--;
                    low++;// 沒有重複數，所以換下一個數來做
                    high--;//  Q:??? 為什麼不將之一low++或high--就好, 而將兩者都++&--,
                } else if (nums[low] + nums[high] < sum) {
                    low++; // 需要找更大的數
                } else high--; // 需要找更小的數
            }
        }
        return res;
    }
}
