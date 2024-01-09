import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class Collision extends Element {

    public Collision(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics, Screen screen) {

    }

    @Override
    public void gameOver() {

    }

    public boolean collidesWith(List<Obstacle> elements, Player player) {
        int playerWidth = 1;
        int playerHeight = 3;

        for (Obstacle obstacle : elements) {
            int obstacleX = obstacle.position.getX();
            int obstacleY = obstacle.position.getY();
            int obstacleWidth = 1;
            int obstacleHeight = 1;

            int playerX = player.position.getX();
            int playerY = player.position.getY();

            int playerLeft = playerX - playerWidth;
            int playerRight = playerX + playerWidth;
            int playerTop = playerY - playerHeight;
            int playerBottom = playerY + playerHeight;


            int obstacleLeft = obstacleX - obstacleWidth;
            int obstacleRight = obstacleX + obstacleWidth;
            int obstacleTop = obstacleY - obstacleHeight;
            int obstacleBottom = obstacleY + obstacleHeight;


            if (playerRight >= obstacleLeft && playerLeft <= obstacleRight &&
                    playerBottom >= obstacleTop && playerTop <= obstacleBottom) {
                player.gameOver();
                obstacle.gameOver();
                return true;
            }
        }
        return false;
    }
}