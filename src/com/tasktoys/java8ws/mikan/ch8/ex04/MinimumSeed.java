/*
 * Copyright(C) 2014-2015 Java 8 Workshop participants. All rights reserved.
 * https://github.com/aosn/java8
 */

package com.tasktoys.java8ws.mikan.ch8.ex04;

import java.math.BigInteger;
import java.util.Random;
import java.util.stream.LongStream;

/**
 * @author mikan
 */
public class MinimumSeed {

    // next(S) = (S * M + A) mod N
    private static long M = 25214903917L; // 0xfdeece66d
    private static long A = 11; // 0xb
    private static long N = (long) Math.pow(2, 48); // 2^48

    // prev(S) = (S - A) * V mod N
    private static long V = 246154705703781L;

    public static void main(String[] args) {
        long min = createPrevStream(10000000).map(s -> s ^ M).min().getAsLong();
        Random generator = new Random(min);
        int count = 0;
        double current;
        do {
            current = generator.nextDouble();
            count++;
        } while (current != 0);
        System.out.println("Found ZERO in " + count + " tries.");
        // Result: Found ZERO in 376050 tries.
    }

    public static long next(long seed) {
        // return (seed * M + A) % N;
        return BigInteger.valueOf(seed)
                .multiply(BigInteger.valueOf(M))
                .add(BigInteger.valueOf(A))
                .mod(BigInteger.valueOf(N))
                .longValue();
    }

    public static long prev(long seed) {
        // return (seed - A) * V % N;
        return BigInteger.valueOf(seed)
                .subtract(BigInteger.valueOf(A))
                .multiply(BigInteger.valueOf(V))
                .mod(BigInteger.valueOf(N))
                .longValue();
    }

    private static LongStream createPrevStream(int size) {
        return LongStream.iterate(prev(0), MinimumSeed::prev).limit(size);
    }
}
