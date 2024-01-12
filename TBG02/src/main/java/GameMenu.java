import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.awt.SystemColor.menu;

public class GameMenu {



    private boolean menustarted =  true;
    private boolean nostalgia = true;
    private PlaySound sound;
    public int width;
    public int height;

    public boolean isGamestarted() {
        return isGamestarted;
    }

    LeaderBoard leaderBoard;

    private boolean isGamestarted = false;


    public GameMenu(int width, int height) {
        this.width = width;
        this.height = height;
        this.leaderBoard = new LeaderBoard();
        this.sound = new PlaySound();
        sound.playSound("nostalgia.wav");
    }

    public void NewGame() {
        isGamestarted = true;
        if (sound.getClip() != null) {
            sound.stopSound();
            if (nostalgia) {
                sound.playSound("platforming.wav");
                nostalgia = false;
            }
        } else {
            sound.playSound("platforming.wav");
        }
    }


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

    public void processKey(com.googlecode.lanterna.input.KeyStroke key, Game game) throws IOException {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'r') {
            menustarted = false;
            game.restartGame();
        } else if (key.getKeyType() == KeyType.Enter) {
            menustarted = false;
            NewGame();

        }
    }

    public PlaySound getSound() {
        return sound;
    }

    public void setSound(PlaySound sound) {
        this.sound = sound;
    }
    public boolean isMenustarted() {
        return menustarted;
    }

    public void setMenustarted(boolean menustarted) {
        this.menustarted = menustarted;
    }
}

