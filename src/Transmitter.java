import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Transmitter extends Thread{

    private Scanner in;
    private DataOutputStream out;

    Transmitter(DataInputStream from, DataOutputStream to){
        this.in = new Scanner(from);
        this.out = to;
    }

    public void run(){
        String s = new String();
        while(!s.equals("STOP")){
            s = in.nextLine();
            try {
                out.write((s+'\n').getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
