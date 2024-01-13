package RushDash;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void testIsJumping() {

        Player player = new Player(0, 0, 5, 5,  Color.yellow);


        assertFalse(player.isJumping());


        player.setJumping(true);


        assertTrue(player.isJumping());
    }

    @Test
    public void testSetJumping() {

        Player player = new Player(0, 0, 5, 5,  Color.yellow);


        player.setJumping(false);


        assertFalse(player.isJumping());
    }

    @Test
    public void testIsBoosting() {

        Player player = new Player( 0, 0, 5, 5, Color.yellow);

    }

    @Test
    public void testSetBoosting() {

        Player player = new Player( 0, 0, 5, 5, Color.yellow);

    }

    @Test
    public void testJump() {

        Player player = new Player( 0, 0, 5, 5, Color.yellow);

        player.getPosition().setY(5);

        assertEquals(5, player.getPosition().getY());

        assertEquals(5, player.getPosition().getY());
    }

    @Test
    public void testGameOver() {
        Player player = new Player(0, 0, 10, 10, java.awt.Color.RED);
        player.gameOver();
    }

    @Test
    public void testCollidesWithElement() {
        Player player = new Player(0, 0, 10, 10, java.awt.Color.RED);
        player.collidesWithElement(player);
    }

    @Test
    public void testIsMuted() {
        Player player = new Player(0, 0, 10, 10, java.awt.Color.RED);

        Assert.assertFalse(player.isMuted());
    }

    @Test
    public void testSetMuted() {
        Player player = new Player(0, 0, 10, 10, java.awt.Color.RED);
        player.setMuted(true);

        Assert.assertTrue(player.isMuted());
    }
}

