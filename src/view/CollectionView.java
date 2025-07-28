package src.view;

import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionListener;

/*
CollectionView - Handles all visual displays related to card collection operations
 */
public class CollectionView extends Frame {
    private Button buttons[];
    private TextField cardNameField;
    private ComboBox rarityBox;
    private ComboBox variantBox;
    private TextField valueField;
    private Button btnBack;
    private Button btnAddCard;
    private boolean isFieldsEmpty;
    private boolean isBoxesEmpty;
    DocumentListener listener;

    public CollectionView() {
        super();
        btnBack = new Button("Back");
    }

    public void displayAddCard(ActionListener listener) {
        ActionListener boxListener = e -> {
            if (e.getSource() == rarityBox || e.getSource() == variantBox) {
                isBoxesEmpty = getRarity() <= 0 || getVariant() <= 0;
                updateBtnAdd();

                if (getRarity() == 3 || getRarity() == 4) {
                    variantBox.setEnabled(true);
                }
                else {
                    variantBox.setSelectedIndex(1);
                    variantBox.setEnabled(false);
                }
            }
        };
        DocumentListener fieldListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isFieldsEmpty = getCardName().isEmpty() && valueField.getText().isEmpty();
                updateBtnAdd();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isFieldsEmpty = getCardName().isEmpty() && valueField.getText().isEmpty();
                updateBtnAdd();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        };

        renameWindow("Add a Card");
        isBoxesEmpty = true;

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);
        this.add(body, BorderLayout.CENTER);

        JPanel cardName = new JPanel();
        Label cardNameLabel = new Label("Name: ", Font.PLAIN, 30);
        cardNameField = new TextField();
        setPanel(cardNameLabel, cardNameField, cardName);

        JPanel rarity = new JPanel();
        Label rarityLabel = new Label("Rarity: ", Font.PLAIN, 30);
        rarityBox = new ComboBox(new String[]{"", "Common", "Uncommon", "Rare", "Legendary"});
        setPanel(rarityLabel, rarityBox, rarity);

        JPanel variant = new JPanel();
        Label variantLabel = new Label("Variant: ", Font.PLAIN, 30);
        variantBox = new ComboBox(new String[]{"", "Normal", "Extended-art", "Full-art", "Alt-art"});
        variantBox.setEnabled(false);
        setPanel(variantLabel, variantBox, variant);

        JPanel value = new JPanel();
        Label valueLabel = new Label("Value (USD): ", Font.PLAIN, 30);
        valueField = new TextField();
        setPanel(valueLabel, valueField, value);

        setActionListener(new ComboBox[]{rarityBox, variantBox}, boxListener);
        setDocumentListener(new TextField[]{cardNameField, valueField}, fieldListener);
        setPanels(new JPanel[]{cardName, rarity, variant, value}, body);

        JPanel options = new JPanel();
        btnAddCard = new Button("Add");
        btnAddCard.setEnabled(false);
        setPanel(new Button[]{btnBack, btnAddCard}, options);

        setActionListener(new Button[]{btnBack, btnAddCard}, listener);

        this.add(options, BorderLayout.SOUTH);
    }

    public void displayCollection(ArrayList<String> cardsList) {
        renameWindow("Collection");

        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        body.add(Box.createVerticalStrut(15));
        List list = new List(cardsList);
        JScrollPane scroll = new JScrollPane(list.list);
        body.add(scroll);
        body.setVisible(true);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);
        options.setVisible(true);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    private void setPanel(Label label, TextField field, JPanel panel) {
        panel.add(label);
        panel.add(field);
        panel.setOpaque(false);
    }

    private void setPanel(Label label, ComboBox box, JPanel panel) {
        panel.add(label);
        panel.add(box);
        panel.setOpaque(false);
    }

    private void setPanel(Button[] buttons, JPanel panel) {
        for (Button b : buttons) {
            panel.add(b);
        }
        panel.setOpaque(false);
    }

    private void setPanels(JPanel[] panelList, JPanel mainPanel) {
        for (JPanel p : panelList) {
            mainPanel.add(Box.createVerticalStrut(15));
            mainPanel.add(p);
        }
    }

    private void setActionListener(Button[] buttons, ActionListener listener) {
        for (Button b : buttons) {
            b.addActionListener(listener);
        }
    }

    private void setActionListener(ComboBox[] comboBoxes, ActionListener listener) {
        for (ComboBox c : comboBoxes) {
            c.addActionListener(listener);
        }
    }

    private void setDocumentListener(TextField[] textFields, DocumentListener listener) {
        for (TextField t : textFields) {
            t.getDocument().addDocumentListener(listener);
        }
    }

    private void updateBtnAdd() {
        btnAddCard.setEnabled(!isFieldsEmpty && !isBoxesEmpty && getValue() != -1);
    }

    public String getCardName() {
        if (cardNameField != null) {
            return cardNameField.getText();
        }
        return "";
    }

    public int getRarity() {
        if (rarityBox != null) {
            return rarityBox.getSelectedIndex();
        }
        return -1;
    }

    public int getVariant() {
        if (variantBox != null) {
            return variantBox.getSelectedIndex();
        }
        return -1;
    }

    public double getValue() {
        if (!valueField.getText().matches("-?\\d+(\\.\\d+)?")) {
            return -1;
        }
        else {
            return Double.parseDouble(valueField.getText());
        }

//        double input;
//
//        try {
//            input = Double.parseDouble(valueField.getText());
//        }
//        catch (NumberFormatException e) {
//            input = -1;
//        }
//
//        return input;
    }


//    private final String RESET = "\u001B[0m";
//    private final String BOLD = "\u001B[1m";
//    private final String RED = "\u001B[31m";
//    private final String GREEN = "\u001B[32m";
//    private final String BLUE = "\u001B[34m";
//
//    /*
//    Method       - printConfirmationMsg
//    int option   - Numeric code representing different confirmation/error messages
//    return type  - void
//    Method Comment : Prints colored status messages for collection operations (success/error cases)
//     */
//    public void printConfirmationMsg(int option) {
//        System.out.println();
//        switch (option) {
//            case 0:
//                System.out.println(GREEN + "Action successfully cancelled." + RESET);
//                break;
//            case 1:
//                System.out.println(GREEN + "Card count successfully incremented." + RESET);
//                break;
//            case 2:
//                System.out.println(GREEN + "Card count successfully decremented." + RESET);
//                break;
//            case 3:
//                System.out.println(RED + "Card not found." + RESET);
//                break;
//            case 4:
//                System.out.println(RED + "Card has no copies in collection." + RESET);
//                break;
//            case 5:
//                System.out.println(RED + "There are currently no cards in collection." + RESET);
//                break;
//        }
//        System.out.println();
//    }
//
//    /*
//    Method       - displayUpdateCardCount
//    return type  - void
//    Method Comment : Displays large ASCII art header for "Update Card Count" with search options
//     */
//    public void displayUpdateCardCount() {
//        System.out.println(BOLD + BLUE +
//                " _   _   ____    ____       _      _____   _____       ____      _      ____    ____        ____    ___    _   _   _   _   _____ \n" +
//                "| | | | |  _ \\  |  _ \\     / \\    |_   _| | ____|     / ___|    / \\    |  _ \\  |  _ \\      / ___|  / _ \\  | | | | | \\ | | |_   _|\n" +
//                "| | | | | |_) | | | | |   / _ \\     | |   |  _|      | |       / _ \\   | |_) | | | | |    | |     | | | | | | | | |  \\| |   | |  \n" +
//                "| |_| | |  __/  | |_| |  / ___ \\    | |   | |___     | |___   / ___ \\  |  _ <  | |_| |    | |___  | |_| | | |_| | | |\\  |   | |  \n" +
//                " \\___/  |_|     |____/  /_/   \\_\\   |_|   |_____|     \\____| /_/   \\_\\ |_| \\_\\ |____/      \\____|  \\___/   \\___/  |_| \\_|   |_|  " + RESET);
//        System.out.println(GREEN + "[1] Search card by name" + RESET);
//        System.out.println(GREEN + "[2] Search card by card no." + RESET);
//        System.out.println(GREEN + "[0] Back\n" + RESET);
//    }
//
//    /*
//    Method       - displayDisplayCard
//    return type  - void
//    Method Comment : Displays large ASCII art header for "Display Card" with search options
//     */
//    public void displayDisplayCard() {
//        System.out.println(BOLD + BLUE +
//                " ____    ___   ____    ____    _          _     __   __      ____      _      ____    ____  \n" +
//                "|  _ \\  |_ _| / ___|  |  _ \\  | |        / \\    \\ \\ / /     / ___|    / \\    |  _ \\  |  _ \\ \n" +
//                "| | | |  | |  \\___ \\  | |_) | | |       / _ \\    \\ V /     | |       / _ \\   | |_) | | | | |\n" +
//                "| |_| |  | |   ___) | |  __/  | |___   / ___ \\    | |      | |___   / ___ \\  |  _ <  | |_| |\n" +
//                "|____/  |___| |____/  |_|     |_____| /_/   \\_\\   |_|       \\____| /_/   \\_\\ |_| \\_\\ |____/ " + RESET);
//        System.out.println(GREEN + "[1] Search card by name" + RESET);
//        System.out.println(GREEN + "[2] Search card by card no." + RESET);
//        System.out.println(GREEN + "[0] Back\n" + RESET);
//    }
//
//    /*
//    Method               - displayCardToUpdateCount
//    String name          - Name of the card to be modified
//    int collectionCount  - Current number of copies in collection
//    return type          - void
//    Method Comment : Shows the current card name and its count before modification
//     */
//    public void displayCardToUpdateCount(String name, int collectionCount) {
//        System.out.println("\n"+ GREEN + name + RESET + " - " +
//                RED + collectionCount + RESET + " copies in collection\n");
//    }
//
//    /*
//    Method       - displayUpdateCardCountOptions
//    return type  - void
//    Method Comment : Displays modification options (increment/decrement) for card counts
//     */
//    public void displayUpdateCardCountOptions() {
//        String UNDERLINE = "\u001B[4m";
//        System.out.println(BOLD + UNDERLINE + RED + "O P T I O N S" + RESET);
//        System.out.println(GREEN + "[1] Increment Card Count" + RESET);
//        System.out.println(GREEN + "[2] Decrement Card Count" + RESET);
//        System.out.println(GREEN + "[0] Back\n" + RESET);
//    }
//
//    /*
//    Method       - displayCollection
//    return type  - void
//    Method Comment : Shows formatted header for the full collection display with borders
//     */
//    public void displayCollection() {
//        String UNDERLINE = "\u001B[4m";
//        String BOLD = "\u001B[1m";
//
//        String formattedTitle = BOLD + UNDERLINE + RED + "C O L L E C T I O N" + RESET;
//        printBorder();
//        System.out.printf(BLUE + "|" + RESET +" %-57s " + BLUE + "|" + RESET + "\n", formattedTitle);
//        printBorder();
//    }
//
//    /*
//    Method               - displayCollectionCard
//    int collectionCount  - Number of copies in collection
//    String name          - Name of the card
//    return type          - void
//    Method Comment : Prints an individual card entry in collection view with collection count and borders
//     */
//    public void displayCollectionCard(int collectionCount, String name) {
//        String MAGENTA = "\u001B[35m";
//
//        System.out.printf(BLUE + "|" + RESET + GREEN + " %-26s " + RESET + BLUE + "|" + RESET +
//                MAGENTA + " Count : %3d " + RESET + BLUE + "|" + RESET + "\n", name, collectionCount);
//        printBorder();
//    }
//
//    /*
//    Method       - printBorder (private)
//    return type  - void
//    Method Comment : Helper method to print consistent bordered lines for collection displays
//     */
//    private void printBorder() {
//        System.out.println(BLUE + "+" + "-".repeat(42) + "+" + RESET);
//    }
}