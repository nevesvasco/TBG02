import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private ScreenMock screenMock;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(80, 24));
        Terminal terminal = terminalFactory.createTerminal();

        screenMock = new ScreenMock(terminal);
        System.setOut(new PrintStream(outContent));

        game = new Game();
        game.screen = screenMock;
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
    }

    @Test
    public void testRun() throws IOException {
        game.run();

        assertTrue(screenMock.isClosed());
    }
    @Test
    public void testMuteWhenMuted() {
        game.setIsmuted(true);

        game.mute();

        assertEquals("Mute sound\n", outContent.toString());
    }

    @Test
    public void testMuteWhenNotMuted() {
        game.setIsmuted(false);

        game.mute();

        assertEquals("", outContent.toString());
    }

    @Test
    public void testRestartGame() throws IOException {
        game.menu.getSound().playSound("test.wav");

        game.restartGame();

        assertFalse(game.arena.isGameOver());
        assertFalse(game.arena.isPaused());
        assertNotNull(game.arena);
        assertEquals("Stop sound\nPlay sound: platforming.wav\n", outContent.toString());
    }

    // Mock para Screen
    private static class ScreenMock extends TerminalScreen {
        private boolean isClosed = false;

        public ScreenMock(Terminal terminal) throws IOException {
            super(terminal);
        }

        @Override
        public void close() {
            isClosed = true;
        }

        public boolean isClosed() {
            return isClosed;
        }
    }
}
