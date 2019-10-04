import java.io.*;  
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger;
public class ServerThreader{

	public static void main(String[] args) throws IOException{

	ServerSocket servSocket = new ServerSocket(4445);



	while(true){
		Socket clntSock = servSocket.accept();
		Thread thread = new Thread(new Server(clntSock));
		thread.start();
		System.out.println("Created and started Thread " + thread.getName());
	}


	}

}