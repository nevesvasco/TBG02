import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void testGetX() {
        Position position = new Position(3, 5);

        int x = position.getX();

        assertEquals(3, x);
    }

    @Test
    public void testGetY() {

        Position position = new Position(3, 5);

        int y = position.getY();

        assertEquals(5, y);
    }

    @Test
    public void testSetX() {

        Position position = new Position(3, 5);

        position.setX(7);

        assertEquals(7, position.getX());
    }

    @Test
    public void testSetY() {
        Position position = new Position(3, 5);

        position.setY(9);

        assertEquals(9, position.getY());
    }

    @Test
    public void testEquals() {
        Position position1 = new Position(3, 5);
        Position position2 = new Position(3, 5);
        Position position3 = new Position(2, 4);

        assertEquals(position1, position2);
        assertNotEquals(position1, position3);
    }

    @Test
    public void testHashCode() {
        Position position1 = new Position(3, 5);
        Position position2 = new Position(3, 5);
        assertEquals(position1.hashCode(), position2.hashCode());
    }
}
