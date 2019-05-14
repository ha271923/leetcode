package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class WordLadder2Test {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"),
                        Arrays.asList(Arrays.asList("hit", "hot", "dot", "dog", "cog"), Arrays.asList("hit", "hot", "lot", "log", "cog"))},
                new Object[]{"a", "c", Arrays.asList("a", "b", "c"), Arrays.asList(Arrays.asList("a", "c"))},
                new Object[]{"red", "tax", Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"),
                        Arrays.asList(Arrays.asList("red", "ted", "tad", "tax"), Arrays.asList("red", "ted", "tex", "tax"), Arrays.asList("red", "rex", "tex", "tax"))}
        };
    }

    @Test(dataProvider = "examples")
    public void testFindLadders(String beginWord, String endWord, List<String> dict, List<List<String>> expected) {
        WordLadder2 w = new WordLadder2();
        List<List<String>> ladders = w.findLadders(beginWord, endWord, dict);
        Assert.assertEquals(ladders, expected);
    }
}