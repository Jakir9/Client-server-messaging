package com.jxa670.ex1;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

// Gets messages from client and puts them in a queue, for another
// thread to forward to the appropriate client.

public class ServerReceiver extends Thread {
  private String myClientsName;
  private BufferedReader myClient;
  private ClientTable clientTable;
  static boolean s_running = true; //boolean created
  
  public ServerReceiver(String n, BufferedReader c, ClientTable t) {
    myClientsName = n;
    myClient = c;
    clientTable = t;
  }
  

  public void run() {
    try {
      while (s_running) {
    	  
    	String recipient = myClient.readLine(); // Matches CCCCC in ClientSender.java
        String text = myClient.readLine();      // Matches DDDDD in ClientSender.java
        
        if (recipient != null && text != null) { 
        	
    	if(!(recipient.contentEquals("quit"))) {
        Message msg = new Message(myClientsName, text); //creates message paul, "yes" 
         
         
          BlockingQueue<Message> recipientsQueue 
            = clientTable.getQueue(recipient); // Matches EEEEE in ServerSender.java
          
          if (recipientsQueue != null) { //Message is put in queue
            recipientsQueue.offer(msg);  //Message is put in queue
          } else {
            Report.error("Message for unexistent client "//if unsuccessful
                         + recipient + ": " + text); //error message is 
                                                    //printed   
          }
    	} else {
    		s_running = false; //if recipient contains "quit" , s_running = false, thus while loop terminates
    	}
       } else {
          // No point in closing socket. Just give up.
          return;
        }
      
      }
    }
      
    catch (IOException e) {
      Report.error("Something went wrong with the client " 
                   + myClientsName + " " + e.getMessage()); 
      // No point in trying to close sockets. Just give up.
      // We end this thread (we don't do System.exit(1)).
    }
  }
}