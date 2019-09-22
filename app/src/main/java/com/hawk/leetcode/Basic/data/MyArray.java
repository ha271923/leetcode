package com.hawk.leetcode.Basic.data;

import java.util.Arrays;

class MyArray<E> {

    private final Object[] arr;
    public final int length;

    // constructor
    public MyArray(int length)
    {
        // Creates a new Object array of specified length
        arr = new Object[length];
        this.length = length;
    }

    // Function to get Object present at index i in the array
    E get(int i) {
        @SuppressWarnings("unchecked") // 如果你無法消除警告 但你很確定引發警告的代碼是類型安全的 那你只能用@SuppressWarnings("unchecked")註解來抑制警告
        final E e = (E)arr[i];
        return e;
    }

    // Function to set a value e at index i in the array
    void set(int i, E e) {
        arr[i] = e;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}

class Main {
    // Program to create a generic array in Java
    public static void main(String[] args)
    {
        final int length = 5;

        // create an Integer array of given length
        MyArray<Integer> intArray = new MyArray(length);

        for (int i = 0; i < length; i++)
            intArray.set(i, i + 1);

        System.out.println(intArray);

        // create a String array of given length
        MyArray<String> strArray = new MyArray(length);

        for (int i = 0; i < length; i++)
            strArray.set(i, String.valueOf((char)(i + 65))); // ASCII: A=64, B=66

        System.out.println(strArray);
    }
}
