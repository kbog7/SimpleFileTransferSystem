import java.io.*;  
import java.net.*;  
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;  
import java.security.*;

public class client {
    public static void main(String[] args) throws IOException {

        
        //Create the client socket
        int servPort = 6666;
        Socket socket = new Socket("ENTER IPv6 here", servPort); //matrix.csc.villanova.edu
        System.out.println("connecting to server...");

        //Create stream processing IO objects

		DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        //Send the command line arg to the server via the socket output stream
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the file path of the file you want to download dummy");
        String cmd = scan.next();
        scan.close();
        out.writeUTF(cmd);

    }

}