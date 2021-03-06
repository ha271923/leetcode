package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.GTNode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class LCAOfDeepestLeavesTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{Utils.buildTree(Arrays.asList(Arrays.asList(new int[]{1, 0}))), 1},
                new Object[]{Utils.buildTree(Arrays.asList(Arrays.asList(new int[]{1, 1}), Arrays.asList(new int[]{2, 0}))), 2},
                new Object[]{Utils.buildTree(Arrays.asList(Arrays.asList(new int[]{1, 2}), Arrays.asList(new int[]{2, 0}, new int[]{3, 0}))), 1},
                new Object[]{Utils.buildTree(Arrays.asList(Arrays.asList(new int[]{1, 3}), Arrays.asList(new int[]{2, 0}, new int[]{3, 0}, new int[]{4, 0}))), 1},
                new Object[]{Utils.buildTree(Arrays.asList(Arrays.asList(new int[]{1, 3}), Arrays.asList(new int[]{2, 1}, new int[]{3, 0}, new int[]{4, 0}), Arrays.asList(new int[]{5, 0}))), 5}
        };
    }

    @Test(dataProvider = "examples")
    public void testFindLca(GTNode root, int expected) {
        LCAOfDeepestLeaves l = new LCAOfDeepestLeaves();
        Assert.assertEquals(l.findLca(root).val, expected);
    }
}