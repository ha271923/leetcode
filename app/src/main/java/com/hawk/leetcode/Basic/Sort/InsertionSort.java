package com.hawk.leetcode.Basic.Sort;

import com.utils.Out;

import java.util.ArrayList;
import java.util.List;
/*
時間複雜度(Time Complexity)
  Best Case：Ο(1)
    當資料的順序恰好為由小到大時，每回合只需比較1次
  Worst Case：Ο(n^2)
    當資料的順序恰好為由大到小時，第i回合需比i次
  Average Case：Ο(n2)
    第n筆資料，平均比較n/2次
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] numbers;
        // numbers = new int[] { 3 ,5 ,8 ,1 ,2 ,9 ,4 ,7 ,6 };
        numbers = new int[] { 1 ,3 ,2 };
        // Utils.getRandNumbers(numbers); // Random it!
        insertionSort(numbers);
        // insertionSort_extraSpace(numbers);
        for(int x: numbers) {
            Out.i(x+",");
        }
        Out.i("");
    }

    public static void insertionSort(int[] array) {
        int val, sortedNumIdx;
        for (int numIdx = 1; numIdx < array.length; ++numIdx) { // 從i=1開始, 為了一次可取i=0,i=1兩數字, 因為至少要兩個數才能比較大小
            val = array[numIdx]; // 挑一個數字跟sorted Nums進行一一比較
            for (sortedNumIdx = numIdx - 1; sortedNumIdx >= 0 && array[sortedNumIdx] > val; --sortedNumIdx) { // KEY: 用array[sortedNumIdx] > val這個判斷式來找到sortedNumIdx作為後續插入使用
                array[sortedNumIdx + 1] = array[sortedNumIdx]; // 數字往右shift, 比val小的數, 找到後立即跳出LOOP並記錄此時的sortedNumIdx
            }
            array[sortedNumIdx + 1] = val; // 當sortedNumIdx=-1時(KEY:靠精妙的--sortedNumIdx), 即設入array[0]的值
        }
    }

    public static void insertionSort_extraSpace(int[] array) {
        List<Integer> sorted = new ArrayList<Integer>(array.length); // 多了這個temp存放區, 參數是initialCapacity not size()
        // new之後, sorted.size()仍為0
        for (int i = 0; i < array.length; ++i) { // LOOP1:
            int val = array[i];
            int index = sorted.size() - 1; // -1 因為要idx使用,所以-1
            while (index >= 0 && val < sorted.get(index)) { // LOOP2: 掃描當前已排序過的數是否有數字小於新數字
                --index; // KEY: -1 一個一個掃, 一掃到就跳出迴圈, 並獲得位置index
            }
            sorted.add(index + 1, val); // KEY: +1 因為上面有先-1 , 一般情況插後面
        }
        // Convert: List<Integer> to int[]
        for (int i = 0;i < array.length;++i)
            array[i] = sorted.get(i);
    }
}
