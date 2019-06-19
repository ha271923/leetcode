package com.cspirat;

import com.freetymekiyan.algorithms.level.medium.SwapNode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SubstringwithConcatenationofAllWordsTest
{
    @DataProvider(name = "data")
    public Object[][] getExamples() {
        String input = "barfoothefoobarman";
        String[] words = { "foo", "bar" };

        return new Object[][]{
                {"barfoothefoobarman", new String[] { "foo", "bar" }}, // the word are all of the same length.
        };
    }

    @Test(dataProvider = "data")
    public void test(String sentence, String[] words) {

        SubstringwithConcatenationofAllWords a = new SubstringwithConcatenationofAllWords();
        List<Integer> res = a.findSubstring(sentence, words);

        System.out.printf("res=0x%X \n", res);
    }

}
