package com.hawk.leetcode;

import android.util.Log;

import com.hawk.leetcode.Exams.BaseClass;

import static com.hawk.leetcode.Global.TAG;

public class DataItem {
    // Sample data set.  children[i] contains the children (String[]) for groups[i].
    public static String[] groups = {
            " 1 ~ 10",    // group-0
            "11 ~ 20",    // group-1
            "21 ~ 30",    // group-2
            "31 ~ 40" };  // group-3
    public static String[][] children = {
            { "TwoSum",   "AddTwoNumbers", "G0_item2", "G0_item3", "G0_item4" }, // items for group-0
            { "G1_item0", "G1_item1", "G1_item2", "G1_item3", "G1_item4" }, // items for group-1
            { "G2_item0", "G2_item1", "G2_item2", "G2_item3", "G2_item4" }, // items for group-2
            { "G3_item0", "G3_item1", "G3_item2", "G3_item3", "G3_item4" }  // items for group-3
    };

    public static boolean findMatchingClass( int groupPos, int childPos) {
        String clazz = children[groupPos][childPos];
        Class cls= null;
        try {
            // Dynamic Programming
            cls = Class.forName("com.hawk.leetcode.Exams." + clazz);
            Log.e(TAG, "Class found = " + cls.getName());
            Log.e(TAG, "Package = " + cls.getPackage());
            Object obj = cls.newInstance();
            ((BaseClass)obj).test();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
        return true;
    }
}
