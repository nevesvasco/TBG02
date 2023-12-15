import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game = new Game();

    public GameTest() throws IOException {
    }

    @Test
    public void testArena() {

        Arena arena = new Arena(80, 24);
        game.setArena(arena);
        Arena retrievedArena = game.getArena();

        assertEquals(arena, retrievedArena);
    }

    @Test
    public void testSetArena() {
        Arena newArena = new Arena(80, 24);
        game.setArena(newArena);

        assertEquals(newArena, game.getArena());
    }

    @Test
    public void testRun() throws IOException {
        Game game = new Game();
        game.run();
    }
}