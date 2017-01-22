public class bowlingstats_prelim1
{
    public static void main(String[] args)
    {
        bgame_v1 g1_2016_12_30 =
                new bgame_v1("4-6-7-1-7-1-1-7-9-1-8-0-7-0-0-10-10-9-0");
        bgame_v1 g2_2016_12_30 =
                new bgame_v1("9-1-8-0-8-2-7-0-10-8-2-10-9-0-10-0-10-9");
        bgame_v1 g3_2016_12_30 = 
                new bgame_v1("9-0-10-0-9-8-2-10-9-0-6-0-0-8-7-3-10-5-0");
        bgame_v1 g4_2016_12_30 =
                new bgame_v1("9-0-7-1-8-2-9-0-10-10-5-5-9-0-7-2-8-0");
        bgame_v1 g5_2016_12_30 =
                new bgame_v1("9-0-10-10-7-3-8-0-3-2-10-6-0-10-8-2-7");
        bgame_v1 g6_2016_12_30 =
                new bgame_v1("1-0-10-9-0-0-10-10-9-1-5-3-9-1-6-1-10-6-4");
        bgame_v1 g7_2016_12_30 =
                new bgame_v1("8-2-5-3-9-0-9-1-9-1-7-1-0-9-9-1-10-7-1");
    }
    
}

//Class "bgame_v1" is meant to refer to an instance of a bowling game.  It
//should contain a list of balls thrown (i.e. how many pins per ball)
//as well as frame data (spares and strikes) and score data.
//The "v1" is arbitrary.  I didn't want to call it "bgame" because I expect
//to make future versions in the same directory.

class bgame_v1
{
    private String balls_str[];
    //Array "balls[]" is not created yet as its length will be determined
    //in method "ballsplitter".
    private int balls[];
    
    //"bg_frames" (bowling game frames) is an array of frame_v1 objects.
    //Each frame contains a score for the first ball and (if the first was
    //not a strike) second ball, as well as the score at the end of the frame.
    private frame_v1 bg_frames[] = new frame_v1 [12];
    private int frameCounter;
    
    //This is the only valid constructor.  It accepts a string that
    //consists of each ball (e.g. 9 pins the first ball, 1 pin the next)
    //separated with a dash as a delimiter.  It immediately calls method
    //"ballsplitter", which takes the score string as a parameter.
    public bgame_v1(String score_breakdown)
    {
        ballsplitter(score_breakdown);
        framecreator();
        test_print();
    }
    
    private class frame_v1
    {
        String ball1, ball2, ball3;
        int score = 0;
    }    
    
    //The method "ballsplitter" first splits the String parameter listing the
    //game by its ball scores into an array String, then parses the array
    //String into an array of integers.
    
    private void ballsplitter (String score_breakdown)
    {
        this.balls_str = score_breakdown.split("-");
        this.balls = new int[balls_str.length];
        for(int counter=0; counter<balls_str.length; counter++)
        {
            balls[counter] = Integer.parseInt(balls_str[counter]);
        }
    }
    
    
    private void framecreator()
    {
        frameCounter = -1;
        //"prevFrame" is the most recent ball on which the frame was changed.
        int prevFrame = -1;
        int ballthrow = 0;
        for(; ballthrow<balls_str.length; ballthrow++)
        {
            if (ballthrow - prevFrame == 1)
            {
                frameCounter++;
                if(frameCounter==9)
                    break;
                bg_frames[frameCounter] = new frame_v1();
                if(frameCounter>0)
                {
                    bg_frames[frameCounter].score +=
                            bg_frames[frameCounter-1].score;
                }
                if (balls[ballthrow] != 10)
                {
                    bg_frames[frameCounter].ball1 = 
                            Integer.toString(balls[ballthrow]);
                }
                else if (balls[ballthrow] == 10)
                {
                    bg_frames[frameCounter].ball1 = " ";
                    bg_frames[frameCounter].ball2 = "X";
                    if(frameCounter<9)
                    {
                    bg_frames[frameCounter].score +=
                            balls[ballthrow+1];
                    bg_frames[frameCounter].score +=
                            balls[ballthrow+2];
                    }
                    else;
                    prevFrame = ballthrow;
                }
            }
            else if (ballthrow - prevFrame == 2)
            {
                prevFrame = ballthrow;
                if (balls[ballthrow] == 10-balls[ballthrow-1])
                {
                    bg_frames[frameCounter].ball2 = "/";
                    if(frameCounter<9)
                    bg_frames[frameCounter].score +=
                            balls[ballthrow+1];
                    else;
                }
                else if (balls[ballthrow] < 10-balls[ballthrow-1])
                {
                    bg_frames[frameCounter].ball2 = 
                            Integer.toString(balls[ballthrow]);
                }
            }
            
            bg_frames[frameCounter].score += balls[ballthrow];
        }
        tenthFrame(ballthrow);
    }
    
    //Far less convoluted to keep the tenth frame, which has different rules
    //than the other nine, in its own method.
    private void tenthFrame(int ballcounter)
    {
        int totalscore = 0;
        bg_frames[9] = new frame_v1();
        bg_frames[9].ball3 = " ";
        if(balls[ballcounter]==10)
        {
            bg_frames[9].ball1 = "X";
            
            if(balls[ballcounter+1]==10)
            {
                bg_frames[9].ball2 = "X";
                if(balls[ballcounter+2]==10)
                    bg_frames[9].ball3 = "X";
                else
                    bg_frames[9].ball3 = Integer.toString(balls[ballcounter+2]);
            }
            else
            {
                bg_frames[9].ball2 = Integer.toString(balls[ballcounter+1]);
                if(balls[ballcounter+1]+balls[ballcounter+2]==10)
                    bg_frames[9].ball3 = "/";
                else
                    bg_frames[9].ball3 = Integer.toString(balls[ballcounter+2]);
            }
        }
        else
        {
            bg_frames[9].ball1 = Integer.toString(balls[ballcounter]);
            if(balls[ballcounter]+balls[ballcounter+1]==10)
            {
                bg_frames[9].ball2 = "/";
                if(balls[ballcounter+2]==10)
                    bg_frames[9].ball3 = "X";
                else
                    bg_frames[9].ball3 = Integer.toString(balls[ballcounter+2]);
            }
            else
                bg_frames[9].ball2 = Integer.toString(balls[ballcounter+1]);
        }
        totalscore += balls[ballcounter];
        totalscore += balls[ballcounter+1];
        try
        {
            totalscore += balls[ballcounter+2];
        }
        catch(ArrayIndexOutOfBoundsException exc1)
                {}
        bg_frames[9].score = bg_frames[8].score + totalscore;
    }
    
    
    private void test_print()
    {
        for(int counter = 1; counter<10; counter++)
        {
            System.out.print("----"+ counter + "---|");
        }
        System.out.println("---10---|");
        System.out.print("  ");
        for(int counter = 0; counter<9; counter++)
        {
            System.out.print(" " + bg_frames[counter].ball1 + " " +
                            bg_frames[counter].ball2 + "  |  ");
        }
        System.out.println(" " + bg_frames[9].ball1 + " " +
                bg_frames[9].ball2 + " " + bg_frames[9].ball3 + "|");
        System.out.print("  ");
        
        for(int counter = 0; counter<10; counter++)
        {
            String spacedel;
            if(bg_frames[counter] != null)
            {
                if(bg_frames[counter].score < 10)
                    spacedel = "    ";
                else if(bg_frames[counter].score < 100)
                    spacedel = "   ";
                else
                    spacedel = "  ";
                System.out.print(spacedel + bg_frames[counter].score + " |  ");
            }
            else
                ;
        }
        System.out.println("\n\n");
    }
}
