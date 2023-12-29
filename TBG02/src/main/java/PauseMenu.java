import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class PauseMenu {

    public int width;
    public int height;

    public PauseMenu(int width, int height) {
        this.width = width;
        this.height = height;
    }



    public void draw(TextGraphics graphics, int width , int height ) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 16, height * 9), ' ');
        // Desenha a caixa do menu de pausa
        graphics.setForegroundColor(TextColor.ANSI.BLACK);
        graphics.setBackgroundColor(TextColor.ANSI.WHITE);

        graphics.fillRectangle(new TerminalPosition(width - 45, height / 4 - 5), new TerminalSize(40, 10), ' ');

        // Desenha o texto "Pause"
        graphics.putString(new TerminalPosition(width - 28, height / 4 - 4), "Pause");

        // Desenha os botões
        graphics.putString(new TerminalPosition(width - 28, height / 4 - 1), "[Continue]");

        // Desenha os botões
        graphics.putString(new TerminalPosition(width - 28, height / 4 + 1), "[Restart]");

        //Desenha a caixa de seleção de mudo e rótulo
        graphics.putString(new TerminalPosition(width - 45, height / 4 - 5), "[ ] Mute");
    }
}