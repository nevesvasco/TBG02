import static org.junit.Assert.*;
import org.junit.Test;

public class WallTest {

    @Test
    public void testMoveLeft() {
        // Cria uma parede na posição inicial (x=10, y=5)
        Wall wall = new Wall(10, 5);

        // Obtém a posição antes de mover para a esquerda
        Position initialPosition = wall.getPosition();

        // Move a parede para a esquerda com uma determinada velocidade
        int speed = 3;
        wall.moveLeft(speed);

        // Obtém a posição após mover para a esquerda
        Position finalPosition = wall.getPosition();

        // Verifica se a posição foi atualizada corretamente
        assertEquals(initialPosition.getX() - speed, finalPosition.getX());
        assertEquals(initialPosition.getY(), finalPosition.getY());
    }
}
