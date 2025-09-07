package src.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeckGUI extends Frame {
    private TextField deckNameField;
    private ComboBox deckTypeBox;
    private ComboBox deckListBox;
    private ComboBox cardListBox;
    private Button btnBack;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnView;
    private Button btnRemove;
    private Button btnSell;
    private Label deckNameLabel;
    private Label deckValueLabel;
    private Label collectorMoneyLabel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private boolean isFieldsEmpty;
    private boolean isBoxesEmpty;

    public DeckGUI() {
        super();
        btnBack = new Button("Back");
    }

    public void displayCreateDeck(ActionListener listener) {
        ActionListener boxListener = e -> {
            if (e.getSource() == deckTypeBox) {
                isBoxesEmpty = getDeckType() <= 0;
                updateBtnAdd();
            }
        };
        DocumentListener fieldListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isFieldsEmpty = getDeckName().isEmpty();
                updateBtnAdd();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isFieldsEmpty = getDeckName().isEmpty();
                updateBtnAdd();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        };

        renameWindow("Create a Deck");
        isBoxesEmpty = true;

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        JPanel deckName = new JPanel();
        Label deckNameLabel = new Label("Name: ", Font.PLAIN, 30);
        deckNameField = new TextField();
        setPanel(deckNameLabel, deckNameField, deckName);

        // maybe a checkbox(?)
        JPanel deckType = new JPanel();
        Label deckTypeLabel = new Label("Type: ", Font.PLAIN, 30);
        deckTypeBox = new ComboBox(new String[]{"", "Normal", "Sellable"});
        setPanel(deckTypeLabel, deckTypeBox, deckType);

        deckTypeBox.addActionListener(boxListener);
        deckNameField.getDocument().addDocumentListener(fieldListener);
        setPanels(new JPanel[]{deckName, deckType}, body);

        JPanel options = new JPanel();
        btnAdd = new Button("Add");
        btnAdd.setEnabled(false);
        setPanel(new Button[]{btnBack, btnAdd}, options);

        setActionListener(new Button[]{btnBack, btnAdd}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayDeleteDeck(String[] decksList, ActionListener listener) {
        ActionListener boxListener = e -> {
            if (e.getSource() == deckListBox) {
                btnDelete.setEnabled(deckListBox.getSelectedIndex() != 0);
            }
        };

        renameWindow("Delete a Deck");

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        body.add(Box.createVerticalStrut(15));
        deckListBox = new ComboBox(decksList);
        deckListBox.insertItemAt("", 0);
        deckListBox.setSelectedIndex(0);
        body.add(deckListBox);

        body.add(Box.createVerticalStrut(15));
        btnDelete = new Button("Delete");
        btnDelete.setEnabled(false);
        body.add(btnDelete);

        body.setVisible(true);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);

        options.setVisible(true);

        deckListBox.addActionListener(boxListener);
        setActionListener(new Button[]{btnBack, btnDelete}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayAddCardToDeck(String[] deckNames, String[] cardNames, ActionListener listener) {
        // ActionListener for combo boxes
        ActionListener boxListener = e -> {
            if (e.getSource() == deckListBox || e.getSource() == cardListBox) {
                btnAdd.setEnabled(deckListBox.getSelectedIndex() > 0 && cardListBox.getSelectedIndex() > 0);
            }
        };

        renameWindow("Add Card to Deck");

        // Main panel with vertical layout
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        // Deck selection
        JPanel deckPanel = new JPanel();
        Label deckLabel = new Label("Select Deck:", Font.PLAIN, 25);
        deckListBox = new ComboBox(deckNames);
        deckListBox.insertItemAt("", 0); // Add empty first item
        deckListBox.setSelectedIndex(0); // Select empty item by default
        deckListBox.addActionListener(boxListener); // Add listener here
        setPanel(deckLabel, deckListBox, deckPanel);

        // Card selection
        JPanel cardPanel = new JPanel();
        Label cardLabel = new Label("Select Card:", Font.PLAIN, 25);
        cardListBox = new ComboBox(cardNames);
        cardListBox.insertItemAt("", 0); // Add empty first item
        cardListBox.setSelectedIndex(0); // Select empty item by default
        cardListBox.addActionListener(boxListener); // Add listener here
        setPanel(cardLabel, cardListBox, cardPanel);

        setPanels(new JPanel[]{deckPanel, cardPanel}, body);

        // Button panel
        JPanel options = new JPanel();
        options.setOpaque(false);
        btnAdd = new Button("Add");
        btnAdd.setEnabled(false); // Disable initially

        // Add buttons and listeners
        setPanel(new Button[]{btnBack, btnAdd}, options);
        setActionListener(new Button[]{btnBack, btnAdd}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayRemoveCardFromDeck(String[] deckNames, ActionListener listener) {
        // ActionListener for combo boxes
        ActionListener boxListener = e -> {
            if (e.getSource() == deckListBox) {
                // Notify controller that deck selection changed
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "DeckSelectionChanged"));
                btnRemove.setEnabled(false); // Disable until both selections are made
                if (deckListBox.getSelectedIndex() == 0) {
                    updateCardList(new String[]{}); // Clear card list if no deck selected
                }
            } else if (e.getSource() == cardListBox) {
                btnRemove.setEnabled(deckListBox.getSelectedIndex() > 0 && cardListBox.getSelectedIndex() > 0);
            }
        };

        renameWindow("Remove Card from Deck");

        // Main panel with vertical layout
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        // Deck selection
        JPanel deckPanel = new JPanel();
        Label deckLabel = new Label("Select Deck:", Font.PLAIN, 25);
        deckListBox = new ComboBox(deckNames);
        deckListBox.insertItemAt("", 0);
        deckListBox.setSelectedIndex(0);
        deckListBox.addActionListener(boxListener);
        setPanel(deckLabel, deckListBox, deckPanel);

        // Card selection
        JPanel cardPanel = new JPanel();
        Label cardLabel = new Label("Select Card:", Font.PLAIN, 25);
        cardListBox = new ComboBox(new String[]{""}); // Initialize empty
        cardListBox.addActionListener(boxListener);
        setPanel(cardLabel, cardListBox, cardPanel);

        setPanels(new JPanel[]{deckPanel, cardPanel}, body);

        // Button panel
        JPanel options = new JPanel();
        options.setOpaque(false);
        btnRemove = new Button("Remove");
        btnRemove.setEnabled(false); // Disable initially

        // Add buttons and listeners
        setPanel(new Button[]{btnBack, btnRemove}, options);
        setActionListener(new Button[]{btnBack, btnRemove}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displaySelectDeck(String[] deckNames, ActionListener listener) {
        // ActionListener to track selection changes
        ActionListener boxListener = e -> {
            if (e.getSource() == deckListBox) {
                btnView.setEnabled(deckListBox.getSelectedIndex() != 0);
            }
        };

        renameWindow("Select a Deck");

        // Main content panel
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        // Deck selection combo box
        body.add(Box.createVerticalStrut(20));
        deckListBox = new ComboBox(deckNames);
        deckListBox.insertItemAt("", 0); // Add empty first item
        deckListBox.setSelectedIndex(0); // Default to empty selection
        deckListBox.addActionListener(boxListener); // Track selection changes
        body.add(deckListBox);
        body.add(Box.createVerticalStrut(20));

        // Button panel with both View and Back buttons
        JPanel options = new JPanel();
        options.setOpaque(false);
        btnView = new Button("View");
        btnView.setEnabled(false);

        // Add buttons and listener
        setPanel(new Button[]{btnBack, btnView}, options);
        setActionListener(new Button[]{btnBack, btnView}, listener);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displayViewDeck(String deckName, String deckType, String[] cardNames, ActionListener backListener, ActionListener viewCardListener) {
        // ActionListener to track selection changes
        ActionListener boxListener = e -> {
            if (e.getSource() == cardListBox) {
                btnView.setEnabled(cardListBox.getSelectedIndex() != 0);
            }
        };

        renameWindow("View Deck: " + deckName);

        // Main panel with vertical layout
        JPanel body = new JPanel();
        body.setLayout(new BorderLayout());
        body.setOpaque(false);

        // Deck info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        infoPanel.setOpaque(false);

        Label nameLabel = new Label(deckName, Font.BOLD, 30);
        nameLabel.setForeground(Color.decode("#FF6666")); // Red color
        infoPanel.add(nameLabel);

        Label typeLabel = new Label("Type: " + deckType, Font.PLAIN, 25);
        typeLabel.setForeground(Color.decode("#66B2FF")); // Blue color
        infoPanel.add(typeLabel);

        body.add(infoPanel, BorderLayout.NORTH);
        body.add(Box.createVerticalStrut(20));

        // Cards list panel with scroll
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setOpaque(false);
        cardsPanel.setPreferredSize(new Dimension(450, 0));

        // Add card names with numbers and borders
        for (int i = 0; i < cardNames.length; i++) {
            JPanel cardPanel = new JPanel(new BorderLayout());
            cardPanel.setOpaque(false);
            cardPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#BCBEC4")));
            cardPanel.setBorder(BorderFactory.createCompoundBorder(
                    cardPanel.getBorder(),
                    BorderFactory.createEmptyBorder(0, 15, 0, 0) // Add 15px left padding
            ));

            // Create a panel for the card number and name
            JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            contentPanel.setOpaque(false);

            // Add card number
            Label numberLabel = new Label(String.format("%02d", i+1), Font.PLAIN, 20);
            numberLabel.setForeground(Color.decode("#FF99CC")); // Pink color
            contentPanel.add(numberLabel);

            // Add card name
            Label cardLabel = new Label(cardNames[i], Font.PLAIN, 20);
            cardLabel.setForeground(Color.decode("#CC99FF")); // Purple color
            contentPanel.add(cardLabel);

            cardPanel.add(contentPanel, BorderLayout.WEST);
            cardsPanel.add(cardPanel);
            cardsPanel.add(Box.createVerticalStrut(5));
        }

        // Scroll pane for cards
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setPreferredSize(new Dimension(450, 0));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JPanel cardSelectPanel = new JPanel();
        cardSelectPanel.setOpaque(false);
        cardSelectPanel.setLayout(new BoxLayout(cardSelectPanel, BoxLayout.Y_AXIS));
        cardSelectPanel.setPreferredSize(new Dimension(450, 0));

        cardSelectPanel.add(Box.createVerticalStrut(100));
        cardListBox = new ComboBox(cardNames);
        cardListBox.insertItemAt("", 0);
        cardListBox.setSelectedIndex(0);
        cardSelectPanel.add(cardListBox);

        cardSelectPanel.add(Box.createVerticalStrut(15));
        btnView = new Button("View");
        btnView.addActionListener(viewCardListener);
        cardSelectPanel.add(btnView);

        body.add(scrollPane, BorderLayout.WEST);
        body.add(cardSelectPanel, BorderLayout.EAST);

        // Button panel
        JPanel options = new JPanel();
        options.setOpaque(false);

//        Button btnViewCard = new Button("View Card");
//        btnViewCard.setEnabled(cardNames.length > 0); // Disable if deck is empty
//        btnViewCard.addActionListener(viewCardListener);

        btnBack.addActionListener(backListener);
        options.add(btnBack);
//        setPanel(new Button[]{btnBack, btnViewCard}, options);

        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }

    public void displaySelectCardInDeck(String[] cardNames, ActionListener viewListener, ActionListener backListener) {
        renameWindow("Select a Card from Deck");

        // Main content panel
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setOpaque(false);

        // Card selection combo box
        body.add(Box.createVerticalStrut(20));
        cardListBox = new ComboBox(cardNames);
        cardListBox.insertItemAt("", 0); // Add empty first item
        cardListBox.setSelectedIndex(0); // Default to empty selection
        body.add(cardListBox);
        body.add(Box.createVerticalStrut(20));

        // Button panel with both View and Back buttons
        JPanel options = new JPanel();
        options.setOpaque(false);
        Button btnView = new Button("View");
        btnView.setEnabled(cardNames.length > 0); // Disable if deck is empty
        btnView.addActionListener(viewListener);

        btnBack.addActionListener(backListener);

        // Track selection changes (only needed if deck isn't empty)
        if (cardNames.length > 0) {
            cardListBox.addActionListener(e -> {
                btnView.setEnabled(cardListBox.getSelectedIndex() != 0);
            });
        }

        // Add buttons and listener
        setPanel(new Button[]{btnBack, btnView}, options);
        this.add(body, BorderLayout.CENTER);
        this.add(options, BorderLayout.SOUTH);
    }

    public void displaySellDeck(String[] decksList, double money, ActionListener listener) {
        ActionListener updateListener = e -> {
            if (e.getSource() == deckListBox) {
                rightPanel.setVisible(deckListBox.getSelectedIndex() != 0);
            }
        };

        renameWindow("Sell a Deck");

        leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(450, 0));

        rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(450, 0));

        leftPanel.add(Box.createVerticalStrut(15));
        deckListBox = new ComboBox(decksList);
        deckListBox.setActionCommand("Select");
        deckListBox.insertItemAt("", 0);
        deckListBox.setSelectedIndex(0);
        leftPanel.add(deckListBox);

        leftPanel.add(Box.createVerticalStrut(300));
        Label collectorLabel = new Label("You currently have", Font.PLAIN, 25);
        leftPanel.add(collectorLabel);

        collectorMoneyLabel = new Label("$", Font.BOLD, 25);
        collectorMoneyLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#BCBEC4"), 2, true));
        leftPanel.add(collectorMoneyLabel);
        setCollectorMoneyLabel(money);

        leftPanel.setVisible(true);

        deckNameLabel = new Label("Name", Font.PLAIN, 25);
        rightPanel.add(deckNameLabel);
        deckValueLabel = new Label("Value: $", Font.PLAIN, 25);
        rightPanel.add(deckValueLabel);

        rightPanel.add(Box.createVerticalStrut(15));
        btnSell = new Button("Sell");
        rightPanel.add(btnSell);

        rightPanel.setVisible(false);

        JPanel options = new JPanel();
        options.setOpaque(false);
        options.add(btnBack);
        options.setVisible(true);

        deckListBox.addActionListener(updateListener);
        deckListBox.addActionListener(listener);
        setActionListener(new Button[]{btnBack, btnSell}, listener);

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

    public void setDeckNameLabel(String deckName) {
        deckNameLabel.setText(deckName);
    }

    public void setDeckValueLabel(double value) {
        deckValueLabel.setText("Value: $" + value);
    }

    public void setCollectorMoneyLabel(double money) {
        collectorMoneyLabel.setText("$" + money);
    }

    private void updateBtnAdd() {
        btnAdd.setEnabled(!isFieldsEmpty && !isBoxesEmpty);
    }

    public String getDeckName() {
        if (deckNameField != null) {
            return deckNameField.getText();
        }
        return "";
    }

    public int getDeckType() {
        if (deckTypeBox != null) {
            return deckTypeBox.getSelectedIndex();
        }
        return -1;
    }

    public int getSelectedDeckIndex() {
        if (deckListBox != null) {
            return deckListBox.getSelectedIndex() - 1;
        }
        return -1;
    }
    public int getSelectedCardIndex() {
        if (cardListBox != null) {
            return cardListBox.getSelectedIndex() - 1;
        }
        return -1;
    }

    public String getSelectedDeckName() {
        if (deckListBox != null && deckListBox.getSelectedIndex() > 0) {
            return (String) deckListBox.getSelectedItem();
        }
        return null;
    }

    public String getSelectedCardName() {
        if (cardListBox != null && cardListBox.getSelectedIndex() > 0) {
            return (String) cardListBox.getSelectedItem();
        }
        return null;
    }

    public void removeDeckFromList(int index) {
        if (deckListBox != null && deckListBox.getItemCount() > index) {
            deckListBox.removeItemAt(index);
            deckListBox.setSelectedIndex(0);
            btnDelete.setEnabled(false);
        }
    }

    public void updateCardList(String[] cardNames) {
        cardListBox.setModel(new DefaultComboBoxModel<>(cardNames));
        cardListBox.insertItemAt("", 0);
        cardListBox.setSelectedIndex(0);
    }

    public void resetDisplayCreateDeck(){
        deckNameField.setText("");
        deckTypeBox.setSelectedIndex(0);
        updateBtnAdd();
    }

    public void resetDisplayAddCardToDeck(boolean isDeckFull, int cardCollectionCount) {
        if (isDeckFull) {
            deckListBox.removeItemAt(getSelectedDeckIndex() + 1);
        }
        if (cardCollectionCount == 0) {
            cardListBox.removeItemAt(getSelectedCardIndex() + 1);
        }
        deckListBox.setSelectedIndex(0);
        cardListBox.setSelectedIndex(0);
    }

    public void resetDisplayRemoveCardFromDeck(boolean isDeckEmpty) {
        if (isDeckEmpty) {
            deckListBox.removeItemAt(getSelectedDeckIndex() + 1);
        }
        updateCardList(new String[]{});
        deckListBox.setSelectedIndex(0);
        cardListBox.setSelectedIndex(0);
    }

    public void resetDisplaySellDeck(double money) {
        setCollectorMoneyLabel(money);
        deckListBox.removeItemAt(getSelectedDeckIndex() + 1);
        deckListBox.setSelectedIndex(0);
        rightPanel.setVisible(false);
    }
}
