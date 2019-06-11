package com.cspirat;

import com.freetymekiyan.algorithms.level.medium.SwapNode;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SwapNodesinPairsTest
{
    @DataProvider(name = "data")
    public Object[][] getExamples() {
        ListNode input1, input2;
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(6);
        ListNode a7 = new ListNode(7);
        ListNode a8 = new ListNode(8);
        ListNode a9 = new ListNode(9);
        a1.next = a2; a2.next = a3; a3.next = a4;
        a4.next = a5; a5.next = a6; a6.next = a7;
        a7.next = a8; a8.next = a9; a9.next = null;

        return new Object[][]{
                new Object[]{a1},
        };
    }
/*
    @Test(dataProvider = "data")
    public void testSwapNodesinPairs(ListNode node) {

        SwapNodesinPairs a = new SwapNodesinPairs();
        ListNode res = a.swapPairs(node);
        System.out.printf("res=0x%X \n", res);
    }
*/
    @Test(dataProvider = "data")
    public void testSwapNodesinPairs2(ListNode node) {

        com.freetymekiyan.algorithms.level.medium.SwapNode a = new com.freetymekiyan.algorithms.level.medium.SwapNode();
        ListNode res = a.swapPairs(node);
        System.out.printf("res=0x%X \n", res);
    }
}
