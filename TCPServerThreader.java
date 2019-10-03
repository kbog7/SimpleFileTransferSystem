import java.io.*;  
import java.net.*;
import java.security.*;
import java.util.*;

public class TCPServerThreader{

    public static void main (String[] args) throws IOException{

        InetAddress ipv6Addr = InetAddress.getByName("127.0.0.1");//fdde:ad00:beef:0:a3d3:5255:6161:64dd
        ServerSocket serverSocket1 = new ServerSocket(6666);//,5, ipv6Addr);
    
        while(true){
            Socket clientSock = serverSocket1.accept();
            Thread thread = new Thread(new serverProtocol(clientSock));
            thread.start();
        }

    }
}