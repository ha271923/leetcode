package com.cspirat;

import com.utils.Out;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MedianofTwoSortedArrays
 * Creator : Edward
 * Date : Dec, 2017
 * Description : 4. Median of Two Sorted Arrays
 */
public class MedianofTwoSortedArrays {
/*
    回顧一下中位數的定義，如果某個有序數組長度是奇數，那麼其中位數就是最中間那個，
    如果是偶數，那麼就是最中間兩個數字的平均值。這裡對於兩個有序數組也是一樣的，假
    設兩個有序數組的長度分別為m和n，由於兩個數組長度之和m+n 的奇偶不確定，因此需要
    分情況來討論，對於奇數的情況，直接找到最中間的數即可，偶數的話需要求最中間兩個
    數的平均值。為了簡化代碼，不分情況討論，使用一個小trick，分別找第(m+n+1) / 2 個
    ，和(m+n+2) / 2 個，然後求其平均值即可，這對奇偶數均適用。若m+n 為奇數的話，那麼
    其實(m+n+1) / 2 和(m+n+2) / 2 的值相等，相當於兩個相同的數字相加再除以2，還是其本身。
 */
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
    public static void main(String args[]){
        // {1, 3, 6, 7, 8} {2, 4, 5} = {1, 2, 3, 4, 5, 6, 7, 8} = {4, 5} = 4.5
        int[] nums1 = new int[]{1, 3, 6, 7, 8};
        int[] nums2 = new int[]{2, 4, 5};
        Out.print_IntArray(nums1);
        Out.print_IntArray(nums2);
        double res;
        res = findMedianSortedArrays_recur(nums1, nums2);
        // res = findMedianSortedArrays(nums1, nums2);
        // res = findMedianSortedArrays_recur(nums1, nums2);
        Out.i("res="+res);
    }

    // Tips0: 一個有序數列的總元素數量, 二分成數量相同的 前半部+後半部
    // Tips1: 兩個有序數列, no Merge
    // Tips2: 先求得元素"總數量", 再將其數值(中間那1或2個)取出
    //        nums1=前半部+後半部=cut1代表切點的位置
    //        nums2=前半部+後半部=cut2代表切點的位置
    // Key:
    //        L1 <= R2
    //        L2 <= R1
    //
    // 題目限制了時間複雜度為O(log (m+n))
    // Ans: https://www.cnblogs.com/grandyang/p/4465932.html

    // Kth=range(數字範圍, 分別在nums1或nums2中的)
    // 如果我要找出排序好的兩個陣列 nums1 和 nums2 合併後的第 k 項，那我就先找出兩邊陣列目前的第k/2 項。
    // 如果nums1[k/2]<nums1[k/2]則表示 nums1 的前 k/2 都比 nums2 的前 k/2 項小，則第 k 項絕不可能在這 nums1 的前 k/2 項中；
    // 反之亦然。（證明方式可以用反證，如果第 k 項會在這之中，表示至少要從 nums2 這邊拿 k/2 去補前面不夠的數量，但 nums2 的第 k/2 項比它大
    // ，故不足，所以不可能。）
    // 排除掉 nums1 的前 k/2 項後，再找尋剩下的數字第 k–k/2 項即可，以此類推直到 nums1 陣列或 nums2 陣列沒有值，或是找到只剩一項為止。
    static public double findMedianSortedArrays_recur(int [] nums1, int [] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        // nums1= {1, 3, 6, 7, 8}       nums2= {2, 4, 5}
        // 若數量是偶數個, 要取兩次, 因為中位數的算法
        int cutL = (len1 + len2 + 1 ) / 2;  // 以全數組中間偏左作為分割點
        int cutR = (len1 + len2 + 2 ) / 2;  // 以全數組中間偏右作為分割點

        int retL = 0, retR = 0;;
        retL = findMidValPos(nums1,0 , nums2,0 , cutL);  // 偏左分割點,求出一個中位數的索引位置
        Out.i("retL="+retL+"   LLLLLLLLLLLLLLLLLLLLL");
        retR = findMidValPos(nums1,0 , nums2,0 , cutR);  // 偏右分割點,求出一個中位數的索引位置
        Out.i("retR="+retR+"   RRRRRRRRRRRRRRRRRRRRR");
        double ret = (retL+retR) / 2.0; // KEY: 只要找出兩個數, 無論cutL與cutR是否重疊, 都可用此公式算出中位數
        Out.i("Ans= ("+retL+"+"+retR+")/2.0="+ret);
        return  ret;
    }
    /*
    i1[0/5]   i2[0/3]     halfRange=4
    i1[2/5]   i2[0/3]     halfRange=2
    i1[2/5]   i2[1/3]     halfRange=1

    i1[0/5]   i2[0/3]     halfRange=5
    i1[2/5]   i2[0/3]     halfRange=3
    i1[2/5]   i2[1/3]     halfRange=2
    i1[2/5]   i2[2/3]     halfRange=1
    res=4.5
     */
    static int findMidValPos(int [] nums1, int scanfrom_i1, int [] nums2, int scanfrom_i2, int halfRange) {
        int ret;
        Out.i("nums1["+scanfrom_i1+"/"+nums1.length+"]   nums2["+scanfrom_i2+"/"+nums2.length+"]     halfRange="+halfRange );
        if (scanfrom_i1 >= nums1.length) { // 掃描nums1[],已超出範圍
            ret = nums2[scanfrom_i2 + halfRange - 1]; // 那一定是在nums2[]
            Out.i("scanfrom_i1 >= nums1.length  , ret="+ret);
            return ret;
        }
        if (scanfrom_i2 >= nums2.length ) {
            ret = nums1[scanfrom_i1 + halfRange - 1 ];
            Out.i("scanfrom_i2 >= nums2.length  , ret="+ret);
            return ret;
        }
        if (halfRange == 1 ) { // Ans: 2分逼近法, 逼近到1時,代表midVal找到了!
            ret = Math.min(nums1[scanfrom_i1], nums2[scanfrom_i2]);
            Out.i("halfRange == 1  , ret="+ret);
            return ret;
        }
        // 分別從數組nums1[]與nums2[]個自挑一個數字(中間位置)出來, 作為二分比較使用
        int midVal1 = (scanfrom_i1 + halfRange / 2 - 1 < nums1.length) ? // 在數組nums1[]及設定range範圍的中間, 用2分法分割, 取出中間那個數當作中位數
                 nums1[scanfrom_i1 + halfRange / 2 - 1 ] : Integer.MAX_VALUE; // KEY: 因為MAX_VALUE不可能是在中間的中位數, 所以用一個不可能是中間的數的數字MAX_VALUE, 當作中位數, 如此一來比對切點數字時, 就可將此數組排除在外
        int midVal2 = (scanfrom_i2 + halfRange / 2 - 1 < nums2.length) ?
                 nums2[scanfrom_i2 + halfRange / 2 - 1 ] : Integer.MAX_VALUE;

        // recursively: nums1從i1開始的半邊天與nums2從i2開始的半邊天 相比較
        if (midVal1 < midVal2) { // KEY: 因為MAX_VALUE代表不可能是在中間的中位數, 所以比大小後, 取較小的區間來重新分割區間, 並趨近
            Out.i("midVal1 < midVal2  +++");
            scanfrom_i1 = scanfrom_i1 + halfRange / 2; // 下次再用2分法分割當起始索引i1
            halfRange = halfRange - halfRange / 2;
            ret= findMidValPos(nums1, scanfrom_i1 , nums2, scanfrom_i2, halfRange );  // Recursive:
            Out.i("midVal1 < midVal2  --- ret="+ret);
            return ret;
        } else {
            Out.i("midVal1 < midVal2  +++");
            scanfrom_i2 = scanfrom_i2 + halfRange / 2;
            halfRange = halfRange - halfRange / 2;
            ret= findMidValPos(nums1, scanfrom_i1, nums2, scanfrom_i2 , halfRange );  // Recursive:
            Out.i("midVal1 >= midVal2 --- ret="+ret);
            return ret;
        }
    }

    // ??? 第二遍還是看不懂XD
    static public double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
            cut1 = (cut1shiftR - cut1shiftL) / 2 + cut1shiftL; // 一開始對半切, cut1shiftL,cut1shiftR偏移
            cut2 = allLen / 2 - cut1;        // KEY: nums1左半數量+nums2左半數量=必為總數的一半
            Out.i("cut1="+cut1+"  cut2="+cut2);
            // double 數值, 因為結果可能在這4選2(L/R)
            // 邊際情況: 若nums1或nums2只有一個元素時,為了使算法能進行, 令Integer.MIN_VALUE給L1&2, Integer.MAX_VALUE給R1&2
            //
            //   cut=     V                            V
            // nums1= {1, 3, 6, 7}       nums2= {2, 4, 5}
            double L1 = (cut1 == 0) ?            Integer.MIN_VALUE : nums1[cut1 - 1]; // L1 val at nums1, 切點前面數
            double R1 = (cut1 == nums1.length) ? Integer.MAX_VALUE : nums1[cut1];     // R1 val at nums1, 即該切點位置的數

            double L2 = (cut2 == 0) ?            Integer.MIN_VALUE : nums2[cut2 - 1]; // L2 val at nums2, 切點前面數
            double R2 = (cut2 == nums2.length) ? Integer.MAX_VALUE : nums2[cut2];     // R2 val at nums2, 即該切點位置的數
            Out.i("L1="+L1+"  R1="+R1+"  L2="+L2+"  R2="+R2);
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
            Out.i("cut1shiftL="+cut1shiftL+"  cut1shiftR="+cut1shiftR);
        }
        return -1;
    }

    static public  double findMedianSortedArrays_loop( int [] nums1, int [] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 < len2)
            return findMedianSortedArrays(nums2, nums1);

        if (len2 == 0)
            return (nums1[(len1 - 1) / 2] + nums1[len1 / 2]) / 2.0 ;
        int L = 0, R = 2 * len2;
        while (L <= R) {
            int mid2 = (L + R) / 2 ;
            int mid1 = len1 + len2 -mid2;
            double L1 = mid1 == 0 ? Double.MIN_VALUE : nums1[(mid1 - 1) / 2 ];
            double L2 = mid2 == 0 ? Double.MIN_VALUE : nums2[(mid2 - 1) / 2 ];
            double R1 = mid1 == len1 * 2 ? Double.MAX_VALUE : nums1[mid1 / 2 ];
            double R2 = mid2 == len2 * 2 ? Double.MAX_VALUE : nums2[mid2 / 2 ];
            if (L1 > R2)
                L = mid2 + 1 ;
            else  if (L2 > R1)
                R = mid2 - 1 ;
            else
                return (Math.max(L1, L2) + Math.min(R1, R2)) / 2 ;
        }
        return -1 ;
    }
}
