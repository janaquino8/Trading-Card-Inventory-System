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

public class CardView extends JPanel {
    private final Label nameLabel;
    private final Label cardNoLabel;
    private final Label variantLabel;
    private final Label rarityLabel;
    private final Label collectionCountLabel;
    private final Label baseValueLabel;
    private final Label finalValueLabel;

    public CardView() {
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

/*
CardView - Displays the card information in a formatted box with color coding
public class CardView {


    Method               - displayCard
    String name          - Name of the card
    int cardNo           - Unique identifier number of the card
    String rarityName    - Rarity level of the card (Common, Uncommon, Rare, Legendary)
    String variantName   - Specific variant of the card (Normal, Extended-art, Full-art, Alt-art)
    int collectionCount  - Copies that currently exist in collection
    double baseValue     - Base value of card WITHOUT the variant's increase in value
    double finalValue    - Final value of card AFTER the variant's increase in value
    return type          - void
    Method Comment : Displays a visually formatted card with colored borders, formatted numbers, and organized sections showing all card details

    public void displayCard(String name, int cardNo, String rarityName, String variantName, int collectionCount, double baseValue, double finalValue) {
        String RESET = "\u001B[0m";
        String BOLD = "\u001B[1m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String MAGENTA = "\u001B[35m";
        String CYAN = "\u001B[36m";

        String rarityColor = switch (rarityName) {
            case "Common" -> YELLOW;
            case "Uncommon" -> GREEN;
            case "Rare" -> MAGENTA;
            default -> RED;
        };

        String formattedCardNo = String.format("%,d", cardNo);
        String formattedCollectionCount = String.format("%,d", collectionCount);
        String formattedBaseValue = String.format("%,.2f", baseValue);
        String formattedFinalValue = String.format("%,.2f", finalValue);

        System.out.println(BOLD + CYAN + "╔══════════════════════════════════════╗" + RESET);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + rarityColor + " %-36s " + RESET + BOLD + CYAN + "║\n" + RESET, name);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Card Number:" + rarityColor + " %-23s " + RESET + BOLD + CYAN + "║\n" + RESET, formattedCardNo);

        System.out.println(BOLD + CYAN + "╠──────────────────────────────────────╣" + RESET);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Variant:" + rarityColor + " %-27s " + RESET + BOLD + CYAN + "║\n" + RESET, variantName);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Rarity:" + rarityColor + " %-28s " + RESET + BOLD + CYAN + "║\n" + RESET, rarityName);

        System.out.println(BOLD + CYAN + "╠──────────────────────────────────────╣" + RESET);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Collection Count:" + rarityColor + " %-18s " + RESET + BOLD + CYAN + "║\n" + RESET, formattedCollectionCount);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Base Value:" + rarityColor + " $%-23s " + RESET + BOLD + CYAN + "║\n" + RESET, formattedBaseValue);

        System.out.printf(BOLD + CYAN + "║" + RESET + BOLD + " Final Value:" + rarityColor + " $%-22s " + RESET + BOLD + CYAN + "║\n" + RESET, formattedFinalValue);

        System.out.println(BOLD + CYAN + "╚══════════════════════════════════════╝" + RESET);
    }
}
*/