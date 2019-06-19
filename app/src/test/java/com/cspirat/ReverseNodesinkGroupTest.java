package com.cspirat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ReverseNodesinkGroupTest
{
    @DataProvider(name = "data")
    public Object[][] getExamples() {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(6);
        ListNode a7 = new ListNode(7);
        ListNode a8 = new ListNode(8);
        ListNode a9 = new ListNode(9);
        ListNode a10 = new ListNode(10);
        ListNode a11 = new ListNode(11);
        a1.next = a2; a2.next = a3; a3.next = a4;
        a4.next = a5; a5.next = a6; a6.next = a7;
        a7.next = a8; a8.next = a9; a9.next = a10;
        a10.next = a11; a11.next = null;

        return new Object[][]{
                new Object[]{a1},
        };
    }

    @Test(dataProvider = "data")
    public void test(ListNode node) {

        ReverseNodesinkGroup a = new ReverseNodesinkGroup();
        // ListNode res = a.reverseKGroup(node, 3);
        // ListNode res = a.reverseKGroup_iterator(node, 3);
        ListNode res = a.reverseKGroup_recursive(node, 3);
        System.out.printf("res=0x%X \n", res);
    }


    @Test(dataProvider = "data")
    public void test2(ListNode node) {

        com.freetymekiyan.algorithms.level.hard.ReverseNodesInKGroup a = new com.freetymekiyan.algorithms.level.hard.ReverseNodesInKGroup();
        ListNode res = a.reverseKGroup(node, 3);  // recursive
        // ListNode res = a.reverseKGroup2(node, 3); // loop
        System.out.printf("res=0x%X \n", res);
    }
}
