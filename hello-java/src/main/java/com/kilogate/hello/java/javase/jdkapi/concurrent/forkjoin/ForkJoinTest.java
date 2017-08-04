package com.kilogate.hello.java.javase.jdkapi.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * This program demonstrates the fork-join framework.
 *
 * @author fengquanwei
 * @create 2017/6/6 23:03
 **/
public class ForkJoinTest {
    public static void main(String[] args) {
        final int SIZE = 10000000;
        double[] numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();
        }

        Counter counter = new Counter(numbers, 0, numbers.length, new Filter() {
            @Override
            public boolean accept(double t) {
                return t > 0.5;
            }
        });

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}

