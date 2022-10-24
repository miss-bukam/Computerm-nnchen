
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UI {
    public static void main(String[] args) {
        // Fenster
        JFrame frame = new JFrame("Computermaennchen ");
        frame.setSize(450, 350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Label
        
            //Spielname
        JLabel label1 = new JLabel("<html>HERZLICH WILLKOMMEN ZUM SPIEL<br/>  &ensp; &ensp; &ensp; COMPUTERMAENNCHEN!! <html> ", JLabel.CENTER);
        frame.add(label1);
        Font ftTitel= new Font("arial", Font.BOLD,18);
        label1.setFont(ftTitel);
        label1.setForeground(Color.BLUE);
        label1.setVerticalAlignment(SwingConstants.TOP);

            // Anweisung
        JLabel anweisung = new JLabel("<html>Sie bekommen ein beliebiges Wort gegeben, <br/>welches sie selbst durch das Erraten von Buchstaben versuchen müssen zu entziffern. <br/>Sie können es aber auch mit Wörtern versuchen. :D <br/>Aber aufgepasst!! Bei jeder falschen Eingabe,wird der unschuldige Roboter mehr zur Verdamnis gebracht!   <br/>Also geben Sie sich Mühe und retten sie den Roboter!!!!! <html>",JLabel.CENTER);
        frame.add(anweisung);
        Font ftAnweisung = new Font("arial", Font.ITALIC,14);
        anweisung.setFont(ftAnweisung);

        // Start
        JButton startButton = new JButton("Start");
        frame.add(startButton);
        startButton.setLocation(150,250);
        startButton.setSize(100, 30);

        JLabel label2 = new JLabel("Viel Erfolg!!");
        frame.add(label2);
        
        label2.setBounds(165, 280, 100, 30);
        label2.setForeground(Color.red);


        


    }

    
}