import javax.swing.*;
import java.awt.*;

public class bowlingstats_display1 extends JFrame
{
    public static void main (String args[])
    {
        new bowlingstats_display1();
    }
    
    private static JPanel mainDisplay =  new JPanel();
    
    public bowlingstats_display1()
    {
        //Due to display behavior, I made set the Y offset to 0
        //for visibility on my computer.
        this.setLocation(1000,0);
        this.setSize(900,700);
        this.setTitle("Bowling Stats Display v.1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        //A game has ten frames; therefore, the row width is 10.
        //I do not currently know how to add discrete rows for each game.
        mainDisplay.setLayout(new GridLayout(0,10));
        this.add(mainDisplay);
    }
    
    //This takes two arrays, one of frame ball data and one of frame scores,
    //then adds text areas for each.  Input arrays are assumed to be of
    //length 10, which is needed to work correctly.
    public static void addFrameScore(String [] frameData, int [] inputScore)
    {
        for(int counter = 0; counter < 10; counter++)
        {
            JTextArea frameScore = new JTextArea(3,5);
            frameScore.setText("Frame " + (counter + 1) + "\n" +
                    frameData[counter] + "\n" +
                    Integer.toString(inputScore[counter]));
            mainDisplay.add(frameScore);
        }
    }
}
