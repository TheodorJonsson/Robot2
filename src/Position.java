 /**
 * @author Theodor Jonsson, ens18trn
 * @since April 28 2022
 * @version 1.1
 * Course: Objektorienterad Programmeringsmetodik
 */

/**
 * Class: Position
 * Creates a position which has a x and a y value.
 * Can get the positions around it but not diagonally aswell as
 * checking if two positions are equal using a hash.
 */
public class Position
{
    private int x;
    private int y;

    /**
     * Constructor: Position
     * Default values for a x and y
     */
    public Position()
    {
        this.x = -1;
        this.y = -1;
    }

    /**
     * Constructor: Position
     * Creates a position with x and y values from paramaters
     *
     * @param x
     * @param y
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Method: getX
     * @return x, value of current object.
     */
    public int getX()
    {
        return this.x;
    }
    /**
     * Method: getY
     * @return Y, value of current object.
     */
    public int getY()
    {
        return this.y;
    }

    /**
     * Method: getPosToSouth
     * Gets the position south of the current position.
     * @return pos, which is newly created position
     */
    public Position getPosToSouth()
    {
        Position pos = new Position(x, (y + 1));
        return pos;
    }

    /**
     * Method: getPosToNorth
     * Gets the position north of the current position.
     * @return pos, which is newly created position
     */
    public Position getPosToNorth()
    {
        Position pos = new Position(x, (y - 1));
        return pos;
    }

    /**
     * Method: getPosToWest
     * Gets the position west of the current position.
     * @return pos, which is newly created position
     */
    public Position getPosToWest()
    {
        Position pos = new Position((this.x - 1), this.y);
        return pos;
    }

    /**
     * Method: getPosToEast
     * Gets the position east of the current position.
     * @return pos, which is newly created position
     */
    public Position getPosToEast()
    {
        Position pos = new Position((this.x + 1), this.y);
        return pos;
    }

    /**
     * Method: equals
     * Checks if the object o is a position then checks if
     * the object o has the same hashcode as the current position
     * @param o
     * @return true if they have the same hashcode, false if not
     */
    @Override
    public boolean equals(Object o)
    {
        if(o == null)
        {
            return false;
        }
        if(o instanceof Position)
        {
            Position test = (Position) o;
            if(test.x == this.x && test.y == this.y)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Method: hashCode
     * Creates a hash value using the x and y position
     * @return hash, which is the hashed value
     */
    public int hashCode()
    {
        int hash = 7;
        int temp = (this.getY() + ((this.getX() + 1)/2));

        hash = 31 * hash * temp;
        return hash;
    }
}
