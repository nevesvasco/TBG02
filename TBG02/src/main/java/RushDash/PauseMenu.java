package RushDash;

import RushDash.Arena;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class PauseMenu {

    public int width;
    public int height;

    public PauseMenu(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public void draw(TextGraphics graphics, int width, int height, Game game) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#F9E76D"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 16, height * 9), ' ');

        graphics.setForegroundColor(TextColor.ANSI.BLACK);
        graphics.setBackgroundColor(TextColor.ANSI.WHITE);

        graphics.fillRectangle(new TerminalPosition(width - 45, height / 4 - 5), new TerminalSize(40, 10), ' ');

        graphics.putString(new TerminalPosition(width - 28, height / 4 - 4), "Pause");

        graphics.putString(new TerminalPosition(width - 35, height / 4 - 1), "[Continue]");

        graphics.putString(new TerminalPosition(width - 35, height / 4 - 1), "[Press P to continue]");

        graphics.putString(new TerminalPosition(width - 35, height / 4 + 1), "[Press R to Restart]");
        if (game.isIsmuted()) {
            graphics.putString(new TerminalPosition(width - 45, height / 4 - 5), "[X] Press M to unmute");
        } else {
            graphics.putString(new TerminalPosition(width - 45, height / 4 - 5), "[ ] Press M to mute");
        }

    }

    public void processKey(com.googlecode.lanterna.input.KeyStroke key, Game game, Arena arena) throws IOException {
        if (arena.isPaused()) {
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'r') {
                game.mute();
                arena.setPaused(false);
                game.restartGame();
            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'm') {
                if (game.isIsmuted()) {
                    game.setIsmuted(false);
                    game.getMenu().getSound().playSound("platforming.wav");
                } else {
                    game.setIsmuted(true);
                    arena.getPlayer().setMuted(game.isIsmuted());
                }
               // if (!game.isIsmuted()) {

               // }

                game.mute();

            } else if (arena.isPaused() && key.getKeyType() == KeyType.Character && key.getCharacter() == 'p') {
                game.mute();
                arena.setPaused(false);
            }
        }
    }
}