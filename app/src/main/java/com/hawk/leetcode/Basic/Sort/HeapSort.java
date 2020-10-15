package com.hawk.leetcode.Basic.Sort;

import com.utils.Out;

/**
 何謂堆積(Heap)？
  堆積是一棵二元樹，其樹根的鍵值大於子樹的鍵值，
  必須符合完整二元樹的定義
  不管左子樹和右子樹的大小順序(與二元搜尋樹最大的差異處)

 * 堆積樹(Heap Tree)：又叫堆、累堆
   二元樹的一種 ⇒ 每個父節點最多兩個子節點
   堆積樹為完全二元樹(Complete Binary Tree)的一種
 * 最小堆積(Min Heap)：父節點的值小於子節點
   樹根(root)一定最所有節點的最小值
 * 最大堆積(Max Heap)：父節點的值大於子節點
   樹根(root)一定最所有節點的最大值
 * 兄弟節點的大小不重要!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 堆積排序法為選擇排序法的改良

 適合依照成績排序名次的情境:
 http://www.notepad.yehyeh.net/Content/Algorithm/Sort/Heap/Heap.php
 https://youtu.be/H5kAcmGOn4Q?t=114
 https://alrightchiu.github.io/SecondRound/comparison-sort-heap-sortdui-ji-pai-xu-fa.html
                             MIN heap        MIN heap           MAX heap        MAX heap
            冠軍                1              1                  3               3
          /     \             /  \           /  \               /  \            /  \
       亞季軍  亞季軍         3    2         2    3             1    2          2    1

     每加入一個參賽者, 立即排出其名次所在位置

  Q: heap[]= {3, 5, 8, 1, 2, 9, 4, 7, 6};
     NOT heapify:                                MAX heap:
              3                                      9
            /  \                                   /  \
           5    8                                 7    8
         /  \  /  \                             /  \  /  \
        1   2 9    4      == heapify ==>       6   2 3   4
       / \                                   / \
      7   6                                 1   5
  A:

 時間複雜度(Time Complexity):
     Best Case：Ο(n log n)
     Worst Case：Ο(n log n)
     Average Case：Ο(n log n)
     說明：
     建立MaxHeap： Ο(n)
     執行n-1次Delete Max：(n-1) × Ο(log n) = Ο( n log n)
     Ο(n) + Ο( n log n) = Ο( n log n)
 */
public class HeapSort {
    public static void main(String[] args) {
        int n = 9;
        int[] numbers = null;
        numbers = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};
        // Utils.getRandNumbers(numbers); // Random it!
        Out.print_IntArray( "input  =",numbers);
        heapSort(numbers);
        Out.print_IntArray( "res    =",numbers);
    }

    // 先把arr[]的順序, 丟到heapify進行重新排列成heap的順序, 注意此時仍是陣列!
    // 再把heap順序的arr[], 利用分割arr[] partition的方式, 將排好的最大值一一放後面
    // 每次遞迴=將兩個元素swap
    static public void heapSort(int arr[])
    {
        int n = arr.length;
        // 1. arr[] to heap order
        // Build MAX heap array (arr[0]= 9 = MAX value) (rearrange array)
        for (int i = n/2 - 1 ; i >= 0; i--) // n=3, i=0 做一次 ;  n=4, i=1 做兩次
            heapify(arr, n, i);
        Out.print_IntArray( "heapify=",arr);
        // 2. Sort arr[] as heap order
        // One by one extract an element from heap
        // Sort MAX heap array to Ascending order for print ( arr={1,2,3,4,5,6,7,8,9} )
        for (int i=n-1; i>=0; i--) { // LOOP: 為了將陣列分成前面區域是未排序區, 後面區域是已排序過區, 所以從高掃到低 i--
            // Swap current root to end(no need to heapify zone)
            swap(arr, 0, i);
            // call MAX heapify on the reduced heap for get MAX node at top for nexp swap.
            heapify(arr, i, 0);
        }
    }

    // 堆積化(Heapify) 一次遞迴呼叫移動一個元素
    // 把一維陣列arr[]視為BTree, 以位置組織
    // arr[  parentPos]    =Parent node
    // arr[2*parentPos + 1]=L node
    // aee[2*parentPos + 2]=R node
    static void heapify(int arr[], int end, int parentPos) {
        // array 與 Btree的對應關係
        int largestPos = parentPos;  // largest as root
        int L = 2*parentPos + 1;     // left  = 2*arrPos + 1
        int R = 2*parentPos + 2;     // right = 2*arrPos + 2

        // If left or right child is larger than root
        if (L < end && arr[L] > arr[largestPos])
            largestPos = L;
        if (R < end && arr[R] > arr[largestPos])
            largestPos = R;

        // If largest is not root, adjust it to root
        if (largestPos != parentPos) {
            swap(arr, parentPos, largestPos);
            // Recursively heapify the affected sub-tree
            heapify(arr, end, largestPos);
        }
        // ESCAPE from recursive call
    }

    static void swap(int[] nums, int pos1, int pos2) {
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
    }
}
