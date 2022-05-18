import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;


public class MenuDialog {
    private JDialog jDialog;
    private int buttonPressID;
    private JLabel label;
    private JButton newGameButton, continueButton, exitButton;
    private JCheckBox checkSoundBox;
    public MenuDialog (JFrame jFrame) {
        buttonPressID = -1;
        jDialog = new JDialog(jFrame,"Main menu",true);
        jDialog.setAlwaysOnTop(true);
        jDialog.setModal(true);
        jDialog.setSize(new Dimension(300, 600));
        jDialog.setResizable(false);
        jDialog.setLocationRelativeTo(null);
        jDialog.getRootPane().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        
        label = new JLabel("Main menu");
        label.setFont(new Font("Serif", Font.PLAIN, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGameButton = new JButton("New game");
        newGameButton.setMaximumSize(new Dimension(120, 40));
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPressID = 0;
                jDialog.dispose();
            }
        });

        continueButton = new JButton("Continue");
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setMaximumSize(new Dimension(120, 40));
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPressID = 1;
                jDialog.dispose();
            }
        });


        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setMaximumSize(new Dimension(120, 40));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPressID = 2;
                jDialog.dispose();
            }
        });

        checkSoundBox = new JCheckBox("Turn on/off sound", true);
        checkSoundBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkSoundBox.setMaximumSize(new Dimension(130, 40));

        jDialog.setLayout(new BoxLayout(jDialog.getContentPane(), BoxLayout.Y_AXIS));
        jDialog.add(label);
        jDialog.add(Box.createRigidArea(new Dimension(40, 40)));
        jDialog.add(newGameButton);
        jDialog.add(Box.createRigidArea(new Dimension(40, 40)));
        jDialog.add(continueButton);
        jDialog.add(Box.createRigidArea(new Dimension(40, 40)));
        jDialog.add(exitButton);
        jDialog.add(Box.createRigidArea(new Dimension(40, 40)));
        jDialog.add(checkSoundBox);

    }

    public int showDialog(boolean showNewGameButton, boolean showContinueButton) {
        newGameButton.setEnabled(showNewGameButton);
        continueButton.setEnabled(showContinueButton);
        jDialog.setVisible(true);
        return buttonPressID;
    }

    public boolean isSoundChecked() {
        return checkSoundBox.isSelected();
    }
}
