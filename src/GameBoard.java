import java.awt.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;

public class GameBoard extends JPanel {
    public final static int ROW = 12, COLOUMN = 22;
    private final long minTimerDelay = 300L, maxTimerDelay = 1000L;
    private long timerDelay;
    private Shape currentShape, nextShape;
    private Timer timer;
    private final Point[][] list;
    private final BufferedImage backgroundImage;
    private ScoreBoard scoreBoard;
    private String SAVE_GAME_PATH = "current/game.txt";

    private void newGameBoard() {
        timerDelay = maxTimerDelay;
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLOUMN; j++)
                list[i][j].setImage(Resource.Images.ID_EMPTY);

        scoreBoard = new ScoreBoard();
        currentShape = Shape.getRandomShape();
        nextShape = Shape.getRandomShape();
        timer = new Timer();
        setTimer();
        repaint();
    }

    private void loadGameBoard() {
        File file = new File(SAVE_GAME_PATH);

        if (!file.exists()) {
            newGameBoard();
            return;
        }

        scoreBoard = new ScoreBoard();

        try {
            Scanner scanner = new Scanner(file);
            scoreBoard.setCurScore(Integer.valueOf(scanner.next()));
            currentShape = null;
            nextShape = null;
            String word = scanner.next();
            Shape.Rotation rotation = Shape.Rotation.valueOf(word);
            for (int i = 0; i < COLOUMN; i++) {
                for (int j = 0; j < ROW; j++) {
                    word = scanner.next();
                    if (word.startsWith("c")) {
                        if (currentShape == null)
                            currentShape = Shape.getShapeFromID(word.substring(1), rotation, j, i);
                    } 
                    list[j][i].setImage(Resource.Images.getImageFromString(word));
                }
            }
            word = scanner.next();
            nextShape = Shape.getShapeFromID(word.substring(1));
            word = scanner.next();
            timerDelay = Integer.valueOf(word);
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancelTimer();
        timer = new Timer();
        setTimer();
        repaint();
    }

    private void setTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        move(Shape.Direction.MOVE_DOWN);
                        long newDelay = maxTimerDelay - (scoreBoard.getCurScore().getScore() / 100) * 50;
                        if (newDelay != timerDelay && newDelay >= minTimerDelay) {
                            timerDelay = newDelay;
                            cancelTimer();
                            timer = new Timer();
                            setTimer();
                        }
                    }
                });
            }
        }, maxTimerDelay, timerDelay);
    }

    private void cancelTimer() {
        timer.cancel();
        timer.purge();
    }

    private void addPointListToBoard(Point[] points) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLOUMN; j++)
                for (Point p2 : points)
                    if (list[i][j].getX() == p2.getX() && list[i][j].getY() == p2.getY())
                        list[i][j].setImage(p2.getImage());
        }
    }

    private int clearRows() {
        boolean gap;
        int numClears = 0;

        for (int i = COLOUMN - 1; i >= 0; i--) {
            gap = false;
            for (int j = 0; j < ROW; j++) {
                if (list[j][i].getImage() == Resource.Images.ID_EMPTY) {
                    gap = true;
                    break;
                }
            }
            if (!gap) {
                deleteRow(i);
                i += 1;
                numClears += 1;
            }
        }

        return numClears;
    }

    private void deleteRow(int row) {
        if (row == 0)
            for (int i = 0; i < ROW; i++)
                list[i][row].setImage(Resource.Images.ID_EMPTY);

        for (int j = row - 1; j >= 0; j--) {
            for (int i = 0; i < ROW; i++) {
                list[i][j + 1].setImage(list[i][j].getImage());
            }
        }
    }

    private boolean isCollidesAt(Shape newShape) {
        Point[] shapes = newShape.getPointList();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLOUMN; j++)
                for (Point p2 : shapes)
                    if ((p2.getX() < 0 || p2.getX() >= ROW || p2.getY() >= COLOUMN)
                            || (list[i][j].getX() == p2.getX() && list[i][j].getY() == p2.getY()
                                    && list[i][j].getImage() != Resource.Images.ID_EMPTY))
                        return true;
        }
        return false;
    }

    public GameBoard() {
        backgroundImage = Resource.Images.ID_BACKGROUND;
        list = new Point[ROW][COLOUMN];
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLOUMN; j++)
                list[i][j] = new Point(i, j, Resource.Images.ID_EMPTY);
        newGameBoard();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLOUMN; j++) {
                g2d.setColor(Color.GRAY);
                g2d.setStroke(new BasicStroke(4));
                g2d.drawRect(26 * list[i][j].getX(), 26 * list[i][j].getY(), Point.SIZE,
                        Point.SIZE);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRect(26 * list[i][j].getX(), 26 * list[i][j].getY(), Point.SIZE,
                        Point.SIZE);
                if (list[i][j].getImage() != Resource.Images.ID_EMPTY) {
                    g2d.drawImage(list[i][j].getImage(), 26 * list[i][j].getX(),
                            26 * list[i][j].getY(), Point.SIZE, Point.SIZE, null);
                }

            }
        }
        currentShape.draw(g);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font(Resource.CustomFont.FONT.getName(), Resource.CustomFont.FONT.getStyle(), 16));
        g2d.drawString("Your Score: " + scoreBoard.getCurScore().getScore(), 26 * ROW + 10, 20);
        g2d.drawString("High Score:  " + scoreBoard.getHighScore().getScore(), 26 * ROW + 10,
                20 + g.getFontMetrics().getHeight());

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(GameBoard.ROW * 26,
                20 + g.getFontMetrics().getHeight() * 2, GameBoard.ROW * 26 + 200,
                20 + g2d.getFontMetrics().getHeight() * 2);

        g2d.setColor(Color.BLACK);
        g2d.drawString("Next Shape", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 3);
        nextShape.drawNextShape(g);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(GameBoard.ROW * 26,
                20 + g2d.getFontMetrics().getHeight() * 5 + 26 * 2, GameBoard.ROW * 26 + 200,
                20 + g2d.getFontMetrics().getHeight() * 5 + 26 * 2);

        g2d.setColor(Color.BLACK);
        g2d.drawString("Manual", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 6 + 26 * 2);

        g2d.drawString("Arrow up: Rotate ", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 7 + 26 * 2);

        g2d.drawString("Arrow down: Down", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 8 + 26 * 2);

        g2d.drawString("Arrow left: Left", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 9 + 26 * 2);

        g2d.drawString("Arrow right: Right", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 10 + 26 * 2);

        g2d.drawString("P: Show menu", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 11 + 26 * 2);

    }

    public void rotateLeft() {
        currentShape.rotateLeft();
        if (isCollidesAt(currentShape))
            currentShape.rotateRight();
        repaint();
    }

    public void rotateRight() {
        currentShape.rotateRight();
        if (isCollidesAt(currentShape))
            currentShape.rotateLeft();
        repaint();
    }

    public void move(Shape.Direction d) {
        currentShape.move(d);
        if (isCollidesAt(currentShape)) {
            currentShape.move(Shape.Direction.getOppositeDirection(d));
            if (d == Shape.Direction.MOVE_DOWN) {
                addPointListToBoard(currentShape.getPointList());
                scoreBoard.setCurScoreOnRowClear(clearRows());
                currentShape = nextShape;
                nextShape = Shape.getRandomShape();
                if (isCollidesAt(currentShape)) {
                    ((Game) this.getTopLevelAncestor()).onGameBoardLose();
                    return;
                }
            }
        }
        repaint();
    }

    public void restart() {
        cancelTimer();
        Score.saveScoreToFile(scoreBoard.getHighScore(), ScoreBoard.HIGH_SCORE_PATH, false);
        newGameBoard();
    }

    public void stopTimer(boolean stop) {
        if (stop) {
            cancelTimer();
        } else {
            timer = new Timer();
            setTimer();
        }
    }

    public void saveGameBoard() {
        File file = new File(SAVE_GAME_PATH);
        try {
            if (!file.createNewFile())
                new FileWriter(SAVE_GAME_PATH, false).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Score.saveScoreToFile(scoreBoard.getCurScore(), SAVE_GAME_PATH, true);
        Score.saveScoreToFile(scoreBoard.getHighScore(), ScoreBoard.HIGH_SCORE_PATH, false);
        String lineSperator = System.getProperty("line.separator");
        FileWriter fw;
        try {
            fw = new FileWriter(file, true);
            boolean isDraw = false;
            fw.write(currentShape.getCurRotation().name() + lineSperator);
            for (int i = 0; i < COLOUMN; i++) {
                for (int j = 0; j < ROW; j++) {
                    isDraw = false;
                    for (Point p : currentShape.getPointList()) {
                        if (p.getX() == list[j][i].getX() && p.getY() == list[j][i].getY()) {
                            fw.write("c" + Resource.Images.getImageString(p.getImage()) + " ");
                            isDraw = true;
                            break;
                        }
                    }
                    if (!isDraw)
                        fw.write(Resource.Images.getImageString(list[j][i].getImage()) + " ");

                }
                fw.write(lineSperator);
            }
            fw.write("p" + Resource.Images.getImageString(nextShape.getPointList()[0].getImage()));
            fw.write(lineSperator);
            fw.write(String.valueOf(timerDelay));
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadGame() {
        loadGameBoard();
    }
}
