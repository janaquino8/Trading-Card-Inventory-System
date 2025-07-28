package src.view;

import java.awt.*;
import javax.swing.*;

public class List extends JList {
    public List(String[] incomingList) {
        super(incomingList);

        this.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        this.setForeground(Color.decode("#1E1F22"));
        this.setBackground(Color.decode("#BCBEC4"));
        this.setEnabled(true);
    }
}
