package com.cspirat;

import com.utils.Out;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SearchinRotatedSortedArrayII
 * Creator : Edward
 * Date : Sep, 2017
 * Description : TODO
 */
public class SearchinRotatedSortedArrayII {
    public static void main(String[] args) {
        // test1
        int[] inputs = {2,5,6,0,0,1,2};
        int target1 = 0; // ans: true
        int target2 = 3; // ans: false
        boolean res = search(inputs, target2);
        System.out.println(res);
    }
    /**
     * 81. Search in Rotated Sorted Array II
     * Follow up for "Search in Rotated Sorted Array":
     What if duplicates are allowed?

     1 1 1 3 1

     time : O(logn) (worst : O(n))
     space : O(1);
     * @param nums
     * @param target
     * @return
     */
/*
                              L           R
                         idx= 0 1 2 3 4 5 6
  {0,0,1,2,2,5,6}==Rotated==>{2,5,6,0,0,1,2}
                               < < > = < < =

 */

    // BinarySearch: 2分, 比對, 針對重複值的情況, 遞增遞減, 限縮邊界, 再一輪
    static public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int L = 0;
        int R = nums.length - 1;
        while (L <= R) { // 每次計算出LR的新值, 逐漸逼近縮小陣列區間
            int M = L + (R - L) / 2; // KEY: 每次分兩半, M是切割線, LR是數值邊界
            if (nums[M] == target) // 在此比對數值是否找到
                return true;
            // Skip duplicates.
            if (nums[L] == nums[M] && nums[M] == nums[R]) { // 連續數字相同時, 頭中尾相同 [1, 3, 1, 1, 1] OR [1, 1, 1, 3, 1] ,無法判斷新區間, 故內縮LR以創造新區間
                L++;
                R--;
            } else if (nums[L] == nums[M]) { // L~M連續數字相同時, 若頭中相同 [1, 1, 1, 2, 3] 跳過相同數字,新區間變成 [     L=2, 3]
                L = M + 1;
            } else if (nums[M] == nums[R]) { // M~R連續數字相同時, 若中尾相同 [2, 3, 1, 1, 1] 跳過相同數字,新區間變成 [2, R=3     ]
                R = M - 1;
            } else if (nums[L] < nums[M]) { // 連續數字不同時,若遞增排序 [L, M] sorted.  [1, 2, 3, 4, 5, 6]
                if (nums[L] <= target && target < nums[M]) { // 比較target,因為排序過target必在左半部範圍,所以重新定義R
                    R = M - 1;
                } else { // 比較target,因為排序過target必在右半部範圍,所以重新定義L
                    L = M + 1;
                }
            } else if (nums[L] > nums[M]) { // 連續數字不同時,若遞減排序 [M, R] sorted.
                if (nums[M] < target && target <= nums[R]) {
                    L = M + 1;
                } else {
                    R = M - 1;
                }
            }
        }
        return false;
    }

    static public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int L = 0;
        int R = nums.length - 1;
        while (L + 1 < R) {
            int M = (R - L) / 2 + L;
            Out.i("  L="+L+"  M="+M+"  R="+R);
            Out.i("val="+nums[L]+"    "+nums[M]+"    "+nums[R]);

            if (nums[M] == target) { // 有沒有找到? 指標M代表所選欲比對target數字
                Out.i("target="+target+" == nums[M]="+ nums[M]);
                return true;
            }
            // 指標M代表所選欲比對target數字, 分幾種情況:
            // [L]    [M]    [R]           M
            //     ==     ==        {1,2,3,1,1,1,1}  頭中尾相同
            //     <=               {1,2,3,4,0,0,0}  遞升
            //            >=,==,<=  {0,0,0,1,2,3,4}  遞減
            //
            if (nums[L] == nums[M] && nums[M] == nums[R]) { // 中間與右邊左邊三值相等, ex: {1,2,3,1,1,1,1}
                L++;
                R--;
            }

            else if (nums[L] <= nums[M]) { // 找左區間 ex: {1,2,3,4,5}
                if (nums[L] <= target && target <= nums[M])
                    R = M;
                else
                    L = M;
            }
             /*
              如果 nums[M] < nums[L]，代表說 first到middle 是由大到小排序的，
              這時候再判斷 target 是不是在這區間，
              是的話就搜尋這區間，
              不是的話就搜尋另一邊。
             */
            else { // 左邊>中間, ex: {5,1,2,3,4}
                if (nums[M] <= target && target <= nums[R]) // 由小到大
                    L = M;
                else
                    R = M;
            }
        }
        Out.i("target="+target+"  nums[L]="+nums[L]+"    nums[R]="+nums[R]);

        if (nums[L] == target)
            return true;
        if (nums[R] == target)
            return true;
        Out.i("return false");
        return false;
    }
}
