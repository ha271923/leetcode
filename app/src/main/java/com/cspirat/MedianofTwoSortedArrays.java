package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MedianofTwoSortedArrays
 * Creator : Edward
 * Date : Dec, 2017
 * Description : 4. Median of Two Sorted Arrays
 */
public class MedianofTwoSortedArrays {

    /**
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.

     Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

     Example 1:
     nums1 = [1, 3]
     nums2 = [2]

     The median is 2.0
     Example 2:
     nums1 = [1, 2]
     nums2 = [3, 4]

     The median is (2 + 3)/2 = 2.5


     O(log(min(m,n)))

     参考博客：http://blog.csdn.net/chen_xinjia/article/details/69258706
     https://www.jianshu.com/p/21f570caca89

     index: 0	1	2	3	4	5
               L1   R1
     num1:	3 	5 |	8 	9    	      4  cut1：左有几个元素
     num2:	1	2 	7 | 10  11  12     6  cut2：左有几个元素
                    L2  R2

     num3:  1 2 3 5 7 | 8 9 10 11 12

     num3 -> num1 + num2 -> num1

     L1 <= R2
     L2 <= R1

     (cutL, cutR)

     L1 > R2 cut1 <<  (cutL, cut1 - 1)
     L2 > R1 cut2 >>  (cut1 + 1, cutR)

     index: 0	1	2	3	4	5
               L1   R1
     num1:	3 	5 |	8 	9   |	      4  cut1：左有几个元素
     num2:	1	2 	7 | 10  11  12     6  cut2：左有几个元素
                    L2  R2

     num3:  1 2 3 5 7 | 8 9 10 11 12

     int cut1 = 2;
     int cut2 = 3;
     int cutL = 0;
     int cutR = 4;


     time : O(log(min(m,n)))
     space : O(1)

     * @param nums1
     * @param nums2
     * @return
     */
    // Tips0: 一個有序數列的總元素數量, 二分成數量相同的 前半部+後半部
    // Tips1: 兩個有序數列,
    // Tips2: 先求得元素"總數量", 再將其數值(中間那1或2個)取出
    //        nums1=前半部+後半部=cut1代表切點的位置
    //        nums2=前半部+後半部=cut2代表切點的位置
    // Key:
    //        L1 <= R2
    //        L2 <= R1
    //
    //
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) { // make input1.len > input2.len
            return findMedianSortedArrays(nums2, nums1); // 數量少的數組在前面
        }
        int allLen = nums1.length + nums2.length; // 先求得元素"總數量", 中間的元素數值即為答案

        // 找兩個排序好的數列, 在合一後, 中間的元素位置落點, 並取出
        int cut1 = 0; // cut pos at nums1
        int cut2 = 0; // cut pos at nums2
        int cut1shiftL = 0;            // cut pos L offset at nums1
        int cut1shiftR = nums1.length; // cut pos R offset at nums1
        while (cut1 <= nums1.length) { // KEY: 在兩個排序好的數列中, 搜尋L/R
            cut1 = (cut1shiftR - cut1shiftL) / 2 + cut1shiftL; // 一開始對半切, 之後cutL,cutR偏移
            cut2 = allLen / 2 - cut1;        // nums1左半數量+nums2左半數量=必為總數的一半
            
            // double 數值, 因為結果可能在這4選2(L/R)
            // 邊際情況: 若nums1或nums2只有一個元素時,為了使算法能進行, 令Integer.MIN_VALUE給L1&2, Integer.MAX_VALUE給R1&2
            double L1 = (cut1 == 0) ?            Integer.MIN_VALUE : nums1[cut1 - 1]; // L1 val at nums1, 切點前面數
            double L2 = (cut2 == 0) ?            Integer.MIN_VALUE : nums2[cut2 - 1]; // R1 val at nums1, 切點前面數
            double R1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];     // L2 val at nums2, 即該切點位置的數
            double R2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];     // R2 val at nums2, 即該切點位置的數
            if (L1 > R2) {        // L1太大,cut1 左移一元素
                cut1shiftR = cut1 - 1;
            } else if (R1 < L2) { // R1太小,cut1 右移一元素
                cut1shiftL = cut1 + 1;
            } else { // 找完了, 開始計算答案
                if (allLen % 2 == 0) { // 總元素量為偶數個
                    L1 = L1 > L2 ? L1 : L2; // 真正的左數是左半部最大的那一個
                    R1 = R1 < R2 ? R1 : R2; // 真正的右數是右半部最小的那一個
                    return (L1 + R1) / 2;
                } else { // 總元素量為奇數個
                    R1 = (R1 < R2) ? R1 : R2;
                    return R1;
                }
            }
        }
        return -1;
    }
}
