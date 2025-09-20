package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton Restart;
    private JButton Quit;
    private JPanel MainPanel;

    public Menu(Game g) {
        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.Redo();
            }
        });
        Quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel GetJPanel() {
        return MainPanel;
    }
}
