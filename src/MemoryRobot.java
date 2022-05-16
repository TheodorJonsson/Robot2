
/**
 * @author Theodor Jonsson, ens18trn
 * @since April 26 2022
 * @version 1.0
 * Course: Objektorienterad Programmeringsmetodik
 */



import java.util.ArrayList;
import java.util.Stack;
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
        if(!visited.contains(position))
        {
            visited.add(position);
        }
        pushMovablePosition(stack, position.getPosToWest());
        pushMovablePosition(stack, position.getPosToEast());
        pushMovablePosition(stack, position.getPosToSouth());
        pushMovablePosition(stack, position.getPosToNorth());
        if(stack.isEmpty())
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
    private void pushMovablePosition(Stack s, Position pos)
    {
        if(isPositionMovable(pos))
        {
            s.push(pos);
        }
    }
    private boolean isPositionMovable(Position pos)
    {
        return ((maze.isMovable(pos) && !(visited.contains(pos))));
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
}