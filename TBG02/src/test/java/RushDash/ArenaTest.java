package RushDash;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArenaTest {
    private Arena arena;
    private BasicScreenMock screenMock;

    @Before
    public void setUp() {
        arena = new Arena(120, 90);
        screenMock = new BasicScreenMock();
    }

    @Test
    public void testProcessKey_GameOver_R() throws IOException {
        KeyStroke keyStroke = new KeyStroke(KeyType.Character);
        Game gameMock = new Game();
        PauseMenu pauseMock = new PauseMenu(120, 90);

        arena.setGameOver(true);
        arena.processKey(keyStroke, screenMock, pauseMock, gameMock);

        assertTrue(gameMock.isResetGameCalled());
    }

    @Test
    public void testProcessKey_GameOver_NotR() throws IOException {
        KeyStroke keyStroke = new KeyStroke(KeyType.Character);
        Game gameMock = new Game();
        PauseMenu pauseMock = new PauseMenu(120, 90);

        arena.setGameOver(true);
        arena.processKey(keyStroke, screenMock, pauseMock, gameMock);

        assertTrue(screenMock.isCloseCalled());
    }

    @Test
    public void testProcessKey_EOF() throws IOException {
        KeyStroke keyStroke = new KeyStroke(KeyType.EOF);

        arena.processKey(keyStroke, screenMock, null, null);

        assertTrue(screenMock.isCloseCalled());
        assertFalse(arena.isRunning());
    }

    @Test
    public void testProcessKey_NotGameOver_Q() throws IOException {
        KeyStroke keyStroke = new KeyStroke(KeyType.Character);

        arena.processKey(keyStroke, screenMock, null, null);

        assertTrue(screenMock.isCloseCalled());
        assertFalse(arena.isRunning());
    }

    @Test
    public void testProcessKey_Space() throws IOException {
        KeyStroke keyStroke = new KeyStroke(KeyType.Character);

        assertFalse(arena.getPlayer().isJumping());  // RushDash.Player should not be jumping initially

        arena.processKey(keyStroke, screenMock, null, null);

        assertTrue(arena.getPlayer().isJumping());  // RushDash.Player should be jumping after processing key
    }

    @Test
    public void testProcessKey_NotPaused_P() throws IOException {
        KeyStroke keyStroke = new KeyStroke(KeyType.Character);

        assertFalse(arena.isPaused());

        arena.processKey(keyStroke, screenMock, null, null);

        assertTrue(arena.isPaused());
    }

    @Test
    public void testProcessKey_SpeedUp_V() throws IOException {
        KeyStroke keyStroke = new KeyStroke(KeyType.Character);

        Assert.assertEquals(1.0, arena.getSpeedMultiplier(), 0.001);

        arena.processKey(keyStroke, screenMock, null, null);

        Assert.assertEquals(2.0, arena.getSpeedMultiplier(), 0.001);
        assertTrue(System.currentTimeMillis() < arena.getSpeedMultiplierEndTime());
    }
}
