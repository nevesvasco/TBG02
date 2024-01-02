import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;

public class GameMenu {

    public int width;
    public int height;

    public boolean isGamestarted() {
        return isGamestarted;
    }

    private boolean isGamestarted = false;


    public GameMenu(int width, int height) {
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
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 16, height * 9), ' ');

        graphics.setForegroundColor(TextColor.ANSI.BLACK);
        graphics.setBackgroundColor(TextColor.ANSI.WHITE);

        graphics.fillRectangle(new TerminalPosition(width - 45, height / 4 - 5), new TerminalSize(40, 10), ' ');

        graphics.putString(new TerminalPosition(width - 30, height / 4 - 4), "Rush Dash");

        graphics.putString(new TerminalPosition(width - 39, height / 4 - 1), "Press Enter to start the game");

        graphics.putString(new TerminalPosition(width - 39, height / 4 + 1), "Press Q to close the game");

        graphics.putString(new TerminalPosition(width - 45, height / 4 - 5), " ");


        for (int i = 0; i < 20; i++) {


            int shape = (int) (Math.random() * 2) + 1;

            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);

            if (!isPointInMenu(x, y)) {
                int size = (int) (Math.random() * 20) + 1;

                switch (shape) {
                    case 1:
                        graphics.fillRectangle(new TerminalPosition(x , y ), new TerminalSize(size,size), '*');
                        break;
                    case 2:
                        graphics.fillTriangle(new TerminalPosition(x , y), new TerminalPosition(x + size, y + size), new TerminalPosition(x - size, y + size), '*');
                        break;
                }
            }
        }
    }
    private boolean isPointInMenu(int x, int y) {
        return (x >= width - 45 && x <= width && y >= height / 4 - 5 && y <= height / 4 + 10);
    }
}

