package com.basic.Comparable;

import java.util.*;
import java.lang.Comparable;
/**
 * @desc "Comparator"和“Comparable”的比較程式。
 *  (01) "Comparable"
 *  它是一個排序interface，只包含一個函式compareTo()。
 *  一個class實現了Comparable介面，就意味著“該類本身支援排序”，它可以直接通過Arrays.sort() 或 Collections.sort()進行排序。
 *
 *  (02) "Comparator"
 *  它是一個比較器interface，包括兩個函式：compare() 和 equals()。
 *  一個class實現了Comparator介面，那麼它就是一個“比較器”。其它的類，可以根據該比較器去排序。
 *
 *  綜上所述：Comparable是內部比較器，而Comparator是外部比較器。
 *  一個class本身實現了Comparable比較器，就意味著它本身支援排序；若它本身沒實現Comparable，也可以通過外部比較器Comparator進行排序。
 */
public class CompareComparatorAndComparableTest {
    public static void main(String[] args) {
        // 新建ArrayList(動態陣列)
        ArrayList<Person> list = new ArrayList<Person>();
        // 新增物件到ArrayList中
        list.add(new Person("ccc", 20));
        list.add(new Person("AAA", 30));
        list.add(new Person("bbb", 10));
        list.add(new Person("ddd", 40));
        // 列印list的原始序列
        System.out.printf("Original sort, list:%s\n", list);

        // 對list進行排序
        // 這裡會根據“Person實現的Comparable<String>介面”進行排序，即會根據“list name”進行排序
        Collections.sort(list);
        System.out.printf("Name   sort, list:%s\n", list);

        // 通過“比較器(AscAgeComparator)”，對list age進行升序排序
        Collections.sort(list, new AscAgeComparator());
        System.out.printf("Asc(age) sort, list:%s\n", list);

        // 通過“比較器(DescAgeComparator)”，對list age進行降序排序
        Collections.sort(list, new DescAgeComparator());
        System.out.printf("Desc(age) sort, list:%s\n", list);

        // 判斷兩個person是否相等
        testEquals(new Person("eee", 100), new Person("eee", 100));
    }
    /**
     * @desc 測試兩個Person比較是否相等。
     *  由於Person實現了equals()函式：若兩person的age、name都相等，則認為這兩個person相等。
     *  所以，這裡的p1和p2相等。
     *
     *  TODO：若去掉Person中的equals()函式，則p1不等於p2
     */
    private static void testEquals(Person p1, Person p2) {
        if (p1.equals(p2))
            System.out.printf("%s EQUAL %s\n", p1, p2);
        else
            System.out.printf("%s NOT EQUAL %s\n", p1, p2);
    }
    /**
     * @desc Person類。
     *    Person實現了Comparable介面，這意味著Person本身支援排序
     */
    private static class Person implements Comparable<Person>{
        int age;
        String name;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
        public String toString() {
            return name + " - " + age;
        }
        /**
         * 比較兩個Person是否相等：若它們的name和age都相等，則認為它們相等
         */
        boolean equals(Person person) {
            if (this.age == person.age && this.name == person.name)
                return true;
            return false;
        }
        /**
         * @desc 實現 “Comparable<String>” 的介面，即重寫compareTo<T t>函式。
         * 這裡是通過“person的名字”進行比較的
         */
        @Override
        public int compareTo(Person person) {
            return name.compareTo(person.name);
            //return this.name - person.name;
        }
    }
    /**
     * @desc AscAgeComparator比較器
     *    它是“Person的age的升序比較器”
     */
    private static class AscAgeComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getAge() - p2.getAge();
        }
    }
    /**
     * @desc DescAgeComparator比較器
     *    它是“Person的age的升序比較器”
     */
    private static class DescAgeComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p2.getAge() - p1.getAge();
        }
    }
}
