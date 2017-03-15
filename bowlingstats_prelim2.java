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
    
    
    private static Font fontDefault = new Font("Sans Serif", Font.PLAIN, 22);
    private static Font fontScore = new Font("Sans Serif", Font.BOLD, 28);
    private static Font fontBold = new Font("Sans Serif", Font.BOLD, 22);
    private static Color closedFrameGreen = new Color (210,255,210);
    private static Border lineBlack =
            BorderFactory.createLineBorder(Color.BLACK);
    
    
    //These fields are used for the list for selection of game.
    private static String [] gameNumbers;
    private static JList gameNumbersList;
    private static JScrollPane gNLScroll;
    
    
    public bowlingstats_prelim2()
    {
        this.setLocation(400,300);
        this.setSize(1040,480);
        this.setTitle("Bowling Stats Display v.2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(mainDisplay);
        
        
        mainDataDisplay.add(metaData);
        mainDataDisplay.setMinimumSize(new Dimension(720,160));
        mainDataDisplay.setMaximumSize(new Dimension(720,400));
        mainDisplay.add(mainDataDisplay);
        
        
        //This creates a String array based on the number of games stored,
        //then it is converted to a JList.
        gameSelect.setAlignmentX(Box.TOP_ALIGNMENT);
        gameNumbers = new String [gamesStorage.length];
        gameCountFormat.setMinimumIntegerDigits(4);
        gameCountFormat.setGroupingUsed(false);
        for(int counter = 1; counter <= gamesStorage.length; counter++)
        {
            gameNumbers[counter - 1] = "Game " + gameCountFormat.format(counter)
                    + "      " +
                    gamesStorage[counter - 1].gameDate + " Game #" +
                    gamesStorage[counter - 1].gameOrdinal;
        }
        gameNumbersList = new JList(gameNumbers);
        gameNumbersList.setVisibleRowCount(7);
        gameNumbersList.setFixedCellWidth(100);
        gameNumbersList.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gNLScroll = new JScrollPane(gameNumbersList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gNLScroll.setPreferredSize(new Dimension(215,130));
        selectButton.addActionListener(gameSel ->
        {
            if (gameSel.getSource() == selectButton)
            {
                int gameNumber = gameNumbersList.getSelectedIndex();
                int [] gameNumbers = gameNumbersList.getSelectedIndices();
                mainDataDisplay.removeAll();
                mainDataDisplay.add(showFrameScore(gameNumbers));
            }
            bowlingstats_prelim2.this.setVisible(true);
        }
        );
        gameSelect.add(gNLScroll);
        gameSelect.add(Box.createVerticalGlue());
        
        
        gameNumbersList.setSelectedIndex(0);
        gameSelect.add(this.selectButton);
        mainDisplay.add(gameSelect);
        this.setVisible(true);
    }
    
    
    //Helper method for creating a JTextField.  This is used in the
    //selectDisplay JPanel, which contains the game data of the selected game.
    private static JTextField createScorePanel
        (int width, Font fontSelection, String ballScore)
    {
        JTextField newScoreField = new JTextField(width);
        newScoreField.setEditable(false);
        newScoreField.setFont(fontSelection);
        newScoreField.setBorder(lineBlack);
        newScoreField.setBackground(Color.WHITE);
        newScoreField.setText(ballScore);
        return newScoreField;
    }
    
    
    public static JScrollPane showFrameScore (int [] gameSelection)
    {
        Box mainPanel = Box.createVerticalBox();
        JScrollPane mainScroll = new JScrollPane(mainPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        gameCountFormat.setMinimumIntegerDigits(4);
        gameCountFormat.setGroupingUsed(false);
        
        for(int gct : gameSelection)
        {
            //Converting the respective selected game to a new one
            //to make access easier.
            bgame_v2 gameIn = gamesStorage[gct];
            
            JTextField gameOrdinalDisplay =
                    new JTextField("Game " +
                                    gameCountFormat.format(gct + 1) );
            gameOrdinalDisplay.setEditable(false);
            gameOrdinalDisplay.setFont(fontScore);
            JTextField gameDateDisplay =
                    new JTextField(gameIn.gameDate + "   Game #" +
                            gameIn.gameOrdinal);
            gameDateDisplay.setEditable(false);
            gameDateDisplay.setFont(fontScore);
            gameDateDisplay.setPreferredSize(
                    new Dimension(150, gameOrdinalDisplay.getHeight()));
            
            Box metaData = Box.createHorizontalBox();
            metaData.add(gameOrdinalDisplay);
            metaData.add(gameDateDisplay);
            
            JPanel framePanel = new JPanel();
            framePanel.setLayout(new GridLayout (0,10,5,5));
            JPanel fPanel[] = new JPanel [10];
            
            for(int fct = 0; fct < 10; fct++)
            {
                JLabel frameHeader = new JLabel("Frame #" + (fct + 1));
                fPanel[fct] = new JPanel();
                fPanel[fct].setLayout(new BorderLayout());
                fPanel[fct].setBorder(lineBlack);
                JPanel framePanelTop = new JPanel();
                framePanelTop.setLayout(new GridLayout(0,3));
                
                
                if(fct==9)//Tenth frame only
                {
                    JTextField blankFrame10 = createScorePanel(1,
                            fontDefault, gameIn.gameFrames[fct].ball1);
                    JTextField frameBall1 = createScorePanel(1,
                            fontDefault, gameIn.gameFrames[fct].ball2);
                    JTextField frameBall2 = createScorePanel(1,
                            fontDefault, gameIn.gameFrames[fct].ball3);
                    framePanelTop.add(blankFrame10);
                    framePanelTop.add(frameBall1);
                    framePanelTop.add(frameBall2);
                    if(gameIn.gameFrames[fct].closedFrame)
                    {
                        blankFrame10.setFont(fontBold);
                        blankFrame10.setBackground(closedFrameGreen);
                        frameBall1.setFont(fontBold);
                        frameBall1.setBackground(closedFrameGreen);
                        frameBall2.setFont(fontBold);
                        frameBall2.setBackground(closedFrameGreen);
                    }
                }
                else//First nine frames
                {
                    JTextField blankField = new JTextField();
                    blankField.setEditable(false);
                    framePanelTop.add(blankField);
                    JTextField frameBall1 = createScorePanel(1,
                            fontDefault, gameIn.gameFrames[fct].ball1);
                    JTextField frameBall2 = createScorePanel(1,
                            fontDefault, gameIn.gameFrames[fct].ball2);
                    framePanelTop.add(frameBall1);
                    framePanelTop.add(frameBall2);
                    if(gameIn.gameFrames[fct].closedFrame)
                    {
                        frameBall1.setFont(fontBold);
                        frameBall1.setBackground(closedFrameGreen);
                        frameBall2.setFont(fontBold);
                        frameBall2.setBackground(closedFrameGreen);
                    }
                }
                
                JTextField frameScore = createScorePanel(2,fontScore,
                        Integer.toString(gameIn.gameFrames[fct].frameScore));
                if(gameIn.gameFrames[fct].closedFrame)
                {
                    frameScore.setBackground(closedFrameGreen);
                }
                
                fPanel[fct].add(frameHeader, BorderLayout.NORTH);
                fPanel[fct].add(framePanelTop, BorderLayout.CENTER);
                fPanel[fct].add(frameScore, BorderLayout.SOUTH);
                
                framePanel.add(fPanel[fct]);
            }
            
            Box gamePanel = Box.createVerticalBox();
            
            gamePanel.setMaximumSize(new Dimension(700,130));
            gamePanel.add(metaData);
            gamePanel.add(framePanel);
            
            mainPanel.add(gamePanel);
        }
        
        //This section adds the date of the game and the ordinal (e.g.
        //the third game of the day).
        gameOrdinalDisplay = new JTextField("");
        gameOrdinalDisplay.setEditable(false);
        gameOrdinalDisplay.setFont(fontScore);
        gameDateDisplay = new JTextField("");
        gameDateDisplay.setEditable(false);
        gameDateDisplay.setFont(fontScore);
        gameDateDisplay.setPreferredSize(
                new Dimension(150, gameOrdinalDisplay.getHeight()));
        metaData.add(gameOrdinalDisplay);
        metaData.add(gameDateDisplay);
        
        mainScroll.setMinimumSize(new Dimension(720,160));
        mainScroll.setMaximumSize(new Dimension(720,400));
        
        return mainScroll;
    }
}
