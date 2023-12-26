import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import java.util.Collections;

public class Wall extends Element {

    private static final int SPEED = 3;  // Velocidade de movimento

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics, Screen screen) {

        TextCharacter wallCharacter = new TextCharacter('#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        TextCharacter wallBackground = new TextCharacter(' ', TextColor.Factory.fromString("#999999"), TextColor.ANSI.DEFAULT);
        TextCharacter boldCharacter = new TextCharacter('#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        boldCharacter = boldCharacter.withModifiers(Collections.singleton(SGR.BOLD));

        int x = position.getX();
        int y = position.getY();

        // Draw horizontal walls only
        graphics.setCharacter(x * 2, y * 0, wallCharacter);
        graphics.setCharacter(x * 2 + 1, y * 0, wallBackground);

        graphics.setCharacter(x * 2, y * 0, boldCharacter);
        graphics.setCharacter(x * 2 + 1, y * 0, boldCharacter);

        // Move a parede para a esquerda com a velocidade especificada
        position.setX((position.getX() - SPEED + screen.getTerminalSize().getColumns()) % screen.getTerminalSize().getColumns());
    }
    public void moveLeft(int speed) {
        position.setX( position.getX() - speed);  // Move a parede para a esquerda com base na velocidade
    }
    public Position getPosition() {
        return position;
    }
}