import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WallTest {

    private SwingTerminalFrame terminalFrame;
    private Screen screen;

    @BeforeEach
    public void setUp() {
        terminalFrame = TerminalFacade.createSwingTerminal();
        terminalFrame.setVisible(true);
        screen = new Screen(terminalFrame.getTerminal());
        screen.startScreen();
    }

    @AfterEach
    public void tearDown() {
        if (screen != null) {
            try {
                screen.stopScreen();
            } catch (Exception ignored) {
            }
        }
        if (terminalFrame != null) {
            terminalFrame.dispose();
        }
    }

    @Test
    public void testDrawWall() {
        Wall wall = new Wall(5, 5);
        wall.draw(screen.newTextGraphics(), screen);
    }
}
