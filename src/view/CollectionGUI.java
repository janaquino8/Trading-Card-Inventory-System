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
    private TextField valueField;
    private ComboBox rarityBox;
    private ComboBox variantBox;
    private ComboBox cardListBox;
    private Button btnBack;
    private Button btnAddCard;
    private Button btnDisplayCard;
    private Button btnIncrement;
    private Button btnDecrement;
    private Button btnSellCard;
    private Label cardNameLabel;
    private Label cardCountLabel;
    private Label cardValueLabel;
    private JPanel leftPanel;
    private JPanel rightPanel;
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

    public void displayUpdateCardCount(String[] cardsList, ActionListener listener) {
        ActionListener updateListener = e -> {
            if (e.getSource() == cardListBox) {
                if (cardListBox.getSelectedIndex() != 0) {
                    rightPanel.setVisible(true);
                }
                else {
                    rightPanel.setVisible(false);
                }
            }
            if (e.getSource() == btnIncrement || e.getSource() == btnDecrement) {
                btnDecrement.setEnabled(!cardCountLabel.getText().equals("Count: 0"));
            }
        };

        renameWindow("Update Card Count");

        leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(450, 0));

        rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(450, 0));

        leftPanel.add(Box.createVerticalStrut(15));
        cardListBox = new ComboBox(cardsList);
        cardListBox.setActionCommand("Select");
        cardListBox.insertItemAt("", 0);
        cardListBox.setSelectedIndex(0);
        leftPanel.add(cardListBox);

        leftPanel.setVisible(true);

        cardNameLabel = new Label("Name", Font.PLAIN, 25);
        rightPanel.add(cardNameLabel);
        cardCountLabel = new Label("Count: ", Font.PLAIN, 25);
        rightPanel.add(cardCountLabel);

        rightPanel.add(Box.createVerticalStrut(15));
        JPanel updateButtonsPanel = new JPanel();
        btnIncrement = new Button("Increment");
        btnDecrement = new Button("Decrement");
        setPanel(new Button[]{btnIncrement, btnDecrement}, updateButtonsPanel);
        rightPanel.add(updateButtonsPanel);

        rightPanel.setVisible(false);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);
        options.setVisible(true);

        cardListBox.addActionListener(updateListener);
        setActionListener(new Button[]{btnIncrement, btnDecrement}, updateListener);
        cardListBox.addActionListener(listener);
        setActionListener(new Button[]{btnBack, btnIncrement, btnDecrement}, listener);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayCard(String[] cardsList, ActionListener listener) {
        ActionListener boxListener = e -> {
            if (e.getSource() == cardListBox) {
                if (cardListBox.getSelectedIndex() == 0) {
                    btnDisplayCard.setEnabled(false);
                }
                else {
                    btnDisplayCard.setEnabled(true);
                }
            }
        };

        renameWindow("Display a Card");

        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        body.add(Box.createVerticalStrut(15));
        cardListBox = new ComboBox(cardsList);
        cardListBox.insertItemAt("", 0);
        cardListBox.setSelectedIndex(0);
        body.add(cardListBox);

        body.add(Box.createVerticalStrut(15));
        btnDisplayCard = new Button("Display");
        btnDisplayCard.setEnabled(false);
        body.add(btnDisplayCard);

        body.setVisible(true);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);

        options.setVisible(true);

        cardListBox.addActionListener(boxListener);
        setActionListener(new Button[]{btnBack, btnDisplayCard}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayCollection(String[] cardsList, ActionListener listener) {
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
        btnBack.addActionListener(listener);
        options.setVisible(true);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displaySellCard(String[] cardsList, ActionListener listener) {
        ActionListener updateListener = e -> {
            if (e.getSource() == cardListBox) {
                if (cardListBox.getSelectedIndex() != 0) {
                    rightPanel.setVisible(true);
                }
                else {
                    rightPanel.setVisible(false);
                }
            }
            if (e.getSource() == btnIncrement) {
                btnDecrement.setEnabled(!cardCountLabel.getText().equals("Count: 0"));
            }
            if (e.getSource() == btnDecrement) {
                btnDecrement.setEnabled(!cardCountLabel.getText().equals("Count: 0"));
            }
        };

        renameWindow("Sell a Card");

        leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(450, 0));

        rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(450, 0));

        leftPanel.add(Box.createVerticalStrut(15));
        cardListBox = new ComboBox(cardsList);
        cardListBox.setName("Select");
        cardListBox.insertItemAt("", 0);
        cardListBox.setSelectedIndex(0);
        leftPanel.add(cardListBox);

        leftPanel.setVisible(true);

        cardNameLabel = new Label("Name", Font.PLAIN, 25);
        rightPanel.add(cardNameLabel);
        cardCountLabel = new Label("Count: ", Font.PLAIN, 25);
        rightPanel.add(cardCountLabel);

        rightPanel.add(Box.createVerticalStrut(15));
        JPanel updateButtonsPanel = new JPanel();
        btnIncrement = new Button("Increment");
        btnDecrement = new Button("Decrement");
        setPanel(new Button[]{btnIncrement, btnDecrement}, updateButtonsPanel);
        rightPanel.add(updateButtonsPanel);

        rightPanel.setVisible(false);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);
        options.setVisible(true);

        cardListBox.addActionListener(updateListener);
        cardListBox.addActionListener(listener);
        setActionListener(new Button[]{btnIncrement, btnDecrement}, updateListener);
        setActionListener(new Button[]{btnBack, btnIncrement, btnDecrement}, listener);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
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

    public void setCardNameField(String cardName) {
        cardNameField.setText(cardName);
    }

    public void setCardCountField(int cardCount) {
        cardCountLabel.setText("Count: " + cardCount);
        if (cardCount == 0) {
            btnDecrement.setEnabled(false);
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
    
    public int getSelectedCardIndex() {
        if (cardListBox != null) {
            return cardListBox.getSelectedIndex() - 1;
        }
        return -1;
    }
}
