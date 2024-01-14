import java.io.DataInputStream;
import java.util.Scanner;

public class Receiver extends Thread{
    private Scanner input;

    Receiver(DataInputStream in){   //takes data input stream of some socket.
        input = new Scanner(in);
    }

    public void run(){
        String s = new String();
        while(!s.equals("STOP")){
            s = input.nextLine();
            System.out.println(s);
        }
        input.close();
    }
}