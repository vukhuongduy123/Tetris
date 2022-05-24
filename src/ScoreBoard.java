
public class ScoreBoard {
    private Score currentScore, highScore;
    public static final String HIGH_SCORE_PATH = "highscore/highscore.txt";

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

    public ScoreBoard() {
        currentScore = new Score(0);
        highScore = Score.getScoreFromFile(HIGH_SCORE_PATH);
    }

    public Score getCurScore() {
        return currentScore;
    }

    public void setCurScoreOnRowClear(int rows) {
        currentScore.setScore(currentScore.getScore() + addScoreOnRowClear(rows));
        if (currentScore.getScore() > highScore.getScore())
            highScore.setScore(currentScore.getScore());
    }

    public Score getHighScore() {
        return highScore;
    }

    public void setCurScore(int score) {
        currentScore.setScore(score);
    }
}
