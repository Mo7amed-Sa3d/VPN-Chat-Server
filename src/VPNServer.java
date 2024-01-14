import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class VPNServer {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(30414);
        System.out.println("Server started.");

        while(true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected.");
            DataInputStream clientIn = new DataInputStream(clientSocket.getInputStream());
            //First receive the destination IP address from the client.
            Scanner in = new Scanner(clientIn);
            String destination = in.nextLine();
            Socket destServer = new Socket(destination.split(" ")[0], Integer.parseInt(destination.split(" ")[1]));
            //start a receiver stream from the client and a sender stream to the chat server.
            DataOutputStream destOut = new DataOutputStream(destServer.getOutputStream());
            DataInputStream destIn = new DataInputStream(destServer.getInputStream());
            DataOutputStream clientOut = new DataOutputStream(clientSocket.getOutputStream());
            clientOut.write("Connected to destination.\n".getBytes());

            Transmitter linkToServer = new Transmitter(clientIn, destOut);
            Transmitter linkToClient = new Transmitter(destIn, clientOut);

            linkToServer.start();
            linkToClient.start();
        }
    }
}
//This class acts as a program that receives the messages with their destination IP and sends them to the destination.
//This program is doesn't show the IP addresses of clients to other clients.
//But as we can see the admin can see the IP addresses of clients.
//All what this class does is that it establishes a connection with the chat server and a connection with the
//Client. And forwards messages it receives from the client.
//What remains to have a more like virtual private network is to encrypt messages at the sender's end and
//decrypt them at the receiver's end.