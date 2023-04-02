https://git.cs.bham.ac.uk/jxa670/SWW-ex1.git


# Solution

1. When user leaves, kill the four threads associated with user, 2 from client side, 2 from server side
2. Also remove client from the queue table
3. Server has to stay online when client quits
4."quit" is used by the client to leave the server

# Implementation Details

(Client.java)
1. check the number of arguments, if one arg, check if arg = quit
	if arg = true then the two infinite client loops as well as 
	the corresponding infinite server loop will end and terminate
	the threads they belong to.

2. if two arguments are entered, do not allow arg[0] to be equal to quit.

3. Do not terminate server treads when client subordinate threads terminate. Only the corresponding ServerSender and ServerReciever thread will terminate along side the Client Sender and Reciever thread.

(ClientTable.java)
4. remove client name from queue table when client quits

(ClientSender.java)
5. Check if recipient (first line) contains "quit", if it does stopClient() is called. stopClient() contains a boolean c_running and sets it to false.
c_running being false causing the while loop in ClientSender.java to terminate.

(ClientReciever.java)
6. When stopClient() is called, while loop terminates as c_running will be set as false.

(ServerReciever.java)
7. if recipient contains "quit" then error message is printed and s_running is set as false, this terminates the while loop in ServerReciever and ServerSender. Else the nickname is added to the client table.

