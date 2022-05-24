import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class MenuDialog {
    private final JDialog dialog;
    private int buttonPressID;
    private final JLabel label;
    private final JButton newGameBtn, continueBtn, exitBtn, saveBtn,loadBtn;
    private final JCheckBox checkSoundBox;
    private final Dimension btnDimension, dialogDimension;
    static public final int NEW_BTN_ID = 0, CONTINUE_BTN_ID = 1, EXIT_BTN_ID = 2, SAVE_BTN_ID = 3, LOAD_BTN_ID = 4;

    private JButton getButton(String text, int id) {
        JButton button = new JButton(text,
                new ImageIcon(Resource.Images.ID_BTN.getScaledInstance((int) btnDimension.getWidth(),
                        (int) btnDimension.getHeight(), java.awt.Image.SCALE_SMOOTH)));
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setMaximumSize(btnDimension);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font(Resource.CustomFont.FONT.getName(), Font.BOLD, 20));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPressID = id;
                dialog.dispose();
            }
        });
        return button;
    }

    public MenuDialog(JFrame jFrame) {
        btnDimension = new Dimension(180, 40);
        dialogDimension = new Dimension(300, 600);
        buttonPressID = -1;
        dialog = new JDialog(jFrame, "Main menu", true);
        dialog.setAlwaysOnTop(true);
        dialog.setSize(dialogDimension);
        dialog.setUndecorated(true);
        dialog.setLocationRelativeTo(null);
        dialog.getRootPane().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));

        label = new JLabel("Main menu");
        label.setFont(new Font(Resource.CustomFont.FONT.getName(), Font.BOLD, 32));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGameBtn = getButton("New Game", NEW_BTN_ID);
        continueBtn = getButton("Continue", CONTINUE_BTN_ID);
        exitBtn = getButton("Exit", EXIT_BTN_ID);
        saveBtn = getButton("Save", SAVE_BTN_ID);
        loadBtn = getButton("Load", LOAD_BTN_ID);

        checkSoundBox = new JCheckBox("Turn on/off sound", true);
        checkSoundBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkSoundBox.setMaximumSize(btnDimension);
        checkSoundBox.setFont(new Font(Resource.CustomFont.FONT.getName(), Font.BOLD, 12));

        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.add(Box.createRigidArea(new Dimension(20, 20)));
        dialog.add(label);
        dialog.add(Box.createRigidArea(new Dimension(40, 40)));
        dialog.add(newGameBtn);
        dialog.add(Box.createRigidArea(new Dimension(40, 40)));
        dialog.add(continueBtn);
        dialog.add(Box.createRigidArea(new Dimension(40, 40)));
        dialog.add(saveBtn);
        dialog.add(Box.createRigidArea(new Dimension(40, 40)));
        dialog.add(loadBtn);
        dialog.add(Box.createRigidArea(new Dimension(40, 40)));
        dialog.add(exitBtn);
        dialog.add(Box.createRigidArea(new Dimension(40, 40)));
        dialog.add(checkSoundBox);

    }

    public int showDialog(boolean newBtn, boolean contBtn, boolean saveGameBtn,boolean loadGameBtn) {
        newGameBtn.setEnabled(newBtn);
        continueBtn.setEnabled(contBtn);
        saveBtn.setEnabled(saveGameBtn);
        loadBtn.setEnabled(loadGameBtn);

        dialog.setVisible(true);
        return buttonPressID;
    }

    public boolean isSoundChecked() {
        return checkSoundBox.isSelected();
    }
}
