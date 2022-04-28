import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class RandomRobotTest {
    @Test
    void shouldStartAtStartPosition() throws Exception
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
        Robot robot1 = new RandomRobot(maze);
        robot1.getPosition().equals(maze.getStart());
    }
    @Test
    void shouldMoveSouthFirstStep() throws Exception
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

        Robot robot1 = new RandomRobot(maze);
        robot1.move();
        robot1.getPosition().equals(maze.getStart().getPosToSouth());

    }
    @Test
    void shouldNotBeInDeadEnd() throws Exception
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
        Robot robot1 = new RandomRobot(maze);
        assertEquals(false, robot1.hasReachedDeadEnd());
    }
    @Test
    void shouldNotBeInGoal() throws Exception
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
        Robot robot1 = new RandomRobot(maze);
        assertEquals(false, robot1.hasReachedGoal());
    }
}