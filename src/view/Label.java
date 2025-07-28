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
        if (style == Font.PLAIN || style == Font.BOLD || style == (Font.BOLD | Font.ITALIC) || style == Font.ITALIC) {
            this.setFont(new Font("Gill Sans MT", style, fontSize));
        }
    }

    // Constructor for CardView-style labels
    public Label(String text, int style, int fontSize, boolean alignLeft) {
        this(text, style, fontSize); // Reuse font styling
        if (alignLeft) {
            this.setHorizontalAlignment(SwingConstants.LEFT); // Override center alignment
        }
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding
    }
}
