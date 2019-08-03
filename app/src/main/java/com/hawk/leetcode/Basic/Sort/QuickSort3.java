package com.hawk.leetcode.Basic.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * https://emn178.pixnet.net/blog/post/88613503-%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%E6%B3%95(quick-sort)
 * */
public class QuickSort3 {

    public static void main(String[] args) {
        int[] numbers = {27, 17, 2, 24, 7, 11, 38, 15, 21, 3, 33, 16, 5};
        quick(numbers);
        System.out.print(numbers);
        System.out.println();
    }

    public static void quick(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int n : array)
            list.add(n);
        list = sort(list);
        for (int i = 0; i < array.length; ++i)
            array[i] = list.get(i);
    }

    public static List<Integer> sort(List<Integer> list) {
        if (list.size() < 2)
            return list;

        // A. random pivot
        Random random = new Random();
        int pivot = list.get(random.nextInt(list.size() - 1));

        // B. middle pivot
        // int pivot = list.get(list.size() / 2);

        list.remove(list.size() / 2);
        List<Integer> less = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (Integer n : list) {
            if (n > pivot)
                greater.add(n);
            else
                less.add(n);
        }
        result.addAll(sort(less));
        result.add(pivot);
        result.addAll(sort(greater));
        return result;
    }
}
