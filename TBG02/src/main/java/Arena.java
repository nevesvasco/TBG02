import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Arena {
    private boolean gameOver = false;
    private int coinCount = 0;
    private int width;
    private int height;
    private boolean isRunning = true;
    private Graphics2D graphics;

    public boolean isGameOver(){
        return gameOver;
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    Player player;

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    private List<Wall> walls = new ArrayList<Wall>();
    private List<Obstacle> obstacles;
    private Random random;

    public Arena(int width, int height) {
        player = new Player(graphics,10,10, 16,9, Color.black);
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.obstacles = new ArrayList<>();
        this.random = new Random();
    }

    public void processKey(KeyStroke key, Screen screen) throws IOException {
        if(gameOver){
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'r') {
                resetGame();
            }else{screen.close();}
        }
        if (key.getKeyType() == KeyType.EOF) {
            isRunning = false;
            return;
        }
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
        }
        if (key.getKeyType() == KeyType.ArrowUp) {
            player.setJumping(true);
            player.jump();
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            player.setBoosting(true);
            player.boost();
        }
    }
    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void setPlayerPosition(Position position) {
        player.setPosition(position);
    }
    public void resetGame() {
        player.setPosition(new Position(10, 10));
        coinCount = 0;
        gameOver = false;
    }
    public void gameOver() {
        gameOver = true;
    }

    public void addObstacle() {
        int x = random.nextInt(width);
        int y = 0;
        Obstacle obstacle = new Obstacle(x, y);
        obstacles.add(obstacle);
    }

    public void updateObstacles() {
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.moveDown();
            if (obstacle.getY() >= height) {
                iterator.remove();
            }
        }
    }

    public void draw(TextGraphics textGraphics, Screen screen) {
        // Clear screen
        screen.clear();

        textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 16, height *9),' ');
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
        textGraphics.putString(new TerminalPosition(width - 16, 9), "Energy: ");
        for (Wall wall : walls){
            wall.draw(textGraphics, screen);
        }
        for (Obstacle obstacle : obstacles) {
            int x = obstacle.getX();
            int y = obstacle.getY();
            screen.setCharacter(x, y, new TextCharacter('X', TextColor.ANSI.RED, TextColor.ANSI.RED));
            obstacle.draw(textGraphics,screen);
            addObstacle();
            updateObstacles();
        }
        drawGameOverMessage(textGraphics);
        player.draw(screen.newTextGraphics(), screen);
    }
    public void drawGameOverMessage(TextGraphics textGraphics) {
        if (gameOver) {
            textGraphics.setForegroundColor(TextColor.ANSI.RED);
            textGraphics.putString(new TerminalPosition(width / 2 - 5, height / 2), "Game Over");
            textGraphics.putString(new TerminalPosition(width / 2 - 10, height / 2 + 2), "Press 'R' to restart or Any Key to Exit");
        }
    }
    private void movePlayer(Position position) {
        player.setPosition(position);
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    public boolean canPlayerMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {

                return false;
            }
        }
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }



    public void setRunning(boolean running) {
        isRunning = running;
    }
}