package src.view;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Label header;

    public Frame() {
        this.setLayout(new BorderLayout());
        this.setSize(900, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#1E1F22"));
        this.setVisible(true);

        JPanel head = new JPanel();
        head.setLayout(new GridLayout(2, 0, 25, 0));
        head.setPreferredSize(new Dimension(1000, 100));
        head.setOpaque(false);

        Label title = new Label("Trading Card Inventory System", Font.PLAIN, 25);
        header = new Label("", Font.PLAIN, 50);

        head.add(title);
        head.add(header);
        this.add(head, BorderLayout.NORTH);
    }

    public void renameWindow(String name) {
        this.setTitle(name + " | TCIS");
        header.setText(name);
    }
}
