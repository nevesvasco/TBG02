import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Arena {
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private boolean gameOver = false;
    public int width;
    public int height;

    public double getPontuacao() {
        return pontuacao;
    }

    private Collision collision;
    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public double pontuacao = 0;



    public void setRunning(boolean running) {
        isRunning = running;
    }

    private boolean isPaused = false;

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    private boolean isRunning = true;
    private Graphics2D graphics;
    private int scrollPosition = 0;

    public int getScrollPosition() {
        return scrollPosition;
    }

    public void setScrollPosition(int scrollPosition) {
        this.scrollPosition = scrollPosition;
    }

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

    private List<Wall> walls = new ArrayList<Wall>();
    private List<Obstacle> obstacles ;
    private Random random;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public Arena(int width, int height) {
        player = new Player(90,45,6,9, Color.black);
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.obstacles =  new ArrayList<Obstacle>() ;
        this.random = new Random();
        this.collision = new Collision(0,0);
    }

    public void processKey(KeyStroke key, Screen screen) throws IOException {
        if(gameOver){
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'r') {
                resetGame();
            }else{screen.close();}
        }
        if (key.getKeyType() == KeyType.EOF) {
            isRunning = false;
            screen.close();
            return;
        }
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            isRunning = false;
            screen.close();
        }
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == ' ') {
            if (!player.isJumping()){
                player.handleKeyPress(key, this);
            }
        }


    }

    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void resetGame() {
        player.setPosition(new Position(90, 45));
        gameOver = false;
    }

    public void createObstacles() {

        int initialY = random.nextInt(38) + 35; // Gera um número aleatório entre 5 e 45 para a posição Y inicial
        int initialX = random.nextInt(119) + 110;

        Obstacle obstacle = new Obstacle(initialX, initialY);
        obstacles.add(obstacle);
    }

    public void updateObstacles() {
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.moveLeft();


            if (obstacle.position.getX() <= 0) {
                iterator.remove();
            }
        }
    }

    public void draw(TextGraphics textGraphics, Screen screen) {
        screen.clear();

        textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#F9E76D"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 16, height *9),' ');
        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(new TerminalPosition(width + 55, height / 8 - 8), "Pontos: "+ (int)pontuacao);

        for (Wall wall : walls){
            wall.draw(textGraphics, screen);
        }
        if(obstacles.isEmpty()){
            executorService.schedule(() -> {
                while(obstacles.size() < 4){createObstacles();}
            }, 5, TimeUnit.MILLISECONDS);
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(textGraphics,screen);
        }
        player.draw(screen.newTextGraphics(), screen);
        updateObstacles();
        if (player.isJumping()){
            executorService.schedule(() -> {
                player.setJumping(false);
                player.setPosition(new Position(90, 45));
            }, 400, TimeUnit.MILLISECONDS);
        }
        executorService.schedule(() -> {
               pontuacao += 1;
        }, 5, TimeUnit.MILLISECONDS);
        if (!gameOver) {
            if (collision.collidesWith(obstacles, player)) {
                gameOver = true;
            }
        }
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
    public boolean isRunning() {
        return isRunning;
    }
}