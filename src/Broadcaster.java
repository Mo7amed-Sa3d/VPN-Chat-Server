import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Broadcaster extends Thread{
    private ArrayList<Socket> clients;
    private Socket current;

    Broadcaster(ArrayList<Socket> temp, Socket curr){
        this.current = curr;
        this.clients = temp;
    }

    public void run(){
        String s = new String();
        Scanner input = null;
        try {
            input = new Scanner(this.current.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(!s.equals("STOP")){
            s = input.nextLine();
            for(Socket client : clients){
                if(client == this.current) continue;
                try {
                    DataOutputStream out = new DataOutputStream(client.getOutputStream());
                    out.write((s + "\n").getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
//its job is to receive messages from the input stream from each client and then resend them to all clients.