package com.cspirat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LetterCombinationsofaPhoneNumberTest {
    /**
     *
     * {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
     * ________________________________
     * |    1    |    2    |    3     |
     * |         |   abc   |   def    |
     * |________ |________ |_________ |
     * |    4    |    5    |    6     |
     * |   ghi   |   jkl   |   mno    |
     * |________ |________ |_________ |
     * |    7    |    8    |    9     |
     * |  pqrs   |   tuv   |  wxyz    |
     * |________ |________ |_________ |
     * |         |    0    |          |
     * |         |         |          |
     * |________ |________ |_________ |
     * */


    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"12"},
                new Object[]{"3456"},
                new Object[]{"17890"},
        };
    }

    @Test(dataProvider = "examples")
    public void testExamples(String s) {
        System.out.printf("s=%s \n", s);
        LetterCombinationsofaPhoneNumber m = new LetterCombinationsofaPhoneNumber();
        List<String> res;
        // *  1. DFS, ArrayList, Recursion
        // *  2. BFS, LinkedList
        // *  3. Recursion, HashMap
        // *  4. BFS, Iteration, Time: O(3^n), Space: O(1)
        res = m.letterCombinations_DFS(s); // Edward, fixed Bug
        System.out.printf("1. res.size()=%d \n", res.size());
        res = m.letterCombinations_BFS(s);  // Edward
        System.out.printf("2. res.size()=%d \n", res.size());
        res = m.letterCombinations_HashMap(s);
        System.out.printf("3-1. res.size()=%d \n", res.size());
        // res = m.letterCombinations_HashMap2(s); // Not support '0' , '1' input char
        // System.out.printf("3-2. res.size()=%d \n", res.size());
        res = m.letterCombinations_Iterator(s); //
        System.out.printf("4. res.size()=%d \n", res.size());
    }
}

