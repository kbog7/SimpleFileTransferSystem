import java.io.*;
import java.io.File;
import java.nio.file.Files;  
import java.net.*;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class serverProtocol implements Runnable{
    private Socket clntSock;

    public serverProtocol(Socket clntSock){
        this.clntSock = clntSock;
    }

    public static void handleMsg(Socket clntSock) throws IOException {

        ServerSocket ss = new ServerSocket(6666);
        String nf = "file not found";
        while (true){
            clntSock = ss.accept();
         DataInputStream in  = new DataInputStream(clntSock.getInputStream());
         DataOutputStream out = new DataOutputStream(clntSock.getOutputStream());

            SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
            System.out.println("waiting for client string");
            String clientString = in.readUTF();
            System.out.println("Client string receieved is:"+clientString);
            File tmpFile = new File(clientString);
            boolean exists = tmpFile.exists();
            if(exists == true){
                byte[] bytes = Files.readAllBytes(tmpFile.toPath());
                int numBytes = bytes.length;
                System.out.println("sending the number of bytes: " + numBytes);
                out.writeInt(numBytes);
                System.out.println("sending the bytes");
                out.write(bytes);
            }
            else{
                out.writeUTF(nf);
            }

        }
    }
    public void run(){
        try{
            handleMsg(clntSock);
        }catch(IOException e){}
    }

}