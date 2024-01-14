import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class NinjaClient {

    public static void main(String[] args){
        Socket portal;
        DataInputStream portalIn;
        DataOutputStream portalOut;

        try{
            portal = new Socket("154.177.31.206", 30414);   //Enter the address of the VPN server.
            System.out.println("Connected.");
            portalIn = new DataInputStream(portal.getInputStream());
            portalOut = new DataOutputStream(portal.getOutputStream());

            Sender to = new Sender(portalOut, "Anon:");
            Receiver from = new Receiver(portalIn);

            System.out.println("Enter the IP address followed by the port number of the destination: ");
            Scanner in = new Scanner(System.in);
            portalOut.write((in.nextLine()+"\n").getBytes());
            to.start();
            from.start();
        }catch (IOException i) {
            System.out.println(i);
        }
    }
}