# bowlingstats
Development of a program to enter, save, and analyze bowling games.

This is a small program currently in development, to serve two purposes.  The first purpose is to give me a tool to check the progress of bowling (i.e. ten pin bowling), one of my hobbies.  I used to do that on a word processor, and more recently, I have been using spreadsheets.  The second purpose is for practicing programming, which I am trying to learn.  My main language at this time is Java, but I am concomitantly learning C#.

Current features (1-21-17):
Right now, all it does is take a game, as entered in a string, and separate it into frames (denoting strikes and spares), as well as calculate the score for each frame, then print out the score in roughly a bowling score format.
The program has seven games hard-coded into it, rather than reading from a file as I intend to make it.

Short-term upcoming changes:
-Logging of date information.
-The ability to load past games from and save new games into a file.
-Notating splits.

Long-term upcoming changes:
-A more user-friendly interface (e.g. Java Swing).
-Calculating averages over a selectable number of games or date range
-Listing top scores per frame (e.g. best scores as of the 5th frame)
-Calculating averages of pins knocked for a selectable range
-Bar graphs showing pin distributions on the first throw (e.g. for a range of 50 throws, how many were strikes, how many were 9's, etc.)

Revision history:
1-21-17
-Corrected handling of bonus frames.  Instead of showing bonus balls in separate frames, they are included in the tenth frame and correctly calculated.
-Aesthetics- added frame count headers.
-Hard-coded more games for diagnostic purposes.
