/*
 * Copyright(C) 2014-2015 Java 8 Workshop participants. All rights reserved.
 * https://github.com/aosn/java8
 */
package com.tasktoys.java8ws.mikan.ch1.ex02;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author mikan
 */
@RunWith(Theories.class)
public class SubDirectoriesTest {

    @DataPoint
    public static String input = "./";

    @Test(expected = NullPointerException.class)
    public void testGetSubDirectories1_NPE() {
        new SubDirectories().getSubDirectories1(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSubDirectories1_IAE() {
        new SubDirectories().getSubDirectories1("foo");
    }

    @Theory
    public void testGetSubDirectories1_theoryInput(String path) {
        List<File> result = new SubDirectories().getSubDirectories1(path);
        assertNotNull(result);
        result.forEach(f -> assertTrue(f.isDirectory()));
    }

    @Theory
    public void testGetSubDirectories2_theoryInput(String path) {
        List<File> result = new SubDirectories().getSubDirectories2(path);
        assertNotNull(result);
        result.forEach(f -> assertTrue(f.isDirectory()));
    }

    @Theory
    public void testGetSubDirectories3_theoryInput(String path) {
        List<File> result = new SubDirectories().getSubDirectories3(path);
        assertNotNull(result);
        result.forEach(f -> assertTrue(f.isDirectory()));
    }
}
