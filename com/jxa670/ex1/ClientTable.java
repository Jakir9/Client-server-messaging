package com.jxa670.ex1;

// Each nickname has a different incoming-message queue.

import java.util.concurrent.*;

public class ClientTable {

  private static ConcurrentMap<String,BlockingQueue<Message>> queueTable
    = new ConcurrentHashMap<String,BlockingQueue<Message>>();
  							//Key , Value  assigned to key
  
  							//The keys are strings and value will be
  							//Messages from the blocking queue
  //A Map is a table in java
  //Concurrent Hash maps avoid races
  

  // The following overrides any previously existing nickname, and
  // hence the last client to use this nickname will get the messages
  // for that nickname, and the previously existing clients with that
  // nickname won't be able to get messages. Obviously, this is not a
  // good design of a messaging system. So I don't get full marks:

  public void add(String nickname) { //Table of queue is a Queue table
    queueTable.put(nickname, new LinkedBlockingQueue<Message>());
  }
  
  public static void remove(String nickname) { //remove user from client table
	queueTable.remove(nickname);
  }
  
  // Returns null if the nickname is not in the table:
  public BlockingQueue<Message> getQueue(String nickname) {
    return queueTable.get(nickname);
  }
}
