# bowlingstats
Development of a program to enter, save, and analyze bowling games.

This is a small program currently in development, to serve two purposes.  The first purpose is to give me a tool to check the progress of bowling (i.e. ten pin bowling), one of my hobbies.  I used to do that on a word processor, and more recently, I have been using spreadsheets.  The second purpose is for practicing programming, which I am trying to learn.  My main language at this time is Java, but I am concomitantly learning C#.

Current features (1-16-17):
Right now, all it does is take a game, as entered in a string, and separate it into frames (denoting strikes and spares), as well as calculate the score for each frame, then print out the score in roughly a bowling score format.
The program has four games hard-coded into it, rather than reading from a file as I intend to make it.

Short-term upcoming changes:
-Handling of bonus frames.  This program is in a very early version, but I have not set it to register those correctly yet.  The current version prints bonuses as "null" rather than blanking them out.  It also treats the two bonus balls as extra frames, rather than listing the scores in the tenth frame.
-The ability to load past games from and save new games into a file.

Long-term upcoming changes:
-A more user-friendly interface (e.g. Java Swing).
-Calculating averages over a selectable number of games or date range
-Listing top scores per frame (e.g. best scores as of the 5th frame)
-Calculating averages of pins knocked for a selectable range
-Bar graphs showing pin distributions on the first throw (e.g. for a range of 50 throws, how many were strikes, how many were 9's, etc.)
