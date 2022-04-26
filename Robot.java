import java.util.Random;
import java.util.ArrayList;
/**
 * Author: Theodor Jonsson, ens18trn
 * Date: April 13 2022
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