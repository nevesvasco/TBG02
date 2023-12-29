import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
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

    PauseMenu pause;
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
        this.pause = new PauseMenu(120,90);
    }

    private void draw() throws IOException {
        if (!arena.isRunning()){
            System.exit(0);
        }
        //Arena arenapre = ArenaLoader.loadArenaFromFile("maps/map1.txt");
        if (!arena.isPaused()){
            screen.clear();
            arena.draw(screen.newTextGraphics(), screen);
            screen.refresh();
        }
        else {
            screen.clear();
            pause.draw(screen.newTextGraphics(), pause.width, pause.height);
            screen.refresh();
        }
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
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p') {
            if (!arena.isPaused()){
                arena.setPaused(true);
                screen.clear();
            }
            else {
                arena.setPaused(false);
            }
        }
    }
}