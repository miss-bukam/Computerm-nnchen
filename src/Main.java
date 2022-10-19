import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Galgenmännchen {

   /* public Galgenmaennchen (string gesuchtesWort) {

    }

    public string RateBuchstaben (char buchstabe) {

    }*/

    public static void main(String[] args) {

        // Die Überschrift + Anleitung des Spiels
        System.out.println("\n\t\tHERZLICH WILLKOMMEN ZUM SPIEL");
        System.out.println("\t\t       COMPUTERMÄNNCHEN");
        System.out.println("\nUnd so geht's:");
        System.out.println("\nSie bekommen ein beliebiges Wort gegeben, welches sie selbst \ndurch das Erraten von Buchstaben versuchen müssen zu entziffern. \nSie können es aber auch mit Wörtern versuchen. :D ");
        System.out.println("Aber aufgepasst!! Bei jeder falschen Eingabe, \nwird der unschuldige Roboter mehr zur Verdamnis gebracht! >_<");
        System.out.println("\nAlso geben Sie sich Mühe und retten sie den Roboter!!!!!");
        System.out.println("\nViel Erfolg! :)");
        System.out.println("\n(Drücken Sie die 1, um ihr Wort zu bekommen)\n");

        int fehler = 0; // Anzahl der Fehler

        List<Character> wort = new ArrayList<Character>();
        wort.add(0, 'P');
        wort.add(1, 'R');
        wort.add(2,'O');
        wort.add(3,'G');
        wort.add(4,'R');
        wort.add(5,'A');
        wort.add(6, 'M');
        wort.add(7,'M');

        //System.out.println("\nLänge des Wortes: " + wort.size());

        Scanner scan = new Scanner(System.in);
        int Antwort = scan.nextInt();

        if (Antwort == 1){
            System.out.println("\nDas gegebene Wort:\n");

            for(int i = 0; i < wort.size(); i++) {
                System.out.print("_ ");
            }
            System.out.println();
        } else {
            System.out.println("(Bitte drücken Sie die 1!)");

        }
        //System.out.println("\n");

        List<Character> geloest = new ArrayList<Character>();
        for (int i = 0; i < wort.size(); i++){
            geloest.add('_');
        }
        //loesen(f, geloest, wort);

        /*Scanner scan = new Scanner(System.in);
        int Antwort = scan.nextInt();*/


    }
}
