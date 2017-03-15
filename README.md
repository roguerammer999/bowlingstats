## bowlingstats: A program to enter, save, and analyze bowling games.

This is a small program currently in development.  It checks the progress of bowling, one of my hobbies, hopefully more efficiently than a word processor or spreadsheet, then can display statistics for one or more games.  I am also using it to learn and practice programming, and it is written in Java, currently my main language.

NOTE: These are the only relevant source files.  Other source files pertain to the earlier version:
- **[bgame_v2.java](bgame_v2.java)** and
- **[bowlingstats_prelim2.java](bowlingstats_prelim2.java)**

#### Current features (3-14-17):
- It takes a String of all games, listed with date, ordinal (e.g. 3rd game of that day), and ball data (e.g. 7 pins, 3 pins) and separates it into individual games, then separates the games into game data with strikes/spares, frame, scores, date, and ordinal.  One or more games can be selected to display.  Closed frames are displayed with bold font and shaded slightly green.
- [Output image](output_v2_170314.png)

#### Short-term upcoming changes:
- Basic statistics and analytics.
- The ability to load past games from and save new games into a file.
- Notating splits.

#### Long-term upcoming changes:
- Calculating averages over a selectable number of games or date range
- Listing top scores per frame (e.g. best scores as of the 5th frame)
- Calculating averages of pins knocked for a selectable range
- Bar graphs showing pin distributions on the first throw (e.g. for a range of 50 throws, how many were strikes, how many were 9's, etc.)


#### Revision history:
- 3-14-17
  - Major overhaul- changed display from one game only to being able to view multiple selected games at once.  The games are now shown in a window with a scroll bar.
  - [Output image](output_v2_170314.png)

- 3-07-17
  - Combined input Strings into a single input String to mimic a text file.  A future change will draw directly from a text file.
  - Added two games to test the ordinals feature, e.g. 1st game of 12/30/2016 is 3rd game overall, and it shows both numbers.
  - Added "Frame #" headers back in.
  - Added scroll bar to accommodate it having 9 games instead of 7 games.
  - Added ordinals feature.  The last version showed which ordinal for that day, but it now shows both ordinals for that day as well as overall.  The overall ordinals is number formatted to four digits (e.g. "0001" for the first game).
  - Set code to get indices/indexes instead of a crude workaround that pulled numbers that just *corresponded to* the correct index.
  - [Output image](output_v2_170307.png)

- 3-03-17
  - Minor changes to bgame_v2 code only.  Zero-pin throws are now correctly recorded as dashes.  Reduced bgame_v2 from 173 lines to 163 lines; it is also easier to follow along.
  - No change in output graphics (see 3-02-17 for latest output).
  
- 3-02-17
  - Revamped the whole visual interface to look more like a bowling scorecard.  It is more consistent, as well as much easier to read.  Numbers are in boxes instead of being separated by spaces.
  - Games now store date and ordinal (e.g. 3rd game of the day) data.  This is also displayed when a game is loaded.
  - [Output image](output_v2_170302.png)

- 2-25-17
  - Fixed the font alignment to be more consistent between games.  (the previous version shifted around the header text)
  - Shaded closed frames light green.
  - Fixed an error in the scoring system.
  - [Output image](output_v2_170225.png)

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
