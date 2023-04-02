package com.jxa670.ex1;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

// Continuously reads from message queue for a particular client,
// forwarding to the client.

public class ServerSender extends Thread {
  private BlockingQueue<Message> clientQueue;
  private PrintStream client;

  public ServerSender(BlockingQueue<Message> q, PrintStream c) { 
    clientQueue = q;   //reads message from this queue
    client = c;		   // c tells where messages has to be sent to
    				   //element of blocking queue is Message's
  }

  public void run() {
    while (ServerReceiver.s_running) { //Server Loop -> Reads from queue and sends & keeps repeating unless c_running is false
      try {            
    	  			   //Takes message from queue, waits 
    	  			   //forever if no message in the queue
        Message msg = clientQueue.take(); // Matches EEEEE in ServerReceiver
        client.println(msg); // Matches FFFFF in ClientReceiver
      }
      catch (InterruptedException e) {
        // Do nothing and go back to the infinite while loop.
      }
    }
  }
}

/*

 * Throws InterruptedException if interrupted while waiting

 * See https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/BlockingQueue.html#take--

 */
