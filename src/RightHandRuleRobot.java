

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

        if(getPrioritizedPosition().equals(previousPosition))
        {
            Position p = this.getPosition();
            setPosition(previousPosition);
            previousPosition = p;
        }
        else
        {
            previousPosition = getPosition();
            setPosition(getPrioritizedPosition());
        }
        setDirections();
    }

    /**
     * Firstly checks if the position to the right is movable
     * then forwards and lastly left. If all of these aren't
     * movable it returns the previousPosition
     * @return the prioritized position
     */
    private Position getPrioritizedPosition()
    {
        if(maze.isMovable(getRight()))
        {
            return getRight();
        }
        else if(maze.isMovable(getForwards()))
        {
            return getForwards();

        }
        else if(maze.isMovable(getLeft()))
        {
            return getLeft();
        }
        return previousPosition;
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
     * Method: setDirections
     * Checks with the previous position of the robot to get which direction
     * the robot is facing now
     */
    private void setDirections()
    {
        if(this.previousPosition.getY() - 1 == this.position.getY())
        {
            northForwards();
        }
        if(this.previousPosition.getX() + 1 == this.position.getX())
        {
            eastForwards();
        }
        if(this.previousPosition.getY() + 1 == this.position.getY())
        {
            southForwards();
        }
        if(this.previousPosition.getX() - 1 == this.position.getX())
        {
            westForwards();
        }
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
            southForwards();
        }
        else if(maze.isMovable(position.getPosToNorth()))
        {
            northForwards();
        }
        else if(maze.isMovable(position.getPosToWest()))
        {
            westForwards();
        }
        else if(maze.isMovable(position.getPosToEast()))
        {
            eastForwards();
        }
    }

    /**
     * Sets the direction if west is forwards.
     */
    private void westForwards() {
        forwards = position.getPosToWest();
        right = position.getPosToNorth();
        left = position.getPosToSouth();
    }
    /**
     * Sets the direction if south is forwards.
     */
    private void southForwards() {
        forwards = position.getPosToSouth();
        right = position.getPosToWest();
        left = position.getPosToEast();
    }
    /**
     * Sets the direction if east is forwards.
     */
    private void eastForwards() {
        forwards = position.getPosToEast();
        right = position.getPosToSouth();
        left = position.getPosToNorth();
    }
    /**
     * Sets the direction if north is forwards.
     */
    private void northForwards() {
        forwards = position.getPosToNorth();
        right = position.getPosToEast();
        left = position.getPosToWest();
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


}