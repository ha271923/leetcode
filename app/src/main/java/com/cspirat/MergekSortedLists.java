package com.cspirat;

import java.util.PriorityQueue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MergekSortedLists
 * Creator : Edward
 * Date : Oct, 2017
 * Description : 23. Merge k Sorted Lists
 */
public class MergekSortedLists {
    /**
     *
     time : O(nlogk) where k is the number of linked lists
     space : O(n)


     * @param lists
     * @return
     */

    /*
    * 下面這種解法利用到了混合排序的思想，也屬於分治法的一種，做法是將原鍊錶分成兩段，
    * 然後對每段調用遞歸函數，suppose返回的left和right已經合併好了，然後再對left和
    * right進行合併，合併的方法就使用之前那道Merge Two Sorted Lists中的任意一個解法
    * */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

// 兩兩合併，就是前兩個先合併，合併好了再跟第三個，然後第四個直到第k個。這樣的思路是對的，但是效率不高
// 這裡就需要用到分治法就是不停的對半劃分，比如k個鍊錶先劃分為合併兩個k/2個鍊錶的任務，再不停的往下劃分，直到劃分成只有一個或兩個鍊錶的任務，開始合併。
//
// A. 5 nodes = [L,R]->[0,1]->[0,2]->[3,4]->[0,3]= result
// B. 4 nodes = [L,R]->[0,1]->[0,2]->[3,4]->[0,3]= result
// C. 3 nodes = [L,R]->[0,1]->[0,2]->[3,4]->[0,3]= result

//          n nodes          callstack -->                   <-- result
// -------------------------------------------------------------------------
// sorted ListNode[0]    <== lowList                             _cut1L2L3L4L_ / [0] -- (0,0) nodeEnd
// sorted ListNode[1]                              _cut1L2L3L_ /  res1L[0,1]   \ [1] -- (1,1)
// sorted ListNode[2]              _cut1L2_      /  res2L[res1L,res1R]
// sorted ListNode[3]         res3L[res2L,res2R] \             \  res1R[2,3] -<
// sorted ListNode[...]
// sorted ListNode[m]  <== middleList
//                _cut1_
// sorted ListNode[m+1]<== middleList+1                             _cut1R2L3L4L_ / [m+1] -- (m+1) nodeEnd
// sorted ListNode[...]                              _cut1R2L3L_  / res1[m+1,m+2] \ [m+2] -- (m+2)
// sorted ListNode[n-3]               _cut1R2L3L_ /  res2L[res1L,res1R]
// sorted ListNode[n-2]  _cut1R2_ / res2L[res2,n-2] \
// sorted ListNode[n-1]     res3R \
// sorted ListNode[n]    <== highList

// OUTPUT:
// Tips: 留意 [0,1,2,3,4,5,6,7,8] 的cut1是切在 L1=[0,1,2,3,4], R1=[5,6,7,8], L份額大R份額小
//   1 sort= left  =sort(0,4)
//      2 sort= left  =sort(0,2)
//          3 sort= left  =sort(0,1)
//              4 sort= left  =sort(0,0) = [0]
//              4 sort= right =sort(1,1) = [1]
//              cut3 -----------------------------
//              4 sort.merge(0,1)
//          3 sort= right =sort(2,2) = [2]
//          3 sort.merge(1,2)
//      cut2 -------------------------------------
//      2 sort= right =sort(3,4)
//          3 sort= left  =sort(3,3) = [3]
//          3 sort= right =sort(4,4) = [4]
//          3 sort.merge(3,4)
//      2 sort.merge(2,3)
//   cut1 ----------------------------------------
//   1 sort= right =sort(5,8)
//      2 sort= left  =sort(5,6)
//          3 sort= left  =sort(5,5) = [5]
//          3 sort= right =sort(6,6) = [6]
//          3 sort.merge(5,6)
//          cut4 ---------------------------------
//      2 sort= right =sort(7,8)
//          3 sort= left  =sort(7,7) = [7]
//          3 sort= right =sort(8,8) = [8]
//          3 sort.merge(7,8)
//      2 sort.merge(6,7)
//   1 sort.merge(4,5)
    static int recursive = 0;
    public ListNode sort(ListNode[] lists, int lowList, int highList) {
        if (lowList >= highList)  // List相同時, 探索到達Node的最尾唯一端點, 無法也不須執行merge
            return lists[lowList];
        recursive++;
        int middleList = (highList - lowList) / 2 + lowList; // KEY: 用2分法,從ListNode[]中挑出2組list
        System.out.printf("%d sort= left  =sort(%d,%d) \n", recursive, lowList, middleList);
        ListNode  left = sort(lists, lowList, middleList);             // ==> 遞迴往下找出左端
        System.out.printf("%d sort= right =sort(%d,%d) \n", recursive, middleList + 1, highList);
        ListNode right = sort(lists, middleList + 1, highList); // <== 遞迴往上找出右端
        System.out.printf("%d sort.merge(%d,%d) \n", recursive, Math.max(lowList, middleList), Math.min(middleList + 1, highList));
        recursive--;
        return merge(left, right);
    }
    // Same with MergeTwoSortedLists.java
    // Tips: merge將l1與l2合而為一
    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        }
        l2.next = merge(l1, l2.next);
        return l2;
    }

    /*
     * 這道題讓我們合併k個有序鍊錶，最終合併出來的結果也必須是有序的，之前我們做過一
     * 道  Merge Two Sorted Lists，是混合插入兩個有序鍊錶。
     * 這道題增加了難度，變成合併k個有序鍊錶了，但是不管合併幾個，基本還是要兩兩合併。
     * 那麼我們首先考慮的方法是能不能利用之前那道題的解法來解答此題? 答案是肯定的，
     * 但是需要修改，怎麼修改呢?
     * 最先想到的就是兩兩合併，就是前兩個先合併，合併好了再跟第三個，然後第四個直到第
     * k個。這樣的思路是對的，但是效率不高，沒法通過OJ，
     * 所以我們只能換一種思路，這裡就需要用到分治法Divide and Conquer Approach。
     * 簡單來說就是不停的對半劃分，比如k個鍊錶先劃分為合併兩個k/2個鍊錶的任務，再不停
     * 的往下劃分，直到劃分成只有一個或兩個鍊錶的任務，開始合併。
     * 舉個例子來說比如合併6個鍊錶，那麼按照分治法，我們首先分別合併0和3，1和4，2和5。
     * 這樣下一次只需合併3個鍊錶，我們再合併1和3，最後和2合併就可以了。
     * 代碼中的k是通過(n+1)/2計算的，這里為啥要加1呢，這是為了當n為奇數的時候，k能始
     * 終從後半段開始，比如當n=5時，那麼此時k=3，則0和3合併，1和4合併，最中間的2空出
     * 來。
     * 當n是偶數的時候，加1也不會有影響，比如當n=4時，此時k=2，那麼0和2合併，1和3合併
     * ，完美解決問題。
     * */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null) {
                queue.add(cur.next);
            }
        }
        return dummy.next;
    }
}
