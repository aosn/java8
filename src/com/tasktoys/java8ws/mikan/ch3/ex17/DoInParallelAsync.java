/*
 * Copyright(C) 2014-2015 Java 8 Workshop participants. All rights reserved.
 * https://github.com/aosn/java8
 */

package com.tasktoys.java8ws.mikan.ch3.ex17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @author mikan
 */
public class DoInParallelAsync {

    private DoInParallelAsync() {
        //static use only
    }

    public static void main(String[] args) {
        doInParallelAsync(() -> {
                    System.out.println("[1st] begin at thread " + Thread.currentThread().getId());
                    IntStream.range(0, Integer.MAX_VALUE).average();
                    System.out.println("[1st] end");
                },
                () -> {
                    System.out.println("[2nd] begin at thread " + Thread.currentThread().getId());
                    IntStream.range(0, Integer.MAX_VALUE).average();
                    System.out.println("[2nd] end");
                }, (t) -> {
                    System.err.println("Handled: " + t.getMessage());
                });
    }

    public static <T> void doInParallelAsync(Runnable first, Runnable second, Consumer<? super Throwable> handler) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(() -> {
            try {
                first.run();
            } catch (Throwable t) {
                handler.accept(t);
            }
        });
        service.submit(() -> {
            try {
                second.run();
            } catch (Throwable t) {
                handler.accept(t);
            }
        });
        service.shutdown();
    }
}
