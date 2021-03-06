/*
 * Copyright(C) 2014-2015 Java 8 Workshop participants. All rights reserved.
 * https://github.com/aosn/java8
 */
package com.tasktoys.java8ws.mikan.ch2.ex08;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author mikan
 */
public class Zip {

    private static final boolean parallel = false;

    private Zip() {
        // static use only
    }

    /**
     * Zip two streams.
     *
     * @param first non-{@code null} first stream
     * @param second non-{@code null} second stream
     * @param <T> type of elements
     * @return non-{@code null} zipped stream
     */
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        // Create a "spliterator" (Split-iterator).
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                new ZipIterator<>(first.iterator(), second.iterator()),
                Spliterator.ORDERED | Spliterator.IMMUTABLE | Spliterator.NONNULL), parallel);
    }

    private static class ZipIterator<T> implements Iterator<T> {

        private final AtomicBoolean firstTurn = new AtomicBoolean(true);
        private final Iterator<T> first;
        private final Iterator<T> second;

        public ZipIterator(Iterator<T> first, Iterator<T> second) {
            Objects.requireNonNull(first);
            Objects.requireNonNull(second);
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean hasNext() {
            return first.hasNext() || second.hasNext();
        }

        @Override
        public T next() {
            return firstTurn.getAndSet(!firstTurn.get()) ?
                    (first.hasNext() ? first.next() : second.next()) :
                    (second.hasNext() ? second.next() : first.next());
        }
    }
}
