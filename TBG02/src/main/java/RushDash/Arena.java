package RushDash;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Arena {


    //Variavéis :
    private double speedMultiplier = 1.0;

    private long speedMultiplierEndTime = 0L;
    private static final long SPEED_UP_DURATION = 2000;  // 2 seconds in milliseconds

    private boolean gameOver = false;
    private final String leaderboardFilePath = "./leaderboard.txt";
    public int width;
    public int height;
    private Collision collision;
    public double pontuacao = 0;
    private boolean isPaused = false;
    private boolean isRunning = true;
    private Graphics2D graphics;
    Player player;
    private List<Wall> walls = new ArrayList<Wall>();
    private List<Obstacle> obstacles;
    private Random random;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    //Metodos
    public Arena(int width, int height) {
        player = new Player(90, 45, 6, 9, Color.black);
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.obstacles = new ArrayList<Obstacle>();
        this.random = new Random();
        this.collision = new Collision(0, 0);
    }

    public void processKey(KeyStroke key, Screen screen, PauseMenu pause, Game game) throws IOException {
        if (gameOver) {
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'r') {
                resetGame();
            } else {
                screen.close();
            }
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
            if (!player.isJumping()) {
                player.handleKeyPress(key, this);
            }
        }
        if (!isPaused && key.getKeyType() == KeyType.Character && key.getCharacter() == 'p') {
            isPaused = true;
        }
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'v') {
            speedMultiplier = 2.0;
            speedMultiplierEndTime = System.currentTimeMillis() + SPEED_UP_DURATION;
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

    public void draw(TextGraphics textGraphics, Screen screen) throws IOException {
        screen.clear();

        textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#F9E76D"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 16, height * 9), ' ');
        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(new TerminalPosition(width + 55, height / 8 - 8), "Pontos: " + (int) pontuacao);

        for (Wall wall : walls) {
            wall.draw(textGraphics, screen);
        }
        if (obstacles.isEmpty()) {
            executorService.schedule(() -> {
                while (obstacles.size() < 4) {
                    createObstacles();
                }
            }, 5, TimeUnit.MILLISECONDS);
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(textGraphics, screen);
        }
        for (Obstacle obstacle : obstacles) {
            if (System.currentTimeMillis() < speedMultiplierEndTime) {
                obstacle.setSpeedMultiplier(speedMultiplier);
            } else {
                obstacle.setSpeedMultiplier(1.0);  // Reset to normal speed
            }
            obstacle.draw(textGraphics, screen);
        }
        player.draw(screen.newTextGraphics(), screen);
        updateObstacles();
        if (player.isJumping()) {
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
                saveScoreToLeaderboard();
            }

        }
        if (screen.getTerminalSize().getRows() <= 1 ){
            isRunning = false;
           screen.close();
           System.exit(0);
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

    private void saveScoreToLeaderboard() {
        LeaderBoardEntry entry = new LeaderBoardEntry((int) pontuacao);

        // Carregar entradas do leaderboard do arquivo
        List<LeaderBoardEntry> existingEntries = loadLeaderboardFromFile();

        // Adicionar a nova entrada
        existingEntries.add(entry);

        // Ordenar as entradas
        Collections.sort(existingEntries);

        // Manter apenas os 10 melhores scores
        if (existingEntries.size() > 10) {
            existingEntries = existingEntries.subList(0, 10);
        }

        // Salvar a leaderboard atualizada no arquivo
        saveLeaderboardToFile(existingEntries);
    }


    private List<LeaderBoardEntry> loadLeaderboardFromFile() {
        List<LeaderBoardEntry> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(leaderboardFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length > 0) {
                    int score = Integer.parseInt(parts[0]);
                    entries.add(new LeaderBoardEntry(score));
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Lidar com exceções (por exemplo, arquivo não encontrado, formato inválido)
            e.printStackTrace();
        }
        return entries;
    }


    private void saveLeaderboardToFile(List<LeaderBoardEntry> entries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(leaderboardFilePath))) {
            Collections.sort(entries); // Ordenar os scores em ordem decrescente

            for (int i = 0; i < Math.min(entries.size(), 10); i++) {
                writer.write(String.valueOf(entries.get(i).getScore()));
                writer.newLine();
            }
        } catch (IOException e) {
            // Lidar com exceções (por exemplo, impossibilidade de escrever no arquivo)
            e.printStackTrace();
        }
    }

    //GETTERS E SETTERS
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public Player getPlayer() {
        return player;
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }
    public long getSpeedMultiplierEndTime() {
        return speedMultiplierEndTime;
    }
}