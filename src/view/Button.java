package src.view;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String name) {
        super(name);
        this.setFocusable(false);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        this.setForeground(Color.decode("#1E1F22"));
        this.setBackground(Color.decode("#BCBEC4"));
    }
}
