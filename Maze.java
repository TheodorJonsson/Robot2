import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Theodor Jonsson, ens18trn
 * @since April 13 2022
 * @version 1.0
 * Course: Objektorienterad Programmeringsmetodik
 */

/**
 * Class name: Maze
 * The class reads a text file from a scanner to a maze. RandomRobot uses this class to
 * check if position is valid or not. The robot also uses this class to get the start
 * and check if it has reached goal or not
 */

public class Maze
{
    private ArrayList<String> maze = new ArrayList<>();
    private int rows;
    /**
     * Constructor: Maze
     * Reads a from a scanner and puts the lines in to a arraylist of strings
     *
     * @params scanner, which is the scanned file from the argument
     * @throws Exception, checks if the scanner is empty or not
     */
    public Maze(Scanner scanner) throws Exception
    {
        int amount_of_lines = 0;
        while(scanner.hasNextLine())
        {
            this.maze.add(scanner.nextLine());
            amount_of_lines++;
        }
        this.rows = maze.size();

        if(amount_of_lines == 0)
        {
            throw new Exception("Scanner is empty");
        }

        isMazeValid();
    }

    /**
     * Method: isMazeValid
     * Checks if the maze has any non valid characters in it
     */
    private void isMazeValid()
    {
        for(int i = 0; i < this.rows; i++)
        {
            String rowIndex = maze.get(i);
            {
                for(int j = 0;j < rowIndex.length(); j++)
                {
                    char character = rowIndex.charAt(j);
                    if((character != ' ') && (character != '*') && (character != 'S') && (character != 'G'))
                    {
                        throw new IllegalArgumentException("Non valid character is in the maze");
                    }
                }
            }
        }
    }

    /**
     * Method: isMovable
     * Firstly checks if the position is in bounds of the maze.
     * Then checks if the position is a goal or a space
     *
     * @param p, which is the position to be checked
     * @return true if the position is a goal or a space, false if the position is out of bounds
     * or a non valid character
     */
    public boolean isMovable(Position p)
    {
        int cols = this.getNumColumns();
        int rows = this.getNumRows();

        if(((p.getX() < 0) || (p.getX() > (cols - 1)) || (p.getY() < 0) || p.getY() > (rows - 1)))
        {
            return false;
        }
        String rowIndex = maze.get(p.getY());
        if((rowIndex.charAt(p.getX()) == 'S'))
        {
            return false;
        }
        if((rowIndex.charAt(p.getX()) != '*'))
        {
            return true;
        }
        return false;
    }

    /**
     * Method: isGoal
     * Checks if the position is a goal
     *
     * @param p, which is the position to be checked
     * @return true if the position is a goal, false if its anything else
     */
    public boolean isGoal(Position p)
    {
        String rowIndex = maze.get(p.getY());
        if(rowIndex.charAt(p.getX()) == 'G')
        {
            return true;
        }
        return false;
    }

    /**
     * Method: getStart
     * Searches for the position of the start if it can't find it
     * throws a illegal argument.
     * @return startPos, which is the position of the start if it can find it.
     * @throws IllegalArgumentException
     */
    public Position getStart() throws IllegalArgumentException
    {

        Position startPos = new Position();
        for(int i = 0; i < this.rows; i++)
        {
            String rowIndex = maze.get(i);
            int colIndex = rowIndex.indexOf('S');
            if(colIndex != -1)
            {
                startPos = new Position(colIndex, i);
                return startPos;
            }
        }

        if (startPos.getX() == -1 && startPos.getY() == -1) {
            throw new IllegalArgumentException("Could not find Start.");
        }
        return startPos;

    }

    /**
     * Method: getNumColumns
     * Searches through the maze to get the largest column in the mze
     * @return maxLength, which is the largest column
     */
    public int getNumColumns()
    {
        int maxLength = 0;
        for(int i = 0; i < this.rows; i++)
        {

            if(maxLength < maze.get(i).length())
            {
                maxLength = maze.get(i).length();
            }
        }
        return maxLength;

    }

    /**
     * Method: getNumRows
     *
     * @return this.rows, which is the amount of rows in the maze
     */
    public int getNumRows()
    {
        return this.rows;
    }
    /*
     * ----NOT USED CODE----
     * Method used for checking where robot is and changing the corresponding string
     * to show a 'o' to show where the robot is and then change the string back to its original value
     */
    public void printMaze(Position playerPos)
    {
        for(int i = 0; i < this.rows; i++)
        {
            if(playerPos.getY() == i)
            {
                String changeString = maze.get(i);
                String temp = changeString;
                char ch = '+';
                changeString = changeString.substring(0, playerPos.getX()) + ch + changeString.substring(playerPos.getX() + 1);
                maze.set(i, changeString);
                System.out.println(maze.get(i));
                maze.set(i, temp);

            }
            else
            {
                System.out.println(maze.get(i));
            }
        }

    }


}

