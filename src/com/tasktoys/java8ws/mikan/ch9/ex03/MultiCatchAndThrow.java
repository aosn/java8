/*
 * Copyright(C) 2014 Java 8 Workshop participants. All rights reserved.
 * https://github.com/Java8Workshop/About
 */
package com.tasktoys.java8ws.mikan.ch9.ex03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mikan
 */
public class MultiCatchAndThrow {

    private static final Logger LOG = Logger.getLogger(MultiCatchAndThrow.class.getName());
    private static int sw = 0;

    public static void main(String[] args) {
        sw = 1;
        try {
            new MultiCatchAndThrow().process();
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, "FNFE", ex);
        } catch (UnknownHostException ex) {
            LOG.log(Level.SEVERE, "UHE", ex);
        }
        try {
            new MultiCatchAndThrow().process2();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "IOE", ex);
        }
    }

    public void process() throws FileNotFoundException, UnknownHostException {
        try {
            if (sw > 0) {
                throw new FileNotFoundException();
            } else {
                throw new UnknownHostException();
            }
        } catch (FileNotFoundException | UnknownHostException e) {
            throw e;
        }
    }
    //    Exception table:
    //       from    to  target type
    //           0    22    22   Class java/io/FileNotFoundException
    //           0    22    22   Class java/net/UnknownHostException
    //    LineNumberTable:
    //      line 40: 0
    //      line 41: 6
    //      line 43: 14
    //      line 45: 22
    //      line 46: 23
    //    LocalVariableTable:
    //       Start  Length  Slot  Name   Signature
    //          23       2     1     e   Ljava/io/IOException; ★ IOE になってるね！
    //           0      25     0  this   Lcom/tasktoys/java8ws/mikan/ch9/ex03/MultiCatchAndThrow;

    public void process2() throws IOException {
        try {
            if (sw > 0) {
                throw new FileNotFoundException();
            } else {
                throw new UnknownHostException();
            }
        } catch (FileNotFoundException | UnknownHostException e) {
            throw e;
        }
    }
}
