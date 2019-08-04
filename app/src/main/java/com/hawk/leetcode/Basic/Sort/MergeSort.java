package com.hawk.leetcode.Basic.Sort;

public class MergeSort {

    public static void main(String[] args) {
        int n = 9;
        int[] numbers = null;
        numbers = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};
        // Utils.getRandNumbers(numbers); // Random it!
        mergeSort(numbers, numbers.length);
        System.out.print(numbers);
        System.out.println();
    }

    public static void mergeSort(int[] nums, int len) {
        if (len < 2) {
            return;
        }
        // middle for L&R numbers
        int mid = len / 2; // int value=1.5 ==> value=1
        int[] Lnums = new int[mid];
        int[] Rnums = new int[len - mid];

        // copy numbers to L numbers
        for (int i = 0  ; i < mid; i++) {
            Lnums[i] = nums[i];
        }
        // copy numbers to R numbers
        for (int i = mid; i < len; i++) {
            Rnums[i - mid] = nums[i];
        }
        mergeSort(Lnums, mid); // recursive to separate L numbers
        mergeSort(Rnums, len - mid); // recursive to separate R numbers

        merge(nums, Lnums, Rnums, mid, len - mid); // mid=L ~ R=(len-mid)
    }

/*
    {3, 5, 8, 1, 2, 9, 4, 7, 6} 4
    {3, 5, 8, 1}{2, 9, 4, 7, 6} 3
          L3            R7
    {3, 5}{8, 1}{2, 9}{4, 7, 6} 2
     L1 R1 L2 R2 L4 R4 L6{ R6 }
                         {7, 6} 1
                       x  L5 R5
    PS: x is return since len<2.
*/
    // Tips: L(邊界)至少會從1開始, 不是0
    // Tips: L代表此次二分的左邊界起點, R代表此次二分的右邊界終點
    // Tips: 不須用swap, 因為我們有三個array
    public static void merge(int[] nums, int[] Lnums, int[] Rnums, int L, int R) {

        int Lidx = 0, Ridx = 0, numsIdx = 0;
        //
        while (Lidx < L && Ridx < R) { // 一開始LR都有數值群
            if (Lnums[Lidx] <= Rnums[Ridx]) { // 誰小誰填入array, 因為是上升排列
                nums[numsIdx++] = Lnums[Lidx++];
            } else {
                nums[numsIdx++] = Rnums[Ridx++];
            }
        }
        // R數值群都分完了, L的數值群還有剩下的數
        while (Lidx < L) {
            nums[numsIdx++] = Lnums[Lidx++];
        }
        // L數值群都分完了, R的數值群還有剩下的數
        while (Ridx < R) {
            nums[numsIdx++] = Rnums[Ridx++];
        }

    }
}
