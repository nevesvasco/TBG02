import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class Collision extends Element{

    public Collision(int x, int y){
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics, Screen screen) {

    }

    @Override
    public void gameOver() {

    }

    public boolean collidesWith(List<Obstacle> elements, Player player) {
        for (Element element : elements) {
            if (element.getPosition() == player.getPosition()){
                element.gameOver();
                player.gameOver();
            }
        }
        return true;
    }
}