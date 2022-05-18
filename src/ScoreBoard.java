
public class ScoreBoard {
    private static Score currentScore, highScore;

    private static int addScoreOnRowClear(int numClears) {
		switch (numClears) {
            case 1:
                return 100;
            case 2:
                return 300;
            case 3:
                return 500;
            case 4:
                return 800;
            default:
                return 0;
        }
    }

    public static void init() {
        currentScore = new Score(0);
        highScore = Score.getScoreFromFile();
    }

    public static Score getCurScore() {
        return currentScore;
    }

    public static void setCurScoreOnRowClear(int rows) {
        currentScore.setScore(currentScore.getScore() + addScoreOnRowClear(rows));
        if (currentScore.getScore() > highScore.getScore())
            highScore.setScore(currentScore.getScore());
    }

    public static Score getHighScore() {
        return highScore;
    }
}
