import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

public class bowlingstats_prelim2 extends JFrame
{
    public static void main(String [] args)
    {
        String rawGames =
                "05/04/2012-1-9-1-1-8-1-6-5-0-10-9-1-9-1-10-10-8-0\n"
                + "05/04/2012-2-0-7-7-3-10-8-0-5-2-9-1-3-6-7-0-5-2-10-6-2\n"
                + "12/30/2016-1-4-6-7-1-7-1-1-7-9-1-8-0-7-0-0-10-10-9-0\n"
                + "12/30/2016-2-9-1-8-0-8-2-7-0-10-8-2-10-9-0-10-0-10-9\n"
                + "12/30/2016-3-9-0-10-0-9-8-2-10-9-0-6-0-0-8-7-3-10-5-0\n"
                + "12/30/2016-4-9-0-7-1-8-2-9-0-10-10-5-5-9-0-7-2-8-0\n"
                + "12/30/2016-5-9-0-10-10-7-3-8-0-3-2-10-6-0-10-8-2-7\n"
                + "12/30/2016-6-1-0-10-9-0-0-10-10-9-1-5-3-9-1-6-1-10-6-4\n"
                + "12/30/2016-7-8-2-5-3-9-0-9-1-9-1-7-1-0-9-9-1-10-7-1\n";
        String allGamesBase [] = rawGames.split("\n");        
        gamesStorage = new bgame_v2[allGamesBase.length];
        
        for(int counter = 0; counter<allGamesBase.length; counter++)
        {
            addGame(new bgame_v2(allGamesBase[counter]));
        }
        
        new bowlingstats_prelim2();
    }
    
        //Method for adding a new game
    private static void addGame(bgame_v2 newGame)
    {
        gamesStorage[gamesCount] = newGame;
        gamesCount++;
    }
    
    //Array "gamesStorage []" is the storage for each of the games.
    private static bgame_v2 gamesStorage [];
    private static int gamesCount = 0;
    
    //JPanel mainDisplay has the game data, while selectDisplay is the
    //area for selecting each game
    private static JPanel mainDisplay = new JPanel();
    private static Box mainDataDisplay = Box.createVerticalBox();
    private static Box metaData = Box.createHorizontalBox();
    private static JPanel selectDisplay = new JPanel();
    private static Box gameSelect = Box.createVerticalBox();
    private static JButton selectButton = new JButton("Display score");
    
    private static JTextField gameDateDisplay;
    private static JTextField gameOrdinalDisplay;
    private static NumberFormat gameCountFormat =
            NumberFormat.getNumberInstance();
    
    //framePanel is an array of 10 JPanels, each one containing components
    //depicting a frame: frameDataDisplay (ball data),
    //frameScoreDisplay (final score), as well as a title ("Frame #5").
    private static JPanel [] framePanel = new JPanel [10];
    private static JTextField [] frameBall1 = new JTextField[10];
    private static JTextField [] frameBall2 = new JTextField[10];
    private static JTextField [] frameScore = new JTextField[10];
    private static JTextField blankFrame10;
    
    
    private static Font fontDefault = new Font("Sans Serif", Font.PLAIN, 22);
    private static Font fontScore = new Font("Sans Serif", Font.BOLD, 28);
    private static Font fontBold = new Font("Sans Serif", Font.BOLD, 22);
    private static Border lineBlack =
            BorderFactory.createLineBorder(Color.BLACK);
    
    
    //These fields are used for the list for selection of game.
    private static String [] gameNumbers;
    private static JList gameNumbersList;
    private static JScrollPane gNLScroll;
    
    
    public bowlingstats_prelim2()
    {
        this.setLocation(400,300);
        this.setSize(900,240);
        this.setTitle("Bowling Stats Display v.2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(mainDisplay);
        mainDataDisplay.add(metaData);
        mainDataDisplay.add(selectDisplay);
        mainDisplay.add(mainDataDisplay);
        selectDisplay.setLayout(new GridLayout(0,10,5,5));
        
        //This section adds the date of the game and the ordinal (e.g.
        //the third game of the day).
        gameDateDisplay = new JTextField("");
        gameDateDisplay.setEditable(false);
        gameDateDisplay.setFont(fontScore);
        gameOrdinalDisplay = new JTextField("");
        gameOrdinalDisplay.setEditable(false);
        gameOrdinalDisplay.setFont(fontScore);
        metaData.add(gameDateDisplay);
        metaData.add(gameOrdinalDisplay);
        
        //This section adds the frame data to the frame display boxes.
        for(int counter = 0; counter < 10; counter++)
        {
            JLabel frameHeader = new JLabel("Frame #" + (counter + 1));
            
            framePanel[counter] = new JPanel();
            framePanel[counter].setLayout(new BorderLayout());
            framePanel[counter].setBorder(lineBlack);
            JPanel framePanelTop = new JPanel();
            framePanelTop.setLayout(new GridLayout(0,3));
            
            if(counter==9)//Tenth frame only
            {
                blankFrame10 = new JTextField();
                blankFrame10.setEditable(false);
                blankFrame10.setFont(fontDefault);
                blankFrame10.setBorder(lineBlack);
                blankFrame10.setBackground(Color.WHITE);
                framePanelTop.add(blankFrame10);
            }
            else
            {
                JTextField blankField = new JTextField();
                blankField.setEditable(false);
                framePanelTop.add(blankField);
            }
            
            frameBall1[counter] = createScorePanel(1, fontDefault);
            framePanelTop.add(frameBall1[counter]);
            frameBall2[counter] = createScorePanel(1, fontDefault);
            framePanelTop.add(frameBall2[counter]);
            
            frameScore[counter] = createScorePanel(2, fontScore);
            
            framePanel[counter].add(frameHeader, BorderLayout.NORTH);
            framePanel[counter].add(framePanelTop, BorderLayout.CENTER);
            framePanel[counter].add(frameScore[counter], BorderLayout.SOUTH);
            selectDisplay.add(framePanel[counter]);
        }
        
        
        //This creates a String array based on the number of games stored,
        //then it is converted to a JList.
        mainDisplay.add(gameSelect);
        gameNumbers = new String [gamesStorage.length];
        for(int counter = 1; counter <= gamesStorage.length; counter++)
        {
            gameNumbers[counter - 1] = "Game " + counter;
        }
        gameNumbersList = new JList(gameNumbers);
        gameNumbersList.setVisibleRowCount(7);
        gameNumbersList.setFixedCellWidth(100);
        gameNumbersList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        gameNumbersList.setFixedCellWidth(100);
        gameNumbersList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        gNLScroll = new JScrollPane(gameNumbersList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        selectButton.addActionListener(new changeSelectedGame());
        gameSelect.add(gNLScroll);
        
        
        gameNumbersList.setSelectedIndex(0);
        gameSelect.add(this.selectButton);
        
        this.setVisible(true);
    }
    
    
    //Method for creating a JTextField.  This is used in the
    //selectDisplay JPanel, which contains the game data of the selected game.
    private static JTextField createScorePanel(int width, Font fontSelection)
    {
        JTextField newScoreField = new JTextField(width);
        newScoreField.setEditable(false);
        newScoreField.setFont(fontSelection);
        newScoreField.setBorder(lineBlack);
        newScoreField.setBackground(Color.WHITE);
        return newScoreField;
    }
    
    public static void showFrameScore(int inputOrdinal)
    {
        bgame_v2 inputGame = gamesStorage[inputOrdinal];
        gameCountFormat.setMinimumIntegerDigits(4);
        gameCountFormat.setGroupingUsed(false);
        gameDateDisplay.setText(inputGame.gameDate +
                "  Game #" + inputGame.gameOrdinal);
        gameOrdinalDisplay.setText("Game " +
                gameCountFormat.format(inputOrdinal + 1));
        
        //First nine frames only
        for(int counter = 0; counter < 9; counter++)
        {
            frameBall1[counter].setText(inputGame.gameFrames[counter].ball1);
            frameBall2[counter].setText(inputGame.gameFrames[counter].ball2);
            if(inputGame.gameFrames[counter].closedFrame)
            {
                frameBall1[counter].setFont(fontBold);
                frameBall1[counter].setBackground(new Color(210,255,210));
                frameBall2[counter].setFont(fontBold);
                frameBall2[counter].setBackground(new Color(210,255,210));
                frameScore[counter].setBackground(new Color(210,255,210));
            }
            else
            {
                frameBall1[counter].setFont(fontDefault);
                frameBall1[counter].setBackground(Color.WHITE);
                frameBall2[counter].setFont(fontDefault);
                frameBall2[counter].setBackground(Color.WHITE);
                frameScore[counter].setBackground(Color.WHITE);
            }
            frameScore[counter].setText(
                    Integer.toString(inputGame.gameFrames[counter].frameScore));
        }
        
        //Tenth frame only
        blankFrame10.setText(inputGame.gameFrames[9].ball1);
        frameBall1[9].setText(inputGame.gameFrames[9].ball2);
        frameBall2[9].setText(inputGame.gameFrames[9].ball3);
        frameScore[9].setText(
                Integer.toString(inputGame.gameFrames[9].frameScore));
        if(inputGame.gameFrames[9].closedFrame)
        {
            blankFrame10.setFont(fontBold);
            blankFrame10.setBackground(new Color(210,255,210));
            frameBall1[9].setFont(fontBold);
            frameBall1[9].setBackground(new Color(210,255,210));
            frameBall2[9].setFont(fontBold);
            frameBall2[9].setBackground(new Color(210,255,210));
            frameScore[9].setBackground(new Color(210,255,210));
        }
        else
        {
            blankFrame10.setFont(fontDefault);
            blankFrame10.setBackground(Color.WHITE);
            frameBall1[9].setFont(fontDefault);
            frameBall1[9].setBackground(Color.WHITE);
            frameBall2[9].setFont(fontDefault);
            frameBall2[9].setBackground(Color.WHITE);
            frameScore[9].setBackground(Color.WHITE);
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
                int gameNumber = gameNumbersList.getSelectedIndex();
                showFrameScore(gameNumber);
            }
        }
    }
}
