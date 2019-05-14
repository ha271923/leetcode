package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AccountsMergeTest {

    List<List<String>> input1 = Collections.unmodifiableList(
            Arrays.asList(
                    Arrays.asList("Alex", "Alex5@m.co", "Alex4@m.co", "Alex0@m.co"),
                    Arrays.asList("Ethan", "Ethan3@m.co", "Ethan3@m.co", "Ethan0@m.co"),
                    Arrays.asList("Kevin", "Kevin4@m.co", "Kevin2@m.co", "Kevin2@m.co"),
                    Arrays.asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe2@m.co"),
                    Arrays.asList("Gabe", "Gabe3@m.co", "Gabe4@m.co", "Gabe2@m.co")
            ));
    List<List<String>> output1 = Collections.unmodifiableList(
            Arrays.asList(
                    Arrays.asList("Alex", "Alex0@m.co", "Alex4@m.co", "Alex5@m.co"),
                    Arrays.asList("Ethan", "Ethan0@m.co", "Ethan3@m.co"),
                    Arrays.asList("Gabe", "Gabe0@m.co", "Gabe2@m.co", "Gabe3@m.co", "Gabe4@m.co"),
                    Arrays.asList("Kevin", "Kevin2@m.co", "Kevin4@m.co")
            )
    );
    List<List<String>> input2 = Collections.unmodifiableList(
            Arrays.asList(
                    Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                    Arrays.asList("John", "johnnybravo@mail.com"),
                    Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                    Arrays.asList("Mary", "mary@mail.com")
            ));
    List<List<String>> output2 = Collections.unmodifiableList(
            Arrays.asList(
                    Arrays.asList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                    Arrays.asList("John", "johnnybravo@mail.com"),
                    Arrays.asList("Mary", "mary@mail.com")
            )
    );

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{input1, output1},
                new Object[]{input2, output2}
        };
    }

    @Test(dataProvider = "examples")
    public void testAccountsMerge(List<List<String>> accounts, List<List<String>> output) {
        AccountsMerge am = new AccountsMerge();
        Assert.assertTrue(Utils.compareListsIgnoreOrder(am.accountsMerge(accounts), output));
    }

    @Test(dataProvider = "examples")
    public void testAccountsMerge2(List<List<String>> accounts, List<List<String>> output) {
        AccountsMerge am = new AccountsMerge();
        Assert.assertTrue(Utils.compareListsIgnoreOrder(am.accountsMerge2(accounts), output));
    }

    @Test(dataProvider = "examples")
    public void testAccountsMerge3(List<List<String>> accounts, List<List<String>> output) {
        AccountsMerge am = new AccountsMerge();
        Assert.assertTrue(Utils.compareListsIgnoreOrder(am.accountsMerge3(accounts), output));
    }
}