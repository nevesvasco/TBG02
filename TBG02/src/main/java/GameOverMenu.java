import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;

public class GameOverMenu {

    public int width;
    public int height;

    public boolean isGamestarted() {
        return isGamestarted;
    }

    private boolean isGamestarted = false;


    public GameOverMenu(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void NewGame() {
        isGamestarted = true;
    }

    public void CloseGame(Screen screen) throws IOException {
        screen.close();
    }

    public void draw(TextGraphics graphics, int width, int height) throws IOException {
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 16, height * 9), ' ');

        graphics.setForegroundColor(TextColor.ANSI.BLACK);
        graphics.setBackgroundColor(TextColor.ANSI.WHITE);

        graphics.fillRectangle(new TerminalPosition(width - 45, height / 4 - 5), new TerminalSize(40, 10), ' ');

        graphics.putString(new TerminalPosition(width - 30, height / 4 - 4), "Game Over");

        graphics.putString(new TerminalPosition(width - 40, height / 4 - 1), "Press Enter to start a new game");

        graphics.putString(new TerminalPosition(width - 40, height / 4 + 1), "Press Q to close the game");

        graphics.putString(new TerminalPosition(width - 45, height / 4 - 5), " ");

    }
}

