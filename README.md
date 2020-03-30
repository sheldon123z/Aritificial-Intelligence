## This is a Artificial intelligence practice homework of USC CSCI561

### The code uses A* search, UCS search, BFS search methods in mutiple worlds to get a best routine to the destination


Please don't use it to cheat 
Please don't copy the code directly 

## Input:
Input: The file input.txt in the current directory of your program will be formatted as follows:

* First line: Instruction of which algorithm to use, as a string: BFS, UCS or A*

* Second line: Two strictly positive 32-bit integers separated by one space character, for “W H” the number of columns (width) and rows (height) for all grid worlds.

* Third line: Three positive 32-bit integers separated by a space character, “Year X Y” for the initial year-location, where 0 ≤ X ≤ W-1 and 0 ≤ Y ≤ H-1. That is, we use 0-based indexing into a grid world: X increases when moving east, Y increases when moving north, and (0,0) is the southwest corner of the grid world.

* Fourth line: Three positive 32-bit integers separated by a space character, “Year X Y” for the target year-location, where 0 ≤ X ≤ W-1 and 0 ≤ Y ≤ H-1. That is, we use 0-based indexing into a grid world: X increases when moving east, Y increases when moving north, and (0,0) is the southwest corner of the grid world.

* Fifth line: A strictly positive 32-bit integer N, indicating the number of time-travel channels in the configuration.

* Next N lines: Four positive 32-bit integers separated by one space character, for ▪ “Year X Y Year”, specifying the coordinates of the time-travel channel and the two years at the ends of the channel. Again, we have 0 ≤ X ≤ W-1, 0 ≤ Y ≤ H-1, and 0-based indexing into the grid worlds.

Input example:  
A*  
100 200  
2020 8 12   
3472 13 17   
3   
2020 12 16 3011  
3011 56 77 2487  
3011 14 19 3472  


### Output: The file output.txt that your program creates in the current directory should be formatted as follows:

* First line: A single integer C, indicating the total cost of your found solution. If no solution was found (target year-location is unreachable from the given initial year-location), then write the word “FAIL”.

* Second line: A single integer N, indicating the total number of steps in your solution including the starting position.

* N lines: Report the steps in your solution travelling from the initial year-location to the target year-location as were given in the input.txt file.

* Write out one line per step with cost. Each line should contain a tuple of four integers: Year, X, Y, Cost, separated by a space character, specifying the year-location with the step cost to visit that year-location by your agent during its (time) traveling from the initial year-location to the target year-location.

For example, the following is a sample output of the corresponding input above:  
164
165  
556 121 232 0  
556 121 231 1  
556 121 230 1  
556 121 229 1  
556 121 228 1  
556 121 227 1  
556 121 226 1  
556 121 225 1  
556 121 224 1  
## Run method:
Import to Eclipse   
select test cases and copy them to the Back_to_the_future folder  
click run

