public class BK_superbowl_prelim1
{
    public static void main(String[] args)
    {
        bgame1 g1_2016_12_30 =
                new bgame1("4-6-7-1-7-1-1-7-9-1-8-0-7-0-0-10-10-9-0");
        bgame1 g2_2016_12_30 =
                new bgame1("9-1-8-0-8-2-7-0-10-8-2-10-9-0-10-0-10-9");
    }
    
}

class bgame1
{
    private String balls_str[];
    private int balls[] = new int [21];
    private frame_v1 bg_frames[] = new frame_v1 [12];
    private int framecounter;
    
    
    private void ballsplitter(String score_breakdown)
    {
        this.balls_str = score_breakdown.split("-");
        for(int counter=0; counter<balls_str.length; counter++)
        {
            balls[counter] = Integer.parseInt(balls_str[counter]);
        }
    }
    
    private void framecreator()
    {
        framecounter = -1;
        int lastframe;
        lastframe = -1;
        for(int ballthrow=0; ballthrow<21; ballthrow++)
        {
            if (ballthrow - lastframe == 1)
            {
                framecounter++;
                bg_frames[framecounter] = new frame_v1();
                if(framecounter>0)
                {
                    bg_frames[framecounter].score += bg_frames[framecounter-1].score;
                }
                if (balls[ballthrow] != 10)
                {
                    bg_frames[framecounter].ball1 = 
                            Integer.toString(balls[ballthrow]);
                }
                else if (balls[ballthrow] == 10)
                {
                    bg_frames[framecounter].ball1 = " ";
                    bg_frames[framecounter].ball2 = "X";
                    bg_frames[framecounter].score +=
                            balls[ballthrow+1];
                    bg_frames[framecounter].score +=
                            balls[ballthrow+2];
                    lastframe = ballthrow;
                }
            }
            else if (ballthrow - lastframe == 2)
            {
                lastframe = ballthrow;
                if (balls[ballthrow] == 10-balls[ballthrow-1])
                {
                    bg_frames[framecounter].ball2 = "/";
                    bg_frames[framecounter].score +=
                            balls[ballthrow+1];
                }
                else if (balls[ballthrow] < 10-balls[ballthrow-1])
                {
                    bg_frames[framecounter].ball2 = 
                            Integer.toString(balls[ballthrow]);
                }
            }
            
            bg_frames[framecounter].score += balls[ballthrow];
        }
    }
    
    private void framescorer()
    {
        for (int framect = 1; framect < 10; framect++)
        {
            bg_frames[framect].score += bg_frames[framect-1].score;
            if(bg_frames[framect].ball2.equals("/"))
                if(bg_frames[framect+1].ball2.equals("X"))
                    bg_frames[framect].score+=10;
                else if(!bg_frames[framect+1].ball2.equals("X"))
                    bg_frames[framect].score+=0;
                bg_frames[framect].score++;
        }
    }
    
    private void test_print()
    {
        for(int counter = 0; counter<12; counter++)
        {
            if(bg_frames[counter] != null)
            {
                System.out.print(bg_frames[counter].ball1 + " " +
                            bg_frames[counter].ball2 + "  |  ");
            }
            else
                ;
        }
        System.out.println("");
        for(int counter = 0; counter<12; counter++)
        {
            if(bg_frames[counter] != null)
            {
                System.out.print("   " + bg_frames[counter].score + "  ");
            }
            else
                ;
        }
        System.out.println("\n\n");
    }
    
    public bgame1(String score_breakdown)
    {
        ballsplitter(score_breakdown);
        framecreator();
        test_print();
    }
    
    private class frame_v1
    {
        String ball1, ball2;
        int score = 0;
    }
}
