package com.cspirat;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PartitionList
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 86. Partition List
 */
public class PartitionList {
    public static void main(String[] args) {
        int x = 3;
        ListNode[] test = new ListNode[6];
        test[0] = new ListNode(1);
        test[1] = new ListNode(2);
        test[2] = new ListNode(2);
        test[3] = new ListNode(4);
        test[4] = new ListNode(3);
        test[5] = new ListNode(5);
        test[0].next = test[1];
        test[1].next = test[2];
        test[2].next = test[3];
        test[3].next = test[4];
        test[4].next = test[5];
        test[5].next = null;
        ListNode head = test[0];

        ListNode current = head;

        partition(current, x);
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

    /**
     * KEY: 看懂題目意思!! 所有比x小的node, 挑出來並放到比x大的數前面
     * 给定一个LinkedList和一个特定值 x，对LinkedList进行分隔，使得
     * 所有小于 x 的Node都在大于或等于 x 的Node之前。
     * 你应当保留两个分区中每个Node的初始相对位置, 不要重新排序
     *
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes
     * greater than or equal to x.

     You should preserve the original relative order of the nodes in each of the two partitions.

     For example,
     Given 1->4->3->2->5->2 and x = 3,
     return 1->2->2->4->3->5.


     1->4->3->2->5->2 and x = 3
     smallHead -> 1 -> 2 -> 2 ->
                          smallPtr(移動)
     bigHead -> 4 -> 3 -> 5 ->
                         bigPtr(移動)

     time : O(n)
     space : O(n)


     Q1: [1,4,3,2,5,2]        A1: [1,2,2,4,3,5]
     Q2: [1,4,7,3,2,8,5,2]    A2: [1,2,2,4,7,3,8,5]
     把小於x=3的數值都放在3的前面

     * @param head
     * @param x
     * @return
     */
    // 1. 掃描
    // 2. 建左Node List
    // 3. 建右Node List
    // 4. 左Node List -> 右Node List 串接兩者
    static public ListNode partition(ListNode head, int x) {
        //  smallPtr[0] , big[0] , head[1,4,7,3,2,8,5,2]
        //   smallhead , x , bighead
        ListNode smallHead = new ListNode(0); // 此Node只是為了要有 .next的資料結構, 用來串接
        ListNode bigHead = new ListNode(0);   // 此Node只是為了要有 .next的資料結構, 用來串接
        ListNode smallPtr = smallHead;
        ListNode bigPtr = bigHead;
        ListNode current = head; // 第一個數值
        while (current != null) {
            ListNode newNode = new ListNode(current.val); // 創造出新的ListNod, 而非調整既有的
            if (current.val < x) { // 掃到小於x的數值, 串接出左側的Node List
                smallPtr.next = newNode;
                smallPtr = smallPtr.next;
            } else { // KEY: // 掃到非小於x的數值(不須排序), 串接出右側的Node List
                bigPtr.next = newNode;
                bigPtr = bigPtr.next;
            }
            current = current.next;
        }
        smallPtr.next = bigHead.next; // KEY: 將左側與右側的兩條Node List, 兩者連接起來
        return smallHead.next;
    }
}
