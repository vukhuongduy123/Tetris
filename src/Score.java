import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Score {
    private int score;
    private static final String path = "highscore\\highscore.txt";

    public Score(int s) {
        this.score = s;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int s) {
        score = s;
    }

    public static Score getScoreFromFile() {
        int highScore = 0;
        Scanner scanner = new Scanner(Score.class.getResourceAsStream(path));
        if (scanner.hasNextInt())
            highScore = scanner.nextInt();
        scanner.close();
        return new Score(highScore);
    }

    public static void saveScoreToFile(Score score)  {
        try {
            FileWriter fw = new FileWriter(new File(Score.class.getResource(path).toURI()),false);
            fw.write(String.valueOf(score.getScore()));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
