package com.hawk.leetcode.Basic.Sort;

import java.util.Random;

public class QuickSort {
    /**
     * C# version
     * https://zh.wikipedia.org/wiki/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F#Java
     */
    public static void main(String[] args) {
        int n = 9;
        int[] numbers;
        numbers = new int[] { 3 ,5 ,8 ,1 ,2 ,9 ,4 ,7 ,6 };
        // Utils.getRandNumbers(numbers); // Random it!
        quick(numbers);
        System.out.print(numbers);
        System.out.println();
    }

    public static void quick(int[] numbers)
    {
        Sort(numbers, 0, numbers.length - 1);
    }

    private static void Sort(int[] numbers, int left, int right)
    {
        if (left < right)
        {
            int pivot; // 三種pivot設定
            // 1. middle pivot
            // pivot = numbers[(left + right) / 2];
            // 2a. start
            // pivot = numbers[left];
            // 2b. end
            // pivot = numbers[right];
            // 3. random
            int seed=0;
            Random r = new Random();
            seed = r.nextInt((right - left) + 1) + left;
            pivot = numbers[seed];

            int L = left - 1;  // 一開始在界外
            int R = right + 1; // 一開始在界外
            while (true) // 找出左側小於pivot, 右側大於pivot 的掃描迴圈
            {
                while (numbers[++L] < pivot) // 找出左側小於pivot
                    ;
                // 當L找不到小於pivot或是掃到盡頭時,往下進行
                while (numbers[--R] > pivot) // 找出右側大於pivot
                    ;
                // 當R找不到大於pivot或是掃到盡頭時,往下進行
                if (L >= R) // 掃完了! 越界或接吻了, 不符合LR分割, 離開
                    break;  // KEY: 掃完了!
                Swap(numbers, L, R); // 因為掃到LR均不, 所以互換
            }
            // |left->| ..左半部數值群.. |(退回++L前的位置才是小於pivot)| pivot |right->| ..右半部數值群.. |(退回--R前的位置才是大於pivot)|
            Sort(numbers, left, --L); // 左半都是小於Pivot: left ~ (退還上面的++L)
            Sort(numbers, ++R, right); // 右半都是大於Pivot: (退還上面的--R) ~ right
        }
    }

    private static void Swap(int[] numbers, int i, int j)
    {
        int number = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = number;
    }
}
