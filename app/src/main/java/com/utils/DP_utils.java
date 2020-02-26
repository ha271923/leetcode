package com.utils;

import java.util.Arrays;

public class DP_utils {

    static public void print(int[][] dp){
        for (int y = 0; y < dp.length; y++) {
            for (int x = 0; x < dp[y].length; x++) {
                System.out.print(dp[y][x] + " ");
            }
            System.out.println();
        }
        // System.out.println(Arrays.deepToString(dp));
    }

    static public void print(int[] dp){
        for (int x = 0; x < dp.length; x++) {
            System.out.print(dp[x] + " ");
        }
        System.out.println();
        // System.out.println(Arrays.toString(dp));
    }
}
