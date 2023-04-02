package com.jxa670.ex1;


import java.io.*;

// This is to handle logging of normal and erroneous behaviours.

public class Report {

  public static void behaviour(String message) {
    System.err.println(message);
  }

  public static void error(String message) { //error
    System.err.println(message);
  }
  
  public static void errorAndGiveUp(String message) { //error and exit

    Report.error(message);
    System.exit(1);
  }
  
  public static void quitting(String message) {
	  System.err.println(message);
  }
  
  public static void invalidName(String message) {
	  System.err.println(message);
  }
  
}
