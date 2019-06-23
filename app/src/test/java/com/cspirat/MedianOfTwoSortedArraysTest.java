package com.cspirat;

import com.freetymekiyan.algorithms.level.hard.MedianOfTwoSortedArrays;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MedianOfTwoSortedArraysTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{1}, new int[]{3}, 2 },
                new Object[]{new int[]{1, 2, 3, 5, 7, 8}, new int[]{9, 10, 11, 12}, 7.5},
                new Object[]{new int[]{1, 3, 8, 9, 15}, new int[]{7, 11, 18, 19, 21, 25}, 11.0},
                new Object[]{new int[]{}, new int[]{}, 0.0},
                new Object[]{new int[]{2}, new int[]{}, 2.0},
                new Object[]{new int[]{}, new int[]{2}, 2.0},
                new Object[]{new int[]{1, 2, 3, 4, 5}, new int[]{2, 4, 5, 6, 7}, 4.0},
                new Object[]{new int[]{1, 2, 3, 4, 5}, new int[]{2, 4, 5, 6, 7, 8}, 4.0},
        };
    }

    @Test(dataProvider = "examples")
    public void testExamples(int[] nums1, int[] nums2, double expected) {
        MedianofTwoSortedArrays m = new MedianofTwoSortedArrays();
        double res = m.findMedianSortedArrays(nums1, nums2);
        Assert.assertEquals(res , expected);
    }
}