package com.hawk.leetcode.Basic.Sort;
/*
選擇排序作法：
  將資料分成已排序、未排序兩部份
  依序由未排序中找最小值(or 最大值)，加入到已排序部份的末端
時間複雜度(Time Complexity):
  Best Case：Ο(n^2)
  Worst Case：Ο(n^2)
  Average Case：Ο(n^2)
  說明：
  無論資料順序如何，都會執行兩個迴圈
 */
public class SelectionSort {
    // KEY: 簡單！ 每次將所有未排序過的n張牌, 全部掃一遍, 找到最小跟當前第1位互換, 重複(n-1)張牌, 直到全部數處理完
    public static void main(String[] args) {
        int[] numbers;
        numbers = new int[] { 3 ,5 ,8 ,1 ,2 ,9 ,4 ,7 ,6 };
        // Utils.getRandNumbers(numbers); // Random it!
        selectionSort(numbers);
        // quicksort_hawk(numbers, 0 , 0);
        for(int x: numbers) {
            System.out.print(x+",");
        }
        System.out.println();
    }

    // https://zh.wikipedia.org/wiki/%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F#Java
    static void selectionSort(int[] arr) {
        for (int baseIdx = 0; baseIdx < arr.length - 1; baseIdx++) { // LOOP1: pic num1
            int minIdx = baseIdx;
            for (int selectIdx = baseIdx + 1; selectIdx < arr.length; selectIdx++) { // LOOP2: pick num2
                if (arr[selectIdx] < arr[minIdx]) // compare with min num, 誰才是最小
                    minIdx = selectIdx;
            }
            Swap(arr, baseIdx, minIdx); // 找到就交換!
        }
    }

    private static void Swap(int[] numbers, int i, int j)
    {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }
}
