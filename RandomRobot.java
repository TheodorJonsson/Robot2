import java.util.Random;
import java.util.ArrayList;
/**
 * Author: Theodor Jonsson, ens18trn
 * Date: April 13 2022
 * Course: Objektorienterad Programmeringsmetodik
 */

/**
 * Class: RandomRobot
 * Creates a robot which randomly moves in the maze. if it can't find a goal it
 * loop infinitely. If it reaches a dead end it will go to the position it was previously
 * and continue randomly moving.
 */
public class RandomRobot implements Robot
{
    private Position position;
    private Position previousPosition;
    private Maze maze;

    /**
     * Constructor: RandomRobot
     *
     * Creates a robot and assign the its position to the start position.
     * assign the default value of a position to the previous position
     * @param maze
     */
    public RandomRobot(Maze maze)
    {
        this.maze = maze;
        try
        {
            this.position = maze.getStart();
        } catch (IllegalArgumentException iae)
        {
            iae.printStackTrace();
        }

        this.previousPosition = new Position();
    }

    /**
     * Method: move
     *
     * Prints out the current position.
     * Then checks if there is any movable position around it if not does nothing.
     * If there are any available position firstly checks
     * if the robot has reached a dead end if it has moves to the previous position
     * else it chooses and random position of the movable positions and moves to it.
     *
     */
    public void move()
    {
        //System.out.println("(" + position.getX() + "," + position.getY() + ")");
        ArrayList<Position> validMoves = new ArrayList<>();

        if(maze.isMovable(position.getPosToSouth()))
        {
            validMoves.add(position.getPosToSouth());
        }
        if(maze.isMovable(position.getPosToEast()))
        {
            validMoves.add(position.getPosToEast());
        }
        if(maze.isMovable(position.getPosToNorth()))
        {
            validMoves.add(position.getPosToNorth());
        }
        if(maze.isMovable(position.getPosToWest()))
        {
            validMoves.add(position.getPosToWest());
        }
        if(validMoves.isEmpty() == false)
        {
            if(hasReachedDeadEnd() == true)
            {
                Position p = this.getPosition();
                this.setPosition(previousPosition);
                previousPosition = p;
            }
            else
            {

                int bound = validMoves.size();

                Random rand = new Random();
                int rand_int = rand.nextInt(bound);

                if (previousPosition != null) {
                   while (previousPosition.equals(validMoves.get(rand_int))) {
                       rand_int = rand.nextInt(bound);
                   }
               }
                Position p = validMoves.get(rand_int);

                previousPosition = getPosition();
                this.setPosition(p);
            }
        }
    }

    /**
     * Method: getPosition
     * Gets the current position of the object.
     * @return position
     */
    public Position getPosition()
    {
        return this.position;
    }

    /**
     * Method: setPosition
     * Sets the position of the current object to the position
     * from the parameter
     * @param p, which is the position to move to
     */
    private void setPosition(Position p)
    {
        this.position = p;
    }

    /**
     * Method: hasReachedGoal
     * Checks if the current position is the position of the goal.
     * @return true if the position is the goal, false if it's not.
     */
    public boolean hasReachedGoal()
    {
        return this.maze.isGoal(this.position);
    }
    /**
     * Method: hasReachedDeadEnd
     * Checks all the position around the current if they are movable
     * and not the previous position.
     * @return true if all positions are not movable and not the previous position
     *          false if one of them are.
     */
    public boolean hasReachedDeadEnd()
    {
        if(this.maze.isMovable(position.getPosToSouth()) && !this.previousPosition.equals(position.getPosToSouth()))
        {
            return false;
        }
        if(this.maze.isMovable(position.getPosToEast()) && !this.previousPosition.equals(position.getPosToEast()))
        {
            return false;
        }
        if(this.maze.isMovable(position.getPosToNorth()) && !this.previousPosition.equals(position.getPosToNorth()))
        {
            return false;
        }
        if(this.maze.isMovable(position.getPosToWest()) && !this.previousPosition.equals(position.getPosToWest()))
        {
            return false;
        }
        return true;
    }
}
