import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Terminal terminal;

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    Arena arena;
    Screen screen;
    TerminalSize terminalSize;

    public Game() throws IOException {
        terminalSize = new TerminalSize(190, 50);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        arena = new Arena(120, 90);

    }

    private void draw() throws IOException {
        if (!arena.isRunning()){
            System.exit(0);
        }
        //Arena arenapre = ArenaLoader.loadArenaFromFile("maps/map1.txt");
        screen.clear();
        arena.draw(screen.newTextGraphics(), screen);
        screen.refresh();
    }

    public void run() throws IOException {
        while (arena.isRunning()) {
            draw();
            com.googlecode.lanterna.input.KeyStroke key = screen.pollInput(); // Usar pollInput() em vez de readInput()
            if (key != null) {
                processKey(key);
            }
        }
        System.exit(0);
    }

    public void processKey(com.googlecode.lanterna.input.KeyStroke key) throws IOException {
        arena.processKey(key, screen);
    }
}