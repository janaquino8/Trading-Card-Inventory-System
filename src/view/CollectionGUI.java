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
public class CollectionGUI extends Frame {
    private Button[] buttons;
    private TextField cardNameField;
    private ComboBox rarityBox;
    private ComboBox variantBox;
    private TextField valueField;
    private Button btnBack;
    private Button btnAddCard;
    private Button btnDisplayCard;
    private Button btnIncrement;
    private Button btnDecrement;
    private Button btnSellCard;
    private Label cardNameLabel;
    private Label cardCountLabel;
    private Label cardValueLabel;
    private boolean isFieldsEmpty;
    private boolean isBoxesEmpty;

    public CollectionGUI() {
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
                isFieldsEmpty = getCardName().isEmpty() || valueField.getText().isEmpty();
                updateBtnAdd();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isFieldsEmpty = getCardName().isEmpty() || valueField.getText().isEmpty();
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

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayUpdateCardCount(String[] cardsList) {
        renameWindow("Update Card Count");

        JPanel cardSelectPanel = new JPanel();
        cardSelectPanel.setOpaque(false);
        cardSelectPanel.setLayout(new BoxLayout(cardSelectPanel, BoxLayout.Y_AXIS));
        cardSelectPanel.setPreferredSize(new Dimension(450, 0));

        JPanel updatePanel = new JPanel();
        updatePanel.setOpaque(false);
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
        updatePanel.setPreferredSize(new Dimension(450, 0));

        cardSelectPanel.add(Box.createVerticalStrut(15));
        Label selectLabel = new Label("Select a card to update.", Font.PLAIN, 30);
        cardSelectPanel.add(selectLabel);

        cardSelectPanel.add(Box.createVerticalStrut(15));
        ComboBox cardList = new ComboBox(cardsList);
        cardList.insertItemAt("", 0);
        cardList.setSelectedIndex(0);
        cardList.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardSelectPanel.add(cardList);

        cardSelectPanel.setVisible(true);

        cardNameLabel = new Label("");
        updatePanel.add(cardNameLabel);
        cardCountLabel = new Label("");
        updatePanel.add(cardCountLabel);

        JPanel updateButtonsPanel = new JPanel();
        btnIncrement = new Button("Increment");
        btnDecrement = new Button("Decrement");
        setPanel(new Button[]{btnIncrement, btnDecrement}, updateButtonsPanel);
        updatePanel.add(updateButtonsPanel);

        updatePanel.setVisible(true);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);
        options.setVisible(true);

        this.add(cardSelectPanel, BorderLayout.WEST);
        this.add(updatePanel, BorderLayout.EAST);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayCard(String[] cardsList) {
        renameWindow("Display a Card");

        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        body.add(Box.createVerticalStrut(15));
        Label selectLabel = new Label("Select a card to display.", Font.PLAIN, 30);
        body.add(selectLabel);

        body.add(Box.createVerticalStrut(15));
        ComboBox cardList = new ComboBox(cardsList);
        cardList.insertItemAt("", 0);
        cardList.setSelectedIndex(0);
        cardList.setAlignmentX(Component.CENTER_ALIGNMENT);
        body.add(cardList);

        body.add(Box.createVerticalStrut(15));
        btnDisplayCard = new Button("Display");
        btnDisplayCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDisplayCard.setEnabled(false);
        body.add(btnDisplayCard);

        body.setVisible(true);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);
        options.setVisible(true);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayCollection(String[] cardsList) {
        renameWindow("Collection");

        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        body.add(Box.createVerticalStrut(15));
        List list = new List(cardsList);
        JScrollPane scroll = new JScrollPane(list);
        body.add(scroll);
        body.setVisible(true);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);
        options.setVisible(true);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displaySellCard(String[] cardsList) {
        renameWindow("Sell a Card");

        JPanel cardSelectPanel = new JPanel();
        cardSelectPanel.setOpaque(false);
        cardSelectPanel.setLayout(new BoxLayout(cardSelectPanel, BoxLayout.Y_AXIS));
        cardSelectPanel.setPreferredSize(new Dimension(450, 0));

        JPanel sellPanel = new JPanel();
        sellPanel.setOpaque(false);
        sellPanel.setLayout(new BoxLayout(sellPanel, BoxLayout.Y_AXIS));
        sellPanel.setPreferredSize(new Dimension(450, 0));

        cardSelectPanel.add(Box.createVerticalStrut(15));
        Label selectLabel = new Label("Select a card to sell.", Font.PLAIN, 30);
        cardSelectPanel.add(selectLabel);

        cardSelectPanel.add(Box.createVerticalStrut(15));
        ComboBox cardList = new ComboBox(cardsList);
        cardList.insertItemAt("", 0);
        cardList.setSelectedIndex(0);
        cardList.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardSelectPanel.add(cardList);

        cardSelectPanel.setVisible(true);

        cardNameLabel = new Label("");
        sellPanel.add(cardNameLabel);
        cardValueLabel = new Label("");
        sellPanel.add(cardValueLabel);

        btnSellCard = new Button("Sell");
        sellPanel.add(btnSellCard);

        sellPanel.setVisible(true);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);
        options.setVisible(true);

        this.add(cardSelectPanel, BorderLayout.WEST);
        this.add(sellPanel, BorderLayout.EAST);
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
        btnAddCard.setEnabled(!isFieldsEmpty && !isBoxesEmpty && getValue() >= 0);
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
        } else {
            return Double.parseDouble(valueField.getText());
        }
    }
}
