import src.Maze;
import src.Position;
import src.Robot;

import java.util.ArrayList;
import java.util.Stack;
/**
 * @author Theodor Jonsson, ens18trn
 * @since April 26 2022
 * @version 1.0
 * Course: Objektorienterad Programmeringsmetodik
 */

/**
 * Class: MemoryRobot
 * Creates a robot which keeps track of the road it has walked. If it can't find
 * a position it hasn't already been in it will go back to where it started and remove
 * that path as a viable option.
 */
public class MemoryRobot implements Robot
{
    private Position position;
    private Position previousPosition;
    private Maze maze;
    private ArrayList<Position> visited;
    private Stack<Position> pathTraveled;

    /**
     * Constructor: MemoryRobot
     *
     * Creates a robot and assign the its position to the start position.
     * assign the default value of a position to the previous position
     * @param maze
     */
    public MemoryRobot(Maze maze)
    {
        this.maze = maze;
        try
        {
            this.position = maze.getStart();
        } catch (IllegalArgumentException iae)
        {
            iae.printStackTrace();
        }
        visited = new ArrayList<Position>();
        pathTraveled = new Stack<Position>();
        this.previousPosition = new Position();
    }

    /**
     * Method: move
     *
     * Pushes all the movable positions around the robot in to a stack
     * then pops the stacks and then moves the robot to that position.
     * Also it puts all the visited positions to a list to keep track which
     * positions the robot has walked on.
     */
    public void move()
    {
        Stack<Position> stack = new Stack<>();
        //System.out.println("(" + position.getX() + "," + position.getY() + ")");
        if(!visited.contains(position))
        {
            visited.add(position);
        }
        if((maze.isMovable(position.getPosToSouth())) && !(visited.contains(position.getPosToSouth())))
        {
            stack.push(position.getPosToSouth());
        }
        if((maze.isMovable(position.getPosToEast())) && !(visited.contains(position.getPosToEast())))
        {
            stack.push(position.getPosToEast());
        }
        if((maze.isMovable(position.getPosToNorth())) && !(visited.contains(position.getPosToNorth())))
        {
            stack.push(position.getPosToNorth());
        }
        if((maze.isMovable(position.getPosToWest())) && !(visited.contains(position.getPosToWest())))
        {
            stack.push(position.getPosToWest());
        }
        if(stack.isEmpty() == true)
        {
            setPosition(pathTraveled.peek());
            pathTraveled.pop();
        }
        else
        {
            setPosition(stack.peek());
            pathTraveled.push(stack.peek());
            visited.add(stack.peek());

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