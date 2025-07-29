package src.view;

import javax.swing.*;
import java.awt.*;

/*
This is what I used in my test project to display it:

        Frame frame = new Frame();
        frame.renameWindow("Card View");

        CardView cardView = new CardView();
        cardView.displayCard("Dragon's Breath", 12345, "Legendary", "Full-art", 3, 24.99, 34.99);

        frame.add(cardView, BorderLayout.CENTER);
        frame.setVisible(true);
*/

public class CardGUI extends JPanel {
    private final Label nameLabel;
    private final Label cardNoLabel;
    private final Label variantLabel;
    private final Label rarityLabel;
    private final Label collectionCountLabel;
    private final Label baseValueLabel;
    private final Label finalValueLabel;

    public CardGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#BCBEC4"));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setMaximumSize(new Dimension(350, 400));

        // Initialize components
        nameLabel = createStyledLabel("", Font.BOLD | Font.ITALIC, 22);
        cardNoLabel = createStyledLabel("Card Number: ", Font.BOLD | Font.ITALIC, 18);
        variantLabel = createStyledLabel("Variant: ", Font.BOLD | Font.ITALIC, 18);
        rarityLabel = createStyledLabel("Rarity: ", Font.BOLD | Font.ITALIC, 18);
        collectionCountLabel = createStyledLabel("Collection Count: ", Font.BOLD | Font.ITALIC, 18);
        baseValueLabel = createStyledLabel("Base Value: ", Font.BOLD | Font.ITALIC, 18);
        finalValueLabel = createStyledLabel("Final Value: ", Font.BOLD | Font.ITALIC, 18);

        // Add components to panel with separators
        add(createBorderedPanel(nameLabel));
        add(createSeparator());
        add(createBorderedPanel(cardNoLabel));
        add(createSeparator());
        add(createBorderedPanel(variantLabel));
        add(createBorderedPanel(rarityLabel));
        add(createSeparator());
        add(createBorderedPanel(collectionCountLabel));
        add(createBorderedPanel(baseValueLabel));
        add(createBorderedPanel(finalValueLabel));
    }

    public void displayCard(String name, int cardNo, String rarityName, String variantName,
                            int collectionCount, double baseValue, double finalValue) {
        // Set colors based on rarity
        Color rarityColor = switch (rarityName) {
            case "Common" -> Color.decode("#CC99FF");
            case "Uncommon" -> Color.decode("#90EE90");
            case "Rare" -> Color.decode("#66B2FF");
            default -> Color.decode("#FF6666"); // Legendary
        };

        // Format values
        String formattedCardNo = String.format("%,d", cardNo);
        String formattedCollectionCount = String.format("%,d", collectionCount);
        String formattedBaseValue = String.format("$%,.2f", baseValue);
        String formattedFinalValue = String.format("$%,.2f", finalValue);

        // Update labels with formatted text and colors
        nameLabel.setText(name);
        nameLabel.setForeground(rarityColor);

        cardNoLabel.setText("Card Number: " + formattedCardNo);
        cardNoLabel.setForeground(rarityColor);

        variantLabel.setText("Variant: " + variantName);
        variantLabel.setForeground(rarityColor);

        rarityLabel.setText("Rarity: " + rarityName);
        rarityLabel.setForeground(rarityColor);

        collectionCountLabel.setText("Collection Count: " + formattedCollectionCount);
        collectionCountLabel.setForeground(rarityColor);

        baseValueLabel.setText("Base Value: " + formattedBaseValue);
        baseValueLabel.setForeground(rarityColor);

        finalValueLabel.setText("Final Value: " + formattedFinalValue);
        finalValueLabel.setForeground(rarityColor);
    }

    private Label createStyledLabel(String text, int style, int fontSize) {
        return new Label(text, style, fontSize, true); // alignLeft = true
    }

    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.decode("#BCBEC4"));
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);
        separator.setMaximumSize(new Dimension(Short.MAX_VALUE, 2));
        return separator;
    }

    private JPanel createBorderedPanel(JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(component, BorderLayout.WEST);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }
}