package src.view;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    protected Font titleFont;
    protected Font headerFont;
    protected Color fontColor;
    JLabel header;

    public Frame(String windowTitle) {
        super(windowTitle);

        titleFont = new Font("Gill Sans MT", Font.PLAIN, 25);
        headerFont = new Font("Gill Sans MT", Font.PLAIN, 50);
        fontColor = Color.decode("#BCBEC4");

        this.setLayout(new BorderLayout());
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#1E1F22"));
        this.setVisible(true);

        JPanel head = new JPanel();
        head.setLayout(new BoxLayout(head, BoxLayout.Y_AXIS));
        head.setPreferredSize(new Dimension(900, 100));
        head.setAlignmentX(Component.CENTER_ALIGNMENT);
        head.setOpaque(false);

        JLabel title = new JLabel();
        title.setText("Trading Card Inventory System");
        title.setFont(titleFont);
        title.setForeground(fontColor);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        header = new JLabel();
        header.setFont(headerFont);
        header.setForeground(fontColor);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        head.add(title);
        head.add(header);
        this.add(head, BorderLayout.NORTH);
    }
}
