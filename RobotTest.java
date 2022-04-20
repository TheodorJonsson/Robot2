import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Author: Theodor Jonsson, ens18trn
 * Date: April 13 2022
 * Course: Objektorienterad Programmeringsmetodik
 *
 */

/**
 * Class: RobotTest
 * main function for RandomRobot used for testing.
 * Gets the file from the arguments and inputs it to a scanner which is
 * used for creating the maze. test
 *
 * Loops through the move method from the robot until it has reached the goal
 */

public class RobotTest
{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(args[0]));
        try {
            Maze maze = new Maze(input);
            RandomRobot robot1 = new RandomRobot(maze);
            while(!robot1.hasReachedGoal())
            {
                robot1.move();
                if(robot1.hasReachedGoal())
                {
                    System.out.println("Robot has found the goal!");
                }
            /*
             * ----NOT USED CODE----
             * Code to visually show the labyrinth and wipe the console
             * for the next frame. Didn't want to remove it,
             * hopefully not detrimental to the assignment.
             *
                try
                {
                  Thread.sleep(50);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                if(!robot1.hasReachedGoal()) {
                    System.out.print("\033[H\033[2J");
                }

             */
            }

        } catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
