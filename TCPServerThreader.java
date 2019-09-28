import java.io.*;  
import java.net.*;
import java.security.*;
import java.util.*;

TCPServerThreader{

    public static void main (String[] args) throws IOException{

        InetAddress ipv6Addr = InetAddress.getByName("fe80::70a7:66ab:db86:37db");
        ServerSocket serverSocket1 = new ServerSocket(6666,5, ipv6Addr);
    
        while(true){
            Socket clientSock = serverSocket1.accept();
            Thread thread = new Thread(new serverProtocol(clientSock));
            thread.start();
        }

    }
}