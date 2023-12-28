import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Element {
    private int width, height;
    private Color color;

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isJumping() {
        return isJumping;
    }

    private boolean isJumping = false;
    private int initialY; // Armazenar a posição inicial Y do jogador

    public Player(int x, int y, int width, int height, Color color) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.color = color;
        this.initialY = y; // Definir a posição inicial Y do jogador
    }

    public void draw(TextGraphics graphics, Screen screen) {
        int x = position.getX();
        int y = position.getY();
        int radius = 3; // Raio do círculo

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33")); // Cor amarela para o círculo

        // Desenha uma aproximação de círculo usando asteriscos
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                if (i * i + j * j <= radius * radius + 2) {
                    screen.setCharacter(x + j, y + i, new TextCharacter('*', TextColor.ANSI.YELLOW, TextColor.ANSI.YELLOW));
                }
            }
        }
    }

    public void handleKeyPress(KeyStroke key, Arena arena) {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == ' ') {
            isJumping = true;
            jump(key);
        }
    }

    public void jump(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == ' ') {
            if (!isJumping) {
                isJumping = true; // Define o sinalizador de salto como verdadeiro
            }
        }
        if (isJumping) {
            if (position.getY() > 45 - 10) {
                position.setY(position.getY() - 10); // Simula o pulo movendo para cima
            } else {
                isJumping = false; // Encerra o salto quando atinge a altura máxima
            }
        } else {
            if (position.getY() < 45) {
                position.setY(position.getY() + 10); // Volta à posição inicial
            }
        }
    }
}
