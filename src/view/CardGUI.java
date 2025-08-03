package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CardGUI extends Frame {
    private Button btnBack;

    public CardGUI() {
        super();
        btnBack = new Button("Back");
    }

    public void displayCard(String name, int cardNo, String rarityName, String variantName,
                            int collectionCount, double baseValue, double finalValue) {
        renameWindow("Card Details");

        // Main panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        // Card info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(7, 1, 0, 10)); // 7 rows, 1 column, 10px vertical gap
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set colors based on rarity
        Color rarityColor = switch (rarityName) {
            case "Common" -> Color.decode("#CC99FF");
            case "Uncommon" -> Color.decode("#90EE90");
            case "Rare" -> Color.decode("#66B2FF");
            default -> Color.decode("#FF6666"); // Legendary
        };

        // Create labels for each piece of information
        Label nameLabel = createDetailLabel("Name: " + name, rarityColor, Font.BOLD, 25);
        Label cardNoLabel = createDetailLabel("Card Number: " + cardNo, rarityColor, Font.PLAIN, 22);
        Label variantLabel = createDetailLabel("Variant: " + variantName, rarityColor, Font.PLAIN, 22);
        Label rarityLabel = createDetailLabel("Rarity: " + rarityName, rarityColor, Font.PLAIN, 22);
        Label countLabel = createDetailLabel("Collection Count: " + collectionCount, rarityColor, Font.PLAIN, 22);
        Label baseValueLabel = createDetailLabel(String.format("Base Value: $%,.2f", baseValue), rarityColor, Font.PLAIN, 22);
        Label finalValueLabel = createDetailLabel(String.format("Final Value: $%,.2f", finalValue), rarityColor, Font.PLAIN, 22);

        // Add all labels to info panel
        infoPanel.add(nameLabel);
        infoPanel.add(cardNoLabel);
        infoPanel.add(variantLabel);
        infoPanel.add(rarityLabel);
        infoPanel.add(countLabel);
        infoPanel.add(baseValueLabel);
        infoPanel.add(finalValueLabel);

        mainPanel.add(infoPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Button panel (centered)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnBack);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }

    private Label createDetailLabel(String text, Color color, int style, int size) {
        Label label = new Label(text, style, size);
        label.setForeground(color);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        return label;
    }

    public void setBackAction(ActionListener listener) {
        btnBack.addActionListener(listener);
    }
}