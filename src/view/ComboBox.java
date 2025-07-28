package src.view;

import javax.swing.*;
import java.awt.*;

public class ComboBox extends JComboBox {
    public ComboBox(String[] options) {
        super(options);
        this.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        this.setPreferredSize(new Dimension(300, 30));
        this.setMinimumSize(new Dimension(300, 30));
        this.setMaximumSize(new Dimension(300, 30));
        this.setForeground(Color.decode("#1E1F22"));
        this.setBackground(Color.decode("#BCBEC4"));
    }
}
