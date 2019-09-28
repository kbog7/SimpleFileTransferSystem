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
            //clntSock = ss.accept();
         DataInputStream in  = new DataInputStream(clntSock.getInputStream());
         DataOutputStream out = new DataOutputStream(clntSock.getOutputStream());

            SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
            String clientString = in.readUTF();

            File tmpFile = new File(clientString);
            boolean exists = tmpFile.exists();
            if(exists == true){
                byte[] songBytes = Files.readAllBytes(tmpFile.toPath());
                out.write(songBytes);
            }
            else{
                out.writeUTF(nf);
            }

        }
    }

}