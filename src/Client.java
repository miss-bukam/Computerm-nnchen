import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
	// write your code here
        String host = "127.0.0.1";
        int port = 4001;
        try (Socket socket = new Socket(host, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            String line = "";
            String input = "";
            while(!input.equalsIgnoreCase("exit")){
                line = in.readLine();
                //Rückmeldung für Client
                if (line.trim().equals("SET")){
                    System.out.print("Geben Sie bitte ein Wort, das Ihr Gegner erraten soll: ");
                    String geheimesWortpruf = scanner.nextLine();
                    while(geheimesWortpruf.length()<6){
                        System.out.print("Bitte wählen Sie eine mehr als 6 Buchstaben Wort aus!");
                        geheimesWortpruf = scanner.nextLine();
                    }
                    input = geheimesWortpruf.toUpperCase();
                    //Geheimes Wort zu Client schicken
                    out.println(input);
                    out.flush();
                    System.out.println("Alles klar! Dein Wort ist: " + input);
                    System.out.println("Warten Sie darauf, dass Ihr Gegner errät.");
                } else if (line.trim().equals("ERRATEN")) {
                    System.out.print("Erraten Sie einen Buchstabe ");
                    input = scanner.nextLine().toUpperCase();
                    out.println(input);
                    out.flush();
                } else if (line.trim().equals("NOCHMALS BITTE")) {
                    System.out.print("Sie haben diesen Buchstaben bereits erraten. Nochmal: ");
                    input = scanner.nextLine();
                    out.println(input);
                    out.flush();
                } else if (line.trim().equals("ENDE")) {
                    System.exit(0);
                } else {
                    System.out.println(line);
                }

            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}