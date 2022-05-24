import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class Game extends JFrame {
    private final GameBoard gameBoard;
    private final MenuDialog menuDialog;

    private void showDialog(boolean newBtn, boolean contBtn, boolean saveGameBtn,boolean loadGameBtn) {
        gameBoard.stopTimer(true);
        int choice = menuDialog.showDialog(newBtn, contBtn, saveGameBtn, loadGameBtn);
        switch (choice) {
            case MenuDialog.NEW_BTN_ID:
                gameBoard.restart();
                break;
            case MenuDialog.CONTINUE_BTN_ID:
                gameBoard.stopTimer(false);
                break;
            case MenuDialog.EXIT_BTN_ID:
                System.exit(0);
                break;
            case MenuDialog.SAVE_BTN_ID:
                gameBoard.stopTimer(false);
                gameBoard.saveGameBoard();
                break;
            case MenuDialog.LOAD_BTN_ID:
                gameBoard.loadGame();
                break;
        }
        Resource.Music.playOrStop(menuDialog.isSoundChecked());
    }
    

    public Game() {
        this.getRootPane().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
        BorderFactory.createLoweredBevelBorder()));
        this.setTitle("Tetris Game");
        this.setSize(GameBoard.ROW * 26 + 200 + 21, 26 * GameBoard.COLOUMN + 45);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
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
                        showDialog(true, true, true, false);
                }
            }
            public void keyReleased(KeyEvent e) {
            }
        });
        menuDialog = new MenuDialog(this);
        gameBoard = new GameBoard();
        showDialog(true, false ,false,true);
        this.add(gameBoard);
    }

    public void run() {
        this.setVisible(true);
    }

    public void onGameBoardLose() {
        showDialog(true,false,false, false);
    }
}
