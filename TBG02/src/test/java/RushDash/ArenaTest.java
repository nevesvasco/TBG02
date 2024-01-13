package RushDash;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaTest {

    private Arena arena;
    private Screen screen;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp() {
        arena = new Arena(100, 50);
    }

    @Test
    public void testResetGame() {
        arena.setGameOver(true);
        arena.resetGame();

        assertFalse(arena.isGameOver());
        assertEquals(new Position(90, 45), arena.getPlayer().getPosition());
    }

    @Test
    public void testCreateObstacles() {
        arena.createObstacles();

        assertTrue(arena.getObstacles().size() > 0);

        Obstacle obstacle = arena.getObstacles().get(0);
        assertTrue(obstacle.getPosition().getX() >= 110 && obstacle.getPosition().getX() <= 229);
        assertTrue(obstacle.getPosition().getY() >= 35 && obstacle.getPosition().getY() <= 73);
    }
        @Test
        void addWall_addsWallToList() {
            Arena arena = new Arena(100, 50);
            Wall wall = new Wall(10, 20);

            arena.addWall(wall);

            List<Wall> walls = arena.getWalls();
            assertTrue(((List<?>) walls).contains(wall));
        }

        @Test
        void resetGame_resetsPlayerPosition() {
            Arena arena = new Arena(100, 50);
            arena.setGameOver(true);
            arena.getPlayer().setPosition(new Position(50, 25));

            arena.resetGame();

            assertFalse(arena.isGameOver());
            assertEquals(new Position(90, 45), arena.getPlayer().getPosition());
        }
    }