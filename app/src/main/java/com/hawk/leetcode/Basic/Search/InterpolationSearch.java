package com.hawk.leetcode.Basic.Search;

import com.utils.Out;

public class InterpolationSearch {
    /*
      在已排序的資料中，將資料視為相同斜率的點，藉由在線上的移動來尋找我們需要中間的索引並進行資料比對，
      如果取出的值小於要尋找的值，則提高下界，
      如果取出的值大於要尋找的 值，則降低下界，
      如此不斷的減少搜尋的範圍，所以其本原則與二分搜尋法是相同的，至於中間值的尋找是透過比例運算

      內插曲線:
      插值 = (設算數 - 最小數) / (最大數 - 最小數)
      https://magiclen.org/interpolation-search/
      https://ithelp.ithome.com.tw/articles/10207069
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

    public static int interpolationSearch(final int[] array, final int y) {
        int x1 = 0;
        int x2 = array.length - 1;

        while (x2 >= x1) { // LOOP:
            //   內插公式:
            //      將首點表示為(x1,y1)，將尾點表示為(x2,y2)，(x,y)為位於中間相同斜率的點, 求(x,y)點公式為：
            //      (y-y1)/(x-x1) =(y2-y1)/(x2-x1)
            //   移項之後：
            //      x = (y-y1)*(x2-x1)/(y2-y1) + x1
            final int x = (y - array[x1]) * (x2 - x1) / (array[x2] - array[x1]) + x1;

            if (x > x2 || x < x1) { // 掃完了!
                break;
            }

            if (y == array[x])  // 掃到了!
                return x;
            else if (y > array[x])
                x1 = x + 1; // x1前進近似
            else
                x2 = x - 1; // x2後退近似
        }

        return -1; // 沒掃到QQ
    }
}
