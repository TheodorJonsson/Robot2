import org.junit.jupiter.api.Test;
import src.Position;

class PositionTest
{
    @Test
    void sameParametershouldBeEqual()
    {
        Position pos1 = new Position(1,1);
        Position pos2 = new Position(1,1);
        pos1.equals(pos2);
    }
    @Test
    void getPosToNorthshouldBeEqual()
    {
        Position pos1 = new Position(1,1);
        Position pos2 = new Position(1, 0);
        pos2.equals(pos1.getPosToNorth());
    }
}