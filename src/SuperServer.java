import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SuperServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(30413);
        ArrayList<Socket> clients = new ArrayList<>();
        System.out.println("Server started.");

        while(true){
            Socket clientSocket = serverSocket.accept();
            clients.add(clientSocket);
            System.out.println("New client with IP("+clientSocket.getInetAddress().getHostAddress()+") connected.");
            //then start a thread of broadcaster that receives a message from that client and then broadcasts it to all others
            Broadcaster toAdd = new Broadcaster(clients, clientSocket);
            toAdd.start();
        }
    }
}