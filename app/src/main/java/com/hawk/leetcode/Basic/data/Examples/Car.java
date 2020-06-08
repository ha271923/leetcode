package com.hawk.leetcode.Basic.data.Examples;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

public class Car {
    LocalDateTime enter;
    LocalDateTime leave;
    String name;
    int cc;
    public Car(String name, int cc) {
        this.name = name;
        this.cc = cc;
    }

    public void onEnter(PriorityQueue<Car> park) {
        System.out.println("+ "+this.name + "  cc="+this.cc);
        park.offer(this);
    }

    public void onLeave(PriorityQueue<Car> park) {
        System.out.println("- "+this.name + "  cc="+this.cc);
        park.remove(this);
    }

    public int getCarType() {
        return cc;
    }
}
