package com.hawk.leetcode.Basic.data.Examples;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample2 {
    static int MAX_AVAILABLE = 2;
    static PriorityQueue<Car> pq;

    public static void main(String[] args) {
        Comparator<Car> carComparator = new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) { // KEY: 每次出入停車場時, compare都會自動被PriorityQueue呼叫一次
                System.out.println("["+c1.name + ","+c1.cc  + "]  V.S  "+"["+c2.name + ","+c2.cc+"]");
                return (c1.getCarType() - c2.getCarType()) * 1; // KEY: 升羃排序方程式
            }
        };
        pq = new PriorityQueue<>(MAX_AVAILABLE, carComparator);

        Car carA = new Car("AAA", 3000);
        Car carB = new Car("BBB", 1800);
        Car carC = new Car("CCC", 2000);
        Car carD = new Car("DDD", 1200);

        enterPark(pq, carA);
        enterPark(pq, carB);
        enterPark(pq, carC);
        enterPark(pq, carD);
        leavePark(pq, carB);

        while (!pq.isEmpty()) { // default is alphabets ascending order.
            Car car = pq.remove();
            System.out.println("in Park: "+car.name + "  cc="+car.cc);
        }
    }

    static void enterPark(PriorityQueue<Car> park, Car car) {
        car.onEnter(park);
    }


    static void leavePark(PriorityQueue<Car> park, Car car) {
        car.onLeave(park);
    }
}