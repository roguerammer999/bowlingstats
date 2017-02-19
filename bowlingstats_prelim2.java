import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class bowlingstats_prelim2 extends JFrame
{
    public static void main(String [] args)
    {
        new bowlingstats_prelim2();
        addGame(new bgame_v2("4-6-7-1-7-1-1-7-9-1-8-0-7-0-0-10-10-9-0"));
        addGame(new bgame_v2("9-1-8-0-8-2-7-0-10-8-2-10-9-0-10-0-10-9"));
        addGame(new bgame_v2("9-0-10-0-9-8-2-10-9-0-6-0-0-8-7-3-10-5-0"));
        addGame(new bgame_v2("9-0-7-1-8-2-9-0-10-10-5-5-9-0-7-2-8-0"));
        addGame(new bgame_v2("9-0-10-10-7-3-8-0-3-2-10-6-0-10-8-2-7"));
        addGame(new bgame_v2("1-0-10-9-0-0-10-10-9-1-5-3-9-1-6-1-10-6-4"));
        addGame(new bgame_v2("8-2-5-3-9-0-9-1-9-1-7-1-0-9-9-1-10-7-1"));
    }
    
    //Array "gamesStorage []" is the storage for each of the seven games.
    //At this point, this will need to be changed when there are more games.
    public static bgame_v2 gamesStorage [] = new bgame_v2 [7];
    public static int gamesCount = 0;
    
    //JPanel mainDisplay has the game data, while selectDisplay is the
    //area for selecting each game
    private static JPanel mainDisplay =  new JPanel();
    private static JPanel selectDisplay = new JPanel();
    private static Box gameSelect = Box.createVerticalBox();
    private static JButton selectButton = new JButton("Display score");
    private static String [] gameNumbers = {"Game 1", "Game 2", "Game 3",
        "Game 4", "Game 5", "Game 6", "Game 7"};
    private static JList gameNumbersList= new JList(gameNumbers);
    
    //framePanel is an array of 10 JPanels, each one containing components
    //depicting a frame: frameDataDisplay (ball data),
    //frameScoreDisplay (final score), as well as a title ("Frame #5").
    private static JPanel [] framePanel = new JPanel [10];
    //private static JTextArea [] frameDataDisplay = new JTextArea [10];
    private static JTextArea [] frameDataDisplay = new JTextArea [10];
    private static JTextArea [] frameScoreDisplay = new JTextArea [10];
    
    private static Font fontDefault = new Font("Sans Serif", Font.PLAIN, 16);
    private static Font fontBold = new Font("Sans Serif", Font.BOLD, 16);
    
    public bowlingstats_prelim2()
    {
        this.setLocation(400,300);
        this.setSize(1050,230);
        this.setTitle("Bowling Stats Display v.2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(mainDisplay);        
        mainDisplay.add(selectDisplay);
        selectDisplay.setLayout(new GridLayout(0,10,5,5));
        
        //This section adds the frame data to the frame display boxes.
        for(int counter = 0; counter < 10; counter++)
        {
            framePanel[counter] = new JPanel();
            Box indivFrame = Box.createVerticalBox();
            framePanel[counter].add(indivFrame);
            
            //First part of each frame is the frame # header.
            indivFrame.add(new JLabel("Frame " + (counter + 1)));
            indivFrame.add(indivFrame.createVerticalStrut(15));
            
            //Second part of each frame is ball data.
            frameDataDisplay[counter] = new JTextArea(3,4);
            frameDataDisplay[counter].setEditable(false);
            frameDataDisplay[counter].setFont(fontDefault);
            indivFrame.add(frameDataDisplay[counter]);
            indivFrame.add(indivFrame.createVerticalStrut(15));
            
            //Third part of each frame is the score for each frame.
            frameScoreDisplay[counter] = new JTextArea(3,4);
            frameScoreDisplay[counter].setEditable(false);
            indivFrame.add(frameScoreDisplay[counter]);
            frameScoreDisplay[counter].setFont(fontDefault);
            selectDisplay.add(indivFrame);
        }
        
        mainDisplay.add(gameSelect);
        gameNumbersList.setFixedCellWidth(100);
        gameNumbersList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        selectButton.addActionListener(new changeSelectedGame());        
        gameSelect.add(gameNumbersList);
        gameSelect.add(gameSelect.createVerticalStrut(15));
        gameSelect.add(this.selectButton);
        
        this.setVisible(true);
    }
    
    public static void addGame(bgame_v2 newGame)
    {
        gamesStorage[gamesCount] = newGame;
        gamesCount++;
    }
    
    public static void showFrameScore(bgame_v2 inputGame)
    {
        for(int counter = 0; counter < 10; counter++)
        {
            frameDataDisplay[counter].setText
        (inputGame.gameFrames[counter].ball1 + "  "
                + inputGame.gameFrames[counter].ball2 + "  " +
                inputGame.gameFrames[counter].ball3);
            
            //Closed frames (10 points or more in the frame) are set to bold
            if(inputGame.gameFrames[counter].closedFrame == true)
                frameDataDisplay[counter].setFont(fontBold);
            else
                frameDataDisplay[counter].setFont(fontDefault);
        }
        for(int counter = 0; counter < 10; counter++)
        {
            frameScoreDisplay[counter].setText
        (Integer.toString(inputGame.gameFrames[counter].frameScore));
        }
    }

    //The ActionListener is for the "Display score" button.  When pressed,
    //the button loads the selected game into the display
    public class changeSelectedGame implements ActionListener
    {
        public void actionPerformed (ActionEvent gameSel)
        {
            if (gameSel.getSource() == selectButton)
            {
                String gameNumber = gameNumbersList.getSelectedValue().
                        toString();
                int numberselect = Integer.parseInt(gameNumber.substring(5));
                showFrameScore(gamesStorage[numberselect - 1]);
            }
        }
    }
}
