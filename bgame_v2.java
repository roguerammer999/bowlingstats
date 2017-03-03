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
    
    public String gameDate;
    public String gameOrdinal;
    public frame_v2 gameFrames[] = new frame_v2 [10];
    final int TENPINS = 10;
    
    
    public int [] rawData_Splitter(String rawBallData)
    {
        String [] refinedData = rawBallData.split("-");
        int [] finalBallData = new int[refinedData.length - 2];
        gameDate = refinedData[0];
        gameOrdinal = refinedData[1];
        for(int counter=0; counter<refinedData.length - 2; counter++)
        {
            finalBallData[counter] =
                    Integer.parseInt(refinedData[counter+2]);
        }
        return finalBallData;
    }
    
    public void createFrames(String rawBallData)
    {
        int finalBallData [] = rawData_Splitter(rawBallData);
        int ballCount = 0;
        
        //First nine frames
        for(int frameCount = 0; frameCount < 9; frameCount++)
        {
            this.gameFrames[frameCount] = new frame_v2();
            this.gameFrames[frameCount].ball3 = "";
            if (finalBallData[ballCount] == TENPINS) //A strike on first throw
            {
                this.gameFrames[frameCount].closedFrame = true;
                this.gameFrames[frameCount].ball1 = "X";
                this.gameFrames[frameCount].ball2 = "";
                if(frameCount==0)
                {
                    this.gameFrames[0].frameScore = TENPINS +
                            finalBallData[ballCount + 1] +
                            finalBallData[ballCount + 2];
                }
                else
                    this.gameFrames[frameCount].frameScore =
                            this.gameFrames[frameCount - 1].frameScore + TENPINS +
                            finalBallData[ballCount + 1] +
                            finalBallData[ballCount + 2];
                ballCount++;
            }
            
            else //Two balls in the frame
            {
                if(finalBallData[ballCount]==0)
                    this.gameFrames[frameCount].ball1 = "-";
                else
                    this.gameFrames[frameCount].ball1 =
                        Integer.toString(finalBallData[ballCount]);
                ballCount++;
                
                //For a spare (two balls total 10)
                if(finalBallData[ballCount - 1] +
                        finalBallData[ballCount]==TENPINS)
                {
                    this.gameFrames[frameCount].ball2 = "/";
                    this.gameFrames[frameCount].closedFrame = true;
                    if(frameCount == 0)
                        this.gameFrames[0].frameScore = TENPINS +
                                finalBallData[ballCount + 1];
                    else
                        this.gameFrames[frameCount].frameScore =
                                this.gameFrames[frameCount - 1].frameScore+ 
                                TENPINS +
                                finalBallData[ballCount + 1];
                    ballCount++;
                }
                
                //For an open frame (two balls total less than 10)
                else
                {
                    if(finalBallData[ballCount] == 0 )
                        this.gameFrames[frameCount].ball2 = "-";
                    else
                        this.gameFrames[frameCount].ball2 =
                            Integer.toString(finalBallData[ballCount]);
                    
                    if(frameCount == 0)
                        this.gameFrames[0].frameScore =
                                finalBallData[ballCount - 1] +
                                finalBallData[ballCount];
                    else
                        this.gameFrames[frameCount].frameScore = 
                                this.gameFrames[frameCount - 1].frameScore +
                                finalBallData[ballCount - 1] +
                                finalBallData[ballCount];
                    ballCount++;
                }
            }
        }
        
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
                            Integer.toString(finalBallData[ballCount + 2]);
            }
            else //2nd ball not a strike
            {
                this.gameFrames[9].ball2 =
                        Integer.toString(finalBallData[ballCount + 1]);
                this.gameFrames[9].ball3 =
                            Integer.toString(finalBallData[ballCount + 2]);
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
                    Integer.toString(finalBallData[ballCount]);
            if(finalBallData[ballCount] +
                    finalBallData[ballCount + 1] == TENPINS)//Tenth frame spare
            {
                this.gameFrames[9].ball2 = "/";
                this.gameFrames[9].closedFrame = true;
                this.gameFrames[9].ball3 =
                        Integer.toString(finalBallData[ballCount + 2]);
                this.gameFrames[9].frameScore = this.gameFrames[8].frameScore +
                        finalBallData[ballCount] +
                        finalBallData[ballCount + 1] +
                        finalBallData[ballCount + 2];
            }
            else //Tenth frame open (less than ten pins)
            {
                this.gameFrames[9].ball2 =
                        Integer.toString(finalBallData[ballCount + 1]);
                this.gameFrames[9].ball3 = "";
                this.gameFrames[9].frameScore = this.gameFrames[8].frameScore +
                        finalBallData[ballCount] +
                        finalBallData[ballCount + 1];
            }
        }
            
    }
}
