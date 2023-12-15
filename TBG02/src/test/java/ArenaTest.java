import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {

    @Test
    public void testCanPlayerMove() {

        Arena arena = new Arena(10, 10);

        Position playerPosition = new Position(5, 5);

        assertTrue(arena.canPlayerMove(playerPosition));

        Position invalidPosition = new Position(4, 5);

        assertTrue(arena.canPlayerMove(invalidPosition));
    }

    @Test
    public void testAddWall() {

        Arena arena = new Arena(10, 10);

        Wall wall = new Wall(3, 3);

        arena.addWall(wall);

        assertTrue(arena.getWalls().contains(wall));
    }
}
