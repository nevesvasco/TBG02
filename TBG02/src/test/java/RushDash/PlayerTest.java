package RushDash;
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
}

