import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class Game extends JFrame {
    private GameBoard gameBoard;
    private Music music;
    private MenuDialog menuDialog;
    public Game() {
        init();
        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        gameBoard.rotateLeft();
                        break;
                    case KeyEvent.VK_DOWN:
                        gameBoard.move(Shape.Direction.MOVE_DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        gameBoard.move(Shape.Direction.MOVE_LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        gameBoard.move(Shape.Direction.MOVE_RIGHT);
                        break;
                    case KeyEvent.VK_P:
                        gameBoard.pauseTimer();
                        int choice = menuDialog.showDialog(true, true);
                        music.playOrStop(menuDialog.isSoundChecked());
                        if (choice == 0)
                            gameBoard.restart();
                        else if (choice == 1)
                            gameBoard.resumeTimer();
                        else if (choice == 2)
                            System.exit(0);
                }
            }
            public void keyReleased(KeyEvent e) {
            }
        });
        music = new Music();
        gameBoard = new GameBoard();
        menuDialog = new MenuDialog(this);
        this.add(gameBoard);

    }

    private void init() {
        this.getRootPane().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        this.setTitle("Tetris Game");
        this.setSize(GameBoard.ROW * 26 + 140 + 21, 26 * GameBoard.COLOUMN + 45);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void run() {
        this.setVisible(true);
        music.playOrStop(menuDialog.isSoundChecked());
    }

    public void onGameBoardLose() {
        gameBoard.pauseTimer();
        int choice = menuDialog.showDialog(true, false);
        music.playOrStop(menuDialog.isSoundChecked());
        if (choice == 0)
            gameBoard.restart();
        else if (choice == 1)
            gameBoard.resumeTimer();
        else if (choice == 2)
            System.exit(0);
    }
}
