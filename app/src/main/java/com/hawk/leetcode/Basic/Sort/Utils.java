package com.hawk.leetcode.Basic.Sort;

import java.util.Random;

public class Utils {
    public static void getRandNumbers(int[] a) {
        for (int i = 0; i < a.length; i++)
            a[i] = (new Random()).nextInt(100);
    }

}
