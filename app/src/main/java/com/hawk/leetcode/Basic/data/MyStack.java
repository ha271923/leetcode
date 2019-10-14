package com.hawk.leetcode.Basic.data;

import java.util.Stack;
import java.util.*;

/**
 * push   ==> 把物件壓入堆疊(stack)空間頂部，並傳回新的堆疊。
 * pop    ==> 移除堆疊(stack)空間頂部的物件，並作為此函數的值返回該物件傳回新的堆疊。
 * peek   ==> 取出堆疊(stack)最頂部物件。
 * empty  ==> 測試堆疊(stack)空間是否為空的方法。
 * search ==> 在堆疊(stack)空間中尋找項目物件並確定到堆疊(stack)空間頂距離的方法， -1 表示此物件不在堆疊(stack)空間中。
 *
 *         search
 * push -->      --> pop
 *         | 9 |<-- peak
 *         | 8 |
 *         |_4_|
 *         empty
 *
 */
public class MyStack<E> extends Stack<E>{

    //add or push element on the top of the stack
    void push_method(E n) {
        E ret = this.push(n);
        System.out.println("PUSH: push(" +n+ ")   ret="+ret);
        System.out.println("Current Stack: " + this);
    }
    // Display element on the top of the stack
    void peek_method()
    {
        E ret = this.peek();
        System.out.println("PEAK: Element on stack top : " + ret);
    }
    // Searches element in the stack
    void search_method(E element)
    {
        int pos = this.search(element);

        if(pos == -1)
            System.out.println("SEARCH: Element not found");
        else
            System.out.println("SEARCH: Element is found at position " + pos);
    }
    // Removes element from the top of the stack
    void pop_method() {
        E ret = this.pop();
        System.out.println("POP: push()   ret=" +ret);
        System.out.println("Current Stack: " + this);
    }

    static MyStack<Integer> mStack = new MyStack();
    public static void main(String args[]) {

        System.out.println("Empty stack: " + mStack);
        mStack.push_method(4);
        mStack.push_method(8);
        mStack.push_method(9);
        mStack.peek_method();
        mStack.search_method(2);
        mStack.search_method(4);
        mStack.pop_method();
        mStack.pop_method();
        mStack.pop_method();
        try {
            mStack.pop_method();
        } catch (EmptyStackException e) {
            System.out.println("empty stack");
        }
    }
}
