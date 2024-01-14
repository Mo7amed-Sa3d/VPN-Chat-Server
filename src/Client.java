import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args){
        Socket portal;
        DataInputStream portalIn;
        DataOutputStream portalOut;
        String prefix;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a prefix for your messages: ");
        prefix = in.next();

        try{
            portal = new Socket("154.177.31.206", 30413);
            System.out.println("Connected.");
            portalIn = new DataInputStream(portal.getInputStream());
            portalOut = new DataOutputStream(portal.getOutputStream());

            Sender to = new Sender(portalOut, prefix);
            Receiver from = new Receiver(portalIn);

            to.start();
            from.start();
        }catch (IOException i) {
            System.out.println(i);
        }
    }
}
