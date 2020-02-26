package com.hawk.leetcode.Basic;

import com.utils.DP_utils;

/**
 * 最大子段和
 * N個整陣列成的序列a[1],a[2],a[3],…,a[n]，求該序列
 * 如a[i] a[i+1] … a[j]的"連續"子段和的最大值。
 * 當所給的整數均為負數時和為0。
 * <p>
 * 例如：-2,11,-4,13,-5,-2，和最大的子段為：11,-4,13。和為11+(-4)+13=20
 *          ^  ^  ^
 *
 * begin idx at [1] 因為題目說:當所給的整數均為負數時和為0。
 *       -2  11,-4,13,-5,-2
 *      ------------------
 *   -2|  0*
 *   11|  0*  0            <- start at 11
 *   -4|  0*  0  7
 *   13|  0*  0  7  20
 *   -5|  0*  0  7  20 15
 *   -2|  0*  0  7  20 15 13
 *                   ^
 * https://blog.csdn.net/sunshine__0411/article/details/78484850
 */
/*
         input=[-5 , 11,-4 , 13,-4 ,-2 ]
       xLen>=x= [x] [1] [2] [3] [4] [5]  Length
           ans=| 0 | 11| 7 | 20| 16| 14|

           Ans: max segSum of num=20
 */
public class DP6 {
    public static void main(String[] args) {
        int[] input = {-5, 11, -4, 13, -4, -2};
        long res = maxSumOfNumbers(input); // 注意return需為long type
        System.out.println("max segSum Of Numbers=" + res);
    }

    static long maxSumOfNumbers(int[] numbers) {
        int[] dp = new int[numbers.length]; //答案数组
        long maxSum = 0;
        if (numbers[0] > 0) {
            dp[0] = numbers[0];
            maxSum = numbers[0];
        }
        for (int i = 1; i < numbers.length; i++) {
            // KEY: 本題的algorithm +++++++++++++
            dp[i] = dp[i - 1] + numbers[i];  // Result=PrevResult+NewValue
            if (dp[i] > maxSum) { // 至少要大於0, 因為題目說:當所給的整數均為負數時和為0。
                maxSum = dp[i];
            }
            if (dp[i] < 0) { // 至少要大於0, 因為題目說
                dp[i] = 0;
            }
            // KEY: 本題的algorithm -------------
        }

        DP_utils.print(dp);
        return maxSum;
    }

    // remove dp[]
    static long maxSumOfNumbers2(int[] numbers) {
        // int[] dp = new int[numbers.length]; //答案数组
        int temp=0;
        long maxSum = 0;
        // if (numbers[0] > 0) {
        //     temp = numbers[0];
        //     maxSum = numbers[0];
        // }
        for (int i = 0; i < numbers.length; i++) {
            temp = temp + numbers[i];
            if (temp > maxSum) {
                maxSum = temp;
            }
            if (temp < 0) {
                temp = 0;
            }
        }
        // for (int i = 0; i < dp.length; i++) {
        //     System.out.print(dp[i] + " ");
        // }
        return maxSum;
    }

    static long maxSumOfNumbers_other(int[] numbers) {
        long segMaxSum = 0; // 最大子段和
        int partialSum = 0; // 局部子段和
        int start = 0; // 最大子段起始处
        int end = 0; // 最大子段终止处
        for (int i = 1; i < numbers.length; i++) { // 一直相加，直到和不为正数，当前值取代
            if (partialSum > 0) {
                partialSum += numbers[i];
            } else {
                partialSum = numbers[i];
                start = i;
            }
            if (partialSum > segMaxSum) {
                segMaxSum = partialSum;
                end = i;
            }
        }
        for (int i = start; i <= end; i++) {
            System.out.print(numbers[i] + " ");
        }
        return segMaxSum;
    }
}
