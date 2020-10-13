package com.basic.Comparable;

class Person implements Comparable<Person> {
    int age;
    String name;
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