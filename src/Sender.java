import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Sender extends Thread{
    private DataOutputStream output;
    private Scanner input;
    String prefix;

    Sender(DataOutputStream out, String n){
        this.output = out;
        this.input = new Scanner(System.in);
        this.prefix = n;
    }

    public void run(){
        String s = new String();
        while(!s.equals("STOP")){
            try {
                s = input.nextLine();
                output.write((prefix + s + "\n").getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
