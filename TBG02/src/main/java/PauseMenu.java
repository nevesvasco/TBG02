import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class PauseMenu {

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private Position position;

    int x;

    public int getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
        this.x = x;
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
        this.y = y;
    }

    int y;

    public PauseMenu(int x, int y) {
        this.x = x;
        this.y = y;
        position = new Position(x, y);
    }



    public void draw(Arena arena,Screen screen,int width , int height ) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(new TerminalPosition(width / 2 - 5, height / 2), "Game Paused");


        graphics.setForegroundColor(TextColor.ANSI.GREEN);
        graphics.putString(new TerminalPosition(width / 2 - 5, height / 2 + 2), "Unpause (space)");


        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.putString(new TerminalPosition(width / 2 - 5, height / 2 + 4), "Quit (q)");


        com.googlecode.lanterna.input.KeyStroke key = screen.pollInput();


        while (key != null) {
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == ' ') {

                arena.setRunning(true);
                screen.refresh();
                break;
            }

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {

                screen.close();
                System.exit(0);
            }


            key = screen.pollInput();
        }
    }
}
