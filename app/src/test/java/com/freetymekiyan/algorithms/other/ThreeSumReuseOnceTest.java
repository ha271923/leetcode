package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ThreeSumReuseOnceTest {

    @Test
    public void testThreeSumReuseOnce() {
        int[] nums = {3, 2, 1, 4, 6, 7};
        int target = 10;
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 2, 7), Arrays.asList(1, 3, 6), Arrays.asList(2, 2, 6), Arrays.asList(3, 3, 4), Arrays.asList(2, 4, 4));
        ThreeSumReuseOnce t = new ThreeSumReuseOnce();
        Assert.assertTrue(Utils.compareListsIgnoreOrder(t.threeSumReuseOnce(nums, target), expected));

        target = 9;
        expected = Arrays.asList(Arrays.asList(1, 1, 7), Arrays.asList(1, 2, 6), Arrays.asList(1, 4, 4), Arrays.asList(2, 3, 4)); // Note that [3,3,3] is invalid.
        Assert.assertTrue(Utils.compareListsIgnoreOrder(t.threeSumReuseOnce(nums, target), expected));
    }
}