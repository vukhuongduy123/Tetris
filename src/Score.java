import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Score {
    private int score;

    public Score(int s) {
        this.score = s;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int s) {
        score = s;
    }

    public static Score getScoreFromFile(String path) {
        int highScore = 0;
        File file = new File(path);
        Scanner scanner = null;
        try {
            if (file.createNewFile()) {
                FileWriter fw = new FileWriter(file, false);
                fw.write(0);
                fw.close();
            }
            scanner = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (scanner != null && scanner.hasNextInt()) {
            highScore = scanner.nextInt();
            scanner.close();
        }
        return new Score(highScore);
    }

    public static void saveScoreToFile(Score score, String path, boolean isAppend) {
        File file = new File(path);
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file, isAppend);
            fw.write(java.lang.String.valueOf(score.getScore()) + System.lineSeparator());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
