package com.hawk.leetcode.Basic.Sort;

import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int n = 9;
        int[] numbers = new int[n];
        // for (int i = 0; i < numbers.length; i++)
        //    numbers[i] = (new Random()).nextInt(100);
        numbers = new int[] { 3 ,5 ,8 ,1 ,2 ,9 ,4 ,7 ,6 };
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
            int pivot;
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
            int L = left - 1;
            int R = right + 1;
            while (true)
            {
                while (numbers[++L] < pivot)
                    ;
                while (numbers[--R] > pivot)
                    ;
                if (L >= R)
                    break;
                Swap(numbers, L, R);
            }
            Sort(numbers, left, L - 1);
            Sort(numbers, R + 1, right);
        }
    }

    private static void Swap(int[] numbers, int i, int j)
    {
        int number = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = number;
    }
}
