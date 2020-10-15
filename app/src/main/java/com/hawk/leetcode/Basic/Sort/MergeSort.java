package com.hawk.leetcode.Basic.Sort;

public class MergeSort {
/*
時間複雜度(Time Complexity)
Best Case：Ο(n log n)
Worst Case：Ο(n log n)
Average Case：Ο(n log n)
T(n) = MergeSort(左子數列) + MergeSort(右子數列) + Merge
     = T(n/2) + T(n/2) + c×n = O(n logn)

https://alrightchiu.github.io/SecondRound/comparison-sort-merge-sorthe-bing-pai-xu-fa.html

A. 先對半分組 ( Divid )
    |3 5 8 1 2 9 4 7 6              |  一開始

    |               |               |  首次對半分

    |       |       |       |       |  再次對半分

    |   |   |   |   |   |   |   |   |  又再次對半分

    |3|5|8|1|2|9|4|7|6| | | | | | |  又又再次對半分, 直到每組都是2個元素


B. 分好組後的各組內的2個元素比大小
    |3|5|8|1|2|9|4|7|6|
    |   |   |   |   | |
    |3 5|1 8|2 9|4|7|6|  第一次比大小, 掃描區間內(n<=2^1)所有數值
    |       |   |     |  sort1, merge1
    |1 3 5 8|2 9|4 6 7|  區間內再次比大小, 掃描區間內(n<=(2^2))所有數值
    |       |         |  sort2, merge2
    |       |2 4 6 7 9|  區間內又再次比大小, 掃描區間內(n<=(2^3))所有數值
    |                 |  sort3, merge3
    |1 2 3 4 5 6 7 8 9|  區間內又再次比大小, 掃描區間內(n<=(2^3))所有數值
 */


    public static void main(String[] args) {
        int n = 9;
        int[] numbers = null;
        numbers = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};
        // Utils.getRandNumbers(numbers); // Random it!
        mergeSort_recursive(numbers);
        mergeSort_loop(numbers);
        printArray("Result= ", numbers);
    }

    public static void mergeSort_recursive(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return;
        }
        // middle for L&R numbers
        int mid = len / 2; // int value=1.5 ==> value=1
        int[] Lnums = new int[mid];
        int[] Rnums = new int[len - mid];

        // 手工將數組分兩半, nums[] to Lnums[],Rnums[]
        for (int i = 0  ; i < mid; i++) {
            Lnums[i] = nums[i];
        }
        for (int i = mid; i < len; i++) {
            Rnums[i - mid] = nums[i];
        }

        mergeSort_recursive(Lnums); // 遞迴：數組分兩半
        mergeSort_recursive(Rnums); // 遞迴：數組分兩半

        // 因為 (len <2 ) 所以不會再往下遞迴
        printArray("merge ++ " , nums);
        // 叫merge sort的原因, 是因為一開始先分支下去變成一個元素一個節點, 接著每層需將排好的Lnums與Rnums進行merge成節點, 最終merge回一個nums[]
        merge(nums, Lnums, Rnums); // Sort two array and merge it to nums in this function
        printArray("merge -- " , nums);
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
    public static void merge(int[] nums, int[] Lnums, int[] Rnums) {
        int L = Lnums.length;
        int R = Rnums.length;

        int Lidx = 0, Ridx = 0, numsIdx = 0;
        //
        while (Lidx < L && Ridx < R) { // 一開始LR都有數值群
            if (Lnums[Lidx] <= Rnums[Ridx]) { // KEY: sorting, 誰小誰填入nums[] OUTPUT array, 因為是上升排列
                nums[numsIdx++] = Lnums[Lidx++]; // merging
            } else {
                nums[numsIdx++] = Rnums[Ridx++]; // merging
            }
        }
        // R數值群都分完了, L的數值群還有剩下的已排序過數
        while (Lidx < L) { // merging
            nums[numsIdx++] = Lnums[Lidx++];
        }
        // L數值群都分完了, R的數值群還有剩下的已排序過數
        while (Ridx < R) { // merging
            nums[numsIdx++] = Rnums[Ridx++];
        }
    }

    public static void printArray(String TAG, int[] nums){
        System.out.print(TAG);
        for(int n:nums){
            System.out.print(n+",");
        }
        System.out.println();
    }

    public static void mergeSort_loop(int[] nums) {
        int[] orderedArr = new int[nums.length];
        for (int i = 2; i < nums.length * 2; i *= 2) {
            for (int j = 0; j < (nums.length + i-1) / i; j++) {
                int L = i * j;
                int mid = L + i / 2 >= nums.length ? (nums.length - 1) : (L + i / 2);
                int R = i * (j + 1) - 1 >= nums.length ? (nums.length - 1) : (i * (j + 1) - 1);
                int start = L, l = L, m = mid;

                while (l < mid && m <= R) {
                    if (nums[l] < nums[m]) {
                        orderedArr[start++] = nums[l++];
                    } else {
                        orderedArr[start++] = nums[m++];
                    }
                }
                while (l < mid)
                    orderedArr[start++] = nums[l++];
                while (m <= R)
                    orderedArr[start++] = nums[m++];

                System.arraycopy(orderedArr, L, nums, L, R - L + 1);
            }
        }
    }

}
