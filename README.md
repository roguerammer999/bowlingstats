# bowlingstats
Development of a program to enter, save, and analyze bowling games.

This is a small program currently in development, to serve two purposes.  The first purpose is to give me a tool to check the progress of bowling (i.e. ten pin bowling), one of my hobbies.  I used to do that on a word processor, and more recently, I have been using spreadsheets.  The second purpose is for practicing programming, which I am trying to learn.

Current features (1-10-17):
Right now, all it does is take a game, as entered in a string, and separate it into frames (denoting strikes and spares), as well as calculate the score for each frame.
There are two games hard-coded into the program.  Because bonus frames are not correctly configured, it treats the tenth frame (a spare) as a conventional spare, adding the next ball's score twice instead of once as it should.  I have not configured the spacing of the scores (e.g. two-digit and three-digit scores needing more/less space), so the alignment is very rough.

Upcoming features in the near future:
-Handling of bonus frames.  This program is in a very early version, but I have not set it to register those correctly yet.
-The ability to save past games in a file, as well as enter new games.

Upcoming features in the farther future

-A more user-friendly interface (e.g. Java Swing).
-Calculating averages over a selectable number of games or date range
-Listing top scores per frame (e.g. best scores as of the 5th frame)
-Calculating averages of pins knocked for a selectable range
-Bar graphs showing pin distributions on the first throw (e.g. for a range of 50 throws, how many were strikes, how many were 9's, etc.)
