package com.cspirat;

import com.freetymekiyan.algorithms.level.medium.AddTwoNumbers2;
import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddTwoNumbers2Test {
    @DataProvider(name = "AddTwoNumbers2Test")
    public Object[][] getExamples() {
        ListNode input1, input2;
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        a1.next = a2; a2.next = a3; a3.next = null;
        input1 = a1;

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(6);
        ListNode b3 = new ListNode(4);
        b1.next = b2; b2.next = b3; b3.next = null;
        input2 = b1;

        return new Object[][]{
                new Object[]{input1, input2},
        };
    }

    @Test(dataProvider = "AddTwoNumbers2Test")
    public void testAddTwoNumbers(ListNode l1, ListNode l2) {

        AddTwoNumbers a = new AddTwoNumbers();
        ListNode result = a.addTwoNumbers(l1, l2);
        int[] expectedValues = new int[]{7, 0, 8};
        Assert.assertNotNull(result);
        compare(expectedValues, result);
    }

    private void compare(int[] expectedValues, ListNode node) {
        for (int value : expectedValues) {
            Assert.assertEquals(value, node.val);
            node = node.next;
        }
    }
}
