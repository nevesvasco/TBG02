import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import java.awt.*;
import java.awt.event.KeyEvent;

    public class Player extends Element {
        private Graphics2D graphics;
        private int width, height;
        private Color color;
        private boolean isJumping = false;

        public boolean isJumping() {
            return isJumping;
        }

        public void setJumping(boolean jumping) {
            isJumping = jumping;
        }

        public boolean isBoosting() {
            return isBoosting;
        }

        public void setBoosting(boolean boosting) {
            isBoosting = boosting;
        }

        private boolean isBoosting = false;

        public Player(Graphics2D graphics, int x, int y, int width, int height, Color color) {
            super(x, y);
            this.graphics = graphics;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        public void draw(TextGraphics graphics, Screen screen) {
            int x = position.getX();
            int y = position.getY();
            int lado = 1; // O tamanho do lado do quadrado

            // Desenha um quadrado usando texto
            for (int i = 0; i < lado; i++) { // Para cada linha do quadrado
                for (int j = 0; j < lado; j++) { // Para cada coluna do quadrado
                    if (i == 0 || i == lado - 1 || j == 0 || j == lado - 1) { // Se for uma borda
                        screen.setCharacter(x + j, y + i, new TextCharacter(' ', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT)); // Desenha o caractere de preenchimento
                    } else { // Se for o interior
                        screen.setCharacter(x + j, y + i, new TextCharacter(' ', TextColor.ANSI.YELLOW, TextColor.ANSI.YELLOW)); // Desenha um espaço em branco
                    }
                }
            }
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33")); // Cor amarela para o quadrado
            graphics.enableModifiers(SGR.BOLD);
        }



        public void update() {
            jump();
            boost();
        }
        public void jump() {
            int posicaoAntiga = position.getY();
            if (isJumping) {
                if (position.getY() > 0) {
                    position.setY(position.getY() - 5);
                    isJumping = false;
                }
            if (!isJumping) {
                    isJumping = false;
                    position.setY(posicaoAntiga);
                }
            }
        }

        public void boost() {
            if (isBoosting) {
                position.setX(position.getX() * 2);
            } else {
                position.setX(position.getX() / 2);
            }
        }

        public void handleKeyUp(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_F) {
                isBoosting = false;
            }
        }
    }
