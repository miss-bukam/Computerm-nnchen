  import java.io.BufferedReader;
  import java.io.IOException;
  import java.io.InputStreamReader;
  import java.io.PrintWriter;
  import java.net.Socket;
  import java.util.Arrays;
public class Game {

    Boolean isRunning = true;
    Socket spieler1;
    Socket spieler2;
    PrintWriter sp1Out;
    PrintWriter sp2Out;
    BufferedReader sp1In;
    BufferedReader sp2In;
    String geheimesWort;
    int gameLives = 2;
    int P1Losses = 0;
    int P2Losses = 0;
    public Game (Socket spieler1, Socket spieler2){
        this.spieler1 = spieler1;
        this.spieler2 = spieler2;
        try {
            sp1Out = new PrintWriter(spieler1.getOutputStream(), true);
            sp2Out = new PrintWriter(spieler2.getOutputStream(), true);
            sp1In = new BufferedReader(new InputStreamReader(spieler1.getInputStream()));
            sp2In = new BufferedReader(new InputStreamReader(spieler2.getInputStream()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        int leben = 9;
        char[] raten = new char[27];
        int nextGuessIndex = 0;
        sp1Out.println("Herzlich wilkommen zu Computermaennchen! Du bist Spieler 1!");
        sp1Out.println("Und so geht's: \n Es gitb 2 Spielern. Spieler 1 wählt ein Wort aus, das mindesstens 4 Buchstaben hat, damit Spieler 2 raten kann. Spieler 2 bekommt ein Wort von anderen, welches sie selbst \ndurch das Erraten von Buchstaben versuchen müssen zu entziffern :D \n Aber aufgepasst!! Bei jeder falschen Eingabe, \nwird der unschuldige Roboter mehr zur Verdamnis gebracht! >_< \n Viel Spass!! ");
        sp2Out.println("Herzlich willkommen zu Computermaennchen! Du bist Spieler 2!");
        sp2Out.println("Und so geht's: \n Es gitb 2 Spielern. Spieler 1 wählt ein Wort aus, das mindesstens 4 Buchstaben hat, damit Spieler 2 raten kann. Spieler 2 bekommt ein Wort von anderen, welches sie selbst \ndurch das Erraten von Buchstaben versuchen müssen zu entziffern :D \n Aber aufgepasst!! Bei jeder falschen Eingabe, \nwird der unschuldige Roboter mehr zur Verdamnis gebracht! >_< \n Viel Spass!! ");
        
        while (P1Losses != gameLives && P2Losses != gameLives) {
            sp1Out.println("Bitte wählen Sie ein Wort aus!");
            sp2Out.println("Warten Sie bitte. Spieler 1 wählt ein Wort für Sie aus. In der nächste Runde können Sie ein Wort auswählen.");
            //sp1Out.println("Lives left: " + (gameLives - P1Losses));
            //P2out.println("Lives left: " + (gameLives - P2Losses));
            sp1Out.println("SETPHRASE");
            sp1Out.flush();
            sp2Out.flush();
            try {
                geheimesWort = sp1In.readLine();
                System.out.println("Gegebene Wort " + geheimesWort);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sp2Out.println("Ok. Los geht's");
            sp2Out.println(display(raten));
            sp2Out.flush();
            while ((!(checkForWin(raten))) && leben != 0) {
                System.out.println((!(checkForWin(raten))) && leben != 0);
                System.out.println(leben != 0);
                System.out.println((!(checkForWin(raten))));
                System.out.println(leben);
                sp2Out.println("GUESS");
                sp2Out.flush();
                try {
                    char rate = sp2In.readLine().charAt(0);
                    System.out.println("Ihr Gegner hat erraetet " + rate);
                    if (!new String(raten).contains(String.valueOf(rate))){
                        //if the guess hasn't already been made
                        raten[nextGuessIndex] = rate;
                        nextGuessIndex++;
                    } else {
                        while ((new String(raten).contains(String.valueOf(rate)))) {
                            sp2Out.println("GUESSAGAIN");
                            sp2Out.flush();
                            rate = sp2In.readLine().charAt(0);
                        }
                        raten[nextGuessIndex] = rate;
                        nextGuessIndex++;
                    }
                    if (geheimesWort.contains(String.valueOf(rate))) {
                        sp2Out.println("Richtig, " + rate + " ist in dem gegebenen Wort");
                        sp2Out.println(display(raten));
                        sp1Out.println("Dein Gegner war richtig " + rate);
                        sp1Out.println(display(raten));
                    } else {
                        sp2Out.println("Nein, " + rate + " ist nicht in dem gegebenen Wort");
                        sp1Out.println("Dein Gegner ist falsch: " + rate);
                        leben--;
                        sp1Out.println(galgenmannchen(leben));
                        sp2Out.println(galgenmannchen(leben));
                        sp1Out.println("Falsche Erraten " + String.valueOf(raten));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (checkForWin(raten)) {
                System.out.println(checkForWin(raten));
                sp1Out.println("Du hast diese Runde verloren. Sieht so aus, dass dein Wort zu einfach war.");
                sp2Out.println("Hurray! Du hast gewonnen!");
                P1Losses++;
            } else {
                sp1Out.println("Glückwusch! Du hast diese Runde gewonnnen.");
                sp2Out.println("Du hast verloren. Das gegebene Wort ist " + geheimesWort);
                P2Losses++;
            }
            if (P1Losses == gameLives) {
                sp1Out.println("Du hast verloren");
                sp2Out.println("Glückwusch. Du hast gewonnen!");
            } else if (P2Losses == gameLives) {
                sp2Out.println("Du hats verloren!");
                sp1Out.println("Glückwunsch. Du hast gewonnen!");
            } else {
                switchPlayers(spieler1, spieler2);
                leben = 6;
                raten = new char[27];
                raten[0] = ' ';
            }
        }
        sp1Out.println("ENDGAME");
        sp2Out.println("ENDGAME");
    }

    public String display(char[] raten){
        char[] wort = geheimesWort.toCharArray();
        int i = 0;
        for(char c : wort){
            //if the character isn't in the guesses list, convert it to an underline.  otherwise, leave it as is.
            if (!(new String(raten).contains(String.valueOf(c)))){
                wort[i] = '_';
            }
            i++;
        }
        return String.valueOf(wort);
    }

    public Boolean checkForWin(char[] raten){
        char[] wort = geheimesWort.toCharArray();
        int i = 0;
        for(char c : wort){
            //Wenn das Buchstabe in der raten-Liste ist
            if (!(new String(raten).contains(String.valueOf(c)))){
                return false;
            }
            i++;
        }
        return true;
    }

    public String galgenmannchen(int lives) {
        String[] mannchen = new String[9];
        mannchen[0] = ("\n|--------|"+
        "\n|       (_)"+
        "\n|      --|--"+
        "\n|       / )"+
        "\n---");
        mannchen[1] = ("\n|--------|"+
        "\n|       (_)"+
        "\n|      --|--"+
        "\n|       /"+
        "\n---");
        mannchen[2] = ("\n|--------|"+
        "\n|       (_)"+
        "\n|      --|--"+
        "\n|"+
        "\n---");
        mannchen[3] = ("\n|--------|"+
        "\n|       (_)"+
        "\n|        |"+
        "\n|"+
        "\n---");
        mannchen[4]=("\n|--------|"+
        "\n|       (_)"+
        "\n|"+
        "\n|"+
        "\n---");
        mannchen[5]=("\n|--------|"+
        "\n|"+
        "\n|"+
        "\n|"+
        "\n---"
        );
        mannchen[6] =("\n|--------|"+
        "\n|"+
        "\n|"+
        "\n|"+
        "\n˜˜˜");
        mannchen[7] = ("\n|"+
        "\n|"+
        "\n|"+
        "\n|"+
        "\n---");
        mannchen[8] = ("\n"+
        "\n"+
        "\n"+
        "\n"+
        "\n---");
        return mannchen[lives];
    }
    public void switchPlayers(Socket player1, Socket player2) {
        int temp = P1Losses;
        P1Losses = P2Losses;
        P2Losses = temp;
        try {
            sp1Out = new PrintWriter(player2.getOutputStream(), true);
            sp2Out = new PrintWriter(player1.getOutputStream(), true);
            sp1In = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            sp2In = new BufferedReader(new InputStreamReader(player1.getInputStream()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

    
