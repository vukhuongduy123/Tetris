import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.image.BufferedImage;

public class GameBoard extends JPanel {
    final static int ROW = 12, COLOUMN = 22;
    private long timerDelay;
    private Point[][] list;
    private Shape currentShape,nextShape;
    private Timer timer;
    private BufferedImage[][] blocks;


    GameBoard() {
        init();
    }

    private void init() {
        timerDelay = 1000L;
        list = new Point[ROW][COLOUMN];
        blocks = new BufferedImage[ROW][COLOUMN];
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLOUMN; j++)
                list[i][j] = new Point(i, j, ColorID.ID_EMPTY);

        ScoreBoard.init();
        currentShape = getRandomShape();
        nextShape = getRandomShape();
        timer = new Timer();
        setTimer();
        paint();
    }

    private void setTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        move(Shape.Direction.MOVE_DOWN);
                        long newDelay = 1000L - (ScoreBoard.getCurScore().getScore() / 100) * 50;
                        if (newDelay != timerDelay && newDelay >= 100L) {
                            timerDelay = newDelay;
                            cancelTimer();
                            timer = new Timer();
                            setTimer();
                        }
                    }
                });
            }
        }, 0, timerDelay);
    }

    private void cancelTimer() {
        timer.cancel();
        timer.purge();
    }

    private void loadImages() {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++)
                blocks[i][j] = ColorID.getBufferedImageFromColorID(list[i][j].getColorID());

        }
        currentShape.loadImages();
        nextShape.loadImages();
    }

    private void paint() {
        loadImages();
        currentShape.loadImages();

        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        super.paintComponent(g2d);
        g.fillRect(0, 0, GameBoard.ROW * 26 + 120 + 21, 26 * GameBoard.COLOUMN + 45);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLOUMN; j++) {
                if (blocks[i][j] == null) {
                    g2d.setColor(Color.GRAY);
                    g2d.setStroke(new BasicStroke(4));
                    g2d.drawRect(26 * list[i][j].getX(), 26 * list[i][j].getY(), list[i][j].getSize(),
                            list[i][j].getSize());
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.fillRect(26 * list[i][j].getX(), 26 * list[i][j].getY(), list[i][j].getSize(),
                            list[i][j].getSize());
                } else {
                    g2d.drawImage(blocks[i][j], 26 * list[i][j].getX(), 26 * list[i][j].getY(), list[i][j].getSize(),
                            list[i][j].getSize(), null);
                }
            }
        }
        currentShape.draw(g);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Serif", Font.BOLD, 16));
        g2d.drawString("Your Score: " + ScoreBoard.getCurScore().getScore(), 26 * ROW + 10, 20);
        g2d.drawString("High Score: " + ScoreBoard.getHighScore().getScore(), 26 * ROW + 10,
                20 + g.getFontMetrics().getHeight());

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(GameBoard.ROW * 26,
                20 + g.getFontMetrics().getHeight() * 2, GameBoard.ROW * 26 + 140,
                20 + g2d.getFontMetrics().getHeight() * 2);
        
        g2d.setColor(Color.WHITE);
        g2d.drawString("Next Shape", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 3);
        nextShape.drawNextShape(g);

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(GameBoard.ROW * 26,
                20 + g2d.getFontMetrics().getHeight() * 5 + 26 * 2, GameBoard.ROW * 26 + 140,
                20 + g2d.getFontMetrics().getHeight() * 5 + 26 * 2);

        g2d.setColor(Color.WHITE);
        g2d.drawString("Manual", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 6 + 26 * 2);

        g2d.drawString("- Arrow up:", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 7 + 26 * 2);
        
        g2d.drawString("Rotate", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 8 + 26 * 2);
        
        g2d.drawString("- Arrow down:", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 9 + 26 * 2);

        g2d.drawString("Shift down", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 10 + 26 * 2);

        g2d.drawString("- Arrow left:", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 11 + 26 * 2);

        g2d.drawString("Shift left", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 12 + 26 * 2);

        g2d.drawString("- Arrow right:", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 13 + 26 * 2);

        g2d.drawString("Shift right", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 14 + 26 * 2);

        g2d.drawString("- P: Show menu", 26 * ROW + 10,
                20 + g2d.getFontMetrics().getHeight() * 15 + 26 * 2);
        
    }

    private void addPointListToBoard(Point[] points) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLOUMN; j++)
                for (Point p2 : points)
                    if (list[i][j].getX() == p2.getX() && list[i][j].getY() == p2.getY())
                        list[i][j].setColorID(p2.getColorID());
        }
    }

    private int clearRows() {
        boolean gap;
        int numClears = 0;

        for (int i = COLOUMN - 1; i > 0; i--) {
            gap = false;
            for (int j = 0; j < ROW; j++) {
                if (list[j][i].getColorID() == ColorID.ID_EMPTY) {
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
        for (int j = row - 1; j > 0; j--) {
            for (int i = 0; i < ROW; i++) {
                list[i][j + 1].setColorID(list[i][j].getColorID());
            }
        }
    }

    private Shape getRandomShape() {
        switch (Shape.Type.getRandomType()) {
            case ISHAPE:
                return new IShape();
            case JSHAPE:
                return new JShape();
            case LSHAPE:
                return new LShape();
            case OSHAPE:
                return new OShape();
            case SSHAPE:
                return new SShape();
            case TSHAPE:
                return new TShape();
            case ZSHAPE:
                return new ZShape();
            default:
                return null;
        }
    }

    void rotateLeft() {
        currentShape.rotateLeft();
        if (isCollidesAt(currentShape))
            currentShape.rotateRight();
        paint();
    }

    void rotateRight() {
        currentShape.rotateRight();
        if (isCollidesAt(currentShape))
            currentShape.rotateLeft();
        paint();
    }

    void move(Shape.Direction d) {
        currentShape.move(d);
        if (isCollidesAt(currentShape)) {
            currentShape.move(Shape.Direction.getOppositeDirection(d));
            if (d == Shape.Direction.MOVE_DOWN) {
                addPointListToBoard(currentShape.getPointList());
                ScoreBoard.setCurScoreOnRowClear(clearRows());
                currentShape = nextShape;
                nextShape = getRandomShape();
                if (isCollidesAt(currentShape))
                    ((Game) this.getTopLevelAncestor()).onGameBoardLose();
            }
        }
        paint();
    }

    private boolean isCollidesAt(Shape newShape) {
        final Point[] shapes = newShape.getPointList();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLOUMN; j++)
                for (Point p2 : shapes)
                    if ((p2.getX() < 0 || p2.getX() >= ROW || p2.getY() >= COLOUMN)
                            || (list[i][j].getX() == p2.getX() && list[i][j].getY() == p2.getY()
                                    && list[i][j].getColorID() != ColorID.ID_EMPTY))
                        return true;
        }
        return false;
    }

    public void restart() {
        cancelTimer();
        Score.saveScoreToFile(ScoreBoard.getHighScore());
        init();
    }

    public void pauseTimer() {
        cancelTimer();
    }

    public void resumeTimer() {
        timer = new Timer();
        setTimer();
    }

}
