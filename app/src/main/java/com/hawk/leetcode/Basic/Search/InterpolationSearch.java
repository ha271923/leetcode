package com.hawk.leetcode.Basic.Search;

import com.utils.Out;

public class InterpolationSearch {
    /*
      內插公式:
      x = [(y-y1)*(x2-x1)]/[(y2-y1)+x1]

      內插曲線:
      https://magiclen.org/interpolation-search/
     */
    public static void main(String[] args) {
        // 0  1  2  3   4   5   6   7   8   9  10  11  12
        int[] sortedArray = {2, 3, 5, 7, 11, 15, 16, 17, 21, 24, 27, 33, 38};

        int index;
        // index = binarySearch_Iterator(sortedArray, 5, 3, 9);
        index = interpolationSearch(sortedArray, 15);
        Out.print_IntArray(sortedArray);
        Out.i("index="+index);
        System.out.println();
    }

    public static int interpolationSearch(final int[] array, final int targetElement) {
        int start = 0;
        int end = array.length - 1;

        while (end >= start) {
            final int x = (targetElement - array[start]) * (end - start) / (array[end] - array[start]) + start;

            if (x > end || x < start) {
                break;
            }

            if (targetElement == array[x])
                return x;
            else if (targetElement > array[x])
                start = x + 1;
            else
                end = x - 1;
        }

        return -1;
    }
}
