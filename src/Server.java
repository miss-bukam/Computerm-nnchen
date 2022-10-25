import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        
        int port = 4001;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Warten auf die Verbindung eines Clients");
                Socket client1 = serverSocket.accept();
                System.out.println("Client 1 hat verbunden");
                Socket client2 = serverSocket.accept();
                System.out.println("Client 2 hat verbunden");
                ClientHandler clientHandler = new ClientHandler(client1, client2);
                Thread thread = new Thread(clientHandler);
                thread.start();
                System.out.println("Los geht's");
            }
        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }

        System.exit(0);
    }

}


class ClientHandler implements Runnable {
    private final Socket client1Socket;
    private final Socket client2Socket;

    public ClientHandler(Socket socket1, Socket socket2) {
        this.client1Socket = socket1;
        this.client2Socket = socket2;
    }

    @Override
    public void run() {
        Game game = new Game(client1Socket, client2Socket);
        game.run();
    }
}
