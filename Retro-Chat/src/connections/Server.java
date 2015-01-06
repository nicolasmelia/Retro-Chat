package connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class Server extends Thread  {
	// The server socket
	public ServerSocket serverSocket;
	// Client connected to
	public Socket client;
	
    private int port;
    public static boolean session = true;

    public Server(int port) {
        this.port = port;
    }
  
    public void run() {
    	// This method looks for messages from the client and runs 
    	// on a background thread
    	readMessage();
    }
    
   
    public void startServer()  {
        System.out.println("Starting the socket server at port: " + port);
        getPublicIp();
        try {
			serverSocket = new ServerSocket(port);
	        System.out.println("Waiting for clients...");
	        client = serverSocket.accept();
	        System.out.println("Success: client connected");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void readMessage () {
        // Read from client
        while (client.isConnected() && session == true ){
	    	String clientText = null;
	    	 try {
	    		 BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));		
				 clientText = reader.readLine();
		    	 System.out.println(clientText);
			} catch (IOException e) {
				session = false;
			    System.out.println("Connection Lost...");
			}   
	    }
    } 

    public void writeMessage(String message, String userName) throws IOException {
    	PrintStream ps = new PrintStream(client.getOutputStream());
    	ps.println(userName + ": " + message); 
    	ps.flush();
        }
    
	
	public void getPublicIp() {
		try {
		URL whatismyip = new URL("http://checkip.amazonaws.com/");
		BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
		String ip = in.readLine(); //you get the IP as a String
        System.out.println("Server Public IP: " + ip);
		} catch (Exception ex) {
			// Nothing	
		}
		
	}

}
	
	
	
	

