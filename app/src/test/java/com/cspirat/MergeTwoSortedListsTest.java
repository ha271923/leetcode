package com.cspirat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MergeTwoSortedListsTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        ListNode input1, input2;

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(3);
        ListNode a3 = new ListNode(4);
        ListNode a4 = new ListNode(9);
        a1.next = a2; a2.next = a3; a3.next = a4;
        a4.next = null;
        input1 = a1;

        ListNode b1 = new ListNode(2);
        ListNode b2 = new ListNode(7);
        ListNode b3 = new ListNode(8);
        ListNode b4 = new ListNode(11);
        ListNode b5 = new ListNode(12);
        b1.next = b2; b2.next = b3; b3.next = b4;
        b4.next = b5; b5.next = null;
        input2  = b1;

        return new Object[][]{
                new Object[]{input1, input2},
        };
    }

    @Test(dataProvider = "examples")
    public void testExamples(ListNode[] listNodes) {
        MergeTwoSortedLists m = new MergeTwoSortedLists();
        ListNode res = m.mergeTwoLists2(listNodes[0], listNodes[1]);
        System.out.printf("res=0x%X \n", res);
    }
}
