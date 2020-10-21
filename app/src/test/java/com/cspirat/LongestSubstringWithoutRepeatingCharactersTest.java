package com.cspirat;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LongestSubstringWithoutRepeatingCharactersTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"abcabcbb"},
                new Object[]{"bbbbb"},
                new Object[]{"pwwkew"},
        };
    }

    @Test(dataProvider = "examples")
    public void testExamples(String s) {
        LongestSubstringWithoutRepeatingCharacters m = new LongestSubstringWithoutRepeatingCharacters();
        // int res = m.lengthOfLongestSubstring(s);

    }
}
