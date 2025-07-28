package src.view;

import javax.swing.*;
import java.awt.*;

public class TextField extends JTextField {
    public TextField() {
        super("");
        this.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        this.setPreferredSize(new Dimension(300, 30));
        this.setMinimumSize(new Dimension(300, 30));
        this.setMaximumSize(new Dimension(300, 30));
        this.setForeground(Color.decode("#1E1F22"));
        this.setBackground(Color.decode("#BCBEC4"));
        this.setCaretColor(Color.decode("#1E1F22"));
    }
}
