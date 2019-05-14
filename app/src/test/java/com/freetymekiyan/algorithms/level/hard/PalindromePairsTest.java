package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PalindromePairsTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new String[]{"bat", "tab", "cat"}, Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0))},
                new Object[]{new String[]{"abcd", "dcba", "lls", "s", "sssll"}, Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0), Arrays.asList(3, 2), Arrays.asList(2, 4))},
        };
    }

    @Test(dataProvider = "examples")
    public void testPalindromePairs(String[] words, List<List<Integer>> expected) {
        PalindromePairs p = new PalindromePairs();
        Assert.assertEquals(p.palindromePairs(words), expected);
    }

    @Test(dataProvider = "examples")
    public void testPalindromePairs2(String[] words, List<List<Integer>> expected) {
        PalindromePairs p = new PalindromePairs();
        Assert.assertTrue(Utils.compareListsIgnoreOrder(p.palindromePairs2(words), expected));
    }
}