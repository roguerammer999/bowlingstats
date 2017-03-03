public class bgame_v2
{
    public bgame_v2(String rawBallData)
    {
        createFrames(rawBallData);
    }
    
    public class frame_v2
    {
        public String ball1, ball2, ball3;
        boolean closedFrame;
        int frameScore;
    }
    
    public frame_v2 gameFrames[] = new frame_v2 [10];
    public int [] finalBallData;
    public String gameDate;
    public String gameOrdinal;
    
    final int TENPINS = 10;
    private int ballCount = 0;
    
        
    
    //Small method to return number as a string, or a zero as a dash
    private String scoreString(int ballScore)
    {
        if(ballScore == 0)
            return "-";
        else
            return Integer.toString(ballScore);
    }
    
    
    public void createFrames(String rawBallData)
    {
        String [] splitData = rawBallData.split("-");
        finalBallData = new int[splitData.length - 2];
        gameDate = splitData[0];
        gameOrdinal = splitData[1];
        for(int counter = 0; counter < (splitData.length - 2); counter++)
        {
            finalBallData[counter] =
                    Integer.parseInt(splitData[counter+2]);
        }
        
        
        //First nine frames
        for(int frameCount = 0; frameCount < 9; frameCount++)
        {
            this.gameFrames[frameCount] = new frame_v2();
            if(finalBallData[ballCount] == TENPINS) //A strike
            {
                this.gameFrames[frameCount].closedFrame = true;
                this.gameFrames[frameCount].ball1 = "X";
                this.gameFrames[frameCount].ball2 = "";
                this.gameFrames[frameCount].frameScore =
                        TENPINS +
                        finalBallData[ballCount + 1] +
                        finalBallData[ballCount + 2];
                if(frameCount != 0)
                    this.gameFrames[frameCount].frameScore +=
                            this.gameFrames[frameCount-1].frameScore;
                ballCount += 1;
            }
            else //Less than ten pins on first throw
            {
                this.gameFrames[frameCount].ball1 =
                        scoreString(finalBallData[ballCount]);
                if(finalBallData[ballCount] +
                        finalBallData[ballCount + 1] == 10) //A spare
                {
                    this.gameFrames[frameCount].closedFrame = true;
                    this.gameFrames[frameCount].ball2 = "/";
                    this.gameFrames[frameCount].frameScore =
                            TENPINS +
                            finalBallData[ballCount + 2];
                    if(frameCount != 0)
                        this.gameFrames[frameCount].frameScore +=
                                this.gameFrames[frameCount-1].frameScore;
                }
                else //An open frame (less than ten pins for both throws total
                {
                    this.gameFrames[frameCount].ball2 =
                            scoreString(finalBallData[ballCount + 1]);
                    this.gameFrames[frameCount].closedFrame = false;
                    this.gameFrames[frameCount].frameScore =
                            finalBallData[ballCount] +
                            finalBallData[ballCount + 1];
                    if(frameCount != 0)
                        this.gameFrames[frameCount].frameScore +=
                                this.gameFrames[frameCount-1].frameScore;
                }
                ballCount += 2;
            }
        }
        //End first nine frames
        
        
        //Tenth frame
        gameFrames[9] = new frame_v2();
        
        //If first ball tenth frame is a strike
        if (finalBallData[ballCount] == TENPINS)
        {
            this.gameFrames[9].ball1 = "X";
            this.gameFrames[9].closedFrame = true;
            if(finalBallData[ballCount + 1] == TENPINS) //Second ball strike
            {
                this.gameFrames[9].ball2 = "X";
                if(finalBallData[ballCount + 2] == TENPINS) //Third ball strike
                    this.gameFrames[9].ball3 = "X";
                else //3rd ball not a strike
                    this.gameFrames[9].ball3 =
                            scoreString(finalBallData[ballCount + 2]);
            }
            else //2nd ball not a strike
            {
                this.gameFrames[9].ball2 =
                        scoreString(finalBallData[ballCount + 1]);
                if(finalBallData[ballCount + 1] +finalBallData[ballCount + 2]
                        == 10)
                    this.gameFrames[9].ball3 = "/";
                else
                    this.gameFrames[9].ball3 =
                            scoreString(finalBallData[ballCount + 2]);
            }
            this.gameFrames[9].frameScore = this.gameFrames[8].frameScore +
                        finalBallData[ballCount] +
                        finalBallData[ballCount + 1] +
                        finalBallData[ballCount + 2];
        }
        
        //If second ball tenth frame is not a strike
        else
        {
            this.gameFrames[9].ball1 =
                    scoreString(finalBallData[ballCount]);
            if(finalBallData[ballCount] +
                    finalBallData[ballCount + 1] == TENPINS)//Tenth frame spare
            {
                this.gameFrames[9].ball2 = "/";
                this.gameFrames[9].closedFrame = true;
                this.gameFrames[9].ball3 =
                        scoreString(finalBallData[ballCount + 2]);
                this.gameFrames[9].frameScore = this.gameFrames[8].frameScore +
                        finalBallData[ballCount] +
                        finalBallData[ballCount + 1] +
                        finalBallData[ballCount + 2];
            }
            else //Tenth frame open (less than ten pins)
            {
                this.gameFrames[9].ball2 =
                        scoreString(finalBallData[ballCount + 1]);
                this.gameFrames[9].ball3 = "";
                this.gameFrames[9].frameScore = this.gameFrames[8].frameScore +
                        finalBallData[ballCount] +
                        finalBallData[ballCount + 1];
            }
        }
        //End tenth frame
    }
}
