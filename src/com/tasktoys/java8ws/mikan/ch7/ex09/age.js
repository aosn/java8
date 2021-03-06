/*
 * Copyright(C) 2014-2015 Java 8 Workshop participants. All rights reserved.
 * https://github.com/aosn/java8
 */

var System = java.lang.System;
var Scanner = java.util.Scanner;

if (arguments.length == 1) {
  print("Next year, you will be " + (arguments[0] - - 1)); // operator trick (+ is used by string)
} else if ("AGE" in $ENV) {
  print("Next year, you will be " + ($ENV.AGE - - 1));
} else {
  print("Input your age[0-32767]: ");
  var scanner = new Scanner(System.in);
  if (scanner.hasNextShort()) {
    var age = scanner.nextShort();
    if (age >= 0) {
      print("Next year, you will be " + (age + 1)); // JS Number + 1 (not overflow)
    } else {
      print("Oops! Are you a human?");
    }
  } else {
    print("Invalid.");
  }
}
