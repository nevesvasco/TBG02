import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;

public class GameMenu {

    private PlaySound sound;
    public int width;
    public int height;

    public boolean isGamestarted() {
        return isGamestarted;
    }

    LeaderBoard leaderBoard ;

    private boolean isGamestarted = false;


    public GameMenu(int width, int height) {
        this.width = width;
        this.height = height;
        this.leaderBoard = new LeaderBoard();
        this.sound = new PlaySound();
    }

    public void NewGame() {
        isGamestarted = true;   sound.playSound("platforming.wav");}

    public void CloseGame(Screen screen) throws IOException {
        screen.close();
    }

    public void draw(TextGraphics graphics, int width, int height) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#F9E76D"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 16, height * 9), ' ');

        graphics.setForegroundColor(TextColor.ANSI.BLACK);
        graphics.setBackgroundColor(TextColor.ANSI.WHITE);

        graphics.fillRectangle(new TerminalPosition(width - 45, height / 4 - 5), new TerminalSize(40, 10), ' ');

        graphics.putString(new TerminalPosition(width - 30, height / 4 - 4), "Rush Dash");

        graphics.putString(new TerminalPosition(width - 40, height / 4 - 1), "Press Enter to start the game");

        graphics.putString(new TerminalPosition(width - 40, height / 4 + 1), "Press Q to close the game");

        graphics.putString(new TerminalPosition(width - 45, height / 4 - 5), " ");

        leaderBoard.draw(graphics);

    }
}

