package RushDash;

import RushDash.Arena;
import RushDash.GameMenu;
import RushDash.GameOverMenu;
import RushDash.PauseMenu;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Game {


    private boolean ismuted = false;


    private boolean isResetGameCalled = false;
    Terminal terminal;

    GameMenu menu;
    PauseMenu pause;
    Arena arena;
    Screen screen;
    TerminalSize terminalSize;
    GameOverMenu gameOver;

    public Game() throws IOException {
        terminalSize = new TerminalSize(190, 50);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        arena = new Arena(120, 90);
        this.pause = new PauseMenu(120, 90);
        this.menu = new GameMenu(120, 90);
        this.gameOver = new GameOverMenu(120, 90);
    }

    private void draw() throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            arena.setRunning(false);
            try {
                screen.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }));
        if (menu.isGamestarted()) {
            if (!arena.isRunning()) {
                System.exit(0);
            } else {

                if (!arena.isGameOver()) {
                    if (!arena.isPaused()) {

                        screen.clear();
                        arena.draw(screen.newTextGraphics(), screen);
                        screen.refresh();
                    } else {
                        screen.clear();
                        pause.draw(screen.newTextGraphics(), pause.width, pause.height, this);
                        screen.refresh();
                    }
                } else if (arena.isGameOver()) {
                    mute();
                    screen.clear();
                    gameOver.draw(screen.newTextGraphics(), gameOver.width, gameOver.height);
                    screen.refresh();
                }
                mute();

            }
        } else {
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
        if (menu.isMenustarted()) {
            menu.processKey(key, this);
        } else {
            if (!arena.isGameOver()) {
                if (arena.isPaused()) {
                    pause.processKey(key, this, arena);
                } else {
                    arena.processKey(key, screen, pause, this);
                }
            } else {
                if (key.getKeyType() == KeyType.Enter) {
                    restartGame();
                }
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    arena.setRunning(false);
                    screen.close();
                    System.exit(0);
                }
            }
        }

    }

    public void mute() {
        if (ismuted) {
            menu.getSound().stopSound();
        }
    }

    public void restartGame() throws IOException {
        menu.getSound().stopSound();
        arena.setGameOver(false);
        arena.setPaused(false);
        arena = new Arena(120, 90);
        arena.setPontuacao(0);
        isResetGameCalled = true;
        menu.getSound().playSound("platforming.wav");
        mute();
    }

    public boolean isIsmuted() {
        return ismuted;
    }

    public void setIsmuted(boolean ismuted) {
        this.ismuted = ismuted;
    }

    public GameMenu getMenu() {
        return menu;
    }

    public boolean isResetGameCalled() {
        return isResetGameCalled;
    }
}