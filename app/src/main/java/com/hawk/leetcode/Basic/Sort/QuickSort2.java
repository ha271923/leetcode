package com.hawk.leetcode.Basic.Sort;

// https://openhome.cc/Gossip/AlgorithmGossip/QuickSort3.htm#Java
public class QuickSort2 {
    public static void main(String[] args) {
        int[] numbers;
        numbers = new int[] { 3 ,5 ,8 ,1 ,2 ,9 ,4 ,7 ,6 };
        // quick(numbers);
        // System.out.print(numbers);
        // System.out.println();

        quickSort3(numbers, 0, numbers.length - 1);
        System.out.print(numbers);
        System.out.println();
    }

    public static void quick(int[] number) {
        sort(number, 0, number.length-1);
    }

    private static void sort(int[] number, int L, int R) {
        if(L < R) {
            int pivotIdx = partition(number, L, R);
            sort(number, L, pivotIdx-1); // 因為[pivot]不參與排序, 所以右邊界排除=pivot-1
            sort(number, pivotIdx+1, R); // 因為[pivot]不參與排序, 所以左邊界排除=pivot+1
        }
    }

    // 算出切割左右的分界線pivot
    /*
          L                       p
        { 3 ,5 ,8 ,1 ,2 ,9 ,4 ,7 ,6 }
                                  p
        { 3 ,5 ,8 ,1 ,2 ,9 ,4 ,7 ,6 }
                                  p
        { 3 ,5 ,8 ,1 ,2 ,9 ,4 ,7 ,6 };
                                  p
        { 3 ,5 ,8 ,1 ,2 ,9 ,4 ,7 ,6 };



     */
    private static int partition(int number[], int L, int R) {
        int L2 = L - 1; // L2之後會往右前進(L2++),取出元素[L2],避免第一次取到[1]而非[0], 故算法以減一起始
        for(int i = L; i < R; i++) { // LOOP: i以L為起點, 範圍從L往右掃描到R邊界停止
            if(number[i] <= number[R]) { // 每次以最右number[R]當pivot,遇到比pivot小的,擺左邊
                L2++; //
                swap(number, L2, i); // 互換的是所掃到的數[i]與
            }
        }
        // iL,iR
        int pivotIdx = L2+1;
        swap(number, pivotIdx, R); // pivotIdx, 來將 [pivotIdx] 擺到正確位置
        return pivotIdx; // 鐵定排好了[pivot]在number[]的位置, [pivot]左側的數均小於(未必有順序),右側的數均大於(未必有順序)
    }

    private static void swap(int[] number, int a, int b) {
        int temp = number[a];
        number[a] = number[b];
        number[b] = temp;
    }

    // http://bioankeyang.blogspot.com/2015/10/javaquick-sort.html
    static public void quickSort3(int[] numbers, int L, int R)
    {
        if(L > R)
        {
            //代表排序已結束
            return;
        }
        int start = L; //代表最左邊那個數字的起始index
        int end = R;   //代表最右邊那個數字的起始index
        int pivotValue = numbers[L]; //用來儲存要排序的數字陣列最左邊的數字
        int temp;   //用來暫存交換時的值

        while (start != end)
        {
            //要先從右往左找
            while (numbers[end] >= pivotValue && start < end)
            {
                end--;
            }

            while (numbers[start] <= pivotValue && start < end)
            {
                start++;
            }

            if (start < end)
            {
                temp = numbers[start];
                numbers[start] = numbers[end];
                numbers[end] = temp;
            }
        }

        numbers[L] = numbers[start];
        numbers[start] = pivotValue;

        //這裡會叫用遞迴，想起有次聽到良葛格說；「遞迴只應天上有，人間只能用迴圈」，XD
        //將原本最左邊的數字歸位後，開始排序以比這個數小的那群數字
        quickSort3(numbers, L, start - 1);
        //將原本最左邊的數字歸位後，開始排序以比這個數大的那群數字
        quickSort3(numbers, start + 1, R);

    }
}
