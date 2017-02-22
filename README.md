## bowlingstats: A program to enter, save, and analyze bowling games.

This is a small program currently in development.  It checks the progress of bowling, one of my hobbies, hopefully more efficiently than a word processor or spreadsheet, then can display statistics for one or more games.  I am also using it to learn and practice programming, and it is written in Java, currently my main language.


####Current features (2-18-17):
- It takes a game, entered as a string of ball data (e.g. 7 pins, 3 pins), and separates it into useful game data (strikes/spares, frames, scores).  The string is simply numbers of pins separated by a dash (e.g. "7-2-10-8-1-9-1...").  Then a game can be selected, and its game data will be displayed.

####Short-term upcoming changes:
- Logging of date information.
- The ability to load past games from and save new games into a file.
- Notating splits.

####Long-term upcoming changes:
- Calculating averages over a selectable number of games or date range
- Listing top scores per frame (e.g. best scores as of the 5th frame)
- Calculating averages of pins knocked for a selectable range
- Bar graphs showing pin distributions on the first throw (e.g. for a range of 50 throws, how many were strikes, how many were 9's, etc.)

####Revision history:

- 2-18-17
  - Made a complete version 2 (in the works the last couple weeks).  It has a Java Swing GUI with frame and score data showing for each frame, as well as a selection area from which to select games.
  - Formatting of the display area to make it look nice.
  - Redid the code for breaking the raw data into game data.  It is slightly longer but much easier to follow.
  - Set fonts for open and closed frames, also increasing the font size.
  - [Output image](output_v2_170218.png)

- 1-23-17
  - Added new class with Java Swing GUI for displaying frame count, frame data, and score data.
  - Slight reformat of spacking to make console output more concise.
  - [Output image](output_v1_170123.png)

- 1-21-17
  - Corrected handling of bonus frames.  Instead of showing bonus balls in separate frames, they are included in the tenth frame and correctly calculated.
  - Aesthetics- added frame count headers.
  - Hard-coded more games for diagnostic purposes.
  - [Output image](output_oldconsole_170121.png)
