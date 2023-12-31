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

    GameMenu menu;
    PauseMenu pause;
    Arena arena;
    Screen screen;
    TerminalSize terminalSize;
    GameOverMenu gameOver;

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
        this.menu = new GameMenu (120,90);
    }

    private void draw() throws IOException {
        if (menu.isGamestarted()) {
            if (!arena.isRunning()) {
                System.exit(0);
            }
            else {
                if (!arena.isGameOver()) {
                    if (!arena.isPaused()) {
                        screen.clear();
                        arena.draw(screen.newTextGraphics(), screen);
                        screen.refresh();
                    } else {
                        screen.clear();
                        pause.draw(screen.newTextGraphics(), pause.width, pause.height);
                        screen.refresh();
                    }
                }
                else {
                    screen.clear();
                    gameOver.draw(screen.newTextGraphics(), gameOver.width, gameOver.height);
                    screen.refresh();
                }
            }
        }
        else {

            screen.clear();
            menu.draw(screen.newTextGraphics(), menu.width, menu.height);
            screen.refresh();
        }
    }

    public void run() throws IOException {
        while (arena.isRunning()) {
            draw();
            com.googlecode.lanterna.input.KeyStroke key = screen.pollInput();
            if (key != null) {
                processKey(key);
            }
        }
        System.exit(0);
    }

    public void processKey(com.googlecode.lanterna.input.KeyStroke key) throws IOException {
        arena.processKey(key, screen);
        if (!arena.isGameOver()) {
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p') {
                if (!arena.isPaused()) {
                    arena.setPaused(true);
                    screen.clear();
                } else {
                    arena.setPaused(false);
                }
            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'r') {
                restartGame();
            } else if (key.getKeyType() == KeyType.Enter) {
                menu.NewGame();
            }
        }
        else {
            if (key.getKeyType() == KeyType.Enter) {
                restartGame();
            }
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                arena.setRunning(false);
                screen.close();
            }
        }
    }

    private void restartGame() throws IOException {
        arena.setPaused(false);
        arena = new Arena(120, 90);
        arena.setPontuacao(0);
    }
}