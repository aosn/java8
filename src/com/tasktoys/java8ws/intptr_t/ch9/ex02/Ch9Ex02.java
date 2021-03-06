package com.tasktoys.java8ws.intptr_t.ch9.ex02;

import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Javaプログラマーなら習得しておきたい Java SE 8 実践プログラム
 * 第9章 練習問題2
 * @author intptr_t
 */
public class Ch9Ex02 {
	public static void main(String[] args) throws Throwable {
		Scanner in = null;
		PrintWriter out = null;
		Throwable secondaryException = null;

		try {
			// in = new Scanner(Paths.get("/usr/share/dict/words"));
			// out = new PrintWriter("/tmp/out.txt");
			in = new Scanner(Paths.get("./README.mdz"));
			out = new PrintWriter("./out/out09-02.md");

			while(in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} catch(Exception e) {
			secondaryException = e;
			System.err.println("exception[1]");
			throw e;
		} finally {
			try {
				if (in != null) { in.close(); }
			} catch(Exception e) {
				if(secondaryException != null) {
					e.addSuppressed(secondaryException);
				}
				secondaryException = e;
				System.err.println("exception[2]");
				throw e;
			} finally {
				try {
					if (out != null){ out.close(); }
				} catch(Exception e) {
					if(secondaryException != null) {
						e.addSuppressed(secondaryException);
					}
					System.err.println("exception[3]");
					throw e;
				}
			}
		}
	}
}
