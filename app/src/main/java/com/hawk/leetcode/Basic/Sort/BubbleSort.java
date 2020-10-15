package com.hawk.leetcode.Basic.Sort;

import com.cspirat.ListNode;
/*
時間複雜度(Time Complexity)
  Best Case：Ο(n)
    當資料的順序恰好為由小到大時
    第一次執行後，未進行任何swap ⇒ 提前結束
  Worst Case：Ο(n2)
    當資料的順序恰好為由大到小時
    每回合分別執行：n-1、n-2、...、1次
    (n-1) + (n-2) + ... + 1 = n(n-1)/2 ⇒ Ο(n^2)
  Average Case：Ο(n2)
    第n筆資料，平均比較(n-1)/2次
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] inputs = new int[]{61, 17, 29, 22, 34, 60, 72, 21, 50, 1, 62};
        int[] outputs = bubbleSort(inputs);

        for(int x: outputs)
            System.out.print(x+",");
    }

    public static int[] bubbleSort(int[] array) {
        int temp;

        for (int i = 0; i < array.length-1; i++) { // N LOOP1: 逐漸縮短數值陣列長度
            for (int j = 0; j < array.length - 1-i; j++) { // N LOOP2: 針對當前數值陣列的數字進行兩兩比對, 並調換位置
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

}
