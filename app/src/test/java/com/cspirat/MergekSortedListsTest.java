package com.cspirat;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MergekSortedListsTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        ListNode[] heads = new ListNode[9]; // a,b,c,d,e,f,g,h,i

        ListNode a1 = new ListNode(0);
        ListNode a2 = new ListNode(3);
        ListNode a3 = new ListNode(4);
        ListNode a4 = new ListNode(9);
        a1.next = a2; a2.next = a3; a3.next = a4;
        a4.next = null;
        heads[0] = a1;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(7);
        ListNode b3 = new ListNode(8);
        ListNode b4 = new ListNode(10);
        ListNode b5 = new ListNode(12);
        b1.next = b2; b2.next = b3; b3.next = b4;
        b4.next = b5; b5.next = null;
        heads[1] = b1;

        ListNode c1 = new ListNode(2);
        c1.next = null;
        heads[2]  = c1;

        ListNode d1 = new ListNode(3);
        ListNode d2 = new ListNode(7);
        ListNode d3 = new ListNode(11);
        d1.next = d2; d2.next = d3; d3.next = null;
        heads[3]  = d1;

        ListNode e1 = new ListNode(4);
        ListNode e2 = new ListNode(6);
        ListNode e3 = new ListNode(7);
        ListNode e4 = new ListNode(9);
        ListNode e5 = new ListNode(13);
        e1.next = e2; e2.next = e3; e3.next = e4;
        e4.next = e5; e5.next = null;
        heads[4]  = e1;

        ListNode f1 = new ListNode(5);
        ListNode f2 = new ListNode(8);
        ListNode f3 = new ListNode(11);
        f1.next = f2; f2.next = f3; f3.next = null;
        heads[5]  = f1;

        ListNode g1 = new ListNode(6);
        g1.next = null;
        heads[6]  = g1;

        ListNode h1 = new ListNode(7);
        ListNode h2 = new ListNode(8);
        ListNode h3 = new ListNode(15);
        h1.next = h2; h2.next = h3; h3.next = null;
        heads[7]  = h1;

        ListNode i1 = new ListNode(8);
        ListNode i2 = new ListNode(12);
        ListNode i3 = new ListNode(13);
        i1.next = i2; i2.next = i3; i3.next = null;
        heads[8]  = i1;

        return new Object[][]{
                new Object[]{heads},
        };
    }

    @Test(dataProvider = "examples")
    public void testExamples(ListNode[] listNodes) {
        MergekSortedLists m = new MergekSortedLists();
        ListNode res = m.mergeKLists(listNodes);
        System.out.printf("res=0x%X \n", res);
    }
}
