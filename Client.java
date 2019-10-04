import java.net.*; 
import java.io.*; 
import java.math.BigInteger;
public class Client 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  in  = null; 
    private DataOutputStream out     = null; 
   BigInteger e = new BigInteger("8889000968882987271174368731036012239033597209053894568054807034214994888266234155243513357496820111772864466020415036269927102942733893645361174930543693");
   BigInteger d = new BigInteger("8291428037619574331657076315567391520386083374509575115392631404397844890688702299593413619173771748490286331104870582406359815004099775584107990268188521916591697095129750187710029136647951734153140454946855455645654276813581564002335243266997752692443609002657136123115156246845440067214872570086198077951760216589223806582961715312236401737014762936665592122992211864792171220345657234211689848277403150749568906403910555320558085083748280527952119576680258058409849186899567286403381566854174051903137472666306666335929276493054775703045748645282777235142954586105610633397482743748775249273918635832132277507333");
   BigInteger N = new BigInteger("11535907797199732835359210871704886391660833121153450090414783601133483474064101188019429953479146340133368498975443797107307196356745536741222921628054855933080990493945752403590587371235447299665605922516925704950592437055526835515375405960296118527620009980135365805174099874752737373541858478234975975772287218235655646455852014604814183591078279236541269478913965846416621349624882947368176889066091372665291492249727179397798306877174491694335367630948103923797042436792643938157135495784391740389155176474947440977588631973296783108211062705991089291948182803799070163305315935550434197740326886399575888952511");
  
    // constructor to put ip address and port 
    public Client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            in  = new DataInputStream(socket.getInputStream()); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
           
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String cmd = "/mnt/c/Users/David/Documents/MFA19/cyberPhy/project1final/server/files/test.txt"; 
  
        // keep reading until "Over" is input 
        try{
        RSA rsa = new RSA(e,d,N);
        byte[] cmdb = rsa.encrypt(cmd.getBytes());
        out.writeInt(cmdb.length);
        out.write(cmdb,0,cmdb.length);
        int ret = in.readInt();
        System.out.println("ret ="+ret);
        if(ret != 0){
            byte[] bytes = new byte[ret];
            in.readFully(bytes,0,bytes.length);
           byte[] dbytes = rsa.decrypt(bytes);
        System.out.println("Writing bytes to a file");
        File newFile = new File("/mnt/c/Users/David/Documents/MFA19/cyberPhy/project1final/client/files/newFile.txt");
        OutputStream os = new FileOutputStream(newFile);
        os.write(dbytes);
        System.out.println(newFile);
        }else{
            int len = in.readInt();
            System.out.println(len);
            byte [] nbytes = new byte[len];
            in.readFully(nbytes,0,nbytes.length);
            byte[] decb = rsa.decrypt(nbytes);
            System.out.println(new String(decb));
            System.out.println("Decrypted String: " + new String(decb));
        }
    }
    catch(IOException k){
        System.out.println(k); 
    }
  
        // close the connection 
        try
        { 
            in.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Client client = new Client("localhost", 4445); 
    } 
} 