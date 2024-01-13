package RushDash;

import RushDash.Element;
import RushDash.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Wall extends Element {

    private static final int SPEED = 3;  // Velocidade de movimento

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics, Screen screen) {
        TextCharacter wallCharacter = new TextCharacter('#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        TextCharacter wallBackground = new TextCharacter(' ', TextColor.Factory.fromString("#999999"), TextColor.ANSI.DEFAULT);

        int width = screen.getTerminalSize().getColumns();

        // Desenha paredes horizontais na parte superior e inferior
        for (int i = 0; i < width; i++) {
            graphics.setCharacter(i, 0, wallCharacter); // Paredes na parte superior
            graphics.setCharacter(i, screen.getTerminalSize().getRows() - 1, wallCharacter); // Paredes na parte inferior
        }
    }

    @Override
    public void gameOver() {

    }

    public void moveLeft(int speed) {
        position.setX( position.getX() - speed);  // Move a parede para a esquerda com base na velocidade
    }
    public Position getPosition() {
        return position;
    }
}