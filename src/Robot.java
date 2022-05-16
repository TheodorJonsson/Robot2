
/**
 * @author Theodor Jonsson, ens18trn
 * @since April 26 2022
 * @version 1.1
 * Course: Objektorienterad Programmeringsmetodik
 */

/**
 * Interface: Robot
 * Interface for the different kind of robots
 *
 */
public interface Robot
{
    public void move();

    public Position getPosition();

    public boolean hasReachedGoal();

    public boolean hasReachedDeadEnd();
}