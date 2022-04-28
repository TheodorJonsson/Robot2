import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
/**
 * For all tests to work you need to change directory of the text file to what you desire
 */
class MazeTest {
    private Maze maze;

    @Test
    void checkIfFileIsFound() throws Exception
    {
        Scanner fileScanner = null;
        try
        {
            fileScanner = new Scanner(new File("C:\\Users\\Theodor\\maze.txt"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Couldn't find file");
        }

        Maze maze = new Maze(fileScanner);
    }

    /**
     * Using the example maze from Robot 1 the expected start position should be 1,0
     */
    @Test
    void isStartPosition() throws Exception
    {
        Scanner fileScanner = null;
        try
        {
            fileScanner = new Scanner(new File("C:\\Users\\Theodor\\maze.txt"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Couldn't find file");
        }
        Maze maze = new Maze(fileScanner);
        assertEquals(0, maze.getStart().getY());
        assertEquals(1, maze.getStart().getX());

    }
    @Test
    void checkIfRowsAndColsAreCorrectSize() throws Exception
    {
        Scanner fileScanner = null;
        try
        {
            fileScanner = new Scanner(new File("C:\\Users\\Theodor\\maze.txt"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Couldn't find file");
        }
        Maze maze = new Maze(fileScanner);
        assertEquals(7, maze.getNumRows());
    }
}