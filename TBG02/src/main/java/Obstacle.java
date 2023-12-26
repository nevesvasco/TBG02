import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Obstacle {
    private int x;
    private int y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveDown() {
        y++;
    }

    public void draw(TextGraphics graphics, Screen screen) {
        screen.setCharacter(x, y, new TextCharacter('X', TextColor.ANSI.RED, TextColor.ANSI.RED));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000")); // Cor vermelha para o obstáculo
        graphics.enableModifiers(SGR.BOLD);
    }
}