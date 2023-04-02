package com.jxa670.ex1;


import java.io.*;


// Repeatedly reads recipient's nickname and text from the user in two
// separate lines, sending them to the server (read by ServerReceiver
// thread).

public class ClientSender extends Thread {

  private String nickname; //eg Helen
  private PrintStream server; 

  ClientSender(String nickname, PrintStream server) {
    this.nickname = nickname;
    this.server = server;
  }
  
  static boolean c_running = true;

  public void run() {
    // So that we can use the method readLine: --> to read from keyboard
    BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

    try {
      // Then loop forever sending messages to recipients via the server:
      while (c_running) { //while loop only runs when c_running is true
    	  				 
        String recipient = user.readLine(); //Read 
        String text = user.readLine();		//Read        
        
        if(recipient.contentEquals("quit")) { //checks if user types quit
        	stopClient(); //calls stopClient method
        } else {
       
        server.println(recipient); // Matches CCCCC in ServerReceiver
        server.println(text);      // Matches DDDDD in ServerReceiver
        }
       }
    }
    
    catch (IOException e) {
      Report.errorAndGiveUp("Communication broke in ClientSender" 
                        + e.getMessage());
    }
    }
  
    public void stopClient() {
   	 c_running = false; //breaks the loop
   	 ClientTable.remove(nickname);
   	}  
}

/*

What happens if recipient is null? Then, according to the Java
documentation, 'print ln' will send the string "null" (not the same as
null!). So maybe we should check for that case! Particularly in
extensions of this system.

 */
