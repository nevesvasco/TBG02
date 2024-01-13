package RushDash;

public class LeaderBoardEntry implements Comparable<LeaderBoardEntry>{
    private final int score;

    public LeaderBoardEntry( int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }


    @Override
    public int compareTo(LeaderBoardEntry other) {
        return Integer.compare(other.score, this.score);
    }
}