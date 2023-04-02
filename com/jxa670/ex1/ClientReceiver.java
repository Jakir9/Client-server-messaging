package com.jxa670.ex1;

import java.io.*;
import java.net.*;

// Gets messages from other clients via the server (by the
// ServerSender thread).

public class ClientReceiver extends Thread {

  private BufferedReader server;

  ClientReceiver(BufferedReader server) { 
    this.server = server;
  }
  
  public void run() {
	  
    // Print to the user whatever we get from the server:
    try {
    	
      while (ClientSender.c_running) { //loop repeats until c_running is false
    	  	
    	  //Reads from server
        String s = server.readLine(); // Matches FFFFF in ServerSender.java
        if (s != null)                
          System.out.println(s);
        else
          Report.errorAndGiveUp("Server seems to have died");
       
      }
    }
    catch (IOException e) {
      Report.quitting("Disconnected from Server");
 
    }
    
  }
  
} 
 

/*

 * The method readLine returns null at the end of the stream

 * It may throw IoException if an I/O error occurs

 * See https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html#readLine--


 */
