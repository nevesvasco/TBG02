package RushDash;
import RushDash.Arena;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PauseMenuTest {

    @Test
    public void testProcessKeyRestart() throws IOException {
        PauseMenu pauseMenu = new PauseMenu(80, 24);
        KeyStroke key = new KeyStroke(KeyType.Character, 'r');
        Game game = new Game();
        Arena arena = new Arena(120, 90);

        arena.setPaused(true);

        pauseMenu.processKey(key, game, arena);

        Assert.assertFalse(arena.isPaused());
    }

    @Test
    public void testProcessKeyMute() throws IOException {
        PauseMenu pauseMenu = new PauseMenu(80, 24);
        KeyStroke key = new KeyStroke(KeyType.Character, 'm');
        Game game = new Game();
        Arena arena = new Arena(120, 90);

        arena.setPaused(true);

        pauseMenu.processKey(key, game, arena);

    }

    @Test
    public void testProcessKeyContinue() throws IOException {
        PauseMenu pauseMenu = new PauseMenu(80, 24);
        KeyStroke key = new KeyStroke(KeyType.Character, 'p');
        Game game = new Game();
        Arena arena = new Arena(120,90);

        arena.setPaused(true);

        pauseMenu.processKey(key, game, arena);

        Assert.assertFalse(arena.isPaused());
    }
}