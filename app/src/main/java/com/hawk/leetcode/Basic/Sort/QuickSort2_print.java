package com.hawk.leetcode.Basic.Sort;

import java.util.Random;

/**
 *
 * https://www.youtube.com/watch?v=lOESmNCnrGc&t=343s
 * */
public class QuickSort2_print {
    static void print(int[] a, int P, int L, int R) {
        for (int x : a)
            System.out.print(x + "\t");
        System.out.println();
        int c_len = (R > L) ? R + 1 : L + 1;
        char[] c = new char[c_len];
        c[L] = 'L';
        c[R] = 'R';
        c[P] = 'P';
        for (char cc : c)
            System.out.print(cc + "\t");
        System.out.println();
    }

    static public void main(String[] args) {
        int n = 9;
        int[] a = new int[n];
        // A. Random number
        // for (int i = 0; i < a.length; i++)
        //    a[i] = (new Random()).nextInt(100);
        // B. Custom test data
        a= new int[] { 3 ,5 ,8 ,1 ,2 ,9 ,4 ,7 ,6 };
        print(a, 0, 1, n);
        qsort(a, 0, n - 1);
        System.out.println();
    }

    static void qsort(int[] a, int low, int high) {
        if (low >= high)
            return;
        else {
            int R = partition(a, low, high); // R=pivot
            // {low, P, L, R, high}
            qsort(a, low, R - 1); // Range1: low to (pivot-1)
            qsort(a, R + 1, high); // Range2: (pivot-1) to  high
        }
    }
    // boundary is {low, P, L, R, high}
    private static int partition(int a[], int low, int high) {
        int pivot = a[low]; // 固定取出區間的最左, a[low]當作pivot
        int L = low; // 這樣下面的 ++L 一開始就會忽略最左的原素, 因為這是pivot
        int R = high + 1; // 這樣下面的--R才不會沒掃到最右邊
        // low>|..L-->..|pivot|..<--R..|<high
        while (L < R) { // L<R, L在low~pivot間掃描, R在pivot~high間掃描, 只要LR沒越界
            while (a[++L] < pivot) { // find a L-val > pivot
                if (L >= high) // scan direction from L --> high
                    break; // 跳出子while
            }
            while (a[--R] > pivot) { // find a R-val < pivot
                if (R <= low) // scan direction from low <-- R
                    break; // 跳出子while
            }
            if (L >= R)
                break; // 這個break跳出主while, 以免過多++L,--R超出a[]界外
            print(a, low, L, R); // found L-val,R-val
            swap(a, L, R); // swap L-val,R-val
            System.out.println("swap a[L] & a[R]");
            print(a, low, L, R); // swap-ed  L-val,R-val
        }
        // 以下開始, L=R 或 L>R, 需要換pivot
        print(a, low, L, R);
        // 要執行左右數組分割:
        // 當前R的右側數值們均是比pivot=a[low]還大的數值, 因為R是一路往左掃過來的
        // 同理L的左側數值們均是比pivot=a[low]還小的數值, 因為L是一路往右掃過來的
        // 而a[low]=pivot必是分割左右數列的基準, 所以將pivot換到中間. 以進行切割
        // 左右數組, 但是應該與中間哪個數互換呢? L還是R互換呢?
        // 此時的L必定是>=pivot,???為何不選L
        // 此時的R必定是<=pivot,必須選R, 因為外面呼叫遞迴是用R計算???
        swap(a, low, R); // KEY: 因a[low]=pivot, 將pivot擺到正確分界點位置R ??? 其他
        print(a, low, L, R);
        System.out.println("swap a[P] & a[R]");
        return R; // 分割後, 將分割線位置回傳, 以便divide-and-conquer
    }

    private static void swap(int[] numbers, int indexA, int indexB) {
        int tmp = numbers[indexA];
        numbers[indexA] = numbers[indexB];
        numbers[indexB] = tmp;
    }
}
