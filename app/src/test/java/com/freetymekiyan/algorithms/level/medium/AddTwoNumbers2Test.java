package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.ListNode;
import com.hawk.leetcode.Exams.AddTwoNumbers;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddTwoNumbers2Test {
    public ListNode input1;
    public ListNode input2;

    @Test
    public void testAddTwoNumbersWithCarry() {

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        a1.next = a2; a2.next = a3; a3.next = null;
        input1 = a1;

        ListNode b1 = new ListNode(4);
        ListNode b2 = new ListNode(5);
        ListNode b3 = new ListNode(6);
        b1.next = b2; b2.next = b3; b3.next = null;
        input2 = b1;

        AddTwoNumbers2 a = new AddTwoNumbers2();
        ListNode result = a.addTwoNumbers(input1, input2);
        int[] expectedValues = new int[]{7, 8, 0, 7};
        Assert.assertNotNull(result);
        compare(expectedValues, result);
    }

    @Test
    public void testNullInputs() {
        ListNode l1 = null;
        ListNode l2 = null;
        AddTwoNumbers2 a = new AddTwoNumbers2();
        Assert.assertNull(a.addTwoNumbers(l1, l2));

        int[] list1 = {1, 2, 3};
        l1 = Utils.buildLinkedList(list1);
        ListNode result = a.addTwoNumbers(l1, l2);
        Assert.assertNotNull(result);
        compare(list1, result);

        l2 = l1;
        l1 = null;
        result = a.addTwoNumbers(l1, l2);
        Assert.assertNotNull(result);
        compare(list1, result);
    }

    private void compare(int[] expectedValues, ListNode node) {
        for (int value : expectedValues) {
            Assert.assertEquals(value, node.val);
            node = node.next;
        }
    }
}