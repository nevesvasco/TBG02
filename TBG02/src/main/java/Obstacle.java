import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.Random;

public class Obstacle extends Element {


    public Obstacle(int x, int y) {
        super(x,y);
    }

    public void moveDown() {
        position.setY(position.getY()+1);
    }
    public void moveLeft() {
        position.setX(position.getX() - 1); // Move o obstáculo para a esquerda subtraindo 1 da posição X
    }
    public void draw(TextGraphics graphics, Screen screen) {
        Random random = new Random();
        int shape = random.nextInt(3); // Gerar um número aleatório para escolher a forma geométrica

        int x = position.getX();
        int y = position.getY();

        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000")); // Cor vermelha para a forma

        switch (shape) {
            case 0: // Quadrado
                drawSquare(graphics, screen, x, y);
                break;
            case 1: // Triângulo
                drawTriangle(graphics, screen, x, y);
                break;
            case 2: // Círculo
                drawCircle(graphics, screen, x, y);
                break;
            default:
                drawSquare(graphics, screen, x, y); // Se o número for inválido, desenha um quadrado por padrão
                break;
        }
    }

    @Override
    public void gameOver() {
        isDead = true;
    }

    private void drawSquare(TextGraphics graphics, Screen screen, int x, int y) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                screen.setCharacter(x + j, y + i, new TextCharacter('#', TextColor.ANSI.RED, TextColor.ANSI.RED));
            }
        }
    }

    private void drawTriangle(TextGraphics graphics, Screen screen, int x, int y) {
        screen.setCharacter(x + 1, y, new TextCharacter('^', TextColor.ANSI.RED, TextColor.ANSI.RED));
        screen.setCharacter(x, y + 1, new TextCharacter('^', TextColor.ANSI.RED, TextColor.ANSI.RED));
        screen.setCharacter(x + 2, y + 1, new TextCharacter('^', TextColor.ANSI.RED, TextColor.ANSI.RED));
        screen.setCharacter(x + 1, y + 2, new TextCharacter('^', TextColor.ANSI.RED, TextColor.ANSI.RED));
    }

    private void drawCircle(TextGraphics graphics, Screen screen, int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i * i + j * j <= 2) {
                    screen.setCharacter(x + j + 1, y + i + 1, new TextCharacter('*', TextColor.ANSI.RED, TextColor.ANSI.RED));
                }
            }
        }
    }
}