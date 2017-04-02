import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.NumberFormat;

public class bowlingstats_prelim2 extends JFrame
{
    public static void main(String [] args)
    {
        String rawGames =
                "05/04/2012-1-9-1-1-8-1-6-5-0-10-9-1-9-1-10-10-8-0\n"
                + "05/04/2012-2-0-7-7-3-10-8-0-5-2-9-1-3-6-7-0-5-2-10-6-2\n"
                + "12/30/2016-1-4-6-7P-1-7-1-1-7-9-1-8-0-7P-0-0-10-10-9-0\n"
                + "12/30/2016-2-9-1-8-0-8-2-7-0-10-8-2-10-9-0-10-0-10-9\n"
                + "12/30/2016-3-9-0-10-0-9-8-2-10-9-0-6-0-0-8-7-3-10-5-0\n"
                + "12/30/2016-4-9-0-7-1-8P-2-9-0-10-10-6-4-8-1-7-2-8-0\n"
                + "12/30/2016-5-9-0-10-10-7-3-8-0-3-2-10-6-0-10-8-2-7\n"
                + "12/30/2016-6-1-0-10-9-0-0-10-10-9-1-5-3-9-1-6-1-10-6-4\n"
                + "12/30/2016-7-8-2-5-3-9-0-9-1-9-1-7-1-0-9-9-1-10-7-1\n";
        String [] gamesBaseAll = rawGames.split("\n");
        addAllGames(gamesBaseAll);
        
        new bowlingstats_prelim2();
    }
    
    
    //Array "gamesStorageAll []" is the storage for all of the games after they
    //are converted to bgame_v2.
    private static bgame_v2 [] gamesStorageAll;
    private static void addAllGames(String [] inputBaseGames)
    {
        int ctmax = inputBaseGames.length;
        gamesStorageAll = new bgame_v2[ctmax];
        for(int ct = 0; ct<ctmax; ct++)
        {
            gamesStorageAll[ct] = new bgame_v2(inputBaseGames[ct]);
        }
    }
    
    
    //JPanel mainDisplay has the game data, while selectDisplay is the
    //area for selecting each game
    private static JPanel mainDisplay = new JPanel();
    private static Box mainDataDisplay = Box.createVerticalBox();
    
    private static Box gameSelect = Box.createVerticalBox();
    private static JButton selectButton = new JButton("Display score");
    
    
    //These fields are used for the list for selection of game.
    private static String [] gameNumbers;
    private static JList gameNumbersList;
    private static JScrollPane gNLScroll;
    
    
    //GUI visual format parameters
    private static NumberFormat gameCountFormat = NumberFormat.getNumberInstance();
    private static NumberFormat avgFormat = NumberFormat.getNumberInstance();
    private static Font fontDefault = new Font("Sans Serif", Font.PLAIN, 22);
    private static Font fontScore = new Font("Sans Serif", Font.BOLD, 28);
    private static Font fontBold = new Font("Sans Serif", Font.BOLD, 22);
    private static Color closedFrameGreen = new Color (210,255,210);
    private static Color splitRed = new Color (255,210,210);
    private static Border lineBlack = BorderFactory.createLineBorder(Color.BLACK);
    
    
    //These three arrays show some statistics for a particular frame,
    //e.g. 3rd frame.  They are highs (highest score for that frame), lows,
    //and averages.  AverageSum uses 10x2 array; the reason for the 2 is that
    //one row represents the score sum while the other is the number of games.
    //It will be divided to calculate the average.
    private static int [] frameHighs = new int[10];
    private static double [][] frameAverageSum = new double [10][2];
    private static int [] frameLows = new int[10];
    
    
    public bowlingstats_prelim2()
    {
        this.setLocation(400,260);
        this.setSize(980,610);
        this.setTitle("Bowling Stats Display v.2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(mainDisplay);
        
        
        mainDataDisplay.setPreferredSize(new Dimension(720,400));
        
        
        gameCountFormat.setMinimumIntegerDigits(4);
        gameCountFormat.setGroupingUsed(false);
        
        //This creates a String array based on the number of games stored,
        //then it is converted to a JList.
        gameNumbers = new String [gamesStorageAll.length];
        for(int counter = 0; counter < gamesStorageAll.length; counter++)
        {
            gameNumbers[counter] = "Game " + gameCountFormat.format(counter + 1)
                    + "      " +
                    gamesStorageAll[counter].gameDate + " Game #" +
                    gamesStorageAll[counter].gameOrdinal;
        }
        gameNumbersList = new JList(gameNumbers);
        gameNumbersList.setVisibleRowCount(7);
        gameNumbersList.setFixedCellWidth(100);
        gameNumbersList.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gameNumbersList.setSelectedIndex(0);
        gNLScroll = new JScrollPane(gameNumbersList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gNLScroll.setPreferredSize(new Dimension(225,220));
        selectButton.addActionListener(gameSel ->
        {
            if (gameSel.getSource() == selectButton)
            {
                int selectedNumber = gameNumbersList.getSelectedIndex();
                int [] selectedNumbers = gameNumbersList.getSelectedIndices();
                mainDataDisplay.removeAll();
                mainDataDisplay.add(
                        showFrameScore(selectedNumbers));
            }
            bowlingstats_prelim2.this.setVisible(true);
        }
        );
        gameSelect.add(gNLScroll);
        gameSelect.add(this.selectButton);
        
        mainDisplay.setLayout(new GridBagLayout());
        mainDisplay.add(mainDataDisplay, layoutAdd(1, 1, 1, 1));
        mainDisplay.add(statsByFrameDisplay(), layoutAdd(1, 2, 1, 1));
        mainDisplay.add(gameSelect, layoutAdd(2, 1, 2, 1));
        
        this.setVisible(true);
    }
    
    private static GridBagConstraints layoutAdd(int inX, int inY, int inW, int inH)
    {
        GridBagConstraints layoutgbc = new GridBagConstraints();
        layoutgbc.gridx = inX;
        layoutgbc.gridy = inY;
        layoutgbc.gridwidth = inW;
        layoutgbc.gridheight = inH;
        layoutgbc.insets = new Insets(2,2,2,2);
        layoutgbc.anchor = GridBagConstraints.WEST;
        return layoutgbc;
    }
    
    //Helper method for creating a JTextField.  This is frequently used in the
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
    
    //Shows frame data for all selected games in mainDataDisplay.
    public static JScrollPane showFrameScore (int [] gameSelection)
    {
        Box mainPanel = Box.createVerticalBox();
        JScrollPane mainScroll = new JScrollPane(mainPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        gameCountFormat.setMinimumIntegerDigits(4);
        gameCountFormat.setGroupingUsed(false);
        
        //Resetting frame-by-frame statistics to zero
        for(int ct = 0; ct < 10; ct++)
        {
            frameHighs[ct] = 0;
            frameAverageSum[ct][0] = 0;
            frameAverageSum[ct][1] = 0;
            frameLows[ct] = 0;
        }
        
        for(int gct : gameSelection)
        {
            //Converting the respective selected game to a new one
            //to make access easier to read/type.
            bgame_v2 gameIn = gamesStorageAll[gct];
            
            //"Meta data" (game number, date) headers for a single game
            Box metaData = Box.createHorizontalBox();
            metaData.add(gameHeader("Game " + gameCountFormat.format(gct + 1)));
            metaData.add(gameHeader(gameIn.gameDate + "   Game #" +
                    gameIn.gameOrdinal));
            
            //"framePanel" contains frame data for a single game
            JPanel framePanel = new JPanel();
            framePanel.setLayout(new GridLayout (1,10,5,5));
            JPanel fPanel[] = new JPanel [10];
            
            for(int fct = 0; fct < 10; fct++)
            {
                JLabel frameHeader = new JLabel("Frame #" + (fct + 1));
                fPanel[fct] = new JPanel();
                fPanel[fct].setLayout(new BorderLayout());
                fPanel[fct].setBorder(lineBlack);
                JPanel framePanelTop = new JPanel();
                framePanelTop.setLayout(new GridLayout(0,3));
                
                if(fct==9) //Tenth frame only
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
                    else
                        frameBall2.setBackground(Color.LIGHT_GRAY);
                    if(gameIn.gameFrames[fct].isSplit)
                        frameBall1.setBackground(splitRed);
                }
                else //First nine frames
                {
                    JTextField blankField = new JTextField();
                    blankField.setEditable(false);
                    blankField.setBorder(lineBlack);
                    JTextField frameBall1 = createScorePanel(1,
                            fontDefault, gameIn.gameFrames[fct].ball1);
                    JTextField frameBall2 = createScorePanel(1,
                            fontDefault, gameIn.gameFrames[fct].ball2);
                    framePanelTop.add(blankField);
                    framePanelTop.add(frameBall1);
                    framePanelTop.add(frameBall2);
                    if(gameIn.gameFrames[fct].closedFrame)
                    {
                        frameBall1.setFont(fontBold);
                        frameBall1.setBackground(closedFrameGreen);
                        frameBall2.setFont(fontBold);
                        frameBall2.setBackground(closedFrameGreen);
                    }
                    if(gameIn.gameFrames[fct].isSplit)
                        frameBall1.setBackground(splitRed);
                }
                
                //Addition of current game to stats; calculation of stats
                if (frameHighs[fct] < gameIn.gameFrames[fct].frameScore)
                    frameHighs[fct] = gameIn.gameFrames[fct].frameScore;
                if (frameLows[fct] > gameIn.gameFrames[fct].frameScore || frameLows[fct] == 0)
                    frameLows[fct] = gameIn.gameFrames[fct].frameScore;
                frameAverageSum[fct][0] += gameIn.gameFrames[fct].frameScore;
                frameAverageSum[fct][1]++;
                
                
                JTextField frameScore = createScorePanel(2,fontScore,
                        Integer.toString(gameIn.gameFrames[fct].frameScore));
                if(gameIn.gameFrames[fct].closedFrame)
                    frameScore.setBackground(closedFrameGreen);
                
                fPanel[fct].add(frameHeader, BorderLayout.NORTH);
                fPanel[fct].add(framePanelTop, BorderLayout.CENTER);
                fPanel[fct].add(frameScore, BorderLayout.SOUTH);
                framePanel.add(fPanel[fct]);
            }
            
            Box gamePanel = Box.createVerticalBox();
            gamePanel.setMaximumSize(new Dimension(700,150));
            gamePanel.setBorder(lineBlack);
            gamePanel.add(metaData);
            gamePanel.add(framePanel);
            gamePanel.add(Box.createVerticalStrut(20));
            mainPanel.add(gamePanel);
        }
        
        //Refreshing the stats area to show the updated statistics
        avgFormat.setMaximumFractionDigits(2);
        for(int ct = 0; ct < 10; ct++)
        {
            statsFrameHighs[ct].setText( Integer.toString(frameHighs[ct]) );
            statsFrameAverages[ct].setText(avgFormat.format
        (frameAverageSum[ct][0] / frameAverageSum[ct][1])   );
            statsFrameLows[ct].setText( Integer.toString(frameLows[ct]) );
        }
        
        return mainScroll;
    }
    
    public static JTextField gameHeader(String ghTitle)
    {
        JTextField gh = new JTextField(ghTitle);
        gh.setEditable(false);
        gh.setFont(fontScore);
        return gh;
    }
    
    
    public static JPanel [] statsFrameBox = new JPanel [10];
    public static Box [] statsFrameBoxInner = new Box [10];
    public static JTextArea [] statsFrameHighs = new JTextArea [10];
    public static JTextArea [] statsFrameAverages = new JTextArea [10];
    public static JTextArea [] statsFrameLows = new JTextArea [10];
    
    public static Box statsByFrameDisplay()
    {
        Box statsDisp = Box.createHorizontalBox();
        for(int ct = 0; ct <10; ct++)
        {
            statsFrameBox[ct] = new JPanel();
            statsFrameBox[ct].setPreferredSize(new Dimension(70,150));
            statsFrameBoxInner[ct] = Box.createVerticalBox();
            statsFrameBoxInner[ct].setPreferredSize(new Dimension(70,125));
            statsFrameBox[ct].add (new JLabel("Frame #"+(ct+1) ) );
            statsFrameHighs[ct] = statsDisplayField("0");
            statsFrameBoxInner[ct].add(statsFrameHighs[ct]);
            statsFrameAverages[ct] = statsDisplayField("0");
            statsFrameBoxInner[ct].add(statsFrameAverages[ct]);
            statsFrameLows[ct] = statsDisplayField("0");
            statsFrameBoxInner[ct].add(statsFrameLows[ct]);
            statsFrameBox[ct].add(statsFrameBoxInner[ct]);
            statsDisp.add(statsFrameBox[ct]);
        }
        return statsDisp;
    }
    
    public static JTextArea statsDisplayField(String dataRaw)
    {
        JTextArea jtaStat = new JTextArea (dataRaw);
        jtaStat.setEditable(false);
        jtaStat.setFont(fontDefault);
        jtaStat.setBorder(lineBlack);
        return jtaStat;
    }
    
    
}
