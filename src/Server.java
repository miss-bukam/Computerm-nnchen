import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        
        int port = 4001;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Warten auf die Verbindung der Spielers");
                Socket spieler1 = serverSocket.accept();
                System.out.println("Spieler 1 hat verbunden");
                Socket spieler2 = serverSocket.accept();
                System.out.println("Spieler 2 hat verbunden");
                ClientHandler clientHandler = new ClientHandler(spieler1, spieler2);
                Thread thread = new Thread(clientHandler);
                thread.start();
                System.out.println("Los geht's");
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

        System.exit(0);
    }

}


class ClientHandler implements Runnable {
    private final Socket sp1Socket;
    private final Socket sp2Socket;

    public ClientHandler(Socket socket1, Socket socket2) {
        this.sp1Socket = socket1;
        this.sp2Socket = socket2;
    }

    @Override
    public void run() {
        Game game = new Game(sp1Socket, sp2Socket);
        game.run();
    }
}
