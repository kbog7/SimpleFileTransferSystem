import java.net.*; 
import java.io.*; 
import java.io.File;
import java.nio.file.Files;  
import java.security.*;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.math.BigInteger;
public class Server implements Runnable
{ 
    //initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
    private int portNum;
    BigInteger e = new BigInteger("8889000968882987271174368731036012239033597209053894568054807034214994888266234155243513357496820111772864466020415036269927102942733893645361174930543693");
    BigInteger d = new BigInteger("8291428037619574331657076315567391520386083374509575115392631404397844890688702299593413619173771748490286331104870582406359815004099775584107990268188521916591697095129750187710029136647951734153140454946855455645654276813581564002335243266997752692443609002657136123115156246845440067214872570086198077951760216589223806582961715312236401737014762936665592122992211864792171220345657234211689848277403150749568906403910555320558085083748280527952119576680258058409849186899567286403381566854174051903137472666306666335929276493054775703045748645282777235142954586105610633397482743748775249273918635832132277507333");
    BigInteger N = new BigInteger("11535907797199732835359210871704886391660833121153450090414783601133483474064101188019429953479146340133368498975443797107307196356745536741222921628054855933080990493945752403590587371235447299665605922516925704950592437055526835515375405960296118527620009980135365805174099874752737373541858478234975975772287218235655646455852014604814183591078279236541269478913965846416621349624882947368176889066091372665291492249727179397798306877174491694335367630948103923797042436792643938157135495784391740389155176474947440977588631973296783108211062705991089291948182803799070163305315935550434197740326886399575888952511");
   
  public Server(Socket socket){
        this.socket=socket;
  }
    // constructor with port 
    public void Server1(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
           // server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            //socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
  
            
            RSA rsa = new RSA(e,d,N);
            // reads message from client until "Over" is sent 
            String clientString;
            int l = in.readInt();
            byte[] mcdb = new byte[l];
            in.readFully(mcdb,0,l);
            byte[] decC = (rsa.decrypt(mcdb));
            String cmd1 = new String(decC);
            System.out.println("Client string receieved is:"+cmd1);

            File tmpFile = new File(cmd1);
            boolean exists = tmpFile.exists();

            if(exists == true){
                byte[] bytes = Files.readAllBytes(tmpFile.toPath());
                int numBytes = bytes.length;
              //  String sBytes = Integer.toString(numBytes);
                System.out.println("sending the number of bytes: " + numBytes);
                out.writeInt(numBytes);

                System.out.println("sending the bytes");
                out.write(rsa.encrypt(bytes));
            }
            else{
                out.writeInt(0);
                String nf = "not found";
                byte[] NF = rsa.encrypt(nf.getBytes());
                out.writeInt(NF.length);
                out.write(NF, 0, NF.length);
            }
            System.out.println("Closing connection"); 
  
            // close connection 
            socket.close(); 
            in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
    public void run(){
        
            Server1(4445);
       
    }

} 