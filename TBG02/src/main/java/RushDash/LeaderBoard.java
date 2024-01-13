package RushDash;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderBoard {
    private List<LeaderBoardEntry> entries;

    public LeaderBoard() {
        this.entries = new ArrayList<>();
    }

    // Método para ler os scores de um arquivo de texto e adicionar ao Leaderboard
    public void readScoresFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("./leaderboard.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int score = Integer.parseInt(line.trim());
                entries.add(new LeaderBoardEntry(score));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para obter os 10 primeiros scores mais altos
    public void draw(TextGraphics textGraphics) {
        if(entries.isEmpty()) {
            readScoresFromFile(); // Lê os scores apenas se a lista estiver vazia
        }
        Collections.sort(entries); // Classificar as entradas em ordem decrescente de score

        textGraphics.fillRectangle(new TerminalPosition(80, 30), new TerminalSize(30, 10), ' ');
        textGraphics.setBackgroundColor(TextColor.ANSI.WHITE);
        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(new TerminalPosition(81, 30 ) , "Leaderboard - Top 10 Scores:");

        int y = 1; // Posição Y inicial para exibir os scores

        // Exibir os 10 primeiros scores
        for (int i = 0; i < entries.size(); i++) {
            LeaderBoardEntry entry = entries.get(i);
            textGraphics.putString(new TerminalPosition(81, 31 + i ) , y +"- " + entry.getScore());
            y++; // Mover para a próxima linha
        }
    }

}
