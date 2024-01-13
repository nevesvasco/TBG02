package RushDash;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ObstacleTest {

    @Test
    public void testMoveDown() {
        Obstacle obstacle = new Obstacle(5, 5);
        obstacle.moveDown();

        Assert.assertEquals(6, obstacle.getPosition().getY());
    }

    @Test
    public void testMoveLeft() {
        Obstacle obstacle = new Obstacle(5, 5);
        obstacle.moveLeft();

        Assert.assertEquals(4, obstacle.getPosition().getX());
    }
}
