package main;




import connections.Client;
import connections.Server;
 
public class Main {
	
	static String userName;
	static String choice = null;
	
	public static void main(String[] args) {
			// TODO Auto-generated method stub
		System.out.println("Welcome to Retro-Chat!");

		System.out.println("Set up your machine as a host(Server) or client.");
		System.out.println("1 for server\n2 for client");    
        System.out.print("Enter Selection: ");
        
        // Get option from user
        while (choice == null) {
	    choice =  System.console().readLine().toString().trim();
        }
        
		System.out.print("Enter a Username: ");
		userName =  System.console().readLine().toString().trim();
	    
	    if (choice.trim().matches("1")) {
	    
			Server server = new Server(4440);
			server.startServer();
			// Starts another thread to look for messages from client
			server.start();
		
			System.out.println("Chat session active: Type below");
	
			while (Server.session) {
			    try {
					String message =  System.console().readLine().toString();
			    	server.writeMessage(message, userName);
			    } catch (Exception ex) {}
			}
		
	    } else if (choice.trim().matches("2"))  {
	    	
	    	System.out.print("Enter the servers IP address: ");
		    String ipAddress =  System.console().readLine().toString().trim();
	
	    	
	    	// If user chooses to be the client
	    	Client client = new Client(ipAddress,4440);
	    	client.connectToServer();
			// Starts another thread to look for messages from server
	    	client.start();
	    	
			System.out.println("Chat session active: Type below");
	    	
	    	while (Client.session) {
	    		try  {
	    		    String message =  System.console().readLine().toString();
	    			client.WriteMessage(message, userName);
	    		} catch (Exception ex) {}
	    	} 
	 	
	    	System.out.println("Connection to server has been lost.");
		
	    } else {
	    	// If user does not select a server ot client
	    	System.out.println("You have not entered a valid choice. Program Terminating. ");
	    }
	    
	}
	
	
	
	
	

}