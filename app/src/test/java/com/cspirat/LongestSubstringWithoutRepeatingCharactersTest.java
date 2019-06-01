package com.cspirat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LongestSubstringWithoutRepeatingCharactersTest {
    @DataProvider(name = "LongestSubstringWithoutRepeatingCharactersTest")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"abcdbabcbb"},
                new Object[]{"bbbbb"},
                new Object[]{"pwwkew"},
        };
    }

    @Test(dataProvider = "LongestSubstringWithoutRepeatingCharactersTest")
    public void test(String s) {
        LongestSubstringWithoutRepeatingCharacters m = new LongestSubstringWithoutRepeatingCharacters();
        // m.lengthOfLongestSubstring(s); // 較複雜
        m.lengthOfLongestSubstring2(s);

    }
}