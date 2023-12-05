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
        private boolean isBoosting = false;

        public Player(Graphics2D graphics, int x, int y, int width, int height, Color color) {
            super(x, y);
            this.graphics = graphics;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        @Override
        public void draw(TextGraphics graphics, Screen screen) {
            screen.setCharacter(position.getX(), position.getY(), new TextCharacter('H', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
            graphics.enableModifiers(SGR.BOLD);
        }
        public void update() {
            jump();
            boost();
        }
        private void jump() {
            if (isJumping) {
                if (position.getY() > 0) {
                    position.setY(position.getY() - 5);
                } else {
                    isJumping = false;
                }
            }
        }

        private void boost() {
            if (isBoosting) {
                position.setX(position.getX() * 2);
            } else {
                position.setX(position.getX() / 2);
            }
        }

        public void handleKeyDown(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_SPACE) {
                if (!isJumping) {
                    isJumping = true;
                }
            } else if (event.getKeyCode() == KeyEvent.VK_F) {
                isBoosting = true;
            }
        }

        public void handleKeyUp(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_F) {
                isBoosting = false;
            }
        }
    }
