package connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread {
	
	private String hostname;
    private int port;
    public Socket socket;
    public static boolean session = true;

    public Client(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }
    
    public void run() {
    	// This method looks for messages from the server and runs 
    	// on a background thread
    	 readMessage();
    }

    public void connectToServer() {
        System.out.println("Attempting to connect to "+hostname+":"+port);
        
			try {
	        	// Open the socket between the server and the client
				socket = new Socket(hostname,port);
			    System.out.println("Success: Connected to Server");
			} catch (Exception e) {
			    System.out.println("Server not found. Program Terminated.");
			}
    }
    
    public void readMessage() {
       	while (socket.isConnected() && session == true) {
	        String userInput = null;
	        try {
	            BufferedReader stdIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));        
				userInput = stdIn.readLine();
		        System.out.println(userInput);
			} catch (IOException e) {
				session = false;
			    System.out.println("Connection Lost...");

			} 
       	} 
    }       

    public void WriteMessage(String message, String userName) {
    	PrintStream ps = null;
		try {
			ps = new PrintStream(socket.getOutputStream());
		   	ps.println(userName + ": " +  message); 
	    	ps.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	
	
	
	
	

}
