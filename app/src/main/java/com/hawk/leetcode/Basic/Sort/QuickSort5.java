package com.hawk.leetcode.Basic.Sort;

import java.util.Random;

/**
 *
 * https://emn178.pixnet.net/blog/post/88613503-%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%E6%B3%95(quick-sort)
 * */
public class QuickSort5 {

    public static void main(String[] args) {
        int[] numbers = {27, 17, 2, 24, 7, 11, 38, 15, 21, 3, 33, 16, 5};
        quick(numbers);
        System.out.print(numbers);
        System.out.println();
    }

    public static void quick(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void sort(int[] numbers, int left, int right) {
        if (right <= left)
            return;
        int swapIndex = partition(numbers, left, right);

        sort(numbers, left, swapIndex - 1);
        sort(numbers, swapIndex + 1, right);
    }

    private static int partition(int numbers[], int start, int end) {
        // A. random pivot
        Random random = new Random();
        int pivotIndex = start + random.nextInt(end - start + 1);

        // B. middle pivot
        // int pivotIndex = (end + start) / 2;

        int pivot = numbers[pivotIndex];
        swap(numbers, pivotIndex, end);
        int swapIndex = start;
        for (int i = start; i < end; ++i) {
            if (numbers[i] <= pivot) {
                swap(numbers, i, swapIndex);
                ++swapIndex;
            }
        }
        swap(numbers, swapIndex, end);
        return swapIndex;
    }

    private static void swap(int[] numbers, int indexA, int indexB) {
        int tmp = numbers[indexA];
        numbers[indexA] = numbers[indexB];
        numbers[indexB] = tmp;
    }

}
