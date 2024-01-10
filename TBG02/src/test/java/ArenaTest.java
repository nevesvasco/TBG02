import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.security.Key;

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
    public void testProcessKeyGameOver() throws IOException {
        arena.setGameOver(true);
        KeyStroke keyStroke = new KeyStroke(KeyType.EOF);
        arena.processKey(keyStroke, screen);

        assertFalse(arena.isRunning());
    }

    @Test
    public void testProcessKeyQuitGame() throws IOException {
        assertFalse(arena.isRunning());
    }

    @Test
    public void testProcessKeyJump() throws IOException {
        // Verificar se o jogador pulou
        assertTrue(arena.getPlayer().isJumping());
    }

    // Outros testes para os métodos da classe Arena podem ser adicionados conforme necessário
}
