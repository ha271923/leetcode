package com.cspirat;

import org.junit.Assert;
import org.testng.annotations.Test;

public class FindFirstandLastPositionofElementinSortedArrayTest {

    @Test
    public void test() {
        FindFirstandLastPositionofElementinSortedArray t = new FindFirstandLastPositionofElementinSortedArray();
        int[] numbers = {1,3};
        int target = 3;
        int[] res = t.searchRange(numbers, target);
    }
}
