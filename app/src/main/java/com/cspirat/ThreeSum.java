package com.cspirat;

import com.utils.Out;

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
    public static void main(String[] args) {
        int[] input = {-1, 0, 1, 2, -1, -4};
        System.out.println("threeSum ");
        List<List<Integer>> res = threeSum(input);
        for( List<Integer> value: res) {
            System.out.println("value = " + value);
        }
    }

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
    // Tips3: 數組劃分成左右兩半, 左半由L指標管理, 右半由R指標管理, 所以(L < R）左右逼近必須成立
    // Reference: https://zhuanlan.zhihu.com/p/53066205
    static public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 重新排序過的數, 創造 R >= L 的規律, 方便搜尋,  O(n log(n)),  sorted nums[]=[-1, 0, 1, 2, -1, -4] =>  [-4, -1, -1, 0, 1, 2]
        // 小測試 { 1, 2, 3, 4} 要做幾次? 先用一個簡單的數組找出規律
        // 1=2+3 N!, 1=2+4 N!
        // 2=1+3 N!, 2=1+4 N!
        // 3=1+2 Y!, 3=1+4 N!
        // 4=1+2 N!, 4=1+3 Y!
        // 規律: C=A+B, C會遍歷每一數字, A不會遍歷每一數字, B亦然
     // for (int i = 0; i < nums.length    ; i++) { // 沒-2輸出依然正確
        for (int i = 0; i < nums.length - 2; i++) { // LOOP1: 每次剔除一個最前面數字(確保所取數組一定有一個數字不同), 再拿出3個數字的數組迴圈, 次數上應該要做(nums.length-2)
            // 快速過濾: 以獲得非重複數組
            if (i > 0 && nums[i] == nums[i - 1]) // Filter: 過濾連續重複數字, 跳過
                continue;                        // 所以不管是否是正確或是錯誤數組, 不用往下運算, 故直接剔除一個最前面數字
            //    sum[i] = C會遍歷每一數字,
            //           ++L         R--
            //    i | ----->         <--------
            //sum[i]= nums[L] + nums[R]
            // A+B=-C, C必需存在於nums[]數值陣列之中,本題才有解, 要獲得-C, 只要把數值陣列中的值乘以(-1)或是以0減去nums[]即可獲得
            int L = i + 1; // KEY: 為何不用從L=0開始?
            int R = nums.length - 1;
            int sum = 0 - nums[i]; // A+B=C, C = sum = -num[i], ex: sorted nums[]=[-4, -1, -1, 0, 1, 2] , idx={L=1,R=5},
                                   // 每次我們取出數組內的3個數, 第一個數存到sum=0-(-4)=4 將初始值被0減去,可以創造出A+B=-C的公式, 然後尋找剩下的兩個數num[L],num[R]
            Out.i("i="+i+"   L="+L+"    R="+R+"    sum="+sum);
            while (L < R) { //　LOOP2: 固定sum情況下! 用L,R掃描述組, idx={L-->往左逼近 往右逼近<--R} , 各自管理各自的數組範圍

                if (nums[L] + nums[R] == sum) // KEY: 找到了! A+B=C
                {
                    Out.i("nums["+i+"]=nums["+L+"]+nums["+R+"]  ==> "+nums[i]+"="+nums[L]+"+"+nums[R]+"  ==> "+nums[i]+"+"+nums[L]+"+"+nums[R]+"=0");
                    res.add(Arrays.asList(nums[i], nums[L], nums[R])); // 將結果存入, 若遇到重複數值需filter
                    // Question: Notice that the solution set must not contain duplicate triplets.
                    while (L < R && nums[L] == nums[L + 1]) // Filter: 遇到重複數字，避免結果是重複數組，跳過
                        L++; // 不用擔心L++會OutOfBoundary, 因為R是擋土牆
                    while (L < R && nums[R] == nums[R - 1]) // Filter: 遇到重複數字，避免結果是重複數組，跳過
                        R--; // 不用擔心R--會OutOfBoundary, 因為L是擋土牆
                    L++;// 沒有重複數，所以換下一個數來做
                    R--;//  Q:??? 為什麼不將之一L++或R--就好, 而將兩者都++&--, A: 因為前次L1+R1=Sum, 所以在濾掉重複值之下L2+R1或L1+R2是不可能=同一 個Sum,只有L2與R2同時變化, 才有可能出現下次的Sum
                } else if (nums[L] + nums[R] < sum) { // a+b+c
                    L++; // 需要找更大的數
                } else R--; // 需要找更小的數
            }
        }
        return res;
    }

}
