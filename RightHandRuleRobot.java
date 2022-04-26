import java.util.Random;
import java.util.ArrayList;
/**
 * @author Theodor Jonsson, ens18trn
 * @since April 26 2022
 * @version 1.0
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
     * Assign the default value of a position to the previous position.
     * Depending where the start is it will set the starting directions accordingly
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
     * Moves the robot in the maze. It will prioritize going right
     * then forwards then left and lastly backwards. It will get the directions
     * after each movement.
     */
    public void move() {
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

    /**
     * Method: setDirections
     * Checks with the previous position of the robot to get which direction
     * the robot is facing now
     */
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

    /**
     * Method: getRight
     * @return the position right of the robot
     */
    private Position getRight()
    {
        return right;
    }

    /**
     * Method: getForwards
     * @return the position forwards of the robot
     */
    private Position getForwards()
    {
        return forwards;
    }
    /**
     * Method: getLeft
     * @return the position left of the robot
     */
    private Position getLeft()
    {
        return left;
    }

    /**
     * Method: setStartDirections
     * Checks which position from the start position is movable
     * then sets the directions accordingly
     */
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
    }
}