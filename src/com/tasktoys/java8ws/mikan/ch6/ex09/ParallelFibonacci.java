/*
 * Copyright(C) 2014-2015 Java 8 Workshop participants. All rights reserved.
 * https://github.com/aosn/java8
 */

package com.tasktoys.java8ws.mikan.ch6.ex09;

import java.math.BigInteger;
import java.util.Arrays;

/**
 *
 * @author mikan
 */
public class ParallelFibonacci {

    public static long fibonacciAsLong(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + " is zero or minus value.");
        }
        Matrix[] array = new Matrix[n];
        Arrays.parallelSetAll(array, i -> Matrix.of(1, 1, 1, 0));
        Arrays.parallelPrefix(array, Matrix::multiply);
        return array[n - 1].at(0).longValue();
    }

    public static BigInteger fibonacciAsBigInteger(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + " is zero or minus value.");
        }
        Matrix[] array = new Matrix[n];
        Arrays.parallelSetAll(array, i -> Matrix.of(
                BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(0)));
        Arrays.parallelPrefix(array, Matrix::multiply);
        return (BigInteger) array[n - 1].at(0).get();
    }

    private ParallelFibonacci() {
        // static use only
    }
}
