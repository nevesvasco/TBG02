import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public abstract class Element {
    protected Position position;
    protected boolean isDead = false;
    public Element(int x, int y) {
        position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void draw(TextGraphics graphics, Screen screen);

    public abstract void gameOver();
}
