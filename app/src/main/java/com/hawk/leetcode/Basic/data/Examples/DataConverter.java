package com.hawk.leetcode.Basic.data.Examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataConverter {

    public static void main(String[] args) {
        intArrayToList();
    }

    public static void intArrayToList() {
        int[] data = {4, 5, 3, 6, 2, 5, 1};

        // A. int[] 轉 List<Integer>
        List<Integer> list1 = Arrays.stream(data).boxed().collect(Collectors.toList());
        // Arrays.stream(arr) 可以替換成IntStream.of(arr)。
        // 1.使用Arrays.stream將int[]轉換成IntStream。
        // 2.使用IntStream中的boxed()裝箱。將IntStream轉換成Stream<Integer>。
        // 3.使用Stream的collect()，將Stream<T>轉換成List<T>，因此正是List<Integer>。

        // B. int[] 轉 Integer[]
        Integer[] integers1 = Arrays.stream(data).boxed().toArray(Integer[]::new);
        // 前兩步同上，此時是Stream<Integer>。
        // 然後使用Stream的toArray，傳入IntFunction<A[]> generator。
        // 這樣就可以返回Integer數組。
        // 不然默認是Object[]。

        // C. List<Integer> 轉 Integer[]
        Integer[] integers2 = list1.toArray(new Integer[0]);
        //  調用toArray。傳入參數T[] a。這種用法是目前推薦的。
        // List<String>轉String[]也同理。

        // D. List<Integer> 轉 int[]
        int[] arr1 = list1.stream().mapToInt(Integer::valueOf).toArray();
        // 想要轉換成int[]類型，就得先轉成IntStream。
        // 這裏就通過mapToInt()把Stream<Integer>調用Integer::valueOf來轉成IntStream
        // 而IntStream中默認toArray()轉成int[]。

        // E. Integer[] 轉 int[]
        int[] arr2 = Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray();
        // 思路同上。先將Integer[]轉成Stream<Integer>，再轉成IntStream。

        // F. Integer[] 轉 List<Integer>
        List<Integer> list2 = Arrays.asList(integers1);
        // 最簡單的方式。String[]轉List<String>也同理。

        // A2. String同理, String[] 轉 List<String>
        String[] strings1 = {"a", "b", "c"};
        // String[] 轉 List<String>
        List<String> list3 = Arrays.asList(strings1);
        // List<String> 轉 String[]
        String[] strings2 = list3.toArray(new String[0]);

    }

}
