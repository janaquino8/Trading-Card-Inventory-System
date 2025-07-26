package src.view;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    public Label(String name) {
        super(name, SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setForeground(Color.decode("#BCBEC4"));
    }

    public Label(String name, int style, int fontSize) {
        this(name);
        if (style == Font.PLAIN || style == Font.BOLD || style == Font.ITALIC) {
            this.setFont(new Font("Gill Sans MT", style, fontSize));
        }
    }
}
