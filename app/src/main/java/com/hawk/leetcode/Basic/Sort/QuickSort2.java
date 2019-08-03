package com.hawk.leetcode.Basic.Sort;

import java.util.Random;

/**
 *
 * https://www.youtube.com/watch?v=lOESmNCnrGc&t=343s
 * */
public class QuickSort2 {

    static public void main(String[] args) {
        int n = 8;
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++)
            a[i] = (new Random()).nextInt(100);
        qsort(a, 0, n - 1);
        System.out.println();
    }

    static void qsort(int[] a, int low, int high) {
        if (low >= high)
            return;
        else {
            int R = partition(a, low, high);
            qsort(a, low, R - 1);
            qsort(a, R + 1, high);
        }
    }

    private static int partition(int a[], int low, int high) {
        int pivot = a[low];
        int L = low;
        int R = high + 1;
        while (L < R) { // scan between L and R
            while (a[++L] < pivot) { // | ++L | P | R |
                if (L >= high)
                    break;
            }
            // found L >= high
            while (a[--R] > pivot) { // | L | P | --R |
                if (R <= low)
                    break;
            }
            if (L >= R)
                break;
            swap(a, L, R); // swap a[L] & a[R]
        }
        swap(a, low, R); // KEY: swap a[P] & a[R] for separate two partition
        return R;
    }

    private static void swap(int[] numbers, int indexA, int indexB) {
        int tmp = numbers[indexA];
        numbers[indexA] = numbers[indexB];
        numbers[indexB] = tmp;
    }
}
