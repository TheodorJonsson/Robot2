import java.util.Random;
import java.util.ArrayList;
/**
 * Author: Theodor Jonsson, ens18trn
 * Date: April 13 2022
 * Course: Objektorienterad Programmeringsmetodik
 */

/**
 * Class: RightHandRuleRobot
 * Creates a robot which goes through the maze as a human would, always having a
 * wall a the right of them and walking alongside it.
 */
public class RightHandRuleRobot implements Robot {
    private Position position;
    private Position previousPosition;
    private Position forwards;
    private Position right;
    private Position left;
    private Maze maze;

    /**
     * Constructor: RightHandRuleRobot
     * Creates a robot and assign the its position to the start position.
     * assign the default value of a position to the previous position
     *
     * @param maze
     */
    public RightHandRuleRobot(Maze maze) {
        this.maze = maze;
        try {
            this.position = maze.getStart();
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
        setStartDirections();
        this.previousPosition = new Position();
    }

    /**
     * Method: move
     * <p>
     * Prints out the current position.
     * Then checks if there is any movable position around it if not does nothing.
     * If there are any available position firstly checks
     * if the robot has reached a dead end if it has moves to the previous position
     * else it chooses and random position of the movable positions and moves to it.
     */
    public void move() {
        //System.out.println("(" + position.getX() + "," + position.getY() + ")");
        if(maze.isMovable(getRight()))
        {
            previousPosition = getPosition();
            setPosition(getRight());
        }
        else if(maze.isMovable(getForwards()))
        {
            previousPosition = getPosition();
            setPosition(getForwards());

        }
        else if(maze.isMovable(getLeft()))
        {
            previousPosition = getPosition();
            setPosition(getLeft());
        }
        else
        {
            Position p = this.getPosition();
            setPosition(previousPosition);
            previousPosition = p;
        }
        setDirections();
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

    private void setDirections()
    {
        if(this.previousPosition.getY() - 1 == this.position.getY())
        {
            forwards = position.getPosToNorth();
            right = position.getPosToEast();
            left = position.getPosToWest();
        }
        if(this.previousPosition.getX() + 1 == this.position.getX())
        {
            forwards = position.getPosToEast();
            right = position.getPosToSouth();
            left = position.getPosToNorth();
        }
        if(this.previousPosition.getY() + 1 == this.position.getY())
        {
            forwards = position.getPosToSouth();
            right = position.getPosToWest();
            left = position.getPosToEast();
        }
        if(this.previousPosition.getX() - 1 == this.position.getX())
        {
            forwards = position.getPosToWest();
            right = position.getPosToNorth();
            left = position.getPosToSouth();
        }
    }
    private Position getRight()
    {
        return right;
    }
    private Position getForwards()
    {
        return forwards;
    }
    private Position getLeft()
    {
        return left;
    }
    private void setStartDirections()
    {
        if(maze.isMovable(position.getPosToSouth()))
        {
            forwards = position.getPosToSouth();
            right = position.getPosToWest();
            left = position.getPosToEast();
        }
        else if(maze.isMovable(position.getPosToNorth()))
        {
            forwards = position.getPosToNorth();
            right = position.getPosToEast();
            left = position.getPosToWest();
        }
        else if(maze.isMovable(position.getPosToWest()))
        {
            forwards = position.getPosToWest();
            right = position.getPosToNorth();
            left = position.getPosToSouth();
        }
        else if(maze.isMovable(position.getPosToEast()))
        {
            forwards = position.getPosToEast();
            right = position.getPosToSouth();
            left = position.getPosToNorth();
        }
        /*
        if(position.getY() <= 0)
        {
            forwards = position.getPosToSouth();
            right = position.getPosToWest();
            left = position.getPosToEast();
        }
        if(position.getY() > 0)
        {
            forwards = position.getPosToNorth();
            right = position.getPosToEast();
            left = position.getPosToWest();
        }
         */
    }
}